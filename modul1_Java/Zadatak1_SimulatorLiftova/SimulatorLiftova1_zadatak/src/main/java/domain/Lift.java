package domain;

/***********************************************************************
 * Module:  Lift.java
 * Author:  Admin
 * Purpose: Defines the Class Lift
 ***********************************************************************/


import domain.enums.NaredbeLifta;
import domain.enums.StanjaLifta;
import domain.enums.StanjaPolusprata;
import domain.enums.StanjaVrata;

/** @pdOid 3dce746d-ae9c-4c0a-8d51-602ff5b7de78 */
public class Lift
{
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "Lift: " + id + ", stanje: " + stanjaLifta + ", naredba: " + naredbeLifta + ", pozicija: " + pozicija;
	}
	/** @pdOid 8d178c65-f080-4b4a-9274-4aad25084325 */
	public int pozicija;
	
	
	
	public final int id;

	public Lift(LiftPodaci liftPodaci, int pozicija)
	{
		this.setSistemLifta(liftPodaci);
		this.pozicija = pozicija;
		this.lampica_dole = new LampicaZaSmjerLifta(this);
		this.lampica_gore = new LampicaZaSmjerLifta(this);
		this.stanjaLifta = StanjaLifta.STOJI_OTVOREN;
		this.naredbeLifta = NaredbeLifta.NISTA;
		this.stanjaVrata = StanjaVrata.OTVORENA;
		this.stanjaPolusprata = StanjaPolusprata.NEMA;
		this.id = liftPodaci.liftovi.indexOf(this) + 1;
	}

	/**
	 * @pdRoleInfo migr=no name=TipkaULiftu assc=relationship4
	 *             coll=java.util.List impl=java.util.ArrayList mult=2..*
	 */
	public java.util.List<TipkaULiftu> tipke_u_liftu = new java.util.ArrayList<TipkaULiftu>();
	/** @pdRoleInfo migr=no name=LampicaZaSmjerLifta assc=relationship8 mult=1..1 */
	public final LampicaZaSmjerLifta lampica_gore;
	/** @pdRoleInfo migr=no name=LampicaZaSmjerLifta assc=association20 mult=1..1 */
	public final LampicaZaSmjerLifta lampica_dole;
	/** @pdRoleInfo migr=no name=SistemLifta assc=relationship1 mult=1..1 side=A */
	public LiftPodaci liftPodaci;
	/** @pdRoleInfo migr=no name=StanjaLifta assc=relationship10 mult=1..1 side=A */
	public StanjaLifta stanjaLifta;
	/**
	 * @pdRoleInfo migr=no name=NaredbeLifta assc=relationship11 mult=1..1
	 *             side=A
	 */
	public NaredbeLifta naredbeLifta;
	/** @pdRoleInfo migr=no name=StanjaVrata assc=relationship12 mult=1..1 side=A */
	public StanjaVrata stanjaVrata;
	/**
	 * @pdRoleInfo migr=no name=StanjaPolusprata assc=relationship13 mult=1..1
	 *             side=A
	 */
	public StanjaPolusprata stanjaPolusprata;
	/**
	 * @pdRoleInfo migr=no name=LampicaULiftu assc=association16
	 *             coll=java.util.List impl=java.util.ArrayList mult=2..* side=A
	 */
	public java.util.List<LampicaULiftu> lampice_u_liftu = new java.util.ArrayList<LampicaULiftu>();




	/** @pdGenerated default getter */

	/**
	 * @pdGenerated default add
	 * @param newTipkaULiftu
	 */
	public void addTipke_u_liftu(TipkaULiftu newTipkaULiftu)
	{
		if (newTipkaULiftu == null)
			return;
		if (this.tipke_u_liftu == null)
			this.tipke_u_liftu = new java.util.ArrayList<TipkaULiftu>();
		if (!this.tipke_u_liftu.contains(newTipkaULiftu))
		{
			this.tipke_u_liftu.add(newTipkaULiftu);
			newTipkaULiftu.setLift(this);
		}
	}

	/** @pdGenerated default parent getter */
	public LiftPodaci getSistemLifta()
	{
		return liftPodaci;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newSistemLifta
	 */
	public void setSistemLifta(LiftPodaci newSistemLifta)
	{
		this.liftPodaci = newSistemLifta;
		this.liftPodaci.addLiftovi(this);
	}


	/**
	 * @pdGenerated default add
	 * @param newLampicaULiftu
	 */
	public void addLampice_u_liftu(LampicaULiftu newLampicaULiftu)
	{
		if (newLampicaULiftu == null)
			return;
		if (this.lampice_u_liftu == null)
			this.lampice_u_liftu = new java.util.ArrayList<LampicaULiftu>();
		if (!this.lampice_u_liftu.contains(newLampicaULiftu))
		{
			this.lampice_u_liftu.add(newLampicaULiftu);
			newLampicaULiftu.setLift(this);
		}
	}



	   
}