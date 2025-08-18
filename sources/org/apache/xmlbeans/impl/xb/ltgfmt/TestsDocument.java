package org.apache.xmlbeans.impl.xb.ltgfmt;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLTOOLS.TypeSystemHolder;

public interface TestsDocument extends XmlObject {
    public static final DocumentFactory<TestsDocument> Factory;
    public static final SchemaType type;

    Tests addNewTests();

    Tests getTests();

    void setTests(Tests tests);

    static {
        DocumentFactory<TestsDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "tests5621doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Tests extends XmlObject {
        public static final ElementFactory<Tests> Factory;
        public static final SchemaType type;

        TestCase addNewTest();

        TestCase getTestArray(int i);

        TestCase[] getTestArray();

        List<TestCase> getTestList();

        TestCase insertNewTest(int i);

        void removeTest(int i);

        void setTestArray(int i, TestCase testCase);

        void setTestArray(TestCase[] testCaseArr);

        int sizeOfTestArray();

        static {
            ElementFactory<Tests> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "tests9d6eelemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
