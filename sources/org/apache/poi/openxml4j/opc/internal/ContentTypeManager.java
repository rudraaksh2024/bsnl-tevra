package org.apache.poi.openxml4j.opc.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class ContentTypeManager {
    public static final String CONTENT_TYPES_PART_NAME = "[Content_Types].xml";
    private static final String CONTENT_TYPE_ATTRIBUTE_NAME = "ContentType";
    private static final String DEFAULT_TAG_NAME = "Default";
    private static final String EXTENSION_ATTRIBUTE_NAME = "Extension";
    private static final String OVERRIDE_TAG_NAME = "Override";
    private static final String PART_NAME_ATTRIBUTE_NAME = "PartName";
    public static final String TYPES_NAMESPACE_URI = "http://schemas.openxmlformats.org/package/2006/content-types";
    private static final String TYPES_TAG_NAME = "Types";
    protected OPCPackage container;
    private TreeMap<String, String> defaultContentType = new TreeMap<>();
    private TreeMap<PackagePartName, String> overrideContentType;

    public abstract boolean saveImpl(Document document, OutputStream outputStream);

    public ContentTypeManager(InputStream inputStream, OPCPackage oPCPackage) throws InvalidFormatException {
        this.container = oPCPackage;
        if (inputStream != null) {
            try {
                parseContentTypesFile(inputStream);
            } catch (InvalidFormatException e) {
                InvalidFormatException invalidFormatException = new InvalidFormatException("Can't read content types part !");
                invalidFormatException.initCause(e);
                throw invalidFormatException;
            }
        }
    }

    public void addContentType(PackagePartName packagePartName, String str) {
        boolean containsValue = this.defaultContentType.containsValue(str);
        String lowerCase = packagePartName.getExtension().toLowerCase(Locale.ROOT);
        if (lowerCase.length() == 0 || ((this.defaultContentType.containsKey(lowerCase) && !containsValue) || (!this.defaultContentType.containsKey(lowerCase) && containsValue))) {
            addOverrideContentType(packagePartName, str);
        } else if (!containsValue) {
            addDefaultContentType(lowerCase, str);
        }
    }

    private void addOverrideContentType(PackagePartName packagePartName, String str) {
        if (this.overrideContentType == null) {
            this.overrideContentType = new TreeMap<>();
        }
        this.overrideContentType.put(packagePartName, str);
    }

    private void addDefaultContentType(String str, String str2) {
        this.defaultContentType.put(str.toLowerCase(Locale.ROOT), str2);
    }

    public void removeContentType(PackagePartName packagePartName) throws InvalidOperationException {
        if (packagePartName != null) {
            TreeMap<PackagePartName, String> treeMap = this.overrideContentType;
            if (treeMap == null || treeMap.get(packagePartName) == null) {
                String extension = packagePartName.getExtension();
                OPCPackage oPCPackage = this.container;
                boolean z = true;
                if (oPCPackage != null) {
                    try {
                        Iterator<PackagePart> it = oPCPackage.getParts().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            PackagePart next = it.next();
                            if (!next.getPartName().equals(packagePartName) && next.getPartName().getExtension().equalsIgnoreCase(extension)) {
                                z = false;
                                break;
                            }
                        }
                    } catch (InvalidFormatException e) {
                        throw new InvalidOperationException(e.getMessage());
                    }
                }
                if (z) {
                    this.defaultContentType.remove(extension);
                }
                OPCPackage oPCPackage2 = this.container;
                if (oPCPackage2 != null) {
                    try {
                        Iterator<PackagePart> it2 = oPCPackage2.getParts().iterator();
                        while (it2.hasNext()) {
                            PackagePart next2 = it2.next();
                            if (!next2.getPartName().equals(packagePartName)) {
                                if (getContentType(next2.getPartName()) == null) {
                                    throw new InvalidOperationException("Rule M2.4 is not respected: Nor a default element or override element is associated with the part: " + next2.getPartName().getName());
                                }
                            }
                        }
                    } catch (InvalidFormatException e2) {
                        throw new InvalidOperationException(e2.getMessage());
                    }
                }
            } else {
                this.overrideContentType.remove(packagePartName);
            }
        } else {
            throw new IllegalArgumentException("partName");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000a, code lost:
        r1 = r1.overrideContentType;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isContentTypeRegister(java.lang.String r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x0019
            java.util.TreeMap<java.lang.String, java.lang.String> r0 = r1.defaultContentType
            boolean r0 = r0.containsValue(r2)
            if (r0 != 0) goto L_0x0017
            java.util.TreeMap<org.apache.poi.openxml4j.opc.PackagePartName, java.lang.String> r1 = r1.overrideContentType
            if (r1 == 0) goto L_0x0015
            boolean r1 = r1.containsValue(r2)
            if (r1 == 0) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r1 = 0
            goto L_0x0018
        L_0x0017:
            r1 = 1
        L_0x0018:
            return r1
        L_0x0019:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "contentType"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.internal.ContentTypeManager.isContentTypeRegister(java.lang.String):boolean");
    }

    public String getContentType(PackagePartName packagePartName) {
        if (packagePartName != null) {
            TreeMap<PackagePartName, String> treeMap = this.overrideContentType;
            if (treeMap != null && treeMap.containsKey(packagePartName)) {
                return this.overrideContentType.get(packagePartName);
            }
            String lowerCase = packagePartName.getExtension().toLowerCase(Locale.ROOT);
            if (this.defaultContentType.containsKey(lowerCase)) {
                return this.defaultContentType.get(lowerCase);
            }
            OPCPackage oPCPackage = this.container;
            if (oPCPackage == null || oPCPackage.getPart(packagePartName) == null) {
                return null;
            }
            throw new OpenXML4JRuntimeException("Rule M2.4 exception : Part '" + packagePartName + "' not found - this error should NEVER happen!\nCheck that your code is closing the open resources in the correct order prior to filing a bug report.\nIf you can provide the triggering file, then please raise a bug at https://bz.apache.org/bugzilla/enter_bug.cgi?product=POI and attach the file that triggers it, thanks!");
        }
        throw new IllegalArgumentException("partName");
    }

    public void clearAll() {
        this.defaultContentType.clear();
        TreeMap<PackagePartName, String> treeMap = this.overrideContentType;
        if (treeMap != null) {
            treeMap.clear();
        }
    }

    public void clearOverrideContentTypes() {
        TreeMap<PackagePartName, String> treeMap = this.overrideContentType;
        if (treeMap != null) {
            treeMap.clear();
        }
    }

    private void parseContentTypesFile(InputStream inputStream) throws InvalidFormatException {
        try {
            Document readDocument = DocumentHelper.readDocument(inputStream);
            NodeList elementsByTagNameNS = readDocument.getDocumentElement().getElementsByTagNameNS("http://schemas.openxmlformats.org/package/2006/content-types", DEFAULT_TAG_NAME);
            int length = elementsByTagNameNS.getLength();
            for (int i = 0; i < length; i++) {
                Element element = (Element) elementsByTagNameNS.item(i);
                addDefaultContentType(element.getAttribute(EXTENSION_ATTRIBUTE_NAME), element.getAttribute(CONTENT_TYPE_ATTRIBUTE_NAME));
            }
            NodeList elementsByTagNameNS2 = readDocument.getDocumentElement().getElementsByTagNameNS("http://schemas.openxmlformats.org/package/2006/content-types", OVERRIDE_TAG_NAME);
            int length2 = elementsByTagNameNS2.getLength();
            for (int i2 = 0; i2 < length2; i2++) {
                Element element2 = (Element) elementsByTagNameNS2.item(i2);
                addOverrideContentType(PackagingURIHelper.createPartName(new URI(element2.getAttribute(PART_NAME_ATTRIBUTE_NAME))), element2.getAttribute(CONTENT_TYPE_ATTRIBUTE_NAME));
            }
        } catch (IOException | URISyntaxException | SAXException e) {
            throw new InvalidFormatException(e.getMessage());
        }
    }

    public boolean save(OutputStream outputStream) {
        Document createDocument = DocumentHelper.createDocument();
        Element createElementNS = createDocument.createElementNS("http://schemas.openxmlformats.org/package/2006/content-types", TYPES_TAG_NAME);
        createDocument.appendChild(createElementNS);
        for (Map.Entry<String, String> appendDefaultType : this.defaultContentType.entrySet()) {
            appendDefaultType(createElementNS, appendDefaultType);
        }
        TreeMap<PackagePartName, String> treeMap = this.overrideContentType;
        if (treeMap != null) {
            for (Map.Entry<PackagePartName, String> appendSpecificTypes : treeMap.entrySet()) {
                appendSpecificTypes(createElementNS, appendSpecificTypes);
            }
        }
        createDocument.normalize();
        return saveImpl(createDocument, outputStream);
    }

    private void appendSpecificTypes(Element element, Map.Entry<PackagePartName, String> entry) {
        Element createElementNS = element.getOwnerDocument().createElementNS("http://schemas.openxmlformats.org/package/2006/content-types", OVERRIDE_TAG_NAME);
        createElementNS.setAttribute(PART_NAME_ATTRIBUTE_NAME, entry.getKey().getName());
        createElementNS.setAttribute(CONTENT_TYPE_ATTRIBUTE_NAME, entry.getValue());
        element.appendChild(createElementNS);
    }

    private void appendDefaultType(Element element, Map.Entry<String, String> entry) {
        Element createElementNS = element.getOwnerDocument().createElementNS("http://schemas.openxmlformats.org/package/2006/content-types", DEFAULT_TAG_NAME);
        createElementNS.setAttribute(EXTENSION_ATTRIBUTE_NAME, entry.getKey());
        createElementNS.setAttribute(CONTENT_TYPE_ATTRIBUTE_NAME, entry.getValue());
        element.appendChild(createElementNS);
    }
}
