package org.apache.poi.poifs.filesystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.poifs.property.DirectoryProperty;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.poifs.property.Property;

public class DirectoryNode extends EntryNode implements DirectoryEntry, POIFSViewable, Iterable<Entry> {
    private final Map<String, Entry> _byname = new HashMap();
    private final ArrayList<Entry> _entries = new ArrayList<>();
    private final POIFSFileSystem _filesystem;
    private final POIFSDocumentPath _path;

    public Object[] getViewableArray() {
        return new Object[0];
    }

    public boolean isDirectoryEntry() {
        return true;
    }

    public boolean preferArray() {
        return false;
    }

    DirectoryNode(DirectoryProperty directoryProperty, POIFSFileSystem pOIFSFileSystem, DirectoryNode directoryNode) {
        super(directoryProperty, directoryNode);
        Entry entry;
        this._filesystem = pOIFSFileSystem;
        if (directoryNode == null) {
            this._path = new POIFSDocumentPath();
        } else {
            this._path = new POIFSDocumentPath(directoryNode._path, new String[]{directoryProperty.getName()});
        }
        Iterator<Property> children = directoryProperty.getChildren();
        while (children.hasNext()) {
            Property next = children.next();
            if (next.isDirectory()) {
                entry = new DirectoryNode((DirectoryProperty) next, this._filesystem, this);
            } else {
                entry = new DocumentNode((DocumentProperty) next, this);
            }
            this._entries.add(entry);
            this._byname.put(entry.getName(), entry);
        }
    }

    public POIFSDocumentPath getPath() {
        return this._path;
    }

    public POIFSFileSystem getFileSystem() {
        return this._filesystem;
    }

    public DocumentInputStream createDocumentInputStream(String str) throws IOException {
        return createDocumentInputStream(getEntry(str));
    }

    public DocumentInputStream createDocumentInputStream(Entry entry) throws IOException {
        if (entry.isDocumentEntry()) {
            return new DocumentInputStream((DocumentEntry) entry);
        }
        throw new IOException("Entry '" + entry.getName() + "' is not a DocumentEntry");
    }

    /* access modifiers changed from: package-private */
    public DocumentEntry createDocument(POIFSDocument pOIFSDocument) throws IOException {
        DocumentProperty documentProperty = pOIFSDocument.getDocumentProperty();
        DocumentNode documentNode = new DocumentNode(documentProperty, this);
        ((DirectoryProperty) getProperty()).addChild(documentProperty);
        this._filesystem.addDocument(pOIFSDocument);
        this._entries.add(documentNode);
        this._byname.put(documentProperty.getName(), documentNode);
        return documentNode;
    }

    /* access modifiers changed from: package-private */
    public boolean changeName(String str, String str2) {
        EntryNode entryNode = (EntryNode) this._byname.get(str);
        if (entryNode == null) {
            return false;
        }
        boolean changeName = ((DirectoryProperty) getProperty()).changeName(entryNode.getProperty(), str2);
        if (!changeName) {
            return changeName;
        }
        this._byname.remove(str);
        this._byname.put(entryNode.getProperty().getName(), entryNode);
        return changeName;
    }

    /* access modifiers changed from: package-private */
    public boolean deleteEntry(EntryNode entryNode) {
        boolean deleteChild = ((DirectoryProperty) getProperty()).deleteChild(entryNode.getProperty());
        if (deleteChild) {
            this._entries.remove(entryNode);
            this._byname.remove(entryNode.getName());
            try {
                this._filesystem.remove(entryNode);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return deleteChild;
    }

    public Iterator<Entry> getEntries() {
        return this._entries.iterator();
    }

    public Set<String> getEntryNames() {
        return this._byname.keySet();
    }

    public boolean isEmpty() {
        return this._entries.isEmpty();
    }

    public int getEntryCount() {
        return this._entries.size();
    }

    public boolean hasEntry(String str) {
        return str != null && this._byname.containsKey(str);
    }

    public Entry getEntry(String str) throws FileNotFoundException {
        Entry entry = str != null ? this._byname.get(str) : null;
        if (entry != null) {
            return entry;
        }
        if (this._byname.containsKey("Workbook")) {
            throw new IllegalArgumentException("The document is really a XLS file");
        } else if (this._byname.containsKey("PowerPoint Document")) {
            throw new IllegalArgumentException("The document is really a PPT file");
        } else if (this._byname.containsKey("VisioDocument")) {
            throw new IllegalArgumentException("The document is really a VSD file");
        } else {
            throw new FileNotFoundException("no such entry: \"" + str + "\", had: " + this._byname.keySet());
        }
    }

    public DocumentEntry createDocument(String str, InputStream inputStream) throws IOException {
        return createDocument(new POIFSDocument(str, this._filesystem, inputStream));
    }

    public DocumentEntry createDocument(String str, int i, POIFSWriterListener pOIFSWriterListener) throws IOException {
        return createDocument(new POIFSDocument(str, i, this._filesystem, pOIFSWriterListener));
    }

    public DirectoryEntry createDirectory(String str) throws IOException {
        DirectoryProperty directoryProperty = new DirectoryProperty(str);
        DirectoryNode directoryNode = new DirectoryNode(directoryProperty, this._filesystem, this);
        this._filesystem.addDirectory(directoryProperty);
        ((DirectoryProperty) getProperty()).addChild(directoryProperty);
        this._entries.add(directoryNode);
        this._byname.put(str, directoryNode);
        return directoryNode;
    }

    public DocumentEntry createOrUpdateDocument(String str, InputStream inputStream) throws IOException {
        if (!hasEntry(str)) {
            return createDocument(str, inputStream);
        }
        DocumentNode documentNode = (DocumentNode) getEntry(str);
        new POIFSDocument(documentNode).replaceContents(inputStream);
        return documentNode;
    }

    public ClassID getStorageClsid() {
        return getProperty().getStorageClsid();
    }

    public void setStorageClsid(ClassID classID) {
        getProperty().setStorageClsid(classID);
    }

    /* access modifiers changed from: protected */
    public boolean isDeleteOK() {
        return isEmpty();
    }

    public Iterator<Object> getViewableIterator() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getProperty());
        arrayList.addAll(this._entries);
        return arrayList.iterator();
    }

    public String getShortDescription() {
        return getName();
    }

    public Iterator<Entry> iterator() {
        return getEntries();
    }

    public Spliterator<Entry> spliterator() {
        return this._entries.spliterator();
    }
}
