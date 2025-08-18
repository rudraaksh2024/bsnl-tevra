package org.apache.poi.poifs.filesystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import org.apache.poi.hpsf.ClassID;

public class FilteringDirectoryNode implements DirectoryEntry {
    private final Map<String, List<String>> childExcludes;
    /* access modifiers changed from: private */
    public final DirectoryEntry directory;
    /* access modifiers changed from: private */
    public final Set<String> excludes;

    public boolean isDirectoryEntry() {
        return true;
    }

    public boolean isDocumentEntry() {
        return false;
    }

    public FilteringDirectoryNode(DirectoryEntry directoryEntry, Collection<String> collection) {
        if (directoryEntry != null) {
            this.directory = directoryEntry;
            this.excludes = new HashSet();
            this.childExcludes = new HashMap();
            for (String next : collection) {
                int indexOf = next.indexOf(47);
                if (indexOf == -1) {
                    this.excludes.add(next);
                } else {
                    String substring = next.substring(0, indexOf);
                    String substring2 = next.substring(indexOf + 1);
                    if (!this.childExcludes.containsKey(substring)) {
                        this.childExcludes.put(substring, new ArrayList());
                    }
                    this.childExcludes.get(substring).add(substring2);
                }
            }
            return;
        }
        throw new IllegalArgumentException("directory cannot be null");
    }

    public DirectoryEntry createDirectory(String str) throws IOException {
        return this.directory.createDirectory(str);
    }

    public DocumentEntry createDocument(String str, InputStream inputStream) throws IOException {
        return this.directory.createDocument(str, inputStream);
    }

    public DocumentEntry createDocument(String str, int i, POIFSWriterListener pOIFSWriterListener) throws IOException {
        return this.directory.createDocument(str, i, pOIFSWriterListener);
    }

    public Iterator<Entry> getEntries() {
        return new FilteringIterator();
    }

    public Iterator<Entry> iterator() {
        return getEntries();
    }

    public Spliterator<Entry> spliterator() {
        return Spliterators.spliterator(iterator(), (long) getEntryCount(), 0);
    }

    public int getEntryCount() {
        int entryCount = this.directory.getEntryCount();
        for (String hasEntry : this.excludes) {
            if (this.directory.hasEntry(hasEntry)) {
                entryCount--;
            }
        }
        return entryCount;
    }

    public Set<String> getEntryNames() {
        HashSet hashSet = new HashSet();
        for (String next : this.directory.getEntryNames()) {
            if (!this.excludes.contains(next)) {
                hashSet.add(next);
            }
        }
        return hashSet;
    }

    public boolean isEmpty() {
        return getEntryCount() == 0;
    }

    public boolean hasEntry(String str) {
        if (this.excludes.contains(str)) {
            return false;
        }
        return this.directory.hasEntry(str);
    }

    public Entry getEntry(String str) throws FileNotFoundException {
        if (!this.excludes.contains(str)) {
            return wrapEntry(this.directory.getEntry(str));
        }
        throw new FileNotFoundException(str);
    }

    /* access modifiers changed from: private */
    public Entry wrapEntry(Entry entry) {
        String name = entry.getName();
        return (!this.childExcludes.containsKey(name) || !(entry instanceof DirectoryEntry)) ? entry : new FilteringDirectoryNode((DirectoryEntry) entry, this.childExcludes.get(name));
    }

    public ClassID getStorageClsid() {
        return this.directory.getStorageClsid();
    }

    public void setStorageClsid(ClassID classID) {
        this.directory.setStorageClsid(classID);
    }

    public boolean delete() {
        return this.directory.delete();
    }

    public boolean renameTo(String str) {
        return this.directory.renameTo(str);
    }

    public String getName() {
        return this.directory.getName();
    }

    public DirectoryEntry getParent() {
        return this.directory.getParent();
    }

    private class FilteringIterator implements Iterator<Entry> {
        private Entry next;
        private final Iterator<Entry> parent;

        private FilteringIterator() {
            this.parent = FilteringDirectoryNode.this.directory.getEntries();
            locateNext();
        }

        private void locateNext() {
            this.next = null;
            while (this.parent.hasNext() && this.next == null) {
                Entry next2 = this.parent.next();
                if (!FilteringDirectoryNode.this.excludes.contains(next2.getName())) {
                    this.next = FilteringDirectoryNode.this.wrapEntry(next2);
                }
            }
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public Entry next() {
            if (hasNext()) {
                Entry entry = this.next;
                locateNext();
                return entry;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
}
