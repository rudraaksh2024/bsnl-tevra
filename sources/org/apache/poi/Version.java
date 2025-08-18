package org.apache.poi;

public class Version {
    private static final String RELEASE_DATE = "20220312";
    private static final String VERSION_STRING = "5.2.2";

    public static String getImplementationLanguage() {
        return "Java";
    }

    public static String getProduct() {
        return "POI";
    }

    public static String getReleaseDate() {
        return RELEASE_DATE;
    }

    public static String getVersion() {
        return VERSION_STRING;
    }

    public static void main(String[] strArr) {
        System.out.println("Apache " + getProduct() + " " + getVersion() + " (" + getReleaseDate() + ")");
    }
}
