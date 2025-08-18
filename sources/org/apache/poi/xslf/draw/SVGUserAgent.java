package org.apache.poi.xslf.draw;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.bridge.ViewBox;
import org.apache.batik.parser.DefaultLengthHandler;
import org.apache.batik.parser.LengthParser;
import org.apache.batik.parser.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.Internal;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGSVGElement;

@Internal
public class SVGUserAgent extends UserAgentAdapter {
    private static final Logger LOG = LogManager.getLogger((Class<?>) SVGUserAgent.class);
    private Rectangle2D viewbox;

    public SVGUserAgent() {
        addStdFeatures();
    }

    public Dimension2D getViewportSize() {
        if (this.viewbox != null) {
            return new Dimension2DDouble(this.viewbox.getWidth(), this.viewbox.getHeight());
        }
        return SVGUserAgent.super.getViewportSize();
    }

    public Rectangle2D getViewbox() {
        Rectangle2D rectangle2D = this.viewbox;
        return rectangle2D != null ? rectangle2D : new Rectangle2D.Double(0.0d, 0.0d, 1.0d, 1.0d);
    }

    public void initViewbox(SVGDocument sVGDocument) {
        this.viewbox = null;
        SVGSVGElement rootElement = sVGDocument.getRootElement();
        if (rootElement != null) {
            String attributeNS = rootElement.getAttributeNS((String) null, "viewBox");
            if (attributeNS == null || attributeNS.isEmpty()) {
                float parseLength = parseLength(rootElement, "width");
                float parseLength2 = parseLength(rootElement, "height");
                if (parseLength != 0.0f && parseLength2 != 0.0f) {
                    this.viewbox = new Rectangle2D.Double(0.0d, 0.0d, (double) parseLength, (double) parseLength2);
                    return;
                }
                return;
            }
            float[] parseViewBoxAttribute = ViewBox.parseViewBoxAttribute(rootElement, attributeNS, (BridgeContext) null);
            this.viewbox = new Rectangle2D.Float(parseViewBoxAttribute[0], parseViewBoxAttribute[1], parseViewBoxAttribute[2], parseViewBoxAttribute[3]);
        }
    }

    private static float parseLength(SVGSVGElement sVGSVGElement, String str) {
        String attributeNS = sVGSVGElement.getAttributeNS((String) null, str);
        if (attributeNS == null || attributeNS.isEmpty()) {
            return 0.0f;
        }
        final float[] fArr = {0.0f};
        LengthParser lengthParser = new LengthParser();
        lengthParser.setLengthHandler(new DefaultLengthHandler() {
            public void lengthValue(float f) throws ParseException {
                fArr[0] = f;
            }
        });
        lengthParser.parse(attributeNS);
        return fArr[0];
    }

    public void displayMessage(String str) {
        LOG.atInfo().log(str);
    }

    public void displayError(String str) {
        LOG.atError().log(str);
    }

    public void displayError(Exception exc) {
        LOG.atError().withThrowable(exc).log(exc.getMessage());
    }

    public void showAlert(String str) {
        LOG.atWarn().log(str);
    }
}
