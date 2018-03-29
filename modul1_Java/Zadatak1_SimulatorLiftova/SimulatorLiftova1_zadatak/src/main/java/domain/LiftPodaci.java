package domain;

import java.util.List;
import java.util.Random;

public class LiftPodaci
{
	public LiftPodaci(int brojLiftova, int brojSpratova)
	{
		this.brojLiftova = brojLiftova;
		this.brojSpratova = brojSpratova;
		//kreiranje katova
		for (int i = 0; i < brojSpratova; i++)
		{
			new Sprat(this);
		}

		//kreiranje liftova
		for (int i = 0; i < brojLiftova; i++)
		{
			int r = Math.abs(new Random().nextInt() % brojSpratova);
			new Lift(this, r);
		}

		//kreiranje tipkih i lampicu (u svakom liftu za svaki sprat)
		for (Lift lift : liftovi)
		{
			for (Sprat sprat : spratovi)
			{
				new TipkaULiftu(lift, sprat);
				new LampicaULiftu(lift, sprat);
			}
		}
	}

	
	/** @pdOid df9328f5-e980-4591-824a-0ad0be26922d */
	public final int brojLiftova;
	/** @pdOid 13ce857b-a3a8-486c-82f4-1c7392573a56 */
	public final int brojSpratova;

	/**
	 * @pdRoleInfo migr=no name=Lift assc=relationship1 coll=java.util.List
	 *             impl=java.util.ArrayList mult=1..*
	 */
	public final List<Lift> liftovi = new java.util.ArrayList<Lift>();
	/**
	 * @pdRoleInfo migr=no name=Sprat assc=relationship2 coll=java.util.List
	 *             impl=java.util.ArrayList mult=2..*
	 */
	public final List<Sprat> spratovi = new java.util.ArrayList<Sprat>();

	public final List<Putnik> putnici = new java.util.ArrayList<Putnik>();
	/**
	 * @pdGenerated default add
	 * @param newLift
	 */
	public void addLiftovi(Lift newLift)
	{
		if (newLift == null)
			return;
		if (!this.liftovi.contains(newLift))
		{
			this.liftovi.add(newLift);
			newLift.setSistemLifta(this);
		}
	}


	/**
	 * @pdGenerated default add
	 * @param sprat
	 */
	public void addSpratovi(Sprat sprat)
	{
		if (sprat == null)
			return;
		if (!this.spratovi.contains(sprat))
		{
			this.spratovi.add(sprat);
			sprat.setSistemLifta(this);
		}
	}
	
	
	  public void addPutnik(Putnik putnik) {
	      if (putnik == null)
	         return;
	      if (!this.putnici.contains(putnik))
	      {
	         this.putnici.add(putnik);
	         putnik.setLiftPodaci(this);      
	      }
	   }
	   
	   /** @pdGenerated default remove
	     * @param putnik */
	   public void removePutnik(Putnik putnik) {
	      if (putnik == null)
	         return;
	      if (this.putnici != null)
	         if (this.putnici.contains(putnik))
	         {
	            this.putnici.remove(putnik);
	            putnik.setLiftPodaci((LiftPodaci)null);
	         }
	   }

}