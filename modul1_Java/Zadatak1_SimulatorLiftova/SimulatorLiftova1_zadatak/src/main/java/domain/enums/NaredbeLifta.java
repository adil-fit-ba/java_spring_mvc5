package domain.enums;

/** @pdOid 72a8157f-ba12-47ec-b2a8-7749036443cc */
public enum NaredbeLifta
{
	NISTA(0x00),
	/** 0000 0000 */
	ZATVORI(0x60),
	/** 0110 0000 */
	OTVORI(0xc0),
	/** 1100 0000 */
	GORE_UBRZAJ(0x03),
	/** 0000 0011 */
	GORE_IDI(0x06),
	/** 0000 0110 */
	GORE_USPORI(0x05),
	/** 0000 0101 */
	DOLE_UBRZAJ(0x1b),
	/** 0001 1011 */
	DOLE_IDI(0x1e),
	/** 0001 1110 */
	DOLE_USPORI(0x1d),
	/** 0001 1101 */
	KRAJ(0xff);

	/**
	 * @param value
	 * @pdOid 7fa52196-6566-49ae-818b-7b1ca5330317
	 */
	NaredbeLifta(int value)
	{
		this.value = value;
	}

	/**
	 * kraj
	 * 
	 * @pdOid 42bc6b09-d063-4d15-965b-48c529fb2bf2
	 */
	public final int value;

}