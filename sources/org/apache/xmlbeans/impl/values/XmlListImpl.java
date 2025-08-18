package org.apache.xmlbeans.impl.values;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlSimpleList;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.XMLChar;

public class XmlListImpl extends XmlObjectBase implements XmlAnySimpleType {
    private static final String[] EMPTY_STRINGARRAY = new String[0];
    private XmlSimpleList<?> _jvalue;
    private final SchemaType _schemaType;
    private XmlSimpleList<? extends XmlAnySimpleType> _value;

    public XmlListImpl(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }

    private static String compute_list_text(List<? extends XmlAnySimpleType> list) {
        return list.isEmpty() ? "" : (String) list.stream().map(new XmlListImpl$$ExternalSyntheticLambda1()).collect(Collectors.joining(" "));
    }

    /* access modifiers changed from: private */
    public static String object2String(Object obj) {
        String stringValue = obj instanceof SimpleValue ? ((SimpleValue) obj).getStringValue() : obj.toString();
        return stringValue == null ? "" : stringValue;
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        return compute_list_text(this._value);
    }

    /* access modifiers changed from: protected */
    public boolean is_defaultable_ws(String str) {
        try {
            XmlSimpleList<? extends XmlAnySimpleType> xmlSimpleList = this._value;
            set_text(str);
            this._value = xmlSimpleList;
            return false;
        } catch (XmlValueOutOfRangeException unused) {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        if (!_validateOnSet() || this._schemaType.matchPatternFacet(str)) {
            XmlSimpleList<? extends XmlAnySimpleType> lex = lex(str, this._schemaType.getListItemType(), _voorVc, has_store() ? get_store() : null);
            if (_validateOnSet()) {
                validateValue(lex, this._schemaType, _voorVc);
            }
            this._value = lex;
            this._jvalue = null;
            return;
        }
        throw new XmlValueOutOfRangeException(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.LIST, str, QNameHelper.readable(this._schemaType)});
    }

    public static String[] split_list(String str) {
        if (str.length() == 0) {
            return EMPTY_STRINGARRAY;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            if (i < str.length() && XMLChar.isSpace(str.charAt(i))) {
                i++;
            } else if (i >= str.length()) {
                return (String[]) arrayList.toArray(EMPTY_STRINGARRAY);
            } else {
                int i2 = i;
                while (i2 < str.length() && !XMLChar.isSpace(str.charAt(i2))) {
                    i2++;
                }
                arrayList.add(str.substring(i, i2));
                i = i2;
            }
        }
    }

    public static XmlSimpleList<? extends XmlAnySimpleType> lex(String str, SchemaType schemaType, ValidationContext validationContext, PrefixResolver prefixResolver) {
        boolean z;
        String[] split_list = split_list(str);
        XmlListImpl$$ExternalSyntheticLambda0 xmlListImpl$$ExternalSyntheticLambda0 = new XmlListImpl$$ExternalSyntheticLambda0(schemaType, validationContext);
        if (prefixResolver != null) {
            NamespaceContext.push(new NamespaceContext(prefixResolver));
            z = true;
        } else {
            z = false;
        }
        try {
            return new XmlSimpleList<>((List) Stream.of(split_list).map(xmlListImpl$$ExternalSyntheticLambda0).collect(Collectors.toList()));
        } finally {
            if (z) {
                NamespaceContext.pop();
            }
        }
    }

