package org.apache.poi.sl.draw.geom;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.util.Internal;

@Internal
class PresetParser {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = LogManager.getLogger((Class<?>) PresetParser.class);
    private CustomGeometry customGeometry;
    private final Map<String, CustomGeometry> geom;
    private Mode mode;
    private Path path;
    private boolean useAdjustValue;

    private static boolean isWhiteSpace(char c) {
        return c == 9 || c == 10 || c == 13 || c == ' ';
    }

    enum Mode {
        FILE(new PresetParser$Mode$$ExternalSyntheticLambda0()),
        SHAPE_LST(new PresetParser$Mode$$ExternalSyntheticLambda1()),
        SHAPE(new PresetParser$Mode$$ExternalSyntheticLambda2()),
        GUIDE_LST(new PresetParser$Mode$$ExternalSyntheticLambda3()),
        AH_LST(new PresetParser$Mode$$ExternalSyntheticLambda4()),
        CXN_LST(new PresetParser$Mode$$ExternalSyntheticLambda5()),
        PATH_LST(new PresetParser$Mode$$ExternalSyntheticLambda6()),
        PATH(new PresetParser$Mode$$ExternalSyntheticLambda7());
        
        final Handler handler;

        interface Handler {
            void update(PresetParser presetParser, XMLStreamReader xMLStreamReader) throws XMLStreamException;
        }

        private Mode(Handler handler2) {
            this.handler = handler2;
        }
    }

    PresetParser(Mode mode2) {
        HashMap hashMap = new HashMap();
        this.geom = hashMap;
        this.mode = mode2;
        if (mode2 == Mode.SHAPE) {
            CustomGeometry customGeometry2 = new CustomGeometry();
            this.customGeometry = customGeometry2;
            hashMap.put("custom", customGeometry2);
        }
    }

