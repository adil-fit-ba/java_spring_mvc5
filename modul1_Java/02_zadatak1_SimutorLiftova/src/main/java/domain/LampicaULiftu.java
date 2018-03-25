package domain;

/***********************************************************************
 * Module:  LampicaULiftu.java
 * Author:  Admin
 * Purpose: Defines the Class LampicaULiftu
 ***********************************************************************/

/** @pdOid bb29376f-bfe0-4872-b9d1-488fe97b8417 */
public class LampicaULiftu extends Lampica
{
	/** @pdRoleInfo migr=no name=Lift assc=association16 mult=1..1 */
	public Lift lift;
	/** @pdRoleInfo migr=no name=Sprat assc=association15 mult=1..1 side=A */
	public Sprat sprat;

	public LampicaULiftu(Lift lift, Sprat sprat)
	{
		setLift(lift);
		setSprat(sprat);
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newLift
	 */
	public void setLift(Lift newLift)
	{
		this.lift = newLift;
		this.lift.addLampice_u_liftu(this);
	}

	/** @pdGenerated default parent getter */

	/**
	 * @pdGenerated default parent setter
	 * @param sprat
	 */
	public void setSprat(Sprat sprat)
	{
		this.sprat = sprat;
		this.sprat.addLampice_u_liftu(this);
	}

}