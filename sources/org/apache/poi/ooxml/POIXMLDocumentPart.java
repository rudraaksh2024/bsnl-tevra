package org.apache.poi.ooxml;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.exceptions.PartAlreadyExistsException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFRelation;

public class POIXMLDocumentPart {
    private static final Logger LOG = LogManager.getLogger((Class<?>) POIXMLDocumentPart.class);
    private String coreDocumentRel;
    private boolean isCommitted;
    private PackagePart packagePart;
    private POIXMLDocumentPart parent;
    private int relationCounter;
    private final Map<String, RelationPart> relations;

    /* access modifiers changed from: protected */
    public void commit() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void onDocumentCreate() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void onDocumentRead() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void onDocumentRemove() throws IOException {
    }

    public boolean isCommitted() {
        return this.isCommitted;
    }

    public void setCommitted(boolean z) {
        this.isCommitted = z;
    }

    public static class RelationPart {
        private final POIXMLDocumentPart documentPart;
        private final PackageRelationship relationship;

        RelationPart(PackageRelationship packageRelationship, POIXMLDocumentPart pOIXMLDocumentPart) {
            this.relationship = packageRelationship;
            this.documentPart = pOIXMLDocumentPart;
        }

        public PackageRelationship getRelationship() {
            return this.relationship;
        }

        public <T extends POIXMLDocumentPart> T getDocumentPart() {
            return this.documentPart;
        }
    }

    /* access modifiers changed from: package-private */
    public int incrementRelationCounter() {
        int i = this.relationCounter + 1;
        this.relationCounter = i;
        return i;
    }

    /* access modifiers changed from: package-private */
    public int decrementRelationCounter() {
        int i = this.relationCounter - 1;
        this.relationCounter = i;
        return i;
    }

    /* access modifiers changed from: package-private */
    public int getRelationCounter() {
        return this.relationCounter;
    }

    public POIXMLDocumentPart(OPCPackage oPCPackage) {
        this(oPCPackage, PackageRelationshipTypes.CORE_DOCUMENT);
    }

    public POIXMLDocumentPart(OPCPackage oPCPackage, String str) {
        this(getPartFromOPCPackage(oPCPackage, str));
        this.coreDocumentRel = str;
    }

    public POIXMLDocumentPart() {
        this.coreDocumentRel = PackageRelationshipTypes.CORE_DOCUMENT;
        this.relations = new LinkedHashMap();
        this.isCommitted = false;
    }

    public POIXMLDocumentPart(PackagePart packagePart2) {
        this((POIXMLDocumentPart) null, packagePart2);
    }

    public POIXMLDocumentPart(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart2) {
        this.coreDocumentRel = PackageRelationshipTypes.CORE_DOCUMENT;
        this.relations = new LinkedHashMap();
        this.isCommitted = false;
        this.packagePart = packagePart2;
        this.parent = pOIXMLDocumentPart;
    }

    /* access modifiers changed from: protected */
    public final void rebase(OPCPackage oPCPackage) throws InvalidFormatException {
        PackageRelationshipCollection relationshipsByType = this.packagePart.getRelationshipsByType(this.coreDocumentRel);
        if (relationshipsByType.size() == 1) {
            this.packagePart = this.packagePart.getRelatedPart(relationshipsByType.getRelationship(0));
            return;
        }
        throw new IllegalStateException("Tried to rebase using " + this.coreDocumentRel + " but found " + relationshipsByType.size() + " parts of the right type");
    }

    public final PackagePart getPackagePart() {
        return this.packagePart;
    }

