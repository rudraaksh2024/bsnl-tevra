package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.hssf.record.aggregates.FormulaRecordAggregate;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationName;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.SheetIdentifier;
import org.apache.poi.ss.formula.SheetRangeIdentifier;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;

@Internal
public final class HSSFEvaluationWorkbook implements FormulaRenderingWorkbook, EvaluationWorkbook, FormulaParsingWorkbook {
    private final InternalWorkbook _iBook;
    private final HSSFWorkbook _uBook;

    public void clearAllCachedResultValues() {
    }

    public static HSSFEvaluationWorkbook create(HSSFWorkbook hSSFWorkbook) {
        if (hSSFWorkbook == null) {
            return null;
        }
        return new HSSFEvaluationWorkbook(hSSFWorkbook);
    }

    private HSSFEvaluationWorkbook(HSSFWorkbook hSSFWorkbook) {
        this._uBook = hSSFWorkbook;
        this._iBook = hSSFWorkbook.getWorkbook();
    }

    public HSSFName createName() {
        return this._uBook.createName();
    }

    public int getExternalSheetIndex(String str) {
        return this._iBook.checkExternSheet(this._uBook.getSheetIndex(str));
    }

    public int getExternalSheetIndex(String str, String str2) {
        return this._iBook.getExternalSheetIndex(str, str2);
    }

    public Ptg get3DReferencePtg(CellReference cellReference, SheetIdentifier sheetIdentifier) {
        return new Ref3DPtg(cellReference, getSheetExtIx(sheetIdentifier));
    }

    public Ptg get3DReferencePtg(AreaReference areaReference, SheetIdentifier sheetIdentifier) {
        return new Area3DPtg(areaReference, getSheetExtIx(sheetIdentifier));
    }

    public NameXPtg getNameXPtg(String str, SheetIdentifier sheetIdentifier) {
        return this._iBook.getNameXPtg(str, getSheetExtIx(sheetIdentifier), this._uBook.getUDFFinder());
    }

    public EvaluationName getName(String str, int i) {
        for (int i2 = 0; i2 < this._iBook.getNumNames(); i2++) {
            NameRecord nameRecord = this._iBook.getNameRecord(i2);
            if (nameRecord.getSheetNumber() == i + 1 && str.equalsIgnoreCase(nameRecord.getNameText())) {
                return new Name(nameRecord, i2);
            }
        }
        if (i == -1) {
            return null;
        }
        return getName(str, -1);
    }

    public int getSheetIndex(EvaluationSheet evaluationSheet) {
        return this._uBook.getSheetIndex((Sheet) ((HSSFEvaluationSheet) evaluationSheet).getHSSFSheet());
    }

    public int getSheetIndex(String str) {
        return this._uBook.getSheetIndex(str);
    }

    public String getSheetName(int i) {
        return this._uBook.getSheetName(i);
    }

    public EvaluationSheet getSheet(int i) {
        return new HSSFEvaluationSheet(this._uBook.getSheetAt(i));
    }

    public int convertFromExternSheetIndex(int i) {
        return this._iBook.getFirstSheetIndexFromExternSheetIndex(i);
    }

    public EvaluationWorkbook.ExternalSheet getExternalSheet(int i) {
        EvaluationWorkbook.ExternalSheet externalSheet = this._iBook.getExternalSheet(i);
        if (externalSheet != null) {
            return externalSheet;
        }
        int convertFromExternSheetIndex = convertFromExternSheetIndex(i);
        if (convertFromExternSheetIndex == -1 || convertFromExternSheetIndex == -2) {
            return null;
        }
        String sheetName = getSheetName(convertFromExternSheetIndex);
        int lastSheetIndexFromExternSheetIndex = this._iBook.getLastSheetIndexFromExternSheetIndex(i);
        if (lastSheetIndexFromExternSheetIndex == convertFromExternSheetIndex) {
            return new EvaluationWorkbook.ExternalSheet((String) null, sheetName);
        }
        return new EvaluationWorkbook.ExternalSheetRange((String) null, sheetName, getSheetName(lastSheetIndexFromExternSheetIndex));
    }

    public EvaluationWorkbook.ExternalSheet getExternalSheet(String str, String str2, int i) {
        throw new IllegalStateException("XSSF-style external references are not supported for HSSF");
    }

    public EvaluationWorkbook.ExternalName getExternalName(int i, int i2) {
        return this._iBook.getExternalName(i, i2);
    }

    public EvaluationWorkbook.ExternalName getExternalName(String str, String str2, int i) {
        throw new IllegalStateException("XSSF-style external names are not supported for HSSF");
    }

    public String resolveNameXText(NameXPtg nameXPtg) {
        return this._iBook.resolveNameXText(nameXPtg.getSheetRefIndex(), nameXPtg.getNameIndex());
    }

    public String getSheetFirstNameByExternSheet(int i) {
        return this._iBook.findSheetFirstNameFromExternSheet(i);
    }

    public String getSheetLastNameByExternSheet(int i) {
        return this._iBook.findSheetLastNameFromExternSheet(i);
    }

    public String getNameText(NamePtg namePtg) {
        return this._iBook.getNameRecord(namePtg.getIndex()).getNameText();
    }

    public EvaluationName getName(NamePtg namePtg) {
        int index = namePtg.getIndex();
        return new Name(this._iBook.getNameRecord(index), index);
    }

    public Ptg[] getFormulaTokens(EvaluationCell evaluationCell) {
        return ((FormulaRecordAggregate) ((HSSFEvaluationCell) evaluationCell).getHSSFCell().getCellValueRecord()).getFormulaTokens();
    }

    public UDFFinder getUDFFinder() {
        return this._uBook.getUDFFinder();
    }

    private static final class Name implements EvaluationName {
        private final int _index;
        private final NameRecord _nameRecord;

        public Name(NameRecord nameRecord, int i) {
            this._nameRecord = nameRecord;
            this._index = i;
        }

        public Ptg[] getNameDefinition() {
            return this._nameRecord.getNameDefinition();
        }

        public String getNameText() {
            return this._nameRecord.getNameText();
        }

        public boolean hasFormula() {
            return this._nameRecord.hasFormula();
        }

        public boolean isFunctionName() {
            return this._nameRecord.isFunctionName();
        }

        public boolean isRange() {
            return this._nameRecord.hasFormula();
        }

        public NamePtg createPtg() {
            return new NamePtg(this._index);
        }
    }

    private int getSheetExtIx(SheetIdentifier sheetIdentifier) {
        if (sheetIdentifier == null) {
            return -1;
        }
        String bookName = sheetIdentifier.getBookName();
        String name = sheetIdentifier.getSheetIdentifier().getName();
        String name2 = sheetIdentifier instanceof SheetRangeIdentifier ? ((SheetRangeIdentifier) sheetIdentifier).getLastSheetIdentifier().getName() : name;
        if (bookName != null) {
            return this._iBook.getExternalSheetIndex(bookName, name, name2);
        }
        return this._iBook.checkExternSheet(this._uBook.getSheetIndex(name), this._uBook.getSheetIndex(name2));
    }

    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL97;
    }

    public Table getTable(String str) {
        throw new IllegalStateException("XSSF-style tables are not supported for HSSF");
    }
}
