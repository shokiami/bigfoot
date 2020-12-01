import java.util.*;

class MaterialData {
  private List<Material> allMats;
  
  public MaterialData(List<Material> allMats) {
    this.allMats = allMats;
  }
  
  public MaterialData(Scanner ifile) {
    while (ifile.hasNext()) {
			allMats.add(new Material(ifile));
		}
  }
  
  public Material getByName(String name) {
    for (Material mat : allMats) {
      if (mat.getName() == name || mat.getOtherNames().contains(name)) {
        return mat;
      }
    }
    return null;
  }
}
