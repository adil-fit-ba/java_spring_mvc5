package domain;

/***********************************************************************
 * Module:  LampicaZaSmjerLifta.java
 * Author:  Admin
 * Purpose: Defines the Class LampicaZaSmjerLifta
 ***********************************************************************/

/** @pdOid 3f0698aa-e8b4-4a7a-83b1-68a41facd4cf */
public class LampicaZaSmjerLifta extends Lampica
{
	public LampicaZaSmjerLifta(Lift lift)
	{
		this.lift = lift;
	}

	/** @pdRoleInfo migr=no name=Lift assc=relationship8 mult=1..1 side=A */
	public final Lift lift;
	/** @pdRoleInfo migr=no name=SmjerLifta assc=association17 mult=1..1 side=A */

}