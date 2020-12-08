import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Product product = new Product("Phone case", 0.2, "Logitech", 5.99);
		MaterialData materialdata = new MaterialData(new Scanner(new File("materials.tsv")));
		CompanyData companydata = new CompanyData();
		
		Map<Material,Double> materials = new HashMap<Material,Double>();
		materials.put(materialdata.getByName("Plastic"), 0.75);
		materials.put(materialdata.getByName("Steel"), 0.25);
		System.out.println(materials);
		
		product.setTrait(new MaterialComposition(product, materialdata, materials));
		product.setTrait(new Company(product, companydata));
		
		double val = product.estimate();
		System.out.println(val);
	}
}
