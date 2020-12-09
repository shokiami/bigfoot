// This class calculates the carbon footprint of a products material composition.
// The class is constructed with a map of materials and percentages, and with
// this composition this class calculates the carbon footprint from the raw materials
// only (not including manufacturing or transport)

import java.util.*;

public class MaterialComposition implements ProductTrait {
    private Map<Material,Double> materials;
    private MaterialData data;
    private Product product;
    
    // Params:
    //    Product product = the product this trait is referring to. cannot be null
    //    MaterialData data - a MaterialData instance. this is used to store static data
    //                        so it doesn't have to get loaded in every time. cannot be null
    //    Map<Material,Double> materials - a map of material objects, with a corresponding
    //                                     "weight" value. the values are normalized so the
    //                                     total is zero. cannot be null.
    public MaterialComposition(Product product, MaterialData data, Map<Material,Double> materials) {
        this.product = product;
        this.data = data;
        this.materials = materials;
        averageMaterials();
    }
  
    // a private method that normalizes the material map. this should
    // be run every time the map is changed.
    private void averageMaterials() {
        double total = 0;
        for (double value : materials.values()) {
            total += value;
        }
        
        for (Material mat : materials.keySet()) {
            materials.put(mat, materials.get(mat)/total);
        }
    }
    
    // This method returns the carbon estimate, in kg of CO2, based on the
    // contained product and material composition.
    public double estimate() {
        double total = 0;
        for (Material mat : materials.keySet()) {
            total += mat.estimate(product.getWeight() * materials.get(mat));
        }
        return total;
    }
}
