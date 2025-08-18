package org.apache.commons.compress.archivers.arj;

import java.util.Arrays;
import java.util.Objects;

class LocalFileHeader {
    int archiverVersionNumber;
    int arjFlags;
    String comment;
    long compressedSize;
    int dateTimeAccessed;
    int dateTimeCreated;
    int dateTimeModified;
    int extendedFilePosition;
    byte[][] extendedHeaders;
    int fileAccessMode;
    int fileSpecPosition;
    int fileType;
    int firstChapter;
    int hostOS;
    int lastChapter;
    int method;
    int minVersionToExtract;
    String name;
    long originalCrc32;
    long originalSize;
    int originalSizeEvenForVolumes;
    int reserved;

    LocalFileHeader() {
    }

    static class Flags {
        static final int BACKUP = 32;
        static final int EXTFILE = 8;
        static final int GARBLED = 1;
        static final int PATHSYM = 16;
        static final int VOLUME = 4;

        Flags() {
        }
    }

    static class FileTypes {
        static final int BINARY = 0;
        static final int CHAPTER_LABEL = 5;
        static final int COMMENT_HEADER = 2;
        static final int DIRECTORY = 3;
        static final int SEVEN_BIT_TEXT = 1;
        static final int VOLUME_LABEL = 4;

        FileTypes() {
        }
    }

    static class Methods {
        static final int COMPRESSED = 2;
        static final int COMPRESSED_FASTER = 3;
        static final int COMPRESSED_FASTEST = 4;
        static final int COMPRESSED_MOST = 1;
        static final int NO_DATA = 9;
        static final int NO_DATA_NO_CRC = 8;
        static final int STORED = 0;

        Methods() {
        }
    }

    public String toString() {
        return "LocalFileHeader [archiverVersionNumber=" + this.archiverVersionNumber + ", minVersionToExtract=" + this.minVersionToExtract + ", hostOS=" + this.hostOS + ", arjFlags=" + this.arjFlags + ", method=" + this.method + ", fileType=" + this.fileType + ", reserved=" + this.reserved + ", dateTimeModified=" + this.dateTimeModified + ", compressedSize=" + this.compressedSize + ", originalSize=" + this.originalSize + ", originalCrc32=" + this.originalCrc32 + ", fileSpecPosition=" + this.fileSpecPosition + ", fileAccessMode=" + this.fileAccessMode + ", firstChapter=" + this.firstChapter + ", lastChapter=" + this.lastChapter + ", extendedFilePosition=" + this.extendedFilePosition + ", dateTimeAccessed=" + this.dateTimeAccessed + ", dateTimeCreated=" + this.dateTimeCreated + ", originalSizeEvenForVolumes=" + this.originalSizeEvenForVolumes + ", name=" + this.name + ", comment=" + this.comment + ", extendedHeaders=" + Arrays.toString(this.extendedHeaders) + "]";
    }

    public int hashCode() {
        String str = this.name;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LocalFileHeader localFileHeader = (LocalFileHeader) obj;
        if (this.archiverVersionNumber == localFileHeader.archiverVersionNumber && this.minVersionToExtract == localFileHeader.minVersionToExtract && this.hostOS == localFileHeader.hostOS && this.arjFlags == localFileHeader.arjFlags && this.method == localFileHeader.method && this.fileType == localFileHeader.fileType && this.reserved == localFileHeader.reserved && this.dateTimeModified == localFileHeader.dateTimeModified && this.compressedSize == localFileHeader.compressedSize && this.originalSize == localFileHeader.originalSize && this.originalCrc32 == localFileHeader.originalCrc32 && this.fileSpecPosition == localFileHeader.fileSpecPosition && this.fileAccessMode == localFileHeader.fileAccessMode && this.firstChapter == localFileHeader.firstChapter && this.lastChapter == localFileHeader.lastChapter && this.extendedFilePosition == localFileHeader.extendedFilePosition && this.dateTimeAccessed == localFileHeader.dateTimeAccessed && this.dateTimeCreated == localFileHeader.dateTimeCreated && this.originalSizeEvenForVolumes == localFileHeader.originalSizeEvenForVolumes && Objects.equals(this.name, localFileHeader.name) && Objects.equals(this.comment, localFileHeader.comment) && Arrays.deepEquals(this.extendedHeaders, localFileHeader.extendedHeaders)) {
            return true;
        }
        return false;
    }
}
