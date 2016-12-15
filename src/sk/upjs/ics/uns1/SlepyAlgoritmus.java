package sk.upjs.ics.uns1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Raven
 */
public class SlepyAlgoritmus {
    
    public double[] vypocitajSlepyAlgoritmus(double odchylka, double globalneMaximum, int k) throws FileNotFoundException {
        SpolocneMetody metody = new SpolocneMetody();
        double maximum = 0;
        int pocetIteracii = 0;
        /*
        iterujeme pokial sa nedosaneme na pozadovanu odchylku
        */
        // PrintWriter pw = new PrintWriter(new File("slepy.txt"));
        PrintWriter pwMax = new PrintWriter(new File("slepyMax.txt"));
        while (!(Math.abs(globalneMaximum - maximum) <= odchylka)) {
            /*
            vygenerujeme nahodnu binarne cislo
            */
            int[] binarneA = metody.generujBinarneCislo(k);
            int[] binarneB = metody.generujBinarneCislo(k);
            int a = 0;
            int b = 0;
            /*
            vygenerovane nahodne cislo prevadzame do realneho cisla
            */
            for (int i = binarneA.length -1; i >= 0; i--) {
                a += binarneA[i]*Math.pow(2, binarneA.length-i-1);
            }
            for (int i = binarneB.length -1; i >= 0; i--) {
                b += binarneB[i]*Math.pow(2, binarneB.length-i-1);
            }
            /*
            dosadime do vzorca
            */
            double realneA = (2/(Math.pow(2, k)-1))*a;
            double realneB = (2/(Math.pow(2, k)-1))*b;
            /*
            vypocitame funkciu
            */
            double aktualnyVysledok = metody.funkcia(realneA, realneB);
            maximum = Math.max(maximum, aktualnyVysledok);
            if(pocetIteracii%5000 == 0) {
                pwMax.write(Double.toString(aktualnyVysledok) + "\n");
            }
            //  pw.write("Iteracia: "+ pocetIteracii+ "\tA: " + a + "\tB: " + b + "\tmaximuma pre a,b: " + maximum + "\n");
            pocetIteracii++;
        }
        double[] vysledok = {maximum, pocetIteracii};
        //pw.close();
        pwMax.write(Double.toString(maximum) + "\n");
        pwMax.close();
        return vysledok;
    }
    
}
