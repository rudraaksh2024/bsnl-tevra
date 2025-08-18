package org.apache.xmlbeans.impl.xpath.saxon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import net.sf.saxon.Configuration;
import net.sf.saxon.dom.DOMNodeWrapper;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.om.SequenceTool;
import net.sf.saxon.sxpath.IndependentContext;
import net.sf.saxon.sxpath.XPathDynamicContext;
import net.sf.saxon.sxpath.XPathEvaluator;
import net.sf.saxon.sxpath.XPathExpression;
import net.sf.saxon.sxpath.XPathVariable;
import net.sf.saxon.tree.wrapper.VirtualNode;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.xpath.Path;
import org.apache.xmlbeans.impl.xpath.XPath;
import org.apache.xmlbeans.impl.xpath.XPathEngine;
import org.w3c.dom.Node;

public class SaxonXPath implements Path {
    private String contextVar;
    private String defaultNS;
    private final Map<String, String> namespaceMap;
    private String path;

    public SaxonXPath(String str, String str2, Map<String, String> map) {
        HashMap hashMap = new HashMap();
        this.namespaceMap = hashMap;
        this.path = str;
        this.contextVar = str2;
        this.defaultNS = map.get(XPath._DEFAULT_ELT_NS);
        hashMap.putAll(map);
        hashMap.remove(XPath._DEFAULT_ELT_NS);
    }

    public XPathEngine execute(Cur cur, XmlOptions xmlOptions) {
        return new SaxonXPathEngine(this, cur);
    }

    public List selectNodes(Object obj) {
        try {
            Node node = (Node) obj;
            Configuration configuration = new Configuration();
            IndependentContext independentContext = new IndependentContext(configuration);
            String str = this.defaultNS;
            if (str != null) {
                independentContext.setDefaultElementNamespace(str);
            }
            this.namespaceMap.forEach(new SaxonXPath$$ExternalSyntheticLambda0(independentContext));
            NodeInfo unravel = configuration.unravel(new DOMSource(node));
            XPathEvaluator xPathEvaluator = new XPathEvaluator(configuration);
            xPathEvaluator.setStaticContext(independentContext);
            XPathVariable declareVariable = independentContext.declareVariable("", this.contextVar);
            XPathExpression createExpression = xPathEvaluator.createExpression(this.path);
            XPathDynamicContext createDynamicContext = createExpression.createDynamicContext((Item) null);
            createDynamicContext.setContextItem(unravel);
            createDynamicContext.setVariable(declareVariable, unravel);
            List<DOMNodeWrapper> evaluate = createExpression.evaluate(createDynamicContext);
            ArrayList arrayList = new ArrayList(evaluate.size());
            for (DOMNodeWrapper dOMNodeWrapper : evaluate) {
                if (dOMNodeWrapper instanceof DOMNodeWrapper) {
                    arrayList.add(getUnderlyingNode(dOMNodeWrapper));
                } else if (dOMNodeWrapper instanceof NodeInfo) {
                    arrayList.add(dOMNodeWrapper.getStringValue());
                } else {
                    arrayList.add(SequenceTool.convertToJava(dOMNodeWrapper));
                }
            }
            return arrayList;
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public List selectPath(Object obj) {
        return selectNodes(obj);
    }

    private static Node getUnderlyingNode(VirtualNode virtualNode) {
        while (true) {
            boolean z = r1 instanceof VirtualNode;
            Object obj = virtualNode;
            if (!z) {
                return (Node) obj;
            }
            obj = ((VirtualNode) obj).getUnderlyingNode();
        }
    }
}
