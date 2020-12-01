import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Product product = new Product();
		MaterialData materialdata = new MaterialData(new Scanner(new File("ice-dataset.tsv")));
		
		product.setTrait(new MaterialComposition(product, materialdata, new HashMap<Material,Double>()));
		
		double val = product.estimate();
		System.out.println(val);
	}
}
