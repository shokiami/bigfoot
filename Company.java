import java.io.FileNotFoundException;

public class Company implements ProductTrait{
    private String name;
    private Map<String, Integer> companyEmissions;

    public Company(String name) throws FileNotFoundException {
        Scanner input = new Scanner(new File("companies.tsv"));
        
        
    }

    public double estimate(Product product) {

    }
}
