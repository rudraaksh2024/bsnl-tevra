package org.etsi.uri.x01903.v13;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class QualifierType$Enum extends StringEnumAbstractBase {
    static final int INT_OID_AS_URI = 1;
    static final int INT_OID_AS_URN = 2;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new QualifierType$Enum[]{new QualifierType$Enum("OIDAsURI", 1), new QualifierType$Enum("OIDAsURN", 2)});

    public static QualifierType$Enum forString(String str) {
        return (QualifierType$Enum) table.forString(str);
    }

    public static QualifierType$Enum forInt(int i) {
        return (QualifierType$Enum) table.forInt(i);
    }

    private QualifierType$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
