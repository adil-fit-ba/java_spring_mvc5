package domain.enums;

public enum SmjerLifta
{
	DOLE(0x0f), 
	GORE(0xf0);

	SmjerLifta(int value)
	{
		this.value = value;
	}

	public final int value;
}