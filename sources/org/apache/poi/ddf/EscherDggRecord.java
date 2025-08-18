package org.apache.poi.ddf;

import com.zaxxer.sparsebits.SparseBitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RecordFormatException;

public final class EscherDggRecord extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.DGG.typeID;
    private int field_1_shapeIdMax;
    private int field_3_numShapesSaved;
    private int field_4_drawingsSaved;
    private final List<FileIdCluster> field_5_fileIdClusters;
    private int maxDgId;

    public static class FileIdCluster implements GenericRecord {
        private int field_1_drawingGroupId;
        private int field_2_numShapeIdsUsed;

        public FileIdCluster(FileIdCluster fileIdCluster) {
            this.field_1_drawingGroupId = fileIdCluster.field_1_drawingGroupId;
            this.field_2_numShapeIdsUsed = fileIdCluster.field_2_numShapeIdsUsed;
        }

        public FileIdCluster(int i, int i2) {
            this.field_1_drawingGroupId = i;
            this.field_2_numShapeIdsUsed = i2;
        }

        public int getDrawingGroupId() {
            return this.field_1_drawingGroupId;
        }

        public int getNumShapeIdsUsed() {
            return this.field_2_numShapeIdsUsed;
        }

        /* access modifiers changed from: private */
        public void incrementUsedShapeId() {
            this.field_2_numShapeIdsUsed++;
        }

        /* access modifiers changed from: private */
        public static int compareFileIdCluster(FileIdCluster fileIdCluster, FileIdCluster fileIdCluster2) {
            int drawingGroupId = fileIdCluster.getDrawingGroupId() - fileIdCluster2.getDrawingGroupId();
            return drawingGroupId != 0 ? drawingGroupId : fileIdCluster2.getNumShapeIdsUsed() - fileIdCluster.getNumShapeIdsUsed();
        }

        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("drawingGroupId", new EscherDggRecord$FileIdCluster$$ExternalSyntheticLambda0(this), "numShapeIdUsed", new EscherDggRecord$FileIdCluster$$ExternalSyntheticLambda1(this));
        }
    }

    public EscherDggRecord() {
        this.field_5_fileIdClusters = new ArrayList();
    }

    public EscherDggRecord(EscherDggRecord escherDggRecord) {
        super(escherDggRecord);
        ArrayList arrayList = new ArrayList();
        this.field_5_fileIdClusters = arrayList;
        this.field_1_shapeIdMax = escherDggRecord.field_1_shapeIdMax;
        this.field_3_numShapesSaved = escherDggRecord.field_3_numShapesSaved;
        this.field_4_drawingsSaved = escherDggRecord.field_4_drawingsSaved;
        escherDggRecord.field_5_fileIdClusters.stream().map(new EscherDggRecord$$ExternalSyntheticLambda1()).forEach(new EscherDggRecord$$ExternalSyntheticLambda2(arrayList));
        this.maxDgId = escherDggRecord.maxDgId;
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        int i2 = i + 8;
        this.field_1_shapeIdMax = LittleEndian.getInt(bArr, i2 + 0);
        this.field_3_numShapesSaved = LittleEndian.getInt(bArr, i2 + 8);
        this.field_4_drawingsSaved = LittleEndian.getInt(bArr, i2 + 12);
        this.field_5_fileIdClusters.clear();
        int i3 = (readHeader - 16) / 8;
        int i4 = 16;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i2 + i4;
            int i7 = LittleEndian.getInt(bArr, i6);
            this.field_5_fileIdClusters.add(new FileIdCluster(i7, LittleEndian.getInt(bArr, i6 + 4)));
            this.maxDgId = Math.max(this.maxDgId, i7);
            i4 += 8;
        }
        int i8 = readHeader - i4;
        if (i8 == 0) {
            return i4 + 8;
        }
        throw new RecordFormatException("Expecting no remaining data but got " + i8 + " byte(s).");
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        int i2 = i + 2;
        LittleEndian.putShort(bArr, i2, getRecordId());
        int i3 = i2 + 2;
        LittleEndian.putInt(bArr, i3, getRecordSize() - 8);
        int i4 = i3 + 4;
        LittleEndian.putInt(bArr, i4, this.field_1_shapeIdMax);
        int i5 = i4 + 4;
        LittleEndian.putInt(bArr, i5, getNumIdClusters());
        int i6 = i5 + 4;
        LittleEndian.putInt(bArr, i6, this.field_3_numShapesSaved);
        int i7 = i6 + 4;
        LittleEndian.putInt(bArr, i7, this.field_4_drawingsSaved);
        int i8 = i7 + 4;
        for (FileIdCluster next : this.field_5_fileIdClusters) {
            LittleEndian.putInt(bArr, i8, next.getDrawingGroupId());
            int i9 = i8 + 4;
            LittleEndian.putInt(bArr, i9, next.getNumShapeIdsUsed());
            i8 = i9 + 4;
        }
        escherSerializationListener.afterRecordSerialize(i8, getRecordId(), getRecordSize(), this);
        return getRecordSize();
    }

    public int getRecordSize() {
        return (this.field_5_fileIdClusters.size() * 8) + 24;
    }

    public short getRecordId() {
        return RECORD_ID;
    }

    public String getRecordName() {
        return EscherRecordTypes.DGG.recordName;
    }

    public int getShapeIdMax() {
        return this.field_1_shapeIdMax;
    }

    public void setShapeIdMax(int i) {
        this.field_1_shapeIdMax = i;
    }

    public int getNumIdClusters() {
        if (this.field_5_fileIdClusters.isEmpty()) {
            return 0;
        }
        return this.field_5_fileIdClusters.size() + 1;
    }

    public int getNumShapesSaved() {
        return this.field_3_numShapesSaved;
    }

    public void setNumShapesSaved(int i) {
        this.field_3_numShapesSaved = i;
    }

    public int getDrawingsSaved() {
        return this.field_4_drawingsSaved;
    }

    public void setDrawingsSaved(int i) {
        this.field_4_drawingsSaved = i;
    }

    public int getMaxDrawingGroupId() {
        return this.maxDgId;
    }

    public FileIdCluster[] getFileIdClusters() {
        return (FileIdCluster[]) this.field_5_fileIdClusters.toArray(new FileIdCluster[0]);
    }

    public void setFileIdClusters(FileIdCluster[] fileIdClusterArr) {
        this.field_5_fileIdClusters.clear();
        if (fileIdClusterArr != null) {
            this.field_5_fileIdClusters.addAll(Arrays.asList(fileIdClusterArr));
        }
    }

    public FileIdCluster addCluster(int i, int i2) {
        return addCluster(i, i2, true);
    }

    public FileIdCluster addCluster(int i, int i2, boolean z) {
        FileIdCluster fileIdCluster = new FileIdCluster(i, i2);
        this.field_5_fileIdClusters.add(fileIdCluster);
        this.maxDgId = Math.min(this.maxDgId, i);
        if (z) {
            sortCluster();
        }
        return fileIdCluster;
    }

    private void sortCluster() {
        this.field_5_fileIdClusters.sort(new EscherDggRecord$$ExternalSyntheticLambda0());
    }

    public short findNewDrawingGroupId() {
        SparseBitSet sparseBitSet = new SparseBitSet();
        sparseBitSet.set(0);
        for (FileIdCluster drawingGroupId : this.field_5_fileIdClusters) {
            sparseBitSet.set(drawingGroupId.getDrawingGroupId());
        }
        return (short) sparseBitSet.nextClearBit(0);
    }

    public int allocateShapeId(EscherDgRecord escherDgRecord, boolean z) {
        FileIdCluster fileIdCluster;
        short drawingGroupId = escherDgRecord.getDrawingGroupId();
        this.field_3_numShapesSaved++;
        Iterator<FileIdCluster> it = this.field_5_fileIdClusters.iterator();
        int i = 1;
        while (true) {
            if (!it.hasNext()) {
                fileIdCluster = null;
                break;
            }
            fileIdCluster = it.next();
            if (fileIdCluster.getDrawingGroupId() == drawingGroupId && fileIdCluster.getNumShapeIdsUsed() < 1024) {
                break;
            }
            i++;
        }
        if (fileIdCluster == null) {
            fileIdCluster = addCluster(drawingGroupId, 0, z);
            this.maxDgId = Math.max(this.maxDgId, drawingGroupId);
        }
        int numShapeIdsUsed = (i * 1024) + fileIdCluster.getNumShapeIdsUsed();
        fileIdCluster.incrementUsedShapeId();
        escherDgRecord.setNumShapes(escherDgRecord.getNumShapes() + 1);
        escherDgRecord.setLastMSOSPID(numShapeIdsUsed);
        this.field_1_shapeIdMax = Math.max(this.field_1_shapeIdMax, numShapeIdsUsed + 1);
        return numShapeIdsUsed;
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.DGG;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherDggRecord$$ExternalSyntheticLambda3(this), "fileIdClusters", new EscherDggRecord$$ExternalSyntheticLambda4(this), "shapeIdMax", new EscherDggRecord$$ExternalSyntheticLambda5(this), "numIdClusters", new EscherDggRecord$$ExternalSyntheticLambda6(this), "numShapesSaved", new EscherDggRecord$$ExternalSyntheticLambda7(this), "drawingsSaved", new EscherDggRecord$$ExternalSyntheticLambda8(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ddf-EscherDggRecord  reason: not valid java name */
    public /* synthetic */ Object m1958lambda$getGenericProperties$1$orgapachepoiddfEscherDggRecord() {
        return super.getGenericProperties();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-ddf-EscherDggRecord  reason: not valid java name */
    public /* synthetic */ Object m1959lambda$getGenericProperties$2$orgapachepoiddfEscherDggRecord() {
        return this.field_5_fileIdClusters;
    }

    public EscherDggRecord copy() {
        return new EscherDggRecord(this);
    }
}
