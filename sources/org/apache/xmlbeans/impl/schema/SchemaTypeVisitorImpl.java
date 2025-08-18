package org.apache.xmlbeans.impl.schema;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;

public class SchemaTypeVisitorImpl implements TypeStoreVisitor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final boolean CHECK_VALIDITY = false;
    static final boolean PROBE_VALIDITY = true;
    private boolean _isValid;
    private SchemaParticle _matchedParticle;
    private VisitorState[] _rollback;
    private int _rollbackIndex;
    int _rollbackSize;
    private VisitorState[] _stack;
    int _stackSize;
    private VisitorState _top;

    public SchemaTypeVisitorImpl(SchemaParticle schemaParticle) {
        init(schemaParticle);
    }

    public SchemaTypeVisitorImpl() {
    }

    public void init(SchemaParticle schemaParticle) {
        if (this._stack == null) {
            this._stack = expand((VisitorState[]) null);
        }
        if (this._rollback == null) {
            this._rollback = expand((VisitorState[]) null);
        }
        this._stackSize = 0;
        this._rollbackSize = 0;
        if (schemaParticle != null) {
            push(schemaParticle);
            this._rollbackIndex = 1;
        }
    }

    public VisitorState[] expand(VisitorState[] visitorStateArr) {
        int length = visitorStateArr == null ? 4 : visitorStateArr.length * 2;
        VisitorState[] visitorStateArr2 = new VisitorState[length];
        int i = 0;
        if (visitorStateArr != null) {
            System.arraycopy(visitorStateArr, 0, visitorStateArr2, 0, visitorStateArr.length);
        }
        if (visitorStateArr != null) {
            i = visitorStateArr.length;
        }
        while (i < length) {
            visitorStateArr2[i] = new VisitorState();
            i++;
        }
        return visitorStateArr2;
    }

    private static class VisitorState {
        int _childCount;
        int _curCount;
        int _curMax;
        int _curMin;
        SchemaParticle _curPart;
        int _processedChildCount;
        boolean[] _seen;

        private VisitorState() {
        }

        public void copy(VisitorState visitorState) {
            this._curPart = visitorState._curPart;
            this._curCount = visitorState._curCount;
            this._curMin = visitorState._curMin;
            this._curMax = visitorState._curMax;
            this._processedChildCount = visitorState._processedChildCount;
            this._childCount = visitorState._childCount;
            boolean[] zArr = visitorState._seen;
            if (zArr != null) {
                boolean[] zArr2 = new boolean[zArr.length];
                this._seen = zArr2;
                boolean[] zArr3 = visitorState._seen;
                System.arraycopy(zArr3, 0, zArr2, 0, zArr3.length);
            }
        }

        public void init(SchemaParticle schemaParticle) {
            this._curPart = schemaParticle;
            this._curMin = schemaParticle.getIntMinOccurs();
            this._curMax = schemaParticle.getIntMaxOccurs();
            this._curCount = 0;
            this._processedChildCount = 0;
            this._childCount = schemaParticle.countOfParticleChild();
            this._seen = schemaParticle.getParticleType() == 1 ? new boolean[this._childCount] : null;
        }
    }

    /* access modifiers changed from: package-private */
    public VisitorState topRef() {
        return this._stack[this._stackSize - 1];
    }

    /* access modifiers changed from: package-private */
    public void saveCopy(VisitorState visitorState) {
        VisitorState[] visitorStateArr = this._rollback;
        if (visitorStateArr.length == this._rollbackSize) {
            this._rollback = expand(visitorStateArr);
        }
        this._rollback[this._rollbackSize].copy(visitorState);
        this._rollbackSize++;
    }

    /* access modifiers changed from: package-private */
    public void addParticle(SchemaParticle schemaParticle) {
        VisitorState[] visitorStateArr = this._stack;
        if (visitorStateArr.length == this._stackSize) {
            this._stack = expand(visitorStateArr);
        }
        this._stack[this._stackSize].init(schemaParticle);
        this._stackSize++;
    }

    /* access modifiers changed from: package-private */
    public boolean prepare() {
        if (this._rollbackIndex == 0) {
            this._top = null;
            return false;
        }
        VisitorState visitorState = topRef();
        this._top = visitorState;
        saveCopy(visitorState);
        this._rollbackIndex = this._stackSize - 1;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void push(SchemaParticle schemaParticle) {
        addParticle(schemaParticle);
        this._top = topRef();
    }

    /* access modifiers changed from: package-private */
    public boolean pop() {
        int i = this._stackSize - 1;
        this._stackSize = i;
        if (i <= this._rollbackIndex) {
            return prepare();
        }
        this._top = topRef();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void commit() {
        this._top = null;
        this._rollbackIndex = this._stackSize;
        this._rollbackSize = 0;
    }

    /* access modifiers changed from: package-private */
    public void rollback() {
        while (true) {
            int i = this._rollbackSize;
            if (i > 0) {
                int i2 = i - 1;
                this._rollbackSize = i2;
                VisitorState[] visitorStateArr = this._stack;
                int i3 = this._rollbackIndex;
                VisitorState visitorState = visitorStateArr[i3];
                VisitorState[] visitorStateArr2 = this._rollback;
                visitorStateArr[i3] = visitorStateArr2[i2];
                visitorStateArr2[i2] = visitorState;
                this._rollbackIndex = i3 + 1;
            } else {
                this._stackSize = this._rollbackIndex;
                this._top = null;
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean notValid() {
        this._isValid = false;
        this._matchedParticle = null;
        rollback();
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean ok(SchemaParticle schemaParticle, boolean z) {
        if (!z) {
            this._matchedParticle = schemaParticle;
            commit();
            return true;
        }
        rollback();
        return true;
    }

    public boolean visit(QName qName) {
        return visit(qName, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        r2 = r8._top._curPart.getParticleType();
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        if (r2 == 1) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
        if (r2 == 2) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004f, code lost:
        if (r2 == 3) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0052, code lost:
        if (r2 == 4) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005d, code lost:
        if (r8._top._curPart.canStartWithElement(r9) != false) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0067, code lost:
        if (r8._top._curCount >= r8._top._curMin) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006d, code lost:
        return notValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006e, code lost:
        r8._top._curCount++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007d, code lost:
        return ok(r8._top._curPart, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0086, code lost:
        if (r8._top._curPart.canStartWithElement(r9) != false) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0090, code lost:
        if (r8._top._curCount >= r8._top._curMin) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0096, code lost:
        return notValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0097, code lost:
        r8._top._curCount++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a6, code lost:
        return ok(r8._top._curPart, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a7, code lost:
        r2 = r8._top._processedChildCount;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00af, code lost:
        if (r2 >= r8._top._childCount) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b1, code lost:
        r5 = r8._top._curPart.getParticleChild(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bd, code lost:
        if (r5.canStartWithElement(r9) == false) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00bf, code lost:
        r8._top._processedChildCount = r2 + 1;
        push(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ce, code lost:
        if (r5.isSkippable() != false) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d4, code lost:
        if (r8._top._processedChildCount != 0) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00de, code lost:
        if (r8._top._curCount >= r8._top._curMin) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e5, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e8, code lost:
        r8._top._curCount++;
        r8._top._processedChildCount = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00f9, code lost:
        if (r3 >= r8._top._childCount) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00fb, code lost:
        r2 = r8._top._curPart.getParticleChild(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0107, code lost:
        if (r2.canStartWithElement(r9) == false) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0109, code lost:
        r8._top._curCount++;
        push(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0115, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0120, code lost:
        if (r8._top._curCount >= r8._top._curMin) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x012a, code lost:
        if (r8._top._curPart.isSkippable() != false) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0130, code lost:
        return notValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0131, code lost:
        r2 = r8._top._processedChildCount;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x013a, code lost:
        if (r5 >= r8._top._childCount) goto L_0x0170;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0142, code lost:
        if (r8._top._seen[r5] == false) goto L_0x0145;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0145, code lost:
        r6 = r8._top._curPart.getParticleChild(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0151, code lost:
        if (r6.canStartWithElement(r9) == false) goto L_0x0165;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0153, code lost:
        r8._top._processedChildCount++;
        r8._top._seen[r5] = true;
        push(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0169, code lost:
        if (r6.isSkippable() == false) goto L_0x016d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x016b, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x016d, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0174, code lost:
        if (r2 >= r8._top._childCount) goto L_0x0198;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x017e, code lost:
        if (r8._top._curCount >= r8._top._curMin) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0184, code lost:
        return notValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0189, code lost:
        if (pop() != false) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x018b, code lost:
        if (r9 != null) goto L_0x0193;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0192, code lost:
        return ok((org.apache.xmlbeans.SchemaParticle) null, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0197, code lost:
        return notValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0198, code lost:
        r8._top._curCount++;
        r8._top._processedChildCount = 0;
        java.util.Arrays.fill(r8._top._seen, false);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean visit(javax.xml.namespace.QName r9, boolean r10) {
        /*
            r8 = this;
            boolean r0 = r8.prepare()
            if (r0 != 0) goto L_0x000b
            boolean r8 = r8.notValid()
            return r8
        L_0x000b:
            r0 = -2
            r1 = r0
        L_0x000d:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._curCount
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r3 = r8._top
            int r3 = r3._curMin
            if (r2 <= r3) goto L_0x0027
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._processedChildCount
            if (r0 != r2) goto L_0x0027
            int r0 = r8._stackSize
            if (r1 != r0) goto L_0x0027
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r0 = r8._top
            int r1 = r0._curMax
            r0._curCount = r1
        L_0x0027:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r0 = r8._top
            int r0 = r0._processedChildCount
            int r1 = r8._stackSize
        L_0x002d:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._curCount
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r3 = r8._top
            int r3 = r3._curMax
            if (r2 < r3) goto L_0x003f
            boolean r2 = r8.pop()
            if (r2 != 0) goto L_0x002d
            goto L_0x018b
        L_0x003f:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            org.apache.xmlbeans.SchemaParticle r2 = r2._curPart
            int r2 = r2.getParticleType()
            r3 = 0
            r4 = 1
            if (r2 == r4) goto L_0x0131
            r5 = 2
            if (r2 == r5) goto L_0x00f5
            r5 = 3
            if (r2 == r5) goto L_0x00a7
            r3 = 4
            if (r2 == r3) goto L_0x007e
            r3 = 5
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            org.apache.xmlbeans.SchemaParticle r2 = r2._curPart
            boolean r2 = r2.canStartWithElement(r9)
            if (r2 != 0) goto L_0x006e
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._curCount
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r3 = r8._top
            int r3 = r3._curMin
            if (r2 >= r3) goto L_0x0185
            boolean r8 = r8.notValid()
            return r8
        L_0x006e:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r9 = r8._top
            int r0 = r9._curCount
            int r0 = r0 + r4
            r9._curCount = r0
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r9 = r8._top
            org.apache.xmlbeans.SchemaParticle r9 = r9._curPart
            boolean r8 = r8.ok(r9, r10)
            return r8
        L_0x007e:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            org.apache.xmlbeans.SchemaParticle r2 = r2._curPart
            boolean r2 = r2.canStartWithElement(r9)
            if (r2 != 0) goto L_0x0097
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._curCount
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r3 = r8._top
            int r3 = r3._curMin
            if (r2 >= r3) goto L_0x0185
            boolean r8 = r8.notValid()
            return r8
        L_0x0097:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r9 = r8._top
            int r0 = r9._curCount
            int r0 = r0 + r4
            r9._curCount = r0
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r9 = r8._top
            org.apache.xmlbeans.SchemaParticle r9 = r9._curPart
            boolean r8 = r8.ok(r9, r10)
            return r8
        L_0x00a7:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._processedChildCount
        L_0x00ab:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r5 = r8._top
            int r5 = r5._childCount
            if (r2 >= r5) goto L_0x00e8
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r5 = r8._top
            org.apache.xmlbeans.SchemaParticle r5 = r5._curPart
            org.apache.xmlbeans.SchemaParticle r5 = r5.getParticleChild(r2)
            boolean r6 = r5.canStartWithElement(r9)
            if (r6 == 0) goto L_0x00ca
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r3 = r8._top
            int r2 = r2 + 1
            r3._processedChildCount = r2
            r8.push(r5)
            goto L_0x000d
        L_0x00ca:
            boolean r5 = r5.isSkippable()
            if (r5 != 0) goto L_0x00e5
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._processedChildCount
            if (r2 != 0) goto L_0x00e0
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._curCount
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r3 = r8._top
            int r3 = r3._curMin
            if (r2 >= r3) goto L_0x0185
        L_0x00e0:
            boolean r8 = r8.notValid()
            return r8
        L_0x00e5:
            int r2 = r2 + 1
            goto L_0x00ab
        L_0x00e8:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r5 = r2._curCount
            int r5 = r5 + r4
            r2._curCount = r5
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            r2._processedChildCount = r3
            goto L_0x000d
        L_0x00f5:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._childCount
            if (r3 >= r2) goto L_0x0118
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            org.apache.xmlbeans.SchemaParticle r2 = r2._curPart
            org.apache.xmlbeans.SchemaParticle r2 = r2.getParticleChild(r3)
            boolean r5 = r2.canStartWithElement(r9)
            if (r5 == 0) goto L_0x0115
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r3 = r8._top
            int r5 = r3._curCount
            int r5 = r5 + r4
            r3._curCount = r5
            r8.push(r2)
            goto L_0x000d
        L_0x0115:
            int r3 = r3 + 1
            goto L_0x00f5
        L_0x0118:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._curCount
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r3 = r8._top
            int r3 = r3._curMin
            if (r2 >= r3) goto L_0x0185
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            org.apache.xmlbeans.SchemaParticle r2 = r2._curPart
            boolean r2 = r2.isSkippable()
            if (r2 != 0) goto L_0x0185
            boolean r8 = r8.notValid()
            return r8
        L_0x0131:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._processedChildCount
            r5 = r3
        L_0x0136:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r6 = r8._top
            int r6 = r6._childCount
            if (r5 >= r6) goto L_0x0170
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r6 = r8._top
            boolean[] r6 = r6._seen
            boolean r6 = r6[r5]
            if (r6 == 0) goto L_0x0145
            goto L_0x016d
        L_0x0145:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r6 = r8._top
            org.apache.xmlbeans.SchemaParticle r6 = r6._curPart
            org.apache.xmlbeans.SchemaParticle r6 = r6.getParticleChild(r5)
            boolean r7 = r6.canStartWithElement(r9)
            if (r7 == 0) goto L_0x0165
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r3 = r2._processedChildCount
            int r3 = r3 + r4
            r2._processedChildCount = r3
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            boolean[] r2 = r2._seen
            r2[r5] = r4
            r8.push(r6)
            goto L_0x000d
        L_0x0165:
            boolean r6 = r6.isSkippable()
            if (r6 == 0) goto L_0x016d
            int r2 = r2 + 1
        L_0x016d:
            int r5 = r5 + 1
            goto L_0x0136
        L_0x0170:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r5 = r8._top
            int r5 = r5._childCount
            if (r2 >= r5) goto L_0x0198
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r2 = r2._curCount
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r3 = r8._top
            int r3 = r3._curMin
            if (r2 >= r3) goto L_0x0185
            boolean r8 = r8.notValid()
            return r8
        L_0x0185:
            boolean r2 = r8.pop()
            if (r2 != 0) goto L_0x000d
        L_0x018b:
            if (r9 != 0) goto L_0x0193
            r9 = 0
            boolean r8 = r8.ok(r9, r10)
            return r8
        L_0x0193:
            boolean r8 = r8.notValid()
            return r8
        L_0x0198:
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            int r5 = r2._curCount
            int r5 = r5 + r4
            r2._curCount = r5
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            r2._processedChildCount = r3
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl$VisitorState r2 = r8._top
            boolean[] r2 = r2._seen
            java.util.Arrays.fill(r2, r3)
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl.visit(javax.xml.namespace.QName, boolean):boolean");
    }

    public boolean testValid(QName qName) {
        return visit(qName, true);
    }

    public int get_elementflags() {
        boolean z = false;
        if (currentParticle() == null || currentParticle().getParticleType() != 4) {
            return 0;
        }
        SchemaLocalElement schemaLocalElement = (SchemaLocalElement) currentParticle();
        boolean isNillable = schemaLocalElement.isNillable() | (schemaLocalElement.isDefault() ? (char) 2 : 0);
        if (schemaLocalElement.isFixed()) {
            z = true;
        }
        return isNillable | z ? 1 : 0;
    }

    public String get_default_text() {
        if (currentParticle() == null || currentParticle().getParticleType() != 4) {
            return null;
        }
        return ((SchemaLocalElement) currentParticle()).getDefaultText();
    }

    public SchemaField get_schema_field() {
        if (currentParticle() instanceof SchemaField) {
            return (SchemaField) currentParticle();
        }
        return null;
    }

    public SchemaParticle currentParticle() {
        return this._matchedParticle;
    }

    public boolean isAllValid() {
        return this._isValid;
    }
}
