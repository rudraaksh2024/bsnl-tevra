package org.apache.commons.compress.archivers.dump;

import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

public class DumpArchiveEntry implements ArchiveEntry {
    private long atime;
    private long ctime;
    private int generation;
    private int gid;
    private final TapeSegmentHeader header = new TapeSegmentHeader();
    private int ino;
    private boolean isDeleted;
    private int mode;
    private long mtime;
    private String name;
    private int nlink;
    private long offset;
    private String originalName;
    private Set<PERMISSION> permissions = Collections.emptySet();
    private String simpleName;
    private long size;
    private final DumpArchiveSummary summary = null;
    private TYPE type = TYPE.UNKNOWN;
    private int uid;
    private int volume;

    public DumpArchiveEntry() {
    }

    public DumpArchiveEntry(String str, String str2) {
        setName(str);
        this.simpleName = str2;
    }

    protected DumpArchiveEntry(String str, String str2, int i, TYPE type2) {
        setType(type2);
        setName(str);
        this.simpleName = str2;
        this.ino = i;
        this.offset = 0;
    }

    public String getSimpleName() {
        return this.simpleName;
    }

    /* access modifiers changed from: protected */
    public void setSimpleName(String str) {
        this.simpleName = str;
    }

    public int getIno() {
        return this.header.getIno();
    }

    public int getNlink() {
        return this.nlink;
    }

    public void setNlink(int i) {
        this.nlink = i;
    }

    public Date getCreationTime() {
        return new Date(this.ctime);
    }

    public void setCreationTime(Date date) {
        this.ctime = date.getTime();
    }

    public int getGeneration() {
        return this.generation;
    }

    public void setGeneration(int i) {
        this.generation = i;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean z) {
        this.isDeleted = z;
    }

    public long getOffset() {
        return this.offset;
    }

    public void setOffset(long j) {
        this.offset = j;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int i) {
        this.volume = i;
    }

    public DumpArchiveConstants.SEGMENT_TYPE getHeaderType() {
        return this.header.getType();
    }

    public int getHeaderCount() {
        return this.header.getCount();
    }

    public int getHeaderHoles() {
        return this.header.getHoles();
    }

    public boolean isSparseRecord(int i) {
        return (this.header.getCdata(i) & 1) == 0;
    }

    public int hashCode() {
        return this.ino;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        DumpArchiveEntry dumpArchiveEntry = (DumpArchiveEntry) obj;
        if (dumpArchiveEntry.header == null || this.ino != dumpArchiveEntry.ino) {
            return false;
        }
        DumpArchiveSummary dumpArchiveSummary = this.summary;
        return (dumpArchiveSummary != null || dumpArchiveEntry.summary == null) && (dumpArchiveSummary == null || dumpArchiveSummary.equals(dumpArchiveEntry.summary));
    }

    public String toString() {
        return getName();
    }

    static DumpArchiveEntry parse(byte[] bArr) {
        DumpArchiveEntry dumpArchiveEntry = new DumpArchiveEntry();
        TapeSegmentHeader tapeSegmentHeader = dumpArchiveEntry.header;
        DumpArchiveConstants.SEGMENT_TYPE unused = tapeSegmentHeader.type = DumpArchiveConstants.SEGMENT_TYPE.find(DumpArchiveUtil.convert32(bArr, 0));
        int unused2 = tapeSegmentHeader.volume = DumpArchiveUtil.convert32(bArr, 12);
        dumpArchiveEntry.ino = tapeSegmentHeader.ino = DumpArchiveUtil.convert32(bArr, 20);
        int convert16 = DumpArchiveUtil.convert16(bArr, 32);
        dumpArchiveEntry.setType(TYPE.find((convert16 >> 12) & 15));
        dumpArchiveEntry.setMode(convert16);
        dumpArchiveEntry.nlink = DumpArchiveUtil.convert16(bArr, 34);
        dumpArchiveEntry.setSize(DumpArchiveUtil.convert64(bArr, 40));
        dumpArchiveEntry.setAccessTime(new Date((((long) DumpArchiveUtil.convert32(bArr, 48)) * 1000) + ((long) (DumpArchiveUtil.convert32(bArr, 52) / 1000))));
        dumpArchiveEntry.setLastModifiedDate(new Date((((long) DumpArchiveUtil.convert32(bArr, 56)) * 1000) + ((long) (DumpArchiveUtil.convert32(bArr, 60) / 1000))));
        dumpArchiveEntry.ctime = (((long) DumpArchiveUtil.convert32(bArr, 64)) * 1000) + ((long) (DumpArchiveUtil.convert32(bArr, 68) / 1000));
        dumpArchiveEntry.generation = DumpArchiveUtil.convert32(bArr, 140);
        dumpArchiveEntry.setUserId(DumpArchiveUtil.convert32(bArr, 144));
        dumpArchiveEntry.setGroupId(DumpArchiveUtil.convert32(bArr, 148));
        int unused3 = tapeSegmentHeader.count = DumpArchiveUtil.convert32(bArr, 160);
        int unused4 = tapeSegmentHeader.holes = 0;
        int i = 0;
        while (i < 512 && i < tapeSegmentHeader.count) {
            if (bArr[i + 164] == 0) {
                TapeSegmentHeader.access$408(tapeSegmentHeader);
            }
            i++;
        }
        System.arraycopy(bArr, 164, tapeSegmentHeader.cdata, 0, 512);
        dumpArchiveEntry.volume = tapeSegmentHeader.getVolume();
        return dumpArchiveEntry;
    }

