package org.apache.poi.ss.util;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.util.Internal;

@Internal
public final class SSCellRange<K extends Cell> implements CellRange<K> {
    private final int _firstColumn;
    private final int _firstRow;
    private final K[] _flattenedArray;
    private final int _height;
    private final int _width;

    private SSCellRange(int i, int i2, int i3, int i4, K[] kArr) {
        this._firstRow = i;
        this._firstColumn = i2;
        this._height = i3;
        this._width = i4;
        this._flattenedArray = (Cell[]) kArr.clone();
    }

    public static <B extends Cell> SSCellRange<B> create(int i, int i2, int i3, int i4, List<B> list, Class<B> cls) {
        int size = list.size();
        if (i3 * i4 == size) {
            Cell[] cellArr = (Cell[]) Array.newInstance(cls, size);
            list.toArray(cellArr);
            return new SSCellRange(i, i2, i3, i4, cellArr);
        }
        throw new IllegalArgumentException("Array size mismatch.");
    }

    public int getHeight() {
        return this._height;
    }

    public int getWidth() {
        return this._width;
    }

    public int size() {
        return this._height * this._width;
    }

    public String getReferenceText() {
        int i = this._firstRow;
        int i2 = this._firstColumn;
        return new CellRangeAddress(i, (this._height + i) - 1, i2, (this._width + i2) - 1).formatAsString();
    }

    public K getTopLeftCell() {
        return this._flattenedArray[0];
    }

    public K getCell(int i, int i2) {
        int i3;
        if (i < 0 || i >= this._height) {
            throw new ArrayIndexOutOfBoundsException("Specified row " + i + " is outside the allowable range (0.." + (this._height - 1) + ").");
        } else if (i2 < 0 || i2 >= (i3 = this._width)) {
            throw new ArrayIndexOutOfBoundsException("Specified colummn " + i2 + " is outside the allowable range (0.." + (this._width - 1) + ").");
        } else {
            return this._flattenedArray[(i3 * i) + i2];
        }
    }

    public K[] getFlattenedCells() {
        return (Cell[]) this._flattenedArray.clone();
    }

    public K[][] getCells() {
        Class<?> cls = this._flattenedArray.getClass();
        K[][] kArr = (Cell[][]) Array.newInstance(cls, this._height);
        Class<?> componentType = cls.getComponentType();
        for (int i = this._height - 1; i >= 0; i--) {
            int i2 = this._width;
            System.arraycopy(this._flattenedArray, i2 * i, (Cell[]) Array.newInstance(componentType, this._width), 0, i2);
        }
        return kArr;
    }

    public Iterator<K> iterator() {
        return Stream.of(this._flattenedArray).iterator();
    }

    public Spliterator<K> spliterator() {
        return Stream.of(this._flattenedArray).spliterator();
    }
}
