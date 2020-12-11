// This trait represents the company that produced the product.
// when constructed with a product, this class estimates the carbon
// emissions from the company based on the carbon intensity metric
// stored in the CompanyData object.

public class Company implements ProductTrait {
    private Product product;
    private CompanyData companyData;
    
    // Constructs the Company object with a product and a referance to
    // a company data object. Both of these shouldn't be null.
    public Company(Product product, CompanyData companyData){
        this.product = product;
        this.companyData = companyData;
    }
    
    // Returns the carbon footprint estimate in kg of CO2.
    public double estimate() {
        return companyData.getCarbonIntensityMetric(product.getSellerName()) * product.getPrice();
    }
}
