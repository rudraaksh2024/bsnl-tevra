package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;

public class FootnoteEndnoteIdManager {
    private XWPFDocument document;

    public FootnoteEndnoteIdManager(XWPFDocument xWPFDocument) {
        this.document = xWPFDocument;
    }

    public BigInteger nextId() {
        ArrayList arrayList = new ArrayList();
        for (XWPFFootnote id : this.document.getFootnotes()) {
            arrayList.add(id.getId());
        }
        for (XWPFEndnote id2 : this.document.getEndnotes()) {
            arrayList.add(id2.getId());
        }
        int size = arrayList.size();
        BigInteger valueOf = BigInteger.valueOf((long) size);
        while (arrayList.contains(valueOf)) {
            size++;
            valueOf = BigInteger.valueOf((long) size);
        }
        return valueOf;
    }
}
