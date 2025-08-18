package org.apache.poi.poifs.crypt.dsig;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;

public class DSigRelation extends POIXMLRelation {
    public static final DSigRelation ORIGIN_SIGS = new DSigRelation(ContentTypes.DIGITAL_SIGNATURE_ORIGIN_PART, PackageRelationshipTypes.DIGITAL_SIGNATURE_ORIGIN, "/_xmlsignatures/origin.sigs");
    public static final DSigRelation SIG = new DSigRelation(ContentTypes.DIGITAL_SIGNATURE_XML_SIGNATURE_PART, PackageRelationshipTypes.DIGITAL_SIGNATURE, "/_xmlsignatures/sig#.xml");
    private static final Map<String, DSigRelation> _table = new HashMap();

    private DSigRelation(String str, String str2, String str3) {
        super(str, str2, str3);
        _table.put(str2, this);
    }

    public static DSigRelation getInstance(String str) {
        return _table.get(str);
    }
}
