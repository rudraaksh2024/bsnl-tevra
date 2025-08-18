package org.apache.poi.openxml4j.opc.internal;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.apache.poi.util.LocaleUtil;

public final class PackagePropertiesPart extends PackagePart implements PackageProperties {
    private static final String[] DATE_FORMATS = {"yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ss.SS'Z'", "yyyy-MM-dd"};
    private static final String DEFAULT_DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String NAMESPACE_CP_URI = "http://schemas.openxmlformats.org/package/2006/metadata/core-properties";
    public static final String NAMESPACE_DCTERMS_URI = "http://purl.org/dc/terms/";
    public static final String NAMESPACE_DC_URI = "http://purl.org/dc/elements/1.1/";
    private final Pattern TIME_ZONE_PAT = Pattern.compile("([-+]\\d\\d):?(\\d\\d)");
    private final String[] TZ_DATE_FORMATS = {"yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd'T'HH:mm:ss.Sz", "yyyy-MM-dd'T'HH:mm:ss.SSz", "yyyy-MM-dd'T'HH:mm:ss.SSSz"};
    protected Optional<String> category = Optional.empty();
    protected Optional<String> contentStatus = Optional.empty();
    protected Optional<String> contentType = Optional.empty();
    protected Optional<Date> created = Optional.empty();
    protected Optional<String> creator = Optional.empty();
    protected Optional<String> description = Optional.empty();
    protected Optional<String> identifier = Optional.empty();
    protected Optional<String> keywords = Optional.empty();
    protected Optional<String> language = Optional.empty();
    protected Optional<String> lastModifiedBy = Optional.empty();
    protected Optional<Date> lastPrinted = Optional.empty();
    protected Optional<Date> modified = Optional.empty();
    protected Optional<String> revision = Optional.empty();
    protected Optional<String> subject = Optional.empty();
    protected Optional<String> title = Optional.empty();
    protected Optional<String> version = Optional.empty();

    public void close() {
    }

    public void flush() {
    }

    public PackagePropertiesPart(OPCPackage oPCPackage, PackagePartName packagePartName) throws InvalidFormatException {
        super(oPCPackage, packagePartName, ContentTypes.CORE_PROPERTIES_PART);
    }

    public Optional<String> getCategoryProperty() {
        return this.category;
    }

    public Optional<String> getContentStatusProperty() {
        return this.contentStatus;
    }

    public Optional<String> getContentTypeProperty() {
        return this.contentType;
    }

    public Optional<Date> getCreatedProperty() {
        return this.created;
    }

    public String getCreatedPropertyString() {
        return getDateValue(this.created);
    }

    public Optional<String> getCreatorProperty() {
        return this.creator;
    }

    public Optional<String> getDescriptionProperty() {
        return this.description;
    }

    public Optional<String> getIdentifierProperty() {
        return this.identifier;
    }

    public Optional<String> getKeywordsProperty() {
        return this.keywords;
    }

    public Optional<String> getLanguageProperty() {
        return this.language;
    }

    public Optional<String> getLastModifiedByProperty() {
        return this.lastModifiedBy;
    }

    public Optional<Date> getLastPrintedProperty() {
        return this.lastPrinted;
    }

    public String getLastPrintedPropertyString() {
        return getDateValue(this.lastPrinted);
    }

    public Optional<Date> getModifiedProperty() {
        return this.modified;
    }

    public String getModifiedPropertyString() {
        if (this.modified.isPresent()) {
            return getDateValue(this.modified);
        }
        return getDateValue((Optional<Date>) Optional.of(new Date()));
    }

    public Optional<String> getRevisionProperty() {
        return this.revision;
    }

    public Optional<String> getSubjectProperty() {
        return this.subject;
    }

    public Optional<String> getTitleProperty() {
        return this.title;
    }

    public Optional<String> getVersionProperty() {
        return this.version;
    }

    public void setCategoryProperty(String str) {
        this.category = parseStringValue(str);
    }

    public void setCategoryProperty(Optional<String> optional) {
        this.category = optional;
    }

    public void setContentStatusProperty(String str) {
        this.contentStatus = parseStringValue(str);
    }

    public void setContentStatusProperty(Optional<String> optional) {
        this.contentStatus = optional;
    }

    public void setContentTypeProperty(String str) {
        this.contentType = parseStringValue(str);
    }

    public void setContentTypeProperty(Optional<String> optional) {
        this.contentType = optional;
    }

    public void setCreatedProperty(String str) throws InvalidFormatException {
        this.created = parseDateValue(str);
    }

    public void setCreatedProperty(Optional<Date> optional) {
        this.created = optional;
    }

    public void setCreatorProperty(String str) {
        this.creator = parseStringValue(str);
    }

