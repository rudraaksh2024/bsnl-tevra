package org.apache.poi.xssf.usermodel;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationName;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.SheetIdentifier;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPxg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.formula.udf.IndexedUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.model.ExternalLinksTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;

@Internal
public abstract class BaseXSSFEvaluationWorkbook implements FormulaRenderingWorkbook, EvaluationWorkbook, FormulaParsingWorkbook {
    private Map<String, XSSFTable> _tableCache;
    protected final XSSFWorkbook _uBook;

    private int convertFromExternalSheetIndex(int i) {
        return i;
    }

    private int convertToExternalSheetIndex(int i) {
        return i;
    }

    public int convertFromExternSheetIndex(int i) {
        return i;
    }

    protected BaseXSSFEvaluationWorkbook(XSSFWorkbook xSSFWorkbook) {
        this._uBook = xSSFWorkbook;
    }

    public void clearAllCachedResultValues() {
        this._tableCache = null;
    }

    public int getExternalSheetIndex(String str) {
        return convertToExternalSheetIndex(this._uBook.getSheetIndex(str));
    }

    private int resolveBookIndex(String str) {
        if (str.startsWith("[") && str.endsWith("]")) {
            str = str.substring(1, str.length() - 2);
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            List<ExternalLinksTable> externalLinksTable = this._uBook.getExternalLinksTable();
            int findExternalLinkIndex = findExternalLinkIndex(str, externalLinksTable);
            if (findExternalLinkIndex != -1) {
                return findExternalLinkIndex;
            }
            if (!str.startsWith("'file:///") || !str.endsWith("'")) {
                throw new RuntimeException("Book not linked for filename " + str);
            }
            String substring = str.substring(str.lastIndexOf(47) + 1);
            String substring2 = substring.substring(0, substring.length() - 1);
            int findExternalLinkIndex2 = findExternalLinkIndex(substring2, externalLinksTable);
            if (findExternalLinkIndex2 != -1) {
                return findExternalLinkIndex2;
            }
            externalLinksTable.add(new FakeExternalLinksTable(substring2));
            return externalLinksTable.size();
        }
    }

    private int findExternalLinkIndex(String str, List<ExternalLinksTable> list) {
        int i = 0;
        for (ExternalLinksTable linkedFileName : list) {
            if (linkedFileName.getLinkedFileName().equals(str)) {
                return i + 1;
            }
            i++;
        }
        return -1;
    }

    private static class FakeExternalLinksTable extends ExternalLinksTable {
        private final String fileName;

        private FakeExternalLinksTable(String str) {
            this.fileName = str;
        }

        public String getLinkedFileName() {
            return this.fileName;
        }
    }

    public EvaluationName getName(String str, int i) {
        for (int i2 = 0; i2 < this._uBook.getNumberOfNames(); i2++) {
            XSSFName nameAt = this._uBook.getNameAt(i2);
            String nameName = nameAt.getNameName();
            int sheetIndex = nameAt.getSheetIndex();
            if (str.equalsIgnoreCase(nameName) && (sheetIndex == -1 || sheetIndex == i)) {
                return new Name(nameAt, i2, this);
            }
        }
        if (i == -1) {
            return null;
        }
        return getName(str, -1);
    }

    public String getSheetName(int i) {
        return this._uBook.getSheetName(i);
    }

    public EvaluationWorkbook.ExternalName getExternalName(int i, int i2) {
        throw new IllegalStateException("HSSF-style external references are not supported for XSSF");
    }

    public EvaluationWorkbook.ExternalName getExternalName(String str, String str2, int i) {
        if (i <= 0) {
            return new EvaluationWorkbook.ExternalName(str, this._uBook.getNameIndex(str), 0);
        }
        ExternalLinksTable externalLinksTable = this._uBook.getExternalLinksTable().get(i - 1);
        for (org.apache.poi.ss.usermodel.Name next : externalLinksTable.getDefinedNames()) {
            if (next.getNameName().equals(str)) {
                return new EvaluationWorkbook.ExternalName(str, -1, next.getSheetIndex() + 1);
            }
        }
        throw new IllegalArgumentException("Name '" + str + "' not found in reference to " + externalLinksTable.getLinkedFileName());
    }

    public NameXPxg getNameXPtg(String str, SheetIdentifier sheetIdentifier) {
        if (((IndexedUDFFinder) getUDFFinder()).findFunction(str) != null) {
            return new NameXPxg((String) null, str);
        }
        if (sheetIdentifier == null) {
            if (!this._uBook.getNames(str).isEmpty()) {
                return new NameXPxg((String) null, str);
            }
            return null;
        } else if (sheetIdentifier.getSheetIdentifier() == null) {
            return new NameXPxg(resolveBookIndex(sheetIdentifier.getBookName()), (String) null, str);
        } else {
            String name = sheetIdentifier.getSheetIdentifier().getName();
            if (sheetIdentifier.getBookName() != null) {
                return new NameXPxg(resolveBookIndex(sheetIdentifier.getBookName()), name, str);
            }
            return new NameXPxg(name, str);
        }
    }

