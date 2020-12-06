public class Company implements ProductTrait{
    private Product product;
    private CompanyData companyData;

    public Company(Product product, CompanyData companyData){
        this.product = product;
        this.companyData = companyData;
    }

    public double estimate() {
        return companyData.getEmissions(product.getSellerName()) * product.getPrice();
    }
}
