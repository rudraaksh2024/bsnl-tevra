package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.SubRecord;

public class HSSFShapeFactory {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: org.apache.poi.hssf.record.ObjRecord} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void createShapeTree(org.apache.poi.ddf.EscherContainerRecord r10, org.apache.poi.hssf.record.EscherAggregate r11, org.apache.poi.hssf.usermodel.HSSFShapeContainer r12, org.apache.poi.poifs.filesystem.DirectoryNode r13) {
        /*
            short r0 = r10.getRecordId()
            short r1 = org.apache.poi.ddf.EscherContainerRecord.SPGR_CONTAINER
            r2 = 0
            r3 = 0
            r4 = 1
            if (r0 != r1) goto L_0x004a
            org.apache.poi.ddf.EscherRecord r0 = r10.getChild(r2)
            org.apache.poi.ddf.EscherContainerRecord r0 = (org.apache.poi.ddf.EscherContainerRecord) r0
            short r1 = org.apache.poi.ddf.EscherClientDataRecord.RECORD_ID
            org.apache.poi.ddf.EscherRecord r0 = r0.getChildById(r1)
            org.apache.poi.ddf.EscherClientDataRecord r0 = (org.apache.poi.ddf.EscherClientDataRecord) r0
            if (r0 == 0) goto L_0x0026
            java.util.Map r1 = r11.getShapeToObjMapping()
            java.lang.Object r0 = r1.get(r0)
            r3 = r0
            org.apache.poi.hssf.record.ObjRecord r3 = (org.apache.poi.hssf.record.ObjRecord) r3
        L_0x0026:
            org.apache.poi.hssf.usermodel.HSSFShapeGroup r0 = new org.apache.poi.hssf.usermodel.HSSFShapeGroup
            r0.<init>((org.apache.poi.ddf.EscherContainerRecord) r10, (org.apache.poi.hssf.record.ObjRecord) r3)
            java.util.List r10 = r10.getChildContainers()
            int r1 = r10.size()
            if (r1 <= r4) goto L_0x0045
            int r1 = r10.size()
            java.util.List r10 = r10.subList(r4, r1)
            org.apache.poi.hssf.usermodel.HSSFShapeFactory$$ExternalSyntheticLambda0 r1 = new org.apache.poi.hssf.usermodel.HSSFShapeFactory$$ExternalSyntheticLambda0
            r1.<init>(r11, r0, r13)
            r10.forEach(r1)
        L_0x0045:
            r12.addShape(r0)
            goto L_0x011f
        L_0x004a:
            short r0 = r10.getRecordId()
            short r1 = org.apache.poi.ddf.EscherContainerRecord.SP_CONTAINER
            if (r0 != r1) goto L_0x011f
            java.util.Map r0 = r11.getShapeToObjMapping()
            java.util.Iterator r1 = r10.iterator()
            r5 = r3
        L_0x005b:
            boolean r6 = r1.hasNext()
            r7 = 2
            if (r6 == 0) goto L_0x008b
            java.lang.Object r6 = r1.next()
            org.apache.poi.ddf.EscherRecord r6 = (org.apache.poi.ddf.EscherRecord) r6
            int[] r8 = org.apache.poi.hssf.usermodel.HSSFShapeFactory.AnonymousClass1.$SwitchMap$org$apache$poi$ddf$EscherRecordTypes
            short r9 = r6.getRecordId()
            org.apache.poi.ddf.EscherRecordTypes r9 = org.apache.poi.ddf.EscherRecordTypes.forTypeID(r9)
            int r9 = r9.ordinal()
            r8 = r8[r9]
            if (r8 == r4) goto L_0x0084
            if (r8 == r7) goto L_0x007d
            goto L_0x005b
        L_0x007d:
            java.lang.Object r5 = r0.get(r6)
            org.apache.poi.hssf.record.TextObjectRecord r5 = (org.apache.poi.hssf.record.TextObjectRecord) r5
            goto L_0x005b
        L_0x0084:
            java.lang.Object r3 = r0.get(r6)
            org.apache.poi.hssf.record.ObjRecord r3 = (org.apache.poi.hssf.record.ObjRecord) r3
            goto L_0x005b
        L_0x008b:
            if (r3 == 0) goto L_0x0117
            boolean r0 = isEmbeddedObject(r3)
            if (r0 == 0) goto L_0x009c
            org.apache.poi.hssf.usermodel.HSSFObjectData r11 = new org.apache.poi.hssf.usermodel.HSSFObjectData
            r11.<init>(r10, r3, r13)
            r12.addShape(r11)
            return
        L_0x009c:
            java.util.List r13 = r3.getSubRecords()
            java.lang.Object r13 = r13.get(r2)
            org.apache.poi.hssf.record.CommonObjectDataSubRecord r13 = (org.apache.poi.hssf.record.CommonObjectDataSubRecord) r13
            short r13 = r13.getObjectType()
            if (r13 == r4) goto L_0x010e
            if (r13 == r7) goto L_0x0108
            r0 = 6
            if (r13 == r0) goto L_0x0102
            r0 = 8
            if (r13 == r0) goto L_0x00fc
            r0 = 20
            if (r13 == r0) goto L_0x00f6
            r0 = 25
            if (r13 == r0) goto L_0x00eb
            r11 = 30
            if (r13 == r11) goto L_0x00c7
            org.apache.poi.hssf.usermodel.HSSFSimpleShape r11 = new org.apache.poi.hssf.usermodel.HSSFSimpleShape
            r11.<init>(r10, r3, r5)
            goto L_0x0113
        L_0x00c7:
            short r11 = org.apache.poi.ddf.EscherOptRecord.RECORD_ID
            org.apache.poi.ddf.EscherRecord r11 = r10.getChildById(r11)
            org.apache.poi.ddf.EscherOptRecord r11 = (org.apache.poi.ddf.EscherOptRecord) r11
            if (r11 != 0) goto L_0x00d7
            org.apache.poi.hssf.usermodel.HSSFSimpleShape r11 = new org.apache.poi.hssf.usermodel.HSSFSimpleShape
            r11.<init>(r10, r3, r5)
            goto L_0x0113
        L_0x00d7:
            org.apache.poi.ddf.EscherPropertyTypes r13 = org.apache.poi.ddf.EscherPropertyTypes.GEOMETRY__VERTICES
            org.apache.poi.ddf.EscherProperty r11 = r11.lookup((org.apache.poi.ddf.EscherPropertyTypes) r13)
            if (r11 == 0) goto L_0x00e5
            org.apache.poi.hssf.usermodel.HSSFPolygon r11 = new org.apache.poi.hssf.usermodel.HSSFPolygon
            r11.<init>(r10, r3, r5)
            goto L_0x0113
        L_0x00e5:
            org.apache.poi.hssf.usermodel.HSSFSimpleShape r11 = new org.apache.poi.hssf.usermodel.HSSFSimpleShape
            r11.<init>(r10, r3, r5)
            goto L_0x0113
        L_0x00eb:
            org.apache.poi.hssf.usermodel.HSSFComment r13 = new org.apache.poi.hssf.usermodel.HSSFComment
            org.apache.poi.hssf.record.NoteRecord r11 = r11.getNoteRecordByObj(r3)
            r13.<init>(r10, r3, r5, r11)
            r11 = r13
            goto L_0x0113
        L_0x00f6:
            org.apache.poi.hssf.usermodel.HSSFCombobox r11 = new org.apache.poi.hssf.usermodel.HSSFCombobox
            r11.<init>((org.apache.poi.ddf.EscherContainerRecord) r10, (org.apache.poi.hssf.record.ObjRecord) r3)
            goto L_0x0113
        L_0x00fc:
            org.apache.poi.hssf.usermodel.HSSFPicture r11 = new org.apache.poi.hssf.usermodel.HSSFPicture
            r11.<init>((org.apache.poi.ddf.EscherContainerRecord) r10, (org.apache.poi.hssf.record.ObjRecord) r3)
            goto L_0x0113
        L_0x0102:
            org.apache.poi.hssf.usermodel.HSSFTextbox r11 = new org.apache.poi.hssf.usermodel.HSSFTextbox
            r11.<init>(r10, r3, r5)
            goto L_0x0113
        L_0x0108:
            org.apache.poi.hssf.usermodel.HSSFSimpleShape r11 = new org.apache.poi.hssf.usermodel.HSSFSimpleShape
            r11.<init>(r10, r3, r5)
            goto L_0x0113
        L_0x010e:
            org.apache.poi.hssf.usermodel.HSSFSimpleShape r11 = new org.apache.poi.hssf.usermodel.HSSFSimpleShape
            r11.<init>((org.apache.poi.ddf.EscherContainerRecord) r10, (org.apache.poi.hssf.record.ObjRecord) r3)
        L_0x0113:
            r12.addShape(r11)
            goto L_0x011f
        L_0x0117:
            org.apache.poi.util.RecordFormatException r10 = new org.apache.poi.util.RecordFormatException
            java.lang.String r11 = "EscherClientDataRecord can't be found."
            r10.<init>((java.lang.String) r11)
            throw r10
        L_0x011f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFShapeFactory.createShapeTree(org.apache.poi.ddf.EscherContainerRecord, org.apache.poi.hssf.record.EscherAggregate, org.apache.poi.hssf.usermodel.HSSFShapeContainer, org.apache.poi.poifs.filesystem.DirectoryNode):void");
    }

    /* renamed from: org.apache.poi.hssf.usermodel.HSSFShapeFactory$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ddf$EscherRecordTypes;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.poi.ddf.EscherRecordTypes[] r0 = org.apache.poi.ddf.EscherRecordTypes.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ddf$EscherRecordTypes = r0
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.CLIENT_DATA     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.CLIENT_TEXTBOX     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFShapeFactory.AnonymousClass1.<clinit>():void");
        }
    }

    private static boolean isEmbeddedObject(ObjRecord objRecord) {
        for (SubRecord subRecord : objRecord.getSubRecords()) {
            if (subRecord instanceof EmbeddedObjectRefSubRecord) {
                return true;
            }
        }
        return false;
    }
}
