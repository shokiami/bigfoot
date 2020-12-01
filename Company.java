import java.util.*;

public class Company implements ProductTrait{
    private Product product;
    private Map<String, Integer> companyEmissions;

    public Company(Product product, CompanyData companyData){
        this.product = product;
        this.companyEmissions = companyData.getEmissions();
    }

    public double estimate() {
        if (companyEmissions.containsKey(product.getSellerName())) {
            return companyEmissions.get(product.getSellerName());
        } else {
            double total = 0;
            int num = 0;
            for (String company : companyEmissions.keySet()) {
                total += companyEmissions.get(company);
                num++;
            }
            return total / num;
        }
    }
}
