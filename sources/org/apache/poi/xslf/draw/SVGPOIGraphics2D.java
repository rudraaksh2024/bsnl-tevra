package org.apache.poi.xslf.draw;

import java.awt.RenderingHints;
import java.util.Map;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.poi.util.Internal;
import org.w3c.dom.Document;

@Internal
public class SVGPOIGraphics2D extends SVGGraphics2D {
    private final RenderingHints hints = getGeneratorContext().getGraphicContextDefaults().getRenderingHints();

    public SVGPOIGraphics2D(Document document, boolean z) {
        super(getCtx(document), z);
        getGeneratorContext().getExtensionHandler().setSvgGraphics2D(this);
    }

    private static SVGGeneratorContext getCtx(Document document) {
        SVGGeneratorContext createDefault = SVGGeneratorContext.createDefault(document);
        createDefault.setExtensionHandler(new SVGRenderExtension());
        SVGGeneratorContext.GraphicContextDefaults graphicContextDefaults = new SVGGeneratorContext.GraphicContextDefaults();
        graphicContextDefaults.setRenderingHints(new RenderingHints((Map) null));
        createDefault.setGraphicContextDefaults(graphicContextDefaults);
        return createDefault;
    }

    public void setRenderingHint(RenderingHints.Key key, Object obj) {
        this.hints.put(key, obj);
        SVGPOIGraphics2D.super.setRenderingHint(key, obj);
    }

    public void setRenderingHints(Map map) {
        this.hints.clear();
        this.hints.putAll(map);
        SVGPOIGraphics2D.super.setRenderingHints(map);
    }

    public void addRenderingHints(Map map) {
        this.hints.putAll(map);
        SVGPOIGraphics2D.super.addRenderingHints(map);
    }
}
