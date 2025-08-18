package org.apache.poi.xssf.streaming;

import java.util.Iterator;
import java.util.Spliterator;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.ObjectData;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFShape;

public class SXSSFDrawing implements Drawing<XSSFShape> {
    private final XSSFDrawing _drawing;
    private final SXSSFWorkbook _wb;

    public SXSSFDrawing(SXSSFWorkbook sXSSFWorkbook, XSSFDrawing xSSFDrawing) {
        this._wb = sXSSFWorkbook;
        this._drawing = xSSFDrawing;
    }

    public SXSSFPicture createPicture(ClientAnchor clientAnchor, int i) {
        return new SXSSFPicture(this._wb, this._drawing.createPicture(clientAnchor, i));
    }

    public Comment createCellComment(ClientAnchor clientAnchor) {
        return this._drawing.createCellComment(clientAnchor);
    }

    public ClientAnchor createAnchor(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return this._drawing.createAnchor(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public ObjectData createObjectData(ClientAnchor clientAnchor, int i, int i2) {
        return this._drawing.createObjectData(clientAnchor, i, i2);
    }

    public Iterator<XSSFShape> iterator() {
        return this._drawing.getShapes().iterator();
    }

    public Spliterator<XSSFShape> spliterator() {
        return this._drawing.getShapes().spliterator();
    }
}
