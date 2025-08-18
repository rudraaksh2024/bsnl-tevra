package org.apache.poi.xssf.usermodel;

import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.poifs.crypt.dsig.SignatureLine;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSSFSignatureLine$$ExternalSyntheticLambda0 implements SignatureLine.AddPictureData {
    public final /* synthetic */ XSSFSignatureLine f$0;
    public final /* synthetic */ XSSFSheet f$1;

    public /* synthetic */ XSSFSignatureLine$$ExternalSyntheticLambda0(XSSFSignatureLine xSSFSignatureLine, XSSFSheet xSSFSheet) {
        this.f$0 = xSSFSignatureLine;
        this.f$1 = xSSFSheet;
    }

    public final String addPictureData(byte[] bArr, PictureType pictureType) {
        return this.f$0.m2312lambda$add$0$orgapachepoixssfusermodelXSSFSignatureLine(this.f$1, bArr, pictureType);
    }
}
