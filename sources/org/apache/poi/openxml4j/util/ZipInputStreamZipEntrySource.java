package org.apache.poi.openxml4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.IOUtils;

public class ZipInputStreamZipEntrySource implements ZipEntrySource {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static boolean encryptTempFiles = false;
    private static int thresholdForTempFiles = -1;
    private InputStream streamToClose;
    private final Map<String, ZipArchiveFakeEntry> zipEntries = new HashMap();

    public static void setThresholdBytesForTempFiles(int i) {
        thresholdForTempFiles = i;
    }

    public static int getThresholdBytesForTempFiles() {
        return thresholdForTempFiles;
    }

    public static void setEncryptTempFiles(boolean z) {
        encryptTempFiles = z;
    }

    public static boolean shouldEncryptTempFiles() {
        return encryptTempFiles;
    }

    public ZipInputStreamZipEntrySource(ZipArchiveThresholdInputStream zipArchiveThresholdInputStream) throws IOException {
        while (true) {
            ZipArchiveEntry nextEntry = zipArchiveThresholdInputStream.getNextEntry();
            if (nextEntry == null) {
                this.streamToClose = zipArchiveThresholdInputStream;
                return;
            }
            this.zipEntries.put(nextEntry.getName(), new ZipArchiveFakeEntry(nextEntry, zipArchiveThresholdInputStream));
        }
    }

    public Enumeration<? extends ZipArchiveEntry> getEntries() {
        return Collections.enumeration(this.zipEntries.values());
    }

    public InputStream getInputStream(ZipArchiveEntry zipArchiveEntry) throws IOException {
        return ((ZipArchiveFakeEntry) zipArchiveEntry).getInputStream();
    }

    public void close() throws IOException {
        for (ZipArchiveFakeEntry close : this.zipEntries.values()) {
            close.close();
        }
        this.zipEntries.clear();
        this.streamToClose.close();
    }

    public boolean isClosed() {
        return this.zipEntries.isEmpty();
    }

    public ZipArchiveEntry getEntry(String str) {
        String replace = str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        ZipArchiveEntry zipArchiveEntry = this.zipEntries.get(replace);
        if (zipArchiveEntry != null) {
            return zipArchiveEntry;
        }
        for (Map.Entry next : this.zipEntries.entrySet()) {
            if (replace.equalsIgnoreCase((String) next.getKey())) {
                return (ZipArchiveEntry) next.getValue();
            }
        }
        return null;
    }
}
