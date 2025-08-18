package org.apache.poi.poifs.filesystem;

import org.apache.poi.openxml4j.opc.PackagingURIHelper;

public class DocumentDescriptor {
    private int hashcode;
    private String name;
    private POIFSDocumentPath path;

    public DocumentDescriptor(POIFSDocumentPath pOIFSDocumentPath, String str) {
        if (pOIFSDocumentPath == null) {
            throw new NullPointerException("path must not be null");
        } else if (str == null) {
            throw new NullPointerException("name must not be null");
        } else if (str.length() != 0) {
            this.path = pOIFSDocumentPath;
            this.name = str;
        } else {
            throw new IllegalArgumentException("name cannot be empty");
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this != obj) {
            DocumentDescriptor documentDescriptor = (DocumentDescriptor) obj;
            if (!this.path.equals(documentDescriptor.path) || !this.name.equals(documentDescriptor.name)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        if (this.hashcode == 0) {
            this.hashcode = this.path.hashCode() ^ this.name.hashCode();
        }
        return this.hashcode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.path.length() + 1) * 40);
        for (int i = 0; i < this.path.length(); i++) {
            sb.append(this.path.getComponent(i)).append(PackagingURIHelper.FORWARD_SLASH_STRING);
        }
        sb.append(this.name);
        return sb.toString();
    }
}
