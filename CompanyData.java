import java.io.*;
import java.util.*;

public class CompanyData{
    private Map<String, Integer> companyEmissions;

    public CompanyData() throws FileNotFoundException {
        companyEmissions = new HashMap<>();
        Scanner input = new Scanner(new File("companies.tsv"));
        while (input.hasNext()) {
            companyEmissions.put(input.next(), input.nextInt());
        }
    }

    public Map<String, Integer> getEmissions() {
        return companyEmissions;
    }
}
