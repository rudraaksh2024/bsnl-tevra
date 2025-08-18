package org.apache.xmlbeans.impl.schema;

import java.io.IOException;
import java.io.Reader;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlFactoryHook;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlSaxHandler;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.xpath.XPathFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Node;

public abstract class SchemaTypeLoaderBase implements SchemaTypeLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String USER_AGENT = ("XMLBeans/" + XmlBeans.getVersion() + " (" + XmlBeans.getTitle() + ")");

    private static String doCompilePath(String str, XmlOptions xmlOptions) {
        return XPathFactory.compilePath(str, xmlOptions);
    }

    private static String doCompileQuery(String str, XmlOptions xmlOptions) {
        return XPathFactory.compileQuery(str, xmlOptions);
    }

    public SchemaType findType(QName qName) {
        SchemaType.Ref findTypeRef = findTypeRef(qName);
        if (findTypeRef == null) {
            return null;
        }
        return findTypeRef.get();
    }

    public SchemaType findDocumentType(QName qName) {
        SchemaType.Ref findDocumentTypeRef = findDocumentTypeRef(qName);
        if (findDocumentTypeRef == null) {
            return null;
        }
        return findDocumentTypeRef.get();
    }

    public SchemaType findAttributeType(QName qName) {
        SchemaType.Ref findAttributeTypeRef = findAttributeTypeRef(qName);
        if (findAttributeTypeRef == null) {
            return null;
        }
        return findAttributeTypeRef.get();
    }

    public SchemaModelGroup findModelGroup(QName qName) {
        SchemaModelGroup.Ref findModelGroupRef = findModelGroupRef(qName);
        if (findModelGroupRef == null) {
            return null;
        }
        return findModelGroupRef.get();
    }

    public SchemaAttributeGroup findAttributeGroup(QName qName) {
        SchemaAttributeGroup.Ref findAttributeGroupRef = findAttributeGroupRef(qName);
        if (findAttributeGroupRef == null) {
            return null;
        }
        return findAttributeGroupRef.get();
    }

    public SchemaGlobalElement findElement(QName qName) {
        SchemaGlobalElement.Ref findElementRef = findElementRef(qName);
        if (findElementRef == null) {
            return null;
        }
        return findElementRef.get();
    }

    public SchemaGlobalAttribute findAttribute(QName qName) {
        SchemaGlobalAttribute.Ref findAttributeRef = findAttributeRef(qName);
        if (findAttributeRef == null) {
            return null;
        }
        return findAttributeRef.get();
    }

    public XmlObject newInstance(SchemaType schemaType, XmlOptions xmlOptions) {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.newInstance(this, schemaType, xmlOptions);
        }
        return Locale.newInstance(this, schemaType, xmlOptions);
    }

    public XmlObject parse(String str, SchemaType schemaType, XmlOptions xmlOptions) throws XmlException {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.parse((SchemaTypeLoader) this, str, schemaType, xmlOptions);
        }
        return Locale.parseToXmlObject((SchemaTypeLoader) this, str, schemaType, xmlOptions);
    }

    public XmlObject parse(XMLStreamReader xMLStreamReader, SchemaType schemaType, XmlOptions xmlOptions) throws XmlException {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.parse((SchemaTypeLoader) this, xMLStreamReader, schemaType, xmlOptions);
        }
        return Locale.parseToXmlObject((SchemaTypeLoader) this, xMLStreamReader, schemaType, xmlOptions);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.XmlObject parse(java.io.File r3, org.apache.xmlbeans.SchemaType r4, org.apache.xmlbeans.XmlOptions r5) throws org.apache.xmlbeans.XmlException, java.io.IOException {
        /*
            r2 = this;
            java.net.URI r0 = r3.toURI()
            java.net.URI r0 = r0.normalize()
            java.lang.String r0 = r0.toString()
            if (r5 != 0) goto L_0x0017
            org.apache.xmlbeans.XmlOptions r5 = new org.apache.xmlbeans.XmlOptions
            r5.<init>()
            r5.setDocumentSourceName(r0)
            goto L_0x0026
        L_0x0017:
            java.lang.String r1 = r5.getDocumentSourceName()
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.XmlOptions r1 = new org.apache.xmlbeans.XmlOptions
            r1.<init>(r5)
            r1.setDocumentSourceName(r0)
            r5 = r1
        L_0x0026:
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r3)
            org.apache.xmlbeans.XmlObject r2 = r2.parse((java.io.InputStream) r0, (org.apache.xmlbeans.SchemaType) r4, (org.apache.xmlbeans.XmlOptions) r5)     // Catch:{ all -> 0x0033 }
            r0.close()
            return r2
        L_0x0033:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x003e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase.parse(java.io.File, org.apache.xmlbeans.SchemaType, org.apache.xmlbeans.XmlOptions):org.apache.xmlbeans.XmlObject");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x007a, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007b, code lost:
        if (r7 != null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0081, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0082, code lost:
        r6.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0085, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.XmlObject parse(java.net.URL r7, org.apache.xmlbeans.SchemaType r8, org.apache.xmlbeans.XmlOptions r9) throws org.apache.xmlbeans.XmlException, java.io.IOException {
        /*
            r6 = this;
            if (r9 != 0) goto L_0x000f
            org.apache.xmlbeans.XmlOptions r9 = new org.apache.xmlbeans.XmlOptions
            r9.<init>()
            java.lang.String r0 = r7.toString()
            r9.setDocumentSourceName(r0)
            goto L_0x0022
        L_0x000f:
            java.lang.String r0 = r9.getDocumentSourceName()
            if (r0 != 0) goto L_0x0022
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            r0.<init>(r9)
            java.lang.String r9 = r7.toString()
            r0.setDocumentSourceName(r9)
            r9 = r0
        L_0x0022:
            r0 = 0
            r1 = r0
            r2 = r1
        L_0x0025:
            java.net.URLConnection r3 = r7.openConnection()
            java.lang.String r4 = "User-Agent"
            java.lang.String r5 = USER_AGENT
            r3.addRequestProperty(r4, r5)
            java.lang.String r4 = "Accept"
            java.lang.String r5 = "application/xml, text/xml, */*"
            r3.addRequestProperty(r4, r5)
            boolean r4 = r3 instanceof java.net.HttpURLConnection
            if (r4 == 0) goto L_0x0068
            r2 = r3
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2
            int r4 = r2.getResponseCode()
            r5 = 301(0x12d, float:4.22E-43)
            if (r4 == r5) goto L_0x004d
            r5 = 302(0x12e, float:4.23E-43)
            if (r4 != r5) goto L_0x004b
            goto L_0x004d
        L_0x004b:
            r4 = r0
            goto L_0x004e
        L_0x004d:
            r4 = 1
        L_0x004e:
            if (r4 == 0) goto L_0x0054
            r5 = 5
            if (r1 <= r5) goto L_0x0054
            r4 = r0
        L_0x0054:
            if (r4 == 0) goto L_0x0067
            java.lang.String r5 = "Location"
            java.lang.String r2 = r2.getHeaderField(r5)
            if (r2 != 0) goto L_0x0060
            r2 = r0
            goto L_0x0068
        L_0x0060:
            java.net.URL r7 = new java.net.URL
            r7.<init>(r2)
            int r1 = r1 + 1
        L_0x0067:
            r2 = r4
        L_0x0068:
            if (r2 != 0) goto L_0x0025
            java.io.InputStream r7 = r3.getInputStream()
            org.apache.xmlbeans.XmlObject r6 = r6.parse((java.io.InputStream) r7, (org.apache.xmlbeans.SchemaType) r8, (org.apache.xmlbeans.XmlOptions) r9)     // Catch:{ all -> 0x0078 }
            if (r7 == 0) goto L_0x0077
            r7.close()
        L_0x0077:
            return r6
        L_0x0078:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x007a }
        L_0x007a:
            r8 = move-exception
            if (r7 == 0) goto L_0x0085
            r7.close()     // Catch:{ all -> 0x0081 }
            goto L_0x0085
        L_0x0081:
            r7 = move-exception
            r6.addSuppressed(r7)
        L_0x0085:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase.parse(java.net.URL, org.apache.xmlbeans.SchemaType, org.apache.xmlbeans.XmlOptions):org.apache.xmlbeans.XmlObject");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001c  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.XmlObject parse(java.io.InputStream r4, org.apache.xmlbeans.SchemaType r5, org.apache.xmlbeans.XmlOptions r6) throws org.apache.xmlbeans.XmlException, java.io.IOException {
        /*
            r3 = this;
            org.apache.xmlbeans.XmlFactoryHook r0 = org.apache.xmlbeans.XmlFactoryHook.ThreadContext.getHook()
            if (r6 == 0) goto L_0x0019
            boolean r1 = r6.isLoadMessageDigest()
            if (r1 == 0) goto L_0x0019
            java.lang.String r1 = "SHA"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ NoSuchAlgorithmException -> 0x0019 }
            java.security.DigestInputStream r2 = new java.security.DigestInputStream
            r2.<init>(r4, r1)
            r4 = r2
            goto L_0x001a
        L_0x0019:
            r2 = 0
        L_0x001a:
            if (r0 == 0) goto L_0x0021
            org.apache.xmlbeans.XmlObject r3 = r0.parse((org.apache.xmlbeans.SchemaTypeLoader) r3, (java.io.InputStream) r4, (org.apache.xmlbeans.SchemaType) r5, (org.apache.xmlbeans.XmlOptions) r6)
            return r3
        L_0x0021:
            org.apache.xmlbeans.XmlObject r3 = org.apache.xmlbeans.impl.store.Locale.parseToXmlObject((org.apache.xmlbeans.SchemaTypeLoader) r3, (java.io.InputStream) r4, (org.apache.xmlbeans.SchemaType) r5, (org.apache.xmlbeans.XmlOptions) r6)
            if (r2 == 0) goto L_0x0036
            org.apache.xmlbeans.XmlDocumentProperties r4 = r3.documentProperties()
            java.security.MessageDigest r5 = r2.getMessageDigest()
            byte[] r5 = r5.digest()
            r4.setMessageDigest(r5)
        L_0x0036:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase.parse(java.io.InputStream, org.apache.xmlbeans.SchemaType, org.apache.xmlbeans.XmlOptions):org.apache.xmlbeans.XmlObject");
    }

    public XmlObject parse(Reader reader, SchemaType schemaType, XmlOptions xmlOptions) throws XmlException, IOException {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.parse((SchemaTypeLoader) this, reader, schemaType, xmlOptions);
        }
        return Locale.parseToXmlObject((SchemaTypeLoader) this, reader, schemaType, xmlOptions);
    }

    public XmlObject parse(Node node, SchemaType schemaType, XmlOptions xmlOptions) throws XmlException {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.parse((SchemaTypeLoader) this, node, schemaType, xmlOptions);
        }
        return Locale.parseToXmlObject((SchemaTypeLoader) this, node, schemaType, xmlOptions);
    }

    public XmlSaxHandler newXmlSaxHandler(SchemaType schemaType, XmlOptions xmlOptions) {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.newXmlSaxHandler(this, schemaType, xmlOptions);
        }
        return Locale.newSaxHandler(this, schemaType, xmlOptions);
    }

    public DOMImplementation newDomImplementation(XmlOptions xmlOptions) {
        return Locale.newDomImplementation(this, xmlOptions);
    }

    public String compilePath(String str) {
        return compilePath(str, (XmlOptions) null);
    }

    public String compilePath(String str, XmlOptions xmlOptions) {
        return doCompilePath(str, xmlOptions);
    }

    public String compileQuery(String str) {
        return compileQuery(str, (XmlOptions) null);
    }

    public String compileQuery(String str, XmlOptions xmlOptions) {
        return doCompileQuery(str, xmlOptions);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.SchemaType typeForSignature(java.lang.String r13) {
        /*
            r12 = this;
            r0 = 64
            int r0 = r13.indexOf(r0)
            if (r0 >= 0) goto L_0x000f
            int r0 = r13.length()
            java.lang.String r1 = ""
            goto L_0x0015
        L_0x000f:
            int r1 = r0 + 1
            java.lang.String r1 = r13.substring(r1)
        L_0x0015:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 0
            r4 = r3
        L_0x001c:
            r5 = 1
            if (r4 >= r0) goto L_0x0045
            r6 = 58
            int r6 = r13.indexOf(r6, r4)
            r7 = 124(0x7c, float:1.74E-43)
            int r7 = r13.indexOf(r7, r4)
            if (r6 >= 0) goto L_0x002f
            r6 = r7
            goto L_0x0036
        L_0x002f:
            if (r7 >= 0) goto L_0x0032
            goto L_0x0036
        L_0x0032:
            int r6 = java.lang.Math.min(r6, r7)
        L_0x0036:
            if (r6 < 0) goto L_0x003a
            if (r6 <= r0) goto L_0x003b
        L_0x003a:
            r6 = r0
        L_0x003b:
            java.lang.String r4 = r13.substring(r4, r6)
            r2.add(r4)
            int r4 = r6 + 1
            goto L_0x001c
        L_0x0045:
            int r13 = r2.size()
            int r13 = r13 - r5
            r0 = 0
            r4 = r0
        L_0x004c:
            if (r13 < 0) goto L_0x01d4
            java.lang.Object r6 = r2.get(r13)
            java.lang.String r6 = (java.lang.String) r6
            int r7 = r6.length()
            if (r7 < r5) goto L_0x01ce
            int r7 = r6.length()
            r8 = 2
            if (r7 < r8) goto L_0x006b
            char r7 = r6.charAt(r5)
            r9 = 61
            if (r7 != r9) goto L_0x006b
            r7 = r8
            goto L_0x006c
        L_0x006b:
            r7 = r5
        L_0x006c:
            char r9 = r6.charAt(r3)
            r10 = 73
            r11 = 3
            if (r9 == r10) goto L_0x01b1
            r10 = 77
            if (r9 == r10) goto L_0x0189
            r8 = 81
            if (r9 == r8) goto L_0x013e
            r8 = 82
            if (r9 == r8) goto L_0x0127
            r8 = 84
            if (r9 == r8) goto L_0x0110
            r8 = 85
            if (r9 == r8) goto L_0x00c4
            switch(r9) {
                case 65: goto L_0x013e;
                case 66: goto L_0x00a9;
                case 67: goto L_0x0127;
                case 68: goto L_0x0092;
                case 69: goto L_0x00c4;
                default: goto L_0x008c;
            }
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            throw r12
        L_0x0092:
            if (r4 != 0) goto L_0x00a3
            java.lang.String r4 = r6.substring(r7)
            javax.xml.namespace.QName r4 = org.apache.xmlbeans.impl.common.QNameHelper.forLNS(r4, r1)
            org.apache.xmlbeans.SchemaType r4 = r12.findDocumentType(r4)
            if (r4 != 0) goto L_0x01c4
            return r0
        L_0x00a3:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            throw r12
        L_0x00a9:
            if (r4 == 0) goto L_0x00be
            int r6 = r4.getSimpleVariety()
            if (r6 == r5) goto L_0x00b2
            return r0
        L_0x00b2:
            org.apache.xmlbeans.SchemaType[] r4 = r4.getAnonymousTypes()
            int r6 = r4.length
            if (r6 == r5) goto L_0x00ba
            return r0
        L_0x00ba:
            r4 = r4[r3]
            goto L_0x01c4
        L_0x00be:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            throw r12
        L_0x00c4:
            if (r4 == 0) goto L_0x00fb
            int r8 = r4.getContentType()
            if (r8 >= r11) goto L_0x00cd
            return r0
        L_0x00cd:
            org.apache.xmlbeans.SchemaType[] r4 = r4.getAnonymousTypes()
            java.lang.String r6 = r6.substring(r7)
            int r7 = r4.length
            r8 = r3
        L_0x00d7:
            if (r8 >= r7) goto L_0x00fa
            r9 = r4[r8]
            org.apache.xmlbeans.SchemaField r10 = r9.getContainerField()
            if (r10 == 0) goto L_0x00f7
            boolean r11 = r10.isAttribute()
            if (r11 != 0) goto L_0x00f7
            javax.xml.namespace.QName r10 = r10.getName()
            java.lang.String r10 = r10.getLocalPart()
            boolean r10 = r10.equals(r6)
            if (r10 == 0) goto L_0x00f7
            goto L_0x016f
        L_0x00f7:
            int r8 = r8 + 1
            goto L_0x00d7
        L_0x00fa:
            return r0
        L_0x00fb:
            java.lang.String r4 = r6.substring(r7)
            javax.xml.namespace.QName r4 = org.apache.xmlbeans.impl.common.QNameHelper.forLNS(r4, r1)
            org.apache.xmlbeans.SchemaGlobalElement r4 = r12.findElement(r4)
            if (r4 != 0) goto L_0x010a
            return r0
        L_0x010a:
            org.apache.xmlbeans.SchemaType r4 = r4.getType()
            goto L_0x01c4
        L_0x0110:
            if (r4 != 0) goto L_0x0121
            java.lang.String r4 = r6.substring(r7)
            javax.xml.namespace.QName r4 = org.apache.xmlbeans.impl.common.QNameHelper.forLNS(r4, r1)
            org.apache.xmlbeans.SchemaType r4 = r12.findType(r4)
            if (r4 != 0) goto L_0x01c4
            return r0
        L_0x0121:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            throw r12
        L_0x0127:
            if (r4 != 0) goto L_0x0138
            java.lang.String r4 = r6.substring(r7)
            javax.xml.namespace.QName r4 = org.apache.xmlbeans.impl.common.QNameHelper.forLNS(r4, r1)
            org.apache.xmlbeans.SchemaType r4 = r12.findAttributeType(r4)
            if (r4 != 0) goto L_0x01c4
            return r0
        L_0x0138:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            throw r12
        L_0x013e:
            if (r4 == 0) goto L_0x0175
            boolean r8 = r4.isSimpleType()
            if (r8 == 0) goto L_0x0147
            return r0
        L_0x0147:
            org.apache.xmlbeans.SchemaType[] r4 = r4.getAnonymousTypes()
            java.lang.String r6 = r6.substring(r7)
            int r7 = r4.length
            r8 = r3
        L_0x0151:
            if (r8 >= r7) goto L_0x0174
            r9 = r4[r8]
            org.apache.xmlbeans.SchemaField r10 = r9.getContainerField()
            if (r10 == 0) goto L_0x0171
            boolean r11 = r10.isAttribute()
            if (r11 == 0) goto L_0x0171
            javax.xml.namespace.QName r10 = r10.getName()
            java.lang.String r10 = r10.getLocalPart()
            boolean r10 = r10.equals(r6)
            if (r10 == 0) goto L_0x0171
        L_0x016f:
            r4 = r9
            goto L_0x01c4
        L_0x0171:
            int r8 = r8 + 1
            goto L_0x0151
        L_0x0174:
            return r0
        L_0x0175:
            java.lang.String r4 = r6.substring(r7)
            javax.xml.namespace.QName r4 = org.apache.xmlbeans.impl.common.QNameHelper.forLNS(r4, r1)
            org.apache.xmlbeans.SchemaGlobalAttribute r4 = r12.findAttribute(r4)
            if (r4 != 0) goto L_0x0184
            return r0
        L_0x0184:
            org.apache.xmlbeans.SchemaType r4 = r4.getType()
            goto L_0x01c4
        L_0x0189:
            if (r4 == 0) goto L_0x01ab
            java.lang.String r6 = r6.substring(r7)     // Catch:{ Exception -> 0x01a5 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x01a5 }
            int r7 = r4.getSimpleVariety()
            if (r7 == r8) goto L_0x019a
            return r0
        L_0x019a:
            org.apache.xmlbeans.SchemaType[] r4 = r4.getAnonymousTypes()
            int r7 = r4.length
            if (r7 > r6) goto L_0x01a2
            return r0
        L_0x01a2:
            r4 = r4[r6]
            goto L_0x01c4
        L_0x01a5:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            throw r12
        L_0x01ab:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            throw r12
        L_0x01b1:
            if (r4 == 0) goto L_0x01c8
            int r6 = r4.getSimpleVariety()
            if (r6 == r11) goto L_0x01ba
            return r0
        L_0x01ba:
            org.apache.xmlbeans.SchemaType[] r4 = r4.getAnonymousTypes()
            int r6 = r4.length
            if (r6 == r5) goto L_0x01c2
            return r0
        L_0x01c2:
            r4 = r4[r3]
        L_0x01c4:
            int r13 = r13 + -1
            goto L_0x004c
        L_0x01c8:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            throw r12
        L_0x01ce:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            throw r12
        L_0x01d4:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase.typeForSignature(java.lang.String):org.apache.xmlbeans.SchemaType");
    }
}
