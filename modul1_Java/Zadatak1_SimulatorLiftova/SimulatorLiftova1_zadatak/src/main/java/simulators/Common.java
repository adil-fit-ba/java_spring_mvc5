package simulators;

import java.util.ArrayList;
import java.util.List;

import domain.LampicaULiftu;
import domain.Lift;
import domain.Putnik;
import domain.Sprat;
import domain.enums.StatusLampice;

public class Common
{

	public static int getMaxSprat(Lift lift)
	{
		int i;
		for (i = lift.lampice_u_liftu.size()-1; i >=0; i--)
		{
			LampicaULiftu lampicaULiftu = lift.lampice_u_liftu.get(i);
			if (lampicaULiftu.statusLampice == StatusLampice.UPALJENA)
				return i;
			
		}
		return lift.liftPodaci.brojSpratova;
	}

	public static int getMinSprat(Lift lift)
	{
		int i;
		for (i = 0; i < lift.lampice_u_liftu.size(); i++)
		{
			LampicaULiftu lampicaULiftu = lift.lampice_u_liftu.get(i);
			if (lampicaULiftu.statusLampice == StatusLampice.UPALJENA)
				return i;
			
		}
		return lift.liftPodaci.brojSpratova;
	}

	public static int getMinSprat(Lift lift, int pozicija)
	{
		int i;
		for (i = 0; i < pozicija; i++)
		{
			LampicaULiftu lampicaULiftu = lift.lampice_u_liftu.get(i);
			if (lampicaULiftu.statusLampice == StatusLampice.UPALJENA)
				return i;
			
		}
		return lift.liftPodaci.brojSpratova;
	}

	public static int getMaxSprat(Lift lift, int pozicija)
	{
		int i;
		for (i = lift.lampice_u_liftu.size()-1; i >pozicija; i--)
		{
			LampicaULiftu lampicaULiftu = lift.lampice_u_liftu.get(i);
			if (lampicaULiftu.statusLampice == StatusLampice.UPALJENA)
				return i;
			
		}
		return 2* lift.liftPodaci.brojSpratova;
	}

	public static boolean dolaziOsobu(Sprat sprat)
	{
		for (Putnik putnik : sprat.liftPodaci.putnici)
		{
			if (putnik.polozajSprat == sprat.id && putnik.polozajLift == -1)
				return true;
		}
		return false;
	}

	public static List<Putnik> getOsobe(Sprat sprat)
	{
		/**
		 * vra�a broj osoba na katu (izvan lifta)
		 */
		List<Putnik> osobe = new ArrayList<Putnik>();
		for (Putnik putnik : sprat.liftPodaci.putnici)
		{
			if (putnik.polozajSprat == sprat.id && putnik.polozajLift == -1)
				osobe.add(putnik);
		}
		return osobe;
	}

	public static boolean odlaziOsobu(Sprat sprat)
	{
		for (Putnik putnik : sprat.liftPodaci.putnici)
		{
			if (putnik.polozajSprat == sprat.id && putnik.polozajLift == -2)
				return true;
		}
		return false;
	}

	public static boolean sadrziOsobu(Lift lift)
	{
		for (Putnik putnik : lift.liftPodaci.putnici)
		{
			if (putnik.polozajLift == lift.id )
				return true;
		}
		return false;
	}

}
