package org.apache.poi.openxml4j.opc;

import java.util.Date;
import java.util.Optional;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public interface PackageProperties {
    public static final String NAMESPACE_DC = "http://purl.org/dc/elements/1.1/";
    public static final String NAMESPACE_DCTERMS = "http://purl.org/dc/terms/";

    Optional<String> getCategoryProperty();

    Optional<String> getContentStatusProperty();

    Optional<String> getContentTypeProperty();

    Optional<Date> getCreatedProperty();

    Optional<String> getCreatorProperty();

    Optional<String> getDescriptionProperty();

    Optional<String> getIdentifierProperty();

    Optional<String> getKeywordsProperty();

    Optional<String> getLanguageProperty();

    Optional<String> getLastModifiedByProperty();

    Optional<Date> getLastPrintedProperty();

    Optional<Date> getModifiedProperty();

    Optional<String> getRevisionProperty();

    Optional<String> getSubjectProperty();

    Optional<String> getTitleProperty();

    Optional<String> getVersionProperty();

    void setCategoryProperty(String str);

    void setCategoryProperty(Optional<String> optional);

    void setContentStatusProperty(String str);

    void setContentStatusProperty(Optional<String> optional);

    void setContentTypeProperty(String str);

    void setContentTypeProperty(Optional<String> optional);

    void setCreatedProperty(String str) throws InvalidFormatException;

    void setCreatedProperty(Optional<Date> optional);

    void setCreatorProperty(String str);

    void setCreatorProperty(Optional<String> optional);

    void setDescriptionProperty(String str);

    void setDescriptionProperty(Optional<String> optional);

    void setIdentifierProperty(String str);

    void setIdentifierProperty(Optional<String> optional);

    void setKeywordsProperty(String str);

    void setKeywordsProperty(Optional<String> optional);

    void setLanguageProperty(String str);

    void setLanguageProperty(Optional<String> optional);

    void setLastModifiedByProperty(String str);

    void setLastModifiedByProperty(Optional<String> optional);

    void setLastPrintedProperty(String str) throws InvalidFormatException;

    void setLastPrintedProperty(Optional<Date> optional);

    void setModifiedProperty(String str) throws InvalidFormatException;

    void setModifiedProperty(Optional<Date> optional);

    void setRevisionProperty(String str);

    void setRevisionProperty(Optional<String> optional);

    void setSubjectProperty(String str);

    void setSubjectProperty(Optional<String> optional);

    void setTitleProperty(String str);

    void setTitleProperty(Optional<String> optional);

    void setVersionProperty(String str);

    void setVersionProperty(Optional<String> optional);
}
