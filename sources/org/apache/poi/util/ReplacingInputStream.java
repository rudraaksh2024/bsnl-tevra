package org.apache.poi.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Internal
public class ReplacingInputStream extends FilterInputStream {
    final int[] buf;
    private int matchedIndex;
    private final byte[] pattern;
    private int replacedIndex;
    private final byte[] replacement;
    private State state;
    private int unbufferIndex;

    private enum State {
        NOT_MATCHED,
        MATCHING,
        REPLACING,
        UNBUFFER
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ReplacingInputStream(InputStream inputStream, String str, String str2) {
        this(inputStream, str.getBytes(StandardCharsets.UTF_8), str2 == null ? null : str2.getBytes(StandardCharsets.UTF_8));
    }

    public ReplacingInputStream(InputStream inputStream, byte[] bArr, byte[] bArr2) {
        super(inputStream);
        this.state = State.NOT_MATCHED;
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("pattern length should be > 0");
        }
        this.pattern = bArr;
        this.replacement = bArr2;
        this.buf = new int[bArr.length];
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        bArr.getClass();
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            int read = read();
            if (read == -1) {
                return -1;
            }
            bArr[i] = (byte) read;
            int i3 = 1;
            while (i3 < i2) {
                int read2 = read();
                if (read2 == -1) {
                    break;
                }
                bArr[i + i3] = (byte) read2;
                i3++;
            }
            return i3;
        }
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    /* renamed from: org.apache.poi.util.ReplacingInputStream$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$util$ReplacingInputStream$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.util.ReplacingInputStream$State[] r0 = org.apache.poi.util.ReplacingInputStream.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$util$ReplacingInputStream$State = r0
                org.apache.poi.util.ReplacingInputStream$State r1 = org.apache.poi.util.ReplacingInputStream.State.NOT_MATCHED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$util$ReplacingInputStream$State     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.util.ReplacingInputStream$State r1 = org.apache.poi.util.ReplacingInputStream.State.MATCHING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$util$ReplacingInputStream$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.util.ReplacingInputStream$State r1 = org.apache.poi.util.ReplacingInputStream.State.REPLACING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$util$ReplacingInputStream$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.util.ReplacingInputStream$State r1 = org.apache.poi.util.ReplacingInputStream.State.UNBUFFER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.ReplacingInputStream.AnonymousClass1.<clinit>():void");
        }
    }

    public int read() throws IOException {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$util$ReplacingInputStream$State[this.state.ordinal()];
        if (i == 2) {
            int read = super.read();
            byte[] bArr = this.pattern;
            int i2 = this.matchedIndex;
            if (bArr[i2] == read) {
                int[] iArr = this.buf;
                int i3 = i2 + 1;
                this.matchedIndex = i3;
                iArr[i2] = read;
                if (i3 == bArr.length) {
                    byte[] bArr2 = this.replacement;
                    if (bArr2 == null || bArr2.length == 0) {
                        this.state = State.NOT_MATCHED;
                        this.matchedIndex = 0;
                    } else {
                        this.state = State.REPLACING;
                        this.replacedIndex = 0;
                    }
                }
            } else {
                int[] iArr2 = this.buf;
                this.matchedIndex = i2 + 1;
                iArr2[i2] = read;
                this.state = State.UNBUFFER;
                this.unbufferIndex = 0;
            }
            return read();
        } else if (i == 3) {
            byte[] bArr3 = this.replacement;
            int i4 = this.replacedIndex;
            int i5 = i4 + 1;
            this.replacedIndex = i5;
            byte b = bArr3[i4];
            if (i5 == bArr3.length) {
                this.state = State.NOT_MATCHED;
                this.replacedIndex = 0;
            }
            return b;
        } else if (i != 4) {
            int read2 = super.read();
            if (this.pattern[0] != read2) {
                return read2;
            }
            Arrays.fill(this.buf, 0);
            int[] iArr3 = this.buf;
            this.matchedIndex = 0 + 1;
            iArr3[0] = read2;
            if (this.pattern.length == 1) {
                this.state = State.REPLACING;
                this.replacedIndex = 0;
            } else {
                this.state = State.MATCHING;
            }
            return read();
        } else {
            int[] iArr4 = this.buf;
            int i6 = this.unbufferIndex;
            int i7 = i6 + 1;
            this.unbufferIndex = i7;
            int i8 = iArr4[i6];
            if (i7 == this.matchedIndex) {
                this.state = State.NOT_MATCHED;
                this.matchedIndex = 0;
            }
            return i8;
        }
    }

    public String toString() {
        return this.state.name() + " " + this.matchedIndex + " " + this.replacedIndex + " " + this.unbufferIndex;
    }
}