    /* access modifiers changed from: package-private */
    public void update(byte[] bArr) {
        int unused = this.header.volume = DumpArchiveUtil.convert32(bArr, 16);
        int unused2 = this.header.count = DumpArchiveUtil.convert32(bArr, 160);
        int unused3 = this.header.holes = 0;
        int i = 0;
        while (i < 512 && i < this.header.count) {
            if (bArr[i + 164] == 0) {
                TapeSegmentHeader.access$408(this.header);
            }
            i++;
        }
        System.arraycopy(bArr, 164, this.header.cdata, 0, 512);
    }

    static class TapeSegmentHeader {
        /* access modifiers changed from: private */
        public final byte[] cdata = new byte[512];
        /* access modifiers changed from: private */
        public int count;
        /* access modifiers changed from: private */
        public int holes;
        /* access modifiers changed from: private */
        public int ino;
        /* access modifiers changed from: private */
        public DumpArchiveConstants.SEGMENT_TYPE type;
        /* access modifiers changed from: private */
        public int volume;

        TapeSegmentHeader() {
        }

        static /* synthetic */ int access$408(TapeSegmentHeader tapeSegmentHeader) {
            int i = tapeSegmentHeader.holes;
            tapeSegmentHeader.holes = i + 1;
            return i;
        }

        public DumpArchiveConstants.SEGMENT_TYPE getType() {
            return this.type;
        }

        public int getVolume() {
            return this.volume;
        }

        public int getIno() {
            return this.ino;
        }

        /* access modifiers changed from: package-private */
        public void setIno(int i) {
            this.ino = i;
        }

        public int getCount() {
            return this.count;
        }

        public int getHoles() {
            return this.holes;
        }

        public int getCdata(int i) {
            return this.cdata[i];
        }
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public String getOriginalName() {
        return this.originalName;
    }

    public final void setName(String str) {
        this.originalName = str;
        if (str != null) {
            if (isDirectory() && !str.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                str = str + PackagingURIHelper.FORWARD_SLASH_STRING;
            }
            if (str.startsWith("./")) {
                str = str.substring(2);
            }
        }
        this.name = str;
    }

    public Date getLastModifiedDate() {
        return new Date(this.mtime);
    }

    public boolean isDirectory() {
        return this.type == TYPE.DIRECTORY;
    }

    public boolean isFile() {
        return this.type == TYPE.FILE;
    }

    public boolean isSocket() {
        return this.type == TYPE.SOCKET;
    }

    public boolean isChrDev() {
        return this.type == TYPE.CHRDEV;
    }

    public boolean isBlkDev() {
        return this.type == TYPE.BLKDEV;
    }

    public boolean isFifo() {
        return this.type == TYPE.FIFO;
    }

    public TYPE getType() {
        return this.type;
    }

    public void setType(TYPE type2) {
        this.type = type2;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int i) {
        this.mode = i & 4095;
        this.permissions = PERMISSION.find(i);
    }

    public Set<PERMISSION> getPermissions() {
        return this.permissions;
    }

    public long getSize() {
        if (isDirectory()) {
            return -1;
        }
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public long getEntrySize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setLastModifiedDate(Date date) {
        this.mtime = date.getTime();
    }

    public Date getAccessTime() {
        return new Date(this.atime);
    }

    public void setAccessTime(Date date) {
        this.atime = date.getTime();
    }

    public int getUserId() {
        return this.uid;
    }

    public void setUserId(int i) {
        this.uid = i;
    }

    public int getGroupId() {
        return this.gid;
    }

    public void setGroupId(int i) {
        this.gid = i;
    }

    public enum TYPE {
        WHITEOUT(14),
        SOCKET(12),
        LINK(10),
        FILE(8),
        BLKDEV(6),
        DIRECTORY(4),
        CHRDEV(2),
        FIFO(1),
        UNKNOWN(15);
        
        private final int code;

        private TYPE(int i) {
            this.code = i;
        }

        public static TYPE find(int i) {
            TYPE type = UNKNOWN;
            for (TYPE type2 : values()) {
                if (i == type2.code) {
                    type = type2;
                }
            }
            return type;
        }
    }

    public enum PERMISSION {
        SETUID(2048),
        SETGUI(1024),
        STICKY(512),
        USER_READ(256),
        USER_WRITE(128),
        USER_EXEC(64),
        GROUP_READ(32),
        GROUP_WRITE(16),
        GROUP_EXEC(8),
        WORLD_READ(4),
        WORLD_WRITE(2),
        WORLD_EXEC(1);
        
        private final int code;

        private PERMISSION(int i) {
            this.code = i;
        }

        public static Set<PERMISSION> find(int i) {
            HashSet hashSet = new HashSet();
            for (PERMISSION permission : values()) {
                int i2 = permission.code;
                if ((i & i2) == i2) {
                    hashSet.add(permission);
                }
            }
            if (hashSet.isEmpty()) {
                return Collections.emptySet();
            }
            return EnumSet.copyOf(hashSet);
        }
    }
}
