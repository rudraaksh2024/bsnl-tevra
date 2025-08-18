package org.apache.poi.sl.usermodel;

import java.awt.Dimension;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface SlideShow<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends Closeable {
    FontInfo addFont(InputStream inputStream) throws IOException;

    PictureData addPicture(File file, PictureData.PictureType pictureType) throws IOException;

    PictureData addPicture(InputStream inputStream, PictureData.PictureType pictureType) throws IOException;

    PictureData addPicture(byte[] bArr, PictureData.PictureType pictureType) throws IOException;

    MasterSheet<S, P> createMasterSheet() throws IOException;

    Slide<S, P> createSlide() throws IOException;

    PictureData findPictureData(byte[] bArr);

    List<? extends FontInfo> getFonts();

    POITextExtractor getMetadataTextExtractor();

    Dimension getPageSize();

    Object getPersistDocument();

    List<? extends PictureData> getPictureData();

    List<? extends MasterSheet<S, P>> getSlideMasters();

    List<? extends Slide<S, P>> getSlides();

    void setPageSize(Dimension dimension);

    void write(OutputStream outputStream) throws IOException;
}
