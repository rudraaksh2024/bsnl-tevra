package com.microsoft.schemas.office.x2006.digsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSignatureInfoV1 extends XmlObject {
    public static final DocumentFactory<CTSignatureInfoV1> Factory;
    public static final SchemaType type;

    String getApplicationVersion();

    int getColorDepth();

    String getDelegateSuggestedSigner();

    String getDelegateSuggestedSigner2();

    String getDelegateSuggestedSignerEmail();

    int getHorizontalResolution();

    String getManifestHashAlgorithm();

    int getMonitors();

    String getOfficeVersion();

    String getSetupID();

    String getSignatureComments();

    byte[] getSignatureImage();

    int getSignatureProviderDetails();

    String getSignatureProviderId();

    String getSignatureProviderUrl();

    String getSignatureText();

    int getSignatureType();

    int getVerticalResolution();

    String getWindowsVersion();

    boolean isSetDelegateSuggestedSigner();

    boolean isSetDelegateSuggestedSigner2();

    boolean isSetDelegateSuggestedSignerEmail();

    boolean isSetManifestHashAlgorithm();

    void setApplicationVersion(String str);

    void setColorDepth(int i);

    void setDelegateSuggestedSigner(String str);

    void setDelegateSuggestedSigner2(String str);

    void setDelegateSuggestedSignerEmail(String str);

    void setHorizontalResolution(int i);

    void setManifestHashAlgorithm(String str);

    void setMonitors(int i);

    void setOfficeVersion(String str);

    void setSetupID(String str);

    void setSignatureComments(String str);

    void setSignatureImage(byte[] bArr);

    void setSignatureProviderDetails(int i);

    void setSignatureProviderId(String str);

    void setSignatureProviderUrl(String str);

    void setSignatureText(String str);

    void setSignatureType(int i);

    void setVerticalResolution(int i);

    void setWindowsVersion(String str);

    void unsetDelegateSuggestedSigner();

    void unsetDelegateSuggestedSigner2();

    void unsetDelegateSuggestedSignerEmail();

    void unsetManifestHashAlgorithm();

    STVersion xgetApplicationVersion();

    STPositiveInteger xgetColorDepth();

    XmlString xgetDelegateSuggestedSigner();

    XmlString xgetDelegateSuggestedSigner2();

    XmlString xgetDelegateSuggestedSignerEmail();

    STPositiveInteger xgetHorizontalResolution();

    XmlAnyURI xgetManifestHashAlgorithm();

    STPositiveInteger xgetMonitors();

    STVersion xgetOfficeVersion();

    STUniqueIdentifierWithBraces xgetSetupID();

    STSignatureComments xgetSignatureComments();

    XmlBase64Binary xgetSignatureImage();

    XmlInt xgetSignatureProviderDetails();

    STUniqueIdentifierWithBraces xgetSignatureProviderId();

    STSignatureProviderUrl xgetSignatureProviderUrl();

    STSignatureText xgetSignatureText();

    STSignatureType xgetSignatureType();

    STPositiveInteger xgetVerticalResolution();

    STVersion xgetWindowsVersion();

    void xsetApplicationVersion(STVersion sTVersion);

    void xsetColorDepth(STPositiveInteger sTPositiveInteger);

    void xsetDelegateSuggestedSigner(XmlString xmlString);

    void xsetDelegateSuggestedSigner2(XmlString xmlString);

    void xsetDelegateSuggestedSignerEmail(XmlString xmlString);

    void xsetHorizontalResolution(STPositiveInteger sTPositiveInteger);

    void xsetManifestHashAlgorithm(XmlAnyURI xmlAnyURI);

    void xsetMonitors(STPositiveInteger sTPositiveInteger);

    void xsetOfficeVersion(STVersion sTVersion);

    void xsetSetupID(STUniqueIdentifierWithBraces sTUniqueIdentifierWithBraces);

    void xsetSignatureComments(STSignatureComments sTSignatureComments);

    void xsetSignatureImage(XmlBase64Binary xmlBase64Binary);

    void xsetSignatureProviderDetails(XmlInt xmlInt);

    void xsetSignatureProviderId(STUniqueIdentifierWithBraces sTUniqueIdentifierWithBraces);

    void xsetSignatureProviderUrl(STSignatureProviderUrl sTSignatureProviderUrl);

    void xsetSignatureText(STSignatureText sTSignatureText);

    void xsetSignatureType(STSignatureType sTSignatureType);

    void xsetVerticalResolution(STPositiveInteger sTPositiveInteger);

    void xsetWindowsVersion(STVersion sTVersion);

    static {
        DocumentFactory<CTSignatureInfoV1> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsignatureinfov13a5ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
