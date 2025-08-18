package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface RealGroup extends Group {
    public static final DocumentFactory<RealGroup> Factory;
    public static final SchemaType type;

    All addNewAll();

    ExplicitGroup addNewChoice();

    ExplicitGroup addNewSequence();

    All getAllArray(int i);

    All[] getAllArray();

    List<All> getAllList();

    ExplicitGroup getChoiceArray(int i);

    ExplicitGroup[] getChoiceArray();

    List<ExplicitGroup> getChoiceList();

    ExplicitGroup getSequenceArray(int i);

    ExplicitGroup[] getSequenceArray();

    List<ExplicitGroup> getSequenceList();

    All insertNewAll(int i);

    ExplicitGroup insertNewChoice(int i);

    ExplicitGroup insertNewSequence(int i);

    void removeAll(int i);

    void removeChoice(int i);

    void removeSequence(int i);

    void setAllArray(int i, All all);

    void setAllArray(All[] allArr);

    void setChoiceArray(int i, ExplicitGroup explicitGroup);

    void setChoiceArray(ExplicitGroup[] explicitGroupArr);

    void setSequenceArray(int i, ExplicitGroup explicitGroup);

    void setSequenceArray(ExplicitGroup[] explicitGroupArr);

    int sizeOfAllArray();

    int sizeOfChoiceArray();

    int sizeOfSequenceArray();

    static {
        DocumentFactory<RealGroup> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "realgroup1f64type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
