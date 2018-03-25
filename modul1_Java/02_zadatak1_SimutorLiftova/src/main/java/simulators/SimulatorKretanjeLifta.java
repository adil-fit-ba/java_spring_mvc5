package simulators;

import java.util.Random;

import domain.LampicaULiftu;
import domain.Lift;
import domain.enums.NaredbeLampice;
import domain.enums.NaredbeLifta;
import domain.enums.StanjaLifta;
import domain.enums.StanjaPolusprata;
import domain.enums.StanjaVrata;
import domain.enums.StatusLampice;

import utilities.FLogger;


public class SimulatorKretanjeLifta implements Runnable
{
	private final Lift lift;

	public SimulatorKretanjeLifta(final Lift lift)
	{
		this.lift = lift;
	}

	public void run()
	{

		String printStari = "";
		String printNovi = "";
		while (true)
		{
			printStari = printNovi;
			printNovi = lift.toString();
			if (!printStari.equals(printNovi))
			{
				FLogger.info(this, "simualor lifta, before: " + printStari);
				FLogger.info(this, "simualor lifta, after : " + printNovi);
			}
			switch (lift.stanjaLifta)
			{
			case STOJI_OTVOREN:
				naredba_lampice_za_smjer(false, false);
				staAko_stoji_otvoren();
				do_stoji_otvoren();
				break;
			case STOJI_ZATVOREN:
				naredba_lampice_za_smjer(false, false);
				staAko_stoji_zatvoren();
				do_stoji_zatvoren();
				break;
			case GORE_IDE:
				naredba_lampice_za_smjer(true, false);
				staAko_gore_ide();
				do_gore_ide();
				break;
			case DOLE_IDE:
				naredba_lampice_za_smjer(false, true);
				staAko_dole_ide();
				do_dole_ide();
				break;
			default:
				break;
			}
		}
	}

	private void staAko_gore_ide()
	{
		//ako je lampica za naredni kad upaljena
		if (lift.lampice_u_liftu.get(lift.pozicija+1).statusLampice == StatusLampice.UPALJENA)
			lift.naredbeLifta = NaredbeLifta.GORE_USPORI;
		else
			lift.naredbeLifta = NaredbeLifta.GORE_IDI;
	}

	private void do_gore_ide()
	{
		if (lift.pozicija == lift.liftPodaci.brojSpratova - 2 && (lift.naredbeLifta != NaredbeLifta.GORE_USPORI))
		{
			FLogger.warning(this, lift + " Treba stati na zadnjem spratu!");
			lift.naredbeLifta = NaredbeLifta.GORE_USPORI;
			//exit(1);
		}
		switch (lift.naredbeLifta)
		{
		case GORE_IDI:
			lift.stanjaLifta = StanjaLifta.GORE_JURI;
			Sleep(1000);
			povecajSprat();
			lift.stanjaPolusprata = StanjaPolusprata.NEMA;
			Sleep(1000);
			lift.stanjaPolusprata = StanjaPolusprata.GORE;
			lift.stanjaLifta = StanjaLifta.GORE_IDE;
			break;
		default:
			if (lift.naredbeLifta != NaredbeLifta.GORE_USPORI)
			{
				FLogger.warning(this, lift + " Greska u naredbi: stanje=STOJI_ZATVOREN, naredba=" + lift.naredbeLifta);
				exit(1);
			}
			/*
			 * treba usporiti i stati na sljede�em spratu
			 */
			lift.stanjaLifta = StanjaLifta.GORE_USPORAVA;
			Sleep(2000);
			lift.stanjaLifta = StanjaLifta.STOJI_ZATVOREN;
			lift.naredbeLifta = NaredbeLifta.OTVORI;
			povecajSprat();
			lift.stanjaPolusprata = StanjaPolusprata.NEMA;
			break;
		}
	
	}

	private void staAko_dole_ide()
	{
		//ako je lampica ispod upaljena
		if (lift.lampice_u_liftu.get(lift.pozicija-1).statusLampice == StatusLampice.UPALJENA)
			lift.naredbeLifta = NaredbeLifta.DOLE_USPORI;
		else
			lift.naredbeLifta = NaredbeLifta.DOLE_IDI;
	}

