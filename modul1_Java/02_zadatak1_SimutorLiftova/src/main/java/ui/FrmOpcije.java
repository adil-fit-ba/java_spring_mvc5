package ui;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Canvas;

public class FrmOpcije extends Shell
{

	private Combo cmbBrojSpratova;
	private Combo cmbBrojLiftova;
	private Rezultat rezultat;
	private Combo cmbBrojOsoba;

	/**
	 * Create the shell.
	 * @param display
	 */
	public FrmOpcije(Display display)
	{
		super(display, SWT.CLOSE | SWT.TITLE);
		setImage(SWTResourceManager.getImage(FrmOpcije.class, "/icons/lift_door_open_person.gif"));
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		cmbBrojSpratova = new Combo(this, SWT.NONE);
		cmbBrojSpratova.setBounds(110, 32, 91, 23);
		cmbBrojSpratova.setText("5");
		
		cmbBrojLiftova = new Combo(this, SWT.NONE);
		cmbBrojLiftova.setBounds(110, 61, 91, 23);
		cmbBrojLiftova.setText("2");
		
		Button btnSimuliraj = new Button(this, SWT.NONE);
		btnSimuliraj.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				do_btnSimuliraj_widgetSelected(arg0);
			}
		});
		btnSimuliraj.setBounds(110, 125, 75, 25);
		btnSimuliraj.setText("Simuliraj");
		
		Label lblBrojKatova = new Label(this, SWT.NONE);
		lblBrojKatova.setBounds(10, 35, 94, 15);
		lblBrojKatova.setText("Broj spratova");
		
		Label lblBrojLiftova = new Label(this, SWT.NONE);
		lblBrojLiftova.setBounds(10, 64, 91, 15);
		lblBrojLiftova.setText("Broj liftova");
		
		cmbBrojOsoba = new Combo(this, SWT.NONE);
		cmbBrojOsoba.setItems(new String[] {"1", "2", "3", "4", "5"});
		cmbBrojOsoba.setBounds(110, 90, 91, 23);
		cmbBrojOsoba.setText("1");
		
		Label lblBrojOsoba = new Label(this, SWT.NONE);
		lblBrojOsoba.setBounds(10, 93, 55, 15);
		lblBrojOsoba.setText("Broj osoba");
		
		Canvas canvas = new Canvas(this, SWT.NONE);
		canvas.setBackgroundImage(SWTResourceManager.getImage(FrmOpcije.class, "/icons/Elevator_icon.jpg"));
		canvas.setBounds(238, 0, 100, 150);
		
		Label lblAdilJoldi = new Label(this, SWT.NONE);
		lblAdilJoldi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblAdilJoldi.setBounds(10, 146, 75, 15);
		lblAdilJoldi.setText("Adil Joldi\u0107");
		
		Label lblHarisBalta = new Label(this, SWT.NONE);
		lblHarisBalta.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblHarisBalta.setBounds(10, 161, 75, 15);
		lblHarisBalta.setText("Haris Balta");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents()
	{
		setText("Simulator liftova");
		setSize(359, 211);
		
		cmbBrojLiftova.add("1");
		for (int i = 2; i < 30; i++)
		{
			cmbBrojSpratova.add(i + "");
			cmbBrojLiftova.add(i + "");
		}
		
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
	protected void do_btnSimuliraj_widgetSelected(SelectionEvent arg0) {
		rezultat = new Rezultat(Integer.parseInt(cmbBrojSpratova.getText()), Integer.parseInt(cmbBrojLiftova.getText()), Integer.parseInt(cmbBrojOsoba.getText()));
		close();
	}
	
	public static Rezultat start()
	{
			Display display = Display.getDefault();
			FrmOpcije shell = new FrmOpcije(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed())
			{
				if (!display.readAndDispatch())
				{
					display.sleep();
				}
			}
			return shell.rezultat; 
	}
	
	public static class Rezultat
	{
		public final int brojSpratova;
		public final int brojLiftova;
		public final int brojOsoba;
		public Rezultat(int brojSpratova, int brojLiftova, int brojOsoba)
		{
			this.brojSpratova = brojSpratova;
			this.brojLiftova = brojLiftova;
			this.brojOsoba = brojOsoba;
		}
	}
}
