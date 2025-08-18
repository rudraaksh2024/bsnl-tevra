package org.apache.poi.xssf.usermodel;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DifferentialStyleProvider;
import org.apache.poi.ss.usermodel.TableStyle;
import org.apache.poi.ss.usermodel.TableStyleType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleElement;

public class XSSFTableStyle implements TableStyle {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSSFTableStyle.class);
    private final Map<TableStyleType, DifferentialStyleProvider> elementMap = new EnumMap(TableStyleType.class);
    private final int index;
    private final String name;

    public boolean isBuiltin() {
        return false;
    }

    public XSSFTableStyle(int i, CTDxfs cTDxfs, CTTableStyle cTTableStyle, IndexedColorMap indexedColorMap) {
        XSSFDxfStyleProvider xSSFDxfStyleProvider;
        CTDxf cTDxf;
        this.name = cTTableStyle.getName();
        this.index = i;
        ArrayList arrayList = new ArrayList();
        XmlCursor newCursor = cTDxfs.newCursor();
        try {
            newCursor.selectPath("declare namespace x='http://schemas.openxmlformats.org/spreadsheetml/2006/main' .//x:dxf | .//dxf");
            while (newCursor.toNextSelection()) {
                XmlObject object = newCursor.getObject();
                String nodeName = object.getDomNode().getParentNode().getNodeName();
                if (nodeName.equals("mc:Fallback") || nodeName.equals("x:dxfs") || nodeName.contentEquals("dxfs")) {
                    if (object instanceof CTDxf) {
                        cTDxf = (CTDxf) object;
                    } else {
                        cTDxf = CTDxf.Factory.parse(object.newXMLStreamReader(), new XmlOptions().setDocumentType(CTDxf.type));
                    }
                    if (cTDxf != null) {
                        arrayList.add(cTDxf);
                    }
                }
            }
            newCursor.dispose();
            for (CTTableStyleElement next : cTTableStyle.getTableStyleElementList()) {
                TableStyleType valueOf = TableStyleType.valueOf(next.getType().toString());
                if (next.isSetDxfId()) {
                    CTDxf cTDxf2 = (CTDxf) arrayList.get((int) next.getDxfId());
                    int size = next.isSetSize() ? (int) next.getSize() : 0;
                    if (cTDxf2 != null) {
                        xSSFDxfStyleProvider = new XSSFDxfStyleProvider(cTDxf2, size, indexedColorMap);
                        this.elementMap.put(valueOf, xSSFDxfStyleProvider);
                    }
                }
                xSSFDxfStyleProvider = null;
                this.elementMap.put(valueOf, xSSFDxfStyleProvider);
            }
        } catch (XmlException e) {
            LOG.atWarn().withThrowable(e).log("Error parsing XSSFTableStyle");
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return this.index;
    }

    public DifferentialStyleProvider getStyle(TableStyleType tableStyleType) {
        return this.elementMap.get(tableStyleType);
    }
}
