package org.apache.poi.xwpf.usermodel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell;

public class XWPFSDTCell extends XWPFAbstractSDT implements ICell {
    private final XWPFSDTContentCell cellContent;

    public XWPFSDTCell(CTSdtCell cTSdtCell, XWPFTableRow xWPFTableRow, IBody iBody) {
        super(cTSdtCell.getSdtPr(), iBody);
        this.cellContent = new XWPFSDTContentCell(cTSdtCell.getSdtContent(), xWPFTableRow, iBody);
    }

    public ISDTContent getContent() {
        return this.cellContent;
    }
}
