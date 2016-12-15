package sk.upjs.ics.uns1;

/**
 *
 * @author Raven
 */
public class SpolocneMetody {
    
    public double funkcia(double x, double y) {
        return Math.pow(Math.E, x) - 3*y;
    }
    
    public int[] klonuj (int[] vstup) {
        int[] vystup = new int[vstup.length];
        for (int i = 0; i < vstup.length; i++) {
            vystup[i] = vstup[i];
        }
        return vystup;
    }
    
    public int[] generujBinarneCislo(int dlzka){
        /*
        vygenerujeme pole na uchovanie binarneho cisla
        */
        int[] binarneCislo = new int[dlzka];
        for (int i = 0; i < binarneCislo.length; i++) {
            double n = Math.random();
            if(n > 0.5)
                binarneCislo[i]=1;
            else
                binarneCislo[i]=0;
        }
        return binarneCislo;
    }
        
}
