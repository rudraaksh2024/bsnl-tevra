package org.apache.poi.hssf.record.common;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.cont.ContinuableRecordInput;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;

public class UnicodeString implements Comparable<UnicodeString>, Duplicatable, GenericRecord {
    private static final Logger LOG = LogManager.getLogger((Class<?>) UnicodeString.class);
    private static final BitField extBit = BitFieldFactory.getInstance(4);
    private static final BitField highByte = BitFieldFactory.getInstance(1);
    private static final BitField richText = BitFieldFactory.getInstance(8);
    private short field_1_charCount;
    private byte field_2_optionflags;
    private String field_3_string;
    private List<FormatRun> field_4_format_runs;
    private ExtRst field_5_ext_rst;

    private UnicodeString(UnicodeString unicodeString) {
        List<FormatRun> list;
        this.field_1_charCount = unicodeString.field_1_charCount;
        this.field_2_optionflags = unicodeString.field_2_optionflags;
        this.field_3_string = unicodeString.field_3_string;
        List<FormatRun> list2 = unicodeString.field_4_format_runs;
        ExtRst extRst = null;
        if (list2 == null) {
            list = null;
        } else {
            list = (List) list2.stream().map(new UnicodeString$$ExternalSyntheticLambda0()).collect(Collectors.toList());
        }
        this.field_4_format_runs = list;
        ExtRst extRst2 = unicodeString.field_5_ext_rst;
        this.field_5_ext_rst = extRst2 != null ? extRst2.copy() : extRst;
    }

    public UnicodeString(String str) {
        setString(str);
    }

