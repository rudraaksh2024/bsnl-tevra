package org.apache.poi;

public class EmptyFileException extends IllegalArgumentException {
    private static final long serialVersionUID = 1536449292174360166L;

    public EmptyFileException() {
        super("The supplied file was empty (zero bytes long)");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public EmptyFileException(java.io.File r3) {
        /*
            r2 = this;
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x0020
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "The supplied file '"
            r0.<init>(r1)
            java.lang.String r3 = r3.getAbsolutePath()
            java.lang.StringBuilder r3 = r0.append(r3)
            java.lang.String r0 = "' was empty (zero bytes long)"
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            goto L_0x0039
        L_0x0020:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "The file '"
            r0.<init>(r1)
            java.lang.String r3 = r3.getAbsolutePath()
            java.lang.StringBuilder r3 = r0.append(r3)
            java.lang.String r0 = "' does not exist"
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
        L_0x0039:
            r2.<init>(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.EmptyFileException.<init>(java.io.File):void");
    }
}
