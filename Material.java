// This class is a class that represents a type of material, (ie Steel or Plastic).
// It contains the relevant data about the material type, including the name,
// the category the material is considered in (ie Metal, Fabric) and the carbon
// footprint per kg.

import java.util.*;

class Material {
  private String category;
  private String name;
  private double co2PerKg;
  
	// fills in the material data from a file
	// Format: unique-id category name kg-co2/kg-material
  public Material(Scanner ifile) {
    category = ifile.next();
    name = ifile.next();
    if (name.charAt(0) == '"') {
      name = name.substring(1, name.length()-1);
    }
		co2PerKg = ifile.nextDouble();
  }
  
  // constructs a material object with all of the attributes needed.
  // category and name cannot be null.
  public Material(String category, String name, double co2PerKg) {
    this.category = category;
    this.name = name;
    this.co2PerKg = co2PerKg;
  }
  
	// returns the carbon footprint in kg co2
  public double estimate(double kg) {
    return co2PerKg * kg;
  }
  
  public String getName() {
    return name;
  }
  
  public String getCategory() {
    return category;
  }
}
