// This is the main class that represents all of the information about a product.
// There are some required attributes, which are the name, the weight in kg,
// the seller name, and the price in USD. Other traits can be added with the
// setTrait method.

import java.util.*;
import java.io.*;
import java.text.*;

public class Product {
    private String name;
    private double weight;
    private String sellerName;
    private double price;
    private List<ProductTrait> traits;
    
    // Constructs the product with the four essential attributes, name, weight, sellerName
    // and price. Both name and sellerName cannot be null, and weight and price should be
    // positive numbers.
    public Product(String name, double weight, String sellerName, double price) {
        this.name = name;
        this.weight = weight;
        this.sellerName = sellerName;
        this.price = price;
        traits = new ArrayList<ProductTrait>();
    }
    
    // Returns the trait that is of the class passed in.
    public <Trait extends ProductTrait> Trait getTrait(Class<Trait> traitClass) {
        for (ProductTrait trait : traits) {
            if (traitClass.isInstance(trait)) {
                return traitClass.cast(trait);
            }
        }
        return null;
    }
    
    // Adds a trait of type Trait. Type Trait must inherit from ProductTrait.
    // if a trait of the same type already exists in this product,
    // then the new trait overwrites the old one.
    public <Trait extends ProductTrait> void setTrait(Trait newtrait) {
        for (int i = 0; i < traits.size(); i ++) {
            if (traits.get(i).getClass() == newtrait.getClass()) {
                traits.set(i, (ProductTrait) newtrait);
                return;
            }
        }
        traits.add((ProductTrait) newtrait);
    }
    
    // Returns the name of this product.
    public String getName(){
        return name;
    }
    
    // Returns the weight in kg of this product.
    public double getWeight() {
        return weight;
    }
    
    // Returns the sellers name of this product.
    public String getSellerName() {
        return sellerName;
    }
    
    // Returns the price of this product.
    public double getPrice() {
        return price;
    }
    
    // Calculates the total carbon footprint of the product in kilograms of CO2.
    public double estimate() {
        double total = 0;
        for (ProductTrait trait : traits) {
            total += trait.estimate();
        }
        return total;
    }
    
    // This method print a breakdown of the calculated carbon footprint,
    // with each traits sub total, and the final total. This is printed
    // to the PrintStream parameter, which cannot be null.
    public void breakdown(PrintStream out) {
        DecimalFormat df = new DecimalFormat("####0.00");
        double total = 0;
        for (ProductTrait trait : traits) {
            double val = trait.estimate();
            out.println(trait.getClass().getName() + ": " + df.format(val) + "kg of CO2");
            total += val;
        }
        out.println("Estimated Carbon Footprint: " + df.format(total) + "kg of CO2");
    }
}