    public final List<POIXMLDocumentPart> getRelations() {
        ArrayList arrayList = new ArrayList();
        for (RelationPart documentPart : this.relations.values()) {
            arrayList.add(documentPart.getDocumentPart());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public final List<RelationPart> getRelationParts() {
        return Collections.unmodifiableList(new ArrayList(this.relations.values()));
    }

    public final POIXMLDocumentPart getRelationById(String str) {
        RelationPart relationPartById = getRelationPartById(str);
        if (relationPartById == null) {
            return null;
        }
        return relationPartById.getDocumentPart();
    }

    public final RelationPart getRelationPartById(String str) {
        return this.relations.get(str);
    }

    public final String getRelationId(POIXMLDocumentPart pOIXMLDocumentPart) {
        for (RelationPart next : this.relations.values()) {
            if (next.getDocumentPart() == pOIXMLDocumentPart) {
                return next.getRelationship().getId();
            }
        }
        return null;
    }

    public final RelationPart addRelation(String str, POIXMLRelation pOIXMLRelation, POIXMLDocumentPart pOIXMLDocumentPart) {
        PackageRelationship findExistingRelation = this.packagePart.findExistingRelation(pOIXMLDocumentPart.getPackagePart());
        if (findExistingRelation == null) {
            findExistingRelation = this.packagePart.addRelationship(pOIXMLDocumentPart.getPackagePart().getPartName(), TargetMode.INTERNAL, pOIXMLRelation.getRelation(), str);
        }
        addRelation(findExistingRelation, pOIXMLDocumentPart);
        return new RelationPart(findExistingRelation, pOIXMLDocumentPart);
    }

    private void addRelation(PackageRelationship packageRelationship, POIXMLDocumentPart pOIXMLDocumentPart) {
        this.relations.put(packageRelationship.getId(), new RelationPart(packageRelationship, pOIXMLDocumentPart));
        pOIXMLDocumentPart.incrementRelationCounter();
    }

    /* access modifiers changed from: protected */
    public final void removeRelation(POIXMLDocumentPart pOIXMLDocumentPart) {
        removeRelation(pOIXMLDocumentPart, true);
    }

    /* access modifiers changed from: protected */
    public final boolean removeRelation(POIXMLDocumentPart pOIXMLDocumentPart, boolean z) {
        return removeRelation(getRelationId(pOIXMLDocumentPart), z);
    }

    /* access modifiers changed from: protected */
    public final void removeRelation(String str) {
        removeRelation(str, true);
    }

    private boolean removeRelation(String str, boolean z) {
        RelationPart relationPart = this.relations.get(str);
        if (relationPart == null) {
            return false;
        }
        POIXMLDocumentPart documentPart = relationPart.getDocumentPart();
        documentPart.decrementRelationCounter();
        getPackagePart().removeRelationship(str);
        this.relations.remove(str);
        if (!z || documentPart.getRelationCounter() != 0) {
            return true;
        }
        try {
            documentPart.onDocumentRemove();
            getPackagePart().getPackage().removePart(documentPart.getPackagePart());
            return true;
        } catch (IOException e) {
            throw new POIXMLException((Throwable) e);
        }
    }

    public final POIXMLDocumentPart getParent() {
        return this.parent;
    }

    public String toString() {
        PackagePart packagePart2 = this.packagePart;
        return packagePart2 == null ? "" : packagePart2.toString();
    }

    /* access modifiers changed from: protected */
    public final void onSave(Set<PackagePart> set) throws IOException {
        if (!this.isCommitted) {
            prepareForCommit();
            commit();
            set.add(getPackagePart());
            for (RelationPart documentPart : this.relations.values()) {
                POIXMLDocumentPart documentPart2 = documentPart.getDocumentPart();
                if (!set.contains(documentPart2.getPackagePart())) {
                    documentPart2.onSave(set);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void prepareForCommit() {
        PackagePart packagePart2 = getPackagePart();
        if (packagePart2 != null) {
            packagePart2.clear();
        }
    }

    public final POIXMLDocumentPart createRelationship(POIXMLRelation pOIXMLRelation, POIXMLFactory pOIXMLFactory) {
        return createRelationship(pOIXMLRelation, pOIXMLFactory, -1, false).getDocumentPart();
    }

    public final POIXMLDocumentPart createRelationship(POIXMLRelation pOIXMLRelation, POIXMLFactory pOIXMLFactory, int i) {
        return createRelationship(pOIXMLRelation, pOIXMLFactory, i, false).getDocumentPart();
    }

    @Internal
    public final int getNextPartNumber(POIXMLRelation pOIXMLRelation, int i) {
        OPCPackage oPCPackage = this.packagePart.getPackage();
        try {
            String defaultFileName = pOIXMLRelation.getDefaultFileName();
            if (!defaultFileName.equals(pOIXMLRelation.getFileName(9999))) {
                int size = i + oPCPackage.getParts().size();
                for (int i2 = i < 0 ? 1 : i; i2 <= size; i2++) {
                    if (!oPCPackage.containPart(PackagingURIHelper.createPartName(pOIXMLRelation.getFileName(i2)))) {
                        return i2;
                    }
                }
                return -1;
            } else if (oPCPackage.containPart(PackagingURIHelper.createPartName(defaultFileName))) {
                return -1;
            } else {
                return 0;
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException((Throwable) e);
        }
    }

    public final RelationPart createRelationship(POIXMLRelation pOIXMLRelation, POIXMLFactory pOIXMLFactory, int i, boolean z) {
        try {
            PackagePartName createPartName = PackagingURIHelper.createPartName(pOIXMLRelation.getFileName(i));
            PackagePart createPart = this.packagePart.getPackage().createPart(createPartName, pOIXMLRelation.getContentType());
            PackageRelationship addRelationship = !z ? this.packagePart.addRelationship(createPartName, TargetMode.INTERNAL, pOIXMLRelation.getRelation()) : null;
            POIXMLDocumentPart newDocumentPart = pOIXMLFactory.newDocumentPart(pOIXMLRelation);
            newDocumentPart.packagePart = createPart;
            newDocumentPart.parent = this;
            if (!z) {
                addRelation(addRelationship, newDocumentPart);
            }
            return new RelationPart(addRelationship, newDocumentPart);
        } catch (PartAlreadyExistsException e) {
            throw e;
        } catch (Exception e2) {
            throw new POIXMLException((Throwable) e2);
        }
    }

    /* access modifiers changed from: protected */
    public void read(POIXMLFactory pOIXMLFactory, Map<PackagePart, POIXMLDocumentPart> map) throws OpenXML4JException {
        PackagePartName packagePartName;
        PackagePart packagePart2 = getPackagePart();
        if (packagePart2.getContentType().equals(XWPFRelation.GLOSSARY_DOCUMENT.getContentType())) {
            LOG.atWarn().log("POI does not currently support template.main+xml (glossary) parts.  Skipping this part for now.");
            return;
        }
        POIXMLDocumentPart put = map.put(packagePart2, this);
        if (put != null && put != this) {
            throw new POIXMLException("Unique PackagePart-POIXMLDocumentPart relation broken!");
        } else if (packagePart2.hasRelationships()) {
            PackageRelationshipCollection relationships = this.packagePart.getRelationships();
            ArrayList<POIXMLDocumentPart> arrayList = new ArrayList<>();
            Iterator<PackageRelationship> it = relationships.iterator();
            while (it.hasNext()) {
                PackageRelationship next = it.next();
                if (next.getTargetMode() == TargetMode.INTERNAL) {
                    URI targetURI = next.getTargetURI();
                    if (targetURI.getRawFragment() != null) {
                        packagePartName = PackagingURIHelper.createPartName(targetURI.getPath());
                    } else {
                        packagePartName = PackagingURIHelper.createPartName(targetURI);
                    }
                    PackagePart part = this.packagePart.getPackage().getPart(packagePartName);
                    if (part == null) {
                        LOG.atError().log("Skipped invalid entry {}", (Object) next.getTargetURI());
                    } else {
                        POIXMLDocumentPart pOIXMLDocumentPart = map.get(part);
                        if (pOIXMLDocumentPart == null) {
                            pOIXMLDocumentPart = pOIXMLFactory.createDocumentPart(this, part);
                            if ((this instanceof XDDFChart) && (pOIXMLDocumentPart instanceof XSSFWorkbook)) {
                                ((XDDFChart) this).setWorkbook((XSSFWorkbook) pOIXMLDocumentPart);
                            }
                            pOIXMLDocumentPart.parent = this;
                            map.put(part, pOIXMLDocumentPart);
                            arrayList.add(pOIXMLDocumentPart);
                        }
                        addRelation(next, pOIXMLDocumentPart);
                    }
                }
            }
            for (POIXMLDocumentPart read : arrayList) {
                read.read(pOIXMLFactory, map);
            }
        }
    }

    /* access modifiers changed from: protected */
    public PackagePart getTargetPart(PackageRelationship packageRelationship) throws InvalidFormatException {
        return getPackagePart().getRelatedPart(packageRelationship);
    }

    @Internal
    @Deprecated
    public static void _invokeOnDocumentRead(POIXMLDocumentPart pOIXMLDocumentPart) throws IOException {
        pOIXMLDocumentPart.onDocumentRead();
    }

    private static PackagePart getPartFromOPCPackage(OPCPackage oPCPackage, String str) {
        try {
            PackageRelationship relationship = oPCPackage.getRelationshipsByType(str).getRelationship(0);
            if (relationship != null) {
                PackagePart part = oPCPackage.getPart(relationship);
                if (part != null) {
                    return part;
                }
                IOUtils.closeQuietly(oPCPackage);
                throw new POIXMLException("OOXML file structure broken/invalid - core document '" + relationship.getTargetURI() + "' not found.");
            } else if (oPCPackage.getRelationshipsByType(PackageRelationshipTypes.STRICT_CORE_DOCUMENT).getRelationship(0) != null) {
                IOUtils.closeQuietly(oPCPackage);
                throw new POIXMLException("Strict OOXML isn't currently supported, please see bug #57699");
            } else {
                IOUtils.closeQuietly(oPCPackage);
                throw new POIXMLException("OOXML file structure broken/invalid - no core document found!");
            }
        } catch (POIXMLException e) {
            throw e;
        } catch (RuntimeException e2) {
            IOUtils.closeQuietly(oPCPackage);
            throw new POIXMLException("OOXML file structure broken/invalid", e2);
        }
    }
}
