package org.apache.poi.poifs.eventfilesystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.poi.poifs.filesystem.DocumentDescriptor;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;

class POIFSReaderRegistry {
    private Map<DocumentDescriptor, Set<POIFSReaderListener>> chosenDocumentDescriptors = new HashMap();
    private Set<POIFSReaderListener> omnivorousListeners = new HashSet();
    private Map<POIFSReaderListener, Set<DocumentDescriptor>> selectiveListeners = new HashMap();

    POIFSReaderRegistry() {
    }

    /* access modifiers changed from: package-private */
    public void registerListener(POIFSReaderListener pOIFSReaderListener, POIFSDocumentPath pOIFSDocumentPath, String str) {
        if (!this.omnivorousListeners.contains(pOIFSReaderListener)) {
            DocumentDescriptor documentDescriptor = new DocumentDescriptor(pOIFSDocumentPath, str);
            if (this.selectiveListeners.computeIfAbsent(pOIFSReaderListener, new POIFSReaderRegistry$$ExternalSyntheticLambda0()).add(documentDescriptor)) {
                this.chosenDocumentDescriptors.computeIfAbsent(documentDescriptor, new POIFSReaderRegistry$$ExternalSyntheticLambda1()).add(pOIFSReaderListener);
            }
        }
    }

    static /* synthetic */ Set lambda$registerListener$0(POIFSReaderListener pOIFSReaderListener) {
        return new HashSet();
    }

    static /* synthetic */ Set lambda$registerListener$1(DocumentDescriptor documentDescriptor) {
        return new HashSet();
    }

    /* access modifiers changed from: package-private */
    public void registerListener(POIFSReaderListener pOIFSReaderListener) {
        if (!this.omnivorousListeners.contains(pOIFSReaderListener)) {
            removeSelectiveListener(pOIFSReaderListener);
            this.omnivorousListeners.add(pOIFSReaderListener);
        }
    }

    /* access modifiers changed from: package-private */
    public Iterable<POIFSReaderListener> getListeners(POIFSDocumentPath pOIFSDocumentPath, String str) {
        HashSet hashSet = new HashSet(this.omnivorousListeners);
        Set set = this.chosenDocumentDescriptors.get(new DocumentDescriptor(pOIFSDocumentPath, str));
        if (set != null) {
            hashSet.addAll(set);
        }
        return hashSet;
    }

    private void removeSelectiveListener(POIFSReaderListener pOIFSReaderListener) {
        Set<DocumentDescriptor> remove = this.selectiveListeners.remove(pOIFSReaderListener);
        if (remove != null) {
            for (DocumentDescriptor dropDocument : remove) {
                dropDocument(pOIFSReaderListener, dropDocument);
            }
        }
    }

    private void dropDocument(POIFSReaderListener pOIFSReaderListener, DocumentDescriptor documentDescriptor) {
        Set set = this.chosenDocumentDescriptors.get(documentDescriptor);
        set.remove(pOIFSReaderListener);
        if (set.isEmpty()) {
            this.chosenDocumentDescriptors.remove(documentDescriptor);
        }
    }
}
