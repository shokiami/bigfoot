public class Product {
    private String name;
    private double weight; //kilograms
    private ProductTrait materials;
    private ProductTrait company;
    private ProductTrait shipping;

    public void setMaterials(Materials materials) {
        this.materials = materials;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }
}
