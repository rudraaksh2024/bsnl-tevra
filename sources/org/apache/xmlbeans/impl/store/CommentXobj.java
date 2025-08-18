package org.apache.xmlbeans.impl.store;

import org.w3c.dom.Comment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class CommentXobj extends NodeXobj implements Comment {
    public Node getFirstChild() {
        return null;
    }

    CommentXobj(Locale locale) {
        super(locale, 4, 8);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new CommentXobj(locale);
    }

    public NodeList getChildNodes() {
        return DomImpl._emptyNodeList;
    }

    public void appendData(String str) {
        DomImpl._characterData_appendData(this, str);
    }

    public void deleteData(int i, int i2) {
        DomImpl._characterData_deleteData(this, i, i2);
    }

    public String getData() {
        return DomImpl._characterData_getData(this);
    }

    public int getLength() {
        return DomImpl._characterData_getLength(this);
    }

    public void insertData(int i, String str) {
        DomImpl._characterData_insertData(this, i, str);
    }

    public void replaceData(int i, int i2, String str) {
        DomImpl._characterData_replaceData(this, i, i2, str);
    }

    public void setData(String str) {
        DomImpl._characterData_setData(this, str);
    }

    public String substringData(int i, int i2) {
        return DomImpl._characterData_substringData(this, i, i2);
    }
}
