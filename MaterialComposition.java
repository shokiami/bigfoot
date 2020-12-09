import java.util.*;

public class MaterialComposition implements ProductTrait {
  private Map<Material,Double> materials;
  private MaterialData data;
  private Product product;
  
  public MaterialComposition(Product product, MaterialData data, Map<Material,Double> materials) {
    this.product = product;
    this.data = data;
    this.materials = materials;
    averageMaterials();
  }
  
  private void averageMaterials() {
    double total = 0;
    for (double value : materials.values()) {
      total += value;
    }
    
    for (Material mat : materials.keySet()) {
      materials.put(mat, materials.get(mat)/total);
    }
  }
  
  public double estimate() {
    double total = 0;
    for (Material mat : materials.keySet()) {
      total += mat.estimate(product.getWeight() * materials.get(mat));
    }
    return total;
  }
}
