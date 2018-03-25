package com.company;

import com.company.helper.MyDownloader;
import com.company.helper.zadaci.RadniZadatak;
import com.company.helper.zadaci.UpravljateljZadataka;

public class Main {


    public static void main(String[] args) {
	// write your code here

        UpravljateljZadataka uprav = new UpravljateljZadataka("Server1");

        uprav.dodaj(new RadniZadatak("download", 4) {
            @Override
            public void run() throws Exception {
                MyDownloader.downloadFile("https://github.com/adil-fit-ba/rs1-2017-18/blob/master/03.1_ado.net/ADO.NET.doc?raw=true", "c:\\tmp");

            }
        });
        uprav.dodaj(new RadniZadatak("hello", 10) {
            @Override
            public void run() throws Exception {
                Thread.sleep(3000);
                System.out.println("hello");
            }
        });

        uprav.dodaj(new RadniZadatak("z", 1) {
            @Override
            public void run() throws Exception {
                Thread.sleep(3000);
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < i; j++) {
                        System.out.print("*");
                    }
                    System.out.println("");
                }
            }
        });


        uprav.start();

        uprav.dodaj(new RadniZadatak() {
            @Override
            public void run() throws Exception {
                Thread.sleep(2000);
                    for (int j = 0; j < 50; j++) {
                        System.out.println("*");
                    }
            }
        });
    }
}
