import java.util.*;

class MaterialData {
  private List<Material> allMats;
  
  public MaterialData(List<Material> allMats) {
    this.allMats = allMats;
  }
  
	// fills the material list from a scanner, with lines of Material objects
  public MaterialData(Scanner ifile) {
    allMats = new ArrayList<Material>();
    ifile = ifile.useDelimiter("\t|\r\n|\n");
    ifile.nextLine();
    while (ifile.hasNext()) {
			allMats.add(new Material(ifile));
		}
  }
  
	// returns a material that matches the passed name
  public Material getByName(String name) {
    for (Material mat : allMats) {
      if (mat.getName() == name || mat.getCategory() == name) {
        return mat;
      }
    }
    return null;
  }
}