    public UnicodeString(RecordInputStream recordInputStream) {
        this.field_1_charCount = recordInputStream.readShort();
        this.field_2_optionflags = recordInputStream.readByte();
        short readShort = isRichText() ? recordInputStream.readShort() : 0;
        int readInt = isExtendedText() ? recordInputStream.readInt() : 0;
        boolean z = (this.field_2_optionflags & 1) != 0 ? false : true;
        int charCount = getCharCount();
        this.field_3_string = z ? recordInputStream.readCompressedUnicode(charCount) : recordInputStream.readUnicodeLEString(charCount);
        if (isRichText() && readShort > 0) {
            this.field_4_format_runs = new ArrayList(readShort);
            for (int i = 0; i < readShort; i++) {
                this.field_4_format_runs.add(new FormatRun((LittleEndianInput) recordInputStream));
            }
        }
        if (isExtendedText() && readInt > 0) {
            ExtRst extRst = new ExtRst(new ContinuableRecordInput(recordInputStream), readInt);
            this.field_5_ext_rst = extRst;
            if (extRst.getDataSize() + 4 != readInt) {
                LOG.atWarn().log("ExtRst was supposed to be {} bytes long, but seems to actually be {}", Unbox.box(readInt), Unbox.box(this.field_5_ext_rst.getDataSize() + 4));
            }
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Short.valueOf(this.field_1_charCount), this.field_3_string});
    }

    public boolean equals(Object obj) {
        int size;
        if (!(obj instanceof UnicodeString)) {
            return false;
        }
        UnicodeString unicodeString = (UnicodeString) obj;
        if (this.field_1_charCount != unicodeString.field_1_charCount || this.field_2_optionflags != unicodeString.field_2_optionflags || !this.field_3_string.equals(unicodeString.field_3_string)) {
            return false;
        }
        List<FormatRun> list = this.field_4_format_runs;
        if (list == null) {
            if (unicodeString.field_4_format_runs == null) {
                return true;
            }
            return false;
        } else if (unicodeString.field_4_format_runs == null || (size = list.size()) != unicodeString.field_4_format_runs.size()) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (!this.field_4_format_runs.get(i).equals(unicodeString.field_4_format_runs.get(i))) {
                    return false;
                }
            }
            ExtRst extRst = this.field_5_ext_rst;
            if (extRst != null) {
                ExtRst extRst2 = unicodeString.field_5_ext_rst;
                if (extRst2 == null) {
                    return false;
                }
                return extRst.equals(extRst2);
            } else if (unicodeString.field_5_ext_rst == null) {
                return true;
            } else {
                return false;
            }
        }
    }

    public int getCharCount() {
        short s = this.field_1_charCount;
        return s < 0 ? s + 65536 : s;
    }

    public short getCharCountShort() {
        return this.field_1_charCount;
    }

    public void setCharCount(short s) {
        this.field_1_charCount = s;
    }

    public byte getOptionFlags() {
        return this.field_2_optionflags;
    }

    public void setOptionFlags(byte b) {
        this.field_2_optionflags = b;
    }

    public String getString() {
        return this.field_3_string;
    }

    public void setString(String str) {
        this.field_3_string = str;
        setCharCount((short) str.length());
        int length = str.length();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (str.charAt(i) > 255) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            this.field_2_optionflags = highByte.setByte(this.field_2_optionflags);
        } else {
            this.field_2_optionflags = highByte.clearByte(this.field_2_optionflags);
        }
    }

    public int getFormatRunCount() {
        List<FormatRun> list = this.field_4_format_runs;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public FormatRun getFormatRun(int i) {
        List<FormatRun> list = this.field_4_format_runs;
        if (list != null && i >= 0 && i < list.size()) {
            return this.field_4_format_runs.get(i);
        }
        return null;
    }

    private int findFormatRunAt(int i) {
        int size = this.field_4_format_runs.size();
        for (int i2 = 0; i2 < size; i2++) {
            FormatRun formatRun = this.field_4_format_runs.get(i2);
            if (formatRun._character == i) {
                return i2;
            }
            if (formatRun._character > i) {
                return -1;
            }
        }
        return -1;
    }

    public void addFormatRun(FormatRun formatRun) {
        if (this.field_4_format_runs == null) {
            this.field_4_format_runs = new ArrayList();
        }
        int findFormatRunAt = findFormatRunAt(formatRun._character);
        if (findFormatRunAt != -1) {
            this.field_4_format_runs.remove(findFormatRunAt);
        }
        this.field_4_format_runs.add(formatRun);
        Collections.sort(this.field_4_format_runs);
        this.field_2_optionflags = richText.setByte(this.field_2_optionflags);
    }

    public Iterator<FormatRun> formatIterator() {
        List<FormatRun> list = this.field_4_format_runs;
        if (list != null) {
            return list.iterator();
        }
        return null;
    }

    public Spliterator<FormatRun> formatSpliterator() {
        List<FormatRun> list = this.field_4_format_runs;
        if (list != null) {
            return list.spliterator();
        }
        return null;
    }

    public void removeFormatRun(FormatRun formatRun) {
        this.field_4_format_runs.remove(formatRun);
        if (this.field_4_format_runs.isEmpty()) {
            this.field_4_format_runs = null;
            this.field_2_optionflags = richText.clearByte(this.field_2_optionflags);
        }
    }

    public void clearFormatting() {
        this.field_4_format_runs = null;
        this.field_2_optionflags = richText.clearByte(this.field_2_optionflags);
    }

    public ExtRst getExtendedRst() {
        return this.field_5_ext_rst;
    }

    /* access modifiers changed from: package-private */
    public void setExtendedRst(ExtRst extRst) {
        if (extRst != null) {
            this.field_2_optionflags = extBit.setByte(this.field_2_optionflags);
        } else {
            this.field_2_optionflags = extBit.clearByte(this.field_2_optionflags);
        }
        this.field_5_ext_rst = extRst;
    }

    public void swapFontUse(short s, short s2) {
        List<FormatRun> list = this.field_4_format_runs;
        if (list != null) {
            for (FormatRun next : list) {
                if (next._fontIndex == s) {
                    next._fontIndex = s2;
                }
            }
        }
    }

    public String toString() {
        return getString();
    }

    public String getDebugInfo() {
        StringBuilder sb = new StringBuilder("[UNICODESTRING]\n    .charcount       = ");
        sb.append(Integer.toHexString(getCharCount())).append("\n    .optionflags     = ");
        sb.append(Integer.toHexString(getOptionFlags())).append("\n    .string          = ");
        sb.append(getString()).append("\n");
        if (this.field_4_format_runs != null) {
            for (int i = 0; i < this.field_4_format_runs.size(); i++) {
                sb.append("      .format_run").append(i).append("          = ").append(this.field_4_format_runs.get(i)).append("\n");
            }
        }
        if (this.field_5_ext_rst != null) {
            sb.append("    .field_5_ext_rst          = \n");
            sb.append(this.field_5_ext_rst).append("\n");
        }
        sb.append("[/UNICODESTRING]\n");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r0 = r5.field_4_format_runs;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void serialize(org.apache.poi.hssf.record.cont.ContinuableRecordOutput r6) {
        /*
            r5 = this;
            boolean r0 = r5.isRichText()
            r1 = 0
            if (r0 == 0) goto L_0x0010
            java.util.List<org.apache.poi.hssf.record.common.FormatRun> r0 = r5.field_4_format_runs
            if (r0 == 0) goto L_0x0010
            int r0 = r0.size()
            goto L_0x0011
        L_0x0010:
            r0 = r1
        L_0x0011:
            boolean r2 = r5.isExtendedText()
            r3 = 4
            if (r2 == 0) goto L_0x0022
            org.apache.poi.hssf.record.common.ExtRst r2 = r5.field_5_ext_rst
            if (r2 == 0) goto L_0x0022
            int r2 = r2.getDataSize()
            int r2 = r2 + r3
            goto L_0x0023
        L_0x0022:
            r2 = r1
        L_0x0023:
            java.lang.String r4 = r5.field_3_string
            r6.writeString(r4, r0, r2)
            if (r0 <= 0) goto L_0x0043
        L_0x002a:
            if (r1 >= r0) goto L_0x0043
            int r4 = r6.getAvailableSpace()
            if (r4 >= r3) goto L_0x0035
            r6.writeContinue()
        L_0x0035:
            java.util.List<org.apache.poi.hssf.record.common.FormatRun> r4 = r5.field_4_format_runs
            java.lang.Object r4 = r4.get(r1)
            org.apache.poi.hssf.record.common.FormatRun r4 = (org.apache.poi.hssf.record.common.FormatRun) r4
            r4.serialize(r6)
            int r1 = r1 + 1
            goto L_0x002a
        L_0x0043:
            if (r2 <= 0) goto L_0x004c
            org.apache.poi.hssf.record.common.ExtRst r5 = r5.field_5_ext_rst
            if (r5 == 0) goto L_0x004c
            r5.serialize(r6)
        L_0x004c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.common.UnicodeString.serialize(org.apache.poi.hssf.record.cont.ContinuableRecordOutput):void");
    }

    public int compareTo(UnicodeString unicodeString) {
        int compareTo = getString().compareTo(unicodeString.getString());
        if (compareTo != 0) {
            return compareTo;
        }
        List<FormatRun> list = this.field_4_format_runs;
        if (list == null) {
            if (unicodeString.field_4_format_runs == null) {
                return 0;
            }
            return 1;
        } else if (unicodeString.field_4_format_runs == null) {
            return -1;
        } else {
            int size = list.size();
            if (size != unicodeString.field_4_format_runs.size()) {
                return size - unicodeString.field_4_format_runs.size();
            }
            for (int i = 0; i < size; i++) {
                int compareTo2 = this.field_4_format_runs.get(i).compareTo(unicodeString.field_4_format_runs.get(i));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
            }
            ExtRst extRst = this.field_5_ext_rst;
            if (extRst != null) {
                ExtRst extRst2 = unicodeString.field_5_ext_rst;
                if (extRst2 == null) {
                    return -1;
                }
                return extRst.compareTo(extRst2);
            } else if (unicodeString.field_5_ext_rst == null) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    private boolean isRichText() {
        return richText.isSet(getOptionFlags());
    }

    private boolean isExtendedText() {
        return extBit.isSet(getOptionFlags());
    }

    public UnicodeString copy() {
        return new UnicodeString(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("charCount", new UnicodeString$$ExternalSyntheticLambda1(this), "optionFlags", new UnicodeString$$ExternalSyntheticLambda2(this), TypedValues.Custom.S_STRING, new UnicodeString$$ExternalSyntheticLambda3(this), "formatRuns", new UnicodeString$$ExternalSyntheticLambda4(this), "extendedRst", new UnicodeString$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-common-UnicodeString  reason: not valid java name */
    public /* synthetic */ Object m2165lambda$getGenericProperties$0$orgapachepoihssfrecordcommonUnicodeString() {
        return this.field_4_format_runs;
    }
}