	private void do_dole_ide()
	{
		//		 
		if (lift.pozicija == 1 && (lift.naredbeLifta != NaredbeLifta.DOLE_USPORI))
		{
			FLogger.warning(this, lift + " Ni�e ne mo�e! " );
			lift.naredbeLifta = NaredbeLifta.DOLE_USPORI; 
			//exit(1); 
		}
		switch (lift.naredbeLifta)
		{
		case DOLE_IDI:
			lift.stanjaLifta = StanjaLifta.DOLE_JURI;
			Sleep(1000);
			umanjiSprat();
			lift.stanjaPolusprata = StanjaPolusprata.NEMA;
			Sleep(1000);
			lift.stanjaPolusprata = StanjaPolusprata.DOLE;
			lift.stanjaLifta = StanjaLifta.DOLE_IDE;
			break;
		default:
			if (lift.naredbeLifta != NaredbeLifta.DOLE_USPORI)
			{
				FLogger.warning(this, lift + " Greska u  naredbi:  stanje=DOLE_IDE,  naredba=" + lift.naredbeLifta);
				exit(1);
			}
			/*
			 * treba usporiti i stati na sljede�em spratu
			 */
			lift.stanjaLifta = StanjaLifta.DOLE_USPORAVA;
			Sleep(2000);
			lift.stanjaLifta = StanjaLifta.STOJI_ZATVOREN;
			umanjiSprat();
			lift.stanjaPolusprata = StanjaPolusprata.NEMA;
			lift.naredbeLifta = NaredbeLifta.OTVORI;
			break;
		}
	
	}

	private void staAko_stoji_zatvoren()
	{
		if (lift.naredbeLifta == NaredbeLifta.OTVORI )
		{
			return;
		}
		
		int spratoviIznad = 0;
		int spratoviIspod = 0;
		for (int i = 0; i < lift.pozicija; i++)
		{
			if (lift.lampice_u_liftu.get(i).statusLampice == StatusLampice.UPALJENA)
				spratoviIspod++;
		}

		for (int i = lift.pozicija + 1; i < lift.liftPodaci.brojSpratova; i++)
		{
			if (lift.lampice_u_liftu.get(i).statusLampice == StatusLampice.UPALJENA)
				spratoviIznad++;
		}
		
		if (spratoviIspod == 0 && spratoviIznad == 0)
		{
			lift.naredbeLifta = NaredbeLifta.OTVORI;
			return;
		}
		// odluka; gore ili dole?
		int daljinaGore = Math.abs(Common.getMaxSprat(lift,lift.pozicija) - lift.pozicija);
		int DaljinaDole = Math.abs(Common.getMinSprat(lift, lift.pozicija) - lift.pozicija);
		if (daljinaGore < DaljinaDole)
		{
			lift.naredbeLifta = NaredbeLifta.GORE_UBRZAJ;
			return;
		}
		if (daljinaGore > DaljinaDole)
		{
			lift.naredbeLifta = NaredbeLifta.DOLE_UBRZAJ;
			return;
		}


		if (spratoviIspod > spratoviIznad)
			lift.naredbeLifta = NaredbeLifta.DOLE_UBRZAJ;
		if (spratoviIspod < spratoviIznad)
			lift.naredbeLifta = NaredbeLifta.GORE_UBRZAJ;

		if (new Random().nextInt() % 2 == 0)
			lift.naredbeLifta = NaredbeLifta.DOLE_UBRZAJ;
		else
			lift.naredbeLifta = NaredbeLifta.GORE_UBRZAJ;
	}

