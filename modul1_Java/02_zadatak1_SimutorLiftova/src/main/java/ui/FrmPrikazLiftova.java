package ui;


import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.pgroup.PGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ui._Starter.Bool;
import utilities.FComboBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import domain.Lift;
import domain.LiftPodaci;
import domain.Sprat;
import domain.enums.NaredbeLampice;

public class FrmPrikazLiftova
{
	protected Shell shell;

	private LiftPodaci liftPodaci = null;
	private FComboBox<Lift> cmbLiftovi;
	private FComboBox<Sprat> cmbKatovi;
	private Button btnTipkaULiftu;

	private Bool pauza;

	private Button btnGeneriiNoveOsobe;

	public FrmPrikazLiftova()
	{

	}

	/**
	 * Open the window.
	 * @param pauza 
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void open(LiftPodaci liftPodaci, Bool pauza)
	{
		this.pauza = pauza;
		Display display = Display.getDefault();
		this.liftPodaci = liftPodaci;
		createContents();
		shell.open();
		shell.layout();

		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	protected void do_btnDodijeliNaredbu_widgetSelected(SelectionEvent arg0)
	{
		cmbLiftovi.getSelectedID().lampice_u_liftu.get(cmbKatovi.getSelectedID().id).naredbeLampice = NaredbeLampice.UPALI;
	}

	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents()
	{
		shell = new Shell();
		shell.setSize(800, 523);
		shell.setText("Simulacija liftova");
		shell.setLayout(new GridLayout(1, false));

		PGroup group = new PGroup(shell, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group.setText("Kretanje liftova");
		group.setLayout(new FillLayout(SWT.HORIZONTAL));

		final TableViewer tblVKretanjeLiftova = new TableViewer(group, SWT.BORDER | SWT.FULL_SELECTION);
		tblVKretanjeLiftova.getTable().setHeaderVisible(true);

		PGroup group_3 = new PGroup(shell, SWT.NONE);
		group_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		group_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		group_3.setText("Lampice za smjer lifta");

		final TableViewer tblVLampiceZaSmjerLifta = new TableViewer(group_3, SWT.BORDER | SWT.FULL_SELECTION);
		tblVLampiceZaSmjerLifta.getTable().setHeaderVisible(true);

		PGroup group_2 = new PGroup(shell, SWT.NONE);
		group_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		group_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		group_2.setText("Lampice u liftovima");

		final TableViewer tblVLampiceLiftoviKatovi = new TableViewer(group_2, SWT.BORDER | SWT.FULL_SELECTION);
		tblVLampiceLiftoviKatovi.getTable().setHeaderVisible(true);

		PGroup group_1 = new PGroup(shell, SWT.NONE);
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		group_1.setText("Kontrola lifta");

		cmbLiftovi = new FComboBox<Lift>(group_1, SWT.NONE);
		cmbLiftovi.setBounds(10, 47, 170, 23);

		cmbKatovi = new FComboBox<Sprat>(group_1, SWT.NONE);
		cmbKatovi.setBounds(208, 47, 162, 23);

		btnTipkaULiftu = new Button(group_1, SWT.NONE);
		btnTipkaULiftu.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				do_btnDodijeliNaredbu_widgetSelected(arg0);
			}
		});
		btnTipkaULiftu.setBounds(393, 45, 104, 25);
		btnTipkaULiftu.setText("Pritisni tipku");

		// my code
		if (liftPodaci == null)
			return;
		new TblLampiceLiftoviSprat(liftPodaci, tblVLampiceLiftoviKatovi);
		new TblLampiceSmjerLiftova(liftPodaci, tblVLampiceZaSmjerLifta);
		new TblLiftoviKretanja(liftPodaci, tblVKretanjeLiftova);
		for (Lift lift : liftPodaci.liftovi)
		{
			cmbLiftovi.add("Lift " + lift.id, lift);
		}
		
		cmbKatovi.add(liftPodaci.spratovi);
		cmbKatovi.select(0);
		cmbLiftovi.select(0);
		
		btnGeneriiNoveOsobe = new Button(group_1, SWT.CHECK);
		btnGeneriiNoveOsobe.setSelection(true);
		btnGeneriiNoveOsobe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				do_btnGeneriiNoveOsobe_widgetSelected(arg0);
			}
		});
		btnGeneriiNoveOsobe.setBounds(516, 49, 127, 16);
		btnGeneriiNoveOsobe.setText("Generi\u0161i nove putnici");

		Runnable runnable = new Runnable()
		{
			@Override
			public void run()
			{
				tblVLampiceZaSmjerLifta.refresh();
				tblVLampiceLiftoviKatovi.refresh();
				tblVKretanjeLiftova.refresh();
				Display.getCurrent().timerExec(100, this);
			}
		};

		runnable.run();
		do_btnGeneriiNoveOsobe_widgetSelected(null);
	}
	protected void do_btnGeneriiNoveOsobe_widgetSelected(SelectionEvent arg0) {
		pauza.value = !btnGeneriiNoveOsobe.getSelection();
	}
}
