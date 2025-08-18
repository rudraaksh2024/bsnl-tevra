package org.apache.commons.io.file.spi;

import com.google.android.gms.common.internal.ImagesContract;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;
import java.util.Objects;

public class FileSystemProviders {
    private static final FileSystemProviders INSTALLED = new FileSystemProviders(FileSystemProvider.installedProviders());
    private final List<FileSystemProvider> providers;

    public static FileSystemProvider getFileSystemProvider(Path path) {
        return ((Path) Objects.requireNonNull(path, "path")).getFileSystem().provider();
    }

    public static FileSystemProviders installed() {
        return INSTALLED;
    }

    private FileSystemProviders(List<FileSystemProvider> list) {
        this.providers = list;
    }

    public FileSystemProvider getFileSystemProvider(String str) {
        Objects.requireNonNull(str, "scheme");
        if (str.equalsIgnoreCase("file")) {
            return FileSystems.getDefault().provider();
        }
        List<FileSystemProvider> list = this.providers;
        if (list == null) {
            return null;
        }
        for (FileSystemProvider next : list) {
            if (next.getScheme().equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    public FileSystemProvider getFileSystemProvider(URI uri) {
        return getFileSystemProvider(((URI) Objects.requireNonNull(uri, "uri")).getScheme());
    }

    public FileSystemProvider getFileSystemProvider(URL url) {
        return getFileSystemProvider(((URL) Objects.requireNonNull(url, ImagesContract.URL)).getProtocol());
    }
}
