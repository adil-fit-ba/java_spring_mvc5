package ui;

import java.util.ArrayList;
import java.util.List;

import domain.Lift;
import domain.LiftPodaci;


import simulators.SimulatorKretanjeLifta;
import simulators.SimulatorLampica;
import simulators.SimulatorPutnika;
import simulators.SimulatorTipki;
import ui.FrmOpcije.Rezultat;

public class _Starter
{
	public static class Bool
	{
		public boolean value = true;
	}

	static Bool pauza = new Bool();
	
	private static List<Thread> threads = new ArrayList<Thread>();

	public static void main(String args[])
	{
		Rezultat rezultat = FrmOpcije.start();
		if (rezultat == null)
			return;
		LiftPodaci liftPodaci = new LiftPodaci(rezultat.brojLiftova, rezultat.brojSpratova);
		dodajSimulatore(liftPodaci, rezultat.brojOsoba);
		startThreads();
		new FrmPrikazLiftova().open(liftPodaci, pauza);
		stopThreads();
	}

	private static void startThreads()
	{
		for (Thread thread : threads)
		{
			thread.start();
		}
	}

	@SuppressWarnings("deprecation")
	private static void stopThreads()
	{
		for (Thread thread : threads)
		{
			thread.stop();
		}
	}

	private static void dodajSimulatore(LiftPodaci liftPodaci, int brojOsoba)
	{
		for (Lift lift : liftPodaci.liftovi)
			threads.add(new Thread(new SimulatorKretanjeLifta(lift)));

		for (int i=0; i<brojOsoba; i++)
			threads.add(new Thread(new SimulatorPutnika(liftPodaci, pauza)));
		
		threads.add(new Thread(new SimulatorLampica(liftPodaci)));
		
		threads.add(new Thread(new SimulatorTipki(liftPodaci)));
		
	}
}
