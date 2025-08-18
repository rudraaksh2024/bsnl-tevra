package org.apache.xmlbeans.impl.validator;

import java.util.Collection;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlWhitespace;

public class ValidatorUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private static class EventImpl implements ValidatorListener.Event {
        PrefixResolver _prefixResolver;
        String _text;

        public Location getLocation() {
            return null;
        }

        public XmlCursor getLocationAsCursor() {
            return null;
        }

        public QName getName() {
            return null;
        }

        public String getXsiLoc() {
            return null;
        }

        public String getXsiNil() {
            return null;
        }

        public String getXsiNoLoc() {
            return null;
        }

        public String getXsiType() {
            return null;
        }

        public boolean textIsWhitespace() {
            return false;
        }

        EventImpl(PrefixResolver prefixResolver, String str) {
            this._prefixResolver = prefixResolver;
            this._text = str;
        }

        public String getText() {
            return this._text;
        }

        public String getText(int i) {
            return XmlWhitespace.collapse(this._text, i);
        }

        public String getNamespaceForPrefix(String str) {
            return this._prefixResolver.getNamespaceForPrefix(str);
        }
    }

    public static boolean validateSimpleType(SchemaType schemaType, String str, Collection collection, PrefixResolver prefixResolver) {
        if (schemaType.isSimpleType() || schemaType.getContentType() == 2) {
            Validator validator = new Validator(schemaType, (SchemaField) null, schemaType.getTypeSystem(), (XmlOptions) null, collection);
            EventImpl eventImpl = new EventImpl(prefixResolver, str);
            validator.nextEvent(1, eventImpl);
            validator.nextEvent(3, eventImpl);
            validator.nextEvent(2, eventImpl);
            return validator.isValid();
        }
        throw new RuntimeException("Not a simple type");
    }
}
