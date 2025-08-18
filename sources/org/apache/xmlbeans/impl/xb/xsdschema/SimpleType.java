package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.AbstractDocumentFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface SimpleType extends Annotated {
    public static final AbstractDocumentFactory<SimpleType> Factory;
    public static final SchemaType type;

    ListDocument.List addNewList();

    RestrictionDocument.Restriction addNewRestriction();

    UnionDocument.Union addNewUnion();

    Object getFinal();

    ListDocument.List getList();

    String getName();

    RestrictionDocument.Restriction getRestriction();

    UnionDocument.Union getUnion();

    boolean isSetFinal();

    boolean isSetList();

    boolean isSetName();

    boolean isSetRestriction();

    boolean isSetUnion();

    void setFinal(Object obj);

    void setList(ListDocument.List list);

    void setName(String str);

    void setRestriction(RestrictionDocument.Restriction restriction);

    void setUnion(UnionDocument.Union union);

    void unsetFinal();

    void unsetList();

    void unsetName();

    void unsetRestriction();

    void unsetUnion();

    SimpleDerivationSet xgetFinal();

    XmlNCName xgetName();

    void xsetFinal(SimpleDerivationSet simpleDerivationSet);

    void xsetName(XmlNCName xmlNCName);

    static {
        AbstractDocumentFactory<SimpleType> abstractDocumentFactory = new AbstractDocumentFactory<>(TypeSystemHolder.typeSystem, "simpletype0707type");
        Factory = abstractDocumentFactory;
        type = abstractDocumentFactory.getType();
    }
}
