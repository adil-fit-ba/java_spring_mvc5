package domain.enums;

/**
 * @param brojLiftova
 * @param brojSpratova
 */
public enum StatusLampice
{
	UPALJENA(0xff), /* 0000 0000 */
	UGASENA(0x00); /* 0110 0000 */

	public final int value;

	StatusLampice(int value)
	{
		this.value = value;
	}
}