package org.apache.xmlbeans.impl.tool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public abstract class SchemaImportResolver {

    public interface SchemaResource {
        String getNamespace();

        SchemaDocument.Schema getSchema();

        String getSchemaLocation();
    }

    public abstract SchemaResource lookupResource(String str, String str2);

    public abstract void reportActualNamespace(SchemaResource schemaResource, String str);

    /* access modifiers changed from: protected */
    public final void resolveImports(SchemaResource[] schemaResourceArr) {
        SchemaResource schemaResource;
        String str;
        LinkedList linkedList = new LinkedList(Arrays.asList(schemaResourceArr));
        LinkedList linkedList2 = new LinkedList();
        HashSet hashSet = new HashSet();
        while (true) {
            if (!linkedList.isEmpty()) {
                schemaResource = (SchemaResource) linkedList.removeFirst();
            } else if (!linkedList2.isEmpty()) {
                SchemaLocator schemaLocator = (SchemaLocator) linkedList2.removeFirst();
                schemaResource = lookupResource(schemaLocator.namespace, schemaLocator.schemaLocation);
                if (schemaResource == null) {
                }
            } else {
                return;
            }
            if (!hashSet.contains(schemaResource)) {
                hashSet.add(schemaResource);
                SchemaDocument.Schema schema = schemaResource.getSchema();
                if (schema != null) {
                    String targetNamespace = schema.getTargetNamespace();
                    if (targetNamespace == null) {
                        targetNamespace = "";
                    }
                    String namespace = schemaResource.getNamespace();
                    if (namespace == null || !targetNamespace.equals(namespace)) {
                        reportActualNamespace(schemaResource, targetNamespace);
                    }
                    ImportDocument.Import[] importArray = schema.getImportArray();
                    for (int i = 0; i < importArray.length; i++) {
                        if (importArray[i].getNamespace() == null) {
                            str = "";
                        } else {
                            str = importArray[i].getNamespace();
                        }
                        linkedList2.add(new SchemaLocator(str, importArray[i].getSchemaLocation()));
                    }
                    IncludeDocument.Include[] includeArray = schema.getIncludeArray();
                    for (IncludeDocument.Include schemaLocation : includeArray) {
                        linkedList2.add(new SchemaLocator((String) null, schemaLocation.getSchemaLocation()));
                    }
                }
            }
        }
    }

    private static class SchemaLocator {
        public final String namespace;
        public final String schemaLocation;

        public SchemaLocator(String str, String str2) {
            this.namespace = str;
            this.schemaLocation = str2;
        }
    }
}
