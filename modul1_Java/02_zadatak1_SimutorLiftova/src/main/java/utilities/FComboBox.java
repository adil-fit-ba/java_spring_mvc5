package utilities;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

/**
 * Ovdje testiramo prosirenje postojecih SWT-kontrola. Ovu klasu ne morate koristiti u seminarskom radu iz SI-a.
 * FComboBox prima kao izvor podataka listu tipa T.
 * 
 * Ovo je doradjeni FCombobox koji je kompatibilan sa novim Window Builder 7.0
 * 
 * v3
 * @author Adil
 *
 * @param <T>
 */

public class FComboBox<T> extends Combo
{
	ArrayList<T> arrayListID = new ArrayList<T>();

	public FComboBox(Composite parent, int s)
	{
		super(parent, SWT.READ_ONLY);
		addNullValue();
	}

	public FComboBox(Composite parent, Collection<T> collection)
	{
		super(parent, SWT.READ_ONLY);
		this.add(collection);
	}

	public void add(Collection<T> collection)
	{
		addNullValue();
		for (T t : collection)
		{
			add(t.toString(), t);
		}
	}

	@Override
	public void add(String text)
	{
		System.err.println("Nemojte koristiti funkciju add(String), " +
				"vec koristite funkciju add(String, T), jer " +
				"T predstavlja tip podatka za ID");
	}
	
	public void add(String text, T id)
	{
		addNullValue();
		super.add(text);
		arrayListID.add(id);
	}

	private void addNullValue()
	{
//		if (this.arrayListID.size() == 0)
//		{
//			super.add("");
//			arrayListID.add(null);
//			select(0);
//		}
	}

	public T getSelectedID()
	{
		try
		{
			return arrayListID.get(getSelectionIndex());
		} catch (Exception e)
		{
		}
		return null;
	}

	public T getIdByIndex(int i)
	{
		return arrayListID.get(i);
	}

	public void selectByID(T id)
	{
		int index = arrayListID.indexOf(id);
		if (index == -1)
		{
			if (id == null)
			{
				selectByID(id);
			}
		}
		select(index);
	}

	@Override
	public void removeAll()
	{
		this.arrayListID.clear();
		super.removeAll();
	}

	@Override
	public void remove(int i)
	{
		if (i < arrayListID.size())
		{
			this.arrayListID.remove(i);
			super.remove(i);
		}
	}

	public void remove_byID(T id)
	{
		int index = arrayListID.indexOf(id);
		if (index != -1)
		{
			remove(index);
		}
	}

	@Override
	public void remove(String arg0)
	{
		//ne koristimo
	}

	@Override
	public void remove(int arg0, int arg1)
	{
		//ne koristimo
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
