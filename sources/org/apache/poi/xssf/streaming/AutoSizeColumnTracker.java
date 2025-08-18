package org.apache.poi.xssf.streaming;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.util.Internal;

@Internal
class AutoSizeColumnTracker {
    private final DataFormatter dataFormatter = new DataFormatter();
    private final int defaultCharWidth;
    private final Map<Integer, ColumnWidthPair> maxColumnWidths = new HashMap();
    private boolean trackAllColumns;
    private final Set<Integer> untrackedColumns = new HashSet();

    private static class ColumnWidthPair {
        private double withSkipMergedCells;
        private double withUseMergedCells;

        public ColumnWidthPair() {
            this(-1.0d, -1.0d);
        }

        public ColumnWidthPair(double d, double d2) {
            this.withSkipMergedCells = d;
            this.withUseMergedCells = d2;
        }

        public double getMaxColumnWidth(boolean z) {
            return z ? this.withUseMergedCells : this.withSkipMergedCells;
        }

        public void setMaxColumnWidths(double d, double d2) {
            this.withUseMergedCells = Math.max(this.withUseMergedCells, d2);
            this.withSkipMergedCells = Math.max(this.withSkipMergedCells, d);
        }
    }

    public AutoSizeColumnTracker(Sheet sheet) {
        this.defaultCharWidth = SheetUtil.getDefaultCharWidth(sheet.getWorkbook());
    }

    public SortedSet<Integer> getTrackedColumns() {
        return Collections.unmodifiableSortedSet(new TreeSet(this.maxColumnWidths.keySet()));
    }

    public boolean isColumnTracked(int i) {
        return (this.trackAllColumns && !this.untrackedColumns.contains(Integer.valueOf(i))) || this.maxColumnWidths.containsKey(Integer.valueOf(i));
    }

    public boolean isAllColumnsTracked() {
        return this.trackAllColumns;
    }

    public void trackAllColumns() {
        this.trackAllColumns = true;
        this.untrackedColumns.clear();
    }

    public void untrackAllColumns() {
        this.trackAllColumns = false;
        this.maxColumnWidths.clear();
        this.untrackedColumns.clear();
    }

    public void trackColumns(Collection<Integer> collection) {
        for (Integer intValue : collection) {
            trackColumn(intValue.intValue());
        }
    }

    public boolean trackColumn(int i) {
        this.untrackedColumns.remove(Integer.valueOf(i));
        if (this.maxColumnWidths.containsKey(Integer.valueOf(i))) {
            return false;
        }
        this.maxColumnWidths.put(Integer.valueOf(i), new ColumnWidthPair());
        return true;
    }

    private boolean implicitlyTrackColumn(int i) {
        if (this.untrackedColumns.contains(Integer.valueOf(i))) {
            return false;
        }
        trackColumn(i);
        return true;
    }

    public boolean untrackColumns(Collection<Integer> collection) {
        this.untrackedColumns.addAll(collection);
        return this.maxColumnWidths.keySet().removeAll(collection);
    }

    public boolean untrackColumn(int i) {
        this.untrackedColumns.add(Integer.valueOf(i));
        return this.maxColumnWidths.keySet().remove(Integer.valueOf(i));
    }

    public int getBestFitColumnWidth(int i, boolean z) {
        if (!this.maxColumnWidths.containsKey(Integer.valueOf(i))) {
            if (!this.trackAllColumns) {
                throw new IllegalStateException("Cannot get best fit column width on untracked column " + i + ". Either explicitly track the column or track all columns.", new IllegalStateException("Column was never explicitly tracked and isAllColumnsTracked() is false (trackAllColumns() was never called or untrackAllColumns() was called after trackAllColumns() was called)."));
            } else if (!implicitlyTrackColumn(i)) {
                throw new IllegalStateException("Cannot get best fit column width on explicitly untracked column " + i + ". Either explicitly track the column or track all columns.", new IllegalStateException("Column was explicitly untracked after trackAllColumns() was called."));
            }
        }
        return Math.toIntExact(Math.round(this.maxColumnWidths.get(Integer.valueOf(i)).getMaxColumnWidth(z) * 256.0d));
    }

    public void updateColumnWidths(Row row) {
        implicitlyTrackColumnsInRow(row);
        if (this.maxColumnWidths.size() < row.getPhysicalNumberOfCells()) {
            for (Map.Entry next : this.maxColumnWidths.entrySet()) {
                Cell cell = row.getCell(((Integer) next.getKey()).intValue());
                if (cell != null) {
                    updateColumnWidth(cell, (ColumnWidthPair) next.getValue());
                }
            }
            return;
        }
        for (Cell next2 : row) {
            int columnIndex = next2.getColumnIndex();
            if (this.maxColumnWidths.containsKey(Integer.valueOf(columnIndex))) {
                updateColumnWidth(next2, this.maxColumnWidths.get(Integer.valueOf(columnIndex)));
            }
        }
    }

    private void implicitlyTrackColumnsInRow(Row row) {
        if (this.trackAllColumns) {
            Iterator<Cell> it = row.iterator();
            while (it.hasNext()) {
                implicitlyTrackColumn(it.next().getColumnIndex());
            }
        }
    }

    private void updateColumnWidth(Cell cell, ColumnWidthPair columnWidthPair) {
        columnWidthPair.setMaxColumnWidths(SheetUtil.getCellWidth(cell, this.defaultCharWidth, this.dataFormatter, false), SheetUtil.getCellWidth(cell, this.defaultCharWidth, this.dataFormatter, true));
    }
}
