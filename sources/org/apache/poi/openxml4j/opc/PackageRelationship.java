package org.apache.poi.openxml4j.opc;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public final class PackageRelationship {
    public static final String ID_ATTRIBUTE_NAME = "Id";
    public static final String RELATIONSHIPS_TAG_NAME = "Relationships";
    public static final String RELATIONSHIP_TAG_NAME = "Relationship";
    public static final String TARGET_ATTRIBUTE_NAME = "Target";
    public static final String TARGET_MODE_ATTRIBUTE_NAME = "TargetMode";
    public static final String TYPE_ATTRIBUTE_NAME = "Type";
    private static URI containerRelationshipPart;
    private final OPCPackage container;
    private final String id;
    private final String relationshipType;
    private final PackagePart source;
    private final TargetMode targetMode;
    private final URI targetUri;

    static {
        try {
            containerRelationshipPart = new URI("/_rels/.rels");
        } catch (URISyntaxException unused) {
        }
    }

    public PackageRelationship(OPCPackage oPCPackage, PackagePart packagePart, URI uri, TargetMode targetMode2, String str, String str2) {
        if (oPCPackage == null) {
            throw new IllegalArgumentException("pkg");
        } else if (uri == null) {
            throw new IllegalArgumentException("targetUri");
        } else if (str == null) {
            throw new IllegalArgumentException("relationshipType");
        } else if (str2 != null) {
            this.container = oPCPackage;
            this.source = packagePart;
            this.targetUri = uri;
            this.targetMode = targetMode2;
            this.relationshipType = str;
            this.id = str2;
        } else {
            throw new IllegalArgumentException("id");
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PackageRelationship)) {
            return false;
        }
        PackageRelationship packageRelationship = (PackageRelationship) obj;
        if (!this.id.equals(packageRelationship.id) || !this.relationshipType.equals(packageRelationship.relationshipType)) {
            return false;
        }
        PackagePart packagePart = packageRelationship.source;
        if ((packagePart == null || packagePart.equals(this.source)) && this.targetMode == packageRelationship.targetMode && this.targetUri.equals(packageRelationship.targetUri)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.relationshipType, this.source, this.targetMode, this.targetUri});
    }

    public static URI getContainerPartRelationship() {
        return containerRelationshipPart;
    }

    public OPCPackage getPackage() {
        return this.container;
    }

    public String getId() {
        return this.id;
    }

    public String getRelationshipType() {
        return this.relationshipType;
    }

    public PackagePart getSource() {
        return this.source;
    }

    public URI getSourceURI() {
        PackagePart packagePart = this.source;
        if (packagePart == null) {
            return PackagingURIHelper.PACKAGE_ROOT_URI;
        }
        return packagePart._partName.getURI();
    }

    public TargetMode getTargetMode() {
        return this.targetMode;
    }

    public URI getTargetURI() {
        if (this.targetMode == TargetMode.EXTERNAL) {
            return this.targetUri;
        }
        if (!this.targetUri.toASCIIString().startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            return PackagingURIHelper.resolvePartUri(getSourceURI(), this.targetUri);
        }
        return this.targetUri;
    }

    public String toString() {
        String str;
        StringBuilder append = new StringBuilder("id=").append(this.id).append(" - container=").append(this.container).append(" - relationshipType=").append(this.relationshipType);
        if (this.source == null) {
            str = " - source=null";
        } else {
            str = " - source=" + getSourceURI().toASCIIString();
        }
        return append.append(str).append(" - target=").append(getTargetURI().toASCIIString()).append(this.targetMode == null ? ",targetMode=null" : ",targetMode=" + this.targetMode).toString();
    }
}
