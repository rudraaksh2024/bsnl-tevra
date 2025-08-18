package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.xmlbeans.XmlError;

public class XMLBean extends MatchingTask {
    private static final String JAVA = ".java";
    private static final String WSDL = ".wsdl";
    private static final String XSD = ".xsd";
    private static final String XSDCONFIG = ".xsdconfig";
    private final Map<String, Set<File>> _extRouter = new HashMap(5);
    private String catalog;
    private File classgendir;
    private Path classpath;
    private String compiler;
    private boolean debug;
    private String debugLevel;
    private File destfile;
    private boolean download;
    private final List<Extension> extensions = new ArrayList();
    private boolean failonerror = true;
    private boolean fork = true;
    private String forkedExecutable;
    private boolean includeAntRuntime = true;
    private boolean includeJavaRuntime = false;
    private Set<String> mdefnamespaces;
    private String memoryInitialSize;
    private String memoryMaximumSize;
    private boolean noSrcRegen;
    private boolean noann;
    private boolean noext = false;
    private boolean nopvr;
    private boolean noupa;
    private boolean novdoc;
    private boolean nowarn = false;
    private boolean optimize;
    private String partialMethods;
    private boolean quiet;
    private String repackage;
    private File schema;
    private final List<FileSet> schemas = new ArrayList();
    private String source = null;
    private File srcgendir;
    private boolean srconly;
    private String typesystemname;
    private boolean verbose;

