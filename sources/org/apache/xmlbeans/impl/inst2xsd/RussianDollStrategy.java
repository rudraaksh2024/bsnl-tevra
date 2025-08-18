package org.apache.xmlbeans.impl.inst2xsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlShort;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.inst2xsd.util.Attribute;
import org.apache.xmlbeans.impl.inst2xsd.util.Element;
import org.apache.xmlbeans.impl.inst2xsd.util.Type;
import org.apache.xmlbeans.impl.inst2xsd.util.TypeSystemHolder;

public class RussianDollStrategy implements XsdGenStrategy {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final String _xsi = "http://www.w3.org/2001/XMLSchema-instance";
    static final QName _xsiNil = new QName(_xsi, "nil", "xsi");
    private final SCTValidationContext _validationContext = new SCTValidationContext();

    /* access modifiers changed from: protected */
    public void checkIfReferenceToGlobalTypeIsNeeded(Element element, TypeSystemHolder typeSystemHolder, Inst2XsdOptions inst2XsdOptions) {
    }

    public void processDoc(XmlObject[] xmlObjectArr, Inst2XsdOptions inst2XsdOptions, TypeSystemHolder typeSystemHolder) {
        for (XmlObject newCursor : xmlObjectArr) {
            XmlCursor newCursor2 = newCursor.newCursor();
            StringBuilder sb = new StringBuilder();
            while (!newCursor2.isStart()) {
                newCursor2.toNextToken();
                if (newCursor2.isComment()) {
                    sb.append(newCursor2.getTextValue());
                } else if (newCursor2.isEnddoc()) {
                    return;
                }
            }
            Element processElement = processElement(newCursor2, sb.toString(), inst2XsdOptions, typeSystemHolder);
            processElement.setGlobal(true);
            addGlobalElement(processElement, typeSystemHolder, inst2XsdOptions);
        }
    }

    /* access modifiers changed from: protected */
    public Element addGlobalElement(Element element, TypeSystemHolder typeSystemHolder, Inst2XsdOptions inst2XsdOptions) {
        Element globalElement = typeSystemHolder.getGlobalElement(element.getName());
        if (globalElement == null) {
            typeSystemHolder.addGlobalElement(element);
            return element;
        }
        combineTypes(globalElement.getType(), element.getType(), inst2XsdOptions);
        combineElementComments(globalElement, element);
        return globalElement;
    }

