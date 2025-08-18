package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

abstract class CharNode implements DomImpl.Dom, Node, CharacterData {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    int _cch;
    private Locale _locale;
    CharNode _next;
    int _off;
    CharNode _prev;
    private Object _src;

    public NamedNodeMap getAttributes() {
        return null;
    }

    public Node getFirstChild() {
        return null;
    }

    public Node getLastChild() {
        return null;
    }

    public QName getQName() {
        return null;
    }

    public boolean hasAttributes() {
        return false;
    }

    public boolean hasChildNodes() {
        return false;
    }

    public boolean nodeCanHavePrefixUri() {
        return false;
    }

    public CharNode(Locale locale) {
        this._locale = locale;
    }

    public Locale locale() {
        Locale locale = this._locale;
        return locale == null ? ((DomImpl.Dom) this._src).locale() : locale;
    }

    public void setChars(Object obj, int i, int i2) {
        if (this._locale == null) {
            this._locale = ((DomImpl.Dom) this._src).locale();
        }
        this._src = obj;
        this._off = i;
        this._cch = i2;
    }

    public DomImpl.Dom getDom() {
        Object obj = this._src;
        if (obj instanceof DomImpl.Dom) {
            return (DomImpl.Dom) obj;
        }
        return null;
    }

    public void setDom(DomImpl.Dom dom) {
        this._src = dom;
        this._locale = null;
    }

    public Cur tempCur() {
        if (!(this._src instanceof DomImpl.Dom)) {
            return null;
        }
        Cur tempCur = locale().tempCur();
        tempCur.moveToCharNode(this);
        return tempCur;
    }

    private boolean isValid() {
        return (this._src instanceof DomImpl.Dom) == (this._locale == null);
    }

    public static boolean isOnList(CharNode charNode, CharNode charNode2) {
        while (charNode != null) {
            if (charNode == charNode2) {
                return true;
            }
            charNode = charNode._next;
        }
        return false;
    }

    public static CharNode remove(CharNode charNode, CharNode charNode2) {
        if (charNode == charNode2) {
            charNode = charNode2._next;
        } else {
            charNode2._prev._next = charNode2._next;
        }
        CharNode charNode3 = charNode2._next;
        if (charNode3 != null) {
            charNode3._prev = charNode2._prev;
        }
        charNode2._next = null;
        charNode2._prev = null;
        return charNode;
    }

    public static CharNode insertNode(CharNode charNode, CharNode charNode2, CharNode charNode3) {
        CharNode charNode4;
        if (charNode != null) {
            if (charNode == charNode3) {
                charNode._prev = charNode2;
                charNode2._next = charNode;
            } else {
                CharNode charNode5 = charNode;
                while (true) {
                    charNode4 = charNode5._next;
                    if (charNode4 == charNode3) {
                        break;
                    }
                    charNode5 = charNode4;
                }
                charNode2._next = charNode4;
                if (charNode4 != null) {
                    charNode5._next._prev = charNode2;
                }
                charNode2._prev = charNode5;
                charNode5._next = charNode2;
                return charNode;
            }
        }
        return charNode2;
    }

    public static CharNode appendNode(CharNode charNode, CharNode charNode2) {
        return insertNode(charNode, charNode2, (CharNode) null);
    }

    public static CharNode appendNodes(CharNode charNode, CharNode charNode2) {
        if (charNode == null) {
            return charNode2;
        }
        CharNode charNode3 = charNode;
        while (true) {
            CharNode charNode4 = charNode3._next;
            if (charNode4 != null) {
                charNode3 = charNode4;
            } else {
                charNode3._next = charNode2;
                charNode2._prev = charNode3;
                return charNode;
            }
        }
    }

    public static CharNode copyNodes(CharNode charNode, Object obj) {
        CharNode charNode2;
        CharNode charNode3 = null;
        CharNode charNode4 = null;
        while (charNode != null) {
            if (charNode instanceof TextNode) {
                charNode2 = charNode.locale().createTextNode();
            } else {
                charNode2 = charNode.locale().createCdataNode();
            }
            charNode2.setChars(obj, charNode._off, charNode._cch);
            if (charNode3 == null) {
                charNode3 = charNode2;
            }
            if (charNode4 != null) {
                charNode4._next = charNode2;
                charNode2._prev = charNode4;
            }
            charNode = charNode._next;
            charNode4 = charNode2;
        }
        return charNode3;
    }

