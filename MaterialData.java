// This class holds static material data that is needed for every material
// object, and so it is constructed seperatley to avoid unnessisary reading.

import java.util.*;

class MaterialData {
  private List<Material> allMats;
  
  // constructs the instance with a prefilled list of materials.
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
      if (mat.getName().equals(name) || mat.getCategory().equals(name)) {
        return mat;
      }
    }
    return null;
  }
}
