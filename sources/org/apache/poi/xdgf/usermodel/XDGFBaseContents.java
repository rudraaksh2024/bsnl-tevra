package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.ConnectType;
import com.microsoft.schemas.office.visio.x2012.main.PageContentsType;
import com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.poi.xdgf.usermodel.shape.ShapeRenderer;
import org.apache.poi.xdgf.usermodel.shape.ShapeVisitor;
import org.apache.poi.xdgf.usermodel.shape.exceptions.StopVisiting;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;

public class XDGFBaseContents extends XDGFXMLDocumentPart {
    protected List<XDGFConnection> _connections = new ArrayList();
    protected PageContentsType _pageContents;
    protected Map<Long, XDGFShape> _shapes = new HashMap();
    protected List<XDGFShape> _toplevelShapes = new ArrayList();

    public XDGFBaseContents(PackagePart packagePart) {
        super(packagePart);
    }

    @Internal
    public PageContentsType getXmlObject() {
        return this._pageContents;
    }

    /* access modifiers changed from: protected */
    public void onDocumentRead() {
        int i = 0;
        if (this._pageContents.isSetShapes()) {
            for (ShapeSheetType xDGFShape : this._pageContents.getShapes().getShapeArray()) {
                XDGFShape xDGFShape2 = new XDGFShape(xDGFShape, this, this._document);
                this._toplevelShapes.add(xDGFShape2);
                addToShapeIndex(xDGFShape2);
            }
        }
        if (this._pageContents.isSetConnects()) {
            ConnectType[] connectArray = this._pageContents.getConnects().getConnectArray();
            int length = connectArray.length;
            while (i < length) {
                ConnectType connectType = connectArray[i];
                XDGFShape xDGFShape3 = this._shapes.get(Long.valueOf(connectType.getFromSheet()));
                XDGFShape xDGFShape4 = this._shapes.get(Long.valueOf(connectType.getToSheet()));
                if (xDGFShape3 == null) {
                    throw new POIXMLException(this + "; Connect; Invalid from id: " + connectType.getFromSheet());
                } else if (xDGFShape4 != null) {
                    this._connections.add(new XDGFConnection(connectType, xDGFShape3, xDGFShape4));
                    i++;
                } else {
                    throw new POIXMLException(this + "; Connect; Invalid to id: " + connectType.getToSheet());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addToShapeIndex(XDGFShape xDGFShape) {
        this._shapes.put(Long.valueOf(xDGFShape.getID()), xDGFShape);
        List<XDGFShape> shapes = xDGFShape.getShapes();
        if (shapes != null) {
            for (XDGFShape addToShapeIndex : shapes) {
                addToShapeIndex(addToShapeIndex);
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        visitShapes(new ShapeRenderer(graphics2D));
    }

    public XDGFShape getShapeById(long j) {
        return this._shapes.get(Long.valueOf(j));
    }

    public Map<Long, XDGFShape> getShapesMap() {
        return Collections.unmodifiableMap(this._shapes);
    }

    public Collection<XDGFShape> getShapes() {
        return this._shapes.values();
    }

    public List<XDGFShape> getTopLevelShapes() {
        return Collections.unmodifiableList(this._toplevelShapes);
    }

    public List<XDGFConnection> getConnections() {
        return Collections.unmodifiableList(this._connections);
    }

    public String toString() {
        return getPackagePart().getPartName().toString();
    }

    public void visitShapes(ShapeVisitor shapeVisitor) {
        try {
            for (XDGFShape visitShapes : this._toplevelShapes) {
                visitShapes.visitShapes(shapeVisitor, new AffineTransform(), 0);
            }
        } catch (StopVisiting unused) {
        } catch (POIXMLException e) {
            throw XDGFException.wrap((POIXMLDocumentPart) this, e);
        }
    }
}
