package org.apache.commons.collections4.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Properties;

public abstract class AbstractPropertiesFactory<T extends Properties> {
    /* access modifiers changed from: protected */
    public abstract T createProperties();

    protected AbstractPropertiesFactory() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        if (r1 != null) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T load(java.lang.ClassLoader r1, java.lang.String r2) throws java.io.IOException {
        /*
            r0 = this;
            java.io.InputStream r1 = r1.getResourceAsStream(r2)
            java.util.Properties r0 = r0.load((java.io.InputStream) r1)     // Catch:{ all -> 0x000e }
            if (r1 == 0) goto L_0x000d
            r1.close()
        L_0x000d:
            return r0
        L_0x000e:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r2 = move-exception
            if (r1 == 0) goto L_0x001b
            r1.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r1 = move-exception
            r0.addSuppressed(r1)
        L_0x001b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.properties.AbstractPropertiesFactory.load(java.lang.ClassLoader, java.lang.String):java.util.Properties");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T load(java.io.File r2) throws java.io.FileNotFoundException, java.io.IOException {
        /*
            r1 = this;
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r2)
            java.util.Properties r1 = r1.load((java.io.InputStream) r0)     // Catch:{ all -> 0x000d }
            r0.close()
            return r1
        L_0x000d:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x000f }
        L_0x000f:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0014 }
            goto L_0x0018
        L_0x0014:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0018:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.properties.AbstractPropertiesFactory.load(java.io.File):java.util.Properties");
    }

    public T load(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        T createProperties = createProperties();
        createProperties.load(inputStream);
        return createProperties;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r2 != null) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T load(java.nio.file.Path r2) throws java.io.IOException {
        /*
            r1 = this;
            r0 = 0
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.io.InputStream r2 = java.nio.file.Files.newInputStream(r2, r0)
            java.util.Properties r1 = r1.load((java.io.InputStream) r2)     // Catch:{ all -> 0x0011 }
            if (r2 == 0) goto L_0x0010
            r2.close()
        L_0x0010:
            return r1
        L_0x0011:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r0 = move-exception
            if (r2 == 0) goto L_0x001e
            r2.close()     // Catch:{ all -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x001e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.properties.AbstractPropertiesFactory.load(java.nio.file.Path):java.util.Properties");
    }

    public T load(Reader reader) throws IOException {
        T createProperties = createProperties();
        createProperties.load(reader);
        return createProperties;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T load(java.lang.String r2) throws java.io.IOException {
        /*
            r1 = this;
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r2)
            java.util.Properties r1 = r1.load((java.io.InputStream) r0)     // Catch:{ all -> 0x000d }
            r0.close()
            return r1
        L_0x000d:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x000f }
        L_0x000f:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0014 }
            goto L_0x0018
        L_0x0014:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0018:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.properties.AbstractPropertiesFactory.load(java.lang.String):java.util.Properties");
    }

    public T load(URI uri) throws IOException {
        return load(Paths.get(uri));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        if (r2 != null) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T load(java.net.URL r2) throws java.io.IOException {
        /*
            r1 = this;
            java.io.InputStream r2 = r2.openStream()
            java.util.Properties r1 = r1.load((java.io.InputStream) r2)     // Catch:{ all -> 0x000e }
            if (r2 == 0) goto L_0x000d
            r2.close()
        L_0x000d:
            return r1
        L_0x000e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r0 = move-exception
            if (r2 == 0) goto L_0x001b
            r2.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x001b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.properties.AbstractPropertiesFactory.load(java.net.URL):java.util.Properties");
    }
}
