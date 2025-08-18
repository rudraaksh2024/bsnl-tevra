package org.apache.xmlbeans.impl.values;

import javax.xml.namespace.QName;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;

public class JavaQNameHolder extends XmlObjectBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final NamespaceManager PRETTY_PREFIXER = new PrettyNamespaceManager();
    private QName _value;

    /* access modifiers changed from: protected */
    public int get_wscanon_rule() {
        return 1;
    }

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_QNAME;
    }

    private static class PrettyNamespaceManager implements NamespaceManager {
        private PrettyNamespaceManager() {
        }

        public String find_prefix_for_nsuri(String str, String str2) {
            return QNameHelper.suggestPrefix(str);
        }

        public String getNamespaceForPrefix(String str) {
            throw new RuntimeException("Should not be called");
        }
    }

    public String compute_text(NamespaceManager namespaceManager) {
        if (namespaceManager == null) {
            namespaceManager = PRETTY_PREFIXER;
        }
        String namespaceURI = this._value.getNamespaceURI();
        String localPart = this._value.getLocalPart();
        if (namespaceURI == null || namespaceURI.length() == 0) {
            return localPart;
        }
        String find_prefix_for_nsuri = namespaceManager.find_prefix_for_nsuri(namespaceURI, (String) null);
        return "".equals(find_prefix_for_nsuri) ? localPart : find_prefix_for_nsuri + ParameterizedMessage.ERROR_MSG_SEPARATOR + localPart;
    }

    public static QName validateLexical(String str, ValidationContext validationContext, PrefixResolver prefixResolver) {
        try {
            return parse(str, prefixResolver);
        } catch (XmlValueOutOfRangeException e) {
            validationContext.invalid(e.getMessage());
            return null;
        }
    }

    private static QName parse(String str, PrefixResolver prefixResolver) {
        String str2;
        String str3;
        String str4;
        int length = str.length();
        while (length > 0 && XMLChar.isSpace(str.charAt(length - 1))) {
            length--;
        }
        int i = 0;
        while (i < length && XMLChar.isSpace(str.charAt(i))) {
            i++;
        }
        int indexOf = str.indexOf(58, i);
        String str5 = "";
        if (indexOf >= 0) {
            str2 = str.substring(i, indexOf);
            str3 = str.substring(indexOf + 1, length);
        } else {
            str3 = str.substring(i, length);
            str2 = str5;
        }
        if (str2.length() > 0 && !XMLChar.isValidNCName(str2)) {
            throw new XmlValueOutOfRangeException(XmlErrorCodes.QNAME, new Object[]{"Prefix not a valid NCName in '" + str + "'"});
        } else if (XMLChar.isValidNCName(str3)) {
            if (prefixResolver == null) {
                str4 = null;
            } else {
                str4 = prefixResolver.getNamespaceForPrefix(str2);
            }
            if (str4 != null) {
                str5 = str4;
            } else if (str2.length() > 0) {
                throw new XmlValueOutOfRangeException(XmlErrorCodes.QNAME, new Object[]{"Can't resolve prefix '" + str2 + "'"});
            }
            if (str2 == null || str2.length() <= 0) {
                return new QName(str5, str3);
            }
            return new QName(str5, str3, str2);
        } else {
            throw new XmlValueOutOfRangeException(XmlErrorCodes.QNAME, new Object[]{"Localname not a valid NCName in '" + str + "'"});
        }
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        PrefixResolver current = NamespaceContext.getCurrent();
        if (current == null && has_store()) {
            current = get_store();
        }
        this._value = parse(str, current);
    }

    /* access modifiers changed from: protected */
    public void set_QName(QName qName) {
        if (has_store()) {
            get_store().find_prefix_for_nsuri(qName.getNamespaceURI(), (String) null);
        }
        this._value = qName;
    }

    /* access modifiers changed from: protected */
    public void set_xmlanysimple(XmlAnySimpleType xmlAnySimpleType) {
        this._value = parse(xmlAnySimpleType.getStringValue(), NamespaceContext.getCurrent());
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = null;
    }

    public QName getQNameValue() {
        check_dated();
        return this._value;
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return this._value.equals(((XmlObjectBase) xmlObject).getQNameValue());
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        return this._value.hashCode();
    }
}