    /* access modifiers changed from: protected */
    public Element processElement(XmlCursor xmlCursor, String str, Inst2XsdOptions inst2XsdOptions, TypeSystemHolder typeSystemHolder) {
        XmlCursor xmlCursor2 = xmlCursor;
        String str2 = str;
        Inst2XsdOptions inst2XsdOptions2 = inst2XsdOptions;
        TypeSystemHolder typeSystemHolder2 = typeSystemHolder;
        Element element = new Element();
        element.setName(xmlCursor.getName());
        element.setGlobal(false);
        Type createUnnamedType = Type.createUnnamedType(1);
        element.setType(createUnnamedType);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        while (true) {
            switch (xmlCursor.toNextToken().intValue()) {
                case 0:
                case 2:
                case 4:
                    String collapse = XmlWhitespace.collapse(sb.toString(), 3);
                    if (str2 == null) {
                        str2 = sb2.length() == 0 ? null : sb2.toString();
                    } else if (sb2.length() != 0) {
                        str2 = sb2.insert(0, str2).toString();
                    }
                    element.setComment(str2);
                    if (arrayList.size() > 0) {
                        if (collapse.length() > 0) {
                            createUnnamedType.setContentType(4);
                        } else {
                            createUnnamedType.setContentType(3);
                        }
                        processElementsInComplexType(createUnnamedType, arrayList, element.getName().getNamespaceURI(), typeSystemHolder, inst2XsdOptions);
                        processAttributesInComplexType(createUnnamedType, arrayList2);
                    } else {
                        XmlCursor newCursor = xmlCursor.newCursor();
                        newCursor.toParent();
                        if (arrayList2.size() > 0) {
                            createUnnamedType.setContentType(2);
                            createUnnamedType.setExtensionType(Type.createNamedType(processSimpleContentType(sb.toString(), inst2XsdOptions2, newCursor), 1));
                            processAttributesInComplexType(createUnnamedType, arrayList2);
                        } else {
                            createUnnamedType.setContentType(1);
                            createUnnamedType.setName(processSimpleContentType(sb.toString(), inst2XsdOptions2, newCursor));
                            if (XmlString.type.getName().equals(createUnnamedType.getName())) {
                                collapse = sb.toString();
                            }
                            createUnnamedType.addEnumerationValue(collapse, newCursor);
                        }
                        newCursor.dispose();
                    }
                    checkIfReferenceToGlobalTypeIsNeeded(element, typeSystemHolder2, inst2XsdOptions2);
                    return element;
                case 1:
                    throw new IllegalStateException();
                case 3:
                    arrayList.add(processElement(xmlCursor2, sb2.toString(), inst2XsdOptions2, typeSystemHolder2));
                    sb2.delete(0, sb2.length());
                    break;
                case 5:
                    sb.append(xmlCursor.getChars());
                    break;
                case 6:
                    QName name = xmlCursor.getName();
                    QName qName = _xsiNil;
                    if (qName.getNamespaceURI().equals(name.getNamespaceURI())) {
                        if (!qName.equals(name)) {
                            break;
                        } else {
                            element.setNillable(true);
                            break;
                        }
                    } else {
                        arrayList2.add(processAttribute(xmlCursor2, inst2XsdOptions2, element.getName().getNamespaceURI(), typeSystemHolder2));
                        break;
                    }
                case 7:
                case 9:
                    break;
                case 8:
                    sb2.append(xmlCursor.getTextValue());
                    break;
                default:
                    throw new IllegalStateException("Unknown TokenType.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processElementsInComplexType(Type type, List<Element> list, String str, TypeSystemHolder typeSystemHolder, Inst2XsdOptions inst2XsdOptions) {
        HashMap hashMap = new HashMap();
        Element element = null;
        for (Element next : list) {
            if (element == null) {
                checkIfElementReferenceIsNeeded(next, str, typeSystemHolder, inst2XsdOptions);
                type.addElement(next);
                hashMap.put(next.getName(), next);
            } else if (element.getName() == next.getName()) {
                combineTypes(element.getType(), next.getType(), inst2XsdOptions);
                combineElementComments(element, next);
                element.setMinOccurs(0);
                element.setMaxOccurs(-1);
            } else if (((Element) hashMap.get(next.getName())) == null) {
                checkIfElementReferenceIsNeeded(next, str, typeSystemHolder, inst2XsdOptions);
                type.addElement(next);
                hashMap.put(next.getName(), next);
            } else {
                combineTypes(element.getType(), next.getType(), inst2XsdOptions);
                combineElementComments(element, next);
                type.setTopParticleForComplexOrMixedContent(2);
            }
            element = next;
        }
    }

    /* access modifiers changed from: protected */
    public void checkIfElementReferenceIsNeeded(Element element, String str, TypeSystemHolder typeSystemHolder, Inst2XsdOptions inst2XsdOptions) {
        if (!element.getName().getNamespaceURI().equals(str)) {
            Element element2 = new Element();
            element2.setGlobal(true);
            element2.setName(element.getName());
            element2.setType(element.getType());
            if (element.isNillable()) {
                element2.setNillable(true);
                element.setNillable(false);
            }
            element.setRef(addGlobalElement(element2, typeSystemHolder, inst2XsdOptions));
        }
    }

    /* access modifiers changed from: protected */
    public void processAttributesInComplexType(Type type, List<Attribute> list) {
        for (Attribute addAttribute : list) {
            type.addAttribute(addAttribute);
        }
    }

    /* access modifiers changed from: protected */
    public Attribute processAttribute(XmlCursor xmlCursor, Inst2XsdOptions inst2XsdOptions, String str, TypeSystemHolder typeSystemHolder) {
        Attribute attribute = new Attribute();
        attribute.setName(xmlCursor.getName());
        XmlCursor newCursor = xmlCursor.newCursor();
        newCursor.toParent();
        Type createNamedType = Type.createNamedType(processSimpleContentType(xmlCursor.getTextValue(), inst2XsdOptions, newCursor), 1);
        newCursor.dispose();
        attribute.setType(createNamedType);
        checkIfAttributeReferenceIsNeeded(attribute, str, typeSystemHolder);
        return attribute;
    }

    /* access modifiers changed from: protected */
    public void checkIfAttributeReferenceIsNeeded(Attribute attribute, String str, TypeSystemHolder typeSystemHolder) {
        if (!attribute.getName().getNamespaceURI().equals("") && !attribute.getName().getNamespaceURI().equals(str)) {
            Attribute attribute2 = new Attribute();
            attribute2.setGlobal(true);
            attribute2.setName(attribute.getName());
            attribute2.setType(attribute.getType());
            typeSystemHolder.addGlobalAttribute(attribute2);
            attribute.setRef(attribute2);
        }
    }

    protected static class SCTValidationContext implements ValidationContext {
        protected boolean valid = true;

        protected SCTValidationContext() {
        }

        public boolean isValid() {
            return this.valid;
        }

        public void resetToValid() {
            this.valid = true;
        }

        public void invalid(String str) {
            this.valid = false;
        }

        public void invalid(String str, Object[] objArr) {
            this.valid = false;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:12|13|14) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:15|16|17) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:18|19|20) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:21|22|23) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:9|10|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        org.apache.xmlbeans.impl.util.XsTypeConverter.lexShort(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        return org.apache.xmlbeans.XmlShort.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        org.apache.xmlbeans.impl.util.XsTypeConverter.lexInt(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        return org.apache.xmlbeans.XmlInt.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        org.apache.xmlbeans.impl.util.XsTypeConverter.lexLong(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        return org.apache.xmlbeans.XmlLong.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        org.apache.xmlbeans.impl.util.XsTypeConverter.lexInteger(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        return org.apache.xmlbeans.XmlInteger.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        org.apache.xmlbeans.impl.util.XsTypeConverter.lexFloat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        return org.apache.xmlbeans.XmlFloat.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        org.apache.xmlbeans.impl.values.XmlDateImpl.validateLexical(r3, org.apache.xmlbeans.XmlDate.type, r2._validationContext);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005e, code lost:
        if (r2._validationContext.isValid() != false) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        return org.apache.xmlbeans.XmlDate.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0067, code lost:
        r2._validationContext.resetToValid();
        org.apache.xmlbeans.impl.values.XmlDateTimeImpl.validateLexical(r3, org.apache.xmlbeans.XmlDateTime.type, r2._validationContext);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0079, code lost:
        if (r2._validationContext.isValid() != false) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0081, code lost:
        return org.apache.xmlbeans.XmlDateTime.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0082, code lost:
        r2._validationContext.resetToValid();
        org.apache.xmlbeans.impl.values.XmlTimeImpl.validateLexical(r3, org.apache.xmlbeans.XmlTime.type, r2._validationContext);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0094, code lost:
        if (r2._validationContext.isValid() != false) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009c, code lost:
        return org.apache.xmlbeans.XmlTime.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009d, code lost:
        r2._validationContext.resetToValid();
        org.apache.xmlbeans.impl.values.XmlDurationImpl.validateLexical(r3, org.apache.xmlbeans.XmlDuration.type, r2._validationContext);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00af, code lost:
        if (r2._validationContext.isValid() != false) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b7, code lost:
        return org.apache.xmlbeans.XmlDuration.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b8, code lost:
        r2._validationContext.resetToValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00cd, code lost:
        org.apache.xmlbeans.impl.values.XmlAnyUriImpl.validateLexical(r3, r2._validationContext);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d8, code lost:
        if (r2._validationContext.isValid() != false) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e0, code lost:
        return org.apache.xmlbeans.XmlAnyURI.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e1, code lost:
        r2._validationContext.resetToValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e6, code lost:
        r0 = r3.indexOf(58);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fb, code lost:
        r4 = r2._validationContext;
        r5.getClass();
        org.apache.xmlbeans.impl.values.XmlQNameImpl.validateLexical(r3, r4, new org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$$ExternalSyntheticLambda0(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x010e, code lost:
        if (r2._validationContext.isValid() != false) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0116, code lost:
        return org.apache.xmlbeans.XmlQName.type.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0117, code lost:
        r2._validationContext.resetToValid();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0122, code lost:
        return org.apache.xmlbeans.XmlString.type.getName();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0029 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0033 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x003d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0047 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.xml.namespace.QName processSimpleContentType(java.lang.String r3, org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions r4, org.apache.xmlbeans.XmlCursor r5) {
        /*
            r2 = this;
            int r0 = r4.getSimpleContentTypes()
            r1 = 2
            if (r0 != r1) goto L_0x000e
            org.apache.xmlbeans.SchemaType r2 = org.apache.xmlbeans.XmlString.type
            javax.xml.namespace.QName r2 = r2.getName()
            return r2
        L_0x000e:
            int r0 = r4.getSimpleContentTypes()
            r1 = 1
            if (r0 != r1) goto L_0x0123
            org.apache.xmlbeans.impl.util.XsTypeConverter.lexByte(r3)     // Catch:{ Exception -> 0x001f }
            org.apache.xmlbeans.SchemaType r4 = org.apache.xmlbeans.XmlByte.type     // Catch:{ Exception -> 0x001f }
            javax.xml.namespace.QName r2 = r4.getName()     // Catch:{ Exception -> 0x001f }
            return r2
        L_0x001f:
            org.apache.xmlbeans.impl.util.XsTypeConverter.lexShort(r3)     // Catch:{ Exception -> 0x0029 }
            org.apache.xmlbeans.SchemaType r4 = org.apache.xmlbeans.XmlShort.type     // Catch:{ Exception -> 0x0029 }
            javax.xml.namespace.QName r2 = r4.getName()     // Catch:{ Exception -> 0x0029 }
            return r2
        L_0x0029:
            org.apache.xmlbeans.impl.util.XsTypeConverter.lexInt(r3)     // Catch:{ Exception -> 0x0033 }
            org.apache.xmlbeans.SchemaType r4 = org.apache.xmlbeans.XmlInt.type     // Catch:{ Exception -> 0x0033 }
            javax.xml.namespace.QName r2 = r4.getName()     // Catch:{ Exception -> 0x0033 }
            return r2
        L_0x0033:
            org.apache.xmlbeans.impl.util.XsTypeConverter.lexLong(r3)     // Catch:{ Exception -> 0x003d }
            org.apache.xmlbeans.SchemaType r4 = org.apache.xmlbeans.XmlLong.type     // Catch:{ Exception -> 0x003d }
            javax.xml.namespace.QName r2 = r4.getName()     // Catch:{ Exception -> 0x003d }
            return r2
        L_0x003d:
            org.apache.xmlbeans.impl.util.XsTypeConverter.lexInteger(r3)     // Catch:{ Exception -> 0x0047 }
            org.apache.xmlbeans.SchemaType r4 = org.apache.xmlbeans.XmlInteger.type     // Catch:{ Exception -> 0x0047 }
            javax.xml.namespace.QName r2 = r4.getName()     // Catch:{ Exception -> 0x0047 }
            return r2
        L_0x0047:
            org.apache.xmlbeans.impl.util.XsTypeConverter.lexFloat(r3)     // Catch:{ Exception -> 0x0051 }
            org.apache.xmlbeans.SchemaType r4 = org.apache.xmlbeans.XmlFloat.type     // Catch:{ Exception -> 0x0051 }
            javax.xml.namespace.QName r2 = r4.getName()     // Catch:{ Exception -> 0x0051 }
            return r2
        L_0x0051:
            org.apache.xmlbeans.SchemaType r4 = org.apache.xmlbeans.XmlDate.type
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r0 = r2._validationContext
            org.apache.xmlbeans.impl.values.XmlDateImpl.validateLexical(r3, r4, r0)
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            boolean r4 = r4.isValid()
            if (r4 == 0) goto L_0x0067
            org.apache.xmlbeans.SchemaType r2 = org.apache.xmlbeans.XmlDate.type
            javax.xml.namespace.QName r2 = r2.getName()
            return r2
        L_0x0067:
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            r4.resetToValid()
            org.apache.xmlbeans.SchemaType r4 = org.apache.xmlbeans.XmlDateTime.type
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r0 = r2._validationContext
            org.apache.xmlbeans.impl.values.XmlDateTimeImpl.validateLexical(r3, r4, r0)
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            boolean r4 = r4.isValid()
            if (r4 == 0) goto L_0x0082
            org.apache.xmlbeans.SchemaType r2 = org.apache.xmlbeans.XmlDateTime.type
            javax.xml.namespace.QName r2 = r2.getName()
            return r2
        L_0x0082:
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            r4.resetToValid()
            org.apache.xmlbeans.SchemaType r4 = org.apache.xmlbeans.XmlTime.type
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r0 = r2._validationContext
            org.apache.xmlbeans.impl.values.XmlTimeImpl.validateLexical(r3, r4, r0)
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            boolean r4 = r4.isValid()
            if (r4 == 0) goto L_0x009d
            org.apache.xmlbeans.SchemaType r2 = org.apache.xmlbeans.XmlTime.type
            javax.xml.namespace.QName r2 = r2.getName()
            return r2
        L_0x009d:
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            r4.resetToValid()
            org.apache.xmlbeans.SchemaType r4 = org.apache.xmlbeans.XmlDuration.type
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r0 = r2._validationContext
            org.apache.xmlbeans.impl.values.XmlDurationImpl.validateLexical(r3, r4, r0)
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            boolean r4 = r4.isValid()
            if (r4 == 0) goto L_0x00b8
            org.apache.xmlbeans.SchemaType r2 = org.apache.xmlbeans.XmlDuration.type
            javax.xml.namespace.QName r2 = r2.getName()
            return r2
        L_0x00b8:
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            r4.resetToValid()
            java.lang.String r4 = "http://"
            boolean r4 = r3.startsWith(r4)
            if (r4 != 0) goto L_0x00cd
            java.lang.String r4 = "www."
            boolean r4 = r3.startsWith(r4)
            if (r4 == 0) goto L_0x00e6
        L_0x00cd:
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            org.apache.xmlbeans.impl.values.XmlAnyUriImpl.validateLexical(r3, r4)
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            boolean r4 = r4.isValid()
            if (r4 == 0) goto L_0x00e1
            org.apache.xmlbeans.SchemaType r2 = org.apache.xmlbeans.XmlAnyURI.type
            javax.xml.namespace.QName r2 = r2.getName()
            return r2
        L_0x00e1:
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            r4.resetToValid()
        L_0x00e6:
            r4 = 58
            int r0 = r3.indexOf(r4)
            if (r0 < 0) goto L_0x011c
            int r4 = r3.lastIndexOf(r4)
            if (r0 != r4) goto L_0x011c
            int r0 = r0 + r1
            int r4 = r3.length()
            if (r0 >= r4) goto L_0x011c
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r4 = r2._validationContext
            r5.getClass()
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$$ExternalSyntheticLambda0 r0 = new org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$$ExternalSyntheticLambda0
            r0.<init>(r5)
            org.apache.xmlbeans.impl.values.XmlQNameImpl.validateLexical(r3, r4, r0)
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r3 = r2._validationContext
            boolean r3 = r3.isValid()
            if (r3 == 0) goto L_0x0117
            org.apache.xmlbeans.SchemaType r2 = org.apache.xmlbeans.XmlQName.type
            javax.xml.namespace.QName r2 = r2.getName()
            return r2
        L_0x0117:
            org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$SCTValidationContext r2 = r2._validationContext
            r2.resetToValid()
        L_0x011c:
            org.apache.xmlbeans.SchemaType r2 = org.apache.xmlbeans.XmlString.type
            javax.xml.namespace.QName r2 = r2.getName()
            return r2
        L_0x0123:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "Unknown value for Inst2XsdOptions.getSimpleContentTypes() :"
            r3.<init>(r5)
            int r4 = r4.getSimpleContentTypes()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy.processSimpleContentType(java.lang.String, org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions, org.apache.xmlbeans.XmlCursor):javax.xml.namespace.QName");
    }

    /* access modifiers changed from: protected */
    public void combineTypes(Type type, Type type2, Inst2XsdOptions inst2XsdOptions) {
        if (type != type2) {
            if (type.isGlobal() && type2.isGlobal() && type.getName().equals(type2.getName())) {
                return;
            }
            if (type.getContentType() == 1 && type2.getContentType() == 1) {
                combineSimpleTypes(type, type2, inst2XsdOptions);
            } else if ((type.getContentType() == 1 || type.getContentType() == 2) && (type2.getContentType() == 1 || type2.getContentType() == 2)) {
                QName name = type.isComplexType() ? type.getExtensionType().getName() : type.getName();
                QName name2 = type2.isComplexType() ? type2.getExtensionType().getName() : type2.getName();
                type.setContentType(2);
                QName combineToMoreGeneralSimpleType = combineToMoreGeneralSimpleType(name, name2);
                if (type.isComplexType()) {
                    type.setExtensionType(Type.createNamedType(combineToMoreGeneralSimpleType, 1));
                } else {
                    type.setName(combineToMoreGeneralSimpleType);
                }
                combineAttributesOfTypes(type, type2);
            } else if (type.getContentType() == 3 && type2.getContentType() == 3) {
                combineAttributesOfTypes(type, type2);
                combineElementsOfTypes(type, type2, inst2XsdOptions);
            } else if (type.getContentType() == 1 || type.getContentType() == 2 || type2.getContentType() == 1 || type2.getContentType() == 2) {
                type.setContentType(4);
                combineAttributesOfTypes(type, type2);
                combineElementsOfTypes(type, type2, inst2XsdOptions);
            } else if ((type.getContentType() == 1 || type.getContentType() == 2 || type.getContentType() == 3 || type.getContentType() == 4) && (type2.getContentType() == 1 || type2.getContentType() == 2 || type2.getContentType() == 3 || type2.getContentType() == 4)) {
                type.setContentType(4);
                combineAttributesOfTypes(type, type2);
                combineElementsOfTypes(type, type2, inst2XsdOptions);
            } else {
                throw new IllegalArgumentException("Unknown content type.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void combineSimpleTypes(Type type, Type type2, Inst2XsdOptions inst2XsdOptions) {
        type.setName(combineToMoreGeneralSimpleType(type.getName(), type2.getName()));
        if (inst2XsdOptions.isUseEnumerations()) {
            type.addAllEnumerationsFrom(type2);
            if (type.getEnumerationValues().size() > inst2XsdOptions.getUseEnumerations()) {
                type.closeEnumeration();
            }
        }
    }

    /* access modifiers changed from: protected */
    public QName combineToMoreGeneralSimpleType(QName qName, QName qName2) {
        if (qName.equals(qName2)) {
            return qName;
        }
        if (qName2.equals(XmlShort.type.getName()) && qName.equals(XmlByte.type.getName())) {
            return qName2;
        }
        if (qName.equals(XmlShort.type.getName()) && qName2.equals(XmlByte.type.getName())) {
            return qName;
        }
        if (qName2.equals(XmlInt.type.getName()) && (qName.equals(XmlShort.type.getName()) || qName.equals(XmlByte.type.getName()))) {
            return qName2;
        }
        if (qName.equals(XmlInt.type.getName()) && (qName2.equals(XmlShort.type.getName()) || qName2.equals(XmlByte.type.getName()))) {
            return qName;
        }
        if (qName2.equals(XmlLong.type.getName()) && (qName.equals(XmlInt.type.getName()) || qName.equals(XmlShort.type.getName()) || qName.equals(XmlByte.type.getName()))) {
            return qName2;
        }
        if (qName.equals(XmlLong.type.getName()) && (qName2.equals(XmlInt.type.getName()) || qName2.equals(XmlShort.type.getName()) || qName2.equals(XmlByte.type.getName()))) {
            return qName;
        }
        if (qName2.equals(XmlInteger.type.getName()) && (qName.equals(XmlLong.type.getName()) || qName.equals(XmlInt.type.getName()) || qName.equals(XmlShort.type.getName()) || qName.equals(XmlByte.type.getName()))) {
            return qName2;
        }
        if (qName.equals(XmlInteger.type.getName()) && (qName2.equals(XmlLong.type.getName()) || qName2.equals(XmlInt.type.getName()) || qName2.equals(XmlShort.type.getName()) || qName2.equals(XmlByte.type.getName()))) {
            return qName;
        }
        if (qName2.equals(XmlFloat.type.getName()) && (qName.equals(XmlInteger.type.getName()) || qName.equals(XmlLong.type.getName()) || qName.equals(XmlInt.type.getName()) || qName.equals(XmlShort.type.getName()) || qName.equals(XmlByte.type.getName()))) {
            return qName2;
        }
        if (!qName.equals(XmlFloat.type.getName()) || (!qName2.equals(XmlInteger.type.getName()) && !qName2.equals(XmlLong.type.getName()) && !qName2.equals(XmlInt.type.getName()) && !qName2.equals(XmlShort.type.getName()) && !qName2.equals(XmlByte.type.getName()))) {
            return XmlString.type.getName();
        }
        return qName;
    }

    /* access modifiers changed from: protected */
    public void combineAttributesOfTypes(Type type, Type type2) {
        for (int i = 0; i < type2.getAttributes().size(); i++) {
            Attribute attribute = type2.getAttributes().get(i);
            int i2 = 0;
            while (true) {
                if (i2 >= type.getAttributes().size()) {
                    type.addAttribute(attribute);
                    break;
                }
                Attribute attribute2 = type.getAttributes().get(i2);
                if (attribute2.getName().equals(attribute.getName())) {
                    attribute2.getType().setName(combineToMoreGeneralSimpleType(attribute2.getType().getName(), attribute.getType().getName()));
                    break;
                }
                i2++;
            }
        }
        for (int i3 = 0; i3 < type.getAttributes().size(); i3++) {
            Attribute attribute3 = type.getAttributes().get(i3);
            for (int i4 = 0; i4 < type2.getAttributes().size(); i4++) {
                type2.getAttributes().get(i4).getName().equals(attribute3.getName());
            }
            attribute3.setOptional(true);
        }
    }

    /* access modifiers changed from: protected */
    public void combineElementsOfTypes(Type type, Type type2, Inst2XsdOptions inst2XsdOptions) {
        Type type3 = type;
        Inst2XsdOptions inst2XsdOptions2 = inst2XsdOptions;
        boolean z = (type.getTopParticleForComplexOrMixedContent() == 1 && type2.getTopParticleForComplexOrMixedContent() == 1) ? false : true;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        int i4 = -1;
        while (!z && i < type.getElements().size()) {
            Element element = type.getElements().get(i);
            int i5 = i2;
            while (true) {
                if (i5 >= type2.getElements().size()) {
                    break;
                } else if (element.getName().equals(type2.getElements().get(i5).getName())) {
                    i3 = i5;
                    break;
                } else {
                    i5++;
                }
            }
            if (i3 < i2) {
                arrayList.add(element);
                element.setMinOccurs(0);
            } else {
                int i6 = i2;
                while (true) {
                    if (i6 >= i3) {
                        break;
                    }
                    Element element2 = type2.getElements().get(i6);
                    for (int i7 = i + 1; i7 < type.getElements().size(); i7++) {
                        if (element2.getName().equals(type.getElements().get(i7).getName())) {
                            i4 = i7;
                            break;
                        }
                    }
                    i6++;
                }
                if (i4 < i) {
                    while (i2 < i3) {
                        Element element3 = type2.getElements().get(i2);
                        arrayList.add(element3);
                        element3.setMinOccurs(0);
                        i2++;
                    }
                    arrayList.add(element);
                    Element element4 = type2.getElements().get(i3);
                    if (element4.getMinOccurs() <= 0) {
                        element.setMinOccurs(0);
                    }
                    if (element4.getMaxOccurs() == -1) {
                        element.setMaxOccurs(-1);
                    }
                    combineTypes(element.getType(), element4.getType(), inst2XsdOptions2);
                    combineElementComments(element, element4);
                    i2 = i3 + 1;
                } else {
                    z = true;
                }
            }
            i++;
        }
        while (i2 < type2.getElements().size()) {
            Element element5 = type2.getElements().get(i2);
            arrayList.add(element5);
            element5.setMinOccurs(0);
            i2++;
        }
        if (z) {
            type3.setTopParticleForComplexOrMixedContent(2);
            for (int i8 = 0; i8 < type2.getElements().size(); i8++) {
                Element element6 = type2.getElements().get(i8);
                int i9 = 0;
                while (true) {
                    if (i9 >= type.getElements().size()) {
                        type3.addElement(element6);
                        element6.setMinOccurs(1);
                        element6.setMaxOccurs(1);
                        break;
                    }
                    Element element7 = type.getElements().get(i9);
                    element7.setMinOccurs(1);
                    element7.setMaxOccurs(1);
                    if (element7 == element6) {
                        break;
                    } else if (element7.getName().equals(element6.getName())) {
                        combineTypes(element7.getType(), element6.getType(), inst2XsdOptions2);
                        combineElementComments(element7, element6);
                        break;
                    } else {
                        i9++;
                    }
                }
            }
            return;
        }
        type3.setElements(arrayList);
    }

    /* access modifiers changed from: protected */
    public void combineElementComments(Element element, Element element2) {
        if (element2.getComment() != null && element2.getComment().length() > 0) {
            if (element.getComment() == null) {
                element.setComment(element2.getComment());
            } else {
                element.setComment(element.getComment() + element2.getComment());
            }
        }
    }
}
