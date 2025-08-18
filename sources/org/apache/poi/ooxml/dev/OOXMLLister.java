package org.apache.poi.ooxml.dev;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;

public class OOXMLLister implements Closeable {
    private final OPCPackage container;
    private final PrintStream disp;

    public OOXMLLister(OPCPackage oPCPackage) {
        this(oPCPackage, System.out);
    }

    public OOXMLLister(OPCPackage oPCPackage, PrintStream printStream) {
        this.container = oPCPackage;
        this.disp = printStream;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        if (r6 != null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0027, code lost:
        r0.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002a, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long getSize(org.apache.poi.openxml4j.opc.PackagePart r6) throws java.io.IOException {
        /*
            java.io.InputStream r6 = r6.getInputStream()
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x001d }
            r1 = 0
            r3 = 0
        L_0x000b:
            r4 = -1
            if (r3 <= r4) goto L_0x0017
            int r3 = r6.read(r0)     // Catch:{ all -> 0x001d }
            if (r3 <= 0) goto L_0x000b
            long r4 = (long) r3
            long r1 = r1 + r4
            goto L_0x000b
        L_0x0017:
            if (r6 == 0) goto L_0x001c
            r6.close()
        L_0x001c:
            return r1
        L_0x001d:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x001f }
        L_0x001f:
            r1 = move-exception
            if (r6 == 0) goto L_0x002a
            r6.close()     // Catch:{ all -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r6 = move-exception
            r0.addSuppressed(r6)
        L_0x002a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.dev.OOXMLLister.getSize(org.apache.poi.openxml4j.opc.PackagePart):long");
    }

    public void displayParts() throws InvalidFormatException, IOException {
        Iterator<PackagePart> it = this.container.getParts().iterator();
        while (it.hasNext()) {
            PackagePart next = it.next();
            this.disp.println(next.getPartName());
            this.disp.println("\t" + next.getContentType());
            if (!next.getPartName().toString().equals("/docProps/core.xml")) {
                this.disp.println("\t" + getSize(next) + " bytes");
            }
            if (!next.isRelationshipPart()) {
                this.disp.println("\t" + next.getRelationships().size() + " relations");
                Iterator<PackageRelationship> it2 = next.getRelationships().iterator();
                while (it2.hasNext()) {
                    displayRelation(it2.next(), "\t  ");
                }
            }
        }
    }

    public void displayRelations() {
        Iterator<PackageRelationship> it = this.container.getRelationships().iterator();
        while (it.hasNext()) {
            displayRelation(it.next(), "");
        }
    }

    private void displayRelation(PackageRelationship packageRelationship, String str) {
        this.disp.println(str + "Relationship:");
        this.disp.println(str + "\tFrom: " + packageRelationship.getSourceURI());
        this.disp.println(str + "\tTo:   " + packageRelationship.getTargetURI());
        this.disp.println(str + "\tID:   " + packageRelationship.getId());
        this.disp.println(str + "\tMode: " + packageRelationship.getTargetMode());
        this.disp.println(str + "\tType: " + packageRelationship.getRelationshipType());
    }

    public void close() throws IOException {
        this.container.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x007a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0080, code lost:
        r0.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0083, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r3) throws java.io.IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            int r0 = r3.length
            if (r0 != 0) goto L_0x0015
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "Use:"
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "\tjava OOXMLLister <filename>"
            r0.println(r1)
            r0 = 1
            java.lang.System.exit(r0)
        L_0x0015:
            java.io.File r0 = new java.io.File
            r1 = 0
            r3 = r3[r1]
            r0.<init>(r3)
            boolean r3 = r0.exists()
            if (r3 != 0) goto L_0x0042
            java.io.PrintStream r3 = java.lang.System.err
            java.lang.String r1 = "Error, file not found!"
            r3.println(r1)
            java.io.PrintStream r3 = java.lang.System.err
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "\t"
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r0)
            java.lang.String r1 = r1.toString()
            r3.println(r1)
            r3 = 2
            java.lang.System.exit(r3)
        L_0x0042:
            org.apache.poi.ooxml.dev.OOXMLLister r3 = new org.apache.poi.ooxml.dev.OOXMLLister
            java.lang.String r1 = r0.toString()
            org.apache.poi.openxml4j.opc.PackageAccess r2 = org.apache.poi.openxml4j.opc.PackageAccess.READ
            org.apache.poi.openxml4j.opc.OPCPackage r1 = org.apache.poi.openxml4j.opc.OPCPackage.open((java.lang.String) r1, (org.apache.poi.openxml4j.opc.PackageAccess) r2)
            r3.<init>(r1)
            java.io.PrintStream r1 = r3.disp     // Catch:{ all -> 0x0078 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0078 }
            r2.<init>()     // Catch:{ all -> 0x0078 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x0078 }
            java.lang.String r2 = "\n"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0078 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0078 }
            r1.println(r0)     // Catch:{ all -> 0x0078 }
            r3.displayParts()     // Catch:{ all -> 0x0078 }
            java.io.PrintStream r0 = r3.disp     // Catch:{ all -> 0x0078 }
            r0.println()     // Catch:{ all -> 0x0078 }
            r3.displayRelations()     // Catch:{ all -> 0x0078 }
            r3.close()
            return
        L_0x0078:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x007a }
        L_0x007a:
            r1 = move-exception
            r3.close()     // Catch:{ all -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r3 = move-exception
            r0.addSuppressed(r3)
        L_0x0083:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.dev.OOXMLLister.main(java.lang.String[]):void");
    }
}
