package icons;


import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

import domain.enums.StanjaPolusprata;
import domain.enums.StanjaVrata;
import domain.enums.StatusLampice;


public class Icons
{
	public static Image lift_nije_tu()
	{
		return SWTResourceManager.getImage(Icons.class, "no_lift.gif");
	}

	public static Image osoba_na_spratu_dolazi(boolean osoba_waiting)
	{
		if (osoba_waiting)
			return SWTResourceManager.getImage(Icons.class, "person_waiting_left.gif");
		else
			return SWTResourceManager.getImage(Icons.class, "person_no.gif");
	}
	
	public static Image osoba_na_spratu_odlazi(boolean osoba_waiting)
	{
		if (osoba_waiting)
			return SWTResourceManager.getImage(Icons.class, "person_waiting_right.gif");
		else
			return SWTResourceManager.getImage(Icons.class, "person_no.gif");
	}
	
	public static Image lift_ikonica(boolean prikaz_osobe, StanjaVrata stanjaVrata, StanjaPolusprata stanjaPolusprata)
	{
		if (stanjaVrata == StanjaVrata.OTVORENA)
		{
			if (prikaz_osobe)
				return SWTResourceManager.getImage(Icons.class, "lift_door_open_person.gif");
			else
				return SWTResourceManager.getImage(Icons.class, "lift_door_open_nobody.gif");
		} else
		{
			if (prikaz_osobe)
			{
				switch (stanjaPolusprata)
				{
				case GORE:
					return SWTResourceManager.getImage(Icons.class, "lift_door_closed_person_(polukat_iznad).gif");
				case DOLE:
					return SWTResourceManager.getImage(Icons.class, "lift_door_closed_person_(polukat_ispod).gif");
				default:
					return SWTResourceManager.getImage(Icons.class, "lift_door_closed_person_(polukat_0).gif");
				}
			}
			else
			{
				switch (stanjaPolusprata)
				{
				case GORE:
					return SWTResourceManager.getImage(Icons.class, "lift_door_closed_nobody_(polukat_iznad).gif");
				case DOLE:
					return SWTResourceManager.getImage(Icons.class, "lift_door_closed_nobody_(polukat_ispod).gif");
				default:
					return SWTResourceManager.getImage(Icons.class, "lift_door_closed_nobody_(polukat_0).gif");
				}
			}
		}
	}

	public static Image lampica(StatusLampice statusLampice)
	{
		if (statusLampice == StatusLampice.UPALJENA)
			return SWTResourceManager.getImage(Icons.class, "light_on.gif");
		else
			return SWTResourceManager.getImage(Icons.class, "light_off.gif");
	}

	public static Image nemaLampice()
	{
		return SWTResourceManager.getImage(Icons.class, "light_no.gif");
	}
}
