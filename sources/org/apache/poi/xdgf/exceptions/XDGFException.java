package org.apache.poi.xdgf.exceptions;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;

public class XDGFException {
    public static POIXMLException error(String str, Object obj) {
        return new POIXMLException(obj + ": " + str);
    }

    public static POIXMLException error(String str, Object obj, Throwable th) {
        return new POIXMLException(obj + ": " + str, th);
    }

    public static POIXMLException wrap(POIXMLDocumentPart pOIXMLDocumentPart, POIXMLException pOIXMLException) {
        String str = pOIXMLDocumentPart.getPackagePart().getPartName() + ": " + pOIXMLException.getMessage();
        Throwable cause = pOIXMLException.getCause();
        Throwable th = pOIXMLException;
        if (cause != null) {
            th = pOIXMLException.getCause();
        }
        return new POIXMLException(str, th);
    }

    public static POIXMLException wrap(String str, POIXMLException pOIXMLException) {
        String str2 = str + ": " + pOIXMLException.getMessage();
        Throwable cause = pOIXMLException.getCause();
        Throwable th = pOIXMLException;
        if (cause != null) {
            th = pOIXMLException.getCause();
        }
        return new POIXMLException(str2, th);
    }
}
