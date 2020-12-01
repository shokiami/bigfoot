import java.util.*;

class MaterialData {
  private List<Material> allMats;
  
  public MaterialData(List<Material> allMats) {
    this.allMats = allMats;
  }
  
	// fills the material list from a scanner, with lines of Material objects
  public MaterialData(Scanner ifile) {
    while (ifile.hasNext()) {
			allMats.add(new Material(ifile));
		}
  }
  
	// returns a material that matches the passed name
  public Material getByName(String name) {
    for (Material mat : allMats) {
      if (mat.getName() == name || mat.getOtherNames().contains(name)) {
        return mat;
      }
    }
    return null;
  }
}
