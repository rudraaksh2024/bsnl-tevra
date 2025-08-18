package org.apache.xmlbeans.impl.common;

import java.util.Stack;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

public class Sax2Dom extends DefaultHandler implements ContentHandler, LexicalHandler {
    public static final String EMPTYSTRING = "";
    public static final String XMLNS_PREFIX = "xmlns";
    public static final String XMLNS_STRING = "xmlns:";
    public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    public static final String XML_PREFIX = "xml";
    private Document _document = null;
    private Vector _namespaceDecls = null;
    private Stack _nodeStk = new Stack();
    private Node _root = null;

    public void endCDATA() {
    }

    public void endDTD() {
    }

    public void endEntity(String str) {
    }

    public void endPrefixMapping(String str) {
    }

    public void ignorableWhitespace(char[] cArr, int i, int i2) {
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void skippedEntity(String str) {
    }

    public void startCDATA() {
    }

    public void startDTD(String str, String str2, String str3) throws SAXException {
    }

    public void startEntity(String str) {
    }

    public Sax2Dom() throws ParserConfigurationException {
        Document createDocument = DocumentHelper.createDocument();
        this._document = createDocument;
        this._root = createDocument;
    }

    public Sax2Dom(Node node) throws ParserConfigurationException {
        this._root = node;
        if (node instanceof Document) {
            this._document = (Document) node;
        } else if (node != null) {
            this._document = node.getOwnerDocument();
        } else {
            Document createDocument = DocumentHelper.createDocument();
            this._document = createDocument;
            this._root = createDocument;
        }
    }

    public Node getDOM() {
        return this._root;
    }

    public void characters(char[] cArr, int i, int i2) {
        Node node = (Node) this._nodeStk.peek();
        if (node != this._document) {
            node.appendChild(this._document.createTextNode(new String(cArr, i, i2)));
        }
    }

    public void startDocument() {
        this._nodeStk.push(this._root);
    }

    public void endDocument() {
        this._nodeStk.pop();
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) {
        Element createElementNS = this._document.createElementNS(str, str3);
        Vector vector = this._namespaceDecls;
        if (vector != null) {
            int size = vector.size();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                String str4 = (String) this._namespaceDecls.elementAt(i);
                if (str4 == null || str4.equals("")) {
                    createElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", XMLNS_PREFIX, (String) this._namespaceDecls.elementAt(i2));
                } else {
                    createElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", XMLNS_STRING + str4, (String) this._namespaceDecls.elementAt(i2));
                }
                i = i2 + 1;
            }
            this._namespaceDecls.clear();
        }
        int length = attributes.getLength();
        for (int i3 = 0; i3 < length; i3++) {
            if (attributes.getLocalName(i3) == null) {
                createElementNS.setAttribute(attributes.getQName(i3), attributes.getValue(i3));
            } else {
                createElementNS.setAttributeNS(attributes.getURI(i3), attributes.getQName(i3), attributes.getValue(i3));
            }
        }
        ((Node) this._nodeStk.peek()).appendChild(createElementNS);
        this._nodeStk.push(createElementNS);
    }

    public void endElement(String str, String str2, String str3) {
        this._nodeStk.pop();
    }

    public void startPrefixMapping(String str, String str2) {
        if (this._namespaceDecls == null) {
            this._namespaceDecls = new Vector(2);
        }
        this._namespaceDecls.addElement(str);
        this._namespaceDecls.addElement(str2);
    }

    public void processingInstruction(String str, String str2) {
        Node node = (Node) this._nodeStk.peek();
        ProcessingInstruction createProcessingInstruction = this._document.createProcessingInstruction(str, str2);
        if (createProcessingInstruction != null) {
            node.appendChild(createProcessingInstruction);
        }
    }

    public void comment(char[] cArr, int i, int i2) {
        Node node = (Node) this._nodeStk.peek();
        Comment createComment = this._document.createComment(new String(cArr, i, i2));
        if (createComment != null) {
            node.appendChild(createComment);
        }
    }
}
