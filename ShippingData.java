// This class holds static data that is needed for the Shipping product trait.
// This class should be constructed only once in a program, and passed to any
// created Shipping objects.

import java.io.*;
import java.util.*;

public class ShippingData{
    private Map<String, Double> ratios; // kgCO2/kg-km
    private List<Integer> distances;
    private List<Map<String, Double>> compositions;
    
    // Constructs the ShippingData object using data from files
    // shippingModes.tsv and shippingDistances.tsv.
    public ShippingData() throws FileNotFoundException {
        ratios = new HashMap<>();
        Scanner input1 = new Scanner(new File("shippingModes.tsv"));
        while (input1.hasNext()) {
            ratios.put(input1.next(), input1.nextDouble());
        }
        distances = new ArrayList<>();
        compositions = new ArrayList<>();
        Scanner input2 = new Scanner(new File("shippingDistances.tsv"));
        while (input2.hasNext()) {
            distances.add(input2.nextInt());
            Map<String, Double> composition = new HashMap<>();
            for (String mode : ratios.keySet()) {
                composition.put(mode, input2.nextDouble());
            }
            compositions.add(composition);
        }
    }
    
    // Returns the ratios for every distance given a mode of transport
    // "road", "rail", "sea" ... the parameter method cannot be null.
    public double getRatio(String method) {
        return ratios.get(method);
    }
    
    // Returns the different modes of transport, and the percentages of a trip,
    // given the distance traveled. The distance should not be negative.
    public Map<String, Double> getComposition(double distance) {
        int index = distances.size() - 1;
        while (distance < distances.get(index)) {
            index--;
        }
        return compositions.get(index);
    }
}
