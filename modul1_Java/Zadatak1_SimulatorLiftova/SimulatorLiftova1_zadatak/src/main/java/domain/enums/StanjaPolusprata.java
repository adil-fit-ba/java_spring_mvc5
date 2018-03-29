package domain.enums;

public enum StanjaPolusprata
{
	GORE(1), 
	DOLE(-1), 
	NEMA(0);

	public final int value;

	StanjaPolusprata(int value)
	{
		this.value = value;
	}
}