	private void do_stoji_zatvoren()
	{
		switch (lift.naredbeLifta)
		{
		case OTVORI:
			lift.stanjaLifta = StanjaLifta.OTVARA;
			Sleep(1000);
			lift.stanjaVrata = StanjaVrata.OTVORENA;
			lift.stanjaLifta = StanjaLifta.STOJI_OTVOREN;
			lift.naredbeLifta = NaredbeLifta.NISTA;
			ugasiLampice();
			break;
		case GORE_UBRZAJ:
			if (lift.pozicija == lift.liftPodaci.brojSpratova - 1)
			{
				FLogger.warning(this, lift + " Ne mo�e vi�e gore! " );
				lift.stanjaLifta = StanjaLifta.STOJI_ZATVOREN;
				lift.naredbeLifta = NaredbeLifta.OTVORI;
				exit(1);
			} else
			{
				lift.stanjaLifta = StanjaLifta.GORE_UBRZAVA;
				Sleep(2000);
				lift.stanjaPolusprata = StanjaPolusprata.GORE;
				lift.stanjaLifta = StanjaLifta.GORE_IDE;
			}
			break;
		case DOLE_UBRZAJ:
			if (lift.pozicija == 0)
			{
				FLogger.warning(this, lift + " Ne mo�e vi�e dole! " );
				lift.stanjaLifta = StanjaLifta.STOJI_ZATVOREN;
				lift.naredbeLifta = NaredbeLifta.OTVORI;
				exit(1);
			} else
			{
				lift.stanjaLifta = StanjaLifta.DOLE_UBRZAVA;
				Sleep(2000);
				lift.stanjaPolusprata = StanjaPolusprata.DOLE;
				lift.stanjaLifta = StanjaLifta.DOLE_IDE;
			}
			break;
		case ZATVORI: /* toleriram */
		case GORE_USPORI:
		case DOLE_USPORI:
		case NISTA:
			Sleep(100);
			break;
		default:
			FLogger.warning(this, lift + " Greska u naredbi: stanje=STOJI_ZATVOREN, naredba=" + lift.naredbeLifta);
			exit(1);
		}
	}

	private void staAko_stoji_otvoren()
	{
		for (LampicaULiftu lampicaULiftu : lift.lampice_u_liftu)
		{
			if (lampicaULiftu.statusLampice == StatusLampice.UPALJENA)
			{
				lift.naredbeLifta = NaredbeLifta.ZATVORI;
				return;
			}
		}	
		lift.naredbeLifta = NaredbeLifta.OTVORI;
	}

	private void do_stoji_otvoren()
	{
		switch (lift.naredbeLifta)
		{
		case ZATVORI:
			Sleep(1000); /* jo� je 1s otvoren */
			lift.stanjaLifta = StanjaLifta.ZATVARA;
			lift.stanjaVrata = StanjaVrata.ZATVORENA;
			Sleep(1000);
			lift.stanjaLifta = StanjaLifta.STOJI_ZATVOREN;
			ugasiLampice();
			break;
		case NISTA:
		case OTVORI: /* toleriram */
			Sleep(100);
			break;
		default:
			FLogger.warning(this, lift + " Greska u naredbi: stanje=STOJI_ZATVOREN, naredba=" + lift.naredbeLifta);
			exit(1);
		}
	
	}

	private void naredba_lampice_za_smjer(boolean gore, boolean dole)
	{
		if (gore)
			lift.lampica_gore.naredbeLampice = NaredbeLampice.UPALI;
		else
			lift.lampica_gore.naredbeLampice = NaredbeLampice.UGASI;
		
		if (dole)
			lift.lampica_dole.naredbeLampice = NaredbeLampice.UPALI;
		else
			lift.lampica_dole.naredbeLampice = NaredbeLampice.UGASI;
	}

	private void ugasiLampice()
	{
		lift.liftPodaci.spratovi.get(lift.pozicija).lampica_dole.naredbeLampice = NaredbeLampice.UGASI;
		lift.liftPodaci.spratovi.get(lift.pozicija).lampica_gore.naredbeLampice = NaredbeLampice.UGASI;
		LampicaULiftu lampicaULiftu = lift.lampice_u_liftu.get(lift.pozicija);
		lampicaULiftu.naredbeLampice = NaredbeLampice.UGASI;
	}
	
	private void umanjiSprat()
	{
		lift.pozicija--;
	}

	private void povecajSprat()
	{
		lift.pozicija++;
	}

	private void Sleep(int i)
	{
		try
		{
			Thread.sleep(i);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private void exit(int i)
	{
		//throw new ExceptionInInitializerError();
	}
}
