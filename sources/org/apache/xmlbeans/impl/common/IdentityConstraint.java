package org.apache.xmlbeans.impl.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlIDREF;
import org.apache.xmlbeans.XmlIDREFS;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.xpath.XPath;
import org.apache.xmlbeans.impl.xpath.XPathExecutionContext;

public class IdentityConstraint {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    /* access modifiers changed from: private */
    public ConstraintState _constraintStack;
    /* access modifiers changed from: private */
    public ElementState _elementStack;
    private Collection _errorListener;
    private boolean _invalid;
    private boolean _trackIdrefs;

    public IdentityConstraint(Collection collection, boolean z) {
        this._errorListener = collection;
        this._trackIdrefs = z;
    }

    public void element(ValidatorListener.Event event, SchemaType schemaType, SchemaIdentityConstraint[] schemaIdentityConstraintArr) {
        newState();
        for (ConstraintState constraintState = this._constraintStack; constraintState != null; constraintState = constraintState._next) {
            constraintState.element(event, schemaType);
        }
        int i = 0;
        while (schemaIdentityConstraintArr != null && i < schemaIdentityConstraintArr.length) {
            newConstraintState(schemaIdentityConstraintArr[i], event, schemaType);
            i++;
        }
    }

    public void endElement(ValidatorListener.Event event) {
        if (this._elementStack._hasConstraints) {
            ConstraintState constraintState = this._constraintStack;
            while (constraintState != null && constraintState != this._elementStack._savePoint) {
                constraintState.remove(event);
                constraintState = constraintState._next;
            }
            this._constraintStack = this._elementStack._savePoint;
        }
        this._elementStack = this._elementStack._next;
        for (ConstraintState constraintState2 = this._constraintStack; constraintState2 != null; constraintState2 = constraintState2._next) {
            constraintState2.endElement(event);
        }
    }

    public void attr(ValidatorListener.Event event, QName qName, SchemaType schemaType, String str) {
        for (ConstraintState constraintState = this._constraintStack; constraintState != null; constraintState = constraintState._next) {
            constraintState.attr(event, qName, schemaType, str);
        }
    }

    public void text(ValidatorListener.Event event, SchemaType schemaType, String str, boolean z) {
        for (ConstraintState constraintState = this._constraintStack; constraintState != null; constraintState = constraintState._next) {
            constraintState.text(event, schemaType, str, z);
        }
    }

    public boolean isValid() {
        return !this._invalid;
    }

    private void newConstraintState(SchemaIdentityConstraint schemaIdentityConstraint, ValidatorListener.Event event, SchemaType schemaType) {
        if (schemaIdentityConstraint.getConstraintCategory() == 2) {
            new KeyrefState(schemaIdentityConstraint, event, schemaType);
        } else {
            new SelectorState(schemaIdentityConstraint, event, schemaType);
        }
    }

    private void buildIdStates() {
        IdState idState = new IdState();
        if (this._trackIdrefs) {
            new IdRefState(idState);
        }
    }

    private void newState() {
        boolean z = this._elementStack == null;
        ElementState elementState = new ElementState();
        elementState._next = this._elementStack;
        this._elementStack = elementState;
        if (z) {
            buildIdStates();
        }
    }

    /* access modifiers changed from: private */
    public void emitError(ValidatorListener.Event event, String str, Object[] objArr) {
        this._invalid = true;
        Collection collection = this._errorListener;
        if (collection != null) {
            collection.add(errorForEvent(str, objArr, 0, event));
        }
    }

    public static XmlError errorForEvent(String str, Object[] objArr, int i, ValidatorListener.Event event) {
        XmlCursor locationAsCursor = event.getLocationAsCursor();
        if (locationAsCursor != null) {
            return XmlError.forCursor(str, objArr, i, locationAsCursor);
        }
        Location location = event.getLocation();
        if (location == null) {
            return XmlError.forMessage(str, objArr, i);
        }
        return XmlError.forLocation(str, objArr, i, location.getSystemId(), location.getLineNumber(), location.getColumnNumber(), location.getCharacterOffset());
    }

