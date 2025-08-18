package org.apache.poi.hssf.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.record.Record;

public final class WorkbookRecordList {
    private int backuppos;
    private int bspos;
    private int externsheetPos;
    private int fontpos;
    private int namepos;
    private int palettepos = -1;
    private int protpos;
    private List<Record> records = new ArrayList();
    private int supbookpos;
    private int tabpos = -1;
    private int xfpos;

    public void setRecords(List<Record> list) {
        this.records = list;
    }

    public int size() {
        return this.records.size();
    }

    public Record get(int i) {
        return this.records.get(i);
    }

    public void add(int i, Record record) {
        this.records.add(i, record);
        updateRecordPos(i, true);
    }

    public List<Record> getRecords() {
        return this.records;
    }

    public void remove(Object obj) {
        int i = 0;
        for (Record record : this.records) {
            if (record == obj) {
                remove(i);
                return;
            }
            i++;
        }
    }

    public void remove(int i) {
        this.records.remove(i);
        updateRecordPos(i, false);
    }

    public int getProtpos() {
        return this.protpos;
    }

    public void setProtpos(int i) {
        this.protpos = i;
    }

    public int getBspos() {
        return this.bspos;
    }

    public void setBspos(int i) {
        this.bspos = i;
    }

    public int getTabpos() {
        return this.tabpos;
    }

    public void setTabpos(int i) {
        this.tabpos = i;
    }

    public int getFontpos() {
        return this.fontpos;
    }

    public void setFontpos(int i) {
        this.fontpos = i;
    }

    public int getXfpos() {
        return this.xfpos;
    }

    public void setXfpos(int i) {
        this.xfpos = i;
    }

    public int getBackuppos() {
        return this.backuppos;
    }

    public void setBackuppos(int i) {
        this.backuppos = i;
    }

    public int getPalettepos() {
        return this.palettepos;
    }

    public void setPalettepos(int i) {
        this.palettepos = i;
    }

    public int getNamepos() {
        return this.namepos;
    }

    public int getSupbookpos() {
        return this.supbookpos;
    }

    public void setNamepos(int i) {
        this.namepos = i;
    }

    public void setSupbookpos(int i) {
        this.supbookpos = i;
    }

    public int getExternsheetPos() {
        return this.externsheetPos;
    }

    public void setExternsheetPos(int i) {
        this.externsheetPos = i;
    }

    private void updateRecordPos(int i, boolean z) {
        int i2 = z ? 1 : -1;
        int protpos2 = getProtpos();
        if (protpos2 >= i) {
            setProtpos(protpos2 + i2);
        }
        int bspos2 = getBspos();
        if (bspos2 >= i) {
            setBspos(bspos2 + i2);
        }
        int tabpos2 = getTabpos();
        if (tabpos2 >= i) {
            setTabpos(tabpos2 + i2);
        }
        int fontpos2 = getFontpos();
        if (fontpos2 >= i) {
            setFontpos(fontpos2 + i2);
        }
        int xfpos2 = getXfpos();
        if (xfpos2 >= i) {
            setXfpos(xfpos2 + i2);
        }
        int backuppos2 = getBackuppos();
        if (backuppos2 >= i) {
            setBackuppos(backuppos2 + i2);
        }
        int namepos2 = getNamepos();
        if (namepos2 >= i) {
            setNamepos(namepos2 + i2);
        }
        int supbookpos2 = getSupbookpos();
        if (supbookpos2 >= i) {
            setSupbookpos(supbookpos2 + i2);
        }
        int palettepos2 = getPalettepos();
        if (palettepos2 != -1 && palettepos2 >= i) {
            setPalettepos(palettepos2 + i2);
        }
        int externsheetPos2 = getExternsheetPos();
        if (externsheetPos2 >= i) {
            setExternsheetPos(externsheetPos2 + i2);
        }
    }
}
