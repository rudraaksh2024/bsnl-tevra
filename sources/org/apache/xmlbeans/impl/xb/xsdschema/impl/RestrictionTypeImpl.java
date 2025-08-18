package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;

public class RestrictionTypeImpl extends AnnotatedImpl implements RestrictionType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "group"), new QName("http://www.w3.org/2001/XMLSchema", "all"), new QName("http://www.w3.org/2001/XMLSchema", "choice"), new QName("http://www.w3.org/2001/XMLSchema", "sequence"), new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("http://www.w3.org/2001/XMLSchema", "minExclusive"), new QName("http://www.w3.org/2001/XMLSchema", "minInclusive"), new QName("http://www.w3.org/2001/XMLSchema", "maxExclusive"), new QName("http://www.w3.org/2001/XMLSchema", "maxInclusive"), new QName("http://www.w3.org/2001/XMLSchema", "totalDigits"), new QName("http://www.w3.org/2001/XMLSchema", "fractionDigits"), new QName("http://www.w3.org/2001/XMLSchema", "length"), new QName("http://www.w3.org/2001/XMLSchema", "minLength"), new QName("http://www.w3.org/2001/XMLSchema", "maxLength"), new QName("http://www.w3.org/2001/XMLSchema", "enumeration"), new QName("http://www.w3.org/2001/XMLSchema", "whiteSpace"), new QName("http://www.w3.org/2001/XMLSchema", "pattern"), new QName("http://www.w3.org/2001/XMLSchema", "attribute"), new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup"), new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute"), new QName("", "base")};
    private static final long serialVersionUID = 1;

    public RestrictionTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public GroupRef getGroup() {
        GroupRef groupRef;
        synchronized (monitor()) {
            check_orphaned();
            groupRef = (GroupRef) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (groupRef == null) {
                groupRef = null;
            }
        }
        return groupRef;
    }

    public boolean isSetGroup() {
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

    public void setGroup(GroupRef groupRef) {
        generatedSetterHelperImpl(groupRef, PROPERTY_QNAME[0], 0, 1);
    }

    public GroupRef addNewGroup() {
        GroupRef groupRef;
        synchronized (monitor()) {
            check_orphaned();
            groupRef = (GroupRef) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return groupRef;
    }

    public void unsetGroup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public All getAll() {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            all = (All) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (all == null) {
                all = null;
            }
        }
        return all;
    }

    public boolean isSetAll() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setAll(All all) {
        generatedSetterHelperImpl(all, PROPERTY_QNAME[1], 0, 1);
    }

    public All addNewAll() {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            all = (All) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return all;
    }

    public void unsetAll() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public ExplicitGroup getChoice() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (explicitGroup == null) {
                explicitGroup = null;
            }
        }
        return explicitGroup;
    }

    public boolean isSetChoice() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setChoice(ExplicitGroup explicitGroup) {
        generatedSetterHelperImpl(explicitGroup, PROPERTY_QNAME[2], 0, 1);
    }

    public ExplicitGroup addNewChoice() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return explicitGroup;
    }

    public void unsetChoice() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public ExplicitGroup getSequence() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (explicitGroup == null) {
                explicitGroup = null;
            }
        }
        return explicitGroup;
    }

    public boolean isSetSequence() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setSequence(ExplicitGroup explicitGroup) {
        generatedSetterHelperImpl(explicitGroup, PROPERTY_QNAME[3], 0, 1);
    }

    public ExplicitGroup addNewSequence() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return explicitGroup;
    }

    public void unsetSequence() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public LocalSimpleType getSimpleType() {
        LocalSimpleType localSimpleType;
        synchronized (monitor()) {
            check_orphaned();
            localSimpleType = (LocalSimpleType) get_store().find_element_user(PROPERTY_QNAME[4], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setSimpleType(LocalSimpleType localSimpleType) {
        generatedSetterHelperImpl(localSimpleType, PROPERTY_QNAME[4], 0, 1);
    }

    public LocalSimpleType addNewSimpleType() {
        LocalSimpleType localSimpleType;
        synchronized (monitor()) {
            check_orphaned();
            localSimpleType = (LocalSimpleType) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return localSimpleType;
    }

    public void unsetSimpleType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public List<Facet> getMinExclusiveList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda1(this), new RestrictionTypeImpl$$ExternalSyntheticLambda2(this), new RestrictionTypeImpl$$ExternalSyntheticLambda3(this), new RestrictionTypeImpl$$ExternalSyntheticLambda4(this), new RestrictionTypeImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public Facet[] getMinExclusiveArray() {
        return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new Facet[0]);
    }

    public Facet getMinExclusiveArray(int i) {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().find_element_user(PROPERTY_QNAME[5], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setMinExclusiveArray(Facet[] facetArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) facetArr, PROPERTY_QNAME[5]);
    }

    public void setMinExclusiveArray(int i, Facet facet) {
        generatedSetterHelperImpl(facet, PROPERTY_QNAME[5], i, 2);
    }

    public Facet insertNewMinExclusive(int i) {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return facet;
    }

    public Facet addNewMinExclusive() {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return facet;
    }

    public void removeMinExclusive(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<Facet> getMinInclusiveList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda61(this), new RestrictionTypeImpl$$ExternalSyntheticLambda62(this), new RestrictionTypeImpl$$ExternalSyntheticLambda63(this), new RestrictionTypeImpl$$ExternalSyntheticLambda64(this), new RestrictionTypeImpl$$ExternalSyntheticLambda65(this));
        }
        return javaListXmlObject;
    }

    public Facet[] getMinInclusiveArray() {
        return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new Facet[0]);
    }

    public Facet getMinInclusiveArray(int i) {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().find_element_user(PROPERTY_QNAME[6], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setMinInclusiveArray(Facet[] facetArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) facetArr, PROPERTY_QNAME[6]);
    }

    public void setMinInclusiveArray(int i, Facet facet) {
        generatedSetterHelperImpl(facet, PROPERTY_QNAME[6], i, 2);
    }

    public Facet insertNewMinInclusive(int i) {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return facet;
    }

    public Facet addNewMinInclusive() {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return facet;
    }

    public void removeMinInclusive(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<Facet> getMaxExclusiveList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda28(this), new RestrictionTypeImpl$$ExternalSyntheticLambda29(this), new RestrictionTypeImpl$$ExternalSyntheticLambda30(this), new RestrictionTypeImpl$$ExternalSyntheticLambda31(this), new RestrictionTypeImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public Facet[] getMaxExclusiveArray() {
        return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new Facet[0]);
    }

    public Facet getMaxExclusiveArray(int i) {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().find_element_user(PROPERTY_QNAME[7], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setMaxExclusiveArray(Facet[] facetArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) facetArr, PROPERTY_QNAME[7]);
    }

    public void setMaxExclusiveArray(int i, Facet facet) {
        generatedSetterHelperImpl(facet, PROPERTY_QNAME[7], i, 2);
    }

    public Facet insertNewMaxExclusive(int i) {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return facet;
    }

    public Facet addNewMaxExclusive() {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return facet;
    }

    public void removeMaxExclusive(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<Facet> getMaxInclusiveList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda6(this), new RestrictionTypeImpl$$ExternalSyntheticLambda7(this), new RestrictionTypeImpl$$ExternalSyntheticLambda8(this), new RestrictionTypeImpl$$ExternalSyntheticLambda9(this), new RestrictionTypeImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public Facet[] getMaxInclusiveArray() {
        return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new Facet[0]);
    }

    public Facet getMaxInclusiveArray(int i) {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().find_element_user(PROPERTY_QNAME[8], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setMaxInclusiveArray(Facet[] facetArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) facetArr, PROPERTY_QNAME[8]);
    }

    public void setMaxInclusiveArray(int i, Facet facet) {
        generatedSetterHelperImpl(facet, PROPERTY_QNAME[8], i, 2);
    }

    public Facet insertNewMaxInclusive(int i) {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return facet;
    }

    public Facet addNewMaxInclusive() {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return facet;
    }

    public void removeMaxInclusive(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<TotalDigitsDocument.TotalDigits> getTotalDigitsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda12(this), new RestrictionTypeImpl$$ExternalSyntheticLambda13(this), new RestrictionTypeImpl$$ExternalSyntheticLambda14(this), new RestrictionTypeImpl$$ExternalSyntheticLambda15(this), new RestrictionTypeImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public TotalDigitsDocument.TotalDigits[] getTotalDigitsArray() {
        return (TotalDigitsDocument.TotalDigits[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new TotalDigitsDocument.TotalDigits[0]);
    }

    public TotalDigitsDocument.TotalDigits getTotalDigitsArray(int i) {
        TotalDigitsDocument.TotalDigits totalDigits;
        synchronized (monitor()) {
            check_orphaned();
            totalDigits = (TotalDigitsDocument.TotalDigits) get_store().find_element_user(PROPERTY_QNAME[9], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setTotalDigitsArray(TotalDigitsDocument.TotalDigits[] totalDigitsArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) totalDigitsArr, PROPERTY_QNAME[9]);
    }

    public void setTotalDigitsArray(int i, TotalDigitsDocument.TotalDigits totalDigits) {
        generatedSetterHelperImpl(totalDigits, PROPERTY_QNAME[9], i, 2);
    }

    public TotalDigitsDocument.TotalDigits insertNewTotalDigits(int i) {
        TotalDigitsDocument.TotalDigits totalDigits;
        synchronized (monitor()) {
            check_orphaned();
            totalDigits = (TotalDigitsDocument.TotalDigits) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return totalDigits;
    }

    public TotalDigitsDocument.TotalDigits addNewTotalDigits() {
        TotalDigitsDocument.TotalDigits totalDigits;
        synchronized (monitor()) {
            check_orphaned();
            totalDigits = (TotalDigitsDocument.TotalDigits) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return totalDigits;
    }

    public void removeTotalDigits(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<NumFacet> getFractionDigitsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda45(this), new RestrictionTypeImpl$$ExternalSyntheticLambda46(this), new RestrictionTypeImpl$$ExternalSyntheticLambda47(this), new RestrictionTypeImpl$$ExternalSyntheticLambda48(this), new RestrictionTypeImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public NumFacet[] getFractionDigitsArray() {
        return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new NumFacet[0]);
    }

    public NumFacet getFractionDigitsArray(int i) {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[10], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setFractionDigitsArray(NumFacet[] numFacetArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) numFacetArr, PROPERTY_QNAME[10]);
    }

    public void setFractionDigitsArray(int i, NumFacet numFacet) {
        generatedSetterHelperImpl(numFacet, PROPERTY_QNAME[10], i, 2);
    }

    public NumFacet insertNewFractionDigits(int i) {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return numFacet;
    }

    public NumFacet addNewFractionDigits() {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return numFacet;
    }

    public void removeFractionDigits(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<NumFacet> getLengthList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda17(this), new RestrictionTypeImpl$$ExternalSyntheticLambda18(this), new RestrictionTypeImpl$$ExternalSyntheticLambda19(this), new RestrictionTypeImpl$$ExternalSyntheticLambda20(this), new RestrictionTypeImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public NumFacet[] getLengthArray() {
        return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new NumFacet[0]);
    }

    public NumFacet getLengthArray(int i) {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[11], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setLengthArray(NumFacet[] numFacetArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) numFacetArr, PROPERTY_QNAME[11]);
    }

    public void setLengthArray(int i, NumFacet numFacet) {
        generatedSetterHelperImpl(numFacet, PROPERTY_QNAME[11], i, 2);
    }

    public NumFacet insertNewLength(int i) {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return numFacet;
    }

    public NumFacet addNewLength() {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return numFacet;
    }

    public void removeLength(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<NumFacet> getMinLengthList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda34(this), new RestrictionTypeImpl$$ExternalSyntheticLambda35(this), new RestrictionTypeImpl$$ExternalSyntheticLambda36(this), new RestrictionTypeImpl$$ExternalSyntheticLambda37(this), new RestrictionTypeImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public NumFacet[] getMinLengthArray() {
        return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new NumFacet[0]);
    }

    public NumFacet getMinLengthArray(int i) {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[12], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setMinLengthArray(NumFacet[] numFacetArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) numFacetArr, PROPERTY_QNAME[12]);
    }

    public void setMinLengthArray(int i, NumFacet numFacet) {
        generatedSetterHelperImpl(numFacet, PROPERTY_QNAME[12], i, 2);
    }

    public NumFacet insertNewMinLength(int i) {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return numFacet;
    }

    public NumFacet addNewMinLength() {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return numFacet;
    }

    public void removeMinLength(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<NumFacet> getMaxLengthList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda0(this), new RestrictionTypeImpl$$ExternalSyntheticLambda11(this), new RestrictionTypeImpl$$ExternalSyntheticLambda22(this), new RestrictionTypeImpl$$ExternalSyntheticLambda33(this), new RestrictionTypeImpl$$ExternalSyntheticLambda44(this));
        }
        return javaListXmlObject;
    }

    public NumFacet[] getMaxLengthArray() {
        return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new NumFacet[0]);
    }

    public NumFacet getMaxLengthArray(int i) {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[13], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setMaxLengthArray(NumFacet[] numFacetArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) numFacetArr, PROPERTY_QNAME[13]);
    }

    public void setMaxLengthArray(int i, NumFacet numFacet) {
        generatedSetterHelperImpl(numFacet, PROPERTY_QNAME[13], i, 2);
    }

    public NumFacet insertNewMaxLength(int i) {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return numFacet;
    }

    public NumFacet addNewMaxLength() {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return numFacet;
    }

    public void removeMaxLength(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<NoFixedFacet> getEnumerationList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda23(this), new RestrictionTypeImpl$$ExternalSyntheticLambda24(this), new RestrictionTypeImpl$$ExternalSyntheticLambda25(this), new RestrictionTypeImpl$$ExternalSyntheticLambda26(this), new RestrictionTypeImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public NoFixedFacet[] getEnumerationArray() {
        return (NoFixedFacet[]) getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new NoFixedFacet[0]);
    }

    public NoFixedFacet getEnumerationArray(int i) {
        NoFixedFacet noFixedFacet;
        synchronized (monitor()) {
            check_orphaned();
            noFixedFacet = (NoFixedFacet) get_store().find_element_user(PROPERTY_QNAME[14], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setEnumerationArray(NoFixedFacet[] noFixedFacetArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) noFixedFacetArr, PROPERTY_QNAME[14]);
    }

    public void setEnumerationArray(int i, NoFixedFacet noFixedFacet) {
        generatedSetterHelperImpl(noFixedFacet, PROPERTY_QNAME[14], i, 2);
    }

    public NoFixedFacet insertNewEnumeration(int i) {
        NoFixedFacet noFixedFacet;
        synchronized (monitor()) {
            check_orphaned();
            noFixedFacet = (NoFixedFacet) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return noFixedFacet;
    }

    public NoFixedFacet addNewEnumeration() {
        NoFixedFacet noFixedFacet;
        synchronized (monitor()) {
            check_orphaned();
            noFixedFacet = (NoFixedFacet) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return noFixedFacet;
    }

    public void removeEnumeration(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    public List<WhiteSpaceDocument.WhiteSpace> getWhiteSpaceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda55(this), new RestrictionTypeImpl$$ExternalSyntheticLambda66(this), new RestrictionTypeImpl$$ExternalSyntheticLambda67(this), new RestrictionTypeImpl$$ExternalSyntheticLambda68(this), new RestrictionTypeImpl$$ExternalSyntheticLambda69(this));
        }
        return javaListXmlObject;
    }

    public WhiteSpaceDocument.WhiteSpace[] getWhiteSpaceArray() {
        return (WhiteSpaceDocument.WhiteSpace[]) getXmlObjectArray(PROPERTY_QNAME[15], (T[]) new WhiteSpaceDocument.WhiteSpace[0]);
    }

    public WhiteSpaceDocument.WhiteSpace getWhiteSpaceArray(int i) {
        WhiteSpaceDocument.WhiteSpace whiteSpace;
        synchronized (monitor()) {
            check_orphaned();
            whiteSpace = (WhiteSpaceDocument.WhiteSpace) get_store().find_element_user(PROPERTY_QNAME[15], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    public void setWhiteSpaceArray(WhiteSpaceDocument.WhiteSpace[] whiteSpaceArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) whiteSpaceArr, PROPERTY_QNAME[15]);
    }

    public void setWhiteSpaceArray(int i, WhiteSpaceDocument.WhiteSpace whiteSpace) {
        generatedSetterHelperImpl(whiteSpace, PROPERTY_QNAME[15], i, 2);
    }

    public WhiteSpaceDocument.WhiteSpace insertNewWhiteSpace(int i) {
        WhiteSpaceDocument.WhiteSpace whiteSpace;
        synchronized (monitor()) {
            check_orphaned();
            whiteSpace = (WhiteSpaceDocument.WhiteSpace) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return whiteSpace;
    }

    public WhiteSpaceDocument.WhiteSpace addNewWhiteSpace() {
        WhiteSpaceDocument.WhiteSpace whiteSpace;
        synchronized (monitor()) {
            check_orphaned();
            whiteSpace = (WhiteSpaceDocument.WhiteSpace) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return whiteSpace;
    }

    public void removeWhiteSpace(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    public List<PatternDocument.Pattern> getPatternList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda50(this), new RestrictionTypeImpl$$ExternalSyntheticLambda51(this), new RestrictionTypeImpl$$ExternalSyntheticLambda52(this), new RestrictionTypeImpl$$ExternalSyntheticLambda53(this), new RestrictionTypeImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public PatternDocument.Pattern[] getPatternArray() {
        return (PatternDocument.Pattern[]) getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new PatternDocument.Pattern[0]);
    }

    public PatternDocument.Pattern getPatternArray(int i) {
        PatternDocument.Pattern pattern;
        synchronized (monitor()) {
            check_orphaned();
            pattern = (PatternDocument.Pattern) get_store().find_element_user(PROPERTY_QNAME[16], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setPatternArray(PatternDocument.Pattern[] patternArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) patternArr, PROPERTY_QNAME[16]);
    }

    public void setPatternArray(int i, PatternDocument.Pattern pattern) {
        generatedSetterHelperImpl(pattern, PROPERTY_QNAME[16], i, 2);
    }

    public PatternDocument.Pattern insertNewPattern(int i) {
        PatternDocument.Pattern pattern;
        synchronized (monitor()) {
            check_orphaned();
            pattern = (PatternDocument.Pattern) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return pattern;
    }

    public PatternDocument.Pattern addNewPattern() {
        PatternDocument.Pattern pattern;
        synchronized (monitor()) {
            check_orphaned();
            pattern = (PatternDocument.Pattern) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return pattern;
    }

    public void removePattern(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public List<Attribute> getAttributeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda39(this), new RestrictionTypeImpl$$ExternalSyntheticLambda40(this), new RestrictionTypeImpl$$ExternalSyntheticLambda41(this), new RestrictionTypeImpl$$ExternalSyntheticLambda42(this), new RestrictionTypeImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public Attribute[] getAttributeArray() {
        return (Attribute[]) getXmlObjectArray(PROPERTY_QNAME[17], (T[]) new Attribute[0]);
    }

    public Attribute getAttributeArray(int i) {
        Attribute attribute;
        synchronized (monitor()) {
            check_orphaned();
            attribute = (Attribute) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (attribute == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return attribute;
    }

    public int sizeOfAttributeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    public void setAttributeArray(Attribute[] attributeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) attributeArr, PROPERTY_QNAME[17]);
    }

    public void setAttributeArray(int i, Attribute attribute) {
        generatedSetterHelperImpl(attribute, PROPERTY_QNAME[17], i, 2);
    }

    public Attribute insertNewAttribute(int i) {
        Attribute attribute;
        synchronized (monitor()) {
            check_orphaned();
            attribute = (Attribute) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return attribute;
    }

    public Attribute addNewAttribute() {
        Attribute attribute;
        synchronized (monitor()) {
            check_orphaned();
            attribute = (Attribute) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return attribute;
    }

    public void removeAttribute(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    public List<AttributeGroupRef> getAttributeGroupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RestrictionTypeImpl$$ExternalSyntheticLambda56(this), new RestrictionTypeImpl$$ExternalSyntheticLambda57(this), new RestrictionTypeImpl$$ExternalSyntheticLambda58(this), new RestrictionTypeImpl$$ExternalSyntheticLambda59(this), new RestrictionTypeImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    public AttributeGroupRef[] getAttributeGroupArray() {
        return (AttributeGroupRef[]) getXmlObjectArray(PROPERTY_QNAME[18], (T[]) new AttributeGroupRef[0]);
    }

    public AttributeGroupRef getAttributeGroupArray(int i) {
        AttributeGroupRef attributeGroupRef;
        synchronized (monitor()) {
            check_orphaned();
            attributeGroupRef = (AttributeGroupRef) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (attributeGroupRef == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return attributeGroupRef;
    }

    public int sizeOfAttributeGroupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    public void setAttributeGroupArray(AttributeGroupRef[] attributeGroupRefArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) attributeGroupRefArr, PROPERTY_QNAME[18]);
    }

    public void setAttributeGroupArray(int i, AttributeGroupRef attributeGroupRef) {
        generatedSetterHelperImpl(attributeGroupRef, PROPERTY_QNAME[18], i, 2);
    }

    public AttributeGroupRef insertNewAttributeGroup(int i) {
        AttributeGroupRef attributeGroupRef;
        synchronized (monitor()) {
            check_orphaned();
            attributeGroupRef = (AttributeGroupRef) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return attributeGroupRef;
    }

    public AttributeGroupRef addNewAttributeGroup() {
        AttributeGroupRef attributeGroupRef;
        synchronized (monitor()) {
            check_orphaned();
            attributeGroupRef = (AttributeGroupRef) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return attributeGroupRef;
    }

    public void removeAttributeGroup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    public Wildcard getAnyAttribute() {
        Wildcard wildcard;
        synchronized (monitor()) {
            check_orphaned();
            wildcard = (Wildcard) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (wildcard == null) {
                wildcard = null;
            }
        }
        return wildcard;
    }

    public boolean isSetAnyAttribute() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    public void setAnyAttribute(Wildcard wildcard) {
        generatedSetterHelperImpl(wildcard, PROPERTY_QNAME[19], 0, 1);
    }

    public Wildcard addNewAnyAttribute() {
        Wildcard wildcard;
        synchronized (monitor()) {
            check_orphaned();
            wildcard = (Wildcard) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return wildcard;
    }

    public void unsetAnyAttribute() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    public QName getBase() {
        QName qName;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
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
            xmlQName = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[20]);
        }
        return xmlQName;
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
            r3 = 20
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl.setBase(javax.xml.namespace.QName):void");
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
            r3 = 20
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl.xsetBase(org.apache.xmlbeans.XmlQName):void");
    }
}
