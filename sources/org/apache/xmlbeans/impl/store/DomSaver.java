package org.apache.xmlbeans.impl.store;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Saver;
import org.w3c.dom.Node;

final class DomSaver extends Saver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean _isFrag;
    private Cur _nodeCur;
    private final XmlOptions _options;
    private final SchemaTypeLoader _stl;
    private SchemaType _type;

    /* access modifiers changed from: protected */
    public void emitEndDoc(Saver.SaveCur saveCur) {
    }

    DomSaver(Cur cur, boolean z, XmlOptions xmlOptions) {
        super(cur, xmlOptions);
        if (cur.isUserNode()) {
            this._type = cur.getUser().get_schema_type();
        }
        this._stl = cur._locale._schemaTypeLoader;
        this._options = xmlOptions;
        this._isFrag = z;
    }

    /* access modifiers changed from: package-private */
    public Node saveDom() {
        Locale locale = Locale.getLocale(this._stl, this._options);
        locale.enter();
        try {
            this._nodeCur = locale.getCur();
            while (process()) {
            }
            while (!this._nodeCur.isRoot()) {
                this._nodeCur.toParent();
            }
            SchemaType schemaType = this._type;
            if (schemaType != null) {
                this._nodeCur.setType(schemaType);
            }
            Node node = (Node) this._nodeCur.getDom();
            this._nodeCur.release();
            this._nodeCur = null;
            return node;
        } finally {
            locale.exit();
        }
    }

    /* access modifiers changed from: protected */
    public boolean emitElement(Saver.SaveCur saveCur, List<QName> list, List<String> list2) {
        if (Locale.isFragmentQName(saveCur.getName())) {
            this._nodeCur.moveTo((Xobj) null, -2);
        }
        ensureDoc();
        this._nodeCur.createElement(getQualifiedName(saveCur, saveCur.getName()));
        this._nodeCur.next();
        iterateMappings();
        while (hasMapping()) {
            Cur cur = this._nodeCur;
            cur.createAttr(cur._locale.createXmlns(mappingPrefix()));
            this._nodeCur.next();
            this._nodeCur.insertString(mappingUri());
            this._nodeCur.toParent();
            this._nodeCur.skipWithAttrs();
            nextMapping();
        }
        for (int i = 0; i < list.size(); i++) {
            this._nodeCur.createAttr(getQualifiedName(saveCur, list.get(i)));
            this._nodeCur.next();
            this._nodeCur.insertString(list2.get(i));
            this._nodeCur.toParent();
            this._nodeCur.skipWithAttrs();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void emitFinish(Saver.SaveCur saveCur) {
        if (!Locale.isFragmentQName(saveCur.getName())) {
            this._nodeCur.next();
        }
    }

    /* access modifiers changed from: protected */
    public void emitText(Saver.SaveCur saveCur) {
        ensureDoc();
        Object chars = saveCur.getChars();
        if (saveCur._cchSrc > 0) {
            this._nodeCur.insertChars(chars, saveCur._offSrc, saveCur._cchSrc);
            this._nodeCur.next();
        }
    }

    /* access modifiers changed from: protected */
    public void emitComment(Saver.SaveCur saveCur) {
        ensureDoc();
        this._nodeCur.createComment();
        emitTextValue(saveCur);
        this._nodeCur.skip();
    }

    /* access modifiers changed from: protected */
    public void emitProcinst(Saver.SaveCur saveCur) {
        ensureDoc();
        this._nodeCur.createProcinst(saveCur.getName().getLocalPart());
        emitTextValue(saveCur);
        this._nodeCur.skip();
    }

    /* access modifiers changed from: protected */
    public void emitDocType(String str, String str2, String str3) {
        ensureDoc();
        XmlDocumentProperties docProps = Locale.getDocProps(this._nodeCur, true);
        docProps.setDoctypeName(str);
        docProps.setDoctypePublicId(str2);
        docProps.setDoctypeSystemId(str3);
    }

    /* access modifiers changed from: protected */
    public void emitStartDoc(Saver.SaveCur saveCur) {
        ensureDoc();
    }

    private QName getQualifiedName(Saver.SaveCur saveCur, QName qName) {
        String namespaceURI = qName.getNamespaceURI();
        String uriMapping = namespaceURI.length() > 0 ? getUriMapping(namespaceURI) : "";
        if (uriMapping.equals(qName.getPrefix())) {
            return qName;
        }
        return this._nodeCur._locale.makeQName(namespaceURI, qName.getLocalPart(), uriMapping);
    }

    private void emitTextValue(Saver.SaveCur saveCur) {
        saveCur.push();
        saveCur.next();
        if (saveCur.isText()) {
            this._nodeCur.next();
            this._nodeCur.insertChars(saveCur.getChars(), saveCur._offSrc, saveCur._cchSrc);
            this._nodeCur.toParent();
        }
        saveCur.pop();
    }

    private void ensureDoc() {
        if (!this._nodeCur.isPositioned()) {
            if (this._isFrag) {
                this._nodeCur.createDomDocFragRoot();
            } else {
                this._nodeCur.createDomDocumentRoot();
            }
            this._nodeCur.next();
        }
    }
}
