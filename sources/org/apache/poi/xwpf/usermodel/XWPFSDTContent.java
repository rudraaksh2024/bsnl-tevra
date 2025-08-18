package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

public class XWPFSDTContent implements ISDTContent {
    private final List<ISDTContents> bodyElements = new ArrayList();

    public XWPFSDTContent(CTSdtContentRun cTSdtContentRun, IBody iBody, IRunBody iRunBody) {
        if (cTSdtContentRun != null) {
            XmlCursor newCursor = cTSdtContentRun.newCursor();
            newCursor.selectPath("./*");
            while (newCursor.toNextSelection()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTR) {
                    this.bodyElements.add(new XWPFRun((CTR) object, iRunBody));
                } else if (object instanceof CTSdtRun) {
                    this.bodyElements.add(new XWPFSDT((CTSdtRun) object, iBody));
                }
            }
            newCursor.dispose();
        }
    }

    public XWPFSDTContent(CTSdtContentBlock cTSdtContentBlock, IBody iBody, IRunBody iRunBody) {
        if (cTSdtContentBlock != null) {
            XmlCursor newCursor = cTSdtContentBlock.newCursor();
            newCursor.selectPath("./*");
            while (newCursor.toNextSelection()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTP) {
                    this.bodyElements.add(new XWPFParagraph((CTP) object, iBody));
                } else if (object instanceof CTTbl) {
                    this.bodyElements.add(new XWPFTable((CTTbl) object, iBody));
                } else if (object instanceof CTSdtBlock) {
                    this.bodyElements.add(new XWPFSDT((CTSdtBlock) object, iBody));
                } else if (object instanceof CTR) {
                    this.bodyElements.add(new XWPFRun((CTR) object, iRunBody));
                }
            }
            newCursor.dispose();
        }
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (int i = 0; i < this.bodyElements.size(); i++) {
            ISDTContents iSDTContents = this.bodyElements.get(i);
            if (iSDTContents instanceof XWPFParagraph) {
                appendParagraph((XWPFParagraph) iSDTContents, sb);
            } else if (iSDTContents instanceof XWPFTable) {
                appendTable((XWPFTable) iSDTContents, sb);
            } else if (iSDTContents instanceof XWPFSDT) {
                sb.append(((XWPFSDT) iSDTContents).getContent().getText());
            } else {
                if (iSDTContents instanceof XWPFRun) {
                    sb.append(iSDTContents);
                    z = false;
                }
                if (z && i < this.bodyElements.size() - 1) {
                    sb.append("\n");
                }
            }
            z = true;
            sb.append("\n");
        }
        return sb.toString();
    }

    private void appendTable(XWPFTable xWPFTable, StringBuilder sb) {
        for (XWPFTableRow tableICells : xWPFTable.getRows()) {
            List<ICell> tableICells2 = tableICells.getTableICells();
            for (int i = 0; i < tableICells2.size(); i++) {
                ICell iCell = tableICells2.get(i);
                if (iCell instanceof XWPFTableCell) {
                    sb.append(((XWPFTableCell) iCell).getTextRecursively());
                } else if (iCell instanceof XWPFSDTCell) {
                    sb.append(((XWPFSDTCell) iCell).getContent().getText());
                }
                if (i < tableICells2.size() - 1) {
                    sb.append("\t");
                }
            }
            sb.append(10);
        }
    }

    private void appendParagraph(XWPFParagraph xWPFParagraph, StringBuilder sb) {
        for (XWPFRun append : xWPFParagraph.getRuns()) {
            sb.append(append);
        }
    }

    public String toString() {
        return getText();
    }
}