    /* access modifiers changed from: package-private */
    public void parse(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        while (xMLStreamReader.hasNext()) {
            int next = xMLStreamReader.next();
            if (next == 1) {
                this.mode.handler.update(this, xMLStreamReader);
            } else if (next == 2) {
                endContext();
            } else if (next == 8) {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, CustomGeometry> getGeom() {
        return this.geom;
    }

    /* access modifiers changed from: private */
    public void updateFile(XMLStreamReader xMLStreamReader) {
        xMLStreamReader.getLocalName();
        this.mode = Mode.SHAPE_LST;
    }

    /* access modifiers changed from: private */
    public void updateShapeList(XMLStreamReader xMLStreamReader) {
        String localName = xMLStreamReader.getLocalName();
        this.customGeometry = new CustomGeometry();
        if (this.geom.containsKey(localName)) {
            LOG.atWarn().log("Duplicate definition of {}", (Object) localName);
        }
        this.geom.put(localName, this.customGeometry);
        this.mode = Mode.SHAPE;
    }

    /* access modifiers changed from: private */
    public void updateShape(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        String localName = xMLStreamReader.getLocalName();
        localName.hashCode();
        char c = 65535;
        switch (localName.hashCode()) {
            case -1346505100:
                if (localName.equals("cxnLst")) {
                    c = 0;
                    break;
                }
                break;
            case -791471768:
                if (localName.equals("pathLst")) {
                    c = 1;
                    break;
                }
                break;
            case 3496420:
                if (localName.equals("rect")) {
                    c = 2;
                    break;
                }
                break;
            case 92756518:
                if (localName.equals("ahLst")) {
                    c = 3;
                    break;
                }
                break;
            case 93173592:
                if (localName.equals("avLst")) {
                    c = 4;
                    break;
                }
                break;
            case 98178480:
                if (localName.equals("gdLst")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mode = Mode.CXN_LST;
                return;
            case 1:
                this.mode = Mode.PATH_LST;
                return;
            case 2:
                addRectangle(xMLStreamReader);
                return;
            case 3:
                this.mode = Mode.AH_LST;
                return;
            case 4:
                this.useAdjustValue = true;
                this.mode = Mode.GUIDE_LST;
                return;
            case 5:
                this.useAdjustValue = false;
                this.mode = Mode.GUIDE_LST;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void updateGuideList(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        AdjustValue adjustValue;
        xMLStreamReader.getLocalName();
        if (this.useAdjustValue) {
            CustomGeometry customGeometry2 = this.customGeometry;
            AdjustValue adjustValue2 = new AdjustValue();
            AdjustValue adjustValue3 = adjustValue2;
            AdjustValue adjustValue4 = adjustValue2;
            customGeometry2.addAdjustGuide(adjustValue2);
            adjustValue = adjustValue2;
        } else {
            CustomGeometry customGeometry3 = this.customGeometry;
            Guide guide = new Guide();
            customGeometry3.addGeomGuide(guide);
            adjustValue = guide;
        }
        parseAttributes(xMLStreamReader, new PresetParser$$ExternalSyntheticLambda3(adjustValue));
        nextTag(xMLStreamReader);
    }

    static /* synthetic */ void lambda$updateGuideList$0(Guide guide, String str, String str2) {
        str.hashCode();
        if (str.equals("fmla")) {
            guide.setFmla(str2);
        } else if (str.equals("name")) {
            guide.setName(collapseString(str2));
        }
    }

    /* access modifiers changed from: private */
    public void updateAhList(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        String localName = xMLStreamReader.getLocalName();
        localName.hashCode();
        if (localName.equals("ahPolar")) {
            addPolar(xMLStreamReader);
        } else if (localName.equals("ahXY")) {
            addXY(xMLStreamReader);
        }
    }

    private void addXY(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        XYAdjustHandle xYAdjustHandle = new XYAdjustHandle();
        this.customGeometry.addAdjustHandle(xYAdjustHandle);
        parseAttributes(xMLStreamReader, new PresetParser$$ExternalSyntheticLambda7(xYAdjustHandle));
        xYAdjustHandle.setPos(parsePosPoint(xMLStreamReader));
    }

    static /* synthetic */ void lambda$addXY$1(XYAdjustHandle xYAdjustHandle, String str, String str2) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1251269470:
                if (str.equals("gdRefX")) {
                    c = 0;
                    break;
                }
                break;
            case -1251269469:
                if (str.equals("gdRefY")) {
                    c = 1;
                    break;
                }
                break;
            case 3344244:
                if (str.equals("maxX")) {
                    c = 2;
                    break;
                }
                break;
            case 3344245:
                if (str.equals("maxY")) {
                    c = 3;
                    break;
                }
                break;
            case 3351622:
                if (str.equals("minX")) {
                    c = 4;
                    break;
                }
                break;
            case 3351623:
                if (str.equals("minY")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                xYAdjustHandle.setGdRefX(collapseString(str2));
                return;
            case 1:
                xYAdjustHandle.setGdRefY(collapseString(str2));
                return;
            case 2:
                xYAdjustHandle.setMaxX(str2);
                return;
            case 3:
                xYAdjustHandle.setMaxY(str2);
                return;
            case 4:
                xYAdjustHandle.setMinX(str2);
                return;
            case 5:
                xYAdjustHandle.setMinY(str2);
                return;
            default:
                return;
        }
    }

    private void addPolar(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        PolarAdjustHandle polarAdjustHandle = new PolarAdjustHandle();
        this.customGeometry.addAdjustHandle(polarAdjustHandle);
        parseAttributes(xMLStreamReader, new PresetParser$$ExternalSyntheticLambda5(polarAdjustHandle));
        polarAdjustHandle.setPos(parsePosPoint(xMLStreamReader));
    }

    static /* synthetic */ void lambda$addPolar$2(PolarAdjustHandle polarAdjustHandle, String str, String str2) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1251269476:
                if (str.equals("gdRefR")) {
                    c = 0;
                    break;
                }
                break;
            case -1081167402:
                if (str.equals("maxAng")) {
                    c = 1;
                    break;
                }
                break;
            case -1074077144:
                if (str.equals("minAng")) {
                    c = 2;
                    break;
                }
                break;
            case 3344238:
                if (str.equals("maxR")) {
                    c = 3;
                    break;
                }
                break;
            case 3351616:
                if (str.equals("minR")) {
                    c = 4;
                    break;
                }
                break;
            case 120863620:
                if (str.equals("gdRefAng")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                polarAdjustHandle.setGdRefR(collapseString(str2));
                return;
            case 1:
                polarAdjustHandle.setMaxAng(str2);
                return;
            case 2:
                polarAdjustHandle.setMinAng(str2);
                return;
            case 3:
                polarAdjustHandle.setMaxR(str2);
                return;
            case 4:
                polarAdjustHandle.setMinR(str2);
                return;
            case 5:
                polarAdjustHandle.setGdRefAng(collapseString(str2));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void updateCxnList(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        xMLStreamReader.getLocalName();
        ConnectionSite connectionSite = new ConnectionSite();
        this.customGeometry.addConnectionSite(connectionSite);
        parseAttributes(xMLStreamReader, new PresetParser$$ExternalSyntheticLambda0(connectionSite));
        connectionSite.setPos(parsePosPoint(xMLStreamReader));
    }

    static /* synthetic */ void lambda$updateCxnList$3(ConnectionSite connectionSite, String str, String str2) {
        if ("ang".equals(str)) {
            connectionSite.setAng(str2);
        }
    }

    /* access modifiers changed from: private */
    public void updatePathLst(XMLStreamReader xMLStreamReader) {
        xMLStreamReader.getLocalName();
        Path path2 = new Path();
        this.path = path2;
        this.customGeometry.addPath(path2);
        parseAttributes(xMLStreamReader, new PresetParser$$ExternalSyntheticLambda2(this));
        this.mode = Mode.PATH;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updatePathLst$4$org-apache-poi-sl-draw-geom-PresetParser  reason: not valid java name */
    public /* synthetic */ void m2235lambda$updatePathLst$4$orgapachepoisldrawgeomPresetParser(String str, String str2) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1453197163:
                if (str.equals("extrusionOk")) {
                    c = 0;
                    break;
                }
                break;
            case -891980232:
                if (str.equals("stroke")) {
                    c = 1;
                    break;
                }
                break;
            case 104:
                if (str.equals("h")) {
                    c = 2;
                    break;
                }
                break;
            case 119:
                if (str.equals("w")) {
                    c = 3;
                    break;
                }
                break;
            case 3143043:
                if (str.equals("fill")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.path.setExtrusionOk(Boolean.parseBoolean(str2));
                return;
            case 1:
                this.path.setStroke(Boolean.parseBoolean(str2));
                return;
            case 2:
                this.path.setH(Long.parseLong(str2));
                return;
            case 3:
                this.path.setW(Long.parseLong(str2));
                return;
            case 4:
                this.path.setFill(mapFill(str2));
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.apache.poi.sl.usermodel.PaintStyle.PaintModifier mapFill(java.lang.String r6) {
        /*
            int r0 = r6.hashCode()
            r1 = 6
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            switch(r0) {
                case -2005126536: goto L_0x003f;
                case -1414880040: goto L_0x0035;
                case -1338968417: goto L_0x002b;
                case 3387192: goto L_0x0021;
                case 3387324: goto L_0x0017;
                case 170546239: goto L_0x000d;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0049
        L_0x000d:
            java.lang.String r0 = "lighten"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = r4
            goto L_0x004a
        L_0x0017:
            java.lang.String r0 = "norm"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = r5
            goto L_0x004a
        L_0x0021:
            java.lang.String r0 = "none"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = 1
            goto L_0x004a
        L_0x002b:
            java.lang.String r0 = "darken"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = r2
            goto L_0x004a
        L_0x0035:
            java.lang.String r0 = "darkenLess"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = r1
            goto L_0x004a
        L_0x003f:
            java.lang.String r0 = "lightenLess"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = r3
            goto L_0x004a
        L_0x0049:
            r6 = -1
        L_0x004a:
            if (r6 == r5) goto L_0x0063
            if (r6 == r4) goto L_0x0060
            if (r6 == r3) goto L_0x005d
            if (r6 == r2) goto L_0x005a
            if (r6 == r1) goto L_0x0057
            org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r6 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.NONE
            return r6
        L_0x0057:
            org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r6 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.DARKEN_LESS
            return r6
        L_0x005a:
            org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r6 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.DARKEN
            return r6
        L_0x005d:
            org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r6 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.LIGHTEN_LESS
            return r6
        L_0x0060:
            org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r6 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.LIGHTEN
            return r6
        L_0x0063:
            org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r6 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.NORM
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.geom.PresetParser.mapFill(java.lang.String):org.apache.poi.sl.usermodel.PaintStyle$PaintModifier");
    }

    /* access modifiers changed from: private */
    public void updatePath(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        String localName = xMLStreamReader.getLocalName();
        localName.hashCode();
        char c = 65535;
        switch (localName.hashCode()) {
            case -1513004949:
                if (localName.equals("quadBezTo")) {
                    c = 0;
                    break;
                }
                break;
            case -1068263892:
                if (localName.equals("moveTo")) {
                    c = 1;
                    break;
                }
                break;
            case 3325853:
                if (localName.equals("lnTo")) {
                    c = 2;
                    break;
                }
                break;
            case 93075565:
                if (localName.equals("arcTo")) {
                    c = 3;
                    break;
                }
                break;
            case 94756344:
                if (localName.equals("close")) {
                    c = 4;
                    break;
                }
                break;
            case 1594918984:
                if (localName.equals("cubicBezTo")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                quadBezTo(xMLStreamReader);
                return;
            case 1:
                moveTo(xMLStreamReader);
                return;
            case 2:
                lineTo(xMLStreamReader);
                return;
            case 3:
                arcTo(xMLStreamReader);
                return;
            case 4:
                closePath(xMLStreamReader);
                return;
            case 5:
                cubicBezTo(xMLStreamReader);
                return;
            default:
                return;
        }
    }

    private void closePath(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.path.addCommand(new ClosePathCommand());
        nextTag(xMLStreamReader);
    }

    private void moveTo(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        MoveToCommand moveToCommand = new MoveToCommand();
        this.path.addCommand(moveToCommand);
        moveToCommand.setPt(parsePtPoint(xMLStreamReader, true));
    }

    private void lineTo(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        LineToCommand lineToCommand = new LineToCommand();
        this.path.addCommand(lineToCommand);
        lineToCommand.setPt(parsePtPoint(xMLStreamReader, true));
    }

    private void arcTo(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        ArcToCommand arcToCommand = new ArcToCommand();
        this.path.addCommand(arcToCommand);
        parseAttributes(xMLStreamReader, new PresetParser$$ExternalSyntheticLambda6(arcToCommand));
        nextTag(xMLStreamReader);
    }

    static /* synthetic */ void lambda$arcTo$5(ArcToCommand arcToCommand, String str, String str2) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 3306:
                if (str.equals("hR")) {
                    c = 0;
                    break;
                }
                break;
            case 3771:
                if (str.equals("wR")) {
                    c = 1;
                    break;
                }
                break;
            case 109726649:
                if (str.equals("stAng")) {
                    c = 2;
                    break;
                }
                break;
            case 109816022:
                if (str.equals("swAng")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                arcToCommand.setHR(str2);
                return;
            case 1:
                arcToCommand.setWR(str2);
                return;
            case 2:
                arcToCommand.setStAng(str2);
                return;
            case 3:
                arcToCommand.setSwAng(str2);
                return;
            default:
                return;
        }
    }

    private void quadBezTo(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        QuadToCommand quadToCommand = new QuadToCommand();
        this.path.addCommand(quadToCommand);
        AdjustPoint parsePtPoint = parsePtPoint(xMLStreamReader, false);
        AdjustPoint parsePtPoint2 = parsePtPoint(xMLStreamReader, true);
        quadToCommand.setPt1(parsePtPoint);
        quadToCommand.setPt2(parsePtPoint2);
    }

    private void cubicBezTo(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        CurveToCommand curveToCommand = new CurveToCommand();
        this.path.addCommand(curveToCommand);
        AdjustPoint parsePtPoint = parsePtPoint(xMLStreamReader, false);
        AdjustPoint parsePtPoint2 = parsePtPoint(xMLStreamReader, false);
        AdjustPoint parsePtPoint3 = parsePtPoint(xMLStreamReader, true);
        curveToCommand.setPt1(parsePtPoint);
        curveToCommand.setPt2(parsePtPoint2);
        curveToCommand.setPt3(parsePtPoint3);
    }

    private void addRectangle(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        String[] strArr = new String[4];
        parseAttributes(xMLStreamReader, new PresetParser$$ExternalSyntheticLambda1(strArr));
        this.customGeometry.setTextBounds(strArr[0], strArr[1], strArr[2], strArr[3]);
        nextTag(xMLStreamReader);
    }

    static /* synthetic */ void lambda$addRectangle$6(String[] strArr, String str, String str2) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 98:
                if (str.equals("b")) {
                    c = 0;
                    break;
                }
                break;
            case 108:
                if (str.equals("l")) {
                    c = 1;
                    break;
                }
                break;
            case 114:
                if (str.equals("r")) {
                    c = 2;
                    break;
                }
                break;
            case 116:
                if (str.equals("t")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                strArr[3] = str2;
                return;
            case 1:
                strArr[0] = str2;
                return;
            case 2:
                strArr[2] = str2;
                return;
            case 3:
                strArr[1] = str2;
                return;
            default:
                return;
        }
    }

    /* renamed from: org.apache.poi.sl.draw.geom.PresetParser$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$draw$geom$PresetParser$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.sl.draw.geom.PresetParser$Mode[] r0 = org.apache.poi.sl.draw.geom.PresetParser.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$draw$geom$PresetParser$Mode = r0
                org.apache.poi.sl.draw.geom.PresetParser$Mode r1 = org.apache.poi.sl.draw.geom.PresetParser.Mode.FILE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$PresetParser$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.draw.geom.PresetParser$Mode r1 = org.apache.poi.sl.draw.geom.PresetParser.Mode.SHAPE_LST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$PresetParser$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.draw.geom.PresetParser$Mode r1 = org.apache.poi.sl.draw.geom.PresetParser.Mode.SHAPE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$PresetParser$Mode     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.draw.geom.PresetParser$Mode r1 = org.apache.poi.sl.draw.geom.PresetParser.Mode.CXN_LST     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$PresetParser$Mode     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.sl.draw.geom.PresetParser$Mode r1 = org.apache.poi.sl.draw.geom.PresetParser.Mode.AH_LST     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$PresetParser$Mode     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.sl.draw.geom.PresetParser$Mode r1 = org.apache.poi.sl.draw.geom.PresetParser.Mode.GUIDE_LST     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$PresetParser$Mode     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.sl.draw.geom.PresetParser$Mode r1 = org.apache.poi.sl.draw.geom.PresetParser.Mode.PATH_LST     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$PresetParser$Mode     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.sl.draw.geom.PresetParser$Mode r1 = org.apache.poi.sl.draw.geom.PresetParser.Mode.PATH     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.geom.PresetParser.AnonymousClass1.<clinit>():void");
        }
    }

    private void endContext() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$sl$draw$geom$PresetParser$Mode[this.mode.ordinal()]) {
            case 1:
            case 2:
                this.mode = Mode.FILE;
                return;
            case 3:
                this.mode = Mode.SHAPE_LST;
                return;
            case 4:
            case 5:
            case 6:
            case 7:
                this.useAdjustValue = false;
                this.path = null;
                this.mode = Mode.SHAPE;
                return;
            case 8:
                this.path = null;
                this.mode = Mode.PATH_LST;
                return;
            default:
                return;
        }
    }

    private AdjustPoint parsePosPoint(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        return parseAdjPoint(xMLStreamReader, true, "pos");
    }

    private AdjustPoint parsePtPoint(XMLStreamReader xMLStreamReader, boolean z) throws XMLStreamException {
        return parseAdjPoint(xMLStreamReader, z, "pt");
    }

    private AdjustPoint parseAdjPoint(XMLStreamReader xMLStreamReader, boolean z, String str) throws XMLStreamException {
        if (nextTag(xMLStreamReader) == 2) {
            return null;
        }
        AdjustPoint adjustPoint = new AdjustPoint();
        parseAttributes(xMLStreamReader, new PresetParser$$ExternalSyntheticLambda4(adjustPoint));
        nextTag(xMLStreamReader);
        if (z) {
            nextTag(xMLStreamReader);
        }
        return adjustPoint;
    }

    static /* synthetic */ void lambda$parseAdjPoint$7(AdjustPoint adjustPoint, String str, String str2) {
        str.hashCode();
        if (str.equals("x")) {
            adjustPoint.setX(str2);
        } else if (str.equals("y")) {
            adjustPoint.setY(str2);
        }
    }

    private void parseAttributes(XMLStreamReader xMLStreamReader, BiConsumer<String, String> biConsumer) {
        for (int i = 0; i < xMLStreamReader.getAttributeCount(); i++) {
            biConsumer.accept(xMLStreamReader.getAttributeLocalName(i), xMLStreamReader.getAttributeValue(i));
        }
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private static int nextTag(javax.xml.stream.XMLStreamReader r2) throws javax.xml.stream.XMLStreamException {
        /*
        L_0x0000:
            int r0 = r2.next()
            r1 = 1
            if (r0 == r1) goto L_0x000e
            r1 = 2
            if (r0 == r1) goto L_0x000e
            r1 = 8
            if (r0 != r1) goto L_0x0000
        L_0x000e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.geom.PresetParser.nextTag(javax.xml.stream.XMLStreamReader):int");
    }

    private static String collapseString(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i = 0;
        while (i < length && !isWhiteSpace(str.charAt(i))) {
            i++;
        }
        if (i == length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length);
        if (i != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(str.charAt(i2));
            }
            sb.append(Chars.SPACE);
        }
        boolean z = true;
        for (int i3 = i + 1; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            boolean isWhiteSpace = isWhiteSpace(charAt);
            if (!z || !isWhiteSpace) {
                if (isWhiteSpace) {
                    charAt = ' ';
                }
                sb.append(charAt);
                z = isWhiteSpace;
            }
        }
        int length2 = sb.length();
        if (length2 > 0) {
            int i4 = length2 - 1;
            if (sb.charAt(i4) == ' ') {
                sb.setLength(i4);
            }
        }
        return sb.toString();
    }
}
