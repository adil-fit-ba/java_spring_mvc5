package domain;

import domain.enums.SmjerLifta;

/***********************************************************************
 * Module:  Sprat.java
 * Author:  Admin
 * Purpose: Defines the Class Sprat
 ***********************************************************************/

/** @pdOid 71c4457b-c4e0-459c-95ec-04884c1302da */
public class Sprat
{
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "Sprat " + id;
	}
	public final int id;

	public Sprat(LiftPodaci liftPodaci)
	{
		setSistemLifta(liftPodaci);
		this.lampica_dole = new LampicaNaSpratu(this, SmjerLifta.DOLE);
		this.lampica_gore = new LampicaNaSpratu(this, SmjerLifta.GORE);
		this.tipka_gore =  new TipkaNaSpratu(this);
		this.tipka_dole =  new TipkaNaSpratu(this);
		this.id = liftPodaci.spratovi.indexOf(this);
	}

	/**
	 * @pdRoleInfo migr=no name=TipkaULiftu assc=relationship7
	 *             coll=java.util.List impl=java.util.ArrayList mult=1..*
	 */
	public final java.util.List<TipkaULiftu> tipke_u_liftu = new java.util.ArrayList<TipkaULiftu>();
	/**
	 * @pdRoleInfo migr=no name=LampicaULiftu assc=association15
	 *             coll=java.util.List impl=java.util.ArrayList mult=1..*
	 */
	public final java.util.List<LampicaULiftu> lampice_u_liftu = new java.util.ArrayList<LampicaULiftu>();
	/** @pdRoleInfo migr=no name=LampicaNaSpratu assc=association18 mult=1..1 */
	public final LampicaNaSpratu lampica_dole;
	/** @pdRoleInfo migr=no name=TipkaNaSpratu assc=association19 mult=1..1 */
	public final TipkaNaSpratu tipka_dole;
	/** @pdRoleInfo migr=no name=SistemLifta assc=relationship2 mult=1..1 side=A */
	public LiftPodaci liftPodaci;
	/** @pdRoleInfo migr=no name=TipkaNaSpratu assc=relationship6 mult=1..1 side=A */
	public final TipkaNaSpratu tipka_gore;
	/**
	 * @pdRoleInfo migr=no name=LampicaNaSpratu assc=association14 mult=1..1
	 *             side=A
	 */
	public final LampicaNaSpratu lampica_gore;


	/**
	 * @pdGenerated default add
	 * @param newTipkaULiftu
	 */
	public void addTipke_u_liftu(TipkaULiftu newTipkaULiftu)
	{
		if (newTipkaULiftu == null)
			return;
		if (!this.tipke_u_liftu.contains(newTipkaULiftu))
		{
			this.tipke_u_liftu.add(newTipkaULiftu);
			newTipkaULiftu.setSprat(this);
		}
	}

	/**
	 * @pdGenerated default add
	 * @param newLampicaULiftu
	 */
	public void addLampice_u_liftu(LampicaULiftu newLampicaULiftu)
	{
		if (newLampicaULiftu == null)
			return;
		if (!this.lampice_u_liftu.contains(newLampicaULiftu))
		{
			this.lampice_u_liftu.add(newLampicaULiftu);
			newLampicaULiftu.setSprat(this);
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
		this.liftPodaci.addSpratovi(this);
	}

}