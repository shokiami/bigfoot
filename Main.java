import java.util.*;

public class Main {
	public static void main(String[] args) {
		Product product = new Product();
		
		Materials mats = new Materials();
		Shipping ship = new Shipping(new HashMap<String, Double>());
		
		System.out.println(mats);
		System.out.println(ship);
		
		product.<Material>setTrait(mats);
		product.<Shipping>setTrait(ship);
		
		System.out.println(product.getTrait(Materials.class));
		System.out.println(product.getTrait(Shipping.class));
		//System.out.println(product.getTrait(Company.class));
	}
}
