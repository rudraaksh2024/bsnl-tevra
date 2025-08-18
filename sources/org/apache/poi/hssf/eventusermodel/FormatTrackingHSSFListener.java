package org.apache.poi.hssf.eventusermodel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.util.LocaleUtil;

public class FormatTrackingHSSFListener implements HSSFListener {
    private static final Logger LOG = LogManager.getLogger((Class<?>) FormatTrackingHSSFListener.class);
    private final HSSFListener _childListener;
    private final Map<Integer, FormatRecord> _customFormatRecords;
    private final NumberFormat _defaultFormat;
    private final HSSFDataFormatter _formatter;
    private final List<ExtendedFormatRecord> _xfRecords;

    public FormatTrackingHSSFListener(HSSFListener hSSFListener) {
        this(hSSFListener, LocaleUtil.getUserLocale());
    }

    public FormatTrackingHSSFListener(HSSFListener hSSFListener, Locale locale) {
        this._customFormatRecords = new HashMap();
        this._xfRecords = new ArrayList();
        this._childListener = hSSFListener;
        this._formatter = new HSSFDataFormatter(locale);
        this._defaultFormat = NumberFormat.getInstance(locale);
    }

    /* access modifiers changed from: protected */
    public int getNumberOfCustomFormats() {
        return this._customFormatRecords.size();
    }

    /* access modifiers changed from: protected */
    public int getNumberOfExtendedFormats() {
        return this._xfRecords.size();
    }

    public void processRecord(Record record) {
        processRecordInternally(record);
        this._childListener.processRecord(record);
    }

    public void processRecordInternally(Record record) {
        if (record instanceof FormatRecord) {
            FormatRecord formatRecord = (FormatRecord) record;
            this._customFormatRecords.put(Integer.valueOf(formatRecord.getIndexCode()), formatRecord);
        }
        if (record instanceof ExtendedFormatRecord) {
            this._xfRecords.add((ExtendedFormatRecord) record);
        }
    }

    public String formatNumberDateCell(CellValueRecordInterface cellValueRecordInterface) {
        double d;
        if (cellValueRecordInterface instanceof NumberRecord) {
            d = ((NumberRecord) cellValueRecordInterface).getValue();
        } else if (cellValueRecordInterface instanceof FormulaRecord) {
            d = ((FormulaRecord) cellValueRecordInterface).getValue();
        } else {
            throw new IllegalArgumentException("Unsupported CellValue Record passed in " + cellValueRecordInterface);
        }
        int formatIndex = getFormatIndex(cellValueRecordInterface);
        String formatString = getFormatString(cellValueRecordInterface);
        if (formatString == null) {
            return this._defaultFormat.format(d);
        }
        return this._formatter.formatRawCellContents(d, formatIndex, formatString);
    }

    public String getFormatString(int i) {
        if (i < HSSFDataFormat.getNumberOfBuiltinBuiltinFormats()) {
            return HSSFDataFormat.getBuiltinFormat((short) i);
        }
        FormatRecord formatRecord = this._customFormatRecords.get(Integer.valueOf(i));
        if (formatRecord != null) {
            return formatRecord.getFormatString();
        }
        LOG.atError().log("Requested format at index {}, but it wasn't found", (Object) Unbox.box(i));
        return null;
    }

    public String getFormatString(CellValueRecordInterface cellValueRecordInterface) {
        int formatIndex = getFormatIndex(cellValueRecordInterface);
        if (formatIndex == -1) {
            return null;
        }
        return getFormatString(formatIndex);
    }

    public int getFormatIndex(CellValueRecordInterface cellValueRecordInterface) {
        ExtendedFormatRecord extendedFormatRecord = this._xfRecords.get(cellValueRecordInterface.getXFIndex());
        if (extendedFormatRecord != null) {
            return extendedFormatRecord.getFormatIndex();
        }
        LOG.atError().log("Cell {},{} uses XF with index {}, but we don't have that", Unbox.box(cellValueRecordInterface.getRow()), Unbox.box(cellValueRecordInterface.getColumn()), Unbox.box(cellValueRecordInterface.getXFIndex()));
        return -1;
    }
}
