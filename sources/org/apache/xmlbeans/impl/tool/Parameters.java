package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.xmlbeans.SchemaCodePrinter;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlOptions;
import org.xml.sax.EntityResolver;

public class Parameters {
    private File baseDir;
    private String catalogFile;
    private File classesDir;
    private File[] classpath;
    private String compiler;
    private File[] configFiles;
    private boolean copyAnn;
    private boolean debug;
    private boolean download;
    private EntityResolver entityResolver;
    private Collection<XmlError> errorListener;
    private List<Extension> extensions = Collections.emptyList();
    private boolean incrementalSrcGen;
    private File[] javaFiles;
    private Set<String> mdefNamespaces = Collections.emptySet();
    private String memoryInitialSize;
    private String memoryMaximumSize;
    private String name;
    private boolean noAnn;
    private boolean noExt;
    private boolean noPvr;
    private boolean noUpa;
    private boolean noVDoc;
    private boolean nojavac;
    private File outputJar;
    private Set<XmlOptions.BeanMethod> partialMethods = Collections.emptySet();
    private boolean quiet;
    private String repackage;
    private SchemaCodePrinter schemaCodePrinter;
    private File srcDir;
    private URL[] urlFiles;
    private boolean verbose;
    private File[] wsdlFiles;
    private File[] xsdFiles;

    public File getBaseDir() {
        return this.baseDir;
    }

    public void setBaseDir(File file) {
        this.baseDir = file;
    }

    public File[] getXsdFiles() {
        return this.xsdFiles;
    }

    public void setXsdFiles(File... fileArr) {
        this.xsdFiles = fileArr == null ? null : (File[]) fileArr.clone();
    }

    public File[] getWsdlFiles() {
        return this.wsdlFiles;
    }

    public void setWsdlFiles(File... fileArr) {
        this.wsdlFiles = fileArr == null ? null : (File[]) fileArr.clone();
    }

    public File[] getJavaFiles() {
        return this.javaFiles;
    }

    public void setJavaFiles(File... fileArr) {
        this.javaFiles = fileArr == null ? null : (File[]) fileArr.clone();
    }

    public File[] getConfigFiles() {
        return this.configFiles;
    }

    public void setConfigFiles(File... fileArr) {
        this.configFiles = fileArr == null ? null : (File[]) fileArr.clone();
    }

    public URL[] getUrlFiles() {
        return this.urlFiles;
    }

    public void setUrlFiles(URL... urlArr) {
        this.urlFiles = urlArr == null ? null : (URL[]) urlArr.clone();
    }

    public File[] getClasspath() {
        return this.classpath;
    }

    public void setClasspath(File... fileArr) {
        this.classpath = fileArr == null ? null : (File[]) fileArr.clone();
    }

    public File getOutputJar() {
        return this.outputJar;
    }

    public void setOutputJar(File file) {
        this.outputJar = file;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public File getSrcDir() {
        return this.srcDir;
    }

    public void setSrcDir(File file) {
        this.srcDir = file;
    }

    public File getClassesDir() {
        return this.classesDir;
    }

    public void setClassesDir(File file) {
        this.classesDir = file;
    }

    public boolean isNojavac() {
        return this.nojavac;
    }

    public void setNojavac(boolean z) {
        this.nojavac = z;
    }

    public boolean isQuiet() {
        return this.quiet;
    }

    public void setQuiet(boolean z) {
        this.quiet = z;
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public boolean isDownload() {
        return this.download;
    }

    public void setDownload(boolean z) {
        this.download = z;
    }

    public boolean isNoUpa() {
        return this.noUpa;
    }

    public void setNoUpa(boolean z) {
        this.noUpa = z;
    }

    public boolean isNoPvr() {
        return this.noPvr;
    }

    public void setNoPvr(boolean z) {
        this.noPvr = z;
    }

    public boolean isNoAnn() {
        return this.noAnn;
    }

    public void setNoAnn(boolean z) {
        this.noAnn = z;
    }

    public boolean isNoVDoc() {
        return this.noVDoc;
    }

    public void setNoVDoc(boolean z) {
        this.noVDoc = z;
    }

    public boolean isNoExt() {
        return this.noExt;
    }

    public void setNoExt(boolean z) {
        this.noExt = z;
    }

    public boolean isIncrementalSrcGen() {
        return this.incrementalSrcGen;
    }

    public void setIncrementalSrcGen(boolean z) {
        this.incrementalSrcGen = z;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    public String getMemoryInitialSize() {
        return this.memoryInitialSize;
    }

    public void setMemoryInitialSize(String str) {
        this.memoryInitialSize = str;
    }

    public String getMemoryMaximumSize() {
        return this.memoryMaximumSize;
    }

    public void setMemoryMaximumSize(String str) {
        this.memoryMaximumSize = str;
    }

    public String getCompiler() {
        return this.compiler;
    }

    public void setCompiler(String str) {
        this.compiler = str;
    }

    public Collection<XmlError> getErrorListener() {
        return this.errorListener;
    }

    public void setErrorListener(Collection<XmlError> collection) {
        this.errorListener = collection;
    }

    public String getRepackage() {
        return this.repackage;
    }

    public void setRepackage(String str) {
        this.repackage = str;
    }

    public boolean isCopyAnn() {
        return this.copyAnn;
    }

    public void setCopyAnn(boolean z) {
        this.copyAnn = z;
    }

    public List<Extension> getExtensions() {
        return this.extensions;
    }

    public void setExtensions(List<Extension> list) {
        this.extensions = list;
    }

    public Set<String> getMdefNamespaces() {
        return this.mdefNamespaces;
    }

    public void setMdefNamespaces(Set<String> set) {
        this.mdefNamespaces = set;
    }

    public String getCatalogFile() {
        return this.catalogFile;
    }

    public void setCatalogFile(String str) {
        this.catalogFile = str;
    }

    public SchemaCodePrinter getSchemaCodePrinter() {
        return this.schemaCodePrinter;
    }

    public void setSchemaCodePrinter(SchemaCodePrinter schemaCodePrinter2) {
        this.schemaCodePrinter = schemaCodePrinter2;
    }

    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    public void setEntityResolver(EntityResolver entityResolver2) {
        this.entityResolver = entityResolver2;
    }

    public Set<XmlOptions.BeanMethod> getPartialMethods() {
        return this.partialMethods;
    }

    public void setPartialMethods(Set<XmlOptions.BeanMethod> set) {
        this.partialMethods = set;
    }
}
