package org.apache.poi.hssf.usermodel;

import java.util.HashSet;
import java.util.Iterator;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.record.StyleRecord;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class HSSFOptimiser {
    public static void optimiseFonts(HSSFWorkbook hSSFWorkbook) {
        int numberOfFontRecords = hSSFWorkbook.getWorkbook().getNumberOfFontRecords() + 1;
        short[] sArr = new short[numberOfFontRecords];
        boolean[] zArr = new boolean[numberOfFontRecords];
        for (int i = 0; i < numberOfFontRecords; i++) {
            sArr[i] = (short) i;
            zArr[i] = false;
        }
        FontRecord[] fontRecordArr = new FontRecord[numberOfFontRecords];
        for (int i2 = 0; i2 < numberOfFontRecords; i2++) {
            if (i2 != 4) {
                fontRecordArr[i2] = hSSFWorkbook.getWorkbook().getFontRecordAt(i2);
            }
        }
        for (int i3 = 5; i3 < numberOfFontRecords; i3++) {
            int i4 = -1;
            for (int i5 = 0; i5 < i3 && i4 == -1; i5++) {
                if (i5 != 4 && hSSFWorkbook.getWorkbook().getFontRecordAt(i5).sameProperties(fontRecordArr[i3])) {
                    i4 = i5;
                }
            }
            if (i4 != -1) {
                sArr[i3] = (short) i4;
                zArr[i3] = true;
            }
        }
        for (int i6 = 5; i6 < numberOfFontRecords; i6++) {
            short s = sArr[i6];
            short s2 = s;
            for (int i7 = 0; i7 < s; i7++) {
                if (zArr[i7]) {
                    s2 = (short) (s2 - 1);
                }
            }
            sArr[i6] = s2;
        }
        for (int i8 = 5; i8 < numberOfFontRecords; i8++) {
            if (zArr[i8]) {
                hSSFWorkbook.getWorkbook().removeFontRecord(fontRecordArr[i8]);
            }
        }
        hSSFWorkbook.resetFontCache();
        for (int i9 = 0; i9 < hSSFWorkbook.getWorkbook().getNumExFormats(); i9++) {
            ExtendedFormatRecord exFormatAt = hSSFWorkbook.getWorkbook().getExFormatAt(i9);
            exFormatAt.setFontIndex(sArr[exFormatAt.getFontIndex()]);
        }
        HashSet hashSet = new HashSet();
        for (int i10 = 0; i10 < hSSFWorkbook.getNumberOfSheets(); i10++) {
            Iterator<Row> it = hSSFWorkbook.getSheetAt(i10).iterator();
            while (it.hasNext()) {
                for (Cell next : it.next()) {
                    if (next.getCellType() == CellType.STRING) {
                        UnicodeString rawUnicodeString = ((HSSFRichTextString) next.getRichStringCellValue()).getRawUnicodeString();
                        if (!hashSet.contains(rawUnicodeString)) {
                            for (short s3 = 5; s3 < numberOfFontRecords; s3 = (short) (s3 + 1)) {
                                short s4 = sArr[s3];
                                if (s3 != s4) {
                                    rawUnicodeString.swapFontUse(s3, s4);
                                }
                            }
                            hashSet.add(rawUnicodeString);
                        }
                    }
                }
            }
        }
    }

    public static void optimiseCellStyles(HSSFWorkbook hSSFWorkbook) {
        int numExFormats = hSSFWorkbook.getWorkbook().getNumExFormats();
        short[] sArr = new short[numExFormats];
        boolean[] zArr = new boolean[numExFormats];
        boolean[] zArr2 = new boolean[numExFormats];
        boolean[] zArr3 = new boolean[numExFormats];
        ExtendedFormatRecord[] extendedFormatRecordArr = new ExtendedFormatRecord[numExFormats];
        for (int i = 0; i < numExFormats; i++) {
            zArr[i] = false;
            sArr[i] = (short) i;
            zArr2[i] = false;
            zArr3[i] = isUserDefined(hSSFWorkbook, i);
            extendedFormatRecordArr[i] = hSSFWorkbook.getWorkbook().getExFormatAt(i);
        }
        int i2 = 21;
        for (int i3 = 21; i3 < numExFormats; i3++) {
            int i4 = 0;
            while (true) {
                if (i4 < i3) {
                    if (hSSFWorkbook.getWorkbook().getExFormatAt(i4).equals(extendedFormatRecordArr[i3]) && !zArr3[i4]) {
                        break;
                    }
                    i4++;
                } else {
                    i4 = -1;
                    break;
                }
            }
            if (i4 != -1) {
                sArr[i3] = (short) i4;
                zArr2[i3] = true;
            }
        }
        for (int i5 = 0; i5 < hSSFWorkbook.getNumberOfSheets(); i5++) {
            HSSFSheet sheetAt = hSSFWorkbook.getSheetAt(i5);
            Iterator<Row> it = sheetAt.iterator();
            while (it.hasNext()) {
                Row<HSSFCell> next = it.next();
                for (HSSFCell cellValueRecord : next) {
                    short xFIndex = cellValueRecord.getCellValueRecord().getXFIndex();
                    if (xFIndex < numExFormats) {
                        zArr[xFIndex] = true;
                    }
                }
                short xFIndex2 = ((HSSFRow) next).getRowRecord().getXFIndex();
                if (xFIndex2 < numExFormats) {
                    zArr[xFIndex2] = true;
                }
            }
            for (int minColumnIndex = sheetAt.getSheet().getMinColumnIndex(); minColumnIndex <= sheetAt.getSheet().getMaxColumnIndex(); minColumnIndex++) {
                short xFIndexForColAt = sheetAt.getSheet().getXFIndexForColAt((short) minColumnIndex);
                if (xFIndexForColAt < numExFormats) {
                    zArr[xFIndexForColAt] = true;
                }
            }
        }
        for (int i6 = 21; i6 < numExFormats; i6++) {
            if (isUserDefined(hSSFWorkbook, i6)) {
                zArr[i6] = true;
            }
            short s = sArr[i6];
            if (s != i6 && zArr[i6]) {
                zArr[s] = true;
            }
        }
        for (int i7 = 21; i7 < numExFormats; i7++) {
            if (!zArr[i7]) {
                zArr2[i7] = true;
                sArr[i7] = 0;
            }
        }
        for (int i8 = 21; i8 < numExFormats; i8++) {
            short s2 = sArr[i8];
            short s3 = s2;
            for (int i9 = 0; i9 < s2; i9++) {
                if (zArr2[i9]) {
                    s3 = (short) (s3 - 1);
                }
            }
            sArr[i8] = s3;
            if (!(i8 == s3 || s3 == 0)) {
                hSSFWorkbook.getWorkbook().updateStyleRecord(i8, s3);
                ExtendedFormatRecord exFormatAt = hSSFWorkbook.getWorkbook().getExFormatAt(i8);
                short parentIndex = exFormatAt.getParentIndex();
                if (parentIndex < numExFormats) {
                    exFormatAt.setParentIndex(sArr[parentIndex]);
                }
            }
        }
        int i10 = numExFormats;
        int i11 = 0;
        while (i2 < i10) {
            if (zArr2[i2 + i11]) {
                hSSFWorkbook.getWorkbook().removeExFormatRecord(i2);
                i2--;
                i10--;
                i11++;
            }
            i2++;
        }
        for (int i12 = 0; i12 < hSSFWorkbook.getNumberOfSheets(); i12++) {
            HSSFSheet sheetAt2 = hSSFWorkbook.getSheetAt(i12);
            Iterator<Row> it2 = sheetAt2.iterator();
            while (it2.hasNext()) {
                Row next2 = it2.next();
                for (Cell next3 : next2) {
                    short xFIndex3 = ((HSSFCell) next3).getCellValueRecord().getXFIndex();
                    if (xFIndex3 < numExFormats) {
                        next3.setCellStyle(hSSFWorkbook.getCellStyleAt((int) sArr[xFIndex3]));
                    }
                }
                short xFIndex4 = ((HSSFRow) next2).getRowRecord().getXFIndex();
                if (xFIndex4 < numExFormats) {
                    next2.setRowStyle(hSSFWorkbook.getCellStyleAt((int) sArr[xFIndex4]));
                }
            }
            for (int minColumnIndex2 = sheetAt2.getSheet().getMinColumnIndex(); minColumnIndex2 <= sheetAt2.getSheet().getMaxColumnIndex(); minColumnIndex2++) {
                short xFIndexForColAt2 = sheetAt2.getSheet().getXFIndexForColAt((short) minColumnIndex2);
                if (xFIndexForColAt2 < numExFormats) {
                    sheetAt2.setDefaultColumnStyle(minColumnIndex2, hSSFWorkbook.getCellStyleAt((int) sArr[xFIndexForColAt2]));
                }
            }
        }
    }

    private static boolean isUserDefined(HSSFWorkbook hSSFWorkbook, int i) {
        StyleRecord styleRecord = hSSFWorkbook.getWorkbook().getStyleRecord(i);
        return (styleRecord == null || styleRecord.isBuiltin() || styleRecord.getName() == null) ? false : true;
    }
}
