package org.apache.poi.hssf.extractor;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.POIDocument;
import org.apache.poi.extractor.POIOLE2TextExtractor;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.StringRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.extractor.ExcelExtractor;

public class EventBasedExcelExtractor implements POIOLE2TextExtractor, ExcelExtractor {
    private final DirectoryNode _dir;
    boolean _formulasNotResults;
    boolean _includeSheetNames;
    private boolean doCloseFilesystem;
    private final POIFSFileSystem poifs;

    public POIDocument getDocument() {
        return null;
    }

    public EventBasedExcelExtractor(DirectoryNode directoryNode) {
        this.doCloseFilesystem = true;
        this._includeSheetNames = true;
        this.poifs = null;
        this._dir = directoryNode;
    }

    public EventBasedExcelExtractor(POIFSFileSystem pOIFSFileSystem) {
        this.doCloseFilesystem = true;
        this._includeSheetNames = true;
        this.poifs = pOIFSFileSystem;
        this._dir = pOIFSFileSystem.getRoot();
    }

    public DocumentSummaryInformation getDocSummaryInformation() {
        throw new IllegalStateException("Metadata extraction not supported in streaming mode, please use ExcelExtractor");
    }

    public SummaryInformation getSummaryInformation() {
        throw new IllegalStateException("Metadata extraction not supported in streaming mode, please use ExcelExtractor");
    }

    public void setIncludeCellComments(boolean z) {
        throw new IllegalStateException("Comment extraction not supported in streaming mode, please use ExcelExtractor");
    }

    public void setIncludeHeadersFooters(boolean z) {
        throw new IllegalStateException("Header/Footer extraction not supported in streaming mode, please use ExcelExtractor");
    }

    public void setIncludeSheetNames(boolean z) {
        this._includeSheetNames = z;
    }

    public void setFormulasNotResults(boolean z) {
        this._formulasNotResults = z;
    }

    public String getText() {
        try {
            String sb = triggerExtraction()._text.toString();
            return !sb.endsWith("\n") ? sb + "\n" : sb;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private TextListener triggerExtraction() throws IOException {
        TextListener textListener = new TextListener();
        FormatTrackingHSSFListener formatTrackingHSSFListener = new FormatTrackingHSSFListener(textListener);
        textListener._ft = formatTrackingHSSFListener;
        HSSFEventFactory hSSFEventFactory = new HSSFEventFactory();
        HSSFRequest hSSFRequest = new HSSFRequest();
        hSSFRequest.addListenerForAllRecords(formatTrackingHSSFListener);
        hSSFEventFactory.processWorkbookEvents(hSSFRequest, this._dir);
        return textListener;
    }

    private class TextListener implements HSSFListener {
        FormatTrackingHSSFListener _ft;
        final StringBuilder _text = new StringBuilder();
        private int nextRow = -1;
        private boolean outputNextStringValue;
        private int rowNum;
        private final List<String> sheetNames = new ArrayList();
        private int sheetNum = -1;
        private SSTRecord sstRecord;

        public TextListener() {
        }

        public void processRecord(Record record) {
            int i;
            short sid = record.getSid();
            String str = null;
            if (sid == 6) {
                FormulaRecord formulaRecord = (FormulaRecord) record;
                i = formulaRecord.getRow();
                if (EventBasedExcelExtractor.this._formulasNotResults) {
                    str = HSSFFormulaParser.toFormulaString((HSSFWorkbook) null, formulaRecord.getParsedExpression());
                } else if (formulaRecord.hasCachedResultString()) {
                    this.outputNextStringValue = true;
                    this.nextRow = formulaRecord.getRow();
                } else {
                    str = this._ft.formatNumberDateCell(formulaRecord);
                }
            } else if (sid != 28) {
                i = -1;
                if (sid == 133) {
                    this.sheetNames.add(((BoundSheetRecord) record).getSheetname());
                } else if (sid != 519) {
                    if (sid != 2057) {
                        if (sid == 252) {
                            this.sstRecord = (SSTRecord) record;
                        } else if (sid == 253) {
                            LabelSSTRecord labelSSTRecord = (LabelSSTRecord) record;
                            i = labelSSTRecord.getRow();
                            SSTRecord sSTRecord = this.sstRecord;
                            if (sSTRecord != null) {
                                str = sSTRecord.getString(labelSSTRecord.getSSTIndex()).toString();
                            } else {
                                throw new IllegalStateException("No SST record found");
                            }
                        } else if (sid == 515) {
                            NumberRecord numberRecord = (NumberRecord) record;
                            i = numberRecord.getRow();
                            str = this._ft.formatNumberDateCell(numberRecord);
                        } else if (sid == 516) {
                            LabelRecord labelRecord = (LabelRecord) record;
                            i = labelRecord.getRow();
                            str = labelRecord.getValue();
                        }
                    } else if (((BOFRecord) record).getType() == 16) {
                        this.sheetNum++;
                        this.rowNum = -1;
                        if (EventBasedExcelExtractor.this._includeSheetNames) {
                            if (this._text.length() > 0) {
                                this._text.append("\n");
                            }
                            this._text.append(this.sheetNames.get(this.sheetNum));
                        }
                    }
                } else if (this.outputNextStringValue) {
                    str = ((StringRecord) record).getString();
                    i = this.nextRow;
                    this.outputNextStringValue = false;
                }
            } else {
                i = ((NoteRecord) record).getRow();
            }
            if (str != null) {
                if (i != this.rowNum) {
                    this.rowNum = i;
                    if (this._text.length() > 0) {
                        this._text.append("\n");
                    }
                } else {
                    this._text.append("\t");
                }
                this._text.append(str);
            }
        }
    }

    public void setCloseFilesystem(boolean z) {
        this.doCloseFilesystem = z;
    }

    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    public Closeable getFilesystem() {
        return this.poifs;
    }

    public DirectoryEntry getRoot() {
        return this._dir;
    }

    public void close() throws IOException {
        super.close();
        DirectoryEntry root = getRoot();
        if (root instanceof DirectoryNode) {
            POIFSFileSystem fileSystem = ((DirectoryNode) root).getFileSystem();
            if (isCloseFilesystem() && fileSystem != null) {
                fileSystem.close();
            }
        }
    }
}
