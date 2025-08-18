package org.apache.xmlbeans.impl.soap;

import java.util.Iterator;
import java.util.Vector;

public class MimeHeaders {
    protected Vector headers = new Vector();

    class MatchingIterator implements Iterator {
        private Iterator iterator;
        private boolean match;
        private String[] names;
        private Object nextHeader;

        private Object nextMatch() {
            while (this.iterator.hasNext()) {
                MimeHeader mimeHeader = (MimeHeader) this.iterator.next();
                if (this.names != null) {
                    int i = 0;
                    while (true) {
                        if (i < this.names.length) {
                            if (!mimeHeader.getName().equalsIgnoreCase(this.names[i])) {
                                i++;
                            } else if (this.match) {
                                return mimeHeader;
                            }
                        } else if (!this.match) {
                            return mimeHeader;
                        }
                    }
                } else if (this.match) {
                    return null;
                } else {
                    return mimeHeader;
                }
            }
            return null;
        }

        public boolean hasNext() {
            if (this.nextHeader == null) {
                this.nextHeader = nextMatch();
            }
            return this.nextHeader != null;
        }

        public Object next() {
            Object obj = this.nextHeader;
            if (obj != null) {
                this.nextHeader = null;
                return obj;
            } else if (hasNext()) {
                return this.nextHeader;
            } else {
                return null;
            }
        }

        public void remove() {
            this.iterator.remove();
        }

        MatchingIterator(String[] strArr, boolean z) {
            this.match = z;
            this.names = strArr;
            this.iterator = MimeHeaders.this.headers.iterator();
        }
    }

    public String[] getHeader(String str) {
        Vector vector = new Vector();
        for (int i = 0; i < this.headers.size(); i++) {
            MimeHeader mimeHeader = (MimeHeader) this.headers.elementAt(i);
            if (mimeHeader.getName().equalsIgnoreCase(str) && mimeHeader.getValue() != null) {
                vector.addElement(mimeHeader.getValue());
            }
        }
        if (vector.size() == 0) {
            return null;
        }
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        return strArr;
    }

    public void setHeader(String str, String str2) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("Illegal MimeHeader name");
        }
        int i = 0;
        boolean z = false;
        while (i < this.headers.size()) {
            MimeHeader mimeHeader = (MimeHeader) this.headers.elementAt(i);
            if (mimeHeader.getName().equalsIgnoreCase(str)) {
                if (!z) {
                    this.headers.setElementAt(new MimeHeader(mimeHeader.getName(), str2), i);
                    z = true;
                } else {
                    this.headers.removeElementAt(i);
                    i--;
                }
            }
            i++;
        }
        if (!z) {
            addHeader(str, str2);
        }
    }

    public void addHeader(String str, String str2) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("Illegal MimeHeader name");
        }
        for (int size = this.headers.size() - 1; size >= 0; size--) {
            if (((MimeHeader) this.headers.elementAt(size)).getName().equalsIgnoreCase(str)) {
                this.headers.insertElementAt(new MimeHeader(str, str2), size + 1);
                return;
            }
        }
        this.headers.addElement(new MimeHeader(str, str2));
    }

    public void removeHeader(String str) {
        int i = 0;
        while (i < this.headers.size()) {
            if (((MimeHeader) this.headers.elementAt(i)).getName().equalsIgnoreCase(str)) {
                this.headers.removeElementAt(i);
                i--;
            }
            i++;
        }
    }

    public void removeAllHeaders() {
        this.headers.removeAllElements();
    }

    public Iterator getAllHeaders() {
        return this.headers.iterator();
    }

    public Iterator getMatchingHeaders(String[] strArr) {
        return new MatchingIterator(strArr, true);
    }

    public Iterator getNonMatchingHeaders(String[] strArr) {
        return new MatchingIterator(strArr, false);
    }
}
