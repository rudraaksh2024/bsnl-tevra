package org.jsoup.helper;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import javax.annotation.Nullable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.Selector;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class W3CDom {
    public static final String SourceProperty = "jsoupSource";
    public static final String XPathFactoryProperty = "javax.xml.xpath.XPathFactory:jsoup";
    protected DocumentBuilderFactory factory;

    public W3CDom() {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        this.factory = newInstance;
        newInstance.setNamespaceAware(true);
    }

    public static Document convert(org.jsoup.nodes.Document document) {
        return new W3CDom().fromJsoup(document);
    }

    public static String asString(Document document, @Nullable Map<String, String> map) {
        try {
            DOMSource dOMSource = new DOMSource(document);
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            if (map != null) {
                newTransformer.setOutputProperties(propertiesFromMap(map));
            }
            if (document.getDoctype() != null) {
                DocumentType doctype = document.getDoctype();
                if (!StringUtil.isBlank(doctype.getPublicId())) {
                    newTransformer.setOutputProperty("doctype-public", doctype.getPublicId());
                }
                if (!StringUtil.isBlank(doctype.getSystemId())) {
                    newTransformer.setOutputProperty("doctype-system", doctype.getSystemId());
                } else if (doctype.getName().equalsIgnoreCase("html") && StringUtil.isBlank(doctype.getPublicId()) && StringUtil.isBlank(doctype.getSystemId())) {
                    newTransformer.setOutputProperty("doctype-system", "about:legacy-compat");
                }
            }
            newTransformer.transform(dOMSource, streamResult);
            return stringWriter.toString();
        } catch (TransformerException e) {
            throw new IllegalStateException(e);
        }
    }

    static Properties propertiesFromMap(Map<String, String> map) {
        Properties properties = new Properties();
        properties.putAll(map);
        return properties;
    }

    public static HashMap<String, String> OutputHtml() {
        return methodMap("html");
    }

    public static HashMap<String, String> OutputXml() {
        return methodMap("xml");
    }

    private static HashMap<String, String> methodMap(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("method", str);
        return hashMap;
    }

    public Document fromJsoup(org.jsoup.nodes.Document document) {
        return fromJsoup((Element) document);
    }

    public Document fromJsoup(Element element) {
        Validate.notNull(element);
        try {
            DocumentBuilder newDocumentBuilder = this.factory.newDocumentBuilder();
            DOMImplementation dOMImplementation = newDocumentBuilder.getDOMImplementation();
            Document newDocument = newDocumentBuilder.newDocument();
            org.jsoup.nodes.Document ownerDocument = element.ownerDocument();
            org.jsoup.nodes.DocumentType documentType = ownerDocument != null ? ownerDocument.documentType() : null;
            if (documentType != null) {
                newDocument.appendChild(dOMImplementation.createDocumentType(documentType.name(), documentType.publicId(), documentType.systemId()));
            }
            newDocument.setXmlStandalone(true);
            convert(element, newDocument);
            return newDocument;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public void convert(org.jsoup.nodes.Document document, Document document2) {
        convert((Element) document, document2);
    }

    public void convert(Element element, Document document) {
        org.jsoup.nodes.Document ownerDocument = element.ownerDocument();
        if (ownerDocument != null && !StringUtil.isBlank(ownerDocument.location())) {
            document.setDocumentURI(ownerDocument.location());
        }
        if (element instanceof org.jsoup.nodes.Document) {
            element = element.child(0);
        }
        NodeTraversor.traverse((NodeVisitor) new W3CBuilder(document), (Node) element);
    }

    public NodeList selectXpath(String str, Document document) {
        XPathFactory xPathFactory;
        Validate.notEmpty(str);
        Validate.notNull(document);
        try {
            if (System.getProperty(XPathFactoryProperty) != null) {
                xPathFactory = XPathFactory.newInstance("jsoup");
            } else {
                xPathFactory = XPathFactory.newInstance();
            }
            NodeList nodeList = (NodeList) xPathFactory.newXPath().compile(str).evaluate(document, XPathConstants.NODESET);
            Validate.notNull(nodeList);
            return nodeList;
        } catch (XPathExpressionException | XPathFactoryConfigurationException e) {
            throw new Selector.SelectorParseException("Could not evaluate XPath query [%s]: %s", str, e.getMessage());
        }
    }

    public <T extends Node> List<T> sourceNodes(NodeList nodeList, Class<T> cls) {
        Validate.notNull(nodeList);
        Validate.notNull(cls);
        ArrayList arrayList = new ArrayList(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); i++) {
            Object userData = nodeList.item(i).getUserData(SourceProperty);
            if (cls.isInstance(userData)) {
                arrayList.add((Node) cls.cast(userData));
            }
        }
        return arrayList;
    }

    public String asString(Document document) {
        return asString(document, (Map<String, String>) null);
    }

    protected static class W3CBuilder implements NodeVisitor {
        private static final String xmlnsKey = "xmlns";
        private static final String xmlnsPrefix = "xmlns:";
        private org.w3c.dom.Node dest;
        private final Document doc;
        private final Stack<HashMap<String, String>> namespacesStack;

        public W3CBuilder(Document document) {
            Stack<HashMap<String, String>> stack = new Stack<>();
            this.namespacesStack = stack;
            this.doc = document;
            stack.push(new HashMap());
            this.dest = document;
        }

        public void head(Node node, int i) {
            org.w3c.dom.Element element;
            this.namespacesStack.push(new HashMap(this.namespacesStack.peek()));
            if (node instanceof Element) {
                Element element2 = (Element) node;
                String str = (String) this.namespacesStack.peek().get(updateNamespaces(element2));
                String tagName = element2.tagName();
                if (str == null) {
                    try {
                        if (tagName.contains(ParameterizedMessage.ERROR_MSG_SEPARATOR)) {
                            element = this.doc.createElementNS("", tagName);
                            copyAttributes(element2, element);
                            append(element, element2);
                            this.dest = element;
                        }
                    } catch (DOMException unused) {
                        append(this.doc.createTextNode("<" + tagName + ">"), element2);
                        return;
                    }
                }
                element = this.doc.createElementNS(str, tagName);
                copyAttributes(element2, element);
                append(element, element2);
                this.dest = element;
            } else if (node instanceof TextNode) {
                TextNode textNode = (TextNode) node;
                append(this.doc.createTextNode(textNode.getWholeText()), textNode);
            } else if (node instanceof Comment) {
                Comment comment = (Comment) node;
                append(this.doc.createComment(comment.getData()), comment);
            } else if (node instanceof DataNode) {
                DataNode dataNode = (DataNode) node;
                append(this.doc.createTextNode(dataNode.getWholeData()), dataNode);
            }
        }

        private void append(org.w3c.dom.Node node, Node node2) {
            node.setUserData(W3CDom.SourceProperty, node2, (UserDataHandler) null);
            this.dest.appendChild(node);
        }

        public void tail(Node node, int i) {
            if ((node instanceof Element) && (this.dest.getParentNode() instanceof org.w3c.dom.Element)) {
                this.dest = this.dest.getParentNode();
            }
            this.namespacesStack.pop();
        }

        private void copyAttributes(Node node, org.w3c.dom.Element element) {
            Iterator<Attribute> it = node.attributes().iterator();
            while (it.hasNext()) {
                Attribute next = it.next();
                String validKey = Attribute.getValidKey(next.getKey(), Document.OutputSettings.Syntax.xml);
                if (validKey != null) {
                    element.setAttribute(validKey, next.getValue());
                }
            }
        }

        private String updateNamespaces(Element element) {
            String str;
            Iterator<Attribute> it = element.attributes().iterator();
            while (true) {
                str = "";
                if (!it.hasNext()) {
                    break;
                }
                Attribute next = it.next();
                String key = next.getKey();
                if (!key.equals("xmlns")) {
                    if (key.startsWith("xmlns:")) {
                        str = key.substring(6);
                    }
                }
                this.namespacesStack.peek().put(str, next.getValue());
            }
            int indexOf = element.tagName().indexOf(58);
            return indexOf > 0 ? element.tagName().substring(0, indexOf) : str;
        }
    }
}
