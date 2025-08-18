package org.apache.commons.io;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class IOExceptionList extends IOException {
    private static final long serialVersionUID = 1;
    private final List<? extends Throwable> causeList;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IOExceptionList(java.util.List<? extends java.lang.Throwable> r4) {
        /*
            r3 = this;
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            if (r4 != 0) goto L_0x0008
            r2 = r1
            goto L_0x000c
        L_0x0008:
            int r2 = r4.size()
        L_0x000c:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0[r1] = r2
            r1 = 1
            r0[r1] = r4
            java.lang.String r1 = "%,d exceptions: %s"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            r3.<init>(r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.IOExceptionList.<init>(java.util.List):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IOExceptionList(String str, List<? extends Throwable> list) {
        super(str, (list == null || list.isEmpty()) ? null : (Throwable) list.get(0));
        this.causeList = list == null ? Collections.emptyList() : list;
    }

    public <T extends Throwable> T getCause(int i) {
        return (Throwable) this.causeList.get(i);
    }

    public <T extends Throwable> T getCause(int i, Class<T> cls) {
        return (Throwable) cls.cast(this.causeList.get(i));
    }

    public <T extends Throwable> List<T> getCauseList() {
        return this.causeList;
    }

    public <T extends Throwable> List<T> getCauseList(Class<T> cls) {
        return this.causeList;
    }
}
