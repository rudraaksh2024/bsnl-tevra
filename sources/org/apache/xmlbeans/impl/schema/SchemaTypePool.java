package org.apache.xmlbeans.impl.schema;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoaderException;
import org.apache.xmlbeans.impl.common.NameUtil;

class SchemaTypePool {
    private final Map<SchemaComponent, String> _componentsToHandles = new LinkedHashMap();
    private final Map<String, SchemaComponent.Ref> _handlesToRefs = new LinkedHashMap();
    private boolean _started;
    private final SchemaTypeSystemImpl typeSystem;

    SchemaTypePool(SchemaTypeSystemImpl schemaTypeSystemImpl) {
        this.typeSystem = schemaTypeSystemImpl;
    }

    private String addUniqueHandle(SchemaComponent schemaComponent, String str) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        int i = 2;
        String str2 = lowerCase;
        while (this._handlesToRefs.containsKey(str2)) {
            str2 = lowerCase + i;
            i++;
        }
        this._handlesToRefs.put(str2, schemaComponent.getComponentRef());
        this._componentsToHandles.put(schemaComponent, str2);
        return str2;
    }

    /* access modifiers changed from: package-private */
    public String handleForComponent(SchemaComponent schemaComponent) {
        if (schemaComponent == null) {
            return null;
        }
        if (schemaComponent.getTypeSystem() != this.typeSystem) {
            throw new IllegalArgumentException("Cannot supply handles for types from another type system");
        } else if (schemaComponent instanceof SchemaType) {
            return handleForType((SchemaType) schemaComponent);
        } else {
            if (schemaComponent instanceof SchemaGlobalElement) {
                return handleForElement((SchemaGlobalElement) schemaComponent);
            }
            if (schemaComponent instanceof SchemaGlobalAttribute) {
                return handleForAttribute((SchemaGlobalAttribute) schemaComponent);
            }
            if (schemaComponent instanceof SchemaModelGroup) {
                return handleForModelGroup((SchemaModelGroup) schemaComponent);
            }
            if (schemaComponent instanceof SchemaAttributeGroup) {
                return handleForAttributeGroup((SchemaAttributeGroup) schemaComponent);
            }
            if (schemaComponent instanceof SchemaIdentityConstraint) {
                return handleForIdentityConstraint((SchemaIdentityConstraint) schemaComponent);
            }
            throw new IllegalStateException("Component type cannot have a handle");
        }
    }

    /* access modifiers changed from: package-private */
    public String handleForElement(SchemaGlobalElement schemaGlobalElement) {
        if (schemaGlobalElement == null) {
            return null;
        }
        if (schemaGlobalElement.getTypeSystem() == this.typeSystem) {
            String str = this._componentsToHandles.get(schemaGlobalElement);
            return str == null ? addUniqueHandle(schemaGlobalElement, NameUtil.upperCamelCase(schemaGlobalElement.getName().getLocalPart()) + "Element") : str;
        }
        throw new IllegalArgumentException("Cannot supply handles for types from another type system");
    }

    /* access modifiers changed from: package-private */
    public String handleForAttribute(SchemaGlobalAttribute schemaGlobalAttribute) {
        if (schemaGlobalAttribute == null) {
            return null;
        }
        if (schemaGlobalAttribute.getTypeSystem() == this.typeSystem) {
            String str = this._componentsToHandles.get(schemaGlobalAttribute);
            return str == null ? addUniqueHandle(schemaGlobalAttribute, NameUtil.upperCamelCase(schemaGlobalAttribute.getName().getLocalPart()) + "Attribute") : str;
        }
        throw new IllegalArgumentException("Cannot supply handles for types from another type system");
    }

    /* access modifiers changed from: package-private */
    public String handleForModelGroup(SchemaModelGroup schemaModelGroup) {
        if (schemaModelGroup == null) {
            return null;
        }
        if (schemaModelGroup.getTypeSystem() == this.typeSystem) {
            String str = this._componentsToHandles.get(schemaModelGroup);
            return str == null ? addUniqueHandle(schemaModelGroup, NameUtil.upperCamelCase(schemaModelGroup.getName().getLocalPart()) + "ModelGroup") : str;
        }
        throw new IllegalArgumentException("Cannot supply handles for types from another type system");
    }

    /* access modifiers changed from: package-private */
    public String handleForAttributeGroup(SchemaAttributeGroup schemaAttributeGroup) {
        if (schemaAttributeGroup == null) {
            return null;
        }
        if (schemaAttributeGroup.getTypeSystem() == this.typeSystem) {
            String str = this._componentsToHandles.get(schemaAttributeGroup);
            return str == null ? addUniqueHandle(schemaAttributeGroup, NameUtil.upperCamelCase(schemaAttributeGroup.getName().getLocalPart()) + "AttributeGroup") : str;
        }
        throw new IllegalArgumentException("Cannot supply handles for types from another type system");
    }

    /* access modifiers changed from: package-private */
    public String handleForIdentityConstraint(SchemaIdentityConstraint schemaIdentityConstraint) {
        if (schemaIdentityConstraint == null) {
            return null;
        }
        if (schemaIdentityConstraint.getTypeSystem() == this.typeSystem) {
            String str = this._componentsToHandles.get(schemaIdentityConstraint);
            return str == null ? addUniqueHandle(schemaIdentityConstraint, NameUtil.upperCamelCase(schemaIdentityConstraint.getName().getLocalPart()) + "IdentityConstraint") : str;
        }
        throw new IllegalArgumentException("Cannot supply handles for types from another type system");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String handleForType(org.apache.xmlbeans.SchemaType r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x0004
            r5 = 0
            return r5
        L_0x0004:
            org.apache.xmlbeans.SchemaTypeSystem r0 = r6.getTypeSystem()
            org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl r1 = r5.typeSystem
            if (r0 != r1) goto L_0x00ae
            java.util.Map<org.apache.xmlbeans.SchemaComponent, java.lang.String> r0 = r5._componentsToHandles
            java.lang.Object r0 = r0.get(r6)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x00ad
            javax.xml.namespace.QName r0 = r6.getName()
            if (r0 != 0) goto L_0x0054
            boolean r1 = r6.isDocumentType()
            if (r1 == 0) goto L_0x0029
            javax.xml.namespace.QName r0 = r6.getDocumentElementName()
            java.lang.String r1 = "Doc"
            goto L_0x0056
        L_0x0029:
            boolean r1 = r6.isAttributeType()
            if (r1 == 0) goto L_0x0036
            javax.xml.namespace.QName r0 = r6.getAttributeTypeAttributeName()
            java.lang.String r1 = "AttrType"
            goto L_0x0056
        L_0x0036:
            org.apache.xmlbeans.SchemaField r1 = r6.getContainerField()
            if (r1 == 0) goto L_0x0054
            org.apache.xmlbeans.SchemaField r0 = r6.getContainerField()
            javax.xml.namespace.QName r0 = r0.getName()
            org.apache.xmlbeans.SchemaField r1 = r6.getContainerField()
            boolean r1 = r1.isAttribute()
            if (r1 == 0) goto L_0x0051
            java.lang.String r1 = "Attr"
            goto L_0x0056
        L_0x0051:
            java.lang.String r1 = "Elem"
            goto L_0x0056
        L_0x0054:
            java.lang.String r1 = ""
        L_0x0056:
            java.lang.String r2 = r6.toString()
            int r2 = r2.hashCode()
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 | r3
            java.lang.String r2 = java.lang.Integer.toHexString(r2)
            r3 = 4
            java.lang.String r2 = r2.substring(r3)
            java.util.Locale r3 = java.util.Locale.ROOT
            java.lang.String r2 = r2.toUpperCase(r3)
            java.lang.String r3 = "Type"
            if (r0 != 0) goto L_0x0088
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Anon"
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            goto L_0x00a9
        L_0x0088:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = r0.getLocalPart()
            java.lang.String r0 = org.apache.xmlbeans.impl.common.NameUtil.upperCamelCase(r0)
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
        L_0x00a9:
            java.lang.String r0 = r5.addUniqueHandle(r6, r0)
        L_0x00ad:
            return r0
        L_0x00ae:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Cannot supply handles for types from another type system"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypePool.handleForType(org.apache.xmlbeans.SchemaType):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public SchemaComponent.Ref refForHandle(String str) {
        if (str == null) {
            return null;
        }
        return this._handlesToRefs.get(str);
    }

    /* access modifiers changed from: package-private */
    public void startWriteMode() {
        this._started = true;
        this._componentsToHandles.clear();
        for (String next : this._handlesToRefs.keySet()) {
            this._componentsToHandles.put(this._handlesToRefs.get(next).getComponent(), next);
        }
    }

    /* access modifiers changed from: package-private */
    public void writeHandlePool(XsbReader xsbReader) {
        xsbReader.writeShort(this._componentsToHandles.size());
        this._componentsToHandles.forEach(new SchemaTypePool$$ExternalSyntheticLambda0(this, xsbReader));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$writeHandlePool$0$org-apache-xmlbeans-impl-schema-SchemaTypePool  reason: not valid java name */
    public /* synthetic */ void m2320lambda$writeHandlePool$0$orgapachexmlbeansimplschemaSchemaTypePool(XsbReader xsbReader, SchemaComponent schemaComponent, String str) {
        xsbReader.writeString(str);
        xsbReader.writeShort(fileTypeFromComponentType(schemaComponent.getComponentType()));
    }

    /* access modifiers changed from: package-private */
    public int fileTypeFromComponentType(int i) {
        if (i == 0) {
            return 2;
        }
        if (i == 1) {
            return 3;
        }
        if (i == 3) {
            return 4;
        }
        if (i == 4) {
            return 7;
        }
        if (i == 5) {
            return 8;
        }
        if (i == 6) {
            return 6;
        }
        throw new IllegalStateException("Unexpected component type");
    }

    /* access modifiers changed from: package-private */
    public void readHandlePool(XsbReader xsbReader) {
        Object obj;
        if (this._handlesToRefs.size() != 0 || this._started) {
            throw new IllegalStateException("Nonempty handle set before read");
        }
        int readShort = xsbReader.readShort();
        for (int i = 0; i < readShort; i++) {
            String readString = xsbReader.readString();
            int readShort2 = xsbReader.readShort();
            if (readShort2 == 2) {
                obj = new SchemaType.Ref(this.typeSystem, readString);
            } else if (readShort2 == 3) {
                obj = new SchemaGlobalElement.Ref(this.typeSystem, readString);
            } else if (readShort2 == 4) {
                obj = new SchemaGlobalAttribute.Ref(this.typeSystem, readString);
            } else if (readShort2 == 6) {
                obj = new SchemaModelGroup.Ref(this.typeSystem, readString);
            } else if (readShort2 == 7) {
                obj = new SchemaAttributeGroup.Ref(this.typeSystem, readString);
            } else if (readShort2 == 8) {
                obj = new SchemaIdentityConstraint.Ref(this.typeSystem, readString);
            } else {
                throw new SchemaTypeLoaderException("Schema index has an unrecognized entry of type " + readShort2, this.typeSystem.getName(), readString, 5);
            }
            this._handlesToRefs.put(readString, obj);
        }
    }
}
