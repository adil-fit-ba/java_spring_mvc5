package domain.enums;

/** @pdOid 1173fa90-ef41-4848-ad53-e155345ead44 */
public enum StanjaLifta
{
	STOJI_OTVOREN(0x00),
	/** 0000 0000 */
	STOJI_ZATVOREN(0xc3),
	/** 1100 0011 */
	ZATVARA(0x60),
	/** 0110 0000 */
	OTVARA(0xc0),
	/** 1100 0000 */
	GORE_UBRZAVA(0x03),
	/** 0000 0011 */
	GORE_IDE(0x06),
	/** 0000 0110 */
	GORE_JURI(0x07),
	/** 0000 0111 */
	GORE_USPORAVA(0x05),
	/** 0000 0101 */
	DOLE_UBRZAVA(0x1b),
	/** 0001 1011 */
	DOLE_IDE(0x1e),
	/** 0001 1110 */
	DOLE_JURI(0x1f),
	/** 0001 1111 */
	DOLE_USPORAVA(0x1d),
	/** 0001 1101 */
	GOTOVO(0xff);

	/**
	 * @param value
	 * @pdOid 0433ef6f-61b2-404b-adad-1f0972927026
	 */
	StanjaLifta(int value)
	{
		this.value = value;
	}

	/**
	 * kraj
	 * 
	 * @pdOid b6541b2d-2a60-41f6-b7a0-03b9725df0d5
	 */
	public final int value;

}