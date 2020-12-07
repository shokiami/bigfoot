import java.util.*;

public class Shipping implements ProductTrait{
    private Product product;
    private Map<String, Double> distances;
    private ShippingData shippingData;

    public Shipping(Product product, Map<String, Double> distances, ShippingData shippingData) {
        this.product = product;
        this.distances = distances;
        this.shippingData = shippingData;
    }

    // Returns kilograms of CO2
    public double estimate() {
        double result = 0;
        for (String method : distances.keySet()) {
            result += shippingData.getRatio(method) * product.getWeight() * distances.get(method);
        }
        return result;
    }
}

// https://cefic.org/app/uploads/2018/12/MeasuringAndManagingCO2EmissionOfEuropeanTransport-McKinnon-24.01.2011-REPORT_TRANSPORT_AND_LOGISTICS.pdf