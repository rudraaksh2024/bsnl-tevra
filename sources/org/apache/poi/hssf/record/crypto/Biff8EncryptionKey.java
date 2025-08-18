package org.apache.poi.hssf.record.crypto;

public final class Biff8EncryptionKey {
    private static final ThreadLocal<String> _userPasswordTLS = new ThreadLocal<>();

    public static void setCurrentUserPassword(String str) {
        if (str == null) {
            _userPasswordTLS.remove();
        } else {
            _userPasswordTLS.set(str);
        }
    }

    public static String getCurrentUserPassword() {
        return _userPasswordTLS.get();
    }
}
