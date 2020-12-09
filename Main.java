import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length > 0) {
			fromFile(new Scanner(new File("products.csv")));
		} else {
			fromUserInput();
		}
	}
	
	public static void fromFile(Scanner input) throws FileNotFoundException {
		input = input.useDelimiter(",|\r\n|\n");
		input.nextLine();
		input.nextLine();
		MaterialData materialdata = new MaterialData(new Scanner(new File("materials.tsv")));
		CompanyData companydata = new CompanyData();
		
		while (input.hasNext()) {
			String name = input.next();
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
	
	public static void fromUserInput() throws FileNotFoundException {
		MaterialData materialdata = new MaterialData(new Scanner(new File("materials.tsv")));
		CompanyData companydata = new CompanyData();
		ShippingData shippingdata = new ShippingData();
		boolean reading = true;
		Scanner input = new Scanner(System.in);
		while (reading) {
			System.out.print("Product name: ");
			String name = input.nextLine();
			
			System.out.print("Product weight: ");
			double weight = input.nextDouble();
			input.nextLine();
			
			System.out.print("Product seller: ");
			String seller = input.nextLine();
			
			System.out.print("Product price: ");
			double price = input.nextDouble();
			input.nextLine();
			
			Product product = new Product(name, weight, seller, price);
			
			double total = 0;
			Map<Material,Double> materials = new HashMap<Material,Double>();
			while (total < 1) {
				System.out.print("Enter a material in your product: ");
				Material mat = materialdata.getByName(input.nextLine());
				if (mat == null) {
					System.out.println("No material by that name was found");
				} else {
					System.out.print("What fraction of the product does this material make up?: ");
					double fraction = input.nextDouble();
					input.nextLine();
					total += fraction;
					materials.put(mat, fraction);
				}
			}
			
			product.setTrait(new MaterialComposition(product, materialdata, materials));
			product.setTrait(new Company(product, companydata));
			
			Map<String,Double> distances = new HashMap<String,Double>();
			String[] types = {"air", "road", "rail", "sea"};
			for (String type : types) {
				System.out.print("How long did the product travel by " + type + " (km): ");
				distances.put(type, input.nextDouble());
			}
			
			product.setTrait(new Shipping(product, distances, shippingdata));
			
			System.out.println("Carbon footprint (kg CO2): " + product.estimate());
			System.out.println("Breakdown:");
			product.breakdown(System.out);
			System.out.print("Do you want to do another product? (yes/no): ");
			reading = input.next().equals("yes");
		}
	}
}
