// This class holds static material data that is needed for every material
// object, and so it is constructed seperatley to avoid unnessisary reading.

import java.util.*;
import java.io.*;

class MaterialData {
    private List<Material> allMats;
    
  	// Constructs the materialdata object from a file called materials.tsv,
    // where each line represents a material type
    public MaterialData() throws FileNotFoundException {
        Scanner ifile = new Scanner(new File("materials.tsv"));
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
            if (mat.getName().equalsIgnoreCase(name) || mat.getCategory().equalsIgnoreCase(name)) {
                return mat;
            }
        }
        return null;
    }
}
