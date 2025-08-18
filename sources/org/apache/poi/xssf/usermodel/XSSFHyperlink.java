package org.apache.poi.xssf.usermodel;

import androidx.core.net.MailTo;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlink;

public class XSSFHyperlink implements Hyperlink, Duplicatable {
    private final CTHyperlink _ctHyperlink;
    private final PackageRelationship _externalRel;
    private String _location;
    private final HyperlinkType _type;

    protected XSSFHyperlink(HyperlinkType hyperlinkType) {
        this._type = hyperlinkType;
        this._ctHyperlink = CTHyperlink.Factory.newInstance();
        this._externalRel = null;
    }

    protected XSSFHyperlink(CTHyperlink cTHyperlink, PackageRelationship packageRelationship) {
        this._ctHyperlink = cTHyperlink;
        this._externalRel = packageRelationship;
        if (packageRelationship != null) {
            this._location = packageRelationship.getTargetURI().toString();
            if (cTHyperlink.getLocation() != null) {
                this._location += "#" + cTHyperlink.getLocation();
            }
            if (this._location.startsWith("http://") || this._location.startsWith("https://") || this._location.startsWith("ftp://")) {
                this._type = HyperlinkType.URL;
            } else if (this._location.startsWith(MailTo.MAILTO_SCHEME)) {
                this._type = HyperlinkType.EMAIL;
            } else {
                this._type = HyperlinkType.FILE;
            }
        } else if (cTHyperlink.getLocation() != null) {
            this._type = HyperlinkType.DOCUMENT;
            this._location = cTHyperlink.getLocation();
        } else if (cTHyperlink.getId() == null) {
            this._type = HyperlinkType.DOCUMENT;
        } else {
            throw new IllegalStateException("The hyperlink for cell " + cTHyperlink.getRef() + " references relation " + cTHyperlink.getId() + ", but that didn't exist!");
        }
    }

    @Internal
    public XSSFHyperlink(Hyperlink hyperlink) {
        if (hyperlink instanceof XSSFHyperlink) {
            XSSFHyperlink xSSFHyperlink = (XSSFHyperlink) hyperlink;
            this._type = xSSFHyperlink.getType();
            this._location = xSSFHyperlink._location;
            this._externalRel = xSSFHyperlink._externalRel;
            this._ctHyperlink = (CTHyperlink) xSSFHyperlink._ctHyperlink.copy();
            return;
        }
        this._type = hyperlink.getType();
        this._location = hyperlink.getAddress();
        this._externalRel = null;
        this._ctHyperlink = CTHyperlink.Factory.newInstance();
        setCellReference(new CellReference(hyperlink.getFirstRow(), hyperlink.getFirstColumn()));
    }

    @Internal
    public CTHyperlink getCTHyperlink() {
        return this._ctHyperlink;
    }

    public boolean needsRelationToo() {
        return this._type != HyperlinkType.DOCUMENT;
    }

    /* access modifiers changed from: protected */
    public void generateRelationIfNeeded(PackagePart packagePart) {
        if (this._externalRel == null && needsRelationToo()) {
            this._ctHyperlink.setId(packagePart.addExternalRelationship(this._location, XSSFRelation.SHEET_HYPERLINKS.getRelation()).getId());
        }
    }

    public HyperlinkType getType() {
        return this._type;
    }

    public String getCellRef() {
        return this._ctHyperlink.getRef();
    }

    public String getAddress() {
        return this._location;
    }

    public String getLabel() {
        return this._ctHyperlink.getDisplay();
    }

    public String getLocation() {
        return this._ctHyperlink.getLocation();
    }

    public void setLabel(String str) {
        this._ctHyperlink.setDisplay(str);
    }

    public void setLocation(String str) {
        this._ctHyperlink.setLocation(str);
    }

    public void setAddress(String str) {
        validate(str);
        this._location = str;
        if (this._type == HyperlinkType.DOCUMENT) {
            setLocation(str);
        }
    }

