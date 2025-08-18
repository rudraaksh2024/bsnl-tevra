package org.apache.xmlbeans.impl.xpath.saxon;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathException;
import net.sf.saxon.Configuration;
import net.sf.saxon.dom.DOMNodeWrapper;
import net.sf.saxon.dom.DocumentWrapper;
import net.sf.saxon.dom.NodeOverNodeInfo;
import net.sf.saxon.ma.map.HashTrieMap;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.type.BuiltInAtomicType;
import net.sf.saxon.value.AnyURIValue;
import net.sf.saxon.value.BigDecimalValue;
import net.sf.saxon.value.BigIntegerValue;
import net.sf.saxon.value.BooleanValue;
import net.sf.saxon.value.DateTimeValue;
import net.sf.saxon.value.DateValue;
import net.sf.saxon.value.DoubleValue;
import net.sf.saxon.value.DurationValue;
import net.sf.saxon.value.FloatValue;
import net.sf.saxon.value.GDayValue;
import net.sf.saxon.value.GMonthDayValue;
import net.sf.saxon.value.GMonthValue;
import net.sf.saxon.value.GYearMonthValue;
import net.sf.saxon.value.GYearValue;
import net.sf.saxon.value.HexBinaryValue;
import net.sf.saxon.value.Int64Value;
import net.sf.saxon.value.ObjectValue;
import net.sf.saxon.value.QNameValue;
import net.sf.saxon.value.SaxonDuration;
import net.sf.saxon.value.SaxonXMLGregorianCalendar;
import net.sf.saxon.value.StringValue;
import net.sf.saxon.value.TimeValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDate;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlTokenSource;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Cursor;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.xpath.XQuery;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class SaxonXQuery implements XQuery {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = LogManager.getLogger((Class<?>) SaxonXQuery.class);
    private Cur _cur;
    private XmlOptions _options;
    private long _version;
    private final Configuration config;
    private final String contextVar;
    private final XQueryExpression xquery;

    public SaxonXQuery(String str, String str2, Integer num, XmlOptions xmlOptions) {
        this._options = xmlOptions;
        Configuration configuration = new Configuration();
        this.config = configuration;
        StaticQueryContext newStaticQueryContext = configuration.newStaticQueryContext();
        Map<String, String> loadAdditionalNamespaces = xmlOptions.getLoadAdditionalNamespaces();
        if (loadAdditionalNamespaces != null) {
            newStaticQueryContext.getClass();
            loadAdditionalNamespaces.forEach(new SaxonXQuery$$ExternalSyntheticLambda0(newStaticQueryContext));
        }
        this.contextVar = str2;
        try {
            this.xquery = newStaticQueryContext.compileQuery(str.substring(0, num.intValue()) + " declare variable $" + str2 + " external;" + str.substring(num.intValue()));
        } catch (TransformerException e) {
            throw new XmlRuntimeException((Throwable) e);
        }
    }

    public XmlObject[] objectExecute(Cur cur, XmlOptions xmlOptions) {
        Cur cur2;
        this._version = cur.getLocale().version();
        this._cur = cur.weakCur(this);
        this._options = xmlOptions;
        List<Object> execQuery = execQuery(this._cur.getDom(), XmlOptions.maskNull(xmlOptions).getXqueryVariables());
        XmlObject[] xmlObjectArr = new XmlObject[execQuery.size()];
        int i = 0;
        while (i < execQuery.size()) {
            Locale locale = Locale.getLocale(this._cur.getLocale().getSchemaTypeLoader(), this._options);
            locale.enter();
            Object obj = execQuery.get(i);
            try {
                if (!(obj instanceof Node)) {
                    cur2 = locale.load("<xml-fragment/>").tempCur();
                    cur2.setValue(obj.toString());
                    Locale.autoTypeDocument(cur2, getType(obj), (XmlOptions) null);
                    xmlObjectArr[i] = cur2.getObject();
                } else {
                    cur2 = loadNode(locale, (Node) obj);
                }
                xmlObjectArr[i] = cur2.getObject();
                locale.exit();
                cur2.release();
                i++;
            } catch (XmlException e) {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                locale.exit();
                throw th;
            }
        }
        release();
        return xmlObjectArr;
    }

    public XmlCursor cursorExecute(Cur cur, XmlOptions xmlOptions) {
        Cursor cursor;
        this._version = cur.getLocale().version();
        this._cur = cur.weakCur(this);
        this._options = xmlOptions;
        List<Object> execQuery = execQuery(this._cur.getDom(), XmlOptions.maskNull(xmlOptions).getXqueryVariables());
        Locale locale = Locale.getLocale(this._cur.getLocale().getSchemaTypeLoader(), this._options);
        locale.enter();
        Cur.CurLoadContext curLoadContext = new Cur.CurLoadContext(locale, this._options);
        int i = 0;
        while (true) {
            cursor = null;
            try {
                if (i >= execQuery.size()) {
                    break;
                }
                loadNodeHelper(locale, (Node) execQuery.get(i), curLoadContext);
                i++;
            } catch (XmlException e) {
                LOG.atInfo().withThrowable(e).log("Can't autotype document");
                locale.exit();
            } catch (Throwable th) {
                locale.exit();
                throw th;
            }
        }
        Cur finish = curLoadContext.finish();
        Locale.associateSourceName(cur, this._options);
        Locale.autoTypeDocument(cur, (SchemaType) null, this._options);
        Cursor cursor2 = new Cursor(finish);
        locale.exit();
        cursor = cursor2;
        release();
        return cursor;
    }

    public List<Object> execQuery(Object obj, Map<String, Object> map) {
        Document document;
        try {
            Node node = (Node) obj;
            if (node.getNodeType() == 9) {
                document = (Document) node;
            } else {
                document = node.getOwnerDocument();
            }
            DocumentWrapper documentWrapper = new DocumentWrapper(document, (String) null, this.config);
            DOMNodeWrapper wrap = documentWrapper.wrap(node);
            DynamicQueryContext dynamicQueryContext = new DynamicQueryContext(this.config);
            dynamicQueryContext.setContextItem(wrap);
            dynamicQueryContext.setParameter(new StructuredQName("", (String) null, this.contextVar), wrap);
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    StructuredQName structuredQName = new StructuredQName("", (String) null, (String) next.getKey());
                    Object value = next.getValue();
                    if (value instanceof XmlTokenSource) {
                        dynamicQueryContext.setParameter(structuredQName, documentWrapper.wrap(((XmlTokenSource) value).getDomNode()));
                    } else {
                        dynamicQueryContext.setParameter(structuredQName, objectToItem(value, this.config));
                    }
                }
            }
            List<Object> evaluate = this.xquery.evaluate(dynamicQueryContext);
            ListIterator<Object> listIterator = evaluate.listIterator();
            while (listIterator.hasNext()) {
                Object next2 = listIterator.next();
                if (next2 instanceof NodeInfo) {
                    listIterator.set(NodeOverNodeInfo.wrap((NodeInfo) next2));
                }
            }
            return evaluate;
        } catch (XPathException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e2) {
            throw new RuntimeException("Error binding " + this.contextVar, e2);
        }
    }

    private static Item objectToItem(Object obj, Configuration configuration) throws XPathException, net.sf.saxon.trans.XPathException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return BooleanValue.get(((Boolean) obj).booleanValue());
        }
        if (obj instanceof byte[]) {
            return new HexBinaryValue((byte[]) obj);
        }
        boolean z = false;
        if (obj instanceof Byte) {
            return new Int64Value((long) ((Byte) obj).byteValue(), BuiltInAtomicType.BYTE, false);
        }
        if (obj instanceof Float) {
            return new FloatValue(((Float) obj).floatValue());
        }
        if (obj instanceof Double) {
            return new DoubleValue(((Double) obj).doubleValue());
        }
        if (obj instanceof Integer) {
            return new Int64Value((long) ((Integer) obj).intValue(), BuiltInAtomicType.INT, false);
        }
        if (obj instanceof Long) {
            return new Int64Value(((Long) obj).longValue(), BuiltInAtomicType.LONG, false);
        }
        if (obj instanceof Short) {
            return new Int64Value((long) ((Short) obj).shortValue(), BuiltInAtomicType.SHORT, false);
        }
        if (obj instanceof String) {
            return new StringValue((String) obj);
        }
        if (obj instanceof BigDecimal) {
            return new BigDecimalValue((BigDecimal) obj);
        }
        if (obj instanceof BigInteger) {
            return new BigIntegerValue((BigInteger) obj);
        }
        if (obj instanceof SaxonDuration) {
            return ((SaxonDuration) obj).getDurationValue();
        }
        if (obj instanceof Duration) {
            Duration duration = (Duration) obj;
            if (duration.getSign() >= 0) {
                z = true;
            }
            return new DurationValue(z, duration.getYears(), duration.getMonths(), duration.getDays(), duration.getHours(), duration.getMinutes(), (long) duration.getSeconds(), 0);
        } else if (obj instanceof SaxonXMLGregorianCalendar) {
            return ((SaxonXMLGregorianCalendar) obj).toCalendarValue();
        } else {
            if (obj instanceof XMLGregorianCalendar) {
                QName xMLSchemaType = ((XMLGregorianCalendar) obj).getXMLSchemaType();
                if (xMLSchemaType.equals(DatatypeConstants.DATETIME)) {
                    return DateTimeValue.makeDateTimeValue(obj.toString(), configuration.getConversionRules()).asAtomic();
                }
                if (xMLSchemaType.equals(DatatypeConstants.DATE)) {
                    return DateValue.makeDateValue(obj.toString(), configuration.getConversionRules()).asAtomic();
                }
                if (xMLSchemaType.equals(DatatypeConstants.TIME)) {
                    return TimeValue.makeTimeValue(obj.toString()).asAtomic();
                }
                if (xMLSchemaType.equals(DatatypeConstants.GYEAR)) {
                    return GYearValue.makeGYearValue(obj.toString(), configuration.getConversionRules()).asAtomic();
                }
                if (xMLSchemaType.equals(DatatypeConstants.GYEARMONTH)) {
                    return GYearMonthValue.makeGYearMonthValue(obj.toString(), configuration.getConversionRules()).asAtomic();
                }
                if (xMLSchemaType.equals(DatatypeConstants.GMONTH)) {
                    String obj2 = obj.toString();
                    if (obj2.endsWith("--")) {
                        obj2 = obj2.substring(0, obj2.length() - 2);
                    }
                    return GMonthValue.makeGMonthValue(obj2).asAtomic();
                } else if (xMLSchemaType.equals(DatatypeConstants.GMONTHDAY)) {
                    return GMonthDayValue.makeGMonthDayValue(obj.toString()).asAtomic();
                } else {
                    if (xMLSchemaType.equals(DatatypeConstants.GDAY)) {
                        return GDayValue.makeGDayValue(obj.toString()).asAtomic();
                    }
                    throw new AssertionError("Unknown Gregorian date type");
                }
            } else if (obj instanceof QName) {
                QName qName = (QName) obj;
                return new QNameValue(qName.getPrefix(), qName.getNamespaceURI(), qName.getLocalPart());
            } else if (obj instanceof URI) {
                return new AnyURIValue(obj.toString());
            } else {
                if (!(obj instanceof Map)) {
                    return new ObjectValue(obj);
                }
                HashTrieMap hashTrieMap = new HashTrieMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    hashTrieMap.initialPut(objectToItem(entry.getKey(), configuration), objectToItem(entry.getValue(), configuration));
                }
                return hashTrieMap;
            }
        }
    }

    private SchemaType getType(Object obj) {
        if (obj instanceof Integer) {
            return XmlInteger.type;
        }
        if (obj instanceof Double) {
            return XmlDouble.type;
        }
        if (obj instanceof Long) {
            return XmlLong.type;
        }
        if (obj instanceof Float) {
            return XmlFloat.type;
        }
        if (obj instanceof BigDecimal) {
            return XmlDecimal.type;
        }
        if (obj instanceof Boolean) {
            return XmlBoolean.type;
        }
        if (obj instanceof String) {
            return XmlString.type;
        }
        if (obj instanceof Date) {
            return XmlDate.type;
        }
        return XmlAnySimpleType.type;
    }

    public void release() {
        Cur cur = this._cur;
        if (cur != null) {
            cur.release();
            this._cur = null;
        }
    }

    private Cur loadNode(Locale locale, Node node) {
        Cur.CurLoadContext curLoadContext = new Cur.CurLoadContext(locale, this._options);
        try {
            loadNodeHelper(locale, node, curLoadContext);
            Cur finish = curLoadContext.finish();
            Locale.associateSourceName(finish, this._options);
            Locale.autoTypeDocument(finish, (SchemaType) null, this._options);
            return finish;
        } catch (Exception e) {
            throw new XmlRuntimeException(e.getMessage(), e);
        }
    }

    private void loadNodeHelper(Locale locale, Node node, Locale.LoadContext loadContext) {
        if (node.getNodeType() == 2) {
            loadContext.attr(new QName(node.getNamespaceURI(), node.getLocalName(), node.getPrefix()), node.getNodeValue());
        } else {
            locale.loadNode(node, loadContext);
        }
    }
}
