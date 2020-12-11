// This is the main class, which provides a command line entry point to the algorithm.
// there are two ways to run the algorithm, with a data table, and by hand. If this class
// is run without any parameters, it will prompt the user for input. If a parameter is given,
// the data is read from that file, in a csv style format.

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length > 0) {
			String outPath;
			if (args.length < 2) {
				outPath = args[0].replace(".csv","") + "-results.csv";
			} else {
				outPath = args[1];
			}
			fromFile(new Scanner(new File(args[0])), new PrintStream(new File(outPath)));
		} else {
			fromUserInput();
		}
	}
	
	// this method reads in product data from a csv file.
	// The file collumns should be formatted like:
	// Name, Weight, Seller, Price, Shipping distance, Material 1,
	// Material 1 fraction ... Material N, Material N fraction
	// Both the scanners should not be null
	public static void fromFile(Scanner inputFile, PrintStream output)
			throws FileNotFoundException {
		inputFile.nextLine();
		inputFile.nextLine();
		output.println("name,footprint");
		MaterialData materialData = new MaterialData();
		CompanyData companyData = new CompanyData();
		ShippingData shippingData = new ShippingData();
		
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
				String matName = input.next();
				if (matName.length() != 0) {
					materials.put(materialData.getByName(matName), input.nextDouble());
				}
			}
			
			product.setTrait(new MaterialComposition(product, materialData, materials));
			product.setTrait(new Company(product, companyData));
			product.setTrait(new Shipping(product, distance, shippingData));
			
			product.breakdown(System.out);
			System.out.println();
			
			output.println(name + "," + product.estimate());
		}
	}
	
	// This method gets a double from a scanner, but doesn't throw an error if
	// a non double is inputted and instead prints a message and prompts the user again.
	// The scanner cannot be null.
	private static double safeNextDouble(Scanner scanner) {
		while (!scanner.hasNextDouble()) {
			scanner.next();
			System.out.print("You didn't enter a number. Please enter a number: ");
		}
		return scanner.nextDouble();
	}
	
	// This method estimates products from user input, it will prompt
	// the user for the nessissary parameters and run the estimation.
	public static void fromUserInput() throws FileNotFoundException {
		MaterialData materialData = new MaterialData();
		CompanyData companyData = new CompanyData();
		ShippingData shippingData = new ShippingData();
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
				String matName = input.nextLine();
				Material mat = materialData.getByName(matName);
				
				if (mat == null && !matName.equalsIgnoreCase("help")) {
					System.out.println("No material by that name was found.");
				}
				
				if (mat == null) {
					int i = 0;
					System.out.print("Possible materials:\n  ");
					for (Material othermat : materialData.getMaterialList()) {
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
						System.out.print(
							"That was not between 0 and 1, please enter a number between 0 and 1: ");
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
			product.setTrait(new MaterialComposition(product, materialData, materials));
			
			product.setTrait(new Company(product, companyData));
			
			System.out.print("Approximate shipping distance (km): ");
			double distance;
			while ((distance = safeNextDouble(input)) < 0) {
				System.out.print("You entered a negative number. Please enter a positive number: ");
			}
			input.nextLine();
			product.setTrait(new Shipping(product, distance, shippingData));
			
			System.out.println();

			product.breakdown(System.out);
			
			System.out.println();

			System.out.print("Do you want to do another product? (yes/no): ");
			reading = input.nextLine().equals("yes");
			
			System.out.println();
		}
	}
}
