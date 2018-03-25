package ui;

import icons.Icons;

import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableColumn;

import simulators.Common;

import domain.Lift;
import domain.LiftPodaci;
import domain.Putnik;
import domain.Sprat;


public class TblLiftoviKretanja
{
	LiftPodaci liftPodaci;
	TableViewer tableViewer;

	public TblLiftoviKretanja(LiftPodaci liftPodaci, TableViewer tableViewer)
	{
		this.liftPodaci = liftPodaci;
		this.tableViewer = tableViewer;
		createColumns();
		createContentProvider();
		createLabelProvider();
		tableViewer.setInput(liftPodaci);
	}

	private void createColumns()
	{
		for (int i = 0; i < liftPodaci.brojLiftova; i++)
		{
			TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
			column.setWidth(80);
			column.setText("Lift " + (i + 1));
		}

		TableColumn column_o = new TableColumn(tableViewer.getTable(), SWT.NONE);
		column_o.setWidth(100);
		column_o.setText("Putnik �eka");

		TableColumn column_gore = new TableColumn(tableViewer.getTable(), SWT.NONE);
		column_gore.setWidth(50);
		column_gore.setText("Gore ");

		TableColumn column_dole = new TableColumn(tableViewer.getTable(), SWT.NONE);
		column_dole.setWidth(70);
		column_dole.setText("Dole ");
		
		TableColumn column_b = new TableColumn(tableViewer.getTable(), SWT.NONE);
		column_b.setWidth(100);
		column_b.setText("Putnik odlazi");
	}

	private void createContentProvider()
	{
		tableViewer.setContentProvider(new IStructuredContentProvider()
		{

			public Object[] getElements(Object input)
			{
				List<Sprat> katovi = liftPodaci.spratovi;
				int size = katovi.size();
				Sprat[] niz = new Sprat[size];
				for (int i = 0; i < size; i++)
				{
					niz[i] = katovi.get(size - i - 1);
				}
				return niz;
			}

			public void dispose()
			{

			}

			public void inputChanged(Viewer arg0, Object arg1, Object arg2)
			{

			}
		});
	}

	private void createLabelProvider()
	{
		tableViewer.setLabelProvider(new ITableLabelProvider()
		{
			@Override
			public Image getColumnImage(Object obj, int kolona)
			{
				if (obj instanceof Sprat)
				{
					Sprat sprat = (Sprat) obj;

					if (liftPodaci.brojLiftova == kolona)
					{
						// ako je kolona za osobu koja dolazi
						return Icons.osoba_na_spratu_dolazi(Common.dolaziOsobu(sprat));
					}

					if (liftPodaci.brojLiftova + 1 == kolona)
					{// ako je kolona za lampicu gore
						if (sprat.id == liftPodaci.brojSpratova-1)
							return Icons.nemaLampice();
						else
						return Icons.lampica(sprat.lampica_gore.statusLampice);
					}

					if (liftPodaci.brojLiftova + 2 == kolona)
					{// ako je kolona za lampicu dole
						if (sprat.id == 0)
							return Icons.nemaLampice();
						else
							return Icons.lampica(sprat.lampica_dole.statusLampice);
					}
					
					if (liftPodaci.brojLiftova + 3 == kolona)
					{
						// ako je kolona za osobu koja dolazi
						return Icons.osoba_na_spratu_odlazi(Common.odlaziOsobu(sprat));
					}

					// onda je kolona za liftove
					Lift lift = liftPodaci.liftovi.get(kolona);
					if (lift.pozicija == sprat.id)
						return Icons.lift_ikonica(Common.sadrziOsobu(lift), lift.stanjaVrata, lift.stanjaPolusprata);
					else
						return Icons.lift_nije_tu();

				}

				return null;
			}

			@Override
			public String getColumnText(Object obj, int kolona)
			{
				// Lift lift = sistemLifta.liftovi.get(brLifta);

				if (obj instanceof Sprat)
				{
					Sprat sprat = (Sprat) obj;
					// Lift lift = liftPodaci.liftovi.get(brLifta);
					if (liftPodaci.brojLiftova == kolona)
					{// ako je kolona za osobu
						List<Putnik> osobeNaKatu = Common.getOsobe(sprat);
						if (osobeNaKatu.size() > 0)
							return sprat.id + " (za: " + osobeNaKatu.get(0).odrediste + ")";
						else
							return sprat.id + "";
					}

					if (liftPodaci.brojLiftova + 1 == kolona)
					{// ako je kolona za lampicu gore

						return "";
					}

					if (liftPodaci.brojLiftova + 2 == kolona)
					{// ako je kolona za lampicu dole

						return sprat.id + "";
					}

					return sprat.id + "";
				}

				return null;
			}

			@Override
			public void addListener(ILabelProviderListener arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void dispose()
			{
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isLabelProperty(Object arg0, String arg1)
			{
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void removeListener(ILabelProviderListener arg0)
			{
				// TODO Auto-generated method stub

			}
		});
	}
}
