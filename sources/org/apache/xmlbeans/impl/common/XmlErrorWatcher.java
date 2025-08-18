package org.apache.xmlbeans.impl.common;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.apache.xmlbeans.XmlError;

public class XmlErrorWatcher extends AbstractCollection<XmlError> {
    private XmlError _firstError;
    private final Collection<XmlError> _underlying;

    public XmlErrorWatcher(Collection<XmlError> collection) {
        this._underlying = collection;
    }

    public boolean add(XmlError xmlError) {
        if (this._firstError == null && xmlError != null && xmlError.getSeverity() == 0) {
            this._firstError = xmlError;
        }
        Collection<XmlError> collection = this._underlying;
        if (collection == null) {
            return false;
        }
        return collection.add(xmlError);
    }

    public Iterator<XmlError> iterator() {
        Collection<XmlError> collection = this._underlying;
        if (collection == null) {
            return Collections.emptyIterator();
        }
        return collection.iterator();
    }

    public int size() {
        Collection<XmlError> collection = this._underlying;
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    public boolean hasError() {
        return this._firstError != null;
    }

    public XmlError firstError() {
        return this._firstError;
    }
}
