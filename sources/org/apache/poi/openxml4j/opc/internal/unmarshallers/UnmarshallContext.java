package org.apache.poi.openxml4j.opc.internal.unmarshallers;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePartName;

public final class UnmarshallContext {
    private OPCPackage _package;
    private PackagePartName partName;
    private ZipArchiveEntry zipEntry;

    public UnmarshallContext(OPCPackage oPCPackage, PackagePartName packagePartName) {
        this._package = oPCPackage;
        this.partName = packagePartName;
    }

    /* access modifiers changed from: package-private */
    public OPCPackage getPackage() {
        return this._package;
    }

    public void setPackage(OPCPackage oPCPackage) {
        this._package = oPCPackage;
    }

    /* access modifiers changed from: package-private */
    public PackagePartName getPartName() {
        return this.partName;
    }

    public void setPartName(PackagePartName packagePartName) {
        this.partName = packagePartName;
    }

    /* access modifiers changed from: package-private */
    public ZipArchiveEntry getZipEntry() {
        return this.zipEntry;
    }

    public void setZipEntry(ZipArchiveEntry zipArchiveEntry) {
        this.zipEntry = zipArchiveEntry;
    }
}
