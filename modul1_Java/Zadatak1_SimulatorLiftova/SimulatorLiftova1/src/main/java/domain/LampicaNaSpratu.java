package domain;

/***********************************************************************
 * Module:  LampicaNaSpratu.java
 * Author:  Admin
 * Purpose: Defines the Class LampicaNaSpratu
 ***********************************************************************/

import domain.enums.SmjerLifta;

/** @pdOid a2d161b7-9b37-4131-a4f8-a1a5fd22c73e */
public class LampicaNaSpratu extends Lampica
{

	public LampicaNaSpratu(Sprat sprat, SmjerLifta smjerLifta)
	{
		super();
		this.sprat = sprat;
		this.smjerLifta = smjerLifta;
	}
	/** @pdRoleInfo migr=no name=Sprat assc=association14 mult=1..1 */
	public final Sprat sprat;
	/** @pdRoleInfo migr=no name=SmjerLifta assc=association21 mult=1..1 */
	public final SmjerLifta smjerLifta;
	/** @pdRoleInfo migr=no name=Sprat assc=association18 mult=1..1 side=A */

}