import java.util.*;

public class Shipping implements ProductTrait{
    private Map<String, Double> distances;
    private Map<String, Integer> ratios = new HashMap<>();

    public Shipping(Map<String, Double> distances) {
        this.distances = new HashMap<String, Double>(distances);
        this.ratios = new HashMap<String, Integer>();
        ratios.put("road", 62);
        ratios.put("rail", 22);
        ratios.put("sea", 31);
        ratios.put("air", 602);
    }

    public double estimate(Product product) {
        double result = 0;
        for (String method : distances.keySet()) {
            result += ratios.get(method) * product.getWeight() * distances.get(method) / 1000000;
        }
        return result;
    }
}

//https://cefic.org/app/uploads/2018/12/MeasuringAndManagingCO2EmissionOfEuropeanTransport-McKinnon-24.01.2011-REPORT_TRANSPORT_AND_LOGISTICS.pdf