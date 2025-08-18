package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument;

public class TestsDocumentImpl extends XmlComplexContentImpl implements TestsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "tests")};
    private static final long serialVersionUID = 1;

    public TestsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public TestsDocument.Tests getTests() {
        TestsDocument.Tests tests;
        synchronized (monitor()) {
            check_orphaned();
            tests = (TestsDocument.Tests) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (tests == null) {
                tests = null;
            }
        }
        return tests;
    }

    public void setTests(TestsDocument.Tests tests) {
        generatedSetterHelperImpl(tests, PROPERTY_QNAME[0], 0, 1);
    }

    public TestsDocument.Tests addNewTests() {
        TestsDocument.Tests tests;
        synchronized (monitor()) {
            check_orphaned();
            tests = (TestsDocument.Tests) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return tests;
    }

    public static class TestsImpl extends XmlComplexContentImpl implements TestsDocument.Tests {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "test")};
        private static final long serialVersionUID = 1;

        public TestsImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public List<TestCase> getTestList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda0(this), new TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda1(this), new TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda2(this), new TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda3(this), new TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda4(this));
            }
            return javaListXmlObject;
        }

        public TestCase[] getTestArray() {
            return (TestCase[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new TestCase[0]);
        }

        public TestCase getTestArray(int i) {
            TestCase testCase;
            synchronized (monitor()) {
                check_orphaned();
                testCase = (TestCase) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (testCase == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return testCase;
        }

        public int sizeOfTestArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        public void setTestArray(TestCase[] testCaseArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) testCaseArr, PROPERTY_QNAME[0]);
        }

        public void setTestArray(int i, TestCase testCase) {
            generatedSetterHelperImpl(testCase, PROPERTY_QNAME[0], i, 2);
        }

        public TestCase insertNewTest(int i) {
            TestCase testCase;
            synchronized (monitor()) {
                check_orphaned();
                testCase = (TestCase) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return testCase;
        }

        public TestCase addNewTest() {
            TestCase testCase;
            synchronized (monitor()) {
                check_orphaned();
                testCase = (TestCase) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return testCase;
        }

        public void removeTest(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }
    }
}
