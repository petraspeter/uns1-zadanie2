package sk.upjs.ics.uns1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Raven
 */
public class HorolezcovAlgoritmus {
    
    private void vygenerujOkolie(double vstup) {
        
    }
    
    
    public double[] urcPocetPotrebnychIteracii(double globalneMaximum, double chyba, int k) throws FileNotFoundException {
        /*
        inicializujeme startovny bod, jeho maximum
        */
        SpolocneMetody metody = new SpolocneMetody();
        double maximum = -15;
        int pocetIteracii = 0;
        int[] binarneA = metody.generujBinarneCislo(k);
        int[] binarneB = metody.generujBinarneCislo(k);
        int maximalnaHodnota =  (int) Math.pow(2, k);
        int a=0;
        int b=0;
        
        for (int i = binarneA.length -1; i >= 0; i--) {
            a += binarneA[i]*Math.pow(2, binarneA.length-i-1);
        }
        for (int i = binarneB.length -1; i >= 0; i--) {
            b += binarneB[i]*Math.pow(2, binarneB.length-i-1);
        }
        double realneA = 2/(Math.pow(2, k)-1)*a;
        double realneB = 2/(Math.pow(2, k)-1)*b;
        maximum = Math.max(maximum, metody.funkcia(realneA, realneB));
        //  PrintWriter pw = new PrintWriter(new File("horolezec.txt"));
        PrintWriter pwMax = new PrintWriter(new File("horolezecMax.txt"));
        pwMax.write(Double.toString(maximum) + "\n");
        /*
        iterujeme pokial nenadobudneme maximum so zadanou odchylkou
        */
        while (!(Math.abs(globalneMaximum - maximum) <= chyba)) {
            double stareMaximum = maximum;
            /*
            vytvorime kandidatov pre priblizenie sa k maximum
            */
            int[] kandidatiA = {a, a, a};
            int[] kandidatiB = {b, b, b};
            if(Math.pow(2, k)-1 > a)  kandidatiA[2] = a +1;
            if(a > 0)  kandidatiA[0] = a -1;
            if(Math.pow(2, k)-1 > b)  kandidatiB[2] = b +1;
            if(b > 0)  kandidatiB[0] = b -1;
            int noveB = b;
            int noveA =a;
            double pomocneMaximum = maximum;
            /*
            vypocitame okolie daneho bodu
            */
            for (int i = 0; i < 3; i++) {
                double noveMaximum = 0;
                realneA = 2/(Math.pow(2, k)-1)*kandidatiA[i];
                for (int j = 0; j < 3; j++) {
                    if(!(i==1) && !(j==1)) {
                        realneB = 2/(Math.pow(2, k)-1)*kandidatiB[j];
                        noveMaximum = Math.max(pomocneMaximum, metody.funkcia(realneA, realneB));
                        /*
                        vyberame najlepsieho kandidata
                        */
                        if (noveMaximum > pomocneMaximum) {
                            noveB = kandidatiB[j];
                            noveA = kandidatiA[i];
                            pomocneMaximum = noveMaximum;
                        }
                    }
                }
            }
            a = noveA;
            b = noveB;
            /*
            ak uviazneme na nejakom bode
            */
            if(stareMaximum >= pomocneMaximum) {
                System.out.println("Uviazli sme pri hladani maxima");
                break;
            }
            maximum = pomocneMaximum;
            pocetIteracii++;
            // pw.write("Iteracia: "+ pocetIteracii+ "\tA: " + a + "\tB: " + b + "\tmaximuma pre a,b: " + maximum + "\n");
            if (pocetIteracii%20 == 0) {
                pwMax.write(Double.toString(maximum) + "\n");
            }
        }
        
        pwMax.write(Double.toString(maximum) + "\n");
        System.out.println("A: " + a + "\tB: "+b + "\t"+maximum);
        realneA = 2/(Math.pow(2, k)-1)*a;
        realneB = 2/(Math.pow(2, k)-1)*b;
        maximum = Math.max(maximum, metody.funkcia(realneA, realneB));
        double[] vrat = {maximum, pocetIteracii};
        // pw.close();
        pwMax.close();
        return vrat;
    }
}
