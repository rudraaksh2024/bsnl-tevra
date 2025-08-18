package org.jsoup.nodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public abstract class Node implements Cloneable {
    static final List<Node> EmptyNodes = Collections.emptyList();
    static final String EmptyString = "";
    @Nullable
    Node parentNode;
    int siblingIndex;

    public abstract Attributes attributes();

    public abstract String baseUri();

    public abstract int childNodeSize();

    /* access modifiers changed from: protected */
    public abstract void doSetBaseUri(String str);

    public abstract Node empty();

    /* access modifiers changed from: protected */
    public abstract List<Node> ensureChildNodes();

    public boolean equals(@Nullable Object obj) {
        return this == obj;
    }

    /* access modifiers changed from: protected */
    public abstract boolean hasAttributes();

    public abstract String nodeName();

    /* access modifiers changed from: package-private */
    public void nodelistChanged() {
    }

    /* access modifiers changed from: package-private */
    public abstract void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException;

    protected Node() {
    }

    public boolean hasParent() {
        return this.parentNode != null;
    }

    public String attr(String str) {
        Validate.notNull(str);
        if (!hasAttributes()) {
            return "";
        }
        String ignoreCase = attributes().getIgnoreCase(str);
        if (ignoreCase.length() > 0) {
            return ignoreCase;
        }
        if (str.startsWith("abs:")) {
            return absUrl(str.substring(4));
        }
        return "";
    }

    public int attributesSize() {
        if (hasAttributes()) {
            return attributes().size();
        }
        return 0;
    }

    public Node attr(String str, String str2) {
        attributes().putIgnoreCase(NodeUtils.parser(this).settings().normalizeAttribute(str), str2);
        return this;
    }

    public boolean hasAttr(String str) {
        Validate.notNull(str);
        if (!hasAttributes()) {
            return false;
        }
        if (str.startsWith("abs:")) {
            String substring = str.substring(4);
            if (attributes().hasKeyIgnoreCase(substring) && !absUrl(substring).isEmpty()) {
                return true;
            }
        }
        return attributes().hasKeyIgnoreCase(str);
    }

    public Node removeAttr(String str) {
        Validate.notNull(str);
        if (hasAttributes()) {
            attributes().removeIgnoreCase(str);
        }
        return this;
    }

    public Node clearAttributes() {
        if (hasAttributes()) {
            Iterator<Attribute> it = attributes().iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }
        return this;
    }

    public void setBaseUri(String str) {
        Validate.notNull(str);
        doSetBaseUri(str);
    }

    public String absUrl(String str) {
        Validate.notEmpty(str);
        return (!hasAttributes() || !attributes().hasKeyIgnoreCase(str)) ? "" : StringUtil.resolve(baseUri(), attributes().getIgnoreCase(str));
    }

    public Node childNode(int i) {
        return ensureChildNodes().get(i);
    }

    public List<Node> childNodes() {
        if (childNodeSize() == 0) {
            return EmptyNodes;
        }
        List<Node> ensureChildNodes = ensureChildNodes();
        ArrayList arrayList = new ArrayList(ensureChildNodes.size());
        arrayList.addAll(ensureChildNodes);
        return Collections.unmodifiableList(arrayList);
    }

    public List<Node> childNodesCopy() {
        List<Node> ensureChildNodes = ensureChildNodes();
        ArrayList arrayList = new ArrayList(ensureChildNodes.size());
        for (Node clone : ensureChildNodes) {
            arrayList.add(clone.clone());
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public Node[] childNodesAsArray() {
        return (Node[]) ensureChildNodes().toArray(new Node[0]);
    }

    @Nullable
    public Node parent() {
        return this.parentNode;
    }

    @Nullable
    public final Node parentNode() {
        return this.parentNode;
    }

    public Node root() {
        while (true) {
            Node node = this.parentNode;
            if (node == null) {
                return this;
            }
            this = node;
        }
    }

    @Nullable
    public Document ownerDocument() {
        Node root = root();
        if (root instanceof Document) {
            return (Document) root;
        }
        return null;
    }

    public void remove() {
        Validate.notNull(this.parentNode);
        this.parentNode.removeChild(this);
    }

    public Node before(String str) {
        addSiblingHtml(this.siblingIndex, str);
        return this;
    }

    public Node before(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(this.siblingIndex, node);
        return this;
    }

    public Node after(String str) {
        addSiblingHtml(this.siblingIndex + 1, str);
        return this;
    }

    public Node after(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(this.siblingIndex + 1, node);
        return this;
    }

    private void addSiblingHtml(int i, String str) {
        Validate.notNull(str);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(i, (Node[]) NodeUtils.parser(this).parseFragmentInput(str, parent() instanceof Element ? (Element) parent() : null, baseUri()).toArray(new Node[0]));
    }

    public Node wrap(String str) {
        Element element;
        Validate.notEmpty(str);
        Node node = this.parentNode;
        if (node == null || !(node instanceof Element)) {
            element = this instanceof Element ? (Element) this : null;
        } else {
            element = (Element) node;
        }
        List<Node> parseFragmentInput = NodeUtils.parser(this).parseFragmentInput(str, element, baseUri());
        Node node2 = parseFragmentInput.get(0);
        if (!(node2 instanceof Element)) {
            return this;
        }
        Element element2 = (Element) node2;
        Element deepChild = getDeepChild(element2);
        Node node3 = this.parentNode;
        if (node3 != null) {
            node3.replaceChild(this, element2);
        }
        deepChild.addChildren(this);
        if (parseFragmentInput.size() > 0) {
            for (int i = 0; i < parseFragmentInput.size(); i++) {
                Node node4 = parseFragmentInput.get(i);
                if (element2 != node4) {
                    Node node5 = node4.parentNode;
                    if (node5 != null) {
                        node5.removeChild(node4);
                    }
                    element2.after(node4);
                }
            }
        }
        return this;
    }

    @Nullable
    public Node unwrap() {
        Validate.notNull(this.parentNode);
        List<Node> ensureChildNodes = ensureChildNodes();
        Node node = ensureChildNodes.size() > 0 ? ensureChildNodes.get(0) : null;
        this.parentNode.addChildren(this.siblingIndex, childNodesAsArray());
        remove();
        return node;
    }

    private Element getDeepChild(Element element) {
        Elements children = element.children();
        return children.size() > 0 ? getDeepChild((Element) children.get(0)) : element;
    }

    public void replaceWith(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.replaceChild(this, node);
    }

    /* access modifiers changed from: protected */
    public void setParentNode(Node node) {
        Validate.notNull(node);
        Node node2 = this.parentNode;
        if (node2 != null) {
            node2.removeChild(this);
        }
        this.parentNode = node;
    }

    /* access modifiers changed from: protected */
    public void replaceChild(Node node, Node node2) {
        Validate.isTrue(node.parentNode == this);
        Validate.notNull(node2);
        Node node3 = node2.parentNode;
        if (node3 != null) {
            node3.removeChild(node2);
        }
        int i = node.siblingIndex;
        ensureChildNodes().set(i, node2);
        node2.parentNode = this;
        node2.setSiblingIndex(i);
        node.parentNode = null;
    }

    /* access modifiers changed from: protected */
    public void removeChild(Node node) {
        Validate.isTrue(node.parentNode == this);
        int i = node.siblingIndex;
        ensureChildNodes().remove(i);
        reindexChildren(i);
        node.parentNode = null;
    }

    /* access modifiers changed from: protected */
    public void addChildren(Node... nodeArr) {
        List<Node> ensureChildNodes = ensureChildNodes();
        for (Node node : nodeArr) {
            reparentChild(node);
            ensureChildNodes.add(node);
            node.setSiblingIndex(ensureChildNodes.size() - 1);
        }
    }

    /* access modifiers changed from: protected */
    public void addChildren(int i, Node... nodeArr) {
        boolean z;
        boolean z2;
        Validate.notNull(nodeArr);
        if (nodeArr.length != 0) {
            List<Node> ensureChildNodes = ensureChildNodes();
            Node parent = nodeArr[0].parent();
            if (parent != null && parent.childNodeSize() == nodeArr.length) {
                List<Node> ensureChildNodes2 = parent.ensureChildNodes();
                int length = nodeArr.length;
                while (true) {
                    int i2 = length - 1;
                    z = true;
                    if (length <= 0) {
                        z2 = true;
                        break;
                    } else if (nodeArr[i2] != ensureChildNodes2.get(i2)) {
                        z2 = false;
                        break;
                    } else {
                        length = i2;
                    }
                }
                if (z2) {
                    if (childNodeSize() != 0) {
                        z = false;
                    }
                    parent.empty();
                    ensureChildNodes.addAll(i, Arrays.asList(nodeArr));
                    int length2 = nodeArr.length;
                    while (true) {
                        int i3 = length2 - 1;
                        if (length2 <= 0) {
                            break;
                        }
                        nodeArr[i3].parentNode = this;
                        length2 = i3;
                    }
                    if (!z || nodeArr[0].siblingIndex != 0) {
                        reindexChildren(i);
                        return;
                    }
                    return;
                }
            }
            Validate.noNullElements(nodeArr);
            for (Node reparentChild : nodeArr) {
                reparentChild(reparentChild);
            }
            ensureChildNodes.addAll(i, Arrays.asList(nodeArr));
            reindexChildren(i);
        }
    }

    /* access modifiers changed from: protected */
    public void reparentChild(Node node) {
        node.setParentNode(this);
    }

    private void reindexChildren(int i) {
        if (childNodeSize() != 0) {
            List<Node> ensureChildNodes = ensureChildNodes();
            while (i < ensureChildNodes.size()) {
                ensureChildNodes.get(i).setSiblingIndex(i);
                i++;
            }
        }
    }

    public List<Node> siblingNodes() {
        Node node = this.parentNode;
        if (node == null) {
            return Collections.emptyList();
        }
        List<Node> ensureChildNodes = node.ensureChildNodes();
        ArrayList arrayList = new ArrayList(ensureChildNodes.size() - 1);
        for (Node next : ensureChildNodes) {
            if (next != this) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    @Nullable
    public Node nextSibling() {
        Node node = this.parentNode;
        if (node == null) {
            return null;
        }
        List<Node> ensureChildNodes = node.ensureChildNodes();
        int i = this.siblingIndex + 1;
        if (ensureChildNodes.size() > i) {
            return ensureChildNodes.get(i);
        }
        return null;
    }

    @Nullable
    public Node previousSibling() {
        Node node = this.parentNode;
        if (node != null && this.siblingIndex > 0) {
            return node.ensureChildNodes().get(this.siblingIndex - 1);
        }
        return null;
    }

    public int siblingIndex() {
        return this.siblingIndex;
    }

    /* access modifiers changed from: protected */
    public void setSiblingIndex(int i) {
        this.siblingIndex = i;
    }

    public Node traverse(NodeVisitor nodeVisitor) {
        Validate.notNull(nodeVisitor);
        NodeTraversor.traverse(nodeVisitor, this);
        return this;
    }

    public Node filter(NodeFilter nodeFilter) {
        Validate.notNull(nodeFilter);
        NodeTraversor.filter(nodeFilter, this);
        return this;
    }

    public String outerHtml() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        outerHtml(borrowBuilder);
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    /* access modifiers changed from: protected */
    public void outerHtml(Appendable appendable) {
        NodeTraversor.traverse((NodeVisitor) new OuterHtmlVisitor(appendable, NodeUtils.outputSettings(this)), this);
    }

    public <T extends Appendable> T html(T t) {
        outerHtml(t);
        return t;
    }

    public String toString() {
        return outerHtml();
    }

    /* access modifiers changed from: protected */
    public void indent(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        appendable.append(10).append(StringUtil.padding(i * outputSettings.indentAmount()));
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean hasSameValue(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return outerHtml().equals(((Node) obj).outerHtml());
    }

    public Node clone() {
        Node doClone = doClone((Node) null);
        LinkedList linkedList = new LinkedList();
        linkedList.add(doClone);
        while (!linkedList.isEmpty()) {
            Node node = (Node) linkedList.remove();
            int childNodeSize = node.childNodeSize();
            for (int i = 0; i < childNodeSize; i++) {
                List<Node> ensureChildNodes = node.ensureChildNodes();
                Node doClone2 = ensureChildNodes.get(i).doClone(node);
                ensureChildNodes.set(i, doClone2);
                linkedList.add(doClone2);
            }
        }
        return doClone;
    }

    public Node shallowClone() {
        return doClone((Node) null);
    }

    /* access modifiers changed from: protected */
    public Node doClone(@Nullable Node node) {
        int i;
        try {
            Node node2 = (Node) super.clone();
            node2.parentNode = node;
            if (node == null) {
                i = 0;
            } else {
                i = this.siblingIndex;
            }
            node2.siblingIndex = i;
            return node2;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class OuterHtmlVisitor implements NodeVisitor {
        private final Appendable accum;
        private final Document.OutputSettings out;

        OuterHtmlVisitor(Appendable appendable, Document.OutputSettings outputSettings) {
            this.accum = appendable;
            this.out = outputSettings;
            outputSettings.prepareEncoder();
        }

        public void head(Node node, int i) {
            try {
                node.outerHtmlHead(this.accum, i, this.out);
            } catch (IOException e) {
                throw new SerializationException((Throwable) e);
            }
        }

        public void tail(Node node, int i) {
            if (!node.nodeName().equals("#text")) {
                try {
                    node.outerHtmlTail(this.accum, i, this.out);
                } catch (IOException e) {
                    throw new SerializationException((Throwable) e);
                }
            }
        }
    }
}
