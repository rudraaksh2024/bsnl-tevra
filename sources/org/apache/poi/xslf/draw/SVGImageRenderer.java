package org.apache.poi.xslf.draw;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.ext.awt.RenderingHintsKeyExt;
import org.apache.batik.ext.awt.image.renderable.ClipRable;
import org.apache.batik.ext.awt.image.renderable.ClipRable8Bit;
import org.apache.batik.ext.awt.image.renderable.Filter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.draw.ImageRenderer;
import org.apache.poi.sl.usermodel.PictureData;
import org.w3c.dom.svg.SVGDocument;

public class SVGImageRenderer implements ImageRenderer {
    private double alpha = 1.0d;
    private final GVTBuilder builder = new GVTBuilder();
    private final BridgeContext context;
    private final SAXSVGDocumentFactory svgFact = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());

    public SVGImageRenderer() {
        SVGUserAgent sVGUserAgent = new SVGUserAgent();
        BridgeContext bridgeContext = new BridgeContext(sVGUserAgent, new DocumentLoader(sVGUserAgent));
        this.context = bridgeContext;
        bridgeContext.setDynamic(true);
    }

    public void loadImage(InputStream inputStream, String str) throws IOException {
        SVGDocument createDocument = this.svgFact.createDocument("", inputStream);
        this.context.getUserAgent().initViewbox(createDocument);
        this.builder.build(this.context, createDocument);
    }

    public void loadImage(byte[] bArr, String str) throws IOException {
        loadImage((InputStream) new UnsynchronizedByteArrayInputStream(bArr), str);
    }

    public Rectangle2D getBounds() {
        return this.context.getUserAgent().getViewbox();
    }

    public void setAlpha(double d) {
        this.alpha = d;
    }

    public BufferedImage getImage() {
        return getImage(getDimension());
    }

    public BufferedImage getImage(Dimension2D dimension2D) {
        BufferedImage bufferedImage = new BufferedImage((int) dimension2D.getWidth(), (int) dimension2D.getHeight(), 2);
        Graphics2D graphics = bufferedImage.getGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.setRenderingHint(RenderingHintsKeyExt.KEY_BUFFERED_IMAGE, new WeakReference(bufferedImage));
        Dimension2D dimension = getDimension();
        graphics.scale(dimension2D.getWidth() / dimension.getWidth(), dimension2D.getHeight() / dimension.getHeight());
        getSVGRoot().paint(graphics);
        graphics.dispose();
        return bufferedImage;
    }

    public boolean drawImage(Graphics2D graphics2D, Rectangle2D rectangle2D) {
        return drawImage(graphics2D, rectangle2D, (Insets) null);
    }

    public boolean drawImage(Graphics2D graphics2D, Rectangle2D rectangle2D, Insets insets) {
        graphics2D.setRenderingHint(RenderingHintsKeyExt.KEY_BUFFERED_IMAGE, graphics2D.getRenderingHint(Drawable.BUFFERED_IMAGE));
        GraphicsNode sVGRoot = getSVGRoot();
        Dimension2D dimension = getDimension();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(rectangle2D.getX(), rectangle2D.getY());
        affineTransform.scale(rectangle2D.getWidth() / dimension.getWidth(), rectangle2D.getHeight() / dimension.getHeight());
        sVGRoot.setTransform(affineTransform);
        if (insets == null) {
            sVGRoot.setClip((ClipRable) null);
        } else {
            sVGRoot.setClip(new ClipRable8Bit((Filter) null, new Rectangle2D.Double(rectangle2D.getX() + ((double) insets.left), rectangle2D.getY() + ((double) insets.top), rectangle2D.getWidth() - ((double) (insets.left + insets.right)), rectangle2D.getHeight() - ((double) (insets.top + insets.bottom)))));
        }
        sVGRoot.paint(graphics2D);
        return true;
    }

    public boolean canRender(String str) {
        return PictureData.PictureType.SVG.contentType.equalsIgnoreCase(str);
    }

    public Rectangle2D getNativeBounds() {
        return getSVGRoot().getPrimitiveBounds();
    }

    public GraphicsNode getSVGRoot() {
        BridgeContext bridgeContext = this.context;
        return bridgeContext.getGraphicsNode(bridgeContext.getDocument());
    }
}
