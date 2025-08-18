package org.apache.poi.xssf.usermodel;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.excel.STObjectType;
import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.vml.CTImageData;
import javax.xml.namespace.QName;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.poifs.crypt.dsig.SignatureLine;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;

public class XSSFSignatureLine extends SignatureLine {
    private static final String MS_VML_URN = "urn:schemas-microsoft-com:vml";

    public void parse(XSSFSheet xSSFSheet) throws XmlException {
        XSSFVMLDrawing vMLDrawing = xSSFSheet.getVMLDrawing(false);
        if (vMLDrawing != null) {
            CTSignatureLine cTSignatureLine = (CTSignatureLine) XPathHelper.selectProperty(vMLDrawing.getDocument(), CTSignatureLine.class, (XSLFShape.ReparseFactory) null, new QName[]{XSSFVMLDrawing.QNAME_VMLDRAWING}, new QName[]{new QName(MS_VML_URN, "shape")}, new QName[]{QNAME_SIGNATURE_LINE});
            if (cTSignatureLine != null) {
                setSignatureShape(cTSignatureLine);
                parse();
            }
        }
    }

    public void add(XSSFSheet xSSFSheet, XSSFClientAnchor xSSFClientAnchor) {
        add(xSSFSheet.getVMLDrawing(true).getDocument().getXml(), new XSSFSignatureLine$$ExternalSyntheticLambda0(this, xSSFSheet));
        CTClientData addNewClientData = getSignatureShape().addNewClientData();
        addNewClientData.addAnchor(xSSFClientAnchor.getCol1() + ", " + xSSFClientAnchor.getDx1() + ", " + xSSFClientAnchor.getRow1() + ", " + xSSFClientAnchor.getDy1() + ", " + xSSFClientAnchor.getCol2() + ", " + xSSFClientAnchor.getDx2() + ", " + xSSFClientAnchor.getRow2() + ", " + xSSFClientAnchor.getDy2());
        addNewClientData.setObjectType(STObjectType.PICT);
        addNewClientData.addSizeWithCells(STTrueFalseBlank.X);
        addNewClientData.addCF(ContentTypes.EXTENSION_PICT);
        addNewClientData.addAutoPict(STTrueFalseBlank.X);
    }

    /* access modifiers changed from: protected */
    public void setRelationId(CTImageData cTImageData, String str) {
        cTImageData.setRelid(str);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        if (r4 != null) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
        throw r3;
     */
    /* renamed from: addPicture */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String m2312lambda$add$0$orgapachepoixssfusermodelXSSFSignatureLine(byte[] r3, org.apache.poi.common.usermodel.PictureType r4, org.apache.poi.xssf.usermodel.XSSFSheet r5) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            r2 = this;
            org.apache.poi.xssf.usermodel.XSSFWorkbook r2 = r5.getWorkbook()
            r0 = 0
            org.apache.poi.xssf.usermodel.XSSFVMLDrawing r5 = r5.getVMLDrawing(r0)
            org.apache.poi.ooxml.POIXMLRelation r4 = mapType(r4)
            r1 = -1
            int r1 = r2.getNextPartNumber(r4, r1)
            org.apache.poi.xssf.usermodel.XSSFFactory r2 = r2.getXssfFactory()
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r2 = r5.createRelationship(r4, r2, r1, r0)
            org.apache.poi.ooxml.POIXMLDocumentPart r4 = r2.getDocumentPart()
            org.apache.poi.openxml4j.opc.PackagePart r4 = r4.getPackagePart()     // Catch:{ IOException -> 0x0045 }
            java.io.OutputStream r4 = r4.getOutputStream()     // Catch:{ IOException -> 0x0045 }
            r4.write(r3)     // Catch:{ all -> 0x0037 }
            if (r4 == 0) goto L_0x002e
            r4.close()     // Catch:{ IOException -> 0x0045 }
        L_0x002e:
            org.apache.poi.openxml4j.opc.PackageRelationship r2 = r2.getRelationship()
            java.lang.String r2 = r2.getId()
            return r2
        L_0x0037:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r3 = move-exception
            if (r4 == 0) goto L_0x0044
            r4.close()     // Catch:{ all -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ IOException -> 0x0045 }
        L_0x0044:
            throw r3     // Catch:{ IOException -> 0x0045 }
        L_0x0045:
            r2 = move-exception
            org.apache.poi.ooxml.POIXMLException r3 = new org.apache.poi.ooxml.POIXMLException
            r3.<init>((java.lang.Throwable) r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFSignatureLine.m2312lambda$add$0$orgapachepoixssfusermodelXSSFSignatureLine(byte[], org.apache.poi.common.usermodel.PictureType, org.apache.poi.xssf.usermodel.XSSFSheet):java.lang.String");
    }

    /* renamed from: org.apache.poi.xssf.usermodel.XSSFSignatureLine$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$common$usermodel$PictureType;

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.common.usermodel.PictureType[] r0 = org.apache.poi.common.usermodel.PictureType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$common$usermodel$PictureType = r0
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.BMP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$PictureType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.DIB     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$PictureType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.EMF     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$PictureType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.EPS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$PictureType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.GIF     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$PictureType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.JPEG     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$PictureType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.PICT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$PictureType     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.PNG     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$PictureType     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.TIFF     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$PictureType     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.WMF     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$PictureType     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.poi.common.usermodel.PictureType r1 = org.apache.poi.common.usermodel.PictureType.WPG     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFSignatureLine.AnonymousClass1.<clinit>():void");
        }
    }

    private static POIXMLRelation mapType(PictureType pictureType) throws InvalidFormatException {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$PictureType[pictureType.ordinal()]) {
            case 1:
                return XSSFRelation.IMAGE_BMP;
            case 2:
                return XSSFRelation.IMAGE_DIB;
            case 3:
                return XSSFRelation.IMAGE_EMF;
            case 4:
                return XSSFRelation.IMAGE_EPS;
            case 5:
                return XSSFRelation.IMAGE_GIF;
            case 6:
                return XSSFRelation.IMAGE_JPEG;
            case 7:
                return XSSFRelation.IMAGE_PICT;
            case 8:
                return XSSFRelation.IMAGE_PNG;
            case 9:
                return XSSFRelation.IMAGE_TIFF;
            case 10:
                return XSSFRelation.IMAGE_WMF;
            case 11:
                return XSSFRelation.IMAGE_WPG;
            default:
                throw new InvalidFormatException("Unsupported picture format " + pictureType);
        }
    }
}
