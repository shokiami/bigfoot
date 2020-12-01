import java.util.*;

class Material {
  private String name;
  private Set<String> otherNames;
  private double co2PerKg;
  
  public Material(Scanner ifile) {
    name = ifile.next();
		otherNames = new HashSet<String>();
		while (!ifile.hasNextDouble()) {
			otherNames.add(ifile.next());
		}
		co2PerKg = ifile.nextDouble();
  }
  
  public Material(String name, Set<String> otherNames, double co2PerKg) {
    this.name = name;
    this.otherNames = otherNames;
    this.co2PerKg = co2PerKg;
  }
  
  public double estimate(double kg) {
    return co2PerKg * kg;
  }
  
  public String getName() {
    return name;
  }
  
  public Set<String> getOtherNames() {
    return otherNames;
  }
}
