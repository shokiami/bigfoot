import java.util.*;

public class MaterialComposition implements ProductTrait {
  private Map<Material,Double> materials;
  private MaterialData data;
  private Product product;
  
  public MaterialComposition(Product product, MaterialData data) {
    materials = new HashMap<Material,Double>();
    this.product = product;
    this.data = data;
  }
  
  public MaterialComposition(Product product, Map<Material,Double> materials) {
    this.product = product;
    this.materials = materials;
  }
  
  public double estimate() {
    double total = 0;
    for (Material mat : materials.keySet()) {
      total += mat.estimate(product.getWeight() * materials.get(mat));
    }
    return total;
  }
}
