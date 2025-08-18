package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.RandomSingleton;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STAlgClass;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STAlgType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STCryptProv;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.SettingsDocument;

public class XWPFSettings extends POIXMLDocumentPart {
    private CTSettings ctSettings;

    public XWPFSettings(PackagePart packagePart) throws IOException {
        super(packagePart);
    }

    public XWPFSettings() {
        this.ctSettings = CTSettings.Factory.newInstance();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        if (r0 != null) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() throws java.io.IOException {
        /*
            r2 = this;
            super.onDocumentRead()
            org.apache.poi.openxml4j.opc.PackagePart r0 = r2.getPackagePart()
            java.io.InputStream r0 = r0.getInputStream()
            r2.readFrom(r0)     // Catch:{ all -> 0x0014 }
            if (r0 == 0) goto L_0x0013
            r0.close()
        L_0x0013:
            return
        L_0x0014:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0016 }
        L_0x0016:
            r1 = move-exception
            if (r0 == 0) goto L_0x0021
            r0.close()     // Catch:{ all -> 0x001d }
            goto L_0x0021
        L_0x001d:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x0021:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFSettings.onDocumentRead():void");
    }

    public long getZoomPercent() {
        CTZoom cTZoom;
        if (!this.ctSettings.isSetZoom()) {
            cTZoom = this.ctSettings.addNewZoom();
        } else {
            cTZoom = this.ctSettings.getZoom();
        }
        if (cTZoom.getPercent() == null) {
            return 100;
        }
        return (long) (POIXMLUnits.parsePercent(cTZoom.xgetPercent()) / 1000);
    }

    public void setZoomPercent(long j) {
        if (!this.ctSettings.isSetZoom()) {
            this.ctSettings.addNewZoom();
        }
        this.ctSettings.getZoom().setPercent(BigInteger.valueOf(j));
    }

    public boolean isEnforcedWith() {
        CTDocProtect documentProtection = this.ctSettings.getDocumentProtection();
        return documentProtection != null && POIXMLUnits.parseOnOff(documentProtection.xgetEnforcement());
    }

    public boolean isEnforcedWith(STDocProtect.Enum enumR) {
        CTDocProtect documentProtection = this.ctSettings.getDocumentProtection();
        return documentProtection != null && POIXMLUnits.parseOnOff(documentProtection.xgetEnforcement()) && documentProtection.getEdit().equals(enumR);
    }

    public void setEnforcementEditValue(STDocProtect.Enum enumR) {
        safeGetDocumentProtection().setEnforcement(STOnOff1.ON);
        safeGetDocumentProtection().setEdit(enumR);
    }

    public void setEnforcementEditValue(STDocProtect.Enum enumR, String str, HashAlgorithm hashAlgorithm) {
        STCryptProv.Enum enumR2;
        int i;
        safeGetDocumentProtection().setEnforcement(STOnOff1.ON);
        safeGetDocumentProtection().setEdit(enumR);
        if (str == null) {
            if (safeGetDocumentProtection().isSetCryptProviderType()) {
                safeGetDocumentProtection().unsetCryptProviderType();
            }
            if (safeGetDocumentProtection().isSetCryptAlgorithmClass()) {
                safeGetDocumentProtection().unsetCryptAlgorithmClass();
            }
            if (safeGetDocumentProtection().isSetCryptAlgorithmType()) {
                safeGetDocumentProtection().unsetCryptAlgorithmType();
            }
            if (safeGetDocumentProtection().isSetCryptAlgorithmSid()) {
                safeGetDocumentProtection().unsetCryptAlgorithmSid();
            }
            if (safeGetDocumentProtection().isSetSalt()) {
                safeGetDocumentProtection().unsetSalt();
            }
            if (safeGetDocumentProtection().isSetCryptSpinCount()) {
                safeGetDocumentProtection().unsetCryptSpinCount();
            }
            if (safeGetDocumentProtection().isSetHash()) {
                safeGetDocumentProtection().unsetHash();
                return;
            }
            return;
        }
        if (hashAlgorithm == null) {
            hashAlgorithm = HashAlgorithm.sha1;
        }
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm[hashAlgorithm.ordinal()]) {
            case 1:
                enumR2 = STCryptProv.RSA_FULL;
                i = 1;
                break;
            case 2:
                enumR2 = STCryptProv.RSA_FULL;
                i = 2;
                break;
            case 3:
                enumR2 = STCryptProv.RSA_FULL;
                i = 3;
                break;
            case 4:
                enumR2 = STCryptProv.RSA_FULL;
                i = 4;
                break;
            case 5:
                enumR2 = STCryptProv.RSA_AES;
                i = 12;
                break;
            case 6:
                enumR2 = STCryptProv.RSA_AES;
                i = 13;
                break;
            case 7:
                enumR2 = STCryptProv.RSA_AES;
                i = 14;
                break;
            default:
                throw new EncryptedDocumentException("Hash algorithm '" + hashAlgorithm + "' is not supported for document write protection.");
        }
        byte[] generateSeed = RandomSingleton.getInstance().generateSeed(16);
        byte[] hashPassword = CryptoFunctions.hashPassword(CryptoFunctions.xorHashPasswordReversed(str), hashAlgorithm, generateSeed, BZip2Constants.BASEBLOCKSIZE, false);
        safeGetDocumentProtection().setSalt(generateSeed);
        safeGetDocumentProtection().setHash(hashPassword);
        safeGetDocumentProtection().setCryptSpinCount(BigInteger.valueOf((long) BZip2Constants.BASEBLOCKSIZE));
        safeGetDocumentProtection().setCryptAlgorithmType(STAlgType.TYPE_ANY);
        safeGetDocumentProtection().setCryptAlgorithmClass(STAlgClass.HASH);
        safeGetDocumentProtection().setCryptProviderType(enumR2);
        safeGetDocumentProtection().setCryptAlgorithmSid(BigInteger.valueOf((long) i));
    }

    /* renamed from: org.apache.poi.xwpf.usermodel.XWPFSettings$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.poifs.crypt.HashAlgorithm[] r0 = org.apache.poi.poifs.crypt.HashAlgorithm.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm = r0
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.md2     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.md4     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.md5     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha1     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha256     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha384     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha512     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFSettings.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean validateProtectionPassword(String str) {
        HashAlgorithm hashAlgorithm;
        BigInteger cryptAlgorithmSid = safeGetDocumentProtection().getCryptAlgorithmSid();
        byte[] hash = safeGetDocumentProtection().getHash();
        byte[] salt = safeGetDocumentProtection().getSalt();
        BigInteger cryptSpinCount = safeGetDocumentProtection().getCryptSpinCount();
        if (cryptAlgorithmSid == null || hash == null || salt == null || cryptSpinCount == null) {
            return false;
        }
        int intValue = cryptAlgorithmSid.intValue();
        if (intValue == 1) {
            hashAlgorithm = HashAlgorithm.md2;
        } else if (intValue == 2) {
            hashAlgorithm = HashAlgorithm.md4;
        } else if (intValue == 3) {
            hashAlgorithm = HashAlgorithm.md5;
        } else if (intValue != 4) {
            switch (intValue) {
                case 12:
                    hashAlgorithm = HashAlgorithm.sha256;
                    break;
                case 13:
                    hashAlgorithm = HashAlgorithm.sha384;
                    break;
                case 14:
                    hashAlgorithm = HashAlgorithm.sha512;
                    break;
                default:
                    return false;
            }
        } else {
            hashAlgorithm = HashAlgorithm.sha1;
        }
        return Arrays.equals(hash, CryptoFunctions.hashPassword(CryptoFunctions.xorHashPasswordReversed(str), hashAlgorithm, salt, cryptSpinCount.intValue(), false));
    }

    public void removeEnforcement() {
        safeGetDocumentProtection().setEnforcement(STOnOff1.OFF);
    }

    public void setUpdateFields() {
        CTOnOff newInstance = CTOnOff.Factory.newInstance();
        newInstance.setVal(STOnOff1.ON);
        this.ctSettings.setUpdateFields(newInstance);
    }

    /* access modifiers changed from: package-private */
    public boolean isUpdateFields() {
        return this.ctSettings.isSetUpdateFields() && POIXMLUnits.parseOnOff(this.ctSettings.getUpdateFields().xgetVal());
    }

    public boolean isTrackRevisions() {
        return this.ctSettings.isSetTrackRevisions();
    }

    public void setTrackRevisions(boolean z) {
        if (z) {
            if (!this.ctSettings.isSetTrackRevisions()) {
                this.ctSettings.addNewTrackRevisions();
            }
        } else if (this.ctSettings.isSetTrackRevisions()) {
            this.ctSettings.unsetTrackRevisions();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        if (r1 != null) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r4 = this;
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings r0 = r4.ctSettings
            if (r0 == 0) goto L_0x0040
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "settings"
            r1.<init>(r2, r3)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings r4 = r4.ctSettings     // Catch:{ all -> 0x0032 }
            r4.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0031
            r1.close()
        L_0x0031:
            return
        L_0x0032:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r0 = move-exception
            if (r1 == 0) goto L_0x003f
            r1.close()     // Catch:{ all -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r1 = move-exception
            r4.addSuppressed(r1)
        L_0x003f:
            throw r0
        L_0x0040:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Unable to write out settings that were never read in!"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFSettings.commit():void");
    }

    private CTDocProtect safeGetDocumentProtection() {
        if (this.ctSettings.getDocumentProtection() == null) {
            this.ctSettings.setDocumentProtection(CTDocProtect.Factory.newInstance());
        }
        return this.ctSettings.getDocumentProtection();
    }

    private void readFrom(InputStream inputStream) {
        try {
            this.ctSettings = SettingsDocument.Factory.parse(inputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getSettings();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getEvenAndOddHeadings() {
        return this.ctSettings.isSetEvenAndOddHeaders();
    }

    public void setEvenAndOddHeadings(boolean z) {
        CTOnOff newInstance = CTOnOff.Factory.newInstance();
        newInstance.setVal(z ? STOnOff1.ON : STOnOff1.OFF);
        this.ctSettings.setEvenAndOddHeaders(newInstance);
    }

    public boolean getMirrorMargins() {
        return this.ctSettings.isSetMirrorMargins();
    }

    public void setMirrorMargins(boolean z) {
        CTOnOff newInstance = CTOnOff.Factory.newInstance();
        newInstance.setVal(z ? STOnOff1.ON : STOnOff1.OFF);
        this.ctSettings.setMirrorMargins(newInstance);
    }
}
