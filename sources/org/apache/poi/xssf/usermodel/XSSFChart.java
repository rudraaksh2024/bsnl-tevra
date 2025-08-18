package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageMargins;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTx;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public final class XSSFChart extends XDDFChart {
    private XSSFGraphicFrame frame;

    /* access modifiers changed from: protected */
    public POIXMLFactory getChartFactory() {
        return null;
    }

    /* access modifiers changed from: protected */
    public POIXMLRelation getChartRelation() {
        return null;
    }

    /* access modifiers changed from: protected */
    public POIXMLRelation getChartWorkbookRelation() {
        return null;
    }

    protected XSSFChart() {
        createChart();
    }

    protected XSSFChart(PackagePart packagePart) throws IOException, XmlException {
        super(packagePart);
    }

    private void createChart() {
        getCTPlotArea().addNewLayout();
        getCTChart().addNewPlotVisOnly().setVal(true);
        CTPrintSettings addNewPrintSettings = this.chartSpace.addNewPrintSettings();
        addNewPrintSettings.addNewHeaderFooter();
        CTPageMargins addNewPageMargins = addNewPrintSettings.addNewPageMargins();
        addNewPageMargins.setB(0.75d);
        addNewPageMargins.setL(0.7d);
        addNewPageMargins.setR(0.7d);
        addNewPageMargins.setT(0.75d);
        addNewPageMargins.setHeader(0.3d);
        addNewPageMargins.setFooter(0.3d);
        addNewPrintSettings.addNewPageSetup();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        r5.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0032, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        if (r1 != null) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r5 = this;
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "chartSpace"
            java.lang.String r4 = "c"
            r1.<init>(r2, r3, r4)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r5.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace r5 = r5.chartSpace     // Catch:{ all -> 0x0030 }
            r5.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            return
        L_0x0030:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r0 = move-exception
            if (r1 == 0) goto L_0x003d
            r1.close()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r1 = move-exception
            r5.addSuppressed(r1)
        L_0x003d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFChart.commit():void");
    }

    public XSSFGraphicFrame getGraphicFrame() {
        return this.frame;
    }

    /* access modifiers changed from: protected */
    public void setGraphicFrame(XSSFGraphicFrame xSSFGraphicFrame) {
        this.frame = xSSFGraphicFrame;
    }

    public XSSFRichTextString getTitleText() {
        if (!getCTChart().isSetTitle()) {
            return null;
        }
        CTTitle title = getCTChart().getTitle();
        StringBuilder sb = new StringBuilder(64);
        for (XmlObject domNode : title.selectPath("declare namespace a='http://schemas.openxmlformats.org/drawingml/2006/main' .//a:t")) {
            NodeList childNodes = domNode.getDomNode().getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node item = childNodes.item(i);
                if (item instanceof Text) {
                    sb.append(item.getNodeValue());
                }
            }
        }
        return new XSSFRichTextString(sb.toString());
    }

    public String getTitleFormula() {
        if (!getCTChart().isSetTitle()) {
            return null;
        }
        CTTitle title = getCTChart().getTitle();
        if (!title.isSetTx()) {
            return null;
        }
        CTTx tx = title.getTx();
        if (!tx.isSetStrRef()) {
            return null;
        }
        return tx.getStrRef().getF();
    }

    public void setTitleFormula(String str) {
        CTTitle cTTitle;
        CTTx cTTx;
        CTStrRef cTStrRef;
        if (getCTChart().isSetTitle()) {
            cTTitle = getCTChart().getTitle();
        } else {
            cTTitle = getCTChart().addNewTitle();
        }
        if (cTTitle.isSetTx()) {
            cTTx = cTTitle.getTx();
        } else {
            cTTx = cTTitle.addNewTx();
        }
        if (cTTx.isSetRich()) {
            cTTx.unsetRich();
        }
        if (cTTx.isSetStrRef()) {
            cTStrRef = cTTx.getStrRef();
        } else {
            cTStrRef = cTTx.addNewStrRef();
        }
        cTStrRef.setF(str);
    }
}
