package org.apache.logging.log4j.message;

import java.util.ResourceBundle;

public class LocalizedMessageFactory extends AbstractMessageFactory {
    private static final long serialVersionUID = -1996295808703146741L;
    private final String baseName;
    private final transient ResourceBundle resourceBundle;

    public LocalizedMessageFactory(ResourceBundle resourceBundle2) {
        this.resourceBundle = resourceBundle2;
        this.baseName = null;
    }

    public LocalizedMessageFactory(String str) {
        this.resourceBundle = null;
        this.baseName = str;
    }

    public String getBaseName() {
        return this.baseName;
    }

    public ResourceBundle getResourceBundle() {
        return this.resourceBundle;
    }

    public Message newMessage(String str) {
        if (this.resourceBundle == null) {
            return new LocalizedMessage(this.baseName, (Object) str);
        }
        return new LocalizedMessage(this.resourceBundle, str);
    }

    public Message newMessage(String str, Object... objArr) {
        if (this.resourceBundle == null) {
            return new LocalizedMessage(this.baseName, str, objArr);
        }
        return new LocalizedMessage(this.resourceBundle, str, objArr);
    }
}