    public boolean isNodeAftertext() {
        Xobj xobj = (Xobj) this._src;
        if (xobj._charNodesValue == null) {
            return true;
        }
        if (xobj._charNodesAfter == null) {
            return false;
        }
        return isOnList(xobj._charNodesAfter, this);
    }

    public void dump(PrintStream printStream, Object obj) {
        Object obj2 = this._src;
        if (obj2 instanceof DomImpl.Dom) {
            ((DomImpl.Dom) obj2).dump(printStream, obj);
        } else {
            printStream.println("Lonely CharNode: \"" + CharUtil.getString(this._src, this._off, this._cch) + "\"");
        }
    }

    public void dump(PrintStream printStream) {
        dump(printStream, this);
    }

    public void dump() {
        dump(System.out);
    }

    public Node appendChild(Node node) {
        return DomImpl._node_appendChild(this, node);
    }

    public Node cloneNode(boolean z) {
        return DomImpl._node_cloneNode(this, z);
    }

    public NodeList getChildNodes() {
        return DomImpl._emptyNodeList;
    }

    public Node getParentNode() {
        return DomImpl._node_getParentNode(this);
    }

    public Node removeChild(Node node) {
        return DomImpl._node_removeChild(this, node);
    }

    public String getLocalName() {
        return DomImpl._node_getLocalName(this);
    }

    public String getNamespaceURI() {
        return DomImpl._node_getNamespaceURI(this);
    }

    public Node getNextSibling() {
        return DomImpl._node_getNextSibling(this);
    }

    public String getNodeName() {
        return DomImpl._node_getNodeName(this);
    }

    public short getNodeType() {
        return DomImpl._node_getNodeType(this);
    }

    public String getNodeValue() {
        return DomImpl._node_getNodeValue(this);
    }

    public Document getOwnerDocument() {
        return DomImpl._node_getOwnerDocument(this);
    }

    public String getPrefix() {
        return DomImpl._node_getPrefix(this);
    }

    public Node getPreviousSibling() {
        return DomImpl._node_getPreviousSibling(this);
    }

    public Node insertBefore(Node node, Node node2) {
        return DomImpl._node_insertBefore(this, node, node2);
    }

    public boolean isSupported(String str, String str2) {
        return DomImpl._node_isSupported(this, str, str2);
    }

    public void normalize() {
        DomImpl._node_normalize(this);
    }

    public Node replaceChild(Node node, Node node2) {
        return DomImpl._node_replaceChild(this, node, node2);
    }

    public void setNodeValue(String str) {
        DomImpl._node_setNodeValue(this, str);
    }

    public void setPrefix(String str) {
        DomImpl._node_setPrefix(this, str);
    }

    public Object getUserData(String str) {
        return DomImpl._node_getUserData(this, str);
    }

    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        return DomImpl._node_setUserData(this, str, obj, userDataHandler);
    }

    public Object getFeature(String str, String str2) {
        return DomImpl._node_getFeature(this, str, str2);
    }

    public boolean isEqualNode(Node node) {
        return DomImpl._node_isEqualNode(this, node);
    }

    public boolean isSameNode(Node node) {
        return DomImpl._node_isSameNode(this, node);
    }

    public String lookupNamespaceURI(String str) {
        return DomImpl._node_lookupNamespaceURI(this, str);
    }

    public String lookupPrefix(String str) {
        return DomImpl._node_lookupPrefix(this, str);
    }

    public boolean isDefaultNamespace(String str) {
        return DomImpl._node_isDefaultNamespace(this, str);
    }

    public void setTextContent(String str) {
        DomImpl._node_setTextContent(this, str);
    }

    public String getTextContent() {
        return DomImpl._node_getTextContent(this);
    }

    public short compareDocumentPosition(Node node) {
        return DomImpl._node_compareDocumentPosition(this, node);
    }

    public String getBaseURI() {
        return DomImpl._node_getBaseURI(this);
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

    /* access modifiers changed from: package-private */
    public Object getObject() {
        return this._src;
    }
}
