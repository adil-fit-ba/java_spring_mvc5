package simulators;

import domain.LampicaULiftu;
import domain.Lift;
import domain.LiftPodaci;
import domain.Sprat;
import domain.enums.NaredbeLampice;
import domain.enums.StatusLampice;

public class SimulatorLampica implements Runnable
{

	private final LiftPodaci liftPodaci;

	public SimulatorLampica(LiftPodaci liftPodaci)
	{
		this.liftPodaci = liftPodaci;
	}

	@Override
	public void run()
	{
		while (true)
		{
			for (Sprat sprat : liftPodaci.spratovi)
			{
				if (sprat.lampica_dole.naredbeLampice != NaredbeLampice.NISTA)
				{
					if (sprat.lampica_dole.naredbeLampice == NaredbeLampice.UGASI)
					{
						sprat.lampica_dole.statusLampice = StatusLampice.UGASENA;
					} else
					{
						sprat.lampica_dole.statusLampice = StatusLampice.UPALJENA;
					}

					sprat.lampica_dole.naredbeLampice = NaredbeLampice.NISTA;
				}

				if (sprat.lampica_gore.naredbeLampice != NaredbeLampice.NISTA)
				{
					if (sprat.lampica_gore.naredbeLampice == NaredbeLampice.UGASI)
					{
						sprat.lampica_gore.statusLampice = StatusLampice.UGASENA;
					} else
					{
						sprat.lampica_gore.statusLampice = StatusLampice.UPALJENA;
					}

					sprat.lampica_gore.naredbeLampice = NaredbeLampice.NISTA;
				}
			}

			for (Lift lift : liftPodaci.liftovi)
			{
				if (lift.lampica_gore.naredbeLampice != NaredbeLampice.NISTA)
				{
					if (lift.lampica_gore.naredbeLampice == NaredbeLampice.UGASI)
					{
						lift.lampica_gore.statusLampice = StatusLampice.UGASENA;
					} else
					{
						lift.lampica_gore.statusLampice = StatusLampice.UPALJENA;
					}

					lift.lampica_gore.naredbeLampice = NaredbeLampice.NISTA;
				}

				if (lift.lampica_dole.naredbeLampice != NaredbeLampice.NISTA)
				{
					if (lift.lampica_dole.naredbeLampice == NaredbeLampice.UGASI)
					{
						lift.lampica_dole.statusLampice = StatusLampice.UGASENA;
					} else
					{
						lift.lampica_dole.statusLampice = StatusLampice.UPALJENA;
					}

					lift.lampica_dole.naredbeLampice = NaredbeLampice.NISTA;
				}

				for (LampicaULiftu lampicaULiftu : lift.lampice_u_liftu)
				{
					if (lampicaULiftu.naredbeLampice != NaredbeLampice.NISTA)
					{
						if (lampicaULiftu.naredbeLampice == NaredbeLampice.UGASI)
						{
							lampicaULiftu.statusLampice = StatusLampice.UGASENA;
						} else
						{
							lampicaULiftu.statusLampice = StatusLampice.UPALJENA;
						}

						lampicaULiftu.naredbeLampice = NaredbeLampice.NISTA;
					}
				}
			}
			Sleep(50);
		}
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

}
