package org.apache.poi.sl.draw.geom;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class PresetGeometries {
    private final Map<String, CustomGeometry> map;

    public boolean equals(Object obj) {
        return this == obj;
    }

    private static class SingletonHelper {
        /* access modifiers changed from: private */
        public static final PresetGeometries INSTANCE = new PresetGeometries();

        private SingletonHelper() {
        }
    }

    public static PresetGeometries getInstance() {
        return SingletonHelper.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        if (r4 != null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004b, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private PresetGeometries() {
        /*
            r4 = this;
            r4.<init>()
            java.util.TreeMap r0 = new java.util.TreeMap
            r0.<init>()
            r4.map = r0
            java.lang.Class<org.apache.poi.sl.draw.geom.PresetGeometries> r4 = org.apache.poi.sl.draw.geom.PresetGeometries.class
            java.lang.String r1 = "presetShapeDefinitions.xml"
            java.io.InputStream r4 = r4.getResourceAsStream(r1)     // Catch:{ IOException | XMLStreamException -> 0x004c }
            javax.xml.stream.XMLInputFactory r1 = org.apache.poi.util.XMLHelper.newXMLInputFactory()     // Catch:{ all -> 0x003e }
            javax.xml.transform.stream.StreamSource r2 = new javax.xml.transform.stream.StreamSource     // Catch:{ all -> 0x003e }
            r2.<init>(r4)     // Catch:{ all -> 0x003e }
            javax.xml.stream.XMLStreamReader r1 = r1.createXMLStreamReader(r2)     // Catch:{ all -> 0x003e }
            org.apache.poi.sl.draw.geom.PresetParser r2 = new org.apache.poi.sl.draw.geom.PresetParser     // Catch:{ all -> 0x0039 }
            org.apache.poi.sl.draw.geom.PresetParser$Mode r3 = org.apache.poi.sl.draw.geom.PresetParser.Mode.FILE     // Catch:{ all -> 0x0039 }
            r2.<init>(r3)     // Catch:{ all -> 0x0039 }
            r2.parse(r1)     // Catch:{ all -> 0x0039 }
            java.util.Map r2 = r2.getGeom()     // Catch:{ all -> 0x0039 }
            r0.putAll(r2)     // Catch:{ all -> 0x0039 }
            r1.close()     // Catch:{ all -> 0x003e }
            if (r4 == 0) goto L_0x0038
            r4.close()     // Catch:{ IOException | XMLStreamException -> 0x004c }
        L_0x0038:
            return
        L_0x0039:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x003e }
            throw r0     // Catch:{ all -> 0x003e }
        L_0x003e:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r1 = move-exception
            if (r4 == 0) goto L_0x004b
            r4.close()     // Catch:{ all -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r4 = move-exception
            r0.addSuppressed(r4)     // Catch:{ IOException | XMLStreamException -> 0x004c }
        L_0x004b:
            throw r1     // Catch:{ IOException | XMLStreamException -> 0x004c }
        L_0x004c:
            r4 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.geom.PresetGeometries.<init>():void");
    }

    public CustomGeometry get(String str) {
        if (str == null) {
            return null;
        }
        return this.map.get(str);
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public int size() {
        return this.map.size();
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.map});
    }
}
