package org.apache.poi.ooxml.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.extractor.ExtractorProvider;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xdgf.extractor.XDGFVisioExtractor;
import org.apache.poi.xslf.extractor.XSLFExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xssf.extractor.XSSFBEventBasedExcelExtractor;
import org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.xmlbeans.XmlException;

public final class POIXMLExtractorFactory implements ExtractorProvider {
    private static final String CORE_DOCUMENT_REL = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument";
    private static final String STRICT_DOCUMENT_REL = "http://purl.oclc.org/ooxml/officeDocument/relationships/officeDocument";
    private static final List<XSLFRelation> SUPPORTED_XSLF_TYPES = Collections.unmodifiableList(Arrays.asList(new XSLFRelation[]{XSLFRelation.MAIN, XSLFRelation.MACRO, XSLFRelation.MACRO_TEMPLATE, XSLFRelation.PRESENTATIONML, XSLFRelation.PRESENTATIONML_TEMPLATE, XSLFRelation.PRESENTATION_MACRO}));
    private static final String VISIO_DOCUMENT_REL = "http://schemas.microsoft.com/visio/2010/relationships/document";

    public boolean accepts(FileMagic fileMagic) {
        return fileMagic == FileMagic.OOXML;
    }

    public static boolean getThreadPrefersEventExtractors() {
        return ExtractorFactory.getThreadPrefersEventExtractors();
    }

    public static Boolean getAllThreadsPreferEventExtractors() {
        return ExtractorFactory.getAllThreadsPreferEventExtractors();
    }

    public static void setThreadPrefersEventExtractors(boolean z) {
        ExtractorFactory.setThreadPrefersEventExtractors(z);
    }

    public static void setAllThreadsPreferEventExtractors(Boolean bool) {
        ExtractorFactory.setAllThreadsPreferEventExtractors(bool);
    }

    public static boolean getPreferEventExtractor() {
        return ExtractorFactory.getPreferEventExtractor();
    }

    public POITextExtractor create(File file, String str) throws IOException {
        if (FileMagic.valueOf(file) != FileMagic.OOXML) {
            return ExtractorFactory.createExtractor(file, str);
        }
        OPCPackage oPCPackage = null;
        try {
            OPCPackage open = OPCPackage.open(file.toString(), PackageAccess.READ);
            POIXMLTextExtractor create = create(open);
            if (create == null) {
                open.revert();
            }
            return create;
        } catch (InvalidFormatException e) {
            throw new IOException(e);
        } catch (IOException e2) {
            if (oPCPackage != null) {
                oPCPackage.revert();
            }
            throw e2;
        }
    }

    public POITextExtractor create(InputStream inputStream, String str) throws IOException {
        InputStream prepareToCheckMagic = FileMagic.prepareToCheckMagic(inputStream);
        if (FileMagic.valueOf(prepareToCheckMagic) != FileMagic.OOXML) {
            return ExtractorFactory.createExtractor(prepareToCheckMagic, str);
        }
        OPCPackage oPCPackage = null;
        try {
            OPCPackage open = OPCPackage.open(prepareToCheckMagic);
            POIXMLTextExtractor create = create(open);
            if (create == null) {
                open.revert();
            }
            return create;
        } catch (InvalidFormatException e) {
            throw new IOException(e);
        } catch (IOException | RuntimeException e2) {
            if (oPCPackage != null) {
                oPCPackage.revert();
            }
            throw e2;
        }
    }

    public POIXMLTextExtractor create(OPCPackage oPCPackage) throws IOException {
        try {
            PackageRelationshipCollection relationshipsByType = oPCPackage.getRelationshipsByType("http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument");
            if (relationshipsByType.isEmpty()) {
                relationshipsByType = oPCPackage.getRelationshipsByType("http://purl.oclc.org/ooxml/officeDocument/relationships/officeDocument");
            }
            if (relationshipsByType.isEmpty()) {
                relationshipsByType = oPCPackage.getRelationshipsByType("http://schemas.microsoft.com/visio/2010/relationships/document");
                if (relationshipsByType.size() == 1) {
                    return new XDGFVisioExtractor(oPCPackage);
                }
            }
            if (relationshipsByType.size() == 1) {
                String contentType = oPCPackage.getPart(relationshipsByType.getRelationship(0)).getContentType();
                for (XSSFRelation contentType2 : XSSFExcelExtractor.SUPPORTED_TYPES) {
                    if (contentType2.getContentType().equals(contentType)) {
                        if (getPreferEventExtractor()) {
                            return new XSSFEventBasedExcelExtractor(oPCPackage);
                        }
                        return new XSSFExcelExtractor(oPCPackage);
                    }
                }
                for (XWPFRelation contentType3 : XWPFWordExtractor.SUPPORTED_TYPES) {
                    if (contentType3.getContentType().equals(contentType)) {
                        return new XWPFWordExtractor(oPCPackage);
                    }
                }
                for (XSLFRelation contentType4 : SUPPORTED_XSLF_TYPES) {
                    if (contentType4.getContentType().equals(contentType)) {
                        return new XSLFExtractor(new XMLSlideShow(oPCPackage));
                    }
                }
                if (XSLFRelation.THEME_MANAGER.getContentType().equals(contentType)) {
                    return new XSLFExtractor(new XMLSlideShow(oPCPackage));
                }
                for (XSSFRelation contentType5 : XSSFBEventBasedExcelExtractor.SUPPORTED_TYPES) {
                    if (contentType5.getContentType().equals(contentType)) {
                        return new XSSFBEventBasedExcelExtractor(oPCPackage);
                    }
                }
                return null;
            }
            throw new IllegalArgumentException("Invalid OOXML Package received - expected 1 core document, found " + relationshipsByType.size());
        } catch (Error | RuntimeException | OpenXML4JException | XmlException e) {
            throw new IOException(e);
        }
    }

