package domain;

/***********************************************************************
 * Module:  Putnik.java
 * Author:  Admin
 * Purpose: Defines the Class Putnik
 ***********************************************************************/


/** @pdOid 325ccdc0-2fd1-414a-ab60-e8cc7e7b9cf0 */
public class Putnik
{
	
	public Putnik(int odrediste, int polaziste, LiftPodaci liftPodaci)
	{
		setLiftPodaci(liftPodaci);
		this.polozajLift = -1;
		this.polozajSprat = polaziste;
		this.odrediste = odrediste;
		this.polaziste = polaziste;
		this.napusio = false;
		System.out.println("kreirana osoba. polaziste: " + polaziste + ", odrediste: " + odrediste);
	}


	/** @pdOid 0b421953-896e-4cb7-986c-e0f6886c9a4f */
	public int polozajLift;
	/** @pdOid 9586c2ce-8a88-446c-b8c7-dfab1c29bff6 */
	public int polozajSprat;
	/** @pdOid 2fe80146-bcd4-4e77-85de-74f3324c4b39 */
	public int odrediste;
	/** @pdOid bce2a1ef-a45d-4624-a489-5f15257d2bd7 */
	public int polaziste;
	/** @pdOid 1bda939a-fbbd-4b20-a31d-f1c4b6bd821c */
	public boolean napusio;

	/** @pdRoleInfo migr=no name=LiftPodaci assc=association23 mult=0..1 side=A */
	public LiftPodaci liftPodaci;


	/**
	 * @pdGenerated default parent setter
	 * @param newLiftPodaci
	 */
	public void setLiftPodaci(LiftPodaci newLiftPodaci)
	{
		if (this.liftPodaci == null || !this.liftPodaci.equals(newLiftPodaci))
		{
			if (this.liftPodaci != null)
			{
				LiftPodaci oldLiftPodaci = this.liftPodaci;
				this.liftPodaci = null;
				oldLiftPodaci.removePutnik(this);
			}
			if (newLiftPodaci != null)
			{
				this.liftPodaci = newLiftPodaci;
				this.liftPodaci.addPutnik(this);
			}
		}
	}

}