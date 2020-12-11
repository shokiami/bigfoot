import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length > 0) {
			String outpath;
			if (args.length < 2) {
				outpath = args[0].replace(".csv","-results.csv");
			} else {
				outpath = args[1];
			}
			fromFile(new Scanner(new File(args[0])), new PrintStream(new File(outpath)));
		} else {
			fromUserInput();
		}
	}
	
	public static void fromFile(Scanner inputFile, PrintStream output) throws FileNotFoundException {
		inputFile.nextLine();
		inputFile.nextLine();
		output.println("name,footprint");
		MaterialData materialdata = new MaterialData();
		CompanyData companydata = new CompanyData();
		ShippingData shippingdata = new ShippingData();
		
		while (inputFile.hasNext()) {
			Scanner input = new Scanner(inputFile.nextLine()).useDelimiter(",|\r\n|\n");
			String name = input.next();
			double weight = input.nextDouble();
			String seller = input.next();
			double price = input.nextDouble();
			System.out.println("Product: " + name + " by " + seller);
			Product product = new Product(name, weight, seller, price);
			double distance = input.nextDouble();
			
			Map<Material,Double> materials = new HashMap<Material,Double>();
			while (input.hasNext()) {
				String matname = input.next();
				if (matname.length() != 0) {
					materials.put(materialdata.getByName(matname), input.nextDouble());
				}
			}
			
			product.setTrait(new MaterialComposition(product, materialdata, materials));
			product.setTrait(new Company(product, companydata));
			product.setTrait(new Shipping(product, distance, shippingdata));
			
			product.breakdown(System.out);
			System.out.println();
			
			output.println(name + "," + product.estimate());
		}
	}
	
	public static double safeNextDouble(Scanner scanner) {
		while (!scanner.hasNextDouble()) {
			scanner.next();
			System.out.print("You didn't enter a number. Please enter a number: ");
		}
		return scanner.nextDouble();
	}
	
	public static void fromUserInput() throws FileNotFoundException {
		MaterialData materialdata = new MaterialData();
		CompanyData companydata = new CompanyData();
		ShippingData shippingdata = new ShippingData();
		boolean reading = true;
		Scanner input = new Scanner(System.in);

		System.out.println();

		while (reading) {
			System.out.print("Product name: ");
			String name = input.nextLine();
			
			System.out.print("Product weight (kg): ");
			double weight;
			while ((weight = safeNextDouble(input)) < 0) {
				System.out.print("You entered a negative number. Please enter a positive number: ");
			}
			input.nextLine();
			
			System.out.print("Seller name: ");
			String seller = input.nextLine();
			
			System.out.print("Product price ($): ");
			double price;
			while ((price = safeNextDouble(input)) < 0) {
				System.out.print("You entered a negative number. Please enter a positive number: ");
			}
			input.nextLine();
			
			Product product = new Product(name, weight, seller, price);
			
			double total = 0;
			Map<Material,Double> materials = new HashMap<Material,Double>();
			System.out.println("Type help to get a list of the materials.");
			while (total < 1) {
				System.out.print("Enter a material in your product: ");
				String matname = input.nextLine();
				Material mat = materialdata.getByName(matname);
				
				if (mat == null && !matname.equalsIgnoreCase("help")) {
					System.out.println("No material by that name was found.");
				}
				
				if (mat == null) {
					int i = 0;
					System.out.print("Possible materials:\n  ");
					for (Material othermat : materialdata.getMaterialList()) {
						if (i % 6 == 5) {
							System.out.print(", \n  ");
						} else if (i != 0) {
							System.out.print(", ");
						}
						if (othermat.getName().equals("Average")) {
							System.out.print(othermat.getCategory());
						} else {
							System.out.print(othermat.getName());
						}
						i ++;
					}
					System.out.println();
				} else {
					System.out.print("Fraction of the product this material makes up (0 - 1): ");
					double fraction;
					while ((fraction = safeNextDouble(input)) > 1 || fraction < 0) {
						System.out.print("That was not between 0 and 1, please enter a number between 0 and 1: ");
					}
					input.nextLine();
					total += fraction;
					if (materials.containsKey(mat)) {
						materials.put(mat, materials.get(mat) + fraction);
					} else {
						materials.put(mat, fraction);
					}
				}
			}
			product.setTrait(new MaterialComposition(product, materialdata, materials));
			
			product.setTrait(new Company(product, companydata));
			
			System.out.print("Approximate shipping distance (km): ");
			double distance;
			while ((distance = safeNextDouble(input)) < 0) {
				System.out.print("You entered a negative number. Please enter a positive number: ");
			}
			input.nextLine();
			product.setTrait(new Shipping(product, distance, shippingdata));
			
			System.out.println();

			product.breakdown(System.out);
			
			System.out.println();

			System.out.print("Do you want to do another product? (yes/no): ");
			reading = input.nextLine().equals("yes");
			
			System.out.println();
		}
	}
}
