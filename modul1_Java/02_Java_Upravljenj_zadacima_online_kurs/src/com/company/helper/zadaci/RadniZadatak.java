package com.company.helper.zadaci;

import java.net.MalformedURLException;
import java.util.Date;

import static com.company.helper.zadaci.RadniZadatak.Status.*;

/**
 * Created by adil on 3/5/2018.
 */


public abstract class RadniZadatak {

    private String naziv;
    private int prioritet;
    private Status status;

    public enum Status
    {
        CEKANJE, IZVRSAVANJE_U_TOKU, ZAVRSENO_USPJESNO, ZAVRSENO_GRESKA
    }


    public RadniZadatak(String naziv, Integer prioritet) {
        this.naziv = naziv!=null?naziv:"bez naziva";
        this.prioritet = prioritet!=null?prioritet:5;
        status = Status.CEKANJE;
    }
    public RadniZadatak(int prioritet) {
       this(null, prioritet);
    }
    public RadniZadatak(String naziv) {
      this(naziv, null);
    }
    public RadniZadatak() {
        this(null, null);
    }


    public abstract void run() throws Exception;

    public void oznaciIzvrsavanjeUToku() {
        status = IZVRSAVANJE_U_TOKU;
    }

    public boolean jelNaCekanju() {
        return status == CEKANJE;
    }

    public void oznaciZavrsenUspjesno() {
        status = ZAVRSENO_USPJESNO;

    }

    public void oznaciZavrsenGreska() {
        status = ZAVRSENO_GRESKA;


    }

    public String getNaziv() {
        return naziv;
    }

    public int getPrioritet() {
        return prioritet;
    }

    public Status getStatus() {
        return status;
    }
}