    static /* synthetic */ XmlAnySimpleType lambda$lex$0(SchemaType schemaType, ValidationContext validationContext, String str) {
        try {
            return schemaType.newValue(str);
        } catch (XmlValueOutOfRangeException unused) {
            validationContext.invalid(XmlErrorCodes.LIST, new Object[]{"item '" + str + "' is not a valid value of " + QNameHelper.readable(schemaType)});
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = null;
    }

    public XmlSimpleList<? extends XmlAnySimpleType> xgetListValue() {
        check_dated();
        return this._value;
    }

    public List<?> getListValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        XmlSimpleList<?> xmlSimpleList = this._jvalue;
        if (xmlSimpleList != null) {
            return xmlSimpleList;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<? extends XmlAnySimpleType> it = this._value.iterator();
        while (it.hasNext()) {
            arrayList.add(java_value((XmlObject) it.next()));
        }
        XmlSimpleList<?> xmlSimpleList2 = new XmlSimpleList<>(arrayList);
        this._jvalue = xmlSimpleList2;
        return xmlSimpleList2;
    }

    private static boolean permits_inner_space(XmlObject xmlObject) {
        int builtinTypeCode = ((SimpleValue) xmlObject).instanceType().getPrimitiveType().getBuiltinTypeCode();
        return builtinTypeCode == 1 || builtinTypeCode == 2 || builtinTypeCode == 6 || builtinTypeCode == 12;
    }

    private static boolean contains_white_space(String str) {
        return str.indexOf(32) >= 0 || str.indexOf(9) >= 0 || str.indexOf(10) >= 0 || str.indexOf(13) >= 0;
    }

    public void set_list(List<?> list) {
        boolean z;
        SchemaType listItemType = this._schemaType.getListItemType();
        if (has_store()) {
            NamespaceContext.push(new NamespaceContext(get_store()));
            z = true;
        } else {
            z = false;
        }
        try {
            XmlSimpleList<? extends XmlAnySimpleType> xmlSimpleList = new XmlSimpleList<>((List) list.stream().map(new XmlListImpl$$ExternalSyntheticLambda2(listItemType)).collect(Collectors.toList()));
            if (_validateOnSet()) {
                validateValue(xmlSimpleList, this._schemaType, _voorVc);
            }
            this._value = xmlSimpleList;
            this._jvalue = null;
        } finally {
            if (z) {
                NamespaceContext.pop();
            }
        }
    }

    static /* synthetic */ XmlAnySimpleType lambda$set_list$1(SchemaType schemaType, Object obj) {
        if (!(obj instanceof XmlObject) || !permits_inner_space((XmlObject) obj) || !contains_white_space(obj.toString())) {
            return schemaType.newValue(obj);
        }
        throw new XmlValueOutOfRangeException();
    }

    public static void validateValue(XmlSimpleList<? extends XmlAnySimpleType> xmlSimpleList, SchemaType schemaType, ValidationContext validationContext) {
        int intValue;
        int intValue2;
        int intValue3;
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues != null) {
            int length = enumerationValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.LIST, xmlSimpleList, QNameHelper.readable(schemaType)});
                    break;
                } else if (equal_xmlLists(xmlSimpleList, ((XmlObjectBase) enumerationValues[i]).xgetListValue())) {
                    break;
                } else {
                    i++;
                }
            }
        }
        XmlAnySimpleType facet = schemaType.getFacet(0);
        if (!(facet == null || (intValue3 = ((SimpleValue) facet).getIntValue()) == xmlSimpleList.size())) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_LENGTH_VALID$LIST_LENGTH, new Object[]{xmlSimpleList, Integer.valueOf(xmlSimpleList.size()), Integer.valueOf(intValue3), QNameHelper.readable(schemaType)});
        }
        XmlAnySimpleType facet2 = schemaType.getFacet(1);
        if (facet2 != null && (intValue2 = ((SimpleValue) facet2).getIntValue()) > xmlSimpleList.size()) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_LENGTH_VALID$LIST_LENGTH, new Object[]{xmlSimpleList, Integer.valueOf(xmlSimpleList.size()), Integer.valueOf(intValue2), QNameHelper.readable(schemaType)});
        }
        XmlAnySimpleType facet3 = schemaType.getFacet(2);
        if (facet3 != null && (intValue = ((SimpleValue) facet3).getIntValue()) < xmlSimpleList.size()) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_LENGTH_VALID$LIST_LENGTH, new Object[]{xmlSimpleList, Integer.valueOf(xmlSimpleList.size()), Integer.valueOf(intValue), QNameHelper.readable(schemaType)});
        }
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return equal_xmlLists(this._value, ((XmlObjectBase) xmlObject).xgetListValue());
    }

    private static boolean equal_xmlLists(List<?> list, List<?> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        XmlSimpleList<? extends XmlAnySimpleType> xmlSimpleList = this._value;
        if (xmlSimpleList == null) {
            return 0;
        }
        int size = xmlSimpleList.size();
        int size2 = this._value.size() / 9;
        if (size2 < 1) {
            size2 = 1;
        }
        for (int i = 0; i < this._value.size(); i += size2) {
            size = (size * 19) + ((XmlAnySimpleType) this._value.get(i)).hashCode();
        }
        return size;
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        validateValue(xgetListValue(), schemaType(), validationContext);
    }
}
