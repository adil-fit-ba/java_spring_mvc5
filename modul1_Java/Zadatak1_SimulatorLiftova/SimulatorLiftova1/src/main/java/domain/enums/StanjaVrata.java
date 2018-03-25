package domain.enums;

public enum StanjaVrata
{
	OTVORENA(0x00),
	ZATVORENA(0xff); 

	public final int value;

	StanjaVrata(int value)
	{
		this.value = value;
	}
}