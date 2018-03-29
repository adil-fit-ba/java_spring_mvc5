package domain;

import domain.enums.StatusTipki;

public abstract class Tipka
{
	/** @pdRoleInfo migr=no name=StatusTipki assc=relationship16 mult=1..1 side=A */
	public StatusTipki statusTipki;

	public Tipka()
	{
		statusTipki = StatusTipki.TIPKA_NEAKTIVNA;
	}

}