package domain.enums;

/**
 * 
 */

/** @pdOid 6d339b1a-91ce-4cf4-8247-3586bfe79a88 */
public enum NaredbeLampice
{
	/**
	 * dodaj ne elemenat NISTA, izmjenjene su vrijednosti u odnosu na zadatak
	 */
	UPALI(0xf0), 
	UGASI(0x0f), 
	NISTA(0x00);

	/**
	 * @param value
	 * @pdOid 54a298e7-b2e6-4d5e-aa94-e5fa80aa5842
	 */
	NaredbeLampice(int value)
	{
		this.value = value;
	}

	/**
	 * 0110 0000
	 * 
	 * @pdOid 82ed74d0-af9d-461b-a1e9-77c3b44cde7a
	 */
	public final int value;

}