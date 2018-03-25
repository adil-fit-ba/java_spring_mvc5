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

import domain.LampicaULiftu;
import domain.LiftPodaci;
import domain.Sprat;



public class TblLampiceLiftoviSprat
{
	LiftPodaci liftPodaci;
	TableViewer tableViewer;
	public TblLampiceLiftoviSprat(LiftPodaci liftPodaci, TableViewer tableViewer)
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
					niz[i] = katovi.get(size-i-1);
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
			public Image getColumnImage(Object objKat, int brLifta)
			{
				Sprat sprat = (Sprat) objKat;
				LampicaULiftu lampicaULiftu = sprat.lampice_u_liftu.get(brLifta);
				
					return Icons.lampica(lampicaULiftu.statusLampice);
			}
	
			@Override
			public String getColumnText(Object objKat, int brLifta)
			{
				Sprat sprat = (Sprat) objKat;
//				Lift lift = sistemLifta.liftovi.get(brLifta);
					return sprat.id + "";
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
