package org.apache.poi.xwpf.usermodel;

import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.poifs.crypt.dsig.SignatureLine;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XWPFSignatureLine$$ExternalSyntheticLambda0 implements SignatureLine.AddPictureData {
    public final /* synthetic */ XWPFParagraph f$0;

    public /* synthetic */ XWPFSignatureLine$$ExternalSyntheticLambda0(XWPFParagraph xWPFParagraph) {
        this.f$0 = xWPFParagraph;
    }

    public final String addPictureData(byte[] bArr, PictureType pictureType) {
        return this.f$0.getDocument().addPictureData(bArr, XWPFSignatureLine.mapType(pictureType));
    }
}
