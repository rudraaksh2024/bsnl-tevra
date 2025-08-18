package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument;

public class RestrictionDocumentImpl extends XmlComplexContentImpl implements RestrictionDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "restriction")};
    private static final long serialVersionUID = 1;

    public RestrictionDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public RestrictionDocument.Restriction getRestriction() {
        RestrictionDocument.Restriction restriction;
        synchronized (monitor()) {
            check_orphaned();
            restriction = (RestrictionDocument.Restriction) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (restriction == null) {
                restriction = null;
            }
        }
        return restriction;
    }

    public void setRestriction(RestrictionDocument.Restriction restriction) {
        generatedSetterHelperImpl(restriction, PROPERTY_QNAME[0], 0, 1);
    }

    public RestrictionDocument.Restriction addNewRestriction() {
        RestrictionDocument.Restriction restriction;
        synchronized (monitor()) {
            check_orphaned();
            restriction = (RestrictionDocument.Restriction) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return restriction;
    }

    public static class RestrictionImpl extends AnnotatedImpl implements RestrictionDocument.Restriction {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("http://www.w3.org/2001/XMLSchema", "minExclusive"), new QName("http://www.w3.org/2001/XMLSchema", "minInclusive"), new QName("http://www.w3.org/2001/XMLSchema", "maxExclusive"), new QName("http://www.w3.org/2001/XMLSchema", "maxInclusive"), new QName("http://www.w3.org/2001/XMLSchema", "totalDigits"), new QName("http://www.w3.org/2001/XMLSchema", "fractionDigits"), new QName("http://www.w3.org/2001/XMLSchema", "length"), new QName("http://www.w3.org/2001/XMLSchema", "minLength"), new QName("http://www.w3.org/2001/XMLSchema", "maxLength"), new QName("http://www.w3.org/2001/XMLSchema", "enumeration"), new QName("http://www.w3.org/2001/XMLSchema", "whiteSpace"), new QName("http://www.w3.org/2001/XMLSchema", "pattern"), new QName("", "base")};
        private static final long serialVersionUID = 1;

        public RestrictionImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public LocalSimpleType getSimpleType() {
            LocalSimpleType localSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                localSimpleType = (LocalSimpleType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                if (localSimpleType == null) {
                    localSimpleType = null;
                }
            }
            return localSimpleType;
        }

        public boolean isSetSimpleType() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = false;
                if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                    z = true;
                }
            }
            return z;
        }

        public void setSimpleType(LocalSimpleType localSimpleType) {
            generatedSetterHelperImpl(localSimpleType, PROPERTY_QNAME[0], 0, 1);
        }

        public LocalSimpleType addNewSimpleType() {
            LocalSimpleType localSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                localSimpleType = (LocalSimpleType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return localSimpleType;
        }

        public void unsetSimpleType() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], 0);
            }
        }

        public List<Facet> getMinExclusiveList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda50(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda51(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda52(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda53(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda54(this));
            }
            return javaListXmlObject;
        }

        public Facet[] getMinExclusiveArray() {
            return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new Facet[0]);
        }

        public Facet getMinExclusiveArray(int i) {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (facet == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return facet;
        }

        public int sizeOfMinExclusiveArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        public void setMinExclusiveArray(Facet[] facetArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) facetArr, PROPERTY_QNAME[1]);
        }

        public void setMinExclusiveArray(int i, Facet facet) {
            generatedSetterHelperImpl(facet, PROPERTY_QNAME[1], i, 2);
        }

        public Facet insertNewMinExclusive(int i) {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return facet;
        }

        public Facet addNewMinExclusive() {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return facet;
        }

        public void removeMinExclusive(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        public List<Facet> getMinInclusiveList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda55(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda56(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda57(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda58(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda59(this));
            }
            return javaListXmlObject;
        }

        public Facet[] getMinInclusiveArray() {
            return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new Facet[0]);
        }

        public Facet getMinInclusiveArray(int i) {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().find_element_user(PROPERTY_QNAME[2], i);
                if (facet == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return facet;
        }

        public int sizeOfMinInclusiveArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
            }
            return count_elements;
        }

        public void setMinInclusiveArray(Facet[] facetArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) facetArr, PROPERTY_QNAME[2]);
        }

        public void setMinInclusiveArray(int i, Facet facet) {
            generatedSetterHelperImpl(facet, PROPERTY_QNAME[2], i, 2);
        }

        public Facet insertNewMinInclusive(int i) {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            }
            return facet;
        }

        public Facet addNewMinInclusive() {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            return facet;
        }

        public void removeMinInclusive(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[2], i);
            }
        }

        public List<Facet> getMaxExclusiveList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda6(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda7(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda8(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda9(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda10(this));
            }
            return javaListXmlObject;
        }

        public Facet[] getMaxExclusiveArray() {
            return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new Facet[0]);
        }

        public Facet getMaxExclusiveArray(int i) {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().find_element_user(PROPERTY_QNAME[3], i);
                if (facet == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return facet;
        }

        public int sizeOfMaxExclusiveArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
            }
            return count_elements;
        }

        public void setMaxExclusiveArray(Facet[] facetArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) facetArr, PROPERTY_QNAME[3]);
        }

        public void setMaxExclusiveArray(int i, Facet facet) {
            generatedSetterHelperImpl(facet, PROPERTY_QNAME[3], i, 2);
        }

        public Facet insertNewMaxExclusive(int i) {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            }
            return facet;
        }

        public Facet addNewMaxExclusive() {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            return facet;
        }

        public void removeMaxExclusive(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[3], i);
            }
        }

        public List<Facet> getMaxInclusiveList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda39(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda40(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda41(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda42(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda43(this));
            }
            return javaListXmlObject;
        }

        public Facet[] getMaxInclusiveArray() {
            return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new Facet[0]);
        }

        public Facet getMaxInclusiveArray(int i) {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().find_element_user(PROPERTY_QNAME[4], i);
                if (facet == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return facet;
        }

        public int sizeOfMaxInclusiveArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
            }
            return count_elements;
        }

        public void setMaxInclusiveArray(Facet[] facetArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) facetArr, PROPERTY_QNAME[4]);
        }

        public void setMaxInclusiveArray(int i, Facet facet) {
            generatedSetterHelperImpl(facet, PROPERTY_QNAME[4], i, 2);
        }

        public Facet insertNewMaxInclusive(int i) {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().insert_element_user(PROPERTY_QNAME[4], i);
            }
            return facet;
        }

        public Facet addNewMaxInclusive() {
            Facet facet;
            synchronized (monitor()) {
                check_orphaned();
                facet = (Facet) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            return facet;
        }

        public void removeMaxInclusive(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[4], i);
            }
        }

        public List<TotalDigitsDocument.TotalDigits> getTotalDigitsList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda34(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda35(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda36(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda37(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda38(this));
            }
            return javaListXmlObject;
        }

        public TotalDigitsDocument.TotalDigits[] getTotalDigitsArray() {
            return (TotalDigitsDocument.TotalDigits[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new TotalDigitsDocument.TotalDigits[0]);
        }

        public TotalDigitsDocument.TotalDigits getTotalDigitsArray(int i) {
            TotalDigitsDocument.TotalDigits totalDigits;
            synchronized (monitor()) {
                check_orphaned();
                totalDigits = (TotalDigitsDocument.TotalDigits) get_store().find_element_user(PROPERTY_QNAME[5], i);
                if (totalDigits == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return totalDigits;
        }

        public int sizeOfTotalDigitsArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
            }
            return count_elements;
        }

        public void setTotalDigitsArray(TotalDigitsDocument.TotalDigits[] totalDigitsArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) totalDigitsArr, PROPERTY_QNAME[5]);
        }

        public void setTotalDigitsArray(int i, TotalDigitsDocument.TotalDigits totalDigits) {
            generatedSetterHelperImpl(totalDigits, PROPERTY_QNAME[5], i, 2);
        }

        public TotalDigitsDocument.TotalDigits insertNewTotalDigits(int i) {
            TotalDigitsDocument.TotalDigits totalDigits;
            synchronized (monitor()) {
                check_orphaned();
                totalDigits = (TotalDigitsDocument.TotalDigits) get_store().insert_element_user(PROPERTY_QNAME[5], i);
            }
            return totalDigits;
        }

        public TotalDigitsDocument.TotalDigits addNewTotalDigits() {
            TotalDigitsDocument.TotalDigits totalDigits;
            synchronized (monitor()) {
                check_orphaned();
                totalDigits = (TotalDigitsDocument.TotalDigits) get_store().add_element_user(PROPERTY_QNAME[5]);
            }
            return totalDigits;
        }

        public void removeTotalDigits(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[5], i);
            }
        }

        public List<NumFacet> getFractionDigitsList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda12(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda13(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda14(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda15(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda16(this));
            }
            return javaListXmlObject;
        }

        public NumFacet[] getFractionDigitsArray() {
            return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new NumFacet[0]);
        }

        public NumFacet getFractionDigitsArray(int i) {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[6], i);
                if (numFacet == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return numFacet;
        }

        public int sizeOfFractionDigitsArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
            }
            return count_elements;
        }

        public void setFractionDigitsArray(NumFacet[] numFacetArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) numFacetArr, PROPERTY_QNAME[6]);
        }

        public void setFractionDigitsArray(int i, NumFacet numFacet) {
            generatedSetterHelperImpl(numFacet, PROPERTY_QNAME[6], i, 2);
        }

        public NumFacet insertNewFractionDigits(int i) {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[6], i);
            }
            return numFacet;
        }

        public NumFacet addNewFractionDigits() {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[6]);
            }
            return numFacet;
        }

        public void removeFractionDigits(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[6], i);
            }
        }

        public List<NumFacet> getLengthList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda17(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda18(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda19(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda20(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda21(this));
            }
            return javaListXmlObject;
        }

        public NumFacet[] getLengthArray() {
            return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new NumFacet[0]);
        }

        public NumFacet getLengthArray(int i) {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[7], i);
                if (numFacet == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return numFacet;
        }

        public int sizeOfLengthArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
            }
            return count_elements;
        }

        public void setLengthArray(NumFacet[] numFacetArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) numFacetArr, PROPERTY_QNAME[7]);
        }

        public void setLengthArray(int i, NumFacet numFacet) {
            generatedSetterHelperImpl(numFacet, PROPERTY_QNAME[7], i, 2);
        }

        public NumFacet insertNewLength(int i) {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[7], i);
            }
            return numFacet;
        }

        public NumFacet addNewLength() {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[7]);
            }
            return numFacet;
        }

        public void removeLength(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[7], i);
            }
        }

        public List<NumFacet> getMinLengthList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda28(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda29(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda30(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda31(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda32(this));
            }
            return javaListXmlObject;
        }

        public NumFacet[] getMinLengthArray() {
            return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new NumFacet[0]);
        }

        public NumFacet getMinLengthArray(int i) {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[8], i);
                if (numFacet == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return numFacet;
        }

        public int sizeOfMinLengthArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
            }
            return count_elements;
        }

        public void setMinLengthArray(NumFacet[] numFacetArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) numFacetArr, PROPERTY_QNAME[8]);
        }

        public void setMinLengthArray(int i, NumFacet numFacet) {
            generatedSetterHelperImpl(numFacet, PROPERTY_QNAME[8], i, 2);
        }

        public NumFacet insertNewMinLength(int i) {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[8], i);
            }
            return numFacet;
        }

        public NumFacet addNewMinLength() {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[8]);
            }
            return numFacet;
        }

        public void removeMinLength(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[8], i);
            }
        }

        public List<NumFacet> getMaxLengthList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda45(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda46(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda47(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda48(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda49(this));
            }
            return javaListXmlObject;
        }

        public NumFacet[] getMaxLengthArray() {
            return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new NumFacet[0]);
        }

        public NumFacet getMaxLengthArray(int i) {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[9], i);
                if (numFacet == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return numFacet;
        }

        public int sizeOfMaxLengthArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
            }
            return count_elements;
        }

        public void setMaxLengthArray(NumFacet[] numFacetArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) numFacetArr, PROPERTY_QNAME[9]);
        }

        public void setMaxLengthArray(int i, NumFacet numFacet) {
            generatedSetterHelperImpl(numFacet, PROPERTY_QNAME[9], i, 2);
        }

        public NumFacet insertNewMaxLength(int i) {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[9], i);
            }
            return numFacet;
        }

        public NumFacet addNewMaxLength() {
            NumFacet numFacet;
            synchronized (monitor()) {
                check_orphaned();
                numFacet = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[9]);
            }
            return numFacet;
        }

        public void removeMaxLength(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[9], i);
            }
        }

        public List<NoFixedFacet> getEnumerationList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda1(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda2(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda3(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda4(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda5(this));
            }
            return javaListXmlObject;
        }

        public NoFixedFacet[] getEnumerationArray() {
            return (NoFixedFacet[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new NoFixedFacet[0]);
        }

        public NoFixedFacet getEnumerationArray(int i) {
            NoFixedFacet noFixedFacet;
            synchronized (monitor()) {
                check_orphaned();
                noFixedFacet = (NoFixedFacet) get_store().find_element_user(PROPERTY_QNAME[10], i);
                if (noFixedFacet == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return noFixedFacet;
        }

        public int sizeOfEnumerationArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
            }
            return count_elements;
        }

        public void setEnumerationArray(NoFixedFacet[] noFixedFacetArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) noFixedFacetArr, PROPERTY_QNAME[10]);
        }

        public void setEnumerationArray(int i, NoFixedFacet noFixedFacet) {
            generatedSetterHelperImpl(noFixedFacet, PROPERTY_QNAME[10], i, 2);
        }

        public NoFixedFacet insertNewEnumeration(int i) {
            NoFixedFacet noFixedFacet;
            synchronized (monitor()) {
                check_orphaned();
                noFixedFacet = (NoFixedFacet) get_store().insert_element_user(PROPERTY_QNAME[10], i);
            }
            return noFixedFacet;
        }

        public NoFixedFacet addNewEnumeration() {
            NoFixedFacet noFixedFacet;
            synchronized (monitor()) {
                check_orphaned();
                noFixedFacet = (NoFixedFacet) get_store().add_element_user(PROPERTY_QNAME[10]);
            }
            return noFixedFacet;
        }

        public void removeEnumeration(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[10], i);
            }
        }

        public List<WhiteSpaceDocument.WhiteSpace> getWhiteSpaceList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda0(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda11(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda22(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda33(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda44(this));
            }
            return javaListXmlObject;
        }

        public WhiteSpaceDocument.WhiteSpace[] getWhiteSpaceArray() {
            return (WhiteSpaceDocument.WhiteSpace[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new WhiteSpaceDocument.WhiteSpace[0]);
        }

        public WhiteSpaceDocument.WhiteSpace getWhiteSpaceArray(int i) {
            WhiteSpaceDocument.WhiteSpace whiteSpace;
            synchronized (monitor()) {
                check_orphaned();
                whiteSpace = (WhiteSpaceDocument.WhiteSpace) get_store().find_element_user(PROPERTY_QNAME[11], i);
                if (whiteSpace == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return whiteSpace;
        }

        public int sizeOfWhiteSpaceArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
            }
            return count_elements;
        }

        public void setWhiteSpaceArray(WhiteSpaceDocument.WhiteSpace[] whiteSpaceArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) whiteSpaceArr, PROPERTY_QNAME[11]);
        }

        public void setWhiteSpaceArray(int i, WhiteSpaceDocument.WhiteSpace whiteSpace) {
            generatedSetterHelperImpl(whiteSpace, PROPERTY_QNAME[11], i, 2);
        }

        public WhiteSpaceDocument.WhiteSpace insertNewWhiteSpace(int i) {
            WhiteSpaceDocument.WhiteSpace whiteSpace;
            synchronized (monitor()) {
                check_orphaned();
                whiteSpace = (WhiteSpaceDocument.WhiteSpace) get_store().insert_element_user(PROPERTY_QNAME[11], i);
            }
            return whiteSpace;
        }

        public WhiteSpaceDocument.WhiteSpace addNewWhiteSpace() {
            WhiteSpaceDocument.WhiteSpace whiteSpace;
            synchronized (monitor()) {
                check_orphaned();
                whiteSpace = (WhiteSpaceDocument.WhiteSpace) get_store().add_element_user(PROPERTY_QNAME[11]);
            }
            return whiteSpace;
        }

        public void removeWhiteSpace(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[11], i);
            }
        }

        public List<PatternDocument.Pattern> getPatternList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda23(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda24(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda25(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda26(this), new RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda27(this));
            }
            return javaListXmlObject;
        }

        public PatternDocument.Pattern[] getPatternArray() {
            return (PatternDocument.Pattern[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new PatternDocument.Pattern[0]);
        }

        public PatternDocument.Pattern getPatternArray(int i) {
            PatternDocument.Pattern pattern;
            synchronized (monitor()) {
                check_orphaned();
                pattern = (PatternDocument.Pattern) get_store().find_element_user(PROPERTY_QNAME[12], i);
                if (pattern == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return pattern;
        }

        public int sizeOfPatternArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
            }
            return count_elements;
        }

        public void setPatternArray(PatternDocument.Pattern[] patternArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) patternArr, PROPERTY_QNAME[12]);
        }

        public void setPatternArray(int i, PatternDocument.Pattern pattern) {
            generatedSetterHelperImpl(pattern, PROPERTY_QNAME[12], i, 2);
        }

        public PatternDocument.Pattern insertNewPattern(int i) {
            PatternDocument.Pattern pattern;
            synchronized (monitor()) {
                check_orphaned();
                pattern = (PatternDocument.Pattern) get_store().insert_element_user(PROPERTY_QNAME[12], i);
            }
            return pattern;
        }

        public PatternDocument.Pattern addNewPattern() {
            PatternDocument.Pattern pattern;
            synchronized (monitor()) {
                check_orphaned();
                pattern = (PatternDocument.Pattern) get_store().add_element_user(PROPERTY_QNAME[12]);
            }
            return pattern;
        }

        public void removePattern(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[12], i);
            }
        }

        public QName getBase() {
            QName qName;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
                if (simpleValue == null) {
                    qName = null;
                } else {
                    qName = simpleValue.getQNameValue();
                }
            }
            return qName;
        }

        public XmlQName xgetBase() {
            XmlQName xmlQName;
            synchronized (monitor()) {
                check_orphaned();
                xmlQName = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            }
            return xmlQName;
        }

        public boolean isSetBase() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setBase(javax.xml.namespace.QName r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 13
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.setQNameValue(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl.RestrictionImpl.setBase(javax.xml.namespace.QName):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetBase(org.apache.xmlbeans.XmlQName r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 13
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.XmlQName r1 = (org.apache.xmlbeans.XmlQName) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.XmlQName r1 = (org.apache.xmlbeans.XmlQName) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.set(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl.RestrictionImpl.xsetBase(org.apache.xmlbeans.XmlQName):void");
        }

        public void unsetBase() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[13]);
            }
        }
    }
}
