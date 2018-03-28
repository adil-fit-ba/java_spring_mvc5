package zadatak2;

import java.util.Date;

public class Kupac
{
	private String email;
	private String ime;

	private Date date;

	public String getEmail()
	{
		return this.email;
	}

	public String getIme()
	{
		return this.ime;
	}

	public void setEmail(String m)
	{
		this.email = m.trim().toLowerCase();
	}

	public void setIme(String i)
	{
		this.ime = i;
	}
}
