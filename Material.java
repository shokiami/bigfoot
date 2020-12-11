// This class is a class that represents a type of material, (ie Steel or Plastic).
// It contains the relevant data about the material type, including the name,
// the category the material is considered in (ie Metal, Fabric) and the carbon
// footprint per kg.

import java.util.*;

class Material {
    private String category;
    private String name;
    private double co2PerKg;
    
  	// Fills in the material data from a file
  	// File format: category [tab] name [tab] kg-co2/kg-material
    public Material(Scanner ifile) {
        category = ifile.next();
        name = ifile.next();
        if (name.charAt(0) == '"') {
            name = name.substring(1, name.length()-1);
        }
    		co2PerKg = ifile.nextDouble();
    }
    
    // Constructs a material object with all of the attributes needed.
    // Category and name cannot be null, co2PerKg should not be negative.
    public Material(String category, String name, double co2PerKg) {
        this.category = category;
        this.name = name;
        this.co2PerKg = co2PerKg;
    }
    
  	// Returns the carbon footprint in kg co2 given a weight in kg.
    // The weight should not be negative
    public double estimate(double kg) {
        return co2PerKg * kg;
    }
    
    // returns the name
    public String getName() {
        return name;
    }
    
    // returns the category
    public String getCategory() {
        return category;
    }
}
