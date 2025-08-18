package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.usermodel.XDGFSheet;

@Internal
enum XDGFSectionTypes {
    LINE_GRADIENT("LineGradient", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    FILL_GRADIENT("FillGradient", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    CHARACTER("Character", new XDGFSectionTypes$$ExternalSyntheticLambda3()),
    PARAGRAPH("Paragraph", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    TABS("Tabs", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    SCRATCH("Scratch", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    CONNECTION("Connection", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    CONNECTION_ABCD("ConnectionABCD", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    FIELD("Field", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    CONTROL("Control", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    GEOMETRY("Geometry", new XDGFSectionTypes$$ExternalSyntheticLambda1()),
    ACTIONS("Actions", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    LAYER("Layer", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    USER("User", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    PROPERTY("Property", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    HYPERLINK("Hyperlink", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    REVIEWER("Reviewer", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    ANNOTATION("Annotation", new XDGFSectionTypes$$ExternalSyntheticLambda0()),
    ACTION_TAG("ActionTag", new XDGFSectionTypes$$ExternalSyntheticLambda0());
    
    private static final Map<String, XDGFSectionTypes> LOOKUP = null;
    private final BiFunction<SectionType, XDGFSheet, ? extends XDGFSection> constructor;
    private final String sectionType;

    static {
        LOOKUP = (Map) Stream.of(values()).collect(Collectors.toMap(new XDGFSectionTypes$$ExternalSyntheticLambda2(), Function.identity()));
    }

    private XDGFSectionTypes(String str, BiFunction<SectionType, XDGFSheet, ? extends XDGFSection> biFunction) {
        this.sectionType = str;
        this.constructor = biFunction;
    }

    public String getSectionType() {
        return this.sectionType;
    }

    public static XDGFSection load(SectionType sectionType2, XDGFSheet xDGFSheet) {
        String n = sectionType2.getN();
        XDGFSectionTypes xDGFSectionTypes = LOOKUP.get(n);
        if (xDGFSectionTypes != null) {
            return (XDGFSection) xDGFSectionTypes.constructor.apply(sectionType2, xDGFSheet);
        }
        throw new POIXMLException("Invalid '" + sectionType2.schemaType().getName().getLocalPart() + "' name '" + n + "'");
    }
}
