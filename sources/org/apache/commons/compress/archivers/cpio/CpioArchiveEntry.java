package org.apache.commons.compress.archivers.cpio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.archivers.ArchiveEntry;

public class CpioArchiveEntry implements CpioConstants, ArchiveEntry {
    private final int alignmentBoundary;
    private long chksum;
    private final short fileFormat;
    private long filesize;
    private long gid;
    private final int headerSize;
    private long inode;
    private long maj;
    private long min;
    private long mode;
    private long mtime;
    private String name;
    private long nlink;
    private long rmaj;
    private long rmin;
    private long uid;

    public CpioArchiveEntry(short s) {
        if (s == 1) {
            this.headerSize = 110;
            this.alignmentBoundary = 4;
        } else if (s == 2) {
            this.headerSize = 110;
            this.alignmentBoundary = 4;
        } else if (s == 4) {
            this.headerSize = 76;
            this.alignmentBoundary = 0;
        } else if (s == 8) {
            this.headerSize = 26;
            this.alignmentBoundary = 2;
        } else {
            throw new IllegalArgumentException("Unknown header type");
        }
        this.fileFormat = s;
    }

    public CpioArchiveEntry(String str) {
        this(1, str);
    }

    public CpioArchiveEntry(short s, String str) {
        this(s);
        this.name = str;
    }

    public CpioArchiveEntry(String str, long j) {
        this(str);
        setSize(j);
    }

    public CpioArchiveEntry(short s, String str, long j) {
        this(s, str);
        setSize(j);
    }

    public CpioArchiveEntry(File file, String str) {
        this(1, file, str);
    }

