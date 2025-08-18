package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;

public interface IBodyElement {
    IBody getBody();

    BodyElementType getElementType();

    POIXMLDocumentPart getPart();

    BodyType getPartType();
}
