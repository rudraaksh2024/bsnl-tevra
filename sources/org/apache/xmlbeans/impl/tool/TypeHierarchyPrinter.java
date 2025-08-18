package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public class TypeHierarchyPrinter {
    public static void printUsage() {
        System.out.println("Prints the inheritance hierarchy of types defined in a schema.\n");
        System.out.println("Usage: xsdtree [-noanon] [-nopvr] [-noupa] [-partial] [-license] schemafile.xsd*");
        System.out.println("    -noanon - Don't include anonymous types in the tree.");
        System.out.println("    -noupa - do not enforce the unique particle attribution rule");
        System.out.println("    -nopvr - do not enforce the particle valid (restriction) rule");
        System.out.println("    -partial - Print only part of the hierarchy.");
        System.out.println("    -license - prints license information");
        System.out.println("    schemafile.xsd - File containing the schema for which to print a tree.");
        System.out.println();
    }

    public static void main(String[] strArr) throws Exception {
        HashSet hashSet = new HashSet();
        hashSet.add("h");
        hashSet.add("help");
        hashSet.add("usage");
        hashSet.add("license");
        hashSet.add("version");
        hashSet.add("noanon");
        hashSet.add("noupr");
        hashSet.add("noupa");
        hashSet.add("partial");
        CommandLine commandLine = new CommandLine(strArr, hashSet, Collections.EMPTY_SET);
        if (commandLine.getOpt("h") == null && commandLine.getOpt("help") == null && commandLine.getOpt("usage") == null) {
            String[] badOpts = commandLine.getBadOpts();
            if (badOpts.length > 0) {
                for (int i = 0; i < badOpts.length; i++) {
                    System.out.println("Unrecognized option: " + badOpts[i]);
                }
                printUsage();
                System.exit(0);
            } else if (commandLine.getOpt("license") != null) {
                CommandLine.printLicense();
                System.exit(0);
            } else if (commandLine.getOpt("version") != null) {
                CommandLine.printVersion();
                System.exit(0);
            } else if (commandLine.args().length == 0) {
                printUsage();
            } else {
                boolean z = commandLine.getOpt("noanon") != null;
                boolean z2 = commandLine.getOpt("nopvr") != null;
                boolean z3 = commandLine.getOpt("noupa") != null;
                boolean z4 = commandLine.getOpt("partial") != null;
                File[] filesEndingWith = commandLine.filesEndingWith(".xsd");
                File[] filesEndingWith2 = commandLine.filesEndingWith(".jar");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < filesEndingWith.length; i2++) {
                    try {
                        arrayList.add(SchemaDocument.Factory.parse(filesEndingWith[i2], new XmlOptions().setLoadLineNumbers()));
                    } catch (Exception e) {
                        System.err.println(filesEndingWith[i2] + " not loadable: " + e);
                    }
                }
                XmlObject[] xmlObjectArr = (XmlObject[]) arrayList.toArray(new XmlObject[0]);
                ArrayList<Object> arrayList2 = new ArrayList<>();
                XmlOptions xmlOptions = new XmlOptions();
                xmlOptions.setErrorListener(arrayList2);
                xmlOptions.setCompileDownloadUrls();
                if (z2) {
                    xmlOptions.setCompileNoPvrRule();
                }
                if (z3) {
                    xmlOptions.setCompileNoUpaRule();
                }
                if (z4) {
                    xmlOptions.setCompilePartialTypesystem();
                }
                try {
                    SchemaTypeSystem compileXsd = XmlBeans.compileXsd(xmlObjectArr, (filesEndingWith2 == null || filesEndingWith2.length <= 0) ? null : XmlBeans.typeLoaderForResource(XmlBeans.resourceLoaderForPath(filesEndingWith2)), xmlOptions);
                    if (z4 && !arrayList2.isEmpty()) {
                        System.out.println("Schema invalid: partial schema type system recovered");
                        for (Object println : arrayList2) {
                            System.out.println(println);
                        }
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("http://www.w3.org/XML/1998/namespace", "xml");
                    hashMap.put("http://www.w3.org/2001/XMLSchema", "xs");
                    System.out.println("xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"");
                    HashMap hashMap2 = new HashMap();
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.addAll(Arrays.asList(compileXsd.documentTypes()));
                    arrayList3.addAll(Arrays.asList(compileXsd.attributeTypes()));
                    arrayList3.addAll(Arrays.asList(compileXsd.globalTypes()));
                    for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                        SchemaType schemaType = (SchemaType) arrayList3.get(i3);
                        if (!z) {
                            arrayList3.addAll(Arrays.asList(schemaType.getAnonymousTypes()));
                        }
                        if (!schemaType.isDocumentType() && !schemaType.isAttributeType() && schemaType != XmlObject.type) {
                            noteNamespace(hashMap, schemaType);
                            Collection collection = (Collection) hashMap2.get(schemaType.getBaseType());
                            if (collection == null) {
                                collection = new ArrayList();
                                hashMap2.put(schemaType.getBaseType(), collection);
                                if (schemaType.getBaseType().isBuiltinType()) {
                                    arrayList3.add(schemaType.getBaseType());
                                }
                            }
                            collection.add(schemaType);
                        }
                    }
                    ArrayList arrayList4 = new ArrayList();
                    arrayList4.add(XmlObject.type);
                    StringBuilder sb = new StringBuilder();
                    while (!arrayList4.isEmpty()) {
                        SchemaType schemaType2 = (SchemaType) arrayList4.remove(arrayList4.size() - 1);
                        if (schemaType2 == null) {
                            sb.setLength(Math.max(0, sb.length() - 2));
                        } else {
                            System.out.println(sb + "+-" + QNameHelper.readable(schemaType2, (Map) hashMap) + notes(schemaType2));
                            Collection collection2 = (Collection) hashMap2.get(schemaType2);
                            if (collection2 != null && collection2.size() > 0) {
                                sb.append((arrayList4.size() == 0 || arrayList4.get(arrayList4.size() - 1) == null) ? "  " : "| ");
                                arrayList4.add((Object) null);
                                arrayList4.addAll(collection2);
                            }
                        }
                    }
                } catch (XmlException e2) {
                    System.out.println("Schema invalid:".concat(z4 ? " couldn't recover from errors" : ""));
                    if (arrayList2.isEmpty()) {
                        System.out.println(e2.getMessage());
                        return;
                    }
                    for (Object println2 : arrayList2) {
                        System.out.println(println2);
                    }
                }
            }
        } else {
            printUsage();
            System.exit(0);
        }
    }

    private static String notes(SchemaType schemaType) {
        if (schemaType.isBuiltinType()) {
            return " (builtin)";
        }
        if (schemaType.isSimpleType()) {
            int simpleVariety = schemaType.getSimpleVariety();
            if (simpleVariety == 2) {
                return " (union)";
            }
            if (simpleVariety == 3) {
                return " (list)";
            }
            if (schemaType.getEnumerationValues() != null) {
                return " (enumeration)";
            }
            return "";
        }
        int contentType = schemaType.getContentType();
        if (contentType == 2) {
            return " (complex)";
        }
        if (contentType != 4) {
            return "";
        }
        return " (mixed)";
    }

    private static void noteNamespace(Map map, SchemaType schemaType) {
        String namespace = QNameHelper.namespace(schemaType);
        if (!namespace.equals("") && !map.containsKey(namespace)) {
            String suggestPrefix = QNameHelper.suggestPrefix(namespace);
            int i = 0;
            String str = suggestPrefix;
            while (map.containsValue(str)) {
                str = suggestPrefix + i;
                i++;
            }
            map.put(namespace, str);
            System.out.println(Sax2Dom.XMLNS_STRING + str + "=\"" + namespace + "\"");
        }
    }
}
