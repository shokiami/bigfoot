import java.util.*;

public class Shipping implements ProductTrait{
    private Product product;
    private double distance;
    private ShippingData shippingData;

    public Shipping(Product product, double distance, ShippingData shippingData) {
        this.product = product;
        this.distance = distance;
        this.shippingData = shippingData;
    }

    // Returns kilograms of CO2
    public double estimate() {
        Map<String, Double> composition = shippingData.getComposition(distance);
        double result = 0;
        for (String mode : composition.keySet()) {
            result += shippingData.getRatio(mode) * product.getWeight() * composition.get(mode) * distance;
        }
        return result;
    }
}

// https://cefic.org/app/uploads/2018/12/MeasuringAndManagingCO2EmissionOfEuropeanTransport-McKinnon-24.01.2011-REPORT_TRANSPORT_AND_LOGISTICS.pdf
// https://ops.fhwa.dot.gov/freight/freight_analysis/nat_freight_stats/docs/13factsfigures/pdfs/fff2013_highres.pdf