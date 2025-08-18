package org.apache.poi.poifs.crypt;

public enum ChainingMode {
    ecb("ECB", 1, (String) null),
    cbc("CBC", 2, "ChainingModeCBC"),
    cfb("CFB8", 3, "ChainingModeCFB");
    
    public final int ecmaId;
    public final String jceId;
    public final String xmlId;

    private ChainingMode(String str, int i, String str2) {
        this.jceId = str;
        this.ecmaId = i;
        this.xmlId = str2;
    }

    public static ChainingMode fromXmlId(String str) {
        for (ChainingMode chainingMode : values()) {
            String str2 = chainingMode.xmlId;
            if (str2 != null && str2.equals(str)) {
                return chainingMode;
            }
        }
        return null;
    }
}
