package domain;

/***********************************************************************
 * Module:  TipkaNaSpratu.java
 * Author:  Admin
 * Purpose: Defines the Class TipkaNaSpratu
 ***********************************************************************/


/** @pdOid bb9242ad-684c-4635-a2fb-cd23b8082c76 */
public class TipkaNaSpratu extends Tipka
{
	public TipkaNaSpratu(Sprat sprat)
	{
		this.sprat = sprat;
	}

	/** @pdRoleInfo migr=no name=Sprat assc=relationship6 mult=1..1 */
	public final Sprat sprat;

}