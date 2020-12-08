import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("products.csv")).useDelimiter(",|\r\n|\n");
		input.nextLine();
		input.nextLine();
		MaterialData materialdata = new MaterialData(new Scanner(new File("materials.tsv")));
		CompanyData companydata = new CompanyData();
		
		while (input.hasNext()) {
			String name = input.next();
			if (name.equals("")) {
				break;
			}
			double weight = input.nextDouble();
			String seller = input.next();
			double price = input.nextDouble();
			System.out.println("Product: " + name + " by " + seller);
			Product product = new Product(name, weight, seller, price);
			input.next();
			input.next();
			
			Map<Material,Double> materials = new HashMap<Material,Double>();
			String matname = input.next();
			while (!matname.equals("")) {
				materials.put(materialdata.getByName(matname), input.nextDouble());
				matname = input.next();
			}
			
			product.setTrait(new MaterialComposition(product, materialdata, materials));
			product.setTrait(new Company(product, companydata));
			
			//System.out.println("Co2: " + product.estimate());
			product.breakdown(System.out);
			System.out.println();
			input.nextLine();
		}
	}
}
