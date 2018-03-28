package domain;

/***********************************************************************
 * Module:  Lampica.java
 * Author:  Admin
 * Purpose: Defines the Class Lampica
 ***********************************************************************/

/***********************************************************************
 * Module:  Lampica.java
 * Author:  Admin
 * Purpose: Defines the Class Lampica
 ***********************************************************************/

import domain.enums.NaredbeLampice;
import domain.enums.StatusLampice;

/** @pdOid 1fcbcdf3-60f5-4b42-a49c-6164ebfb11e6 */
public abstract class Lampica
{
	public Lampica()
	{
		naredbeLampice = NaredbeLampice.NISTA;
		statusLampice = StatusLampice.UGASENA;
	}

	/**
	 * @pdRoleInfo migr=no name=StatusLampice assc=relationship14 mult=1..1
	 *             side=A
	 */
	public StatusLampice statusLampice;
	/**
	 * @pdRoleInfo migr=no name=NaredbeLampice assc=relationship15 mult=1..1
	 *             side=A
	 */
	public NaredbeLampice naredbeLampice;

}