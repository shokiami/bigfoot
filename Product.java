import java.util.*;

public class Product {
    private String name;
    private double weight; //kilograms
    private String sellerName;
    private double price;
    private List<ProductTrait> traits;
    
    public Product() {
        traits = new ArrayList<ProductTrait>();
    }
    
    // returns the trait that is of the type passed in
    public <Trait extends ProductTrait> Trait getTrait(Class<Trait> traitClass) {
        for (ProductTrait trait : traits) {
            if (traitClass.isInstance(trait)) {
                return traitClass.cast(trait);
            }
        }
        return null;
    }
    
    // adds a trait of type Trait, if a trait of the same type already
    // exists then the new trait overwrites the old one
    public <Trait extends ProductTrait> void setTrait(Trait newtrait) {
        for (int i = 0; i < traits.size(); i ++) {
            if (traits.get(i).getClass() == newtrait.getClass()) {
                traits.set(i, (ProductTrait) newtrait);
                return;
            }
        }
        traits.add((ProductTrait) newtrait);
        System.out.println(traits.size());
    }
    
    public String getName(){
        return name;
    }
    
    public double getWeight() {
        return weight;
    }

    public String getSellerName() {
        return sellerName;
    }

    public double getPrice() {
        return price;
    }
    
    // calculates the total carbon footprint of the product in kg co2
    public double estimate() {
        double total = 0;
        for (ProductTrait trait : traits) {
            total += trait.estimate();
        }
        return total;
    }
}
