package org.apache.poi.xslf.model;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;

public abstract class TextBodyPropertyFetcher<T> extends PropertyFetcher<T> {
    private static final QName[] BODY_PR = {new QName(XSSFRelation.NS_DRAWINGML, "bodyPr")};
    private static final QName[] TX_BODY = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "txBody")};

    public abstract boolean fetch(CTTextBodyProperties cTTextBodyProperties);

    public boolean fetch(XSLFShape xSLFShape) {
        try {
            CTTextBodyProperties cTTextBodyProperties = (CTTextBodyProperties) XPathHelper.selectProperty(xSLFShape.getXmlObject(), CTTextBodyProperties.class, new TextBodyPropertyFetcher$$ExternalSyntheticLambda0(), TX_BODY, BODY_PR);
            if (cTTextBodyProperties == null || !fetch(cTTextBodyProperties)) {
                return false;
            }
            return true;
        } catch (XmlException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static CTTextBodyProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
        CTTextBody parse = CTTextBody.Factory.parse(xMLStreamReader);
        if (parse != null) {
            return parse.getBodyPr();
        }
        return null;
    }
}
