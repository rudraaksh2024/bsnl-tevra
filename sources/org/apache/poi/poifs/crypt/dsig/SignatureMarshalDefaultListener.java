package org.apache.poi.poifs.crypt.dsig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.traversal.DocumentTraversal;

public class SignatureMarshalDefaultListener implements SignatureMarshalListener {
    private static final List<String> DIRECT_NS = Arrays.asList(new String[]{"http://schemas.openxmlformats.org/package/2006/digital-signature", SignatureFacet.MS_DIGSIG_NS});
    private static final Set<String> IGNORE_NS = new HashSet(Arrays.asList(new String[]{null, "http://www.w3.org/2000/xmlns/", SignatureFacet.XML_DIGSIG_NS}));
    private static final String OBJECT_TAG = "Object";

    public void handleElement(SignatureInfo signatureInfo, Document document, EventTarget eventTarget, EventListener eventListener) {
        forEachElement(document.getElementsByTagName(OBJECT_TAG), new SignatureMarshalDefaultListener$$ExternalSyntheticLambda1(this, (DocumentTraversal) document, signatureInfo.getSignatureConfig().getNamespacePrefixes(), new HashMap()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$handleElement$2$org-apache-poi-poifs-crypt-dsig-SignatureMarshalDefaultListener  reason: not valid java name */
    public /* synthetic */ void m2220lambda$handleElement$2$orgapachepoipoifscryptdsigSignatureMarshalDefaultListener(DocumentTraversal documentTraversal, Map map, Map map2, Element element) {
        forEachElement(element.getChildNodes(), new SignatureMarshalDefaultListener$$ExternalSyntheticLambda2(this, documentTraversal, map, map2));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$null$1$org-apache-poi-poifs-crypt-dsig-SignatureMarshalDefaultListener  reason: not valid java name */
    public /* synthetic */ void m2221lambda$null$1$orgapachepoipoifscryptdsigSignatureMarshalDefaultListener(DocumentTraversal documentTraversal, Map map, Map map2, Element element) {
        getAllNamespaces(documentTraversal, element, map, map2);
        map2.forEach(new SignatureMarshalDefaultListener$$ExternalSyntheticLambda0(element));
    }

    private static void forEachElement(NodeList nodeList, Consumer<Element> consumer) {
        int length = nodeList.getLength();
        for (int i = 0; i < length; i++) {
            Node item = nodeList.item(i);
            if (item instanceof Element) {
                consumer.accept((Element) item);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0012 A[Catch:{ all -> 0x002e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void getAllNamespaces(org.w3c.dom.traversal.DocumentTraversal r5, org.w3c.dom.Element r6, java.util.Map<java.lang.String, java.lang.String> r7, java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            r4 = this;
            r8.clear()
            r0 = 1
            r1 = 0
            r2 = 0
            org.w3c.dom.traversal.NodeIterator r5 = r5.createNodeIterator(r6, r0, r1, r2)
        L_0x000a:
            org.w3c.dom.Node r6 = r5.nextNode()     // Catch:{ all -> 0x002e }
            org.w3c.dom.Element r6 = (org.w3c.dom.Element) r6     // Catch:{ all -> 0x002e }
            if (r6 == 0) goto L_0x002a
            r4.setPrefix(r6, r7, r8)     // Catch:{ all -> 0x002e }
            org.w3c.dom.NamedNodeMap r6 = r6.getAttributes()     // Catch:{ all -> 0x002e }
            int r0 = r6.getLength()     // Catch:{ all -> 0x002e }
            r1 = r2
        L_0x001e:
            if (r1 >= r0) goto L_0x000a
            org.w3c.dom.Node r3 = r6.item(r1)     // Catch:{ all -> 0x002e }
            r4.setPrefix(r3, r7, r8)     // Catch:{ all -> 0x002e }
            int r1 = r1 + 1
            goto L_0x001e
        L_0x002a:
            r5.detach()
            return
        L_0x002e:
            r4 = move-exception
            r5.detach()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.SignatureMarshalDefaultListener.getAllNamespaces(org.w3c.dom.traversal.DocumentTraversal, org.w3c.dom.Element, java.util.Map, java.util.Map):void");
    }

    private void setPrefix(Node node, Map<String, String> map, Map<String, String> map2) {
        String namespaceURI = node.getNamespaceURI();
        String str = map.get(namespaceURI);
        if (!IGNORE_NS.contains(namespaceURI)) {
            if (str != null) {
                node.setPrefix(str);
            }
            if (DIRECT_NS.contains(namespaceURI)) {
                setXmlns(node, str, namespaceURI);
            } else {
                map2.put(namespaceURI, str);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void setXmlns(Node node, String str, String str2) {
        if ((node instanceof Element) && !str2.equals(node.getParentNode().getNamespaceURI())) {
            ((Element) node).setAttributeNS("http://www.w3.org/2000/xmlns/", Sax2Dom.XMLNS_PREFIX + (str == null ? "" : ParameterizedMessage.ERROR_MSG_SEPARATOR + str), str2);
        }
    }
}
