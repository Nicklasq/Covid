import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.ArrayList;
public class FileHandler {
    public static void main(String[] args) throws IOException {
        ArrayList<Covid19Data> covidDataList = new ArrayList<>();

        PrintStream p = new PrintStream("covid19data.csv");
        System.setOut(p);

        try {
            File f = new File("11_noegletal_pr_region_pr_aldersgruppe.csv");
            try (Scanner sc = new Scanner(f, StandardCharsets.ISO_8859_1)) {
                //sc.nextLine();
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] data = line.split(",");
                    if (args.length == 7) {
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

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}



//TODO Få den printet ud i csv filen ...


