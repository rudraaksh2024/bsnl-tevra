package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class MavenPlugin extends AbstractMojo {
    private String baseSchemaLocation;
    private File basedir;
    private boolean buildSchemas;
    private String catalogLocation;
    private String classPath;
    private String classTargetDir;
    private String compiler;
    private boolean copyAnn;
    private boolean debug;
    private boolean download;
    private List<Extension> extensions;
    private String javaTargetDir;
    private List<String> mdefNamespaces;
    private String memoryInitialSize;
    private String memoryMaximumSize;
    private String name;
    private boolean noAnn;
    private boolean noPvr;
    private boolean noUpa;
    private boolean noVDoc;
    private File outputJar;
    private String partialMethods;
    private MavenProject project;
    private boolean quiet;
    private boolean quite;
    private String repackage;
    private List<Resource> resources;
    private String sourceDir;
    private boolean sourceOnly;
    private String sourceSchemas;
    private boolean verbose;
    private String xmlConfigs;

    public void execute() throws MojoExecutionException, MojoFailureException {
        List list;
        String str = this.sourceDir;
        if (str == null || str.isEmpty() || !new File(this.sourceDir).isDirectory()) {
            throw new MojoFailureException("Set configuration <sourceDir> (='" + this.sourceDir + "') to a valid directory containing *.xsd,*.wsdl files.");
        }
        String str2 = this.baseSchemaLocation;
        if (str2 == null || str2.isEmpty()) {
            throw new MojoFailureException("baseSchemaLocation is empty");
        }
        if (this.sourceSchemas == null) {
            getLog().debug("sourceSchemas is null");
        }
        if (this.classPath == null) {
            getLog().debug("classPath is null");
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        File file = new File(this.sourceDir);
        Resource resource = new Resource();
        resource.setDirectory(this.sourceDir);
        resource.setTargetPath(this.baseSchemaLocation);
        String str3 = ".*";
        if (this.sourceSchemas != null) {
            str3 = "(" + this.sourceSchemas.replace(",", "|").replace(".", "\\.").replace("*", str3) + ")";
        }
        boolean z = false;
        for (File file2 : (File[]) Objects.requireNonNull(file.listFiles(new MavenPlugin$$ExternalSyntheticLambda1(Pattern.compile(str3))))) {
            String name2 = file2.getName();
            String replaceAll = name2.replaceAll(".*\\.", "");
            replaceAll.hashCode();
            if (replaceAll.equals("java")) {
                arrayList3.add(file2);
            } else if (!replaceAll.equals("wsdl")) {
                arrayList.add(file2);
            } else {
                arrayList2.add(file2);
            }
            resource.addInclude(name2);
        }
        this.resources = Collections.singletonList(resource);
        if (this.buildSchemas) {
            String str4 = this.xmlConfigs;
            if (str4 == null || str4.isEmpty()) {
                list = Collections.emptyList();
            } else {
                list = (List) Stream.of(this.xmlConfigs.split(",")).flatMap(new MavenPlugin$$ExternalSyntheticLambda2(file)).collect(Collectors.toList());
            }
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            String str5 = this.classPath;
            if (str5 != null) {
                String[] split = str5.split(",");
                int length = split.length;
                int i = 0;
                while (i < length) {
                    File file3 = new File(split[i]);
                    arrayList4.add(file3);
                    try {
                        arrayList5.add(file3.toURI().toURL());
                        i++;
                    } catch (MalformedURLException e) {
                        throw new MojoFailureException("invalid classpath: " + file3, e);
                    }
                }
            }
            PassThroughResolver passThroughResolver = new PassThroughResolver(new URLClassLoader((URL[]) arrayList5.toArray(new URL[0])), MavenPluginResolver.getResolver(this.catalogLocation), new File(this.sourceDir).toURI(), this.baseSchemaLocation);
            Parameters parameters = new Parameters();
            parameters.setXsdFiles(files(arrayList));
            parameters.setWsdlFiles(files(arrayList2));
            parameters.setJavaFiles(files(arrayList3));
            parameters.setConfigFiles(files(list));
            parameters.setClasspath(files(arrayList4));
            parameters.setName(this.name);
            parameters.setSrcDir(new File(this.javaTargetDir));
            parameters.setClassesDir(new File(this.classTargetDir));
            parameters.setNojavac(this.sourceOnly);
            parameters.setVerbose(this.verbose);
            parameters.setEntityResolver(passThroughResolver);
            if (this.quiet && this.quite) {
                z = true;
            }
            parameters.setQuiet(z);
            parameters.setNoUpa(this.noUpa);
            parameters.setNoPvr(this.noPvr);
            parameters.setNoAnn(this.noAnn);
            parameters.setCopyAnn(this.copyAnn);
            parameters.setNoVDoc(this.noVDoc);
            String str6 = this.repackage;
            if (str6 != null && !str6.isEmpty()) {
                parameters.setRepackage("org.apache.xmlbeans.metadata:" + this.repackage);
            }
            List<String> list2 = this.mdefNamespaces;
            if (list2 != null && !list2.isEmpty()) {
                parameters.setMdefNamespaces(new HashSet(this.mdefNamespaces));
            }
            ArrayList arrayList6 = new ArrayList();
            parameters.setErrorListener(arrayList6);
            String str7 = this.partialMethods;
            if (str7 != null && !str7.isEmpty()) {
                parameters.setPartialMethods(SchemaCompiler.parsePartialMethods(this.partialMethods));
            }
            parameters.setDownload(this.download);
            parameters.setBaseDir(this.basedir);
            parameters.setCompiler(this.compiler);
            parameters.setMemoryInitialSize(this.memoryInitialSize);
            parameters.setMemoryMaximumSize(this.memoryMaximumSize);
            parameters.setOutputJar(this.outputJar);
            parameters.setDebug(this.debug);
            parameters.setExtensions(this.extensions);
            if (SchemaCompiler.compile(parameters)) {
                Resource resource2 = new Resource();
                resource2.setDirectory(this.classTargetDir);
                this.project.addResource(resource2);
                this.project.addCompileSourceRoot(this.javaTargetDir);
                return;
            }
            throw new MojoFailureException("Schema compilation failed!\n" + ((String) arrayList6.stream().map(new MavenPlugin$$ExternalSyntheticLambda3()).collect(Collectors.joining("\n"))));
        }
    }

    static /* synthetic */ boolean lambda$execute$0(Pattern pattern, File file, String str) {
        return !str.endsWith(".xsdconfig") && pattern.matcher(str).matches();
    }

    private static File[] files(List<File> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (File[]) list.toArray(new File[0]);
    }

    private static class PassThroughResolver implements EntityResolver {
        private final String baseSchemaLocation;
        private final ClassLoader cl;
        private final EntityResolver delegate;
        private final URI sourceDir;

        public PassThroughResolver(ClassLoader classLoader, EntityResolver entityResolver, URI uri, String str) {
            this.cl = classLoader;
            this.delegate = entityResolver;
            this.sourceDir = uri;
            this.baseSchemaLocation = str + PackagingURIHelper.FORWARD_SLASH_STRING;
        }

        public InputSource resolveEntity(String str, String str2) throws SAXException, IOException {
            InputSource resolveEntity;
            EntityResolver entityResolver = this.delegate;
            if (entityResolver != null && (resolveEntity = entityResolver.resolveEntity(str, str2)) != null) {
                return resolveEntity;
            }
            System.out.println("Could not resolve publicId: " + str + ", systemId: " + str2 + " from catalog");
            try {
                String uri = this.sourceDir.relativize(new URI(str2)).toString();
                InputStream resourceAsStream = this.cl.getResourceAsStream(uri);
                if (resourceAsStream != null) {
                    System.out.println("found in classpath at: " + uri);
                    return new InputSource(resourceAsStream);
                }
                InputStream resourceAsStream2 = this.cl.getResourceAsStream(this.baseSchemaLocation + uri);
                if (resourceAsStream2 != null) {
                    System.out.println("found in classpath at: META-INF/" + uri);
                    return new InputSource(resourceAsStream2);
                }
                System.out.println("Not found in classpath, looking in current directory: " + str2);
                return new InputSource(str2);
            } catch (URISyntaxException e) {
                throw new IOException("Could not relativeize systemId", e);
            }
        }
    }
}