    public POITextExtractor create(POIFSFileSystem pOIFSFileSystem) throws IOException {
        return create(pOIFSFileSystem.getRoot(), Biff8EncryptionKey.getCurrentUserPassword());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        if (r3 != null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0020, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0023, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0054, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0055, code lost:
        if (r0 != null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x005f, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.extractor.POITextExtractor create(org.apache.poi.poifs.filesystem.DirectoryNode r3, java.lang.String r4) throws java.io.IOException {
        /*
            r2 = this;
            java.lang.String r0 = "Package"
            boolean r1 = r3.hasEntry(r0)
            if (r1 == 0) goto L_0x0024
            org.apache.poi.poifs.filesystem.DocumentInputStream r3 = r3.createDocumentInputStream((java.lang.String) r0)
            org.apache.poi.extractor.POITextExtractor r2 = r2.create((java.io.InputStream) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0016 }
            if (r3 == 0) goto L_0x0015
            r3.close()
        L_0x0015:
            return r2
        L_0x0016:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0018 }
        L_0x0018:
            r4 = move-exception
            if (r3 == 0) goto L_0x0023
            r3.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r3 = move-exception
            r2.addSuppressed(r3)
        L_0x0023:
            throw r4
        L_0x0024:
            java.lang.String r0 = "EncryptedPackage"
            boolean r0 = r3.hasEntry(r0)
            if (r0 == 0) goto L_0x007c
            org.apache.poi.poifs.crypt.EncryptionInfo r0 = new org.apache.poi.poifs.crypt.EncryptionInfo
            r0.<init>((org.apache.poi.poifs.filesystem.DirectoryNode) r3)
            org.apache.poi.poifs.crypt.Decryptor r0 = r0.getDecryptor()
            boolean r1 = r0.verifyPassword(r4)     // Catch:{ IOException | RuntimeException -> 0x007a, Exception -> 0x0073 }
            if (r1 == 0) goto L_0x006b
            java.io.InputStream r0 = r0.getDataStream((org.apache.poi.poifs.filesystem.DirectoryNode) r3)     // Catch:{ all -> 0x0060 }
            org.apache.poi.extractor.POITextExtractor r2 = r2.create((java.io.InputStream) r0, (java.lang.String) r4)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0048
            r0.close()     // Catch:{ all -> 0x0060 }
        L_0x0048:
            org.apache.poi.poifs.filesystem.POIFSFileSystem r3 = r3.getFileSystem()     // Catch:{ IOException | RuntimeException -> 0x007a, Exception -> 0x0073 }
            if (r3 == 0) goto L_0x0051
            r3.close()     // Catch:{ IOException | RuntimeException -> 0x007a, Exception -> 0x0073 }
        L_0x0051:
            return r2
        L_0x0052:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r4 = move-exception
            if (r0 == 0) goto L_0x005f
            r0.close()     // Catch:{ all -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ all -> 0x0060 }
        L_0x005f:
            throw r4     // Catch:{ all -> 0x0060 }
        L_0x0060:
            r2 = move-exception
            org.apache.poi.poifs.filesystem.POIFSFileSystem r3 = r3.getFileSystem()     // Catch:{ IOException | RuntimeException -> 0x007a, Exception -> 0x0073 }
            if (r3 == 0) goto L_0x006a
            r3.close()     // Catch:{ IOException | RuntimeException -> 0x007a, Exception -> 0x0073 }
        L_0x006a:
            throw r2     // Catch:{ IOException | RuntimeException -> 0x007a, Exception -> 0x0073 }
        L_0x006b:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x007a, Exception -> 0x0073 }
            java.lang.String r3 = "Invalid password specified"
            r2.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x007a, Exception -> 0x0073 }
            throw r2     // Catch:{ IOException | RuntimeException -> 0x007a, Exception -> 0x0073 }
        L_0x0073:
            r2 = move-exception
            java.io.IOException r3 = new java.io.IOException
            r3.<init>(r2)
            throw r3
        L_0x007a:
            r2 = move-exception
            throw r2
        L_0x007c:
            java.io.IOException r2 = new java.io.IOException
            java.lang.String r3 = "The OLE2 file neither contained a plain OOXML package node (\"Package\") nor an encrypted one (\"EncryptedPackage\")."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.extractor.POIXMLExtractorFactory.create(org.apache.poi.poifs.filesystem.DirectoryNode, java.lang.String):org.apache.poi.extractor.POITextExtractor");
    }
}