    public void setCreatorProperty(Optional<String> optional) {
        this.creator = optional;
    }

    public void setDescriptionProperty(String str) {
        this.description = parseStringValue(str);
    }

    public void setDescriptionProperty(Optional<String> optional) {
        this.description = optional;
    }

    public void setIdentifierProperty(String str) {
        this.identifier = parseStringValue(str);
    }

    public void setIdentifierProperty(Optional<String> optional) {
        this.identifier = optional;
    }

    public void setKeywordsProperty(String str) {
        this.keywords = parseStringValue(str);
    }

    public void setKeywordsProperty(Optional<String> optional) {
        this.keywords = optional;
    }

    public void setLanguageProperty(String str) {
        this.language = parseStringValue(str);
    }

    public void setLanguageProperty(Optional<String> optional) {
        this.language = optional;
    }

    public void setLastModifiedByProperty(String str) {
        this.lastModifiedBy = parseStringValue(str);
    }

    public void setLastModifiedByProperty(Optional<String> optional) {
        this.lastModifiedBy = optional;
    }

    public void setLastPrintedProperty(String str) throws InvalidFormatException {
        this.lastPrinted = parseDateValue(str);
    }

    public void setLastPrintedProperty(Optional<Date> optional) {
        this.lastPrinted = optional;
    }

    public void setModifiedProperty(String str) throws InvalidFormatException {
        this.modified = parseDateValue(str);
    }

    public void setModifiedProperty(Optional<Date> optional) {
        this.modified = optional;
    }

    public void setRevisionProperty(Optional<String> optional) {
        this.revision = optional;
    }

    public void setRevisionProperty(String str) {
        this.revision = parseStringValue(str);
    }

    public void setSubjectProperty(String str) {
        this.subject = parseStringValue(str);
    }

    public void setSubjectProperty(Optional<String> optional) {
        this.subject = optional;
    }

    public void setTitleProperty(String str) {
        this.title = parseStringValue(str);
    }

    public void setTitleProperty(Optional<String> optional) {
        this.title = optional;
    }

    public void setVersionProperty(String str) {
        this.version = parseStringValue(str);
    }

    public void setVersionProperty(Optional<String> optional) {
        this.version = optional;
    }

    private Optional<String> parseStringValue(String str) {
        if (str == null || str.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(str);
    }

    private Optional<Date> parseDateValue(String str) throws InvalidFormatException {
        if (str == null || str.isEmpty()) {
            return Optional.empty();
        }
        Matcher matcher = this.TIME_ZONE_PAT.matcher(str);
        Date parseDateFormat = matcher.find() ? parseDateFormat(this.TZ_DATE_FORMATS, str.substring(0, matcher.start()) + matcher.group(1) + matcher.group(2)) : null;
        if (parseDateFormat == null) {
            parseDateFormat = parseDateFormat(DATE_FORMATS, str.endsWith("Z") ? str : str + "Z");
        }
        if (parseDateFormat != null) {
            return Optional.of(parseDateFormat);
        }
        throw new InvalidFormatException("Date " + str + " not well formatted, expected format in: " + ((String) Stream.of(new String[][]{this.TZ_DATE_FORMATS, DATE_FORMATS}).flatMap(new PackagePropertiesPart$$ExternalSyntheticLambda1()).collect(Collectors.joining(", "))));
    }

    private static Date parseDateFormat(String[] strArr, String str) {
        for (String simpleDateFormat : strArr) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(simpleDateFormat, Locale.ROOT);
            simpleDateFormat2.setTimeZone(LocaleUtil.TIMEZONE_UTC);
            Date parse = simpleDateFormat2.parse(str, new ParsePosition(0));
            if (parse != null) {
                return parse;
            }
        }
        return null;
    }

    private static String getDateValue(Optional<Date> optional) {
        return (String) optional.map(new PackagePropertiesPart$$ExternalSyntheticLambda0()).orElse("");
    }

    /* access modifiers changed from: private */
    public static String getDateValue(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT);
        simpleDateFormat.setTimeZone(LocaleUtil.TIMEZONE_UTC);
        return simpleDateFormat.format(date);
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStreamImpl() {
        throw new InvalidOperationException("Operation not authorized. This part may only be manipulated using the getters and setters on PackagePropertiesPart");
    }

    /* access modifiers changed from: protected */
    public OutputStream getOutputStreamImpl() {
        throw new InvalidOperationException("Can't use output stream to set properties !");
    }

    public boolean save(OutputStream outputStream) {
        throw new InvalidOperationException("Operation not authorized. This part may only be manipulated using the getters and setters on PackagePropertiesPart");
    }

    public boolean load(InputStream inputStream) {
        throw new InvalidOperationException("Operation not authorized. This part may only be manipulated using the getters and setters on PackagePropertiesPart");
    }
}
