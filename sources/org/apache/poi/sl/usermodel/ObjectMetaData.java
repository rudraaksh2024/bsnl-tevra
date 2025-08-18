package org.apache.poi.sl.usermodel;

import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;

public interface ObjectMetaData {
    ClassID getClassID();

    String getObjectName();

    String getOleEntry();

    String getProgId();

    public enum Application {
        EXCEL_V8("Worksheet", "Excel.Sheet.8", ExtractorFactory.OOXML_PACKAGE, ClassIDPredefined.EXCEL_V8),
        EXCEL_V12("Worksheet", "Excel.Sheet.12", ExtractorFactory.OOXML_PACKAGE, ClassIDPredefined.EXCEL_V12),
        WORD_V8("Document", "Word.Document.8", ExtractorFactory.OOXML_PACKAGE, ClassIDPredefined.WORD_V8),
        WORD_V12("Document", "Word.Document.12", ExtractorFactory.OOXML_PACKAGE, ClassIDPredefined.WORD_V12),
        PDF("PDF", "AcroExch.Document", "Contents", ClassIDPredefined.PDF),
        CUSTOM((String) null, (int) null, (String) null, (String) null);
        
        ClassID classId;
        String objectName;
        String oleEntry;
        String progId;

        private Application(String str, String str2, String str3, ClassIDPredefined classIDPredefined) {
            ClassID classID;
            this.objectName = str;
            this.progId = str2;
            if (classIDPredefined == null) {
                classID = null;
            } else {
                classID = classIDPredefined.getClassID();
            }
            this.classId = classID;
            this.oleEntry = str3;
        }

        public static Application lookup(String str) {
            for (Application application : values()) {
                String str2 = application.progId;
                if (str2 != null && str2.equals(str)) {
                    return application;
                }
            }
            return null;
        }

        public ObjectMetaData getMetaData() {
            return new ObjectMetaData() {
                public String getObjectName() {
                    return Application.this.objectName;
                }

                public String getProgId() {
                    return Application.this.progId;
                }

                public String getOleEntry() {
                    return Application.this.oleEntry;
                }

                public ClassID getClassID() {
                    return Application.this.classId;
                }
            };
        }
    }
}
