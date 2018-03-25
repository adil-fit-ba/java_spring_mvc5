package domain;

/***********************************************************************
 * Module:  TipkaULiftu.java
 * Author:  Admin
 * Purpose: Defines the Class TipkaULiftu
 ***********************************************************************/

/** @pdOid 538d9714-ecf4-4225-a388-ef735ed602d9 */
public class TipkaULiftu extends Tipka
{

	public Lift lift;
	/** @pdRoleInfo migr=no name=Sprat assc=relationship7 mult=1..1 side=A */
	public Sprat sprat;

	public TipkaULiftu(Lift lift, Sprat sprat)
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
		this.lift.addTipke_u_liftu(this);
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newKat
	 */
	public void setSprat(Sprat newKat)
	{
		this.sprat = newKat;
		this.sprat.addTipke_u_liftu(this);
	}

}