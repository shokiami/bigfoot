import java.io.*;
import java.util.*;

public class ShippingData{
    private Map<String, Double> ratios; // kgCO2/kg-km
    private List<Integer> distances;
    private List<Map<String, Double>> compositions;

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

    public double getRatio(String method) {
        return ratios.get(method);
    }

    public Map<String, Double> getComposition(double distance) {
        int index = distances.size() - 1;
        while (distance < distances.get(index)) {
            index--;
        }
        return compositions.get(index);
    }
}
