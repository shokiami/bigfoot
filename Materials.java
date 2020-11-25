import java.util.*;

class Mat {
  private String name;
  private Set<String> otherNames;
  private double co2PerKg;
  
  public Mat(Scanner ifile) {
    
  }
  
  public Mat(String name, Set<String> otherNames, double co2PerKg) {
    this.name = name;
    this.otherNames = otherNames;
    this.co2PerKg = co2PerKg;
  }
  
  public double estimate(double kg) {
    
  }
  
  public String getName() {
    return name;
  }
  
  public Set<String> getOtherNames() {
    return otherNames;
  }
}

class MatStorage {
  private List<Mat> allMats;
  
  public MatStorage(List<Mat> allMats) {
    this.allMats = allMats;
  }
  
  public MatStorage(Scanner ifile) {
    
  }
  
  public Mat getByName(String name) {
    for (Mat mat : allMats) {
      if (mat.getName() == name || mat.getOtherNames().contains(name)) {
        return mat;
      }
    }
    return null;
  }
}

public class Materials implements ProductTrait {
  private Map<Mat,double> materials;
  
  public Materials() {
    materials = new HashMap<Mat,double>();
  }
  
  public Materials(Mat<Mat,double> materials) {
    this.materials = materials;
  }
  
  public double estimate(Product product) {
    double total = 0;
    for (Mat mat : materials) {
      total += mat->estimate(product.getWeight());
    }
    return total;
  }
}
