// This interface is implemented by all of the product traits.
// A product trait contains data about a product, and also
// has an estimate method.

public interface ProductTrait {

    // Returns the carbon footprint for this specific trait of the product.
    public double estimate();
}
