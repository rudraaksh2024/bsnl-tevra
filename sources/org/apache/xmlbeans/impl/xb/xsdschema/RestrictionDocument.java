package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface RestrictionDocument extends XmlObject {
    public static final DocumentFactory<RestrictionDocument> Factory;
    public static final SchemaType type;

    Restriction addNewRestriction();

    Restriction getRestriction();

    void setRestriction(Restriction restriction);

    static {
        DocumentFactory<RestrictionDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "restriction0049doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Restriction extends Annotated {
        public static final ElementFactory<Restriction> Factory;
        public static final SchemaType type;

        NoFixedFacet addNewEnumeration();

        NumFacet addNewFractionDigits();

        NumFacet addNewLength();

        Facet addNewMaxExclusive();

        Facet addNewMaxInclusive();

        NumFacet addNewMaxLength();

        Facet addNewMinExclusive();

        Facet addNewMinInclusive();

        NumFacet addNewMinLength();

        PatternDocument.Pattern addNewPattern();

        LocalSimpleType addNewSimpleType();

        TotalDigitsDocument.TotalDigits addNewTotalDigits();

        WhiteSpaceDocument.WhiteSpace addNewWhiteSpace();

        QName getBase();

        NoFixedFacet getEnumerationArray(int i);

        NoFixedFacet[] getEnumerationArray();

        List<NoFixedFacet> getEnumerationList();

        NumFacet getFractionDigitsArray(int i);

        NumFacet[] getFractionDigitsArray();

        List<NumFacet> getFractionDigitsList();

        NumFacet getLengthArray(int i);

        NumFacet[] getLengthArray();

        List<NumFacet> getLengthList();

        Facet getMaxExclusiveArray(int i);

        Facet[] getMaxExclusiveArray();

        List<Facet> getMaxExclusiveList();

        Facet getMaxInclusiveArray(int i);

        Facet[] getMaxInclusiveArray();

        List<Facet> getMaxInclusiveList();

        NumFacet getMaxLengthArray(int i);

        NumFacet[] getMaxLengthArray();

        List<NumFacet> getMaxLengthList();

        Facet getMinExclusiveArray(int i);

        Facet[] getMinExclusiveArray();

        List<Facet> getMinExclusiveList();

        Facet getMinInclusiveArray(int i);

        Facet[] getMinInclusiveArray();

        List<Facet> getMinInclusiveList();

        NumFacet getMinLengthArray(int i);

        NumFacet[] getMinLengthArray();

        List<NumFacet> getMinLengthList();

        PatternDocument.Pattern getPatternArray(int i);

        PatternDocument.Pattern[] getPatternArray();

        List<PatternDocument.Pattern> getPatternList();

        LocalSimpleType getSimpleType();

        TotalDigitsDocument.TotalDigits getTotalDigitsArray(int i);

        TotalDigitsDocument.TotalDigits[] getTotalDigitsArray();

        List<TotalDigitsDocument.TotalDigits> getTotalDigitsList();

        WhiteSpaceDocument.WhiteSpace getWhiteSpaceArray(int i);

        WhiteSpaceDocument.WhiteSpace[] getWhiteSpaceArray();

        List<WhiteSpaceDocument.WhiteSpace> getWhiteSpaceList();

        NoFixedFacet insertNewEnumeration(int i);

        NumFacet insertNewFractionDigits(int i);

        NumFacet insertNewLength(int i);

        Facet insertNewMaxExclusive(int i);

        Facet insertNewMaxInclusive(int i);

        NumFacet insertNewMaxLength(int i);

        Facet insertNewMinExclusive(int i);

        Facet insertNewMinInclusive(int i);

        NumFacet insertNewMinLength(int i);

        PatternDocument.Pattern insertNewPattern(int i);

        TotalDigitsDocument.TotalDigits insertNewTotalDigits(int i);

        WhiteSpaceDocument.WhiteSpace insertNewWhiteSpace(int i);

        boolean isSetBase();

        boolean isSetSimpleType();

        void removeEnumeration(int i);

        void removeFractionDigits(int i);

        void removeLength(int i);

        void removeMaxExclusive(int i);

        void removeMaxInclusive(int i);

        void removeMaxLength(int i);

        void removeMinExclusive(int i);

        void removeMinInclusive(int i);

        void removeMinLength(int i);

        void removePattern(int i);

        void removeTotalDigits(int i);

        void removeWhiteSpace(int i);

        void setBase(QName qName);

        void setEnumerationArray(int i, NoFixedFacet noFixedFacet);

        void setEnumerationArray(NoFixedFacet[] noFixedFacetArr);

        void setFractionDigitsArray(int i, NumFacet numFacet);

        void setFractionDigitsArray(NumFacet[] numFacetArr);

        void setLengthArray(int i, NumFacet numFacet);

        void setLengthArray(NumFacet[] numFacetArr);

        void setMaxExclusiveArray(int i, Facet facet);

        void setMaxExclusiveArray(Facet[] facetArr);

        void setMaxInclusiveArray(int i, Facet facet);

        void setMaxInclusiveArray(Facet[] facetArr);

        void setMaxLengthArray(int i, NumFacet numFacet);

        void setMaxLengthArray(NumFacet[] numFacetArr);

        void setMinExclusiveArray(int i, Facet facet);

        void setMinExclusiveArray(Facet[] facetArr);

        void setMinInclusiveArray(int i, Facet facet);

        void setMinInclusiveArray(Facet[] facetArr);

        void setMinLengthArray(int i, NumFacet numFacet);

        void setMinLengthArray(NumFacet[] numFacetArr);

        void setPatternArray(int i, PatternDocument.Pattern pattern);

        void setPatternArray(PatternDocument.Pattern[] patternArr);

        void setSimpleType(LocalSimpleType localSimpleType);

        void setTotalDigitsArray(int i, TotalDigitsDocument.TotalDigits totalDigits);

        void setTotalDigitsArray(TotalDigitsDocument.TotalDigits[] totalDigitsArr);

        void setWhiteSpaceArray(int i, WhiteSpaceDocument.WhiteSpace whiteSpace);

        void setWhiteSpaceArray(WhiteSpaceDocument.WhiteSpace[] whiteSpaceArr);

        int sizeOfEnumerationArray();

        int sizeOfFractionDigitsArray();

        int sizeOfLengthArray();

        int sizeOfMaxExclusiveArray();

        int sizeOfMaxInclusiveArray();

        int sizeOfMaxLengthArray();

        int sizeOfMinExclusiveArray();

        int sizeOfMinInclusiveArray();

        int sizeOfMinLengthArray();

        int sizeOfPatternArray();

        int sizeOfTotalDigitsArray();

        int sizeOfWhiteSpaceArray();

        void unsetBase();

        void unsetSimpleType();

        XmlQName xgetBase();

        void xsetBase(XmlQName xmlQName);

        static {
            ElementFactory<Restriction> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "restrictionad11elemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
