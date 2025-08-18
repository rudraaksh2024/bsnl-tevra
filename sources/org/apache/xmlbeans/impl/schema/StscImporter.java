package org.apache.xmlbeans.impl.schema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.common.XmlEncodingSniffer;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class StscImporter {
    private static final String PROJECT_URL_PREFIX = "project://local";

    public static SchemaToProcess[] resolveImportsAndIncludes(SchemaDocument.Schema[] schemaArr, boolean z) {
        return new DownloadTable(schemaArr).resolveImportsAndIncludes(z);
    }

    public static class SchemaToProcess {
        private final String chameleonNamespace;
        private List<SchemaToProcess> includes;
        private Set<SchemaToProcess> indirectIncludedBy;
        private Set<SchemaToProcess> indirectIncludes;
        private List<RedefineDocument.Redefine> redefineObjects;
        private List<SchemaToProcess> redefines;
        private final SchemaDocument.Schema schema;

        public SchemaToProcess(SchemaDocument.Schema schema2, String str) {
            this.schema = schema2;
            this.chameleonNamespace = str;
        }

        public SchemaDocument.Schema getSchema() {
            return this.schema;
        }

        public String getSourceName() {
            return this.schema.documentProperties().getSourceName();
        }

        public String getChameleonNamespace() {
            return this.chameleonNamespace;
        }

        public List<SchemaToProcess> getRedefines() {
            return this.redefines;
        }

        public List<RedefineDocument.Redefine> getRedefineObjects() {
            return this.redefineObjects;
        }

        /* access modifiers changed from: private */
        public void addInclude(SchemaToProcess schemaToProcess) {
            if (this.includes == null) {
                this.includes = new ArrayList();
            }
            this.includes.add(schemaToProcess);
        }

        /* access modifiers changed from: private */
        public void addRedefine(SchemaToProcess schemaToProcess, RedefineDocument.Redefine redefine) {
            if (this.redefines == null || this.redefineObjects == null) {
                this.redefines = new ArrayList();
                this.redefineObjects = new ArrayList();
            }
            this.redefines.add(schemaToProcess);
            this.redefineObjects.add(redefine);
        }

        /* access modifiers changed from: private */
        public void buildIndirectReferences() {
            List<SchemaToProcess> list = this.includes;
            if (list != null) {
                for (SchemaToProcess addIndirectIncludes : list) {
                    addIndirectIncludes(addIndirectIncludes);
                }
            }
            List<SchemaToProcess> list2 = this.redefines;
            if (list2 != null) {
                for (SchemaToProcess addIndirectIncludes2 : list2) {
                    addIndirectIncludes(addIndirectIncludes2);
                }
            }
        }

        private void addIndirectIncludes(SchemaToProcess schemaToProcess) {
            if (this.indirectIncludes == null) {
                this.indirectIncludes = new HashSet();
            }
            this.indirectIncludes.add(schemaToProcess);
            if (schemaToProcess.indirectIncludedBy == null) {
                schemaToProcess.indirectIncludedBy = new HashSet();
            }
            schemaToProcess.indirectIncludedBy.add(this);
            addIndirectIncludesHelper(this, schemaToProcess);
            Set<SchemaToProcess> set = this.indirectIncludedBy;
            if (set != null) {
                for (SchemaToProcess next : set) {
                    next.indirectIncludes.add(schemaToProcess);
                    schemaToProcess.indirectIncludedBy.add(next);
                    addIndirectIncludesHelper(next, schemaToProcess);
                }
            }
        }

        private static void addIndirectIncludesHelper(SchemaToProcess schemaToProcess, SchemaToProcess schemaToProcess2) {
            Set<SchemaToProcess> set = schemaToProcess2.indirectIncludes;
            if (set != null) {
                for (SchemaToProcess next : set) {
                    schemaToProcess.indirectIncludes.add(next);
                    next.indirectIncludedBy.add(schemaToProcess);
                }
            }
        }

        public boolean indirectIncludes(SchemaToProcess schemaToProcess) {
            Set<SchemaToProcess> set = this.indirectIncludes;
            return set != null && set.contains(schemaToProcess);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SchemaToProcess)) {
                return false;
            }
            SchemaToProcess schemaToProcess = (SchemaToProcess) obj;
            if (!Objects.equals(this.chameleonNamespace, schemaToProcess.chameleonNamespace)) {
                return false;
            }
            if (this.schema == schemaToProcess.schema) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.schema.hashCode() * 29;
            String str = this.chameleonNamespace;
            return hashCode + (str != null ? str.hashCode() : 0);
        }
    }

    /* access modifiers changed from: private */
    public static String baseURLForDoc(XmlObject xmlObject) {
        String sourceName = xmlObject.documentProperties().getSourceName();
        if (sourceName == null) {
            return null;
        }
        if (sourceName.startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            return PROJECT_URL_PREFIX + sourceName.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        }
        int indexOf = sourceName.indexOf(58);
        if (indexOf <= 1 || !sourceName.substring(0, indexOf).matches("^\\w+$")) {
            return "project://local/" + sourceName.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        }
        return sourceName;
    }

    /* access modifiers changed from: private */
    public static URI parseURI(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new URI(str);
        } catch (URISyntaxException unused) {
            return null;
        }
    }

    public static URI resolve(URI uri, String str) throws URISyntaxException {
        URI uri2 = new URI(str);
        URI resolve = uri.resolve(uri2);
        if (uri2.equals(resolve) && !uri2.isAbsolute() && (uri.getScheme().equals(ArchiveStreamFactory.JAR) || uri.getScheme().equals(ArchiveStreamFactory.ZIP))) {
            String uri3 = uri.toString();
            String str2 = uri3.substring(0, uri3.lastIndexOf(47)) + PackagingURIHelper.FORWARD_SLASH_STRING + uri2;
            int lastIndexOf = str2.lastIndexOf("!/");
            if (lastIndexOf > 0) {
                for (int indexOf = str2.indexOf("/..", lastIndexOf); indexOf > 0; indexOf = str2.indexOf("/..", lastIndexOf)) {
                    int lastIndexOf2 = str2.lastIndexOf(PackagingURIHelper.FORWARD_SLASH_STRING, indexOf - 1);
                    if (lastIndexOf2 >= lastIndexOf) {
                        str2 = str2.substring(0, lastIndexOf2).concat(str2.substring(indexOf + 3));
                    }
                }
            }
            return URI.create(str2);
        } else if (!"file".equals(resolve.getScheme()) || str.equals(resolve.getPath()) || !uri.getPath().startsWith("//") || resolve.getPath().startsWith("//")) {
            return resolve;
        } else {
            try {
                return new URI("file", (String) null, "///".concat(resolve.getPath()), resolve.getQuery(), resolve.getFragment());
            } catch (URISyntaxException unused) {
                return resolve;
            }
        }
    }

    public static class DownloadTable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Set<SchemaDocument.Schema> emptyNamespaceSchemas = new HashSet();
        private final Set<String> failedDownloads = new HashSet();
        private final LinkedList<SchemaToProcess> scanNeeded = new LinkedList<>();
        private final Map<SchemaToProcess, SchemaToProcess> scannedAlready = new HashMap();
        private final Map<DigestKey, SchemaDocument.Schema> schemaByDigestKey = new HashMap();
        private final Map<NsLocPair, SchemaDocument.Schema> schemaByNsLocPair = new HashMap();

        private static String emptyStringIfNull(String str) {
            return str == null ? "" : str;
        }

        static {
            Class<StscImporter> cls = StscImporter.class;
        }

        private static class NsLocPair {
            private final String locationURL;
            private final String namespaceURI;

            public NsLocPair(String str, String str2) {
                this.namespaceURI = str;
                this.locationURL = str2;
            }

            public String getNamespaceURI() {
                return this.namespaceURI;
            }

            public String getLocationURL() {
                return this.locationURL;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof NsLocPair)) {
                    return false;
                }
                NsLocPair nsLocPair = (NsLocPair) obj;
                if (!Objects.equals(this.locationURL, nsLocPair.locationURL)) {
                    return false;
                }
                return Objects.equals(this.namespaceURI, nsLocPair.namespaceURI);
            }

            public int hashCode() {
                String str = this.namespaceURI;
                int i = 0;
                int hashCode = (str != null ? str.hashCode() : 0) * 29;
                String str2 = this.locationURL;
                if (str2 != null) {
                    i = str2.hashCode();
                }
                return hashCode + i;
            }
        }

        private static class DigestKey {
            byte[] _digest;
            int _hashCode;

            DigestKey(byte[] bArr) {
                this._digest = bArr;
                int i = 0;
                while (i < 4 && i < bArr.length) {
                    this._hashCode = (this._hashCode << 8) + bArr[i];
                    i++;
                }
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof DigestKey)) {
                    return false;
                }
                return Arrays.equals(this._digest, ((DigestKey) obj)._digest);
            }

            public int hashCode() {
                return this._hashCode;
            }
        }

        private SchemaDocument.Schema downloadSchema(XmlObject xmlObject, String str, String str2) {
            SchemaDocument.Schema schema;
            SchemaDocument.Schema schema2;
            if (str2 == null) {
                return null;
            }
            StscState stscState = StscState.get();
            URI access$100 = StscImporter.parseURI(StscImporter.baseURLForDoc(xmlObject));
            if (access$100 != null) {
                try {
                    str2 = StscImporter.resolve(access$100, str2).toString();
                } catch (URISyntaxException e) {
                    stscState.error("Could not find resource - invalid location URL: " + e.getMessage(), 56, xmlObject);
                    return null;
                }
            }
            if (stscState.isFileProcessed(str2)) {
                return null;
            }
            if (str != null && (schema2 = this.schemaByNsLocPair.get(new NsLocPair(str, str2))) != null) {
                return schema2;
            }
            if (str != null && !str.equals("")) {
                if (!stscState.shouldDownloadURI(str2) && (schema = this.schemaByNsLocPair.get(new NsLocPair(str, (String) null))) != null) {
                    return schema;
                }
                if (stscState.linkerDefinesNamespace(str)) {
                    return null;
                }
            }
            SchemaDocument.Schema schema3 = this.schemaByNsLocPair.get(new NsLocPair((String) null, str2));
            if (schema3 != null) {
                return schema3;
            }
            if (previouslyFailedToDownload(str2)) {
                return null;
            }
            if (!stscState.shouldDownloadURI(str2)) {
                stscState.error("Could not load resource \"" + str2 + "\" (network downloads disabled).", 56, xmlObject);
                addFailedDownload(str2);
                return null;
            }
            try {
                XmlObject downloadDocument = downloadDocument(stscState.getS4SLoader(), str, str2);
                SchemaDocument.Schema findMatchByDigest = findMatchByDigest(downloadDocument);
                String relativize = stscState.relativize(str2);
                if (findMatchByDigest != null) {
                    String relativize2 = stscState.relativize(findMatchByDigest.documentProperties().getSourceName());
                    if (relativize2 != null) {
                        stscState.info(relativize + " is the same as " + relativize2 + " (ignoring the duplicate file)");
                    } else {
                        stscState.info(relativize + " is the same as another schema");
                    }
                } else {
                    XmlOptions xmlOptions = new XmlOptions();
                    xmlOptions.setErrorListener(stscState.getErrorListener());
                    if (downloadDocument instanceof SchemaDocument) {
                        if (downloadDocument.validate(xmlOptions)) {
                            findMatchByDigest = ((SchemaDocument) downloadDocument).getSchema();
                            stscState.info("Loading referenced file " + relativize);
                        }
                    }
                    stscState.error("Referenced document is not a valid schema", 56, xmlObject);
                    addFailedDownload(str2);
                    return null;
                }
                addSuccessfulDownload(new NsLocPair(emptyStringIfNull(findMatchByDigest.getTargetNamespace()), str2), findMatchByDigest);
                return findMatchByDigest;
            } catch (MalformedURLException unused) {
                stscState.error("URL \"" + str2 + "\" is not well-formed", 56, xmlObject);
            } catch (IOException e2) {
                stscState.error(e2.toString(), 56, xmlObject);
            } catch (XmlException e3) {
                stscState.error("Problem parsing referenced XML resource - " + e3.getMessage(), 56, xmlObject);
            }
        }

        static XmlObject downloadDocument(SchemaTypeLoader schemaTypeLoader, String str, String str2) throws IOException, XmlException {
            StscState stscState = StscState.get();
            EntityResolver entityResolver = stscState.getEntityResolver();
            if (entityResolver != null) {
                try {
                    InputSource resolveEntity = entityResolver.resolveEntity(str, str2);
                    if (resolveEntity != null) {
                        stscState.addSourceUri(str2, (String) null);
                        Reader characterStream = resolveEntity.getCharacterStream();
                        if (characterStream != null) {
                            Reader copySchemaSource = copySchemaSource(str2, characterStream, stscState);
                            XmlOptions xmlOptions = new XmlOptions();
                            xmlOptions.setLoadLineNumbers();
                            xmlOptions.setDocumentSourceName(str2);
                            return schemaTypeLoader.parse(copySchemaSource, (SchemaType) null, xmlOptions);
                        }
                        InputStream byteStream = resolveEntity.getByteStream();
                        if (byteStream != null) {
                            InputStream copySchemaSource2 = copySchemaSource(str2, byteStream, stscState);
                            String encoding = resolveEntity.getEncoding();
                            XmlOptions xmlOptions2 = new XmlOptions();
                            xmlOptions2.setLoadLineNumbers();
                            xmlOptions2.setLoadMessageDigest();
                            xmlOptions2.setDocumentSourceName(str2);
                            if (encoding != null) {
                                xmlOptions2.setCharacterEncoding(encoding);
                            }
                            return schemaTypeLoader.parse(copySchemaSource2, (SchemaType) null, xmlOptions2);
                        }
                        String systemId = resolveEntity.getSystemId();
                        if (systemId != null) {
                            copySchemaSource(str2, stscState, false);
                            XmlOptions xmlOptions3 = new XmlOptions();
                            xmlOptions3.setLoadLineNumbers();
                            xmlOptions3.setLoadMessageDigest();
                            xmlOptions3.setDocumentSourceName(str2);
                            return schemaTypeLoader.parse(new URL(systemId), (SchemaType) null, xmlOptions3);
                        }
                        throw new IOException("EntityResolver unable to resolve " + str2 + " (for namespace " + str + ")");
                    }
                } catch (SAXException e) {
                    throw new XmlException((Throwable) e);
                }
            }
            stscState.addSourceUri(str2, (String) null);
            copySchemaSource(str2, stscState, false);
            XmlOptions xmlOptions4 = new XmlOptions();
            xmlOptions4.setLoadLineNumbers();
            xmlOptions4.setLoadMessageDigest();
            return schemaTypeLoader.parse(new URL(str2), (SchemaType) null, xmlOptions4);
        }

        private void addSuccessfulDownload(NsLocPair nsLocPair, SchemaDocument.Schema schema) {
            byte[] messageDigest = schema.documentProperties().getMessageDigest();
            if (messageDigest == null) {
                StscState.get().addSchemaDigest((byte[]) null);
            } else {
                if (!this.schemaByDigestKey.containsKey(new DigestKey(messageDigest))) {
                    this.schemaByDigestKey.put(new DigestKey(messageDigest), schema);
                    StscState.get().addSchemaDigest(messageDigest);
                }
            }
            this.schemaByNsLocPair.put(nsLocPair, schema);
            NsLocPair nsLocPair2 = new NsLocPair(nsLocPair.getNamespaceURI(), (String) null);
            if (!this.schemaByNsLocPair.containsKey(nsLocPair2)) {
                this.schemaByNsLocPair.put(nsLocPair2, schema);
            }
            NsLocPair nsLocPair3 = new NsLocPair((String) null, nsLocPair.getLocationURL());
            if (!this.schemaByNsLocPair.containsKey(nsLocPair3)) {
                this.schemaByNsLocPair.put(nsLocPair3, schema);
            }
        }

        private SchemaDocument.Schema findMatchByDigest(XmlObject xmlObject) {
            byte[] messageDigest = xmlObject.documentProperties().getMessageDigest();
            if (messageDigest == null) {
                return null;
            }
            return this.schemaByDigestKey.get(new DigestKey(messageDigest));
        }

        private void addFailedDownload(String str) {
            this.failedDownloads.add(str);
        }

        private boolean previouslyFailedToDownload(String str) {
            return this.failedDownloads.contains(str);
        }

        private static boolean nullableStringsMatch(String str, String str2) {
            if (str == null && str2 == null) {
                return true;
            }
            if (str == null || str2 == null) {
                return false;
            }
            return str.equals(str2);
        }

        private SchemaToProcess addScanNeeded(SchemaToProcess schemaToProcess) {
            if (this.scannedAlready.containsKey(schemaToProcess)) {
                return this.scannedAlready.get(schemaToProcess);
            }
            this.scannedAlready.put(schemaToProcess, schemaToProcess);
            this.scanNeeded.add(schemaToProcess);
            return schemaToProcess;
        }

        private void addEmptyNamespaceSchema(SchemaDocument.Schema schema) {
            this.emptyNamespaceSchemas.add(schema);
        }

        private void usedEmptyNamespaceSchema(SchemaDocument.Schema schema) {
            this.emptyNamespaceSchemas.remove(schema);
        }

        private boolean fetchRemainingEmptyNamespaceSchemas() {
            if (this.emptyNamespaceSchemas.isEmpty()) {
                return false;
            }
            for (SchemaDocument.Schema schemaToProcess : this.emptyNamespaceSchemas) {
                addScanNeeded(new SchemaToProcess(schemaToProcess, (String) null));
            }
            this.emptyNamespaceSchemas.clear();
            return true;
        }

        private boolean hasNextToScan() {
            return !this.scanNeeded.isEmpty();
        }

        private SchemaToProcess nextToScan() {
            return this.scanNeeded.removeFirst();
        }

        public DownloadTable(SchemaDocument.Schema[] schemaArr) {
            for (SchemaDocument.Schema schema : schemaArr) {
                String targetNamespace = schema.getTargetNamespace();
                addSuccessfulDownload(new NsLocPair(targetNamespace, StscImporter.baseURLForDoc(schema)), schema);
                if (targetNamespace != null) {
                    addScanNeeded(new SchemaToProcess(schema, (String) null));
                } else {
                    addEmptyNamespaceSchema(schema);
                }
            }
        }

        public SchemaToProcess[] resolveImportsAndIncludes(boolean z) {
            StscState stscState;
            StscState stscState2;
            StscState stscState3 = StscState.get();
            ArrayList<SchemaToProcess> arrayList = new ArrayList<>();
            boolean z2 = false;
            while (true) {
                if (hasNextToScan()) {
                    SchemaToProcess nextToScan = nextToScan();
                    String sourceName = nextToScan.getSourceName();
                    stscState3.addSourceUri(sourceName, (String) null);
                    arrayList.add(nextToScan);
                    copySchemaSource(sourceName, stscState3, z);
                    for (ImportDocument.Import importR : nextToScan.getSchema().getImportArray()) {
                        SchemaDocument.Schema downloadSchema = downloadSchema(importR, emptyStringIfNull(importR.getNamespace()), importR.getSchemaLocation());
                        if (downloadSchema != null) {
                            if (!nullableStringsMatch(downloadSchema.getTargetNamespace(), importR.getNamespace())) {
                                StscState.get().error("Imported schema has a target namespace \"" + downloadSchema.getTargetNamespace() + "\" that does not match the specified \"" + importR.getNamespace() + "\"", 4, (XmlObject) importR);
                            } else {
                                addScanNeeded(new SchemaToProcess(downloadSchema, (String) null));
                            }
                        }
                    }
                    IncludeDocument.Include[] includeArray = nextToScan.getSchema().getIncludeArray();
                    String chameleonNamespace = nextToScan.getChameleonNamespace();
                    if (chameleonNamespace == null) {
                        chameleonNamespace = emptyStringIfNull(nextToScan.getSchema().getTargetNamespace());
                    }
                    int length = includeArray.length;
                    int i = 0;
                    while (i < length) {
                        IncludeDocument.Include include = includeArray[i];
                        SchemaDocument.Schema downloadSchema2 = downloadSchema(include, (String) null, include.getSchemaLocation());
                        if (downloadSchema2 != null) {
                            if (emptyStringIfNull(downloadSchema2.getTargetNamespace()).equals(chameleonNamespace)) {
                                nextToScan.addInclude(addScanNeeded(new SchemaToProcess(downloadSchema2, (String) null)));
                            } else {
                                if (downloadSchema2.getTargetNamespace() != null) {
                                    stscState2 = stscState3;
                                    StscState.get().error("Included schema has a target namespace \"" + downloadSchema2.getTargetNamespace() + "\" that does not match the source namespace \"" + chameleonNamespace + "\"", 4, (XmlObject) include);
                                } else {
                                    stscState2 = stscState3;
                                    nextToScan.addInclude(addScanNeeded(new SchemaToProcess(downloadSchema2, chameleonNamespace)));
                                    usedEmptyNamespaceSchema(downloadSchema2);
                                }
                                i++;
                                stscState3 = stscState2;
                            }
                        }
                        stscState2 = stscState3;
                        i++;
                        stscState3 = stscState2;
                    }
                    stscState = stscState3;
                    RedefineDocument.Redefine[] redefineArray = nextToScan.getSchema().getRedefineArray();
                    String chameleonNamespace2 = nextToScan.getChameleonNamespace();
                    if (chameleonNamespace2 == null) {
                        chameleonNamespace2 = emptyStringIfNull(nextToScan.getSchema().getTargetNamespace());
                    }
                    for (RedefineDocument.Redefine redefine : redefineArray) {
                        SchemaDocument.Schema downloadSchema3 = downloadSchema(redefine, (String) null, redefine.getSchemaLocation());
                        if (downloadSchema3 != null) {
                            if (emptyStringIfNull(downloadSchema3.getTargetNamespace()).equals(chameleonNamespace2)) {
                                nextToScan.addRedefine(addScanNeeded(new SchemaToProcess(downloadSchema3, (String) null)), redefine);
                                z2 = true;
                            } else {
                                if (downloadSchema3.getTargetNamespace() != null) {
                                    StscState.get().error("Redefined schema has a target namespace \"" + downloadSchema3.getTargetNamespace() + "\" that does not match the source namespace \"" + chameleonNamespace2 + "\"", 4, (XmlObject) redefine);
                                } else {
                                    nextToScan.addRedefine(addScanNeeded(new SchemaToProcess(downloadSchema3, chameleonNamespace2)), redefine);
                                    usedEmptyNamespaceSchema(downloadSchema3);
                                    z2 = true;
                                }
                            }
                        }
                    }
                } else {
                    boolean z3 = z;
                    stscState = stscState3;
                    if (!fetchRemainingEmptyNamespaceSchemas()) {
                        break;
                    }
                }
                stscState3 = stscState;
            }
            if (z2) {
                for (SchemaToProcess access$400 : arrayList) {
                    access$400.buildIndirectReferences();
                }
            }
            return (SchemaToProcess[]) arrayList.toArray(new SchemaToProcess[0]);
        }

        private static Reader copySchemaSource(String str, Reader reader, StscState stscState) {
            if (stscState.getSchemasDir() == null) {
                return reader;
            }
            File file = new File(stscState.getSchemasDir(), stscState.sourceNameForUri(str));
            if (file.exists()) {
                return reader;
            }
            try {
                IOUtil.createDir(new File(file.getParent()), (String) null);
                CharArrayReader copy = copy(reader);
                IOUtil.copyCompletely((Reader) copy, (Writer) new OutputStreamWriter(new FileOutputStream(file), new XmlEncodingSniffer((Reader) copy, (String) null).getXmlEncoding()));
                copy.reset();
                return copy;
            } catch (IOException e) {
                System.err.println("IO Error " + e);
                return reader;
            }
        }

        private static InputStream copySchemaSource(String str, InputStream inputStream, StscState stscState) {
            if (stscState.getSchemasDir() == null) {
                return inputStream;
            }
            File file = new File(stscState.getSchemasDir(), stscState.sourceNameForUri(str));
            if (file.exists()) {
                return inputStream;
            }
            try {
                IOUtil.createDir(new File(file.getParent()), (String) null);
                ByteArrayInputStream copy = copy(inputStream);
                IOUtil.copyCompletely((InputStream) copy, (OutputStream) new FileOutputStream(file));
                copy.reset();
                return copy;
            } catch (IOException e) {
                System.err.println("IO Error " + e);
                return inputStream;
            }
        }

        private static void copySchemaSource(String str, StscState stscState, boolean z) {
            InputStream inputStream;
            if (stscState.getSchemasDir() != null) {
                File file = new File(stscState.getSchemasDir(), stscState.sourceNameForUri(str));
                if (z || !file.exists()) {
                    try {
                        inputStream = null;
                        IOUtil.createDir(new File(file.getParent()), (String) null);
                        inputStream = new URL(str).openStream();
                    } catch (FileNotFoundException e) {
                        if (z) {
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                        throw e;
                    } catch (IOException e2) {
                        System.err.println("IO Error " + e2);
                        return;
                    }
                    if (inputStream != null) {
                        IOUtil.copyCompletely(inputStream, (OutputStream) new FileOutputStream(file));
                    }
                }
            }
        }

        private static ByteArrayInputStream copy(InputStream inputStream) throws IOException {
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = inputStream.read(bArr, 0, 1024);
                if (read <= 0) {
                    return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        }

        private static CharArrayReader copy(Reader reader) throws IOException {
            char[] cArr = new char[1024];
            CharArrayWriter charArrayWriter = new CharArrayWriter();
            while (true) {
                int read = reader.read(cArr, 0, 1024);
                if (read <= 0) {
                    return new CharArrayReader(charArrayWriter.toCharArray());
                }
                charArrayWriter.write(cArr, 0, read);
            }
        }
    }
}