    public Ptg get3DReferencePtg(CellReference cellReference, SheetIdentifier sheetIdentifier) {
        if (sheetIdentifier.getBookName() != null) {
            return new Ref3DPxg(resolveBookIndex(sheetIdentifier.getBookName()), sheetIdentifier, cellReference);
        }
        return new Ref3DPxg(sheetIdentifier, cellReference);
    }

    public Ptg get3DReferencePtg(AreaReference areaReference, SheetIdentifier sheetIdentifier) {
        if (sheetIdentifier.getBookName() != null) {
            return new Area3DPxg(resolveBookIndex(sheetIdentifier.getBookName()), sheetIdentifier, areaReference);
        }
        return new Area3DPxg(sheetIdentifier, areaReference);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0011, code lost:
        r1 = r1._uBook.getNameAt(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String resolveNameXText(org.apache.poi.ss.formula.ptg.NameXPtg r2) {
        /*
            r1 = this;
            int r2 = r2.getNameIndex()
            org.apache.poi.ss.formula.udf.UDFFinder r0 = r1.getUDFFinder()
            org.apache.poi.ss.formula.udf.IndexedUDFFinder r0 = (org.apache.poi.ss.formula.udf.IndexedUDFFinder) r0
            java.lang.String r0 = r0.getFunctionName(r2)
            if (r0 == 0) goto L_0x0011
            return r0
        L_0x0011:
            org.apache.poi.xssf.usermodel.XSSFWorkbook r1 = r1._uBook
            org.apache.poi.xssf.usermodel.XSSFName r1 = r1.getNameAt(r2)
            if (r1 == 0) goto L_0x001d
            java.lang.String r0 = r1.getNameName()
        L_0x001d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.BaseXSSFEvaluationWorkbook.resolveNameXText(org.apache.poi.ss.formula.ptg.NameXPtg):java.lang.String");
    }

    public EvaluationWorkbook.ExternalSheet getExternalSheet(int i) {
        throw new IllegalStateException("HSSF-style external references are not supported for XSSF");
    }

    public EvaluationWorkbook.ExternalSheet getExternalSheet(String str, String str2, int i) {
        String str3;
        if (i > 0) {
            str3 = this._uBook.getExternalLinksTable().get(i - 1).getLinkedFileName();
        } else {
            str3 = null;
        }
        if (str2 == null || str.equals(str2)) {
            return new EvaluationWorkbook.ExternalSheet(str3, str);
        }
        return new EvaluationWorkbook.ExternalSheetRange(str3, str, str2);
    }

    @NotImplemented
    public int getExternalSheetIndex(String str, String str2) {
        throw new RuntimeException("not implemented yet");
    }

    public int getSheetIndex(String str) {
        return this._uBook.getSheetIndex(str);
    }

    public String getSheetFirstNameByExternSheet(int i) {
        return this._uBook.getSheetName(convertFromExternalSheetIndex(i));
    }

    public String getSheetLastNameByExternSheet(int i) {
        return getSheetFirstNameByExternSheet(i);
    }

    public String getNameText(NamePtg namePtg) {
        return this._uBook.getNameAt(namePtg.getIndex()).getNameName();
    }

    public EvaluationName getName(NamePtg namePtg) {
        int index = namePtg.getIndex();
        return new Name(this._uBook.getNameAt(index), index, this);
    }

    public XSSFName createName() {
        return this._uBook.createName();
    }

    private Map<String, XSSFTable> getTableCache() {
        Map<String, XSSFTable> map = this._tableCache;
        if (map != null) {
            return map;
        }
        this._tableCache = new ConcurrentSkipListMap(String.CASE_INSENSITIVE_ORDER);
        Iterator<Sheet> it = this._uBook.iterator();
        while (it.hasNext()) {
            for (XSSFTable next : ((XSSFSheet) it.next()).getTables()) {
                this._tableCache.put(next.getName(), next);
            }
        }
        return this._tableCache;
    }

    public XSSFTable getTable(String str) {
        if (str == null) {
            return null;
        }
        return getTableCache().get(str);
    }

    public UDFFinder getUDFFinder() {
        return this._uBook.getUDFFinder();
    }

    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    private static final class Name implements EvaluationName {
        private final FormulaParsingWorkbook _fpBook;
        private final int _index;
        private final XSSFName _nameRecord;

        public Name(XSSFName xSSFName, int i, FormulaParsingWorkbook formulaParsingWorkbook) {
            this._nameRecord = xSSFName;
            this._index = i;
            this._fpBook = formulaParsingWorkbook;
        }

        public Ptg[] getNameDefinition() {
            return FormulaParser.parse(this._nameRecord.getRefersToFormula(), this._fpBook, FormulaType.NAMEDRANGE, this._nameRecord.getSheetIndex());
        }

        public String getNameText() {
            return this._nameRecord.getNameName();
        }

        public boolean hasFormula() {
            CTDefinedName cTName = this._nameRecord.getCTName();
            String stringValue = cTName.getStringValue();
            return !cTName.getFunction() && stringValue != null && stringValue.length() > 0;
        }

        public boolean isFunctionName() {
            return this._nameRecord.isFunctionName();
        }

        public boolean isRange() {
            return hasFormula();
        }

        public NamePtg createPtg() {
            return new NamePtg(this._index);
        }
    }
}
