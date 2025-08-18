package org.apache.poi.hssf.record.common;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.StringUtil;

@Internal
public class ExtRst implements Comparable<ExtRst>, GenericRecord {
    private static final Logger LOG = LogManager.getLogger((Class<?>) ExtRst.class);
    private byte[] extraData;
    private short formattingFontIndex;
    private short formattingOptions;
    private int numberOfRuns;
    private PhRun[] phRuns;
    private String phoneticText;
    private short reserved;

    protected ExtRst() {
        populateEmpty();
    }

    protected ExtRst(ExtRst extRst) {
        this();
        this.reserved = extRst.reserved;
        this.formattingFontIndex = extRst.formattingFontIndex;
        this.formattingOptions = extRst.formattingOptions;
        this.numberOfRuns = extRst.numberOfRuns;
        this.phoneticText = extRst.phoneticText;
        PhRun[] phRunArr = extRst.phRuns;
        this.phRuns = phRunArr == null ? null : (PhRun[]) Stream.of(phRunArr).map(new ExtRst$$ExternalSyntheticLambda7()).toArray(new ExtRst$$ExternalSyntheticLambda8());
    }

    static /* synthetic */ PhRun[] lambda$new$0(int i) {
        return new PhRun[i];
    }

    protected ExtRst(LittleEndianInput littleEndianInput, int i) {
        short readShort = littleEndianInput.readShort();
        this.reserved = readShort;
        if (readShort == -1) {
            populateEmpty();
            return;
        }
        int i2 = 0;
        if (readShort != 1) {
            LOG.atWarn().log("ExtRst has wrong magic marker, expecting 1 but found {} - ignoring", (Object) Unbox.box(this.reserved));
            while (i2 < i - 2) {
                littleEndianInput.readByte();
                i2++;
            }
            populateEmpty();
            return;
        }
        short readShort2 = littleEndianInput.readShort();
        this.formattingFontIndex = littleEndianInput.readShort();
        this.formattingOptions = littleEndianInput.readShort();
        this.numberOfRuns = littleEndianInput.readUShort();
        short readShort3 = littleEndianInput.readShort();
        short readShort4 = littleEndianInput.readShort();
        if (readShort3 == 0 && readShort4 > 0) {
            readShort4 = 0;
        }
        if (readShort3 == readShort4) {
            String readUnicodeLE = StringUtil.readUnicodeLE(littleEndianInput, readShort3);
            this.phoneticText = readUnicodeLE;
            int length = ((readShort2 - 4) - 6) - (readUnicodeLE.length() * 2);
            int i3 = length / 6;
            this.phRuns = new PhRun[i3];
            int i4 = 0;
            while (true) {
                PhRun[] phRunArr = this.phRuns;
                if (i4 >= phRunArr.length) {
                    break;
                }
                phRunArr[i4] = new PhRun(littleEndianInput);
                i4++;
            }
            int i5 = length - (i3 * 6);
            if (i5 < 0) {
                LOG.atWarn().log("ExtRst overran by {} bytes", (Object) Unbox.box(-i5));
                i5 = 0;
            }
            this.extraData = IOUtils.safelyAllocate((long) i5, HSSFWorkbook.getMaxRecordLength());
            while (true) {
                byte[] bArr = this.extraData;
                if (i2 < bArr.length) {
                    bArr[i2] = littleEndianInput.readByte();
                    i2++;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalStateException("The two length fields of the Phonetic Text don't agree! " + readShort3 + " vs " + readShort4);
        }
    }

    private void populateEmpty() {
        this.reserved = 1;
        this.phoneticText = "";
        this.phRuns = new PhRun[0];
        this.extraData = new byte[0];
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this.phoneticText.length() * 2) + 10 + (this.phRuns.length * 6) + this.extraData.length;
    }

    /* access modifiers changed from: protected */
    public void serialize(ContinuableRecordOutput continuableRecordOutput) {
        int dataSize = getDataSize();
        continuableRecordOutput.writeContinueIfRequired(8);
        continuableRecordOutput.writeShort(this.reserved);
        continuableRecordOutput.writeShort(dataSize);
        continuableRecordOutput.writeShort(this.formattingFontIndex);
        continuableRecordOutput.writeShort(this.formattingOptions);
        continuableRecordOutput.writeContinueIfRequired(6);
        continuableRecordOutput.writeShort(this.numberOfRuns);
        continuableRecordOutput.writeShort(this.phoneticText.length());
        continuableRecordOutput.writeShort(this.phoneticText.length());
        continuableRecordOutput.writeContinueIfRequired(this.phoneticText.length() * 2);
        StringUtil.putUnicodeLE(this.phoneticText, continuableRecordOutput);
        for (PhRun serialize : this.phRuns) {
            serialize.serialize(continuableRecordOutput);
        }
        continuableRecordOutput.write(this.extraData);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof ExtRst) && compareTo((ExtRst) obj) == 0) {
            return true;
        }
        return false;
    }

    public int compareTo(ExtRst extRst) {
        int i = this.reserved - extRst.reserved;
        if (i != 0) {
            return i;
        }
        int i2 = this.formattingFontIndex - extRst.formattingFontIndex;
        if (i2 != 0) {
            return i2;
        }
        int i3 = this.formattingOptions - extRst.formattingOptions;
        if (i3 != 0) {
            return i3;
        }
        int i4 = this.numberOfRuns - extRst.numberOfRuns;
        if (i4 != 0) {
            return i4;
        }
        int compareTo = this.phoneticText.compareTo(extRst.phoneticText);
        if (compareTo != 0) {
            return compareTo;
        }
        int length = this.phRuns.length - extRst.phRuns.length;
        if (length != 0) {
            return length;
        }
        int i5 = 0;
        while (true) {
            PhRun[] phRunArr = this.phRuns;
            if (i5 >= phRunArr.length) {
                return Arrays.hashCode(this.extraData) - Arrays.hashCode(extRst.extraData);
            }
            int i6 = phRunArr[i5].phoneticTextFirstCharacterOffset - extRst.phRuns[i5].phoneticTextFirstCharacterOffset;
            if (i6 != 0) {
                return i6;
            }
            int i7 = this.phRuns[i5].realTextFirstCharacterOffset - extRst.phRuns[i5].realTextFirstCharacterOffset;
            if (i7 != 0) {
                return i7;
            }
            int i8 = this.phRuns[i5].realTextLength - extRst.phRuns[i5].realTextLength;
            if (i8 != 0) {
                return i8;
            }
            i5++;
        }
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{Short.valueOf(this.reserved), Short.valueOf(this.formattingFontIndex), Short.valueOf(this.formattingOptions), Integer.valueOf(this.numberOfRuns), this.phoneticText, this.phRuns});
    }

    public ExtRst copy() {
        return new ExtRst(this);
    }

    public short getFormattingFontIndex() {
        return this.formattingFontIndex;
    }

    public short getFormattingOptions() {
        return this.formattingOptions;
    }

    public int getNumberOfRuns() {
        return this.numberOfRuns;
    }

    public String getPhoneticText() {
        return this.phoneticText;
    }

    public PhRun[] getPhRuns() {
        return this.phRuns;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new ExtRst$$ExternalSyntheticLambda0(this), "formattingFontIndex", new ExtRst$$ExternalSyntheticLambda1(this), "formattingOptions", new ExtRst$$ExternalSyntheticLambda2(this), "numberOfRuns", new ExtRst$$ExternalSyntheticLambda3(this), "phoneticText", new ExtRst$$ExternalSyntheticLambda4(this), "phRuns", new ExtRst$$ExternalSyntheticLambda5(this), "extraData", new ExtRst$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-common-ExtRst  reason: not valid java name */
    public /* synthetic */ Object m2161lambda$getGenericProperties$1$orgapachepoihssfrecordcommonExtRst() {
        return Short.valueOf(this.reserved);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-common-ExtRst  reason: not valid java name */
    public /* synthetic */ Object m2162lambda$getGenericProperties$2$orgapachepoihssfrecordcommonExtRst() {
        return this.extraData;
    }
}
