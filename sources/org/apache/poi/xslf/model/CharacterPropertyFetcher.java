package org.apache.poi.xslf.model;

import java.util.function.Consumer;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

@Internal
public final class CharacterPropertyFetcher<T> extends PropertyFetcher<T> {
    int _level;
    private final CharPropFetcher<T> fetcher;
    private final XSLFTextRun run;

    public interface CharPropFetcher<S> {
        void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer<S> consumer);
    }

    public CharacterPropertyFetcher(XSLFTextRun xSLFTextRun, CharPropFetcher<T> charPropFetcher) {
        this._level = xSLFTextRun.getParagraph().getIndentLevel();
        this.fetcher = charPropFetcher;
        this.run = xSLFTextRun;
    }

    public boolean fetch(XSLFShape xSLFShape) {
        try {
            fetchProp(ParagraphPropertyFetcher.select(xSLFShape, this._level));
        } catch (XmlException unused) {
        }
        return isSet();
    }

    public T fetchProperty(XSLFShape xSLFShape) {
        XSLFSheet sheet = xSLFShape.getSheet();
        fetchRunProp();
        if (!(sheet instanceof XSLFSlideMaster)) {
            fetchParagraphDefaultRunProp();
            fetchShapeProp(xSLFShape);
            fetchThemeProp(xSLFShape);
        }
        fetchMasterProp();
        if (isSet()) {
            return getValue();
        }
        return null;
    }

    private void fetchRunProp() {
        fetchProp(this.run.getRPr(false));
    }

    private void fetchParagraphDefaultRunProp() {
        CTTextParagraphProperties pPr;
        if (!isSet() && (pPr = this.run.getParagraph().getXmlObject().getPPr()) != null) {
            fetchProp(pPr.getDefRPr());
        }
    }

    private void fetchShapeProp(XSLFShape xSLFShape) {
        if (!isSet()) {
            xSLFShape.fetchShapeProperty(this);
        }
    }

    private void fetchThemeProp(XSLFShape xSLFShape) {
        if (!isSet()) {
            fetchProp(ParagraphPropertyFetcher.getThemeProps(xSLFShape, this._level));
        }
    }

    private void fetchMasterProp() {
        if (!isSet()) {
            fetchProp(this.run.getParagraph().getDefaultMasterStyle());
        }
    }

    private void fetchProp(CTTextParagraphProperties cTTextParagraphProperties) {
        if (cTTextParagraphProperties != null) {
            fetchProp(cTTextParagraphProperties.getDefRPr());
        }
    }

    private void fetchProp(CTTextCharacterProperties cTTextCharacterProperties) {
        if (cTTextCharacterProperties != null) {
            this.fetcher.fetch(cTTextCharacterProperties, new CharacterPropertyFetcher$$ExternalSyntheticLambda0(this));
        }
    }
}
