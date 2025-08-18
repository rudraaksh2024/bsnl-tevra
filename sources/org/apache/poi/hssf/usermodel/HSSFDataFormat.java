package org.apache.poi.hssf.usermodel;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormat;

public final class HSSFDataFormat implements DataFormat {
    private static final String[] _builtinFormats = BuiltinFormats.getAll();
    private final Vector<String> _formats = new Vector<>();
    private boolean _movedBuiltins;
    private final InternalWorkbook _workbook;

    HSSFDataFormat(InternalWorkbook internalWorkbook) {
        this._workbook = internalWorkbook;
        for (FormatRecord next : internalWorkbook.getFormats()) {
            ensureFormatsSize(next.getIndexCode());
            this._formats.set(next.getIndexCode(), next.getFormatString());
        }
    }

    public static List<String> getBuiltinFormats() {
        return Arrays.asList(_builtinFormats);
    }

    public static short getBuiltinFormat(String str) {
        return (short) BuiltinFormats.getBuiltinFormat(str);
    }

    public short getFormat(String str) {
        if (str.equalsIgnoreCase("TEXT")) {
            str = "@";
        }
        if (!this._movedBuiltins) {
            int i = 0;
            while (true) {
                String[] strArr = _builtinFormats;
                if (i >= strArr.length) {
                    break;
                }
                ensureFormatsSize(i);
                if (this._formats.get(i) == null) {
                    this._formats.set(i, strArr[i]);
                }
                i++;
            }
            this._movedBuiltins = true;
        }
        for (int i2 = 0; i2 < this._formats.size(); i2++) {
            if (str.equals(this._formats.get(i2))) {
                return (short) i2;
            }
        }
        short format = this._workbook.getFormat(str, true);
        ensureFormatsSize(format);
        this._formats.set(format, str);
        return format;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r2 = r2[r3];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getFormat(short r3) {
        /*
            r2 = this;
            boolean r0 = r2._movedBuiltins
            if (r0 == 0) goto L_0x000d
            java.util.Vector<java.lang.String> r2 = r2._formats
            java.lang.Object r2 = r2.get(r3)
            java.lang.String r2 = (java.lang.String) r2
            return r2
        L_0x000d:
            r0 = -1
            r1 = 0
            if (r3 != r0) goto L_0x0012
            return r1
        L_0x0012:
            java.util.Vector<java.lang.String> r0 = r2._formats
            int r0 = r0.size()
            if (r0 <= r3) goto L_0x0023
            java.util.Vector<java.lang.String> r2 = r2._formats
            java.lang.Object r2 = r2.get(r3)
            r1 = r2
            java.lang.String r1 = (java.lang.String) r1
        L_0x0023:
            java.lang.String[] r2 = _builtinFormats
            int r0 = r2.length
            if (r0 <= r3) goto L_0x0030
            r2 = r2[r3]
            if (r2 == 0) goto L_0x0030
            if (r1 == 0) goto L_0x002f
            return r1
        L_0x002f:
            return r2
        L_0x0030:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFDataFormat.getFormat(short):java.lang.String");
    }

    public static String getBuiltinFormat(short s) {
        return BuiltinFormats.getBuiltinFormat((int) s);
    }

    public static int getNumberOfBuiltinBuiltinFormats() {
        return _builtinFormats.length;
    }

    private void ensureFormatsSize(int i) {
        if (this._formats.size() <= i) {
            this._formats.setSize(i + 1);
        }
    }
}
