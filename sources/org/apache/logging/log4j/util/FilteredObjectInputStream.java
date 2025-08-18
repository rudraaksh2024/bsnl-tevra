package org.apache.logging.log4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FilteredObjectInputStream extends ObjectInputStream {
    private static final Set<String> REQUIRED_JAVA_CLASSES = new HashSet(Arrays.asList(new String[]{"java.math.BigDecimal", "java.math.BigInteger", "java.rmi.MarshalledObject", "[B"}));
    private static final Set<String> REQUIRED_JAVA_PACKAGES = new HashSet(Arrays.asList(new String[]{"java.lang.", "java.time.", "java.util.", "org.apache.logging.log4j.", "[Lorg.apache.logging.log4j."}));
    private final Collection<String> allowedExtraClasses;

    public FilteredObjectInputStream() throws IOException, SecurityException {
        this.allowedExtraClasses = Collections.emptySet();
    }

    public FilteredObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
        this.allowedExtraClasses = Collections.emptySet();
    }

    public FilteredObjectInputStream(Collection<String> collection) throws IOException, SecurityException {
        this.allowedExtraClasses = collection;
    }

    public FilteredObjectInputStream(InputStream inputStream, Collection<String> collection) throws IOException {
        super(inputStream);
        this.allowedExtraClasses = collection;
    }

    public Collection<String> getAllowedClasses() {
        return this.allowedExtraClasses;
    }

    /* access modifiers changed from: protected */
    public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        String name = objectStreamClass.getName();
        if (isAllowedByDefault(name) || this.allowedExtraClasses.contains(name)) {
            return super.resolveClass(objectStreamClass);
        }
        throw new InvalidObjectException("Class is not allowed for deserialization: " + name);
    }

    private static boolean isAllowedByDefault(String str) {
        return isRequiredPackage(str) || REQUIRED_JAVA_CLASSES.contains(str);
    }

    private static boolean isRequiredPackage(String str) {
        for (String startsWith : REQUIRED_JAVA_PACKAGES) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }
}
