public class Covid19Data {

    private String region;
    private String aldersgruppe;
    private int positive;
    private int døde;
    private int indlagteIintensiv;
    private int indlagte;
    private String dato;

    public Covid19Data(String region, String aldersgruppe, int positive, int døde, int indlagteIintensiv, int indlagte, String dato) {
        this.region = region;
        this.aldersgruppe = aldersgruppe;
        this.positive = positive;
        this.døde = døde;
        this.indlagteIintensiv = indlagteIintensiv;
        this.indlagte = indlagte;
        this.dato = dato;

    }

    @Override
    public String toString() {
        return "region " + region + ", aldersgruppe" + aldersgruppe + ", positive " + positive + ", døde " + døde
                + ", indlagteIintensiv " + indlagteIintensiv + ", indlagte" + indlagte + ", dato " + dato;
    }

    public static boolean validateLine(String[] line) {
        if (line.length < 7) {
            return false;
        }
        String validRegioner[] = {"Hovedstaden", "Midtjylland", "Nordjylland", "Sjælland", "Syddanmark"};
        String region = line[0];
        for (String validRegion : validRegioner) {
            if (validRegion.equals(region)){
            return true;
        }
    }
    return false;

}

}