    public CpioArchiveEntry(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        this(1, path, str, linkOptionArr);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CpioArchiveEntry(short s, File file, String str) {
        this(s, str, file.isFile() ? file.length() : 0);
        if (file.isDirectory()) {
            setMode(PlaybackStateCompat.ACTION_PREPARE);
        } else if (file.isFile()) {
            setMode(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID);
        } else {
            throw new IllegalArgumentException("Cannot determine type of file " + file.getName());
        }
        setTime(file.lastModified() / 1000);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CpioArchiveEntry(short s, Path path, String str, LinkOption... linkOptionArr) throws IOException {
        this(s, str, Files.isRegularFile(path, linkOptionArr) ? Files.size(path) : 0);
        if (Files.isDirectory(path, linkOptionArr)) {
            setMode(PlaybackStateCompat.ACTION_PREPARE);
        } else if (Files.isRegularFile(path, linkOptionArr)) {
            setMode(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID);
        } else {
            throw new IllegalArgumentException("Cannot determine type of file " + path);
        }
        setTime(Files.getLastModifiedTime(path, linkOptionArr));
    }

    private void checkNewFormat() {
        if ((this.fileFormat & 3) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    private void checkOldFormat() {
        if ((this.fileFormat & 12) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    public long getChksum() {
        checkNewFormat();
        return this.chksum & 4294967295L;
    }

    public long getDevice() {
        checkOldFormat();
        return this.min;
    }

    public long getDeviceMaj() {
        checkNewFormat();
        return this.maj;
    }

    public long getDeviceMin() {
        checkNewFormat();
        return this.min;
    }

    public long getSize() {
        return this.filesize;
    }

    public short getFormat() {
        return this.fileFormat;
    }

    public long getGID() {
        return this.gid;
    }

    public int getHeaderSize() {
        return this.headerSize;
    }

    public int getAlignmentBoundary() {
        return this.alignmentBoundary;
    }

    @Deprecated
    public int getHeaderPadCount() {
        return getHeaderPadCount((Charset) null);
    }

    public int getHeaderPadCount(Charset charset) {
        String str = this.name;
        if (str == null) {
            return 0;
        }
        if (charset == null) {
            return getHeaderPadCount((long) str.length());
        }
        return getHeaderPadCount((long) str.getBytes(charset).length);
    }

    public int getHeaderPadCount(long j) {
        int i = this.alignmentBoundary;
        if (i == 0) {
            return 0;
        }
        int i2 = this.headerSize + 1;
        if (this.name != null) {
            i2 = (int) (((long) i2) + j);
        }
        int i3 = i2 % i;
        if (i3 > 0) {
            return i - i3;
        }
        return 0;
    }

    public int getDataPadCount() {
        int i;
        int i2 = this.alignmentBoundary;
        if (i2 != 0 && (i = (int) (this.filesize % ((long) i2))) > 0) {
            return i2 - i;
        }
        return 0;
    }

    public long getInode() {
        return this.inode;
    }

    public long getMode() {
        return (this.mode != 0 || CpioConstants.CPIO_TRAILER.equals(this.name)) ? this.mode : PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
    }

    public String getName() {
        return this.name;
    }

    public long getNumberOfLinks() {
        long j = this.nlink;
        if (j == 0) {
            return isDirectory() ? 2 : 1;
        }
        return j;
    }

    public long getRemoteDevice() {
        checkOldFormat();
        return this.rmin;
    }

    public long getRemoteDeviceMaj() {
        checkNewFormat();
        return this.rmaj;
    }

    public long getRemoteDeviceMin() {
        checkNewFormat();
        return this.rmin;
    }

    public long getTime() {
        return this.mtime;
    }

    public Date getLastModifiedDate() {
        return new Date(getTime() * 1000);
    }

    public long getUID() {
        return this.uid;
    }

    public boolean isBlockDevice() {
        return CpioUtil.fileType(this.mode) == 24576;
    }

    public boolean isCharacterDevice() {
        return CpioUtil.fileType(this.mode) == PlaybackStateCompat.ACTION_PLAY_FROM_URI;
    }

    public boolean isDirectory() {
        return CpioUtil.fileType(this.mode) == PlaybackStateCompat.ACTION_PREPARE;
    }

    public boolean isNetwork() {
        return CpioUtil.fileType(this.mode) == 36864;
    }

    public boolean isPipe() {
        return CpioUtil.fileType(this.mode) == PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }

    public boolean isRegularFile() {
        return CpioUtil.fileType(this.mode) == PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
    }

    public boolean isSocket() {
        return CpioUtil.fileType(this.mode) == 49152;
    }

    public boolean isSymbolicLink() {
        return CpioUtil.fileType(this.mode) == 40960;
    }

    public void setChksum(long j) {
        checkNewFormat();
        this.chksum = j & 4294967295L;
    }

    public void setDevice(long j) {
        checkOldFormat();
        this.min = j;
    }

    public void setDeviceMaj(long j) {
        checkNewFormat();
        this.maj = j;
    }

    public void setDeviceMin(long j) {
        checkNewFormat();
        this.min = j;
    }

    public void setSize(long j) {
        if (j < 0 || j > 4294967295L) {
            throw new IllegalArgumentException("Invalid entry size <" + j + ">");
        }
        this.filesize = j;
    }

    public void setGID(long j) {
        this.gid = j;
    }

    public void setInode(long j) {
        this.inode = j;
    }

    public void setMode(long j) {
        long j2 = 61440 & j;
        switch ((int) j2) {
            case 4096:
            case 8192:
            case 16384:
            case CpioConstants.C_ISBLK:
            case 32768:
            case CpioConstants.C_ISNWK:
            case 40960:
            case CpioConstants.C_ISSOCK:
                this.mode = j;
                return;
            default:
                throw new IllegalArgumentException("Unknown mode. Full: " + Long.toHexString(j) + " Masked: " + Long.toHexString(j2));
        }
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNumberOfLinks(long j) {
        this.nlink = j;
    }

    public void setRemoteDevice(long j) {
        checkOldFormat();
        this.rmin = j;
    }

    public void setRemoteDeviceMaj(long j) {
        checkNewFormat();
        this.rmaj = j;
    }

    public void setRemoteDeviceMin(long j) {
        checkNewFormat();
        this.rmin = j;
    }

    public void setTime(long j) {
        this.mtime = j;
    }

    public void setTime(FileTime fileTime) {
        this.mtime = fileTime.to(TimeUnit.SECONDS);
    }

    public void setUID(long j) {
        this.uid = j;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CpioArchiveEntry cpioArchiveEntry = (CpioArchiveEntry) obj;
        String str = this.name;
        if (str != null) {
            return str.equals(cpioArchiveEntry.name);
        }
        if (cpioArchiveEntry.name == null) {
            return true;
        }
        return false;
    }
}
