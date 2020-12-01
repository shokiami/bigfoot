import java.util.*;

public class Main {
	public static void main(String[] args) {
		Product product = new Product();
		
		product.setTrait(new MaterialComposition(product, new HashMap<Material,Double>()));
		
		double val = product.estimate();
		System.out.println(val);
	}
}
