package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Za ispis logova koristite ovu klasu. Trenutno su funkcije implementirane samo za ispis u konzolu.
 *  
 * @author Adil
 *
 */
public class FLogger
{
	private FLogger(){}
	
	@SuppressWarnings("unchecked")
	public static void warning(Object obj, String strMsg)
	{
		Class type = null;
		if(obj instanceof Class)
			type = (Class)obj;
		else
			type = obj.getClass();
		
		String strDate = new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		System.out.println("warn: <<" + type.getName() + ">> " +  strDate + ": " + strMsg);
	}
	
	@SuppressWarnings("unchecked")
	public static void error(Object obj, String strMsg)
	{
		Class type = null;
		if(obj instanceof Class)
			type = (Class)obj;
		else
			type = obj.getClass();
		
		String strDate = new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		System.out.println("error: <<" + type.getName() + ">> " +  strDate + ": " + strMsg);
		throw new ExceptionInInitializerError();
	}
	
	@SuppressWarnings("unchecked")
	public static void info(Object obj, String strMsg)
	{
		Class type = null;
		if(obj instanceof Class)
			type = (Class)obj;
		else
			type = obj.getClass();
		
		String strDate = new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		System.out.println("info: <<" + type.getName() + ">> " +  strDate + ": " + strMsg);
	}
}