    /* JADX WARNING: Code restructure failed: missing block: B:106:0x034b, code lost:
        throw new org.apache.tools.ant.BuildException(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x034c, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x034d, code lost:
        throw r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x02fb, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x030d, code lost:
        log("Exception while building schemas: " + r0.getMessage(), 0);
        r2 = new java.io.StringWriter();
        r0.printStackTrace(new java.io.PrintWriter(r2));
        log(r2.toString(), 3);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x033f  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0346  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x034c A[ExcHandler: BuildException (r15v1 'e' org.apache.tools.ant.BuildException A[CUSTOM_DECLARE]), Splitter:B:45:0x0143] */
    /* JADX WARNING: Removed duplicated region for block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0156 A[Catch:{ BuildException -> 0x034c, all -> 0x0303 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0162 A[Catch:{ BuildException -> 0x034c, all -> 0x0303 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x016e A[Catch:{ BuildException -> 0x034c, all -> 0x0303 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0194 A[Catch:{ BuildException -> 0x034c, all -> 0x0303 }, LOOP:1: B:63:0x0191->B:65:0x0194, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0221 A[SYNTHETIC, Splitter:B:68:0x0221] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x02ff A[Catch:{ BuildException -> 0x034c, all -> 0x02fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x030d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            r15 = this;
            java.lang.String r0 = "1.8"
            java.util.List<org.apache.tools.ant.types.FileSet> r1 = r15.schemas
            int r1 = r1.size()
            r2 = 0
            if (r1 != 0) goto L_0x002b
            java.io.File r1 = r15.schema
            if (r1 != 0) goto L_0x002b
            org.apache.tools.ant.types.FileSet r1 = r15.fileset
            org.apache.tools.ant.Project r3 = r15.getProject()
            java.io.File r1 = r1.getDir(r3)
            if (r1 != 0) goto L_0x002b
            boolean r0 = r15.failonerror
            java.lang.String r1 = "The 'schema' or 'dir' attribute or a nested fileset is required."
            if (r0 != 0) goto L_0x0025
            r15.log(r1, r2)
            return
        L_0x0025:
            org.apache.tools.ant.BuildException r15 = new org.apache.tools.ant.BuildException
            r15.<init>(r1)
            throw r15
        L_0x002b:
            java.util.Map<java.lang.String, java.util.Set<java.io.File>> r1 = r15._extRouter
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.lang.String r4 = ".xsd"
            r1.put(r4, r3)
            java.util.Map<java.lang.String, java.util.Set<java.io.File>> r1 = r15._extRouter
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.lang.String r5 = ".wsdl"
            r1.put(r5, r3)
            java.util.Map<java.lang.String, java.util.Set<java.io.File>> r1 = r15._extRouter
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.lang.String r6 = ".java"
            r1.put(r6, r3)
            java.util.Map<java.lang.String, java.util.Set<java.io.File>> r1 = r15._extRouter
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.lang.String r7 = ".xsdconfig"
            r1.put(r7, r3)
            java.io.File r1 = r15.schema
            r3 = 1
            if (r1 == 0) goto L_0x008b
            boolean r8 = r1.isDirectory()
            if (r8 == 0) goto L_0x0078
            java.io.File r8 = r15.schema
            org.apache.tools.ant.DirectoryScanner r8 = r15.getDirectoryScanner(r8)
            java.lang.String[] r9 = r8.getIncludedFiles()
            java.io.File r8 = r8.getBasedir()
            r15.processPaths(r9, r8)
            goto L_0x008b
        L_0x0078:
            java.io.File r1 = r15.schema
            java.io.File r1 = r1.getParentFile()
            java.lang.String[] r8 = new java.lang.String[r3]
            java.io.File r9 = r15.schema
            java.lang.String r9 = r9.getName()
            r8[r2] = r9
            r15.processPaths(r8, r1)
        L_0x008b:
            org.apache.tools.ant.types.FileSet r8 = r15.fileset
            org.apache.tools.ant.Project r9 = r15.getProject()
            java.io.File r8 = r8.getDir(r9)
            if (r8 == 0) goto L_0x009e
            java.util.List<org.apache.tools.ant.types.FileSet> r8 = r15.schemas
            org.apache.tools.ant.types.FileSet r9 = r15.fileset
            r8.add(r9)
        L_0x009e:
            java.util.List<org.apache.tools.ant.types.FileSet> r8 = r15.schemas
            java.util.Iterator r8 = r8.iterator()
        L_0x00a4:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00c4
            java.lang.Object r9 = r8.next()
            org.apache.tools.ant.types.FileSet r9 = (org.apache.tools.ant.types.FileSet) r9
            org.apache.tools.ant.Project r10 = r15.getProject()
            org.apache.tools.ant.DirectoryScanner r9 = r9.getDirectoryScanner(r10)
            java.io.File r10 = r9.getBasedir()
            java.lang.String[] r9 = r9.getIncludedFiles()
            r15.processPaths(r9, r10)
            goto L_0x00a4
        L_0x00c4:
            java.util.Map<java.lang.String, java.util.Set<java.io.File>> r8 = r15._extRouter
            java.lang.Object r4 = r8.get(r4)
            java.util.Set r4 = (java.util.Set) r4
            java.util.Map<java.lang.String, java.util.Set<java.io.File>> r8 = r15._extRouter
            java.lang.Object r5 = r8.get(r5)
            java.util.Set r5 = (java.util.Set) r5
            int r8 = r4.size()
            int r9 = r5.size()
            int r8 = r8 + r9
            if (r8 != 0) goto L_0x00e5
            java.lang.String r0 = "Could not find any xsd or wsdl files to process."
            r15.log(r0, r3)
            return
        L_0x00e5:
            java.util.Map<java.lang.String, java.util.Set<java.io.File>> r8 = r15._extRouter
            java.lang.Object r6 = r8.get(r6)
            java.util.Set r6 = (java.util.Set) r6
            java.util.Map<java.lang.String, java.util.Set<java.io.File>> r8 = r15._extRouter
            java.lang.Object r7 = r8.get(r7)
            java.util.Set r7 = (java.util.Set) r7
            java.io.File r8 = r15.srcgendir
            if (r8 != 0) goto L_0x0101
            boolean r8 = r15.srconly
            if (r8 == 0) goto L_0x0101
            java.io.File r8 = r15.classgendir
            r15.srcgendir = r8
        L_0x0101:
            java.io.File r8 = r15.destfile
            if (r8 != 0) goto L_0x0116
            java.io.File r8 = r15.classgendir
            if (r8 != 0) goto L_0x0116
            boolean r8 = r15.srconly
            if (r8 != 0) goto L_0x0116
            java.io.File r8 = new java.io.File
            java.lang.String r9 = "xmltypes.jar"
            r8.<init>(r9)
            r15.destfile = r8
        L_0x0116:
            boolean r8 = r15.verbose
            if (r8 == 0) goto L_0x011c
            r15.quiet = r2
        L_0x011c:
            java.io.File[] r8 = new java.io.File[r2]
            java.lang.Object[] r4 = r4.toArray(r8)
            java.io.File[] r4 = (java.io.File[]) r4
            java.io.File[] r8 = new java.io.File[r2]
            java.lang.Object[] r5 = r5.toArray(r8)
            java.io.File[] r5 = (java.io.File[]) r5
            java.io.File[] r8 = new java.io.File[r2]
            java.lang.Object[] r6 = r6.toArray(r8)
            java.io.File[] r6 = (java.io.File[]) r6
            java.io.File[] r8 = new java.io.File[r2]
            java.lang.Object[] r7 = r7.toArray(r8)
            java.io.File[] r7 = (java.io.File[]) r7
            org.apache.xmlbeans.impl.tool.XMLBean$ErrorLogger r8 = new org.apache.xmlbeans.impl.tool.XMLBean$ErrorLogger
            boolean r9 = r15.verbose
            r8.<init>(r9)
            java.io.File r9 = r15.srcgendir     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            if (r9 == 0) goto L_0x014e
            java.io.File r9 = r15.classgendir     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            if (r9 != 0) goto L_0x014c
            goto L_0x014e
        L_0x014c:
            r9 = 0
            goto L_0x0152
        L_0x014e:
            java.io.File r9 = org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.createTempDir()     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
        L_0x0152:
            java.io.File r10 = r15.srcgendir     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            if (r10 != 0) goto L_0x015e
            java.lang.String r10 = "src"
            java.io.File r10 = org.apache.xmlbeans.impl.common.IOUtil.createDir(r9, r10)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r15.srcgendir = r10     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
        L_0x015e:
            java.io.File r10 = r15.classgendir     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            if (r10 != 0) goto L_0x016a
            java.lang.String r10 = "classes"
            java.io.File r10 = org.apache.xmlbeans.impl.common.IOUtil.createDir(r9, r10)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r15.classgendir = r10     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
        L_0x016a:
            org.apache.tools.ant.types.Path r10 = r15.classpath     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            if (r10 != 0) goto L_0x017c
            org.apache.tools.ant.types.Path r10 = new org.apache.tools.ant.types.Path     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            org.apache.tools.ant.Project r11 = r15.getProject()     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.<init>(r11)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r15.classpath = r10     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.concatSystemClasspath()     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
        L_0x017c:
            org.apache.tools.ant.types.Path r10 = r15.classpath     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            org.apache.tools.ant.types.Path$PathElement r10 = r10.createPathElement()     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.io.File r11 = r15.classgendir     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setLocation(r11)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            org.apache.tools.ant.types.Path r10 = r15.classpath     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.lang.String[] r10 = r10.list()     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            int r11 = r10.length     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.io.File[] r11 = new java.io.File[r11]     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r12 = r2
        L_0x0191:
            int r13 = r10.length     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            if (r12 >= r13) goto L_0x01a0
            java.io.File r13 = new java.io.File     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r14 = r10[r12]     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r13.<init>(r14)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r11[r12] = r13     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            int r12 = r12 + 1
            goto L_0x0191
        L_0x01a0:
            org.apache.xmlbeans.impl.tool.Parameters r10 = new org.apache.xmlbeans.impl.tool.Parameters     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.<init>()     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setBaseDir(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setXsdFiles(r4)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setWsdlFiles(r5)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setJavaFiles(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setConfigFiles(r7)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setClasspath(r11)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.lang.String r1 = r15.typesystemname     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setName(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.io.File r1 = r15.srcgendir     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setSrcDir(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.io.File r1 = r15.classgendir     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setClassesDir(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setNojavac(r3)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = r15.debug     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setDebug(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = r15.verbose     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setVerbose(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = r15.quiet     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setQuiet(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = r15.download     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setDownload(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.util.List<org.apache.xmlbeans.impl.tool.Extension> r1 = r15.extensions     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setExtensions(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setErrorListener(r8)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.lang.String r1 = r15.catalog     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setCatalogFile(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = r15.noSrcRegen     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setIncrementalSrcGen(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.util.Set<java.lang.String> r1 = r15.mdefnamespaces     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setMdefNamespaces(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = r15.noupa     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setNoUpa(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = r15.nopvr     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setNoPvr(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = r15.noann     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setNoAnn(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = r15.novdoc     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setNoVDoc(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = r15.noext     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setNoExt(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.lang.String r1 = r15.repackage     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setRepackage(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.lang.String r1 = r15.partialMethods     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            java.util.Set r1 = org.apache.xmlbeans.impl.tool.SchemaCompiler.parsePartialMethods(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            r10.setPartialMethods(r1)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            boolean r1 = org.apache.xmlbeans.impl.tool.SchemaCompiler.compile(r10)     // Catch:{ BuildException -> 0x034c, all -> 0x0303 }
            if (r1 == 0) goto L_0x02fd
            boolean r3 = r15.srconly     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            if (r3 != 0) goto L_0x02fd
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            org.apache.tools.ant.taskdefs.Javac r5 = new org.apache.tools.ant.taskdefs.Javac     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.<init>()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            org.apache.tools.ant.Project r6 = r15.getProject()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setProject(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.lang.String r6 = r15.getTaskName()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setTaskName(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            org.apache.tools.ant.types.Path r6 = r15.classpath     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setClasspath(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.lang.String r6 = r15.compiler     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            if (r6 == 0) goto L_0x0248
            r5.setCompiler(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
        L_0x0248:
            boolean r6 = r15.debug     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setDebug(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.lang.String r6 = r15.debugLevel     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            if (r6 == 0) goto L_0x0254
            r5.setDebugLevel(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
        L_0x0254:
            java.io.File r6 = r15.classgendir     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setDestdir(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.lang.String r6 = r15.forkedExecutable     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setExecutable(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            boolean r6 = r15.failonerror     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setFailonerror(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            boolean r6 = r15.fork     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setFork(r6)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setSource(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setTarget(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            boolean r0 = r15.includeAntRuntime     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setIncludeantruntime(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            boolean r0 = r15.includeJavaRuntime     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setIncludejavaruntime(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            boolean r0 = r15.nowarn     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setNowarn(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            org.apache.tools.ant.types.Path r0 = new org.apache.tools.ant.types.Path     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            org.apache.tools.ant.Project r6 = r15.getProject()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.io.File r7 = r15.srcgendir     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r0.<init>(r6, r7)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setSrcdir(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.lang.String r0 = r15.memoryInitialSize     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            if (r0 == 0) goto L_0x0296
            r5.setMemoryInitialSize(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
        L_0x0296:
            java.lang.String r0 = r15.memoryMaximumSize     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            if (r0 == 0) goto L_0x029d
            r5.setMemoryMaximumSize(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
        L_0x029d:
            boolean r0 = r15.optimize     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setOptimize(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            boolean r0 = r15.verbose     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.setVerbose(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5.execute()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            boolean r0 = r15.quiet     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            if (r0 != 0) goto L_0x02d6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r0.<init>()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.lang.String r7 = "Time to compile code: "
            java.lang.StringBuilder r0 = r0.append(r7)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            long r5 = r5 - r3
            double r3 = (double) r5     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r3 = r3 / r5
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.lang.String r3 = " seconds"
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.lang.String r0 = r0.toString()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r15.log(r0)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
        L_0x02d6:
            java.io.File r0 = r15.destfile     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            if (r0 == 0) goto L_0x02fd
            org.apache.tools.ant.taskdefs.Jar r0 = new org.apache.tools.ant.taskdefs.Jar     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r0.<init>()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            org.apache.tools.ant.Project r3 = r15.getProject()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r0.setProject(r3)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.lang.String r3 = r15.getTaskName()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r0.setTaskName(r3)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.io.File r3 = r15.classgendir     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r0.setBasedir(r3)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            java.io.File r3 = r15.destfile     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r0.setDestFile(r3)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            r0.execute()     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            goto L_0x02fd
        L_0x02fb:
            r0 = move-exception
            goto L_0x0305
        L_0x02fd:
            if (r9 == 0) goto L_0x0338
            org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.tryHardToDelete(r9)     // Catch:{ BuildException -> 0x034c, all -> 0x02fb }
            goto L_0x0338
        L_0x0303:
            r0 = move-exception
            r1 = r2
        L_0x0305:
            boolean r3 = r0 instanceof java.lang.InterruptedException
            if (r3 != 0) goto L_0x0346
            boolean r3 = r15.failonerror
            if (r3 != 0) goto L_0x0346
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Exception while building schemas: "
            r3.<init>(r4)
            java.lang.String r4 = r0.getMessage()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r15.log(r3, r2)
            java.io.StringWriter r2 = new java.io.StringWriter
            r2.<init>()
            java.io.PrintWriter r3 = new java.io.PrintWriter
            r3.<init>(r2)
            r0.printStackTrace(r3)
            java.lang.String r0 = r2.toString()
            r2 = 3
            r15.log(r0, r2)
        L_0x0338:
            if (r1 != 0) goto L_0x0345
            boolean r15 = r15.failonerror
            if (r15 != 0) goto L_0x033f
            goto L_0x0345
        L_0x033f:
            org.apache.tools.ant.BuildException r15 = new org.apache.tools.ant.BuildException
            r15.<init>()
            throw r15
        L_0x0345:
            return
        L_0x0346:
            org.apache.tools.ant.BuildException r15 = new org.apache.tools.ant.BuildException
            r15.<init>(r0)
            throw r15
        L_0x034c:
            r15 = move-exception
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.XMLBean.execute():void");
    }

    private void processPaths(String[] strArr, File file) {
        Set set;
        for (String str : strArr) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf > -1 && (set = this._extRouter.get(str.substring(lastIndexOf).toLowerCase(Locale.ROOT))) != null) {
                set.add(new File(file, str));
            }
        }
    }

    public void addFileset(FileSet fileSet) {
        this.schemas.add(fileSet);
    }

    public File getSchema() {
        return this.schema;
    }

    public void setSchema(File file) {
        this.schema = file;
    }

    public void setClasspath(Path path) {
        Path path2 = this.classpath;
        if (path2 != null) {
            path2.append(path);
        } else {
            this.classpath = path;
        }
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference reference) {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        this.classpath.createPath().setRefid(reference);
    }

    public Path getClasspath() {
        return this.classpath;
    }

    public File getDestfile() {
        return this.destfile;
    }

    public void setDestfile(File file) {
        this.destfile = file;
    }

    public File getSrcgendir() {
        return this.srcgendir;
    }

    public void setSrcgendir(File file) {
        this.srcgendir = file;
    }

    public File getClassgendir() {
        return this.classgendir;
    }

    public void setClassgendir(File file) {
        this.classgendir = file;
    }

    public void setCompiler(String str) {
        this.compiler = str;
    }

    public boolean isDownload() {
        return this.download;
    }

    public void setDownload(boolean z) {
        this.download = z;
    }

    public void setOptimize(boolean z) {
        this.optimize = z;
    }

    public boolean getOptimize() {
        return this.optimize;
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public boolean isQuiet() {
        return this.quiet;
    }

    public void setQuiet(boolean z) {
        this.quiet = z;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public String getDebugLevel() {
        return this.debugLevel;
    }

    public void setDebugLevel(String str) {
        this.debugLevel = str;
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    public void setFork(boolean z) {
        this.fork = z;
    }

    public void setExecutable(String str) {
        this.forkedExecutable = str;
    }

    public String getExecutable() {
        return this.forkedExecutable;
    }

    public boolean isSrconly() {
        return this.srconly;
    }

    public void setSrconly(boolean z) {
        this.srconly = z;
    }

    public String getTypesystemname() {
        return this.typesystemname;
    }

    public Extension createExtension() {
        Extension extension = new Extension();
        this.extensions.add(extension);
        return extension;
    }

    public void setIgnoreDuplicatesInNamespaces(String str) {
        this.mdefnamespaces = new HashSet();
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.mdefnamespaces.add(stringTokenizer.nextToken().trim());
        }
    }

    public String getIgnoreDuplicatesInNamespaces() {
        Set<String> set = this.mdefnamespaces;
        if (set == null) {
            return null;
        }
        return String.join(",", set);
    }

    public void setTypesystemname(String str) {
        this.typesystemname = str;
    }

    public boolean isFailonerror() {
        return this.failonerror;
    }

    public void setFailonerror(boolean z) {
        this.failonerror = z;
    }

    public boolean isIncludeAntRuntime() {
        return this.includeAntRuntime;
    }

    public void setIncludeAntRuntime(boolean z) {
        this.includeAntRuntime = z;
    }

    public boolean isIncludeJavaRuntime() {
        return this.includeJavaRuntime;
    }

    public void setIncludeJavaRuntime(boolean z) {
        this.includeJavaRuntime = z;
    }

    public boolean isNowarn() {
        return this.nowarn;
    }

    public void setNowarn(boolean z) {
        this.nowarn = z;
    }

    public boolean isNoSrcRegen() {
        return this.noSrcRegen;
    }

    public void setNoSrcRegen(boolean z) {
        this.noSrcRegen = z;
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

    public void setNoUpa(boolean z) {
        this.noupa = z;
    }

    public boolean isNoUpa() {
        return this.noupa;
    }

    public void setNoPvr(boolean z) {
        this.nopvr = z;
    }

    public boolean isNoPvr() {
        return this.nopvr;
    }

    public void setNoAnnotations(boolean z) {
        this.noann = z;
    }

    public boolean isNoAnnotations() {
        return this.noann;
    }

    public void setNoValidateDoc(boolean z) {
        this.novdoc = z;
    }

    public boolean isNoValidateDoc() {
        return this.novdoc;
    }

    public void setNoExt(boolean z) {
        this.noext = z;
    }

    public boolean isNoExt() {
        return this.noext;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setCatalog(String str) {
        this.catalog = str;
    }

    public String getRepackage() {
        return this.repackage;
    }

    public void setRepackage(String str) {
        this.repackage = str;
    }

    public String getPartialMethods() {
        return this.partialMethods;
    }

    public void setPartialMethods(String str) {
        this.partialMethods = str;
    }

    /* access modifiers changed from: private */
    public static URI uriFromFile(File file) {
        if (file == null) {
            return null;
        }
        try {
            return file.getCanonicalFile().toURI();
        } catch (IOException unused) {
            return file.getAbsoluteFile().toURI();
        }
    }

    public class ErrorLogger extends AbstractCollection<XmlError> {
        private final URI _baseURI;
        private final boolean _noisy;

        public int size() {
            return 0;
        }

        public ErrorLogger(boolean z) {
            this._noisy = z;
            this._baseURI = XMLBean.uriFromFile(XMLBean.this.getProject().getBaseDir());
        }

        public boolean add(XmlError xmlError) {
            if (xmlError.getSeverity() == 0) {
                XMLBean.this.log(xmlError.toString(this._baseURI), 0);
            } else if (xmlError.getSeverity() == 1) {
                XMLBean.this.log(xmlError.toString(this._baseURI), 1);
            } else if (this._noisy) {
                XMLBean.this.log(xmlError.toString(this._baseURI), 2);
            }
            return false;
        }

        public Iterator<XmlError> iterator() {
            return Collections.emptyIterator();
        }
    }
}
