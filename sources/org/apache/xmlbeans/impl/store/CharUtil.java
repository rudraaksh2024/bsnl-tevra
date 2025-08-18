package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.lang.ref.SoftReference;

public final class CharUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CHARUTIL_INITIAL_BUFSIZE = 32768;
    private static final int MAX_COPY = 64;
    private static final ThreadLocal<SoftReference<CharUtil>> tl_charUtil = ThreadLocal.withInitial(new CharUtil$$ExternalSyntheticLambda0());
    public int _cchSrc;
    private final int _charBufSize;
    private final CharIterator _charIter = new CharIterator();
    private char[] _currentBuffer;
    private int _currentOffset;
    public int _offSrc;

    public static boolean isWhiteSpace(char c) {
        return c == 9 || c == 10 || c == 13 || c == ' ';
    }

    public CharUtil(int i) {
        this._charBufSize = i;
    }

    public CharIterator getCharIterator(Object obj, int i, int i2) {
        this._charIter.init(obj, i, i2);
        return this._charIter;
    }

    public CharIterator getCharIterator(Object obj, int i, int i2, int i3) {
        this._charIter.init(obj, i, i2, i3);
        return this._charIter;
    }

    public static CharUtil getThreadLocalCharUtil() {
        ThreadLocal<SoftReference<CharUtil>> threadLocal = tl_charUtil;
        CharUtil charUtil = (CharUtil) threadLocal.get().get();
        if (charUtil != null) {
            return charUtil;
        }
        CharUtil charUtil2 = new CharUtil(32768);
        threadLocal.set(new SoftReference(charUtil2));
        return charUtil2;
    }

    public static void getString(StringBuffer stringBuffer, Object obj, int i, int i2) {
        if (i2 != 0) {
            if (obj instanceof char[]) {
                stringBuffer.append((char[]) obj, i, i2);
            } else if (obj instanceof String) {
                String str = (String) obj;
                if (i == 0 && i2 == str.length()) {
                    stringBuffer.append(str);
                } else {
                    stringBuffer.append(str, i, i2 + i);
                }
            } else {
                ((CharJoin) obj).getString(stringBuffer, i, i2);
            }
        }
    }

    public static void getChars(char[] cArr, int i, Object obj, int i2, int i3) {
        if (i3 != 0) {
            if (obj instanceof char[]) {
                System.arraycopy((char[]) obj, i2, cArr, i, i3);
            } else if (obj instanceof String) {
                ((String) obj).getChars(i2, i3 + i2, cArr, i);
            } else {
                ((CharJoin) obj).getChars(cArr, i, i2, i3);
            }
        }
    }

    public static String getString(Object obj, int i, int i2) {
        if (i2 == 0) {
            return "";
        }
        if (obj instanceof char[]) {
            return new String((char[]) obj, i, i2);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (i == 0 && i2 == str.length()) {
                return str;
            }
            return str.substring(i, i2 + i);
        }
        StringBuffer stringBuffer = new StringBuffer();
        ((CharJoin) obj).getString(stringBuffer, i, i2);
        return stringBuffer.toString();
    }

    public final boolean isWhiteSpace(Object obj, int i, int i2) {
        boolean z = true;
        if (i2 <= 0) {
            return true;
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            while (i2 > 0) {
                int i3 = i + 1;
                if (!isWhiteSpace(cArr[i])) {
                    return false;
                }
                i2--;
                i = i3;
            }
            return true;
        } else if (obj instanceof String) {
            String str = (String) obj;
            while (i2 > 0) {
                int i4 = i + 1;
                if (!isWhiteSpace(str.charAt(i))) {
                    return false;
                }
                i2--;
                i = i4;
            }
            return true;
        } else {
            this._charIter.init(obj, i, i2);
            while (true) {
                if (this._charIter.hasNext()) {
                    if (!isWhiteSpace(this._charIter.next())) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            this._charIter.release();
            return z;
        }
    }

    public Object stripLeft(Object obj, int i, int i2) {
        if (i2 > 0) {
            if (obj instanceof char[]) {
                char[] cArr = (char[]) obj;
                while (i2 > 0 && isWhiteSpace(cArr[i])) {
                    i2--;
                    i++;
                }
            } else if (obj instanceof String) {
                String str = (String) obj;
                while (i2 > 0 && isWhiteSpace(str.charAt(i))) {
                    i2--;
                    i++;
                }
            } else {
                this._charIter.init(obj, i, i2);
                int i3 = 0;
                while (this._charIter.hasNext() && isWhiteSpace(this._charIter.next())) {
                    i3++;
                }
                this._charIter.release();
                i += i3;
            }
        }
        if (i2 == 0) {
            this._offSrc = 0;
            this._cchSrc = 0;
            return null;
        }
        this._offSrc = i;
        this._cchSrc = i2;
        return obj;
    }

    public Object stripRight(Object obj, int i, int i2) {
        if (i2 > 0) {
            this._charIter.init(obj, i, i2, i2);
            while (this._charIter.hasPrev() && isWhiteSpace(this._charIter.prev())) {
                i2--;
            }
            this._charIter.release();
        }
        if (i2 == 0) {
            this._offSrc = 0;
            this._cchSrc = 0;
            return null;
        }
        this._offSrc = i;
        this._cchSrc = i2;
        return obj;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object insertChars(int r9, java.lang.Object r10, int r11, int r12, java.lang.Object r13, int r14, int r15) {
        /*
            r8 = this;
            if (r15 != 0) goto L_0x0007
            r8._cchSrc = r12
            r8._offSrc = r11
            return r10
        L_0x0007:
            if (r12 != 0) goto L_0x000e
            r8._cchSrc = r15
            r8._offSrc = r14
            return r13
        L_0x000e:
            int r0 = r12 + r15
            r8._cchSrc = r0
            r1 = 64
            if (r0 > r1) goto L_0x0037
            boolean r0 = r8.canAllocate(r0)
            if (r0 == 0) goto L_0x0037
            int r0 = r8._cchSrc
            char[] r0 = r8.allocate(r0)
            int r1 = r8._offSrc
            getChars(r0, r1, r10, r11, r9)
            int r1 = r8._offSrc
            int r1 = r1 + r9
            getChars(r0, r1, r13, r14, r15)
            int r8 = r8._offSrc
            int r8 = r8 + r9
            int r8 = r8 + r15
            int r11 = r11 + r9
            int r12 = r12 - r9
            getChars(r0, r8, r10, r11, r12)
            goto L_0x007c
        L_0x0037:
            r0 = 0
            r8._offSrc = r0
            if (r9 != 0) goto L_0x0049
            org.apache.xmlbeans.impl.store.CharUtil$CharJoin r9 = new org.apache.xmlbeans.impl.store.CharUtil$CharJoin
            r2 = r9
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r10
            r7 = r11
            r2.<init>(r3, r4, r5, r6, r7)
        L_0x0047:
            r0 = r9
            goto L_0x0070
        L_0x0049:
            if (r9 != r12) goto L_0x0057
            org.apache.xmlbeans.impl.store.CharUtil$CharJoin r9 = new org.apache.xmlbeans.impl.store.CharUtil$CharJoin
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r6 = r13
            r7 = r14
            r2.<init>(r3, r4, r5, r6, r7)
            goto L_0x0047
        L_0x0057:
            org.apache.xmlbeans.impl.store.CharUtil$CharJoin r12 = new org.apache.xmlbeans.impl.store.CharUtil$CharJoin
            r2 = r12
            r3 = r10
            r4 = r11
            r5 = r9
            r6 = r13
            r7 = r14
            r2.<init>(r3, r4, r5, r6, r7)
            org.apache.xmlbeans.impl.store.CharUtil$CharJoin r13 = new org.apache.xmlbeans.impl.store.CharUtil$CharJoin
            r4 = 0
            int r5 = r9 + r15
            int r7 = r11 + r9
            r2 = r13
            r3 = r12
            r6 = r10
            r2.<init>(r3, r4, r5, r6, r7)
            r0 = r13
        L_0x0070:
            int r9 = r0._depth
            if (r9 <= r1) goto L_0x007c
            int r9 = r8._offSrc
            int r10 = r8._cchSrc
            java.lang.Object r0 = r8.saveChars(r0, r9, r10)
        L_0x007c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.CharUtil.insertChars(int, java.lang.Object, int, int, java.lang.Object, int, int):java.lang.Object");
    }

    public Object removeChars(int i, int i2, Object obj, int i3, int i4) {
        int i5 = i4 - i2;
        this._cchSrc = i5;
        if (i5 == 0) {
            this._offSrc = 0;
            return null;
        } else if (i == 0) {
            this._offSrc = i3 + i2;
            return obj;
        } else if (i + i2 == i4) {
            this._offSrc = i3;
            return obj;
        } else if (i5 > 64 || !canAllocate(i5)) {
            CharJoin charJoin = new CharJoin(obj, i3, i, obj, i3 + i + i2);
            if (charJoin._depth > 64) {
                return saveChars(charJoin, 0, this._cchSrc);
            }
            this._offSrc = 0;
            return charJoin;
        } else {
            char[] allocate = allocate(i5);
            getChars(allocate, this._offSrc, obj, i3, i);
            getChars(allocate, this._offSrc + i, obj, i3 + i + i2, (i4 - i) - i2);
            return allocate;
        }
    }

    private boolean canAllocate(int i) {
        char[] cArr = this._currentBuffer;
        return cArr == null || cArr.length - this._currentOffset >= i;
    }

    private char[] allocate(int i) {
        if (this._currentBuffer == null) {
            this._currentBuffer = new char[Math.max(i, this._charBufSize)];
            this._currentOffset = 0;
        }
        int i2 = this._currentOffset;
        this._offSrc = i2;
        int min = Math.min(this._currentBuffer.length - i2, i);
        this._cchSrc = min;
        char[] cArr = this._currentBuffer;
        int i3 = this._currentOffset + min;
        this._currentOffset = i3;
        if (i3 == cArr.length) {
            this._currentBuffer = null;
            this._currentOffset = 0;
        }
        return cArr;
    }

    public Object saveChars(Object obj, int i, int i2) {
        return saveChars(obj, i, i2, (Object) null, 0, 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: char[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: org.apache.xmlbeans.impl.store.CharUtil$CharJoin} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: org.apache.xmlbeans.impl.store.CharUtil$CharJoin} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: org.apache.xmlbeans.impl.store.CharUtil$CharJoin} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: org.apache.xmlbeans.impl.store.CharUtil$CharJoin} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: org.apache.xmlbeans.impl.store.CharUtil$CharJoin} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: org.apache.xmlbeans.impl.store.CharUtil$CharJoin} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: org.apache.xmlbeans.impl.store.CharUtil$CharJoin} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v9, resolved type: org.apache.xmlbeans.impl.store.CharUtil$CharJoin} */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        if ((((r20 + r21) - r4._cchLeft) + r4._offRight) == r9) goto L_0x0039;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object saveChars(java.lang.Object r16, int r17, int r18, java.lang.Object r19, int r20, int r21) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r5 = r19
            char[] r8 = r15.allocate(r3)
            int r9 = r0._offSrc
            int r10 = r0._cchSrc
            getChars(r8, r9, r1, r2, r10)
            int r11 = r10 + r21
            r12 = 64
            r13 = 0
            if (r21 != 0) goto L_0x001d
            r14 = r8
            goto L_0x0053
        L_0x001d:
            if (r5 != r8) goto L_0x0024
            int r4 = r20 + r21
            if (r4 != r9) goto L_0x0024
            goto L_0x0039
        L_0x0024:
            boolean r4 = r5 instanceof org.apache.xmlbeans.impl.store.CharUtil.CharJoin
            if (r4 == 0) goto L_0x003d
            r4 = r5
            org.apache.xmlbeans.impl.store.CharUtil$CharJoin r4 = (org.apache.xmlbeans.impl.store.CharUtil.CharJoin) r4
            java.lang.Object r6 = r4._srcRight
            if (r6 != r8) goto L_0x003d
            int r6 = r20 + r21
            int r7 = r4._cchLeft
            int r6 = r6 - r7
            int r4 = r4._offRight
            int r6 = r6 + r4
            if (r6 != r9) goto L_0x003d
        L_0x0039:
            r9 = r20
            r14 = r5
            goto L_0x0053
        L_0x003d:
            org.apache.xmlbeans.impl.store.CharUtil$CharJoin r14 = new org.apache.xmlbeans.impl.store.CharUtil$CharJoin
            r4 = r14
            r5 = r19
            r6 = r20
            r7 = r21
            r4.<init>(r5, r6, r7, r8, r9)
            int r4 = r14._depth
            if (r4 <= r12) goto L_0x0052
            java.lang.Object r4 = r15.saveChars(r14, r13, r11)
            r14 = r4
        L_0x0052:
            r9 = r13
        L_0x0053:
            int r4 = r3 - r10
            if (r4 <= 0) goto L_0x007e
            char[] r5 = r15.allocate(r4)
            int r6 = r0._offSrc
            int r3 = r3 - r4
            int r2 = r2 + r3
            getChars(r5, r6, r1, r2, r4)
            org.apache.xmlbeans.impl.store.CharUtil$CharJoin r1 = new org.apache.xmlbeans.impl.store.CharUtil$CharJoin
            r16 = r1
            r17 = r14
            r18 = r9
            r19 = r11
            r20 = r5
            r21 = r6
            r16.<init>(r17, r18, r19, r20, r21)
            int r11 = r11 + r4
            int r2 = r1._depth
            if (r2 <= r12) goto L_0x007c
            java.lang.Object r1 = r15.saveChars(r1, r13, r11)
        L_0x007c:
            r14 = r1
            goto L_0x007f
        L_0x007e:
            r13 = r9
        L_0x007f:
            r0._offSrc = r13
            r0._cchSrc = r11
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.CharUtil.saveChars(java.lang.Object, int, int, java.lang.Object, int, int):java.lang.Object");
    }

    private static void dumpText(PrintStream printStream, String str) {
        printStream.print("\"");
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                break;
            }
            char charAt = str.charAt(i);
            if (i == 36) {
                printStream.print("...");
                break;
            }
            if (charAt == 9) {
                printStream.print("\\t");
            } else if (charAt == 10) {
                printStream.print("\\n");
            } else if (charAt == 12) {
                printStream.print("\\f");
            } else if (charAt == 13) {
                printStream.print("\\r");
            } else if (charAt != '\"') {
                printStream.print(charAt);
            } else {
                printStream.print("\\\"");
            }
            i++;
        }
        printStream.print("\"");
    }

    public static void dump(Object obj, int i, int i2) {
        dumpChars(System.out, obj, i, i2);
        System.out.println();
    }

    public static void dumpChars(PrintStream printStream, Object obj, int i, int i2) {
        int i3;
        int i4;
        printStream.print("off=" + i + ", cch=" + i2 + ", ");
        if (obj == null) {
            printStream.print("<null-src>");
        } else if (obj instanceof String) {
            String str = (String) obj;
            printStream.print("String");
            if (!(i == 0 && i2 == str.length()) && (i < 0 || i > str.length() || (i4 = i + i2) < 0 || i4 > str.length())) {
                printStream.print(" (Error)");
            } else {
                dumpText(printStream, str.substring(i, i2 + i));
            }
        } else if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            printStream.print("char[]");
            if (!(i == 0 && i2 == cArr.length) && (i < 0 || i > cArr.length || (i3 = i + i2) < 0 || i3 > cArr.length)) {
                printStream.print(" (Error)");
            } else {
                dumpText(printStream, new String(cArr, i, i2));
            }
        } else if (obj instanceof CharJoin) {
            printStream.print("CharJoin");
            ((CharJoin) obj).dumpChars(printStream, i, i2);
        } else {
            printStream.print("Unknown text source");
        }
    }

    public static boolean isValid(Object obj, int i, int i2) {
        if (i2 >= 0 && i >= 0) {
            if (obj == null) {
                return i == 0 && i2 == 0;
            }
            if (obj instanceof char[]) {
                char[] cArr = (char[]) obj;
                if (i > cArr.length || i + i2 > cArr.length) {
                    return false;
                }
                return true;
            } else if (obj instanceof String) {
                String str = (String) obj;
                if (i > str.length() || i + i2 > str.length()) {
                    return false;
                }
                return true;
            } else if (obj instanceof CharJoin) {
                return ((CharJoin) obj).isValid(i, i2);
            }
        }
        return false;
    }

    public static final class CharJoin {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final int MAX_DEPTH = 64;
        public final int _cchLeft;
        public final int _depth;
        public final int _offLeft;
        public final int _offRight;
        public final Object _srcLeft;
        public final Object _srcRight;

        static {
            Class<CharUtil> cls = CharUtil.class;
        }

        public CharJoin(Object obj, int i, int i2, Object obj2, int i3) {
            int i4;
            this._srcLeft = obj;
            this._offLeft = i;
            this._cchLeft = i2;
            this._srcRight = obj2;
            this._offRight = i3;
            int i5 = obj instanceof CharJoin ? ((CharJoin) obj)._depth : 0;
            if ((obj2 instanceof CharJoin) && (i4 = ((CharJoin) obj2)._depth) > i5) {
                i5 = i4;
            }
            this._depth = i5 + 1;
        }

        private int cchRight(int i, int i2) {
            return Math.max(0, (i2 - this._cchLeft) - i);
        }

        public int depth() {
            Object obj = this._srcLeft;
            int depth = obj instanceof CharJoin ? ((CharJoin) obj).depth() : 0;
            Object obj2 = this._srcRight;
            if (obj2 instanceof CharJoin) {
                depth = Math.max(((CharJoin) obj2).depth(), depth);
            }
            return depth + 1;
        }

        public boolean isValid(int i, int i2) {
            if (this._depth > 2) {
                return true;
            }
            if (i < 0 || i2 < 0 || !CharUtil.isValid(this._srcLeft, this._offLeft, this._cchLeft)) {
                return false;
            }
            return CharUtil.isValid(this._srcRight, this._offRight, cchRight(i, i2));
        }

        /* access modifiers changed from: private */
        public void getString(StringBuffer stringBuffer, int i, int i2) {
            int i3 = this._cchLeft;
            if (i < i3) {
                int min = Math.min(i3 - i, i2);
                CharUtil.getString(stringBuffer, this._srcLeft, this._offLeft + i, min);
                if (i2 > min) {
                    CharUtil.getString(stringBuffer, this._srcRight, this._offRight, i2 - min);
                    return;
                }
                return;
            }
            CharUtil.getString(stringBuffer, this._srcRight, (this._offRight + i) - i3, i2);
        }

        /* access modifiers changed from: private */
        public void getChars(char[] cArr, int i, int i2, int i3) {
            int i4 = this._cchLeft;
            if (i2 < i4) {
                int min = Math.min(i4 - i2, i3);
                CharUtil.getChars(cArr, i, this._srcLeft, this._offLeft + i2, min);
                if (i3 > min) {
                    CharUtil.getChars(cArr, i + min, this._srcRight, this._offRight, i3 - min);
                    return;
                }
                return;
            }
            CharUtil.getChars(cArr, i, this._srcRight, (this._offRight + i2) - i4, i3);
        }

        /* access modifiers changed from: private */
        public void dumpChars(PrintStream printStream, int i, int i2) {
            printStream.print("( ");
            CharUtil.dumpChars(printStream, this._srcLeft, this._offLeft, this._cchLeft);
            printStream.print(", ");
            CharUtil.dumpChars(printStream, this._srcRight, this._offRight, cchRight(i, i2));
            printStream.print(" )");
        }
    }

    public static final class CharIterator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int _cchRoot;
        private int _maxPos;
        private int _minPos;
        private int _offLeaf;
        private int _offRoot;
        private int _pos;
        private char[] _srcLeafChars;
        private String _srcLeafString;
        private Object _srcRoot;

        static {
            Class<CharUtil> cls = CharUtil.class;
        }

        public void init(Object obj, int i, int i2) {
            init(obj, i, i2, 0);
        }

        public void init(Object obj, int i, int i2, int i3) {
            release();
            this._srcRoot = obj;
            this._offRoot = i;
            this._cchRoot = i2;
            this._maxPos = -1;
            this._minPos = -1;
            movePos(i3);
        }

        public void release() {
            this._srcRoot = null;
            this._srcLeafString = null;
            this._srcLeafChars = null;
        }

        public boolean hasNext() {
            return this._pos < this._cchRoot;
        }

        public boolean hasPrev() {
            return this._pos > 0;
        }

        public char next() {
            char currentChar = currentChar();
            movePos(this._pos + 1);
            return currentChar;
        }

        public char prev() {
            movePos(this._pos - 1);
            return currentChar();
        }

        public void movePos(int i) {
            Object obj;
            if (i < this._minPos || i > this._maxPos) {
                Object obj2 = this._srcRoot;
                int i2 = this._offRoot;
                int i3 = i2 + i;
                int i4 = this._cchRoot;
                this._offLeaf = i2;
                while (obj2 instanceof CharJoin) {
                    CharJoin charJoin = (CharJoin) obj2;
                    if (i3 < charJoin._cchLeft) {
                        obj = charJoin._srcLeft;
                        this._offLeaf = charJoin._offLeft;
                        i3 += charJoin._offLeft;
                        i4 = charJoin._cchLeft;
                    } else {
                        obj = charJoin._srcRight;
                        this._offLeaf = charJoin._offRight;
                        i3 -= charJoin._cchLeft - charJoin._offRight;
                        i4 -= charJoin._cchLeft;
                    }
                    obj2 = obj;
                }
                int i5 = i - (i3 - this._offLeaf);
                this._minPos = i5;
                int i6 = i5 + i4;
                this._maxPos = i6;
                if (i < this._cchRoot) {
                    this._maxPos = i6 - 1;
                }
                this._srcLeafChars = null;
                this._srcLeafString = null;
                if (obj2 instanceof char[]) {
                    this._srcLeafChars = (char[]) obj2;
                } else {
                    this._srcLeafString = (String) obj2;
                }
            }
            this._pos = i;
        }

        private char currentChar() {
            int i = (this._offLeaf + this._pos) - this._minPos;
            char[] cArr = this._srcLeafChars;
            return cArr == null ? this._srcLeafString.charAt(i) : cArr[i];
        }
    }

    static /* synthetic */ SoftReference lambda$static$0() {
        return new SoftReference(new CharUtil(32768));
    }

    public static void clearThreadLocals() {
        tl_charUtil.remove();
    }
}
