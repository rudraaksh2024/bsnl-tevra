package org.apache.commons.compress.archivers.examples;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

public class Archiver {
    public static final EnumSet<FileVisitOption> EMPTY_FileVisitOption = EnumSet.noneOf(FileVisitOption.class);

    private static class ArchiverFileVisitor extends SimpleFileVisitor<Path> {
        private final Path directory;
        private final LinkOption[] linkOptions;
        private final ArchiveOutputStream target;

        private ArchiverFileVisitor(ArchiveOutputStream archiveOutputStream, Path path, LinkOption... linkOptionArr) {
            this.target = archiveOutputStream;
            this.directory = path;
            this.linkOptions = linkOptionArr == null ? IOUtils.EMPTY_LINK_OPTIONS : (LinkOption[]) linkOptionArr.clone();
        }

        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            return visit(path, basicFileAttributes, false);
        }

        /* access modifiers changed from: protected */
        public FileVisitResult visit(Path path, BasicFileAttributes basicFileAttributes, boolean z) throws IOException {
            Objects.requireNonNull(path);
            Objects.requireNonNull(basicFileAttributes);
            String replace = this.directory.relativize(path).toString().replace(org.apache.commons.io.IOUtils.DIR_SEPARATOR_WINDOWS, '/');
            if (!replace.isEmpty()) {
                ArchiveOutputStream archiveOutputStream = this.target;
                if (!z && !replace.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                    replace = replace + PackagingURIHelper.FORWARD_SLASH_STRING;
                }
                this.target.putArchiveEntry(archiveOutputStream.createArchiveEntry(path, replace, this.linkOptions));
                if (z) {
                    Files.copy(path, this.target);
                }
                this.target.closeArchiveEntry();
            }
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            return visit(path, basicFileAttributes, true);
        }
    }

    public void create(ArchiveOutputStream archiveOutputStream, File file) throws IOException, ArchiveException {
        create(archiveOutputStream, file.toPath(), EMPTY_FileVisitOption, new LinkOption[0]);
    }

    public void create(ArchiveOutputStream archiveOutputStream, Path path, EnumSet<FileVisitOption> enumSet, LinkOption... linkOptionArr) throws IOException {
        Files.walkFileTree(path, enumSet, Integer.MAX_VALUE, new ArchiverFileVisitor(archiveOutputStream, path, linkOptionArr));
        archiveOutputStream.finish();
    }

    public void create(ArchiveOutputStream archiveOutputStream, Path path) throws IOException {
        create(archiveOutputStream, path, EMPTY_FileVisitOption, new LinkOption[0]);
    }

    public void create(SevenZOutputFile sevenZOutputFile, File file) throws IOException {
        create(sevenZOutputFile, file.toPath());
    }

    public void create(SevenZOutputFile sevenZOutputFile, Path path) throws IOException {
        final Path path2 = path;
        final SevenZOutputFile sevenZOutputFile2 = sevenZOutputFile;
        Files.walkFileTree(path, new ArchiverFileVisitor((ArchiveOutputStream) null, path, new LinkOption[0]) {
            /* access modifiers changed from: protected */
            public FileVisitResult visit(Path path, BasicFileAttributes basicFileAttributes, boolean z) throws IOException {
                Objects.requireNonNull(path);
                Objects.requireNonNull(basicFileAttributes);
                String replace = path2.relativize(path).toString().replace(org.apache.commons.io.IOUtils.DIR_SEPARATOR_WINDOWS, '/');
                if (!replace.isEmpty()) {
                    SevenZOutputFile sevenZOutputFile = sevenZOutputFile2;
                    if (!z && !replace.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                        replace = replace + PackagingURIHelper.FORWARD_SLASH_STRING;
                    }
                    sevenZOutputFile2.putArchiveEntry(sevenZOutputFile.createArchiveEntry(path, replace, new LinkOption[0]));
                    if (z) {
                        sevenZOutputFile2.write(path, new OpenOption[0]);
                    }
                    sevenZOutputFile2.closeArchiveEntry();
                }
                return FileVisitResult.CONTINUE;
            }
        });
        sevenZOutputFile.finish();
    }

    public void create(String str, File file, File file2) throws IOException, ArchiveException {
        create(str, file.toPath(), file2.toPath());
    }

    @Deprecated
    public void create(String str, OutputStream outputStream, File file) throws IOException, ArchiveException {
        create(str, outputStream, file, CloseableConsumer.NULL_CONSUMER);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void create(java.lang.String r2, java.io.OutputStream r3, java.io.File r4, org.apache.commons.compress.archivers.examples.CloseableConsumer r5) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r1 = this;
            org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter r0 = new org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter
            r0.<init>(r5)
            org.apache.commons.compress.archivers.ArchiveStreamFactory r5 = org.apache.commons.compress.archivers.ArchiveStreamFactory.DEFAULT     // Catch:{ all -> 0x0018 }
            org.apache.commons.compress.archivers.ArchiveOutputStream r2 = r5.createArchiveOutputStream(r2, r3)     // Catch:{ all -> 0x0018 }
            java.io.Closeable r2 = r0.track(r2)     // Catch:{ all -> 0x0018 }
            org.apache.commons.compress.archivers.ArchiveOutputStream r2 = (org.apache.commons.compress.archivers.ArchiveOutputStream) r2     // Catch:{ all -> 0x0018 }
            r1.create((org.apache.commons.compress.archivers.ArchiveOutputStream) r2, (java.io.File) r4)     // Catch:{ all -> 0x0018 }
            r0.close()
            return
        L_0x0018:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001a }
        L_0x001a:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x0023:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Archiver.create(java.lang.String, java.io.OutputStream, java.io.File, org.apache.commons.compress.archivers.examples.CloseableConsumer):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        if (r5 != null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0032, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004f, code lost:
        if (r4 != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0055, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0056, code lost:
        r3.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0059, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void create(java.lang.String r4, java.nio.file.Path r5, java.nio.file.Path r6) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r3 = this;
            boolean r0 = r3.prefersSeekableByteChannel(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0033
            r0 = 3
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.nio.file.StandardOpenOption r2 = java.nio.file.StandardOpenOption.WRITE
            r0[r1] = r2
            r1 = 1
            java.nio.file.StandardOpenOption r2 = java.nio.file.StandardOpenOption.CREATE
            r0[r1] = r2
            r1 = 2
            java.nio.file.StandardOpenOption r2 = java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
            r0[r1] = r2
            java.nio.channels.FileChannel r5 = java.nio.channels.FileChannel.open(r5, r0)
            r3.create((java.lang.String) r4, (java.nio.channels.SeekableByteChannel) r5, (java.nio.file.Path) r6)     // Catch:{ all -> 0x0025 }
            if (r5 == 0) goto L_0x0024
            r5.close()
        L_0x0024:
            return
        L_0x0025:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0027 }
        L_0x0027:
            r4 = move-exception
            if (r5 == 0) goto L_0x0032
            r5.close()     // Catch:{ all -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r5 = move-exception
            r3.addSuppressed(r5)
        L_0x0032:
            throw r4
        L_0x0033:
            org.apache.commons.compress.archivers.ArchiveStreamFactory r0 = org.apache.commons.compress.archivers.ArchiveStreamFactory.DEFAULT
            java.nio.file.OpenOption[] r2 = new java.nio.file.OpenOption[r1]
            java.io.OutputStream r5 = java.nio.file.Files.newOutputStream(r5, r2)
            org.apache.commons.compress.archivers.ArchiveOutputStream r4 = r0.createArchiveOutputStream(r4, r5)
            java.util.EnumSet<java.nio.file.FileVisitOption> r5 = EMPTY_FileVisitOption     // Catch:{ all -> 0x004c }
            java.nio.file.LinkOption[] r0 = new java.nio.file.LinkOption[r1]     // Catch:{ all -> 0x004c }
            r3.create((org.apache.commons.compress.archivers.ArchiveOutputStream) r4, (java.nio.file.Path) r6, (java.util.EnumSet<java.nio.file.FileVisitOption>) r5, (java.nio.file.LinkOption[]) r0)     // Catch:{ all -> 0x004c }
            if (r4 == 0) goto L_0x004b
            r4.close()
        L_0x004b:
            return
        L_0x004c:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x004e }
        L_0x004e:
            r5 = move-exception
            if (r4 == 0) goto L_0x0059
            r4.close()     // Catch:{ all -> 0x0055 }
            goto L_0x0059
        L_0x0055:
            r4 = move-exception
            r3.addSuppressed(r4)
        L_0x0059:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Archiver.create(java.lang.String, java.nio.file.Path, java.nio.file.Path):void");
    }

    @Deprecated
    public void create(String str, SeekableByteChannel seekableByteChannel, File file) throws IOException, ArchiveException {
        create(str, seekableByteChannel, file, CloseableConsumer.NULL_CONSUMER);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0061, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0066, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0067, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006a, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void create(java.lang.String r3, java.nio.channels.SeekableByteChannel r4, java.io.File r5, org.apache.commons.compress.archivers.examples.CloseableConsumer r6) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r2 = this;
            java.lang.String r0 = "Don't know how to handle format "
            org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter r1 = new org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter
            r1.<init>(r6)
            boolean r6 = r2.prefersSeekableByteChannel(r3)     // Catch:{ all -> 0x005f }
            if (r6 != 0) goto L_0x001b
            java.io.OutputStream r4 = java.nio.channels.Channels.newOutputStream(r4)     // Catch:{ all -> 0x005f }
            java.io.Closeable r4 = r1.track(r4)     // Catch:{ all -> 0x005f }
            java.io.OutputStream r4 = (java.io.OutputStream) r4     // Catch:{ all -> 0x005f }
            r2.create((java.lang.String) r3, (java.io.OutputStream) r4, (java.io.File) r5)     // Catch:{ all -> 0x005f }
            goto L_0x0048
        L_0x001b:
            java.lang.String r6 = "zip"
            boolean r6 = r6.equalsIgnoreCase(r3)     // Catch:{ all -> 0x005f }
            if (r6 == 0) goto L_0x0032
            org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream r3 = new org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream     // Catch:{ all -> 0x005f }
            r3.<init>((java.nio.channels.SeekableByteChannel) r4)     // Catch:{ all -> 0x005f }
            java.io.Closeable r3 = r1.track(r3)     // Catch:{ all -> 0x005f }
            org.apache.commons.compress.archivers.ArchiveOutputStream r3 = (org.apache.commons.compress.archivers.ArchiveOutputStream) r3     // Catch:{ all -> 0x005f }
            r2.create((org.apache.commons.compress.archivers.ArchiveOutputStream) r3, (java.io.File) r5)     // Catch:{ all -> 0x005f }
            goto L_0x0048
        L_0x0032:
            java.lang.String r6 = "7z"
            boolean r6 = r6.equalsIgnoreCase(r3)     // Catch:{ all -> 0x005f }
            if (r6 == 0) goto L_0x004c
            org.apache.commons.compress.archivers.sevenz.SevenZOutputFile r3 = new org.apache.commons.compress.archivers.sevenz.SevenZOutputFile     // Catch:{ all -> 0x005f }
            r3.<init>((java.nio.channels.SeekableByteChannel) r4)     // Catch:{ all -> 0x005f }
            java.io.Closeable r3 = r1.track(r3)     // Catch:{ all -> 0x005f }
            org.apache.commons.compress.archivers.sevenz.SevenZOutputFile r3 = (org.apache.commons.compress.archivers.sevenz.SevenZOutputFile) r3     // Catch:{ all -> 0x005f }
            r2.create((org.apache.commons.compress.archivers.sevenz.SevenZOutputFile) r3, (java.io.File) r5)     // Catch:{ all -> 0x005f }
        L_0x0048:
            r1.close()
            return
        L_0x004c:
            org.apache.commons.compress.archivers.ArchiveException r2 = new org.apache.commons.compress.archivers.ArchiveException     // Catch:{ all -> 0x005f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
            r4.<init>(r0)     // Catch:{ all -> 0x005f }
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ all -> 0x005f }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x005f }
            r2.<init>(r3)     // Catch:{ all -> 0x005f }
            throw r2     // Catch:{ all -> 0x005f }
        L_0x005f:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0061 }
        L_0x0061:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x006a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Archiver.create(java.lang.String, java.nio.channels.SeekableByteChannel, java.io.File, org.apache.commons.compress.archivers.examples.CloseableConsumer):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0040, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0041, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0044, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r3 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void create(java.lang.String r2, java.nio.channels.SeekableByteChannel r3, java.nio.file.Path r4) throws java.io.IOException {
        /*
            r1 = this;
            java.lang.String r0 = "7z"
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x0020
            org.apache.commons.compress.archivers.sevenz.SevenZOutputFile r2 = new org.apache.commons.compress.archivers.sevenz.SevenZOutputFile
            r2.<init>((java.nio.channels.SeekableByteChannel) r3)
            r1.create((org.apache.commons.compress.archivers.sevenz.SevenZOutputFile) r2, (java.nio.file.Path) r4)     // Catch:{ all -> 0x0014 }
            r2.close()
            goto L_0x0038
        L_0x0014:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0016 }
        L_0x0016:
            r3 = move-exception
            r2.close()     // Catch:{ all -> 0x001b }
            goto L_0x001f
        L_0x001b:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x001f:
            throw r3
        L_0x0020:
            java.lang.String r0 = "zip"
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x0045
            org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream r2 = new org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream
            r2.<init>((java.nio.channels.SeekableByteChannel) r3)
            java.util.EnumSet<java.nio.file.FileVisitOption> r3 = EMPTY_FileVisitOption     // Catch:{ all -> 0x0039 }
            r0 = 0
            java.nio.file.LinkOption[] r0 = new java.nio.file.LinkOption[r0]     // Catch:{ all -> 0x0039 }
            r1.create((org.apache.commons.compress.archivers.ArchiveOutputStream) r2, (java.nio.file.Path) r4, (java.util.EnumSet<java.nio.file.FileVisitOption>) r3, (java.nio.file.LinkOption[]) r0)     // Catch:{ all -> 0x0039 }
            r2.close()
        L_0x0038:
            return
        L_0x0039:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x003b }
        L_0x003b:
            r3 = move-exception
            r2.close()     // Catch:{ all -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x0044:
            throw r3
        L_0x0045:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Archiver.create(java.lang.String, java.nio.channels.SeekableByteChannel, java.nio.file.Path):void");
    }

    private boolean prefersSeekableByteChannel(String str) {
        return ArchiveStreamFactory.ZIP.equalsIgnoreCase(str) || ArchiveStreamFactory.SEVEN_Z.equalsIgnoreCase(str);
    }
}
