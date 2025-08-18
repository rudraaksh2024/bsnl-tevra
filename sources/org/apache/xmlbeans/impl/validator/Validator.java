package org.apache.xmlbeans.impl.validator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlValidationError;
import org.apache.xmlbeans.impl.common.IdentityConstraint;
import org.apache.xmlbeans.impl.common.InvalidLexicalValueException;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl;
import org.apache.xmlbeans.impl.util.XsTypeConverter;
import org.apache.xmlbeans.impl.values.JavaBase64HolderEx;
import org.apache.xmlbeans.impl.values.JavaBooleanHolderEx;
import org.apache.xmlbeans.impl.values.JavaDecimalHolderEx;
import org.apache.xmlbeans.impl.values.JavaDoubleHolderEx;
import org.apache.xmlbeans.impl.values.JavaFloatHolderEx;
import org.apache.xmlbeans.impl.values.JavaHexBinaryHolderEx;
import org.apache.xmlbeans.impl.values.JavaNotationHolderEx;
import org.apache.xmlbeans.impl.values.JavaQNameHolderEx;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.JavaUriHolderEx;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;
import org.apache.xmlbeans.impl.values.XmlDateImpl;
import org.apache.xmlbeans.impl.values.XmlDurationImpl;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public final class Validator implements ValidatorListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean _booleanValue;
    private byte[] _byteArrayValue;
    private final IdentityConstraint _constraintEngine;
    private BigDecimal _decimalValue;
    private double _doubleValue;
    private int _eatContent;
    private Collection<XmlError> _errorListener;
    private int _errorState;
    private float _floatValue;
    private GDate _gdateValue;
    private GDuration _gdurationValue;
    private final SchemaTypeLoader _globalTypes;
    private boolean _invalid;
    private List<SchemaType> _listTypes;
    private List<Object> _listValue;
    private SchemaLocalAttribute _localAttribute;
    private SchemaLocalElement _localElement;
    private QName _qnameValue;
    private final SchemaField _rootField;
    private final SchemaType _rootType;
    private State _stateStack;
    private final boolean _strict;
    private String _stringValue;
    private int _suspendErrors;
    private final boolean _treatLaxAsSkip;
    private SchemaType _unionType;
    private final ValidatorVC _vc;
    private final LinkedList<TypeStoreVisitor> _visitorPool = new LinkedList<>();
    private SchemaAttributeModel _wildcardAttribute;
    private SchemaParticle _wildcardElement;

    public Validator(SchemaType schemaType, SchemaField schemaField, SchemaTypeLoader schemaTypeLoader, XmlOptions xmlOptions, Collection<XmlError> collection) {
        XmlOptions maskNull = XmlOptions.maskNull(xmlOptions);
        this._errorListener = maskNull.getErrorListener();
        this._treatLaxAsSkip = maskNull.isValidateTreatLaxAsSkip();
        this._strict = maskNull.isValidateStrict();
        if (this._errorListener == null) {
            this._errorListener = collection;
        }
        this._constraintEngine = new IdentityConstraint(this._errorListener, schemaType.isDocumentType());
        this._globalTypes = schemaTypeLoader;
        this._rootType = schemaType;
        this._rootField = schemaField;
        this._vc = new ValidatorVC();
    }

    private class ValidatorVC implements ValidationContext {
        ValidatorListener.Event _event;

        private ValidatorVC() {
        }

        public void invalid(String str) {
            Validator.this.emitError(this._event, str, (QName) null, (SchemaType) null, 1001);
        }

        public void invalid(String str, Object[] objArr) {
            Validator.this.emitError(this._event, str, objArr, (QName) null, (SchemaType) null, 1001, (SchemaType) null);
        }
    }

    public boolean isValid() {
        return !this._invalid && this._constraintEngine.isValid();
    }

    /* access modifiers changed from: private */
    public void emitError(ValidatorListener.Event event, String str, QName qName, SchemaType schemaType, int i) {
        emitError(event, str, (String) null, (Object[]) null, 0, (QName) null, qName, schemaType, (List<QName>) null, i, (SchemaType) null);
    }

    /* access modifiers changed from: private */
    public void emitError(ValidatorListener.Event event, String str, Object[] objArr, QName qName, SchemaType schemaType, int i, SchemaType schemaType2) {
        emitError(event, (String) null, str, objArr, 0, (QName) null, qName, schemaType, (List<QName>) null, i, schemaType2);
    }

    private void emitError(ValidatorListener.Event event, String str, String str2, Object[] objArr, int i, QName qName, QName qName2, SchemaType schemaType, List<QName> list, int i2, SchemaType schemaType2) {
        XmlValidationError xmlValidationError;
        this._errorState++;
        if (this._suspendErrors == 0) {
            if (i == 0) {
                this._invalid = true;
            }
            if (this._errorListener != null) {
                XmlCursor locationAsCursor = event.getLocationAsCursor();
                if (locationAsCursor != null) {
                    xmlValidationError = XmlValidationError.forCursorWithDetails(str, str2, objArr, i, locationAsCursor, qName, qName2, schemaType, list, i2, schemaType2);
                } else {
                    xmlValidationError = XmlValidationError.forLocationWithDetails(str, str2, objArr, i, event.getLocation(), qName, qName2, schemaType, list, i2, schemaType2);
                }
                this._errorListener.add(xmlValidationError);
            }
        }
    }

    private void emitFieldError(ValidatorListener.Event event, String str, Object[] objArr, QName qName, SchemaType schemaType, List<QName> list, int i, SchemaType schemaType2) {
        State state = this._stateStack;
        emitError(event, (String) null, str, objArr, 0, (state == null || state._field == null) ? null : this._stateStack._field.getName(), qName, schemaType, list, i, schemaType2);
    }

    public void nextEvent(int i, ValidatorListener.Event event) {
        resetValues();
        int i2 = this._eatContent;
        if (i2 > 0) {
            if (i == 1) {
                this._eatContent = i2 + 1;
            } else if (i == 2) {
                this._eatContent = i2 - 1;
            }
        } else if (i == 1) {
            beginEvent(event);
        } else if (i == 2) {
            endEvent(event);
        } else if (i == 3) {
            textEvent(event);
        } else if (i == 4) {
            attrEvent(event);
        } else if (i == 5) {
            endAttrsEvent(event);
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: org.apache.xmlbeans.SchemaIdentityConstraint[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: org.apache.xmlbeans.SchemaIdentityConstraint[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v4, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: javax.xml.namespace.QName} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: javax.xml.namespace.QName} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: javax.xml.namespace.QName} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v13, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v14, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v15, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v16, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v17, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v18, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v19, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v21, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v22, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v23, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v24, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v25, resolved type: org.apache.xmlbeans.SchemaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v78, resolved type: org.apache.xmlbeans.SchemaGlobalElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v48, resolved type: org.apache.xmlbeans.SchemaGlobalElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: org.apache.xmlbeans.SchemaIdentityConstraint[]} */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:62|63|64) */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        r10._errorState++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0172, code lost:
        r10._suspendErrors--;
        r6 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01b2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01b3, code lost:
        r10._suspendErrors--;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01b8, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:62:0x016d */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void beginEvent(org.apache.xmlbeans.impl.common.ValidatorListener.Event r19) {
        /*
            r18 = this;
            r10 = r18
            r0 = r19
            r11 = 0
            r10._localElement = r11
            r10._wildcardElement = r11
            org.apache.xmlbeans.impl.validator.Validator$State r12 = r18.topState()
            r13 = 2
            r14 = 0
            r15 = 1
            if (r12 != 0) goto L_0x001a
            org.apache.xmlbeans.SchemaType r1 = r10._rootType
            org.apache.xmlbeans.SchemaField r2 = r10._rootField
        L_0x0016:
            r9 = r1
            r11 = r2
            goto L_0x0127
        L_0x001a:
            javax.xml.namespace.QName r9 = r19.getName()
            r12._isEmpty = r14
            boolean r1 = r12._isNil
            if (r1 == 0) goto L_0x003d
            java.lang.String r3 = "cvc-elt.3.2.1"
            r4 = 0
            org.apache.xmlbeans.SchemaField r1 = r12._field
            javax.xml.namespace.QName r5 = r1.getName()
            org.apache.xmlbeans.SchemaType r6 = r12._type
            r7 = 0
            r8 = 4
            org.apache.xmlbeans.SchemaType r9 = r12._type
            r1 = r18
            r2 = r19
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            return
        L_0x003d:
            org.apache.xmlbeans.SchemaField r1 = r12._field
            if (r1 == 0) goto L_0x0072
            org.apache.xmlbeans.SchemaField r1 = r12._field
            boolean r1 = r1.isFixed()
            if (r1 == 0) goto L_0x0072
            java.lang.String r3 = "cvc-elt.5.2.2.1"
            java.lang.Object[] r4 = new java.lang.Object[r15]
            org.apache.xmlbeans.SchemaField r1 = r12._field
            javax.xml.namespace.QName r1 = r1.getName()
            java.lang.String r1 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r1)
            r4[r14] = r1
            org.apache.xmlbeans.SchemaField r1 = r12._field
            javax.xml.namespace.QName r5 = r1.getName()
            org.apache.xmlbeans.SchemaType r6 = r12._type
            r7 = 0
            r8 = 2
            org.apache.xmlbeans.SchemaType r2 = r12._type
            r1 = r18
            r16 = r2
            r2 = r19
            r11 = r9
            r9 = r16
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0073
        L_0x0072:
            r11 = r9
        L_0x0073:
            boolean r1 = r12.visit(r11)
            if (r1 != 0) goto L_0x007f
            r10.findDetailedErrorBegin(r0, r12, r11)
            r10._eatContent = r15
            return
        L_0x007f:
            org.apache.xmlbeans.SchemaParticle r1 = r12.currentParticle()
            r10._wildcardElement = r1
            int r2 = r1.getParticleType()
            r3 = 5
            if (r2 != r3) goto L_0x00e8
            org.apache.xmlbeans.QNameSet r2 = r1.getWildcardSet()
            boolean r2 = r2.contains(r11)
            if (r2 != 0) goto L_0x00b0
            java.lang.String r3 = "cvc-particle.1.3"
            java.lang.Object[] r4 = new java.lang.Object[r15]
            java.lang.String r1 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r11)
            r4[r14] = r1
            r6 = 0
            r7 = 0
            r8 = 2
            org.apache.xmlbeans.SchemaType r9 = r12._type
            r1 = r18
            r2 = r19
            r5 = r11
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            return
        L_0x00b0:
            int r1 = r1.getWildcardProcess()
            r2 = 3
            if (r1 == r2) goto L_0x00e5
            if (r1 != r13) goto L_0x00be
            boolean r2 = r10._treatLaxAsSkip
            if (r2 == 0) goto L_0x00be
            goto L_0x00e5
        L_0x00be:
            org.apache.xmlbeans.SchemaTypeLoader r2 = r10._globalTypes
            org.apache.xmlbeans.SchemaGlobalElement r2 = r2.findElement(r11)
            r10._localElement = r2
            if (r2 != 0) goto L_0x0121
            if (r1 != r15) goto L_0x00e2
            java.lang.String r3 = "cvc-assess-elt.1.1.1.3.2"
            java.lang.Object[] r4 = new java.lang.Object[r15]
            java.lang.String r1 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r11)
            r4[r14] = r1
            org.apache.xmlbeans.SchemaType r6 = r12._type
            r7 = 0
            r8 = 2
            org.apache.xmlbeans.SchemaType r9 = r12._type
            r1 = r18
            r2 = r19
            r5 = r11
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
        L_0x00e2:
            r10._eatContent = r15
            return
        L_0x00e5:
            r10._eatContent = r15
            return
        L_0x00e8:
            javax.xml.namespace.QName r2 = r1.getName()
            boolean r2 = r2.equals(r11)
            if (r2 != 0) goto L_0x011e
            org.apache.xmlbeans.SchemaLocalElement r1 = (org.apache.xmlbeans.SchemaLocalElement) r1
            boolean r1 = r1.blockSubstitution()
            if (r1 == 0) goto L_0x0115
            java.lang.String r3 = "cvc-particle.2.3.3a"
            java.lang.Object[] r4 = new java.lang.Object[r15]
            java.lang.String r1 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r11)
            r4[r14] = r1
            org.apache.xmlbeans.SchemaType r6 = r12._type
            r7 = 0
            r8 = 2
            org.apache.xmlbeans.SchemaType r9 = r12._type
            r1 = r18
            r2 = r19
            r5 = r11
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            return
        L_0x0115:
            org.apache.xmlbeans.SchemaTypeLoader r1 = r10._globalTypes
            org.apache.xmlbeans.SchemaGlobalElement r1 = r1.findElement(r11)
            r10._localElement = r1
            goto L_0x0120
        L_0x011e:
            org.apache.xmlbeans.SchemaField r1 = (org.apache.xmlbeans.SchemaField) r1
        L_0x0120:
            r2 = r1
        L_0x0121:
            org.apache.xmlbeans.SchemaType r1 = r2.getType()
            goto L_0x0016
        L_0x0127:
            boolean r1 = r9.isNoType()
            if (r1 == 0) goto L_0x0146
            java.lang.String r3 = "cvc-elt.1"
            r4 = 0
            javax.xml.namespace.QName r5 = r19.getName()
            r6 = 0
            r7 = 0
            r8 = 3
            r16 = 0
            r1 = r18
            r2 = r19
            r13 = r9
            r9 = r16
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            goto L_0x0147
        L_0x0146:
            r13 = r9
        L_0x0147:
            java.lang.String r1 = r19.getXsiType()
            if (r1 == 0) goto L_0x01b9
            int r2 = r10._errorState
            int r3 = r10._suspendErrors
            int r3 = r3 + r15
            r10._suspendErrors = r3
            org.apache.xmlbeans.impl.validator.Validator$ValidatorVC r3 = r10._vc     // Catch:{ all -> 0x016c }
            r9 = 0
            r3._event = r9     // Catch:{ all -> 0x016d }
            org.apache.xmlbeans.SchemaTypeLoader r3 = r10._globalTypes     // Catch:{ all -> 0x016d }
            org.apache.xmlbeans.impl.validator.Validator$ValidatorVC r4 = r10._vc     // Catch:{ all -> 0x016d }
            javax.xml.namespace.QName r4 = org.apache.xmlbeans.impl.values.XmlQNameImpl.validateLexical(r1, r4, r0)     // Catch:{ all -> 0x016d }
            org.apache.xmlbeans.SchemaType r3 = r3.findType(r4)     // Catch:{ all -> 0x016d }
            int r4 = r10._suspendErrors
            int r4 = r4 - r15
            r10._suspendErrors = r4
            r6 = r3
            goto L_0x0178
        L_0x016c:
            r9 = 0
        L_0x016d:
            int r3 = r10._errorState     // Catch:{ all -> 0x01b2 }
            int r3 = r3 + r15
            r10._errorState = r3     // Catch:{ all -> 0x01b2 }
            int r3 = r10._suspendErrors
            int r3 = r3 - r15
            r10._suspendErrors = r3
            r6 = r9
        L_0x0178:
            int r3 = r10._errorState
            if (r2 == r3) goto L_0x0198
            java.lang.String r3 = "cvc-elt.4.1"
            java.lang.Object[] r4 = new java.lang.Object[r15]
            r4[r14] = r1
            javax.xml.namespace.QName r5 = r19.getName()
            r7 = 0
            r8 = 3
            if (r12 != 0) goto L_0x018b
            goto L_0x018e
        L_0x018b:
            org.apache.xmlbeans.SchemaType r1 = r12._type
            r9 = r1
        L_0x018e:
            r1 = r18
            r2 = r19
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            return
        L_0x0198:
            if (r6 != 0) goto L_0x01bb
            java.lang.String r3 = "cvc-elt.4.2"
            java.lang.Object[] r4 = new java.lang.Object[r15]
            r4[r14] = r1
            javax.xml.namespace.QName r5 = r19.getName()
            r6 = 0
            r7 = 0
            r8 = 3
            r9 = 0
            r1 = r18
            r2 = r19
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            return
        L_0x01b2:
            r0 = move-exception
            int r1 = r10._suspendErrors
            int r1 = r1 - r15
            r10._suspendErrors = r1
            throw r0
        L_0x01b9:
            r9 = 0
            r6 = r9
        L_0x01bb:
            if (r6 == 0) goto L_0x02bc
            boolean r1 = r6.equals(r13)
            if (r1 != 0) goto L_0x02bc
            boolean r1 = r13.isAssignableFrom(r6)
            if (r1 != 0) goto L_0x01e9
            java.lang.String r3 = "cvc-elt.4.3a"
            r1 = 2
            java.lang.Object[] r4 = new java.lang.Object[r1]
            r4[r14] = r6
            r4[r15] = r13
            javax.xml.namespace.QName r5 = r19.getName()
            r7 = 0
            r8 = 3
            if (r12 != 0) goto L_0x01db
            goto L_0x01de
        L_0x01db:
            org.apache.xmlbeans.SchemaType r1 = r12._type
            r9 = r1
        L_0x01de:
            r1 = r18
            r2 = r19
            r6 = r13
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            return
        L_0x01e9:
            boolean r1 = r13.blockExtension()
            if (r1 == 0) goto L_0x0224
            r1 = r6
        L_0x01f0:
            boolean r2 = r1.equals(r13)
            if (r2 != 0) goto L_0x0224
            int r2 = r1.getDerivationType()
            r3 = 2
            if (r2 != r3) goto L_0x021f
            java.lang.String r4 = "cvc-elt.4.3b"
            java.lang.Object[] r5 = new java.lang.Object[r3]
            r5[r14] = r6
            r5[r15] = r13
            javax.xml.namespace.QName r6 = r19.getName()
            r7 = 0
            r8 = 3
            if (r12 != 0) goto L_0x020e
            goto L_0x0211
        L_0x020e:
            org.apache.xmlbeans.SchemaType r1 = r12._type
            r9 = r1
        L_0x0211:
            r1 = r18
            r2 = r19
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r13
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            return
        L_0x021f:
            org.apache.xmlbeans.SchemaType r1 = r1.getBaseType()
            goto L_0x01f0
        L_0x0224:
            boolean r1 = r13.blockRestriction()
            if (r1 == 0) goto L_0x025c
            r1 = r6
        L_0x022b:
            boolean r2 = r1.equals(r13)
            if (r2 != 0) goto L_0x025c
            int r2 = r1.getDerivationType()
            if (r2 != r15) goto L_0x0257
            java.lang.String r3 = "cvc-elt.4.3c"
            r1 = 2
            java.lang.Object[] r4 = new java.lang.Object[r1]
            r4[r14] = r6
            r4[r15] = r13
            javax.xml.namespace.QName r5 = r19.getName()
            r7 = 0
            r8 = 3
            if (r12 != 0) goto L_0x0249
            goto L_0x024c
        L_0x0249:
            org.apache.xmlbeans.SchemaType r1 = r12._type
            r9 = r1
        L_0x024c:
            r1 = r18
            r2 = r19
            r6 = r13
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            return
        L_0x0257:
            org.apache.xmlbeans.SchemaType r1 = r1.getBaseType()
            goto L_0x022b
        L_0x025c:
            boolean r1 = r11 instanceof org.apache.xmlbeans.SchemaLocalElement
            if (r1 == 0) goto L_0x02bb
            r1 = r11
            org.apache.xmlbeans.SchemaLocalElement r1 = (org.apache.xmlbeans.SchemaLocalElement) r1
            r10._localElement = r1
            boolean r2 = r1.blockExtension()
            if (r2 != 0) goto L_0x0271
            boolean r2 = r1.blockRestriction()
            if (r2 == 0) goto L_0x02bb
        L_0x0271:
            r2 = r6
        L_0x0272:
            boolean r3 = r2.equals(r13)
            if (r3 != 0) goto L_0x02bb
            int r3 = r2.getDerivationType()
            if (r3 != r15) goto L_0x0287
            boolean r3 = r1.blockRestriction()
            if (r3 != 0) goto L_0x0285
            goto L_0x0287
        L_0x0285:
            r4 = 2
            goto L_0x0294
        L_0x0287:
            int r3 = r2.getDerivationType()
            r4 = 2
            if (r3 != r4) goto L_0x02b6
            boolean r3 = r1.blockExtension()
            if (r3 == 0) goto L_0x02b6
        L_0x0294:
            java.lang.String r3 = "cvc-elt.4.3d"
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r14] = r6
            javax.xml.namespace.QName r2 = r1.getName()
            java.lang.String r2 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r2)
            r4[r15] = r2
            javax.xml.namespace.QName r5 = r1.getName()
            r6 = 0
            r7 = 0
            r8 = 3
            r9 = 0
            r1 = r18
            r2 = r19
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            return
        L_0x02b6:
            org.apache.xmlbeans.SchemaType r2 = r2.getBaseType()
            goto L_0x0272
        L_0x02bb:
            r13 = r6
        L_0x02bc:
            boolean r8 = r11 instanceof org.apache.xmlbeans.SchemaLocalElement
            if (r8 == 0) goto L_0x02ea
            r1 = r11
            org.apache.xmlbeans.SchemaLocalElement r1 = (org.apache.xmlbeans.SchemaLocalElement) r1
            r10._localElement = r1
            boolean r2 = r1.isAbstract()
            if (r2 == 0) goto L_0x02ea
            java.lang.String r3 = "cvc-elt.2"
            java.lang.Object[] r4 = new java.lang.Object[r15]
            javax.xml.namespace.QName r2 = r1.getName()
            java.lang.String r2 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r2)
            r4[r14] = r2
            javax.xml.namespace.QName r5 = r1.getName()
            r6 = 0
            r7 = 3
            r8 = 0
            r1 = r18
            r2 = r19
            r1.emitError(r2, r3, r4, r5, r6, r7, r8)
            r10._eatContent = r15
            return
        L_0x02ea:
            boolean r1 = r13.isAbstract()
            if (r1 == 0) goto L_0x030d
            java.lang.String r3 = "cvc-elt.2"
            java.lang.Object[] r4 = new java.lang.Object[r15]
            r4[r14] = r13
            javax.xml.namespace.QName r5 = r19.getName()
            r7 = 3
            if (r12 != 0) goto L_0x02ff
            r8 = r9
            goto L_0x0302
        L_0x02ff:
            org.apache.xmlbeans.SchemaType r1 = r12._type
            r8 = r1
        L_0x0302:
            r1 = r18
            r2 = r19
            r6 = r13
            r1.emitError(r2, r3, r4, r5, r6, r7, r8)
            r10._eatContent = r15
            return
        L_0x030d:
            java.lang.String r1 = r19.getXsiNil()
            if (r1 == 0) goto L_0x0320
            org.apache.xmlbeans.impl.validator.Validator$ValidatorVC r2 = r10._vc
            r2._event = r0
            org.apache.xmlbeans.impl.validator.Validator$ValidatorVC r2 = r10._vc
            boolean r14 = org.apache.xmlbeans.impl.values.JavaBooleanHolder.validateLexical(r1, r2)
            r7 = r14
            r14 = r15
            goto L_0x0321
        L_0x0320:
            r7 = r14
        L_0x0321:
            if (r14 == 0) goto L_0x034a
            if (r11 == 0) goto L_0x032b
            boolean r1 = r11.isNillable()
            if (r1 != 0) goto L_0x034a
        L_0x032b:
            java.lang.String r3 = "cvc-elt.3.1"
            r4 = 0
            if (r11 != 0) goto L_0x0332
            r5 = r9
            goto L_0x0337
        L_0x0332:
            javax.xml.namespace.QName r1 = r11.getName()
            r5 = r1
        L_0x0337:
            r7 = 0
            r8 = 3
            if (r12 != 0) goto L_0x033c
            goto L_0x033f
        L_0x033c:
            org.apache.xmlbeans.SchemaType r1 = r12._type
            r9 = r1
        L_0x033f:
            r1 = r18
            r2 = r19
            r6 = r13
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r10._eatContent = r15
            return
        L_0x034a:
            if (r7 == 0) goto L_0x0376
            if (r11 == 0) goto L_0x0376
            boolean r1 = r11.isFixed()
            if (r1 == 0) goto L_0x0376
            java.lang.String r3 = "cvc-elt.3.2.2"
            r4 = 0
            javax.xml.namespace.QName r5 = r11.getName()
            r14 = 0
            r15 = 3
            if (r12 != 0) goto L_0x0361
            r12 = r9
            goto L_0x0364
        L_0x0361:
            org.apache.xmlbeans.SchemaType r1 = r12._type
            r12 = r1
        L_0x0364:
            r1 = r18
            r2 = r19
            r6 = r13
            r17 = r7
            r7 = r14
            r14 = r8
            r8 = r15
            r15 = r9
            r9 = r12
            r1.emitFieldError(r2, r3, r4, r5, r6, r7, r8, r9)
            r1 = r17
            goto L_0x0379
        L_0x0376:
            r14 = r8
            r15 = r9
            r1 = r7
        L_0x0379:
            r10.newState(r13, r11, r1)
            org.apache.xmlbeans.impl.common.IdentityConstraint r1 = r10._constraintEngine
            if (r14 == 0) goto L_0x0387
            org.apache.xmlbeans.SchemaLocalElement r11 = (org.apache.xmlbeans.SchemaLocalElement) r11
            org.apache.xmlbeans.SchemaIdentityConstraint[] r11 = r11.getIdentityConstraints()
            goto L_0x0388
        L_0x0387:
            r11 = r15
        L_0x0388:
            r1.element(r0, r13, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.validator.Validator.beginEvent(org.apache.xmlbeans.impl.common.ValidatorListener$Event):void");
    }

    private void attrEvent(ValidatorListener.Event event) {
        SchemaLocalAttribute schemaLocalAttribute;
        QName name = event.getName();
        State state = topState();
        if (state._attrs == null) {
            state._attrs = new HashSet<>();
        }
        if (state._attrs.contains(name)) {
            emitFieldError(event, XmlErrorCodes.XML_DUPLICATE_ATTRIBUTE, new Object[]{QNameHelper.pretty(name)}, name, (SchemaType) null, (List<QName>) null, 1000, state._type);
            return;
        }
        state._attrs.add(name);
        if (!state._canHaveAttrs) {
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$NO_WILDCARD, new Object[]{QNameHelper.pretty(name)}, name, (SchemaType) null, (List<QName>) null, 1000, state._type);
            return;
        }
        if (state._attrModel == null) {
            schemaLocalAttribute = null;
        } else {
            schemaLocalAttribute = state._attrModel.getAttribute(name);
        }
        if (schemaLocalAttribute != null) {
            this._localAttribute = schemaLocalAttribute;
            if (schemaLocalAttribute.getUse() == 1) {
                emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$PROHIBITED_ATTRIBUTE, new Object[]{QNameHelper.pretty(name)}, name, (SchemaType) null, (List<QName>) null, 1000, state._type);
                return;
            }
            this._constraintEngine.attr(event, name, schemaLocalAttribute.getType(), validateSimpleType(schemaLocalAttribute.getType(), schemaLocalAttribute, event, false, false));
            return;
        }
        int wildcardProcess = state._attrModel.getWildcardProcess();
        this._wildcardAttribute = state._attrModel;
        if (wildcardProcess == 0) {
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$NO_WILDCARD, new Object[]{QNameHelper.pretty(name)}, name, (SchemaType) null, (List<QName>) null, 1000, state._type);
        } else if (!state._attrModel.getWildcardSet().contains(name)) {
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$NOT_WILDCARD_VALID, new Object[]{QNameHelper.pretty(name)}, name, (SchemaType) null, (List<QName>) null, 1000, state._type);
        } else if (wildcardProcess == 3) {
        } else {
            if (wildcardProcess != 2 || !this._treatLaxAsSkip) {
                SchemaGlobalAttribute findAttribute = this._globalTypes.findAttribute(name);
                this._localAttribute = findAttribute;
                if (findAttribute != null) {
                    this._constraintEngine.attr(event, name, findAttribute.getType(), validateSimpleType(findAttribute.getType(), findAttribute, event, false, false));
                } else if (wildcardProcess != 2) {
                    emitFieldError(event, XmlErrorCodes.ASSESS_ATTR_SCHEMA_VALID$NOT_RESOLVED, new Object[]{QNameHelper.pretty(name)}, name, (SchemaType) null, (List<QName>) null, 1000, state._type);
                }
            }
        }
    }

    private void endAttrsEvent(ValidatorListener.Event event) {
        State state = topState();
        if (state._attrModel != null) {
            for (SchemaLocalAttribute schemaLocalAttribute : state._attrModel.getAttributes()) {
                if (state._attrs == null || !state._attrs.contains(schemaLocalAttribute.getName())) {
                    if (schemaLocalAttribute.getUse() == 3) {
                        emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$MISSING_REQUIRED_ATTRIBUTE, new Object[]{QNameHelper.pretty(schemaLocalAttribute.getName())}, schemaLocalAttribute.getName(), (SchemaType) null, (List<QName>) null, 1000, state._type);
                    } else if (schemaLocalAttribute.isDefault() || schemaLocalAttribute.isFixed()) {
                        this._constraintEngine.attr(event, schemaLocalAttribute.getName(), schemaLocalAttribute.getType(), schemaLocalAttribute.getDefaultText());
                    }
                }
                ValidatorListener.Event event2 = event;
            }
        }
    }

    private void endEvent(ValidatorListener.Event event) {
        this._localElement = null;
        this._wildcardElement = null;
        State state = topState();
        if (!state._isNil) {
            if (!state.end()) {
                findDetailedErrorEnd(event, state);
            }
            if (state._isEmpty) {
                handleText(event, true, state._field);
            }
        }
        popState(event);
        this._constraintEngine.endElement(event);
    }

    private void textEvent(ValidatorListener.Event event) {
        State state = topState();
        if (state._isNil) {
            emitFieldError(event, XmlErrorCodes.ELEM_LOCALLY_VALID$NIL_WITH_CONTENT, (Object[]) null, state._field.getName(), state._type, (List<QName>) null, 4, state._type);
        } else {
            handleText(event, false, state._field);
        }
        state._isEmpty = false;
    }

    private void handleText(ValidatorListener.Event event, boolean z, SchemaField schemaField) {
        State state = topState();
        if (!state._sawText) {
            if (state._hasSimpleContent) {
                this._constraintEngine.text(event, state._type, validateSimpleType(state._type, schemaField, event, z, true), false);
            } else if (state._canHaveMixedContent) {
                this._constraintEngine.text(event, XmlString.type, validateSimpleType(XmlString.type, schemaField, event, z, true), false);
            } else if (z) {
                this._constraintEngine.text(event, state._type, (String) null, true);
            } else {
                this._constraintEngine.text(event, state._type, "", false);
            }
        }
        if (!z && !state._canHaveMixedContent && !event.textIsWhitespace() && !state._hasSimpleContent) {
            if (schemaField instanceof SchemaLocalElement) {
                SchemaLocalElement schemaLocalElement = (SchemaLocalElement) schemaField;
                emitError(event, state._type.getContentType() == 1 ? XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$EMPTY_WITH_CONTENT : XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$ELEMENT_ONLY_WITH_TEXT, new Object[]{QNameHelper.pretty(schemaLocalElement.getName())}, schemaLocalElement.getName(), schemaField.getType(), 3, (SchemaType) null);
            } else {
                emitError(event, "Can't have mixed content", event.getName(), state._type, 3);
            }
        }
        if (!z) {
            state._sawText = true;
        }
    }

    private void findDetailedErrorBegin(ValidatorListener.Event event, State state, QName qName) {
        State state2 = state;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (SchemaProperty schemaProperty : state2._type.getElementProperties()) {
            if (state2.test(schemaProperty.getName())) {
                if (BigInteger.ZERO.compareTo(schemaProperty.getMinOccurs()) == 0) {
                    arrayList2.add(schemaProperty.getName());
                } else {
                    arrayList.add(schemaProperty.getName());
                }
            }
        }
        ArrayList arrayList3 = arrayList.size() > 0 ? arrayList : arrayList2;
        if (arrayList3.size() > 0) {
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$EXPECTED_DIFFERENT_ELEMENT, new Object[]{Integer.valueOf(arrayList3.size()), (String) arrayList3.stream().map(new Validator$$ExternalSyntheticLambda0()).collect(Collectors.joining(" ")), QNameHelper.pretty(qName)}, qName, (SchemaType) null, arrayList3, 1, state2._type);
            return;
        }
        emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$ELEMENT_NOT_ALLOWED, new Object[]{QNameHelper.pretty(qName)}, qName, (SchemaType) null, (List<QName>) null, 1, state2._type);
    }

    private void findDetailedErrorEnd(ValidatorListener.Event event, State state) {
        State state2 = state;
        SchemaProperty[] elementProperties = state2._type.getElementProperties();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (SchemaProperty schemaProperty : elementProperties) {
            if (state2.test(schemaProperty.getName())) {
                if (BigInteger.ZERO.compareTo(schemaProperty.getMinOccurs()) == 0) {
                    arrayList2.add(schemaProperty.getName());
                } else {
                    arrayList.add(schemaProperty.getName());
                }
            }
        }
        ArrayList arrayList3 = arrayList.size() > 0 ? arrayList : arrayList2;
        if (arrayList3.size() > 0) {
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$MISSING_ELEMENT, new Object[]{Integer.valueOf(arrayList3.size()), (String) arrayList3.stream().map(new Validator$$ExternalSyntheticLambda0()).collect(Collectors.joining(" "))}, (QName) null, (SchemaType) null, arrayList3, 1, state2._type);
            return;
        }
        emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$EXPECTED_ELEMENT, (Object[]) null, (QName) null, (SchemaType) null, (List<QName>) null, 2, state2._type);
    }

    private static final class State {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        SchemaAttributeModel _attrModel;
        HashSet<QName> _attrs;
        boolean _canHaveAttrs;
        boolean _canHaveElements;
        boolean _canHaveMixedContent;
        SchemaField _field;
        boolean _hasSimpleContent;
        boolean _isEmpty;
        boolean _isNil;
        State _next;
        boolean _sawText;
        SchemaType _type;
        SchemaTypeVisitorImpl _visitor;

        static {
            Class<Validator> cls = Validator.class;
        }

        private State() {
        }

        /* access modifiers changed from: package-private */
        public boolean visit(QName qName) {
            return this._canHaveElements && this._visitor.visit(qName);
        }

        /* access modifiers changed from: package-private */
        public boolean test(QName qName) {
            return this._canHaveElements && this._visitor.testValid(qName);
        }

        /* access modifiers changed from: package-private */
        public boolean end() {
            return !this._canHaveElements || this._visitor.visit((QName) null);
        }

        /* access modifiers changed from: package-private */
        public SchemaParticle currentParticle() {
            return this._visitor.currentParticle();
        }
    }

    private boolean derivedFromInteger(SchemaType schemaType) {
        int builtinTypeCode = schemaType.getBuiltinTypeCode();
        while (builtinTypeCode == 0) {
            schemaType = schemaType.getBaseType();
            builtinTypeCode = schemaType.getBuiltinTypeCode();
        }
        return builtinTypeCode >= 22 && builtinTypeCode <= 34;
    }

    private void newState(SchemaType schemaType, SchemaField schemaField, boolean z) {
        State state = new State();
        state._type = schemaType;
        state._field = schemaField;
        boolean z2 = true;
        state._isEmpty = true;
        state._isNil = z;
        if (schemaType.isSimpleType()) {
            state._hasSimpleContent = true;
        } else {
            state._canHaveAttrs = true;
            state._attrModel = schemaType.getAttributeModel();
            int contentType = schemaType.getContentType();
            if (contentType != 1) {
                if (contentType != 2) {
                    if (contentType != 3) {
                        if (contentType == 4) {
                            state._canHaveMixedContent = true;
                        } else {
                            throw new RuntimeException("Unexpected content type");
                        }
                    }
                    SchemaParticle contentModel = schemaType.getContentModel();
                    if (contentModel == null) {
                        z2 = false;
                    }
                    state._canHaveElements = z2;
                    if (state._canHaveElements) {
                        state._visitor = initVisitor(contentModel);
                    }
                } else {
                    state._hasSimpleContent = true;
                }
            }
        }
        pushState(state);
    }

    private void popState(ValidatorListener.Event event) {
        if (this._stateStack._visitor != null) {
            poolVisitor(this._stateStack._visitor);
            this._stateStack._visitor = null;
        }
        this._stateStack = this._stateStack._next;
    }

    private void pushState(State state) {
        state._next = this._stateStack;
        this._stateStack = state;
    }

    private void poolVisitor(SchemaTypeVisitorImpl schemaTypeVisitorImpl) {
        this._visitorPool.add(schemaTypeVisitorImpl);
    }

    private SchemaTypeVisitorImpl initVisitor(SchemaParticle schemaParticle) {
        if (this._visitorPool.isEmpty()) {
            return new SchemaTypeVisitorImpl(schemaParticle);
        }
        SchemaTypeVisitorImpl schemaTypeVisitorImpl = (SchemaTypeVisitorImpl) this._visitorPool.removeLast();
        schemaTypeVisitorImpl.init(schemaParticle);
        return schemaTypeVisitorImpl;
    }

    private State topState() {
        return this._stateStack;
    }

    private String validateSimpleType(SchemaType schemaType, SchemaField schemaField, ValidatorListener.Event event, boolean z, boolean z2) {
        String str;
        String str2;
        SchemaType schemaType2 = schemaType;
        ValidatorListener.Event event2 = event;
        if (!schemaType.isSimpleType() && schemaType.getContentType() != 2) {
            return null;
        }
        if (schemaType.isNoType()) {
            emitError(event, schemaField.isAttribute() ? XmlErrorCodes.ATTR_LOCALLY_VALID$NO_TYPE : XmlErrorCodes.ELEM_LOCALLY_VALID$NO_TYPE, (Object[]) null, schemaField.getName(), schemaType, 3, (SchemaType) null);
            return null;
        }
        if (!z) {
            int whiteSpaceRule = schemaType.getWhiteSpaceRule();
            str = whiteSpaceRule == 1 ? event.getText() : event2.getText(whiteSpaceRule);
        } else {
            str = "";
        }
        if (str.length() != 0 || !z2 || schemaField == null || (!schemaField.isDefault() && !schemaField.isFixed())) {
            if (!validateSimpleType(schemaType, str, event2)) {
                return null;
            }
            if (schemaField != null && schemaField.isFixed()) {
                String collapse = XmlWhitespace.collapse(schemaField.getDefaultText(), schemaType.getWhiteSpaceRule());
                if (!validateSimpleType(schemaType, collapse, event2)) {
                    return null;
                }
                if (!schemaType.newValue(str).valueEquals(schemaType.newValue(collapse))) {
                    if (schemaField.isAttribute()) {
                        emitError(event, XmlErrorCodes.ATTR_LOCALLY_VALID$FIXED, new Object[]{str, collapse, QNameHelper.pretty(event.getName())}, (QName) null, schemaField.getType(), 3, (SchemaType) null);
                    } else {
                        if (schemaField.getType().getContentType() == 4) {
                            str2 = XmlErrorCodes.ELEM_LOCALLY_VALID$FIXED_VALID_MIXED_CONTENT;
                        } else {
                            str2 = schemaType.isSimpleType() ? XmlErrorCodes.ELEM_LOCALLY_VALID$FIXED_VALID_SIMPLE_TYPE : null;
                        }
                        emitError(event, str2, new Object[]{str, collapse}, schemaField.getName(), schemaField.getType(), 3, (SchemaType) null);
                    }
                    return null;
                }
            }
            return str;
        } else if (XmlQName.type.isAssignableFrom(schemaType)) {
            emitError(event, "Default QName values are unsupported for " + QNameHelper.readable(schemaType) + " - ignoring.", (String) null, (Object[]) null, 2, schemaField.getName(), (QName) null, schemaType, (List<QName>) null, 3, (SchemaType) null);
            return null;
        } else {
            String collapse2 = XmlWhitespace.collapse(schemaField.getDefaultText(), schemaType.getWhiteSpaceRule());
            if (validateSimpleType(schemaType, collapse2, event2)) {
                return collapse2;
            }
            return null;
        }
    }

    private boolean validateSimpleType(SchemaType schemaType, String str, ValidatorListener.Event event) {
        if (schemaType.isSimpleType() || schemaType.getContentType() == 2) {
            int i = this._errorState;
            int simpleVariety = schemaType.getSimpleVariety();
            if (simpleVariety == 1) {
                validateAtomicType(schemaType, str, event);
            } else if (simpleVariety == 2) {
                validateUnionType(schemaType, str, event);
            } else if (simpleVariety == 3) {
                validateListType(schemaType, str, event);
            } else {
                throw new RuntimeException("Unexpected simple variety");
            }
            if (i == this._errorState) {
                return true;
            }
            return false;
        }
        throw new RuntimeException("Not a simple type");
    }

    private void validateAtomicType(SchemaType schemaType, String str, ValidatorListener.Event event) {
        int i = this._errorState;
        this._vc._event = event;
        switch (schemaType.getPrimitiveType().getBuiltinTypeCode()) {
            case 2:
                this._stringValue = str;
                return;
            case 3:
                this._booleanValue = JavaBooleanHolderEx.validateLexical(str, schemaType, this._vc);
                return;
            case 4:
                byte[] validateLexical = JavaBase64HolderEx.validateLexical(str, schemaType, this._vc);
                if (validateLexical != null) {
                    JavaBase64HolderEx.validateValue(validateLexical, schemaType, this._vc);
                }
                this._byteArrayValue = validateLexical;
                return;
            case 5:
                byte[] validateLexical2 = JavaHexBinaryHolderEx.validateLexical(str, schemaType, this._vc);
                if (validateLexical2 != null) {
                    JavaHexBinaryHolderEx.validateValue(validateLexical2, schemaType, this._vc);
                }
                this._byteArrayValue = validateLexical2;
                return;
            case 6:
                JavaUriHolderEx.validateLexical(str, schemaType, this._vc);
                if (this._strict) {
                    try {
                        XsTypeConverter.lexAnyURI(str);
                    } catch (InvalidLexicalValueException unused) {
                        this._vc.invalid(XmlErrorCodes.ANYURI, new Object[]{str});
                    }
                }
                this._stringValue = str;
                return;
            case 7:
                QName validateLexical3 = JavaQNameHolderEx.validateLexical(str, schemaType, this._vc, event);
                if (i == this._errorState) {
                    JavaQNameHolderEx.validateValue(validateLexical3, schemaType, this._vc);
                }
                this._qnameValue = validateLexical3;
                return;
            case 8:
                QName validateLexical4 = JavaNotationHolderEx.validateLexical(str, schemaType, this._vc, event);
                if (i == this._errorState) {
                    JavaNotationHolderEx.validateValue(validateLexical4, schemaType, this._vc);
                }
                this._qnameValue = validateLexical4;
                return;
            case 9:
                float validateLexical5 = JavaFloatHolderEx.validateLexical(str, schemaType, this._vc);
                if (i == this._errorState) {
                    JavaFloatHolderEx.validateValue(validateLexical5, schemaType, this._vc);
                }
                this._floatValue = validateLexical5;
                return;
            case 10:
                double validateLexical6 = JavaDoubleHolderEx.validateLexical(str, schemaType, this._vc);
                if (i == this._errorState) {
                    JavaDoubleHolderEx.validateValue(validateLexical6, schemaType, this._vc);
                }
                this._doubleValue = validateLexical6;
                return;
            case 11:
                JavaDecimalHolderEx.validateLexical(str, schemaType, this._vc);
                if (derivedFromInteger(schemaType) && str.lastIndexOf(46) >= 0) {
                    this._vc.invalid("integer", new Object[]{str});
                }
                if (i == this._errorState) {
                    BigDecimal bigDecimal = new BigDecimal(str);
                    this._decimalValue = bigDecimal;
                    JavaDecimalHolderEx.validateValue(bigDecimal, schemaType, this._vc);
                    return;
                }
                return;
            case 12:
                JavaStringEnumerationHolderEx.validateLexical(str, schemaType, this._vc);
                this._stringValue = str;
                return;
            case 13:
                GDuration validateLexical7 = XmlDurationImpl.validateLexical(str, schemaType, this._vc);
                if (validateLexical7 != null) {
                    XmlDurationImpl.validateValue(validateLexical7, schemaType, this._vc);
                }
                this._gdurationValue = validateLexical7;
                return;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                break;
            case 21:
                if (this._strict && str.length() == 6 && str.charAt(4) == '-' && str.charAt(5) == '-') {
                    this._vc.invalid(XmlErrorCodes.DATE, new Object[]{str});
                    break;
                }
            default:
                throw new RuntimeException("Unexpected primitive type code");
        }
        GDate validateLexical8 = XmlDateImpl.validateLexical(str, schemaType, this._vc);
        if (validateLexical8 != null) {
            XmlDateImpl.validateValue(validateLexical8, schemaType, this._vc);
        }
        this._gdateValue = validateLexical8;
    }

    private void validateListType(SchemaType schemaType, String str, ValidatorListener.Event event) {
        int i;
        int i2;
        String[] strArr;
        int intValue;
        int intValue2;
        int intValue3;
        SchemaType schemaType2 = schemaType;
        String str2 = str;
        ValidatorListener.Event event2 = event;
        int i3 = this._errorState;
        if (!schemaType.matchPatternFacet(str)) {
            i2 = 0;
            i = 1;
            emitError(event, XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.LIST, str2, QNameHelper.readable(schemaType)}, (QName) null, schemaType, 2000, (SchemaType) null);
        } else {
            i2 = 0;
            i = 1;
        }
        String[] split_list = XmlListImpl.split_list(str);
        XmlAnySimpleType facet = schemaType2.getFacet(i2);
        if (facet == null || (intValue3 = ((SimpleValue) facet).getIntValue()) == split_list.length) {
            strArr = split_list;
        } else {
            Object[] objArr = new Object[4];
            objArr[i2] = str2;
            objArr[i] = Integer.valueOf(split_list.length);
            objArr[2] = Integer.valueOf(intValue3);
            objArr[3] = QNameHelper.readable(schemaType);
            strArr = split_list;
            emitError(event, XmlErrorCodes.DATATYPE_LENGTH_VALID$LIST_LENGTH, objArr, (QName) null, schemaType, 2000, (SchemaType) null);
        }
        XmlAnySimpleType facet2 = schemaType2.getFacet(i);
        if (facet2 != null && (intValue2 = ((SimpleValue) facet2).getIntValue()) > strArr.length) {
            Object[] objArr2 = new Object[4];
            objArr2[0] = str2;
            objArr2[i] = Integer.valueOf(strArr.length);
            objArr2[2] = Integer.valueOf(intValue2);
            objArr2[3] = QNameHelper.readable(schemaType);
            emitError(event, XmlErrorCodes.DATATYPE_LENGTH_VALID$LIST_LENGTH, objArr2, (QName) null, schemaType, 2000, (SchemaType) null);
        }
        XmlAnySimpleType facet3 = schemaType2.getFacet(2);
        if (facet3 != null && (intValue = ((SimpleValue) facet3).getIntValue()) < strArr.length) {
            Object[] objArr3 = new Object[4];
            objArr3[0] = str2;
            objArr3[i] = Integer.valueOf(strArr.length);
            objArr3[2] = Integer.valueOf(intValue);
            objArr3[3] = QNameHelper.readable(schemaType);
            emitError(event, XmlErrorCodes.DATATYPE_LENGTH_VALID$LIST_LENGTH, objArr3, (QName) null, schemaType, 2000, (SchemaType) null);
        }
        SchemaType listItemType = schemaType.getListItemType();
        this._listValue = new ArrayList();
        this._listTypes = new ArrayList();
        for (String validateSimpleType : strArr) {
            validateSimpleType(listItemType, validateSimpleType, event2);
            addToList(listItemType);
        }
        if (i3 == this._errorState && schemaType.getEnumerationValues() != null) {
            NamespaceContext.push(new NamespaceContext((PrefixResolver) event2));
            try {
                ((SchemaTypeImpl) schemaType2).newValidatingValue(str2);
            } catch (XmlValueOutOfRangeException unused) {
                Object[] objArr4 = new Object[3];
                objArr4[0] = XmlErrorCodes.LIST;
                objArr4[i] = str2;
                objArr4[2] = QNameHelper.readable(schemaType);
                emitError(event, XmlErrorCodes.DATATYPE_ENUM_VALID, objArr4, (QName) null, schemaType, 2000, (SchemaType) null);
            } catch (Throwable th) {
                NamespaceContext.pop();
                throw th;
            }
            NamespaceContext.pop();
        }
    }

    /* JADX INFO: finally extract failed */
    private void validateUnionType(SchemaType schemaType, String str, ValidatorListener.Event event) {
        String str2 = str;
        ValidatorListener.Event event2 = event;
        if (!schemaType.matchPatternFacet(str)) {
            emitError(event, XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.UNION, str2, QNameHelper.readable(schemaType)}, (QName) null, schemaType, 3000, (SchemaType) null);
        }
        SchemaType[] unionMemberTypes = schemaType.getUnionMemberTypes();
        int i = this._errorState;
        String str3 = str2;
        int i2 = 0;
        int i3 = 1;
        while (true) {
            if (i2 >= unionMemberTypes.length) {
                break;
            }
            int whiteSpaceRule = unionMemberTypes[i2].getWhiteSpaceRule();
            if (whiteSpaceRule == 0) {
                whiteSpaceRule = 1;
            }
            if (whiteSpaceRule != i3) {
                str3 = XmlWhitespace.collapse(str2, whiteSpaceRule);
                i3 = whiteSpaceRule;
            }
            int i4 = this._errorState;
            this._suspendErrors++;
            try {
                validateSimpleType(unionMemberTypes[i2], str3, event2);
                this._suspendErrors--;
                if (i4 == this._errorState) {
                    this._unionType = unionMemberTypes[i2];
                    break;
                }
                i2++;
            } catch (Throwable th) {
                this._suspendErrors--;
                throw th;
            }
        }
        this._errorState = i;
        if (i2 >= unionMemberTypes.length) {
            emitError(event, XmlErrorCodes.DATATYPE_VALID$UNION, new Object[]{str2, QNameHelper.readable(schemaType)}, (QName) null, schemaType, 3000, (SchemaType) null);
            return;
        }
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues != null) {
            NamespaceContext.push(new NamespaceContext((PrefixResolver) event2));
            try {
                XmlAnySimpleType newValue = schemaType.newValue(str);
                int i5 = 0;
                while (true) {
                    if (i5 >= enumerationValues.length) {
                        break;
                    } else if (newValue.valueEquals(enumerationValues[i5])) {
                        break;
                    } else {
                        i5++;
                    }
                }
                if (i5 >= enumerationValues.length) {
                    emitError(event, XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.UNION, str2, QNameHelper.readable(schemaType)}, (QName) null, schemaType, 3000, (SchemaType) null);
                }
            } catch (XmlValueOutOfRangeException unused) {
                emitError(event, XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.UNION, str2, QNameHelper.readable(schemaType)}, (QName) null, schemaType, 3000, (SchemaType) null);
            } catch (Throwable th2) {
                NamespaceContext.pop();
                throw th2;
            }
            NamespaceContext.pop();
        }
    }

    private void addToList(SchemaType schemaType) {
        if (schemaType.getSimpleVariety() == 1 || schemaType.getSimpleVariety() == 2) {
            if (schemaType.getUnionMemberTypes().length > 0 && getUnionType() != null) {
                schemaType = getUnionType();
                this._unionType = null;
            }
            this._listTypes.add(schemaType);
            if (schemaType.getPrimitiveType() == null) {
                this._listValue.add((Object) null);
                return;
            }
            switch (schemaType.getPrimitiveType().getBuiltinTypeCode()) {
                case 2:
                case 6:
                    this._listValue.add(this._stringValue);
                    return;
                case 3:
                    this._listValue.add(this._booleanValue ? Boolean.TRUE : Boolean.FALSE);
                    this._booleanValue = false;
                    return;
                case 4:
                case 5:
                    this._listValue.add(this._byteArrayValue);
                    this._byteArrayValue = null;
                    return;
                case 7:
                case 8:
                    this._listValue.add(this._qnameValue);
                    this._qnameValue = null;
                    return;
                case 9:
                    this._listValue.add(Float.valueOf(this._floatValue));
                    this._floatValue = 0.0f;
                    return;
                case 10:
                    this._listValue.add(Double.valueOf(this._doubleValue));
                    this._doubleValue = 0.0d;
                    return;
                case 11:
                    this._listValue.add(this._decimalValue);
                    this._decimalValue = null;
                    return;
                case 12:
                    this._listValue.add(this._stringValue);
                    this._stringValue = null;
                    return;
                case 13:
                    this._listValue.add(this._gdurationValue);
                    this._gdurationValue = null;
                    return;
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                    this._listValue.add(this._gdateValue);
                    this._gdateValue = null;
                    return;
                default:
                    throw new RuntimeException("Unexpected primitive type code");
            }
        }
    }

    private void resetValues() {
        this._localAttribute = null;
        this._wildcardAttribute = null;
        this._stringValue = null;
        this._decimalValue = null;
        this._booleanValue = false;
        this._floatValue = 0.0f;
        this._doubleValue = 0.0d;
        this._qnameValue = null;
        this._gdateValue = null;
        this._gdurationValue = null;
        this._byteArrayValue = null;
        this._listValue = null;
        this._listTypes = null;
        this._unionType = null;
    }

    public SchemaType getCurrentElementSchemaType() {
        State state = topState();
        if (state != null) {
            return state._type;
        }
        return null;
    }

    public SchemaLocalElement getCurrentElement() {
        State state;
        SchemaLocalElement schemaLocalElement = this._localElement;
        if (schemaLocalElement != null) {
            return schemaLocalElement;
        }
        if (this._eatContent <= 0 && (state = this._stateStack) != null && (state._field instanceof SchemaLocalElement)) {
            return (SchemaLocalElement) this._stateStack._field;
        }
        return null;
    }

    public SchemaParticle getCurrentWildcardElement() {
        return this._wildcardElement;
    }

    public SchemaLocalAttribute getCurrentAttribute() {
        return this._localAttribute;
    }

    public SchemaAttributeModel getCurrentWildcardAttribute() {
        return this._wildcardAttribute;
    }

    public String getStringValue() {
        return this._stringValue;
    }

    public BigDecimal getDecimalValue() {
        return this._decimalValue;
    }

    public boolean getBooleanValue() {
        return this._booleanValue;
    }

    public float getFloatValue() {
        return this._floatValue;
    }

    public double getDoubleValue() {
        return this._doubleValue;
    }

    public QName getQNameValue() {
        return this._qnameValue;
    }

    public GDate getGDateValue() {
        return this._gdateValue;
    }

    public GDuration getGDurationValue() {
        return this._gdurationValue;
    }

    public byte[] getByteArrayValue() {
        return this._byteArrayValue;
    }

    public List<Object> getListValue() {
        return this._listValue;
    }

    public List<SchemaType> getListTypes() {
        return this._listTypes;
    }

    public SchemaType getUnionType() {
        return this._unionType;
    }
}
