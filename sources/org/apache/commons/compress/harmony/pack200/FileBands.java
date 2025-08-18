package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;
import kotlin.UByte;
import org.apache.commons.compress.harmony.pack200.Archive;
import org.objectweb.asm.ClassReader;

public class FileBands extends BandSet {
    private final CpBands cpBands;
    private final List fileList;
    private final CPUTF8[] fileName;
    private final byte[][] file_bits;
    private final int[] file_modtime;
    private int[] file_name;
    private final int[] file_options;
    private final long[] file_size;
    private final PackingOptions options;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileBands(CpBands cpBands2, SegmentHeader segmentHeader, PackingOptions packingOptions, Archive.SegmentUnit segmentUnit, int i) {
        super(i, segmentHeader);
        CpBands cpBands3 = cpBands2;
        PackingOptions packingOptions2 = packingOptions;
        List fileList2 = segmentUnit.getFileList();
        this.fileList = fileList2;
        this.options = packingOptions2;
        this.cpBands = cpBands3;
        int size = fileList2.size();
        this.fileName = new CPUTF8[size];
        this.file_modtime = new int[size];
        this.file_size = new long[size];
        this.file_options = new int[size];
        this.file_bits = new byte[size][];
        int archive_modtime = segmentHeader.getArchive_modtime();
        HashSet hashSet = new HashSet();
        for (ClassReader className : segmentUnit.getClassList()) {
            hashSet.add(className.getClassName());
        }
        CPUTF8 cPUtf8 = cpBands3.getCPUtf8("");
        boolean z = !"keep".equals(packingOptions.getModificationTime());
        int i2 = 0;
        int i3 = Integer.MIN_VALUE;
        int i4 = 0;
        while (i4 < size) {
            Archive.PackingFile packingFile = (Archive.PackingFile) this.fileList.get(i4);
            String name = packingFile.getName();
            if (!name.endsWith(".class") || packingOptions2.isPassFile(name)) {
                this.fileName[i4] = cpBands3.getCPUtf8(name);
            } else {
                int[] iArr = this.file_options;
                iArr[i4] = iArr[i4] | 2;
                if (hashSet.contains(name.substring(i2, name.length() - 6))) {
                    this.fileName[i4] = cPUtf8;
                } else {
                    this.fileName[i4] = cpBands3.getCPUtf8(name);
                }
            }
            if (packingOptions.isKeepDeflateHint() && packingFile.isDefalteHint()) {
                int[] iArr2 = this.file_options;
                iArr2[i4] = iArr2[i4] | 1;
            }
            this.file_size[i4] = (long) packingFile.getContents().length;
            int modtime = (int) (((packingFile.getModtime() + ((long) TimeZone.getDefault().getRawOffset())) / 1000) - ((long) archive_modtime));
            this.file_modtime[i4] = modtime;
            i3 = i3;
            if (z && i3 < modtime) {
                i3 = modtime;
            }
            this.file_bits[i4] = packingFile.getContents();
            i4++;
            i2 = 0;
        }
        if (z) {
            for (int i5 = 0; i5 < size; i5++) {
                this.file_modtime[i5] = i3;
            }
        }
    }

    public void finaliseBands() {
        this.file_name = new int[this.fileName.length];
        for (int i = 0; i < this.file_name.length; i++) {
            if (this.fileName[i].equals(this.cpBands.getCPUtf8(""))) {
                String name = ((Archive.PackingFile) this.fileList.get(i)).getName();
                if (this.options.isPassFile(name)) {
                    this.fileName[i] = this.cpBands.getCPUtf8(name);
                    int[] iArr = this.file_options;
                    iArr[i] = iArr[i] & -3;
                }
            }
            this.file_name[i] = this.fileName[i].getIndex();
        }
    }

    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing file bands...");
        byte[] encodeBandInt = encodeBandInt("file_name", this.file_name, Codec.UNSIGNED5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from file_name[" + this.file_name.length + "]");
        byte[] encodeFlags = encodeFlags("file_size", this.file_size, Codec.UNSIGNED5, Codec.UNSIGNED5, this.segmentHeader.have_file_size_hi());
        outputStream.write(encodeFlags);
        PackingUtils.log("Wrote " + encodeFlags.length + " bytes from file_size[" + this.file_size.length + "]");
        if (this.segmentHeader.have_file_modtime()) {
            byte[] encodeBandInt2 = encodeBandInt("file_modtime", this.file_modtime, Codec.DELTA5);
            outputStream.write(encodeBandInt2);
            PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from file_modtime[" + this.file_modtime.length + "]");
        }
        if (this.segmentHeader.have_file_options()) {
            byte[] encodeBandInt3 = encodeBandInt("file_options", this.file_options, Codec.UNSIGNED5);
            outputStream.write(encodeBandInt3);
            PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from file_options[" + this.file_options.length + "]");
        }
        byte[] encodeBandInt4 = encodeBandInt("file_bits", flatten(this.file_bits), Codec.BYTE1);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from file_bits[" + this.file_bits.length + "]");
    }

    private int[] flatten(byte[][] bArr) {
        int i = 0;
        for (byte[] length : bArr) {
            i += length.length;
        }
        int[] iArr = new int[i];
        int i2 = 0;
        for (byte[] bArr2 : bArr) {
            int i3 = 0;
            while (true) {
                if (i3 >= bArr2.length) {
                    break;
                }
                iArr[i2] = bArr2[i3] & UByte.MAX_VALUE;
                i3++;
                i2++;
            }
        }
        return iArr;
    }
}
