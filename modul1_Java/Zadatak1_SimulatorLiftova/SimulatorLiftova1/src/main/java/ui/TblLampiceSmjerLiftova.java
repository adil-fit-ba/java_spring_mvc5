package ui;

import icons.Icons;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableColumn;

import domain.Lift;
import domain.LiftPodaci;
import domain.enums.SmjerLifta;


public class TblLampiceSmjerLiftova
{
	LiftPodaci liftPodaci;
	TableViewer tableViewer;
	public TblLampiceSmjerLiftova(LiftPodaci liftPodaci, TableViewer tableViewer)
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
				
				SmjerLifta[] niz = new SmjerLifta[2];
				//predzadnji red
				niz [0] = SmjerLifta.GORE;
				//zadnji red
				niz[1] = SmjerLifta.DOLE;
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
			public Image getColumnImage(Object obj, int brLifta)
			{
				Lift lift = liftPodaci.liftovi.get(brLifta);
				
				if (obj instanceof SmjerLifta)
				{
					SmjerLifta smjerLifta = (SmjerLifta) obj;
					//ako je predzadnji red
					if (smjerLifta == SmjerLifta.GORE)
						return Icons.lampica(lift.lampica_gore.statusLampice);
					
					//ako je zadnji red
					if (smjerLifta == SmjerLifta.DOLE)
						return Icons.lampica(lift.lampica_dole.statusLampice);
				}
	
				return null;
			}
	
			@Override
			public String getColumnText(Object obj, int brLifta)
			{
				
			//	Lift lift = sistemLifta.liftovi.get(brLifta);
				
				if (obj instanceof SmjerLifta)
				{
					SmjerLifta smjerLifta = (SmjerLifta) obj;
					//ako je predzadnji red
					if (smjerLifta == SmjerLifta.GORE)
						return "GORE";
					
					//ako je zadnji red
					if (smjerLifta == SmjerLifta.DOLE)
						return "DOLE";
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