    /* access modifiers changed from: private */
    public void emitError(ValidatorListener.Event event, String str) {
        this._invalid = true;
        Collection collection = this._errorListener;
        if (collection != null) {
            collection.add(errorForEvent(str, 0, event));
        }
    }

    public static XmlError errorForEvent(String str, int i, ValidatorListener.Event event) {
        XmlCursor locationAsCursor = event.getLocationAsCursor();
        if (locationAsCursor != null) {
            return XmlError.forCursor(str, i, locationAsCursor);
        }
        Location location = event.getLocation();
        if (location == null) {
            return XmlError.forMessage(str, i);
        }
        return XmlError.forLocation(str, i, location.getSystemId(), location.getLineNumber(), location.getColumnNumber(), location.getCharacterOffset());
    }

    /* access modifiers changed from: private */
    public void setSavePoint(ConstraintState constraintState) {
        if (!this._elementStack._hasConstraints) {
            this._elementStack._savePoint = constraintState;
        }
        this._elementStack._hasConstraints = true;
    }

    /* access modifiers changed from: private */
    public static XmlObject newValue(SchemaType schemaType, String str) {
        try {
            return schemaType.newValue(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    static SchemaType getSimpleType(SchemaType schemaType) {
        while (!schemaType.isSimpleType()) {
            schemaType = schemaType.getBaseType();
        }
        return schemaType;
    }

    static boolean hasSimpleContent(SchemaType schemaType) {
        return schemaType.isSimpleType() || schemaType.getContentType() == 2;
    }

    public abstract class ConstraintState {
        ConstraintState _next;

        /* access modifiers changed from: package-private */
        public abstract void attr(ValidatorListener.Event event, QName qName, SchemaType schemaType, String str);

        /* access modifiers changed from: package-private */
        public abstract void element(ValidatorListener.Event event, SchemaType schemaType);

        /* access modifiers changed from: package-private */
        public abstract void endElement(ValidatorListener.Event event);

        /* access modifiers changed from: package-private */
        public abstract void remove(ValidatorListener.Event event);

        /* access modifiers changed from: package-private */
        public abstract void text(ValidatorListener.Event event, SchemaType schemaType, String str, boolean z);

        ConstraintState() {
            IdentityConstraint.this.setSavePoint(IdentityConstraint.this._constraintStack);
            this._next = IdentityConstraint.this._constraintStack;
            ConstraintState unused = IdentityConstraint.this._constraintStack = this;
        }
    }

    public class SelectorState extends ConstraintState {
        SchemaIdentityConstraint _constraint;
        XPathExecutionContext _context;
        Set _values = new LinkedHashSet();

        /* access modifiers changed from: package-private */
        public void attr(ValidatorListener.Event event, QName qName, SchemaType schemaType, String str) {
        }

        /* access modifiers changed from: package-private */
        public void text(ValidatorListener.Event event, SchemaType schemaType, String str, boolean z) {
        }

        SelectorState(SchemaIdentityConstraint schemaIdentityConstraint, ValidatorListener.Event event, SchemaType schemaType) {
            super();
            this._constraint = schemaIdentityConstraint;
            XPathExecutionContext xPathExecutionContext = new XPathExecutionContext();
            this._context = xPathExecutionContext;
            xPathExecutionContext.init((XPath) this._constraint.getSelectorPath());
            if ((this._context.start() & 1) != 0) {
                createFieldState(event, schemaType);
            }
        }

        /* access modifiers changed from: package-private */
        public void addFields(XmlObjectList xmlObjectList, ValidatorListener.Event event) {
            if (this._constraint.getConstraintCategory() == 2) {
                this._values.add(xmlObjectList);
            } else if (!this._values.contains(xmlObjectList)) {
                this._values.add(xmlObjectList);
            } else if (this._constraint.getConstraintCategory() == 3) {
                IdentityConstraint.this.emitError(event, XmlErrorCodes.IDENTITY_CONSTRAINT_VALID$DUPLICATE_UNIQUE, new Object[]{xmlObjectList, QNameHelper.pretty(this._constraint.getName())});
            } else {
                IdentityConstraint.this.emitError(event, XmlErrorCodes.IDENTITY_CONSTRAINT_VALID$DUPLICATE_KEY, new Object[]{xmlObjectList, QNameHelper.pretty(this._constraint.getName())});
            }
        }

        /* access modifiers changed from: package-private */
        public void element(ValidatorListener.Event event, SchemaType schemaType) {
            if ((this._context.element(event.getName()) & 1) != 0) {
                createFieldState(event, schemaType);
            }
        }

        /* access modifiers changed from: package-private */
        public void endElement(ValidatorListener.Event event) {
            this._context.end();
        }

        /* access modifiers changed from: package-private */
        public void createFieldState(ValidatorListener.Event event, SchemaType schemaType) {
            new FieldState(this, event, schemaType);
        }

        /* access modifiers changed from: package-private */
        public void remove(ValidatorListener.Event event) {
            for (ConstraintState constraintState = this._next; constraintState != null; constraintState = constraintState._next) {
                if (constraintState instanceof KeyrefState) {
                    KeyrefState keyrefState = (KeyrefState) constraintState;
                    if (keyrefState._constraint.getReferencedKey() == this._constraint) {
                        keyrefState.addKeyValues(this._values, true);
                    }
                }
            }
        }
    }

    public class KeyrefState extends SelectorState {
        private Object CHILD_ADDED = new Object();
        private Object CHILD_REMOVED = new Object();
        private Object SELF_ADDED = new Object();
        Map _keyValues = new HashMap();

        KeyrefState(SchemaIdentityConstraint schemaIdentityConstraint, ValidatorListener.Event event, SchemaType schemaType) {
            super(schemaIdentityConstraint, event, schemaType);
        }

        /* access modifiers changed from: package-private */
        public void addKeyValues(Set set, boolean z) {
            for (Object next : set) {
                Object obj = this._keyValues.get(next);
                if (obj == null) {
                    this._keyValues.put(next, z ? this.CHILD_ADDED : this.SELF_ADDED);
                } else if (obj == this.CHILD_ADDED) {
                    if (z) {
                        this._keyValues.put(next, this.CHILD_REMOVED);
                    } else {
                        this._keyValues.put(next, this.SELF_ADDED);
                    }
                } else if (obj == this.CHILD_REMOVED && !z) {
                    this._keyValues.put(next, this.SELF_ADDED);
                }
            }
        }

        private boolean hasKeyValue(Object obj) {
            Object obj2 = this._keyValues.get(obj);
            return (obj2 == null || obj2 == this.CHILD_REMOVED) ? false : true;
        }

        /* access modifiers changed from: package-private */
        public void remove(ValidatorListener.Event event) {
            ConstraintState constraintState = this._next;
            while (constraintState != null && constraintState != IdentityConstraint.this._elementStack._savePoint) {
                if (constraintState instanceof SelectorState) {
                    SelectorState selectorState = (SelectorState) constraintState;
                    if (selectorState._constraint == this._constraint.getReferencedKey()) {
                        addKeyValues(selectorState._values, false);
                    }
                }
                constraintState = constraintState._next;
            }
            for (XmlObjectList xmlObjectList : this._values) {
                if (xmlObjectList.unfilled() < 0 && !hasKeyValue(xmlObjectList)) {
                    IdentityConstraint.this.emitError(event, XmlErrorCodes.IDENTITY_CONSTRAINT_VALID$KEYREF_KEY_NOT_FOUND, new Object[]{xmlObjectList, QNameHelper.pretty(this._constraint.getName())});
                    return;
                }
            }
        }
    }

    public class FieldState extends ConstraintState {
        XPathExecutionContext[] _contexts;
        boolean[] _needsValue;
        SelectorState _selector;
        XmlObjectList _value;

        FieldState(SelectorState selectorState, ValidatorListener.Event event, SchemaType schemaType) {
            super();
            this._selector = selectorState;
            SchemaIdentityConstraint schemaIdentityConstraint = selectorState._constraint;
            int length = schemaIdentityConstraint.getFields().length;
            this._contexts = new XPathExecutionContext[length];
            this._needsValue = new boolean[length];
            this._value = new XmlObjectList(length);
            for (int i = 0; i < length; i++) {
                this._contexts[i] = new XPathExecutionContext();
                this._contexts[i].init((XPath) schemaIdentityConstraint.getFieldPath(i));
                if ((this._contexts[i].start() & 1) != 0) {
                    if (!IdentityConstraint.hasSimpleContent(schemaType)) {
                        IdentityConstraint.this.emitError(event, "Identity constraint field must have simple content");
                    } else {
                        this._needsValue[i] = true;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void element(ValidatorListener.Event event, SchemaType schemaType) {
            int i = 0;
            for (int i2 = 0; i2 < this._contexts.length; i2++) {
                if (this._needsValue[i2]) {
                    IdentityConstraint.this.emitError(event, "Identity constraint field must have simple content");
                    this._needsValue[i2] = false;
                }
            }
            while (true) {
                XPathExecutionContext[] xPathExecutionContextArr = this._contexts;
                if (i < xPathExecutionContextArr.length) {
                    if ((xPathExecutionContextArr[i].element(event.getName()) & 1) != 0) {
                        if (!IdentityConstraint.hasSimpleContent(schemaType)) {
                            IdentityConstraint.this.emitError(event, "Identity constraint field must have simple content");
                        } else {
                            this._needsValue[i] = true;
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void attr(ValidatorListener.Event event, QName qName, SchemaType schemaType, String str) {
            if (str != null) {
                int i = 0;
                while (true) {
                    XPathExecutionContext[] xPathExecutionContextArr = this._contexts;
                    if (i < xPathExecutionContextArr.length) {
                        if (xPathExecutionContextArr[i].attr(qName)) {
                            XmlObject access$600 = IdentityConstraint.newValue(schemaType, str);
                            if (access$600 != null) {
                                if (!this._value.set(access$600, i)) {
                                    IdentityConstraint.this.emitError(event, "Multiple instances of field with xpath: '" + this._selector._constraint.getFields()[i] + "' for a selector");
                                }
                            } else {
                                return;
                            }
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void text(ValidatorListener.Event event, SchemaType schemaType, String str, boolean z) {
            if (str != null || z) {
                for (int i = 0; i < this._contexts.length; i++) {
                    if (this._needsValue[i]) {
                        if (z || !IdentityConstraint.hasSimpleContent(schemaType)) {
                            IdentityConstraint.this.emitError(event, "Identity constraint field must have simple content");
                            return;
                        }
                        XmlObject access$600 = IdentityConstraint.newValue(IdentityConstraint.getSimpleType(schemaType), str);
                        if (access$600 != null) {
                            if (!this._value.set(access$600, i)) {
                                IdentityConstraint.this.emitError(event, "Multiple instances of field with xpath: '" + this._selector._constraint.getFields()[i] + "' for a selector");
                            }
                        } else {
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void endElement(ValidatorListener.Event event) {
            for (int i = 0; i < this._needsValue.length; i++) {
                this._contexts[i].end();
                this._needsValue[i] = false;
            }
        }

        /* access modifiers changed from: package-private */
        public void remove(ValidatorListener.Event event) {
            if (this._selector._constraint.getConstraintCategory() != 1 || this._value.unfilled() < 0) {
                this._selector.addFields(this._value, event);
            } else {
                IdentityConstraint.this.emitError(event, "Key " + QNameHelper.pretty(this._selector._constraint.getName()) + " is missing field with xpath: '" + this._selector._constraint.getFields()[this._value.unfilled()] + "'");
            }
        }
    }

    public class IdState extends ConstraintState {
        Set _values = new LinkedHashSet();

        /* access modifiers changed from: package-private */
        public void element(ValidatorListener.Event event, SchemaType schemaType) {
        }

        /* access modifiers changed from: package-private */
        public void endElement(ValidatorListener.Event event) {
        }

        /* access modifiers changed from: package-private */
        public void remove(ValidatorListener.Event event) {
        }

        IdState() {
            super();
        }

        /* access modifiers changed from: package-private */
        public void attr(ValidatorListener.Event event, QName qName, SchemaType schemaType, String str) {
            handleValue(event, schemaType, str);
        }

        /* access modifiers changed from: package-private */
        public void text(ValidatorListener.Event event, SchemaType schemaType, String str, boolean z) {
            if (!z) {
                handleValue(event, schemaType, str);
            }
        }

        private void handleValue(ValidatorListener.Event event, SchemaType schemaType, String str) {
            if (str != null && schemaType != null && !schemaType.isNoType() && XmlID.type.isAssignableFrom(schemaType)) {
                XmlObjectList xmlObjectList = new XmlObjectList(1);
                XmlObject access$600 = IdentityConstraint.newValue(XmlID.type, str);
                if (access$600 != null) {
                    xmlObjectList.set(access$600, 0);
                    if (this._values.contains(xmlObjectList)) {
                        IdentityConstraint.this.emitError(event, XmlErrorCodes.ID_VALID$DUPLICATE, new Object[]{str});
                        return;
                    }
                    this._values.add(xmlObjectList);
                }
            }
        }
    }

    public class IdRefState extends ConstraintState {
        IdState _ids;
        List _values = new ArrayList();

        /* access modifiers changed from: package-private */
        public void element(ValidatorListener.Event event, SchemaType schemaType) {
        }

        /* access modifiers changed from: package-private */
        public void endElement(ValidatorListener.Event event) {
        }

        IdRefState(IdState idState) {
            super();
            this._ids = idState;
        }

        private void handleValue(ValidatorListener.Event event, SchemaType schemaType, String str) {
            if (str != null && schemaType != null && !schemaType.isNoType()) {
                if (XmlIDREFS.type.isAssignableFrom(schemaType)) {
                    XmlIDREFS xmlIDREFS = (XmlIDREFS) IdentityConstraint.newValue(XmlIDREFS.type, str);
                    if (xmlIDREFS != null) {
                        List xgetListValue = xmlIDREFS.xgetListValue();
                        for (int i = 0; i < xgetListValue.size(); i++) {
                            XmlObjectList xmlObjectList = new XmlObjectList(1);
                            xmlObjectList.set((XmlIDREF) xgetListValue.get(i), 0);
                            this._values.add(xmlObjectList);
                        }
                    }
                } else if (XmlIDREF.type.isAssignableFrom(schemaType)) {
                    XmlObjectList xmlObjectList2 = new XmlObjectList(1);
                    XmlIDREF xmlIDREF = (XmlIDREF) schemaType.newValue(str);
                    if (xmlIDREF != null) {
                        xmlObjectList2.set(xmlIDREF, 0);
                        this._values.add(xmlObjectList2);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void attr(ValidatorListener.Event event, QName qName, SchemaType schemaType, String str) {
            handleValue(event, schemaType, str);
        }

        /* access modifiers changed from: package-private */
        public void text(ValidatorListener.Event event, SchemaType schemaType, String str, boolean z) {
            if (!z) {
                handleValue(event, schemaType, str);
            }
        }

        /* access modifiers changed from: package-private */
        public void remove(ValidatorListener.Event event) {
            for (Object next : this._values) {
                if (!this._ids._values.contains(next)) {
                    IdentityConstraint.this.emitError(event, "ID not found for IDRef value '" + next + "'");
                }
            }
        }
    }

    private static class ElementState {
        boolean _hasConstraints;
        ElementState _next;
        ConstraintState _savePoint;

        private ElementState() {
        }
    }
}
