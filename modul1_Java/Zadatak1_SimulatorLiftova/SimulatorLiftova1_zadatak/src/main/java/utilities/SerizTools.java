package utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class SerizTools
{
	/**
	 * 
	 */

	public static void writeObject(Object object)
	{
		try
		{
			// use buffering
			OutputStream file = new FileOutputStream("quarks.ser");
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			try
			{
				output.writeObject(object);
			} finally
			{
				output.close();
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	public static Object readObject()
	{
		Object object = null;
		try
		{
			// use buffering
			InputStream file = new FileInputStream("quarks.ser");
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			try
			{
				// deserialize the List
				object = input.readObject();
				// display its data
			} finally
			{
				input.close();
			}
		} catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return object;
	}
}
