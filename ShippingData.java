import java.io.*;
import java.util.*;

public class ShippingData{
    private Map<String, Integer> ratios; // kgCO2/kg-km

    public ShippingData() throws FileNotFoundException {
        ratios = new HashMap<>();
        Scanner input = new Scanner(new File("shipping.tsv"));
        while (input.hasNext()) {
            ratios.put(input.next(), input.nextInt());
        }
    }

    public double getRatio(String method) {
        return ratios.get(method);
    }
}
