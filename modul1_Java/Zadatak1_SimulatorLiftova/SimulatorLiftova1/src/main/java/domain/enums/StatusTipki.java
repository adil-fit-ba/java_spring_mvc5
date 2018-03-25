package domain.enums;

/***********************************************************************
 * Module:  StatusTipki.java
 * Author:  Admin
 * Purpose: Defines the Class StatusTipki
 ***********************************************************************/

/**
 * 
 */

/** @pdOid a96d7e16-03d7-4576-b6e3-f2a9e012519b */
public enum StatusTipki
{
	TIPKA_STISNUTA(0xff),
	/** 0000 0000 */
	TIPKA_NEAKTIVNA(0x00);

	/**
	 * @param value
	 * @pdOid 75d9d4ed-e9e0-46a1-9b69-f373a827a586
	 */
	StatusTipki(int value)
	{
		this.value = value;
	}

	/**
	 * 0110 0000
	 * 
	 * @pdOid fe44a18f-96a1-4ba9-9db9-ab9bd12aa8ed
	 */
	public final int value;

}