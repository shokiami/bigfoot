// This interface is implemented by all of the product traits.
// A product trait contains data about a product, and also
// has an estimate method, that returns the carbon footprint of
// the specific data of the product.

public interface ProductTrait {
    public double estimate();
}