    /* renamed from: org.apache.poi.xssf.usermodel.XSSFHyperlink$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.common.usermodel.HyperlinkType[] r0 = org.apache.poi.common.usermodel.HyperlinkType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType = r0
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.EMAIL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.FILE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.URL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.DOCUMENT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFHyperlink.AnonymousClass1.<clinit>():void");
        }
    }

    private void validate(String str) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$HyperlinkType[this._type.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            try {
                new URI(str);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Address of hyperlink must be a valid URI", e);
            }
        } else if (i != 4) {
            throw new IllegalStateException("Invalid Hyperlink type: " + this._type);
        }
    }

    @Internal
    public void setCellReference(String str) {
        this._ctHyperlink.setRef(str);
    }

    @Internal
    public void setCellReference(CellReference cellReference) {
        setCellReference(cellReference.formatAsString());
    }

    private CellReference buildFirstCellReference() {
        return buildCellReference(false);
    }

    private CellReference buildLastCellReference() {
        return buildCellReference(true);
    }

    private CellReference buildCellReference(boolean z) {
        String ref = this._ctHyperlink.getRef();
        if (ref == null) {
            ref = "A1";
        }
        if (!ref.contains(ParameterizedMessage.ERROR_MSG_SEPARATOR)) {
            return new CellReference(ref);
        }
        AreaReference areaReference = new AreaReference(ref, SpreadsheetVersion.EXCEL2007);
        return z ? areaReference.getLastCell() : areaReference.getFirstCell();
    }

    public int getFirstColumn() {
        return buildFirstCellReference().getCol();
    }

    public int getLastColumn() {
        return buildLastCellReference().getCol();
    }

    public int getFirstRow() {
        return buildFirstCellReference().getRow();
    }

    public int getLastRow() {
        return buildLastCellReference().getRow();
    }

    public void setFirstColumn(int i) {
        int lastColumn = getLastColumn();
        if (i > lastColumn) {
            lastColumn = i;
        }
        setCellRange((CellReference.convertNumToColString(i) + (getFirstRow() + 1)) + ParameterizedMessage.ERROR_MSG_SEPARATOR + (CellReference.convertNumToColString(lastColumn) + (getLastRow() + 1)));
    }

    public void setLastColumn(int i) {
        int firstColumn = getFirstColumn();
        if (i < firstColumn) {
            firstColumn = i;
        }
        setCellRange((CellReference.convertNumToColString(firstColumn) + (getFirstRow() + 1)) + ParameterizedMessage.ERROR_MSG_SEPARATOR + (CellReference.convertNumToColString(i) + (getLastRow() + 1)));
    }

    public void setFirstRow(int i) {
        int lastRow = getLastRow();
        if (i > lastRow) {
            lastRow = i;
        }
        setCellRange((CellReference.convertNumToColString(getFirstColumn()) + (i + 1)) + ParameterizedMessage.ERROR_MSG_SEPARATOR + (CellReference.convertNumToColString(getLastColumn()) + (lastRow + 1)));
    }

    public void setLastRow(int i) {
        int firstRow = getFirstRow();
        if (i < firstRow) {
            firstRow = i;
        }
        setCellRange((CellReference.convertNumToColString(getFirstColumn()) + (firstRow + 1)) + ParameterizedMessage.ERROR_MSG_SEPARATOR + (CellReference.convertNumToColString(getLastColumn()) + (i + 1)));
    }

    private void setCellRange(String str) {
        AreaReference areaReference = new AreaReference(str, SpreadsheetVersion.EXCEL2007);
        if (areaReference.isSingleCell()) {
            setCellReference(areaReference.getFirstCell());
        } else {
            setCellReference(areaReference.formatAsString());
        }
    }

    public String getTooltip() {
        return this._ctHyperlink.getTooltip();
    }

    public void setTooltip(String str) {
        this._ctHyperlink.setTooltip(str);
    }

    public Duplicatable copy() {
        return new XSSFHyperlink((Hyperlink) this);
    }
}
