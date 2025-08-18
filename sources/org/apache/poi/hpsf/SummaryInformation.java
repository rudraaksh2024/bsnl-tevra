package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;

public final class SummaryInformation extends PropertySet {
    public static final String DEFAULT_STREAM_NAME = "\u0005SummaryInformation";
    public static final ClassID FORMAT_ID = ClassIDPredefined.SUMMARY_PROPERTIES.getClassID();

    public PropertyIDMap getPropertySetIDMap() {
        return PropertyIDMap.getSummaryInformationProperties();
    }

    public SummaryInformation() {
        getFirstSection().setFormatID(FORMAT_ID);
    }

    public SummaryInformation(PropertySet propertySet) throws UnexpectedPropertySetTypeException {
        super(propertySet);
        if (!isSummaryInformation()) {
            throw new UnexpectedPropertySetTypeException("Not a " + getClass().getName());
        }
    }

    public SummaryInformation(InputStream inputStream) throws NoPropertySetStreamException, IOException {
        super(inputStream);
    }

    public String getTitle() {
        return getPropertyStringValue(2);
    }

    public void setTitle(String str) {
        set1stProperty(2, str);
    }

    public void removeTitle() {
        remove1stProperty(2);
    }

    public String getSubject() {
        return getPropertyStringValue(3);
    }

    public void setSubject(String str) {
        set1stProperty(3, str);
    }

    public void removeSubject() {
        remove1stProperty(3);
    }

    public String getAuthor() {
        return getPropertyStringValue(4);
    }

    public void setAuthor(String str) {
        set1stProperty(4, str);
    }

    public void removeAuthor() {
        remove1stProperty(4);
    }

    public String getKeywords() {
        return getPropertyStringValue(5);
    }

    public void setKeywords(String str) {
        set1stProperty(5, str);
    }

    public void removeKeywords() {
        remove1stProperty(5);
    }

    public String getComments() {
        return getPropertyStringValue(6);
    }

    public void setComments(String str) {
        set1stProperty(6, str);
    }

    public void removeComments() {
        remove1stProperty(6);
    }

    public String getTemplate() {
        return getPropertyStringValue(7);
    }

    public void setTemplate(String str) {
        set1stProperty(7, str);
    }

    public void removeTemplate() {
        remove1stProperty(7);
    }

    public String getLastAuthor() {
        return getPropertyStringValue(8);
    }

    public void setLastAuthor(String str) {
        set1stProperty(8, str);
    }

    public void removeLastAuthor() {
        remove1stProperty(8);
    }

    public String getRevNumber() {
        return getPropertyStringValue(9);
    }

    public void setRevNumber(String str) {
        set1stProperty(9, str);
    }

    public void removeRevNumber() {
        remove1stProperty(9);
    }

    public long getEditTime() {
        Date date = (Date) getProperty(10);
        if (date == null) {
            return 0;
        }
        return Filetime.dateToFileTime(date);
    }

    public void setEditTime(long j) {
        getFirstSection().setProperty(10, 64, Filetime.filetimeToDate(j));
    }

    public void removeEditTime() {
        remove1stProperty(10);
    }

    public Date getLastPrinted() {
        return (Date) getProperty(11);
    }

    public void setLastPrinted(Date date) {
        getFirstSection().setProperty(11, 64, date);
    }

    public void removeLastPrinted() {
        remove1stProperty(11);
    }

    public Date getCreateDateTime() {
        return (Date) getProperty(12);
    }

    public void setCreateDateTime(Date date) {
        getFirstSection().setProperty(12, 64, date);
    }

    public void removeCreateDateTime() {
        remove1stProperty(12);
    }

    public Date getLastSaveDateTime() {
        return (Date) getProperty(13);
    }

    public void setLastSaveDateTime(Date date) {
        getFirstSection().setProperty(13, 64, date);
    }

    public void removeLastSaveDateTime() {
        remove1stProperty(13);
    }

    public int getPageCount() {
        return getPropertyIntValue(14);
    }

    public void setPageCount(int i) {
        set1stProperty(14, i);
    }

    public void removePageCount() {
        remove1stProperty(14);
    }

    public int getWordCount() {
        return getPropertyIntValue(15);
    }

    public void setWordCount(int i) {
        set1stProperty(15, i);
    }

    public void removeWordCount() {
        remove1stProperty(15);
    }

    public int getCharCount() {
        return getPropertyIntValue(16);
    }

    public void setCharCount(int i) {
        set1stProperty(16, i);
    }

    public void removeCharCount() {
        remove1stProperty(16);
    }

    public byte[] getThumbnail() {
        return (byte[]) getProperty(17);
    }

    public Thumbnail getThumbnailThumbnail() {
        byte[] thumbnail = getThumbnail();
        if (thumbnail == null) {
            return null;
        }
        return new Thumbnail(thumbnail);
    }

    public void setThumbnail(byte[] bArr) {
        getFirstSection().setProperty(17, 30, bArr);
    }

    public void removeThumbnail() {
        remove1stProperty(17);
    }

    public String getApplicationName() {
        return getPropertyStringValue(18);
    }

    public void setApplicationName(String str) {
        set1stProperty(18, str);
    }

    public void removeApplicationName() {
        remove1stProperty(18);
    }

    public int getSecurity() {
        return getPropertyIntValue(19);
    }

    public void setSecurity(int i) {
        set1stProperty(19, i);
    }

    public void removeSecurity() {
        remove1stProperty(19);
    }
}
