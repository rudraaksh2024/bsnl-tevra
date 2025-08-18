package org.apache.xmlbeans.impl.common;

import java.net.URI;
import java.util.AbstractCollection;
import java.util.Collections;
import java.util.Iterator;
import org.apache.xmlbeans.XmlError;

public class XmlErrorPrinter extends AbstractCollection<XmlError> {
    private final URI _baseURI;
    private final boolean _noisy;

    public int size() {
        return 0;
    }

    public XmlErrorPrinter(boolean z, URI uri) {
        this._noisy = z;
        this._baseURI = uri;
    }

    public boolean add(XmlError xmlError) {
        if (xmlError == null) {
            return false;
        }
        if (xmlError.getSeverity() == 0 || xmlError.getSeverity() == 1) {
            System.err.println(xmlError.toString(this._baseURI));
            return false;
        } else if (!this._noisy) {
            return false;
        } else {
            System.out.println(xmlError.toString(this._baseURI));
            return false;
        }
    }

    public Iterator<XmlError> iterator() {
        return Collections.emptyIterator();
    }
}
