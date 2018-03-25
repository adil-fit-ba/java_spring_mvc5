package com.company.helper.zadaci;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by adil on 3/5/2018.
 */
public class UpravljateljZadataka
{

    private String naziv;
    private int idSeed = 0;

    boolean jelAktivan;

    public UpravljateljZadataka(String naziv)
    {
        this.naziv = naziv;
        jelAktivan = false;
    }

    private Set<RadniZadatak> radniZadaci = new HashSet<>();

    public void dodaj(RadniZadatak zadatak)
    {
        radniZadaci.add(zadatak);

        if (jelAktivan)
            odaberiIIzvrsiZadatak();
    }

    public void start()
    {
        jelAktivan = true;
        odaberiIIzvrsiZadatak();
    }

    private void odaberiIIzvrsiZadatak() {
        RadniZadatak zMax = getRadniZadatakPrioritetMax();
        if (zMax == null)
            return;

        radniZadaci.remove(zMax);
        idSeed++;
        print(zMax, "Izvrsavanje u toku");

        zMax.oznaciIzvrsavanjeUToku();
        try {
            zMax.run();
        } catch (Exception e) {
            zMax.oznaciZavrsenGreska();
           print(zMax, "zavrseno sa greskom");
            odaberiIIzvrsiZadatak();
           return;
        }
        zMax.oznaciZavrsenUspjesno();

        odaberiIIzvrsiZadatak();
    }

    private RadniZadatak getRadniZadatakPrioritetMax()
    {
        int pMax = Integer.MIN_VALUE;
        RadniZadatak zMax = null;

        for (RadniZadatak zadatak : radniZadaci) {
            if (zadatak.getPrioritet() > pMax && zadatak.jelNaCekanju())
            {
                pMax = zadatak.getPrioritet();
                zMax = zadatak;
            }
        }

        return zMax;

    }

    private void print(RadniZadatak zadatak, String poruka)
    {
       System.out.println("<<< " + naziv + " <<< " + zadatak.getNaziv() + " (id: " + idSeed + ") <<< " + poruka);
    }
}
