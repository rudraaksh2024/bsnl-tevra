package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.ResourceLoader;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.XmlErrorWatcher;
import org.apache.xmlbeans.impl.schema.StscImporter;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public class SchemaTypeSystemCompiler {

    public static class Parameters {
        private URI baseURI;
        private BindingConfig config;
        private Collection<XmlError> errorListener;
        private SchemaTypeSystem existingSystem;
        private boolean javaize;
        private SchemaTypeLoader linkTo;
        private String name;
        private XmlOptions options;
        private SchemaDocument.Schema[] schemas;
        private File schemasDir;
        private Map<String, String> sourcesToCopyMap;

        public SchemaTypeSystem getExistingTypeSystem() {
            return this.existingSystem;
        }

        public void setExistingTypeSystem(SchemaTypeSystem schemaTypeSystem) {
            this.existingSystem = schemaTypeSystem;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public SchemaDocument.Schema[] getSchemas() {
            return this.schemas;
        }

        public void setSchemas(SchemaDocument.Schema[] schemaArr) {
            this.schemas = schemaArr == null ? null : (SchemaDocument.Schema[]) schemaArr.clone();
        }

        public BindingConfig getConfig() {
            return this.config;
        }

        public void setConfig(BindingConfig bindingConfig) {
            this.config = bindingConfig;
        }

        public SchemaTypeLoader getLinkTo() {
            return this.linkTo;
        }

        public void setLinkTo(SchemaTypeLoader schemaTypeLoader) {
            this.linkTo = schemaTypeLoader;
        }

        public XmlOptions getOptions() {
            return this.options;
        }

        public void setOptions(XmlOptions xmlOptions) {
            this.options = xmlOptions;
        }

        public Collection<XmlError> getErrorListener() {
            return this.errorListener;
        }

        public void setErrorListener(Collection<XmlError> collection) {
            this.errorListener = collection;
        }

        public boolean isJavaize() {
            return this.javaize;
        }

        public void setJavaize(boolean z) {
            this.javaize = z;
        }

        public URI getBaseURI() {
            return this.baseURI;
        }

        public void setBaseURI(URI uri) {
            this.baseURI = uri;
        }

        public Map<String, String> getSourcesToCopyMap() {
            return this.sourcesToCopyMap;
        }

        public void setSourcesToCopyMap(Map<String, String> map) {
            this.sourcesToCopyMap = map;
        }

        public File getSchemasDir() {
            return this.schemasDir;
        }

        public void setSchemasDir(File file) {
            this.schemasDir = file;
        }
    }

    public static SchemaTypeSystem compile(Parameters parameters) {
        return compileImpl(parameters.getExistingTypeSystem(), parameters.getName(), parameters.getSchemas(), parameters.getConfig(), parameters.getLinkTo(), parameters.getOptions(), parameters.getErrorListener(), parameters.isJavaize(), parameters.getBaseURI(), parameters.getSourcesToCopyMap(), parameters.getSchemasDir());
    }

    public static SchemaTypeSystemImpl compile(String str, SchemaTypeSystem schemaTypeSystem, XmlObject[] xmlObjectArr, BindingConfig bindingConfig, SchemaTypeLoader schemaTypeLoader, Filer filer, XmlOptions xmlOptions) throws XmlException {
        XmlObject[] xmlObjectArr2 = xmlObjectArr;
        Filer filer2 = filer;
        XmlOptions maskNull = XmlOptions.maskNull(xmlOptions);
        ArrayList arrayList = new ArrayList();
        if (xmlObjectArr2 != null) {
            for (int i = 0; i < xmlObjectArr2.length; i++) {
                XmlObject xmlObject = xmlObjectArr2[i];
                if (xmlObject instanceof SchemaDocument.Schema) {
                    arrayList.add((SchemaDocument.Schema) xmlObject);
                } else if (!(xmlObject instanceof SchemaDocument) || ((SchemaDocument) xmlObject).getSchema() == null) {
                    throw new XmlException("Thread " + Thread.currentThread().getName() + ": The " + i + "th supplied input is not a schema document: its type is " + xmlObjectArr2[i].schemaType());
                } else {
                    arrayList.add(((SchemaDocument) xmlObjectArr2[i]).getSchema());
                }
            }
        }
        XmlErrorWatcher xmlErrorWatcher = new XmlErrorWatcher(maskNull.getErrorListener());
        SchemaTypeSystemImpl compileImpl = compileImpl(schemaTypeSystem, str, (SchemaDocument.Schema[]) arrayList.toArray(new SchemaDocument.Schema[0]), bindingConfig, schemaTypeLoader, maskNull, xmlErrorWatcher, filer2 != null, maskNull.getBaseURI(), (Map<String, String>) null, (File) null);
        if (!xmlErrorWatcher.hasError() || compileImpl != null) {
            if (!(compileImpl == null || compileImpl.isIncomplete() || filer2 == null)) {
                compileImpl.save(filer2);
                generateTypes(compileImpl, filer2, maskNull);
            }
            return compileImpl;
        }
        throw new XmlException(xmlErrorWatcher.firstError());
    }

    static SchemaTypeSystemImpl compileImpl(SchemaTypeSystem schemaTypeSystem, String str, SchemaDocument.Schema[] schemaArr, BindingConfig bindingConfig, SchemaTypeLoader schemaTypeLoader, XmlOptions xmlOptions, Collection<XmlError> collection, boolean z, URI uri, Map<String, String> map, File file) {
        if (schemaTypeLoader != null) {
            XmlErrorWatcher xmlErrorWatcher = new XmlErrorWatcher(collection);
            boolean z2 = schemaTypeSystem != null;
            StscState start = StscState.start();
            boolean z3 = xmlOptions == null || !xmlOptions.isCompileNoValidation();
            try {
                start.setErrorListener(xmlErrorWatcher);
                start.setBindingConfig(bindingConfig);
                start.setOptions(xmlOptions);
                start.setGivenTypeSystemName(str);
                start.setSchemasDir(file);
                if (uri != null) {
                    start.setBaseUri(uri);
                }
                start.setImportingTypeLoader(SchemaTypeLoaderImpl.build(new SchemaTypeLoader[]{BuiltinSchemaTypeSystem.get(), schemaTypeLoader}, (ResourceLoader) null, (ClassLoader) null));
                ArrayList arrayList = new ArrayList(schemaArr.length);
                if (z3) {
                    XmlOptions errorListener = new XmlOptions().setErrorListener(xmlErrorWatcher);
                    if (xmlOptions != null && xmlOptions.isValidateTreatLaxAsSkip()) {
                        errorListener.setValidateTreatLaxAsSkip();
                    }
                    for (SchemaDocument.Schema schema : schemaArr) {
                        if (schema.validate(errorListener)) {
                            arrayList.add(schema);
                        }
                    }
                } else {
                    arrayList.addAll(Arrays.asList(schemaArr));
                }
                SchemaDocument.Schema[] schemaArr2 = (SchemaDocument.Schema[]) arrayList.toArray(new SchemaDocument.Schema[0]);
                if (z2) {
                    HashSet hashSet = new HashSet();
                    schemaArr2 = getSchemasToRecompile((SchemaTypeSystemImpl) schemaTypeSystem, schemaArr2, hashSet);
                    start.initFromTypeSystem((SchemaTypeSystemImpl) schemaTypeSystem, hashSet);
                } else {
                    start.setDependencies(new SchemaDependencies());
                }
                StscTranslator.addAllDefinitions(StscImporter.resolveImportsAndIncludes(schemaArr2, z2));
                StscResolver.resolveAll();
                StscChecker.checkAll();
                StscJavaizer.javaizeAllTypes(z);
                StscState.get().sts().loadFromStscState(start);
                if (map != null) {
                    map.putAll(start.sourceCopyMap());
                }
                if (xmlErrorWatcher.hasError()) {
                    if (!start.allowPartial() || start.getRecovered() != xmlErrorWatcher.size()) {
                        StscState.end();
                        return null;
                    }
                    StscState.get().sts().setIncomplete(true);
                }
                if (schemaTypeSystem != null) {
                    ((SchemaTypeSystemImpl) schemaTypeSystem).setIncomplete(true);
                }
                return StscState.get().sts();
            } finally {
                StscState.end();
            }
        } else {
            throw new IllegalArgumentException("Must supply linkTo");
        }
    }

    private static SchemaDocument.Schema[] getSchemasToRecompile(SchemaTypeSystemImpl schemaTypeSystemImpl, SchemaDocument.Schema[] schemaArr, Set<String> set) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        int length = schemaArr.length;
        int i = 0;
        while (i < length) {
            SchemaDocument.Schema schema = schemaArr[i];
            String sourceName = schema.documentProperties().getSourceName();
            if (sourceName != null) {
                hashSet.add(sourceName);
                hashMap.put(sourceName, schema);
                arrayList.add(schema);
                i++;
            } else {
                throw new IllegalArgumentException("One of the Schema files passed in doesn't have the source set, which prevents it to be incrementally compiled");
            }
        }
        SchemaDependencies dependencies = schemaTypeSystemImpl.getDependencies();
        set.addAll(dependencies.computeTransitiveClosure(dependencies.getNamespacesTouched(hashSet)));
        List<String> filesTouched = dependencies.getFilesTouched(set);
        StscState.get().setDependencies(new SchemaDependencies(dependencies, set));
        for (String next : filesTouched) {
            if (((SchemaDocument.Schema) hashMap.get(next)) == null) {
                try {
                    XmlObject downloadDocument = StscImporter.DownloadTable.downloadDocument(StscState.get().getS4SLoader(), (String) null, next);
                    XmlOptions xmlOptions = new XmlOptions();
                    xmlOptions.setErrorListener(StscState.get().getErrorListener());
                    if (downloadDocument instanceof SchemaDocument) {
                        if (downloadDocument.validate(xmlOptions)) {
                            arrayList.add(((SchemaDocument) downloadDocument).getSchema());
                        }
                    }
                    StscState.get().error("Referenced document is not a valid schema, URL = " + next, 56, (XmlObject) null);
                } catch (MalformedURLException e) {
                    StscState.get().error(XmlErrorCodes.EXCEPTION_LOADING_URL, new Object[]{"MalformedURLException", next, e.getMessage()}, (XmlObject) null);
                } catch (IOException e2) {
                    StscState.get().error(XmlErrorCodes.EXCEPTION_LOADING_URL, new Object[]{"IOException", next, e2.getMessage()}, (XmlObject) null);
                } catch (XmlException e3) {
                    StscState.get().error(XmlErrorCodes.EXCEPTION_LOADING_URL, new Object[]{"XmlException", next, e3.getMessage()}, (XmlObject) null);
                }
            }
        }
        return (SchemaDocument.Schema[]) arrayList.toArray(new SchemaDocument.Schema[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0066, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0067, code lost:
        if (r5 != null) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0071, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b7, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b8, code lost:
        if (r5 != null) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c2, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00ea, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00eb, code lost:
        if (r5 != null) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x00f5, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean generateTypes(org.apache.xmlbeans.SchemaTypeSystem r7, org.apache.xmlbeans.Filer r8, org.apache.xmlbeans.XmlOptions r9) {
        /*
            java.lang.String r0 = "IO Error "
            boolean r1 = r7 instanceof org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl
            r2 = 0
            if (r1 == 0) goto L_0x0011
            r1 = r7
            org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl r1 = (org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl) r1
            boolean r1 = r1.isIncomplete()
            if (r1 == 0) goto L_0x0011
            return r2
        L_0x0011:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            org.apache.xmlbeans.SchemaType[] r3 = r7.globalTypes()
            java.util.List r3 = java.util.Arrays.asList(r3)
            r1.addAll(r3)
            org.apache.xmlbeans.SchemaType[] r3 = r7.documentTypes()
            java.util.List r3 = java.util.Arrays.asList(r3)
            r1.addAll(r3)
            org.apache.xmlbeans.SchemaType[] r3 = r7.attributeTypes()
            java.util.List r3 = java.util.Arrays.asList(r3)
            r1.addAll(r3)
            r3 = 0
            if (r9 != 0) goto L_0x003c
            r4 = r3
            goto L_0x0040
        L_0x003c:
            org.apache.xmlbeans.SchemaCodePrinter r4 = r9.getSchemaCodePrinter()
        L_0x0040:
            if (r4 != 0) goto L_0x0047
            org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter r4 = new org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter
            r4.<init>()
        L_0x0047:
            java.lang.String r5 = org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter.indexClassForSystem(r7)
            java.io.Writer r5 = r8.createSourceFile(r5)     // Catch:{ IOException -> 0x0072 }
            boolean r6 = r8 instanceof org.apache.xmlbeans.impl.util.FilerImpl     // Catch:{ all -> 0x0064 }
            if (r6 == 0) goto L_0x005a
            r3 = r8
            org.apache.xmlbeans.impl.util.FilerImpl r3 = (org.apache.xmlbeans.impl.util.FilerImpl) r3     // Catch:{ all -> 0x0064 }
            org.apache.xmlbeans.impl.repackage.Repackager r3 = r3.getRepackager()     // Catch:{ all -> 0x0064 }
        L_0x005a:
            r4.printHolder(r5, r7, r9, r3)     // Catch:{ all -> 0x0064 }
            if (r5 == 0) goto L_0x0062
            r5.close()     // Catch:{ IOException -> 0x0072 }
        L_0x0062:
            r7 = 1
            goto L_0x0086
        L_0x0064:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0066 }
        L_0x0066:
            r3 = move-exception
            if (r5 == 0) goto L_0x0071
            r5.close()     // Catch:{ all -> 0x006d }
            goto L_0x0071
        L_0x006d:
            r5 = move-exception
            r7.addSuppressed(r5)     // Catch:{ IOException -> 0x0072 }
        L_0x0071:
            throw r3     // Catch:{ IOException -> 0x0072 }
        L_0x0072:
            r7 = move-exception
            java.io.PrintStream r3 = java.lang.System.err
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r0)
            java.lang.StringBuilder r7 = r5.append(r7)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
            r7 = r2
        L_0x0086:
            java.util.Iterator r1 = r1.iterator()
        L_0x008a:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x010c
            java.lang.Object r3 = r1.next()
            org.apache.xmlbeans.SchemaType r3 = (org.apache.xmlbeans.SchemaType) r3
            boolean r5 = r3.isBuiltinType()
            if (r5 == 0) goto L_0x009d
            goto L_0x008a
        L_0x009d:
            java.lang.String r5 = r3.getFullJavaName()
            if (r5 != 0) goto L_0x00a4
            goto L_0x008a
        L_0x00a4:
            java.lang.String r5 = r3.getFullJavaName()
            java.io.Writer r5 = r8.createSourceFile(r5)     // Catch:{ IOException -> 0x00c3 }
            r4.printType(r5, r3, r9)     // Catch:{ all -> 0x00b5 }
            if (r5 == 0) goto L_0x00d7
            r5.close()     // Catch:{ IOException -> 0x00c3 }
            goto L_0x00d7
        L_0x00b5:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00b7 }
        L_0x00b7:
            r6 = move-exception
            if (r5 == 0) goto L_0x00c2
            r5.close()     // Catch:{ all -> 0x00be }
            goto L_0x00c2
        L_0x00be:
            r5 = move-exception
            r7.addSuppressed(r5)     // Catch:{ IOException -> 0x00c3 }
        L_0x00c2:
            throw r6     // Catch:{ IOException -> 0x00c3 }
        L_0x00c3:
            r7 = move-exception
            java.io.PrintStream r5 = java.lang.System.err
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r0)
            java.lang.StringBuilder r7 = r6.append(r7)
            java.lang.String r7 = r7.toString()
            r5.println(r7)
            r7 = r2
        L_0x00d7:
            java.lang.String r5 = r3.getFullJavaImplName()
            java.io.Writer r5 = r8.createSourceFile(r5)     // Catch:{ IOException -> 0x00f6 }
            r4.printTypeImpl(r5, r3, r9)     // Catch:{ all -> 0x00e8 }
            if (r5 == 0) goto L_0x008a
            r5.close()     // Catch:{ IOException -> 0x00f6 }
            goto L_0x008a
        L_0x00e8:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00ea }
        L_0x00ea:
            r3 = move-exception
            if (r5 == 0) goto L_0x00f5
            r5.close()     // Catch:{ all -> 0x00f1 }
            goto L_0x00f5
        L_0x00f1:
            r5 = move-exception
            r7.addSuppressed(r5)     // Catch:{ IOException -> 0x00f6 }
        L_0x00f5:
            throw r3     // Catch:{ IOException -> 0x00f6 }
        L_0x00f6:
            r7 = move-exception
            java.io.PrintStream r3 = java.lang.System.err
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r0)
            java.lang.StringBuilder r7 = r5.append(r7)
            java.lang.String r7 = r7.toString()
            r3.println(r7)
            r7 = r2
            goto L_0x008a
        L_0x010c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeSystemCompiler.generateTypes(org.apache.xmlbeans.SchemaTypeSystem, org.apache.xmlbeans.Filer, org.apache.xmlbeans.XmlOptions):boolean");
    }
}
