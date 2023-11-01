import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class FileHandler {
    public static void main(String[] args) {
        ArrayList<Covid19Data> covidDataList = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("11_noegletal_pr_region_pr_aldersgruppe.csv"), StandardCharsets.ISO_8859_1.name())) {
            sc.nextLine(); // Skip the header line

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(";");
                if (data.length == 7 && Covid19Data.validateLine(data)) {
                    String region = data[0];
                    String aldersgruppe = data[1];
                    int positive = Integer.parseInt(data[2]);
                    int døde = Integer.parseInt(data[3]);
                    int indlagteIintensiv = Integer.parseInt(data[4]);
                    int indlagte = Integer.parseInt(data[5]);
                    String dato = data[6];

                    Covid19Data covidData = new Covid19Data(region, aldersgruppe, positive, døde, indlagteIintensiv, indlagte, dato);
                    covidDataList.add(covidData);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Sort the data by region and date
        covidDataList.sort(new Covid19DataComparator());

        // Write the sorted data to "covid19data.csv"
        try (PrintWriter writer = new PrintWriter("covid19data.csv")) {
            for (Covid19Data data : covidDataList) {
                // Use the correct delimiter, which is ';'
                writer.println(data.getRegion() + ";" + data.getDato() + ";" + data.getAldersgruppe() + ";" +
                        data.getPositive() + ";" + data.getDøde() + ";" + data.getIndlagteIintensiv() + ";" + data.getIndlagte());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Custom comparator for sorting Covid19Data
class Covid19DataComparator implements Comparator<Covid19Data> {
    @Override
    public int compare(Covid19Data data1, Covid19Data data2) {
        int regionComparison = data1.getRegion().compareTo(data2.getRegion());
        if (regionComparison == 0) {
            return data1.getDato().compareTo(data2.getDato());
        }
        return regionComparison;
    }
}