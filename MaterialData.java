// This class holds static material data that is needed for every material
// object, and so it is constructed seperatley to avoid unnessisary reading.
// This class should be constructed only once in a program, and passed to any
// created MaterialComposition objects.

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
        //ifile.nextLine();
        while (ifile.hasNext()) {
      			allMats.add(new Material(ifile));
    		}
    }
    
  	// returns a material that either has the same name as the parameter
    // or the same category as the parameter
    public Material getByName(String name) {
        for (Material mat : allMats) {
            if (mat.getName().equalsIgnoreCase(name)
                  || ( mat.getCategory().equalsIgnoreCase(name)
                  && mat.getName().equals("Average"))) {
                return mat;
            }
        }
        return null;
    }
    
    // returns a list of all materials in the dataset
    public List<Material> getMaterialList() {
      return allMats;
    }
}
