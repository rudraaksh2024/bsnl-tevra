package org.apache.poi.xwpf.usermodel;

import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.vml.CTImageData;
import javax.xml.namespace.QName;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.poifs.crypt.dsig.SignatureLine;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.xmlbeans.XmlException;

public class XWPFSignatureLine extends SignatureLine {
    private static final String MS_VML_URN = "urn:schemas-microsoft-com:vml";
    static final String NS_OOXML_WP_MAIN = "http://schemas.openxmlformats.org/wordprocessingml/2006/main";
    private CTSignatureLine line;

    public void parse(XWPFDocument xWPFDocument) throws XmlException {
        CTSignatureLine cTSignatureLine = (CTSignatureLine) XPathHelper.selectProperty(xWPFDocument.getDocument(), CTSignatureLine.class, (XSLFShape.ReparseFactory) null, new QName[]{new QName(NS_OOXML_WP_MAIN, "body")}, new QName[]{new QName(NS_OOXML_WP_MAIN, "p")}, new QName[]{new QName(NS_OOXML_WP_MAIN, "r")}, new QName[]{new QName(NS_OOXML_WP_MAIN, ContentTypes.EXTENSION_PICT)}, new QName[]{new QName(MS_VML_URN, "shape")}, new QName[]{QNAME_SIGNATURE_LINE});
        this.line = cTSignatureLine;
        if (cTSignatureLine != null) {
            setSignatureShape(cTSignatureLine);
            parse();
        }
    }

    public void add(XWPFParagraph xWPFParagraph) {
        add(xWPFParagraph.createRun().getCTR().addNewPict(), new XWPFSignatureLine$$ExternalSyntheticLambda0(xWPFParagraph));
    }

    /* access modifiers changed from: protected */
    public void setRelationId(CTImageData cTImageData, String str) {
        cTImageData.setId2(str);
    }

    /* renamed from: org.apache.poi.xwpf.usermodel.XWPFSignatureLine$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFSignatureLine.AnonymousClass1.<clinit>():void");
        }
    }

    private static int mapType(PictureType pictureType) throws InvalidFormatException {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$PictureType[pictureType.ordinal()]) {
            case 1:
                return 11;
            case 2:
                return 7;
            case 3:
                return 2;
            case 4:
                return 10;
            case 5:
                return 8;
            case 6:
                return 5;
            case 7:
                return 4;
            case 8:
                return 6;
            case 9:
                return 9;
            case 10:
                return 3;
            case 11:
                return 12;
            default:
                throw new InvalidFormatException("Unsupported picture format " + pictureType);
        }
    }
}
