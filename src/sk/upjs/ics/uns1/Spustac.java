package sk.upjs.ics.uns1;

import java.io.FileNotFoundException;

/**
 *
 * @author Raven
 */
public class Spustac {
    public static void main(String[] args) throws FileNotFoundException {
        
        
        double odchylka = 0.001;
        double globalneMaximum = 7.389056;
        int k = 11;
        SlepyAlgoritmus slepyAlgoritmus = new SlepyAlgoritmus();
        HorolezcovAlgoritmus horolezcovAlgoritmus = new HorolezcovAlgoritmus();
        
        double[] maximum =  horolezcovAlgoritmus.urcPocetPotrebnychIteracii(globalneMaximum, odchylka, k);
        // double[] maximum = slepyAlgoritmus.vypocitajSlepyAlgoritmus(odchylka, globalneMaximum, k);
        System.out.println("Priblizne maximum zvolenej funkcie je: "+ maximum[0] + ". Na jeho ziskanie sme potrebovali: " + maximum[1] + " Iteracii.");
        
        
    }
}
