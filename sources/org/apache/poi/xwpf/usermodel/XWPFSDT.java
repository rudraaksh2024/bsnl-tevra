package org.apache.poi.xwpf.usermodel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;

public class XWPFSDT extends XWPFAbstractSDT implements IBodyElement, IRunBody, ISDTContents, IRunElement {
    private final ISDTContent content;

    public XWPFSDT(CTSdtRun cTSdtRun, IBody iBody) {
        super(cTSdtRun.getSdtPr(), iBody);
        this.content = new XWPFSDTContent(cTSdtRun.getSdtContent(), iBody, (IRunBody) this);
    }

    public XWPFSDT(CTSdtBlock cTSdtBlock, IBody iBody) {
        super(cTSdtBlock.getSdtPr(), iBody);
        this.content = new XWPFSDTContent(cTSdtBlock.getSdtContent(), iBody, (IRunBody) this);
    }

    public ISDTContent getContent() {
        return this.content;
    }
}
