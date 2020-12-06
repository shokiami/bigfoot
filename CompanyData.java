import java.io.*;
import java.util.*;

public class CompanyData{
    private Map<String, Integer> companyEmissions;
    private double averageEmissions;

    public CompanyData() throws FileNotFoundException {
        companyEmissions = new HashMap<>();
        Scanner input = new Scanner(new File("companies.tsv"));
        while (input.hasNext()) {
            companyEmissions.put(input.next(), input.nextInt());
        }
        double total = 0;
        int num = 0;
        for (String company : companyEmissions.keySet()) {
            total += companyEmissions.get(company);
            num++;
        }
        averageEmissions = total / num;
    }

    public double getEmissions(String company) {
        if (companyEmissions.containsKey(company)) {
            return companyEmissions.get(company);
        }
        return averageEmissions;
    }
}
