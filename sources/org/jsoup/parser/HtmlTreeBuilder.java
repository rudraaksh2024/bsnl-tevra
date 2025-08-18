package org.jsoup.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.InputDeviceCompat;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.logging.log4j.util.Chars;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

public class HtmlTreeBuilder extends TreeBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MaxScopeSearchDepth = 100;
    static final String[] TagSearchButton = {"button"};
    static final String[] TagSearchEndTags = {"dd", "dt", "li", "optgroup", "option", "p", "rb", "rp", "rt", "rtc"};
    static final String[] TagSearchList = {"ol", "ul"};
    static final String[] TagSearchSelectScope = {"optgroup", "option"};
    static final String[] TagSearchSpecial = {"address", "applet", "area", "article", "aside", "base", "basefont", "bgsound", "blockquote", "body", CompressorStreamFactory.BROTLI, "button", "caption", "center", "col", "colgroup", "command", "dd", "details", "dir", "div", "dl", "dt", "embed", "fieldset", "figcaption", "figure", "footer", "form", TypedValues.AttributesType.S_FRAME, "frameset", "h1", "h2", "h3", "h4", "h5", "h6", "head", "header", "hgroup", "hr", "html", "iframe", "img", "input", "isindex", "li", "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", "object", "ol", "p", "param", "plaintext", "pre", "script", "section", "select", "style", "summary", "table", "tbody", "td", "textarea", "tfoot", "th", "thead", "title", "tr", "ul", "wbr", "xmp"};
    static final String[] TagSearchTableScope = {"html", "table"};
    static final String[] TagThoroughSearchEndTags = {"caption", "colgroup", "dd", "dt", "li", "optgroup", "option", "p", "rb", "rp", "rt", "rtc", "tbody", "td", "tfoot", "th", "thead", "tr"};
    static final String[] TagsSearchInScope = {"applet", "caption", "html", "marquee", "object", "table", "td", "th"};
    private static final int maxQueueDepth = 256;
    private static final int maxUsedFormattingElements = 12;
    private boolean baseUriSetFromDoc;
    @Nullable
    private Element contextElement;
    private Token.EndTag emptyEnd;
    @Nullable
    private FormElement formElement;
    private ArrayList<Element> formattingElements;
    private boolean fosterInserts;
    private boolean fragmentParsing;
    private boolean framesetOk;
    @Nullable
    private Element headElement;
    private HtmlTreeBuilderState originalState;
    private List<String> pendingTableCharacters;
    private String[] specificScopeTarget = {null};
    private HtmlTreeBuilderState state;
    private ArrayList<HtmlTreeBuilderState> tmplInsertMode;

    public /* bridge */ /* synthetic */ boolean processStartTag(String str, Attributes attributes) {
        return super.processStartTag(str, attributes);
    }

    /* access modifiers changed from: package-private */
    public ParseSettings defaultSettings() {
        return ParseSettings.htmlDefault;
    }

    /* access modifiers changed from: package-private */
    public HtmlTreeBuilder newInstance() {
        return new HtmlTreeBuilder();
    }

    /* access modifiers changed from: protected */
    @ParametersAreNonnullByDefault
    public void initialiseParse(Reader reader, String str, Parser parser) {
        super.initialiseParse(reader, str, parser);
        this.state = HtmlTreeBuilderState.Initial;
        this.originalState = null;
        this.baseUriSetFromDoc = false;
        this.headElement = null;
        this.formElement = null;
        this.contextElement = null;
        this.formattingElements = new ArrayList<>();
        this.tmplInsertMode = new ArrayList<>();
        this.pendingTableCharacters = new ArrayList();
        this.emptyEnd = new Token.EndTag();
        this.framesetOk = true;
        this.fosterInserts = false;
        this.fragmentParsing = false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a6, code lost:
        if (r0.equals("iframe") == false) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<org.jsoup.nodes.Node> parseFragment(java.lang.String r3, @javax.annotation.Nullable org.jsoup.nodes.Element r4, java.lang.String r5, org.jsoup.parser.Parser r6) {
        /*
            r2 = this;
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.Initial
            r2.state = r0
            java.io.StringReader r0 = new java.io.StringReader
            r0.<init>(r3)
            r2.initialiseParse(r0, r5, r6)
            r2.contextElement = r4
            r3 = 1
            r2.fragmentParsing = r3
            r6 = -1
            if (r4 == 0) goto L_0x011b
            org.jsoup.nodes.Document r0 = r4.ownerDocument()
            if (r0 == 0) goto L_0x0027
            org.jsoup.nodes.Document r0 = r2.doc
            org.jsoup.nodes.Document r1 = r4.ownerDocument()
            org.jsoup.nodes.Document$QuirksMode r1 = r1.quirksMode()
            r0.quirksMode(r1)
        L_0x0027:
            java.lang.String r0 = r4.normalName()
            r0.hashCode()
            int r1 = r0.hashCode()
            switch(r1) {
                case -1321546630: goto L_0x00a9;
                case -1191214428: goto L_0x00a0;
                case -1003243718: goto L_0x0095;
                case -907685685: goto L_0x008a;
                case 118807: goto L_0x007f;
                case 109780401: goto L_0x0074;
                case 110371416: goto L_0x0069;
                case 1192721831: goto L_0x005e;
                case 1551550924: goto L_0x0052;
                case 1973234167: goto L_0x0045;
                case 2115613112: goto L_0x0038;
                default: goto L_0x0035;
            }
        L_0x0035:
            r3 = r6
            goto L_0x00b3
        L_0x0038:
            java.lang.String r3 = "noembed"
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x0041
            goto L_0x0035
        L_0x0041:
            r3 = 10
            goto L_0x00b3
        L_0x0045:
            java.lang.String r3 = "plaintext"
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x004e
            goto L_0x0035
        L_0x004e:
            r3 = 9
            goto L_0x00b3
        L_0x0052:
            java.lang.String r3 = "noscript"
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x005b
            goto L_0x0035
        L_0x005b:
            r3 = 8
            goto L_0x00b3
        L_0x005e:
            java.lang.String r3 = "noframes"
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x0067
            goto L_0x0035
        L_0x0067:
            r3 = 7
            goto L_0x00b3
        L_0x0069:
            java.lang.String r3 = "title"
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x0072
            goto L_0x0035
        L_0x0072:
            r3 = 6
            goto L_0x00b3
        L_0x0074:
            java.lang.String r3 = "style"
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x007d
            goto L_0x0035
        L_0x007d:
            r3 = 5
            goto L_0x00b3
        L_0x007f:
            java.lang.String r3 = "xml"
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x0088
            goto L_0x0035
        L_0x0088:
            r3 = 4
            goto L_0x00b3
        L_0x008a:
            java.lang.String r3 = "script"
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x0093
            goto L_0x0035
        L_0x0093:
            r3 = 3
            goto L_0x00b3
        L_0x0095:
            java.lang.String r3 = "textarea"
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x009e
            goto L_0x0035
        L_0x009e:
            r3 = 2
            goto L_0x00b3
        L_0x00a0:
            java.lang.String r1 = "iframe"
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L_0x00b3
            goto L_0x0035
        L_0x00a9:
            java.lang.String r3 = "template"
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x00b2
            goto L_0x0035
        L_0x00b2:
            r3 = 0
        L_0x00b3:
            switch(r3) {
                case 0: goto L_0x00e6;
                case 1: goto L_0x00de;
                case 2: goto L_0x00d6;
                case 3: goto L_0x00ce;
                case 4: goto L_0x00de;
                case 5: goto L_0x00de;
                case 6: goto L_0x00d6;
                case 7: goto L_0x00de;
                case 8: goto L_0x00c6;
                case 9: goto L_0x00be;
                case 10: goto L_0x00de;
                default: goto L_0x00b6;
            }
        L_0x00b6:
            org.jsoup.parser.Tokeniser r3 = r2.tokeniser
            org.jsoup.parser.TokeniserState r1 = org.jsoup.parser.TokeniserState.Data
            r3.transition(r1)
            goto L_0x00f2
        L_0x00be:
            org.jsoup.parser.Tokeniser r3 = r2.tokeniser
            org.jsoup.parser.TokeniserState r1 = org.jsoup.parser.TokeniserState.PLAINTEXT
            r3.transition(r1)
            goto L_0x00f2
        L_0x00c6:
            org.jsoup.parser.Tokeniser r3 = r2.tokeniser
            org.jsoup.parser.TokeniserState r1 = org.jsoup.parser.TokeniserState.Data
            r3.transition(r1)
            goto L_0x00f2
        L_0x00ce:
            org.jsoup.parser.Tokeniser r3 = r2.tokeniser
            org.jsoup.parser.TokeniserState r1 = org.jsoup.parser.TokeniserState.ScriptData
            r3.transition(r1)
            goto L_0x00f2
        L_0x00d6:
            org.jsoup.parser.Tokeniser r3 = r2.tokeniser
            org.jsoup.parser.TokeniserState r1 = org.jsoup.parser.TokeniserState.Rcdata
            r3.transition(r1)
            goto L_0x00f2
        L_0x00de:
            org.jsoup.parser.Tokeniser r3 = r2.tokeniser
            org.jsoup.parser.TokeniserState r1 = org.jsoup.parser.TokeniserState.Rawtext
            r3.transition(r1)
            goto L_0x00f2
        L_0x00e6:
            org.jsoup.parser.Tokeniser r3 = r2.tokeniser
            org.jsoup.parser.TokeniserState r1 = org.jsoup.parser.TokeniserState.Data
            r3.transition(r1)
            org.jsoup.parser.HtmlTreeBuilderState r3 = org.jsoup.parser.HtmlTreeBuilderState.InTemplate
            r2.pushTemplateMode(r3)
        L_0x00f2:
            org.jsoup.nodes.Element r3 = new org.jsoup.nodes.Element
            org.jsoup.parser.ParseSettings r1 = r2.settings
            org.jsoup.parser.Tag r0 = r2.tagFor(r0, r1)
            r3.<init>(r0, r5)
            org.jsoup.nodes.Document r5 = r2.doc
            r5.appendChild(r3)
            java.util.ArrayList r5 = r2.stack
            r5.add(r3)
            r2.resetInsertionMode()
            r5 = r4
        L_0x010b:
            if (r5 == 0) goto L_0x011c
            boolean r0 = r5 instanceof org.jsoup.nodes.FormElement
            if (r0 == 0) goto L_0x0116
            org.jsoup.nodes.FormElement r5 = (org.jsoup.nodes.FormElement) r5
            r2.formElement = r5
            goto L_0x011c
        L_0x0116:
            org.jsoup.nodes.Element r5 = r5.parent()
            goto L_0x010b
        L_0x011b:
            r3 = 0
        L_0x011c:
            r2.runParser()
            if (r4 == 0) goto L_0x0133
            java.util.List r2 = r3.siblingNodes()
            boolean r4 = r2.isEmpty()
            if (r4 != 0) goto L_0x012e
            r3.insertChildren((int) r6, (java.util.Collection<? extends org.jsoup.nodes.Node>) r2)
        L_0x012e:
            java.util.List r2 = r3.childNodes()
            return r2
        L_0x0133:
            org.jsoup.nodes.Document r2 = r2.doc
            java.util.List r2 = r2.childNodes()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilder.parseFragment(java.lang.String, org.jsoup.nodes.Element, java.lang.String, org.jsoup.parser.Parser):java.util.List");
    }

    /* access modifiers changed from: protected */
    public boolean process(Token token) {
        this.currentToken = token;
        return this.state.process(token, this);
    }

    /* access modifiers changed from: package-private */
    public boolean process(Token token, HtmlTreeBuilderState htmlTreeBuilderState) {
        this.currentToken = token;
        return htmlTreeBuilderState.process(token, this);
    }

    /* access modifiers changed from: package-private */
    public void transition(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.state = htmlTreeBuilderState;
    }

    /* access modifiers changed from: package-private */
    public HtmlTreeBuilderState state() {
        return this.state;
    }

    /* access modifiers changed from: package-private */
    public void markInsertionMode() {
        this.originalState = this.state;
    }

    /* access modifiers changed from: package-private */
    public HtmlTreeBuilderState originalState() {
        return this.originalState;
    }

    /* access modifiers changed from: package-private */
    public void framesetOk(boolean z) {
        this.framesetOk = z;
    }

    /* access modifiers changed from: package-private */
    public boolean framesetOk() {
        return this.framesetOk;
    }

    /* access modifiers changed from: package-private */
    public Document getDocument() {
        return this.doc;
    }

    /* access modifiers changed from: package-private */
    public String getBaseUri() {
        return this.baseUri;
    }

    /* access modifiers changed from: package-private */
    public void maybeSetBaseUri(Element element) {
        if (!this.baseUriSetFromDoc) {
            String absUrl = element.absUrl("href");
            if (absUrl.length() != 0) {
                this.baseUri = absUrl;
                this.baseUriSetFromDoc = true;
                this.doc.setBaseUri(absUrl);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isFragmentParsing() {
        return this.fragmentParsing;
    }

    /* access modifiers changed from: package-private */
    public void error(HtmlTreeBuilderState htmlTreeBuilderState) {
        if (this.parser.getErrors().canAddError()) {
            this.parser.getErrors().add(new ParseError(this.reader, "Unexpected %s token [%s] when in state [%s]", this.currentToken.tokenType(), this.currentToken, htmlTreeBuilderState));
        }
    }

    /* access modifiers changed from: package-private */
    public Element insert(Token.StartTag startTag) {
        if (startTag.hasAttributes() && !startTag.attributes.isEmpty() && startTag.attributes.deduplicate(this.settings) > 0) {
            error("Dropped duplicate attribute(s) in tag [%s]", startTag.normalName);
        }
        if (startTag.isSelfClosing()) {
            Element insertEmpty = insertEmpty(startTag);
            this.stack.add(insertEmpty);
            this.tokeniser.transition(TokeniserState.Data);
            this.tokeniser.emit((Token) this.emptyEnd.reset().name(insertEmpty.tagName()));
            return insertEmpty;
        }
        Element element = new Element(tagFor(startTag.name(), this.settings), (String) null, this.settings.normalizeAttributes(startTag.attributes));
        insert(element);
        return element;
    }

    /* access modifiers changed from: package-private */
    public Element insertStartTag(String str) {
        Element element = new Element(tagFor(str, this.settings), (String) null);
        insert(element);
        return element;
    }

    /* access modifiers changed from: package-private */
    public void insert(Element element) {
        insertNode(element);
        this.stack.add(element);
    }

    /* access modifiers changed from: package-private */
    public Element insertEmpty(Token.StartTag startTag) {
        Tag tagFor = tagFor(startTag.name(), this.settings);
        Element element = new Element(tagFor, (String) null, this.settings.normalizeAttributes(startTag.attributes));
        insertNode(element);
        if (startTag.isSelfClosing()) {
            if (!tagFor.isKnownTag()) {
                tagFor.setSelfClosing();
            } else if (!tagFor.isEmpty()) {
                this.tokeniser.error("Tag [%s] cannot be self closing; not a void tag", tagFor.normalName());
            }
        }
        return element;
    }

    /* access modifiers changed from: package-private */
    public FormElement insertForm(Token.StartTag startTag, boolean z, boolean z2) {
        FormElement formElement2 = new FormElement(tagFor(startTag.name(), this.settings), (String) null, this.settings.normalizeAttributes(startTag.attributes));
        if (!z2) {
            setFormElement(formElement2);
        } else if (!onStack("template")) {
            setFormElement(formElement2);
        }
        insertNode(formElement2);
        if (z) {
            this.stack.add(formElement2);
        }
        return formElement2;
    }

    /* access modifiers changed from: package-private */
    public void insert(Token.Comment comment) {
        insertNode(new Comment(comment.getData()));
    }

    /* access modifiers changed from: package-private */
    public void insert(Token.Character character) {
        Node node;
        Element currentElement = currentElement();
        String normalName = currentElement.normalName();
        String data = character.getData();
        if (character.isCData()) {
            node = new CDataNode(data);
        } else if (isContentForTagData(normalName)) {
            node = new DataNode(data);
        } else {
            node = new TextNode(data);
        }
        currentElement.appendChild(node);
    }

    private void insertNode(Node node) {
        FormElement formElement2;
        if (this.stack.isEmpty()) {
            this.doc.appendChild(node);
        } else if (!isFosterInserts() || !StringUtil.inSorted(currentElement().normalName(), HtmlTreeBuilderState.Constants.InTableFoster)) {
            currentElement().appendChild(node);
        } else {
            insertInFosterParent(node);
        }
        if (node instanceof Element) {
            Element element = (Element) node;
            if (element.tag().isFormListed() && (formElement2 = this.formElement) != null) {
                formElement2.addElement(element);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Element pop() {
        return (Element) this.stack.remove(this.stack.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public void push(Element element) {
        this.stack.add(element);
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Element> getStack() {
        return this.stack;
    }

    /* access modifiers changed from: package-private */
    public boolean onStack(Element element) {
        return onStack(this.stack, element);
    }

    /* access modifiers changed from: package-private */
    public boolean onStack(String str) {
        return getFromStack(str) != null;
    }

    private static boolean onStack(ArrayList<Element> arrayList, Element element) {
        int size = arrayList.size() - 1;
        int i = size >= 256 ? size + InputDeviceCompat.SOURCE_ANY : 0;
        while (size >= i) {
            if (arrayList.get(size) == element) {
                return true;
            }
            size--;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Element getFromStack(String str) {
        int size = this.stack.size() - 1;
        int i = size >= 256 ? size + InputDeviceCompat.SOURCE_ANY : 0;
        while (size >= i) {
            Element element = (Element) this.stack.get(size);
            if (element.normalName().equals(str)) {
                return element;
            }
            size--;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean removeFromStack(Element element) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            if (((Element) this.stack.get(size)) == element) {
                this.stack.remove(size);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Element popStackToClose(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element element = (Element) this.stack.get(size);
            this.stack.remove(size);
            if (element.normalName().equals(str)) {
                return element;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void popStackToClose(String... strArr) {
        int size = this.stack.size() - 1;
        while (size >= 0) {
            this.stack.remove(size);
            if (!StringUtil.inSorted(((Element) this.stack.get(size)).normalName(), strArr)) {
                size--;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void popStackToBefore(String str) {
        int size = this.stack.size() - 1;
        while (size >= 0 && !((Element) this.stack.get(size)).normalName().equals(str)) {
            this.stack.remove(size);
            size--;
        }
    }

    /* access modifiers changed from: package-private */
    public void clearStackToTableContext() {
        clearStackToContext("table", "template");
    }

    /* access modifiers changed from: package-private */
    public void clearStackToTableBodyContext() {
        clearStackToContext("tbody", "tfoot", "thead", "template");
    }

    /* access modifiers changed from: package-private */
    public void clearStackToTableRowContext() {
        clearStackToContext("tr", "template");
    }

    private void clearStackToContext(String... strArr) {
        int size = this.stack.size() - 1;
        while (size >= 0) {
            Element element = (Element) this.stack.get(size);
            if (!StringUtil.in(element.normalName(), strArr) && !element.normalName().equals("html")) {
                this.stack.remove(size);
                size--;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Element aboveOnStack(Element element) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            if (((Element) this.stack.get(size)) == element) {
                return (Element) this.stack.get(size - 1);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void insertOnStackAfter(Element element, Element element2) {
        int lastIndexOf = this.stack.lastIndexOf(element);
        Validate.isTrue(lastIndexOf != -1);
        this.stack.add(lastIndexOf + 1, element2);
    }

    /* access modifiers changed from: package-private */
    public void replaceOnStack(Element element, Element element2) {
        replaceInQueue(this.stack, element, element2);
    }

    private void replaceInQueue(ArrayList<Element> arrayList, Element element, Element element2) {
        int lastIndexOf = arrayList.lastIndexOf(element);
        Validate.isTrue(lastIndexOf != -1);
        arrayList.set(lastIndexOf, element2);
    }

    /* access modifiers changed from: package-private */
    public void resetInsertionMode() {
        int size = this.stack.size() - 1;
        int i = size >= 256 ? size + InputDeviceCompat.SOURCE_ANY : 0;
        if (this.stack.size() == 0) {
            transition(HtmlTreeBuilderState.InBody);
        }
        boolean z = false;
        while (size >= i) {
            Element element = (Element) this.stack.get(size);
            if (size == i) {
                if (this.fragmentParsing) {
                    element = this.contextElement;
                }
                z = true;
            }
            String normalName = element != null ? element.normalName() : "";
            normalName.hashCode();
            char c = 65535;
            switch (normalName.hashCode()) {
                case -1644953643:
                    if (normalName.equals("frameset")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1321546630:
                    if (normalName.equals("template")) {
                        c = 1;
                        break;
                    }
                    break;
                case -906021636:
                    if (normalName.equals("select")) {
                        c = 2;
                        break;
                    }
                    break;
                case -636197633:
                    if (normalName.equals("colgroup")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3696:
                    if (normalName.equals("td")) {
                        c = 4;
                        break;
                    }
                    break;
                case 3700:
                    if (normalName.equals("th")) {
                        c = 5;
                        break;
                    }
                    break;
                case 3710:
                    if (normalName.equals("tr")) {
                        c = 6;
                        break;
                    }
                    break;
                case 3029410:
                    if (normalName.equals("body")) {
                        c = 7;
                        break;
                    }
                    break;
                case 3198432:
                    if (normalName.equals("head")) {
                        c = 8;
                        break;
                    }
                    break;
                case 3213227:
                    if (normalName.equals("html")) {
                        c = 9;
                        break;
                    }
                    break;
                case 110115790:
                    if (normalName.equals("table")) {
                        c = 10;
                        break;
                    }
                    break;
                case 110157846:
                    if (normalName.equals("tbody")) {
                        c = 11;
                        break;
                    }
                    break;
                case 110277346:
                    if (normalName.equals("tfoot")) {
                        c = 12;
                        break;
                    }
                    break;
                case 110326868:
                    if (normalName.equals("thead")) {
                        c = Chars.CR;
                        break;
                    }
                    break;
                case 552573414:
                    if (normalName.equals("caption")) {
                        c = 14;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    transition(HtmlTreeBuilderState.InFrameset);
                    return;
                case 1:
                    HtmlTreeBuilderState currentTemplateMode = currentTemplateMode();
                    Validate.notNull(currentTemplateMode, "Bug: no template insertion mode on stack!");
                    transition(currentTemplateMode);
                    return;
                case 2:
                    transition(HtmlTreeBuilderState.InSelect);
                    return;
                case 3:
                    transition(HtmlTreeBuilderState.InColumnGroup);
                    return;
                case 4:
                case 5:
                    if (!z) {
                        transition(HtmlTreeBuilderState.InCell);
                        return;
                    }
                    break;
                case 6:
                    transition(HtmlTreeBuilderState.InRow);
                    return;
                case 7:
                    transition(HtmlTreeBuilderState.InBody);
                    return;
                case 8:
                    if (!z) {
                        transition(HtmlTreeBuilderState.InHead);
                        return;
                    }
                    break;
                case 9:
                    transition(this.headElement == null ? HtmlTreeBuilderState.BeforeHead : HtmlTreeBuilderState.AfterHead);
                    return;
                case 10:
                    transition(HtmlTreeBuilderState.InTable);
                    return;
                case 11:
                case 12:
                case 13:
                    transition(HtmlTreeBuilderState.InTableBody);
                    return;
                case 14:
                    transition(HtmlTreeBuilderState.InCaption);
                    return;
            }
            if (z) {
                transition(HtmlTreeBuilderState.InBody);
                return;
            }
            size--;
        }
    }

    private boolean inSpecificScope(String str, String[] strArr, String[] strArr2) {
        String[] strArr3 = this.specificScopeTarget;
        strArr3[0] = str;
        return inSpecificScope(strArr3, strArr, strArr2);
    }

    private boolean inSpecificScope(String[] strArr, String[] strArr2, String[] strArr3) {
        int size = this.stack.size() - 1;
        int i = size > 100 ? size - 100 : 0;
        while (size >= i) {
            String normalName = ((Element) this.stack.get(size)).normalName();
            if (StringUtil.inSorted(normalName, strArr)) {
                return true;
            }
            if (StringUtil.inSorted(normalName, strArr2)) {
                return false;
            }
            if (strArr3 != null && StringUtil.inSorted(normalName, strArr3)) {
                return false;
            }
            size--;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean inScope(String[] strArr) {
        return inSpecificScope(strArr, TagsSearchInScope, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public boolean inScope(String str) {
        return inScope(str, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public boolean inScope(String str, String[] strArr) {
        return inSpecificScope(str, TagsSearchInScope, strArr);
    }

    /* access modifiers changed from: package-private */
    public boolean inListItemScope(String str) {
        return inScope(str, TagSearchList);
    }

    /* access modifiers changed from: package-private */
    public boolean inButtonScope(String str) {
        return inScope(str, TagSearchButton);
    }

    /* access modifiers changed from: package-private */
    public boolean inTableScope(String str) {
        return inSpecificScope(str, TagSearchTableScope, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public boolean inSelectScope(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            String normalName = ((Element) this.stack.get(size)).normalName();
            if (normalName.equals(str)) {
                return true;
            }
            if (!StringUtil.inSorted(normalName, TagSearchSelectScope)) {
                return false;
            }
        }
        Validate.fail("Should not be reachable");
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setHeadElement(Element element) {
        this.headElement = element;
    }

    /* access modifiers changed from: package-private */
    public Element getHeadElement() {
        return this.headElement;
    }

    /* access modifiers changed from: package-private */
    public boolean isFosterInserts() {
        return this.fosterInserts;
    }

    /* access modifiers changed from: package-private */
    public void setFosterInserts(boolean z) {
        this.fosterInserts = z;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public FormElement getFormElement() {
        return this.formElement;
    }

    /* access modifiers changed from: package-private */
    public void setFormElement(FormElement formElement2) {
        this.formElement = formElement2;
    }

    /* access modifiers changed from: package-private */
    public void newPendingTableCharacters() {
        this.pendingTableCharacters = new ArrayList();
    }

    /* access modifiers changed from: package-private */
    public List<String> getPendingTableCharacters() {
        return this.pendingTableCharacters;
    }

    /* access modifiers changed from: package-private */
    public void generateImpliedEndTags(String str) {
        while (StringUtil.inSorted(currentElement().normalName(), TagSearchEndTags)) {
            if (str == null || !currentElementIs(str)) {
                pop();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void generateImpliedEndTags() {
        generateImpliedEndTags(false);
    }

    /* access modifiers changed from: package-private */
    public void generateImpliedEndTags(boolean z) {
        String[] strArr = z ? TagThoroughSearchEndTags : TagSearchEndTags;
        while (StringUtil.inSorted(currentElement().normalName(), strArr)) {
            pop();
        }
    }

    /* access modifiers changed from: package-private */
    public void closeElement(String str) {
        generateImpliedEndTags(str);
        if (!str.equals(currentElement().normalName())) {
            error(state());
        }
        popStackToClose(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isSpecial(Element element) {
        return StringUtil.inSorted(element.normalName(), TagSearchSpecial);
    }

    /* access modifiers changed from: package-private */
    public Element lastFormattingElement() {
        if (this.formattingElements.size() <= 0) {
            return null;
        }
        ArrayList<Element> arrayList = this.formattingElements;
        return arrayList.get(arrayList.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public int positionOfElement(Element element) {
        for (int i = 0; i < this.formattingElements.size(); i++) {
            if (element == this.formattingElements.get(i)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public Element removeLastFormattingElement() {
        int size = this.formattingElements.size();
        if (size > 0) {
            return this.formattingElements.remove(size - 1);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void pushActiveFormattingElements(Element element) {
        checkActiveFormattingElements(element);
        this.formattingElements.add(element);
    }

    /* access modifiers changed from: package-private */
    public void pushWithBookmark(Element element, int i) {
        checkActiveFormattingElements(element);
        try {
            this.formattingElements.add(i, element);
        } catch (IndexOutOfBoundsException unused) {
            this.formattingElements.add(element);
        }
    }

    /* access modifiers changed from: package-private */
    public void checkActiveFormattingElements(Element element) {
        int size = this.formattingElements.size() - 1;
        int i = 0;
        while (size >= 0) {
            Element element2 = this.formattingElements.get(size);
            if (element2 != null) {
                if (isSameFormattingElement(element, element2)) {
                    i++;
                }
                if (i == 3) {
                    this.formattingElements.remove(size);
                    return;
                }
                size--;
            } else {
                return;
            }
        }
    }

    private boolean isSameFormattingElement(Element element, Element element2) {
        return element.normalName().equals(element2.normalName()) && element.attributes().equals(element2.attributes());
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    void reconstructFormattingElements() {
        /*
            r7 = this;
            org.jsoup.nodes.Element r0 = r7.lastFormattingElement()
            if (r0 == 0) goto L_0x0061
            boolean r1 = r7.onStack((org.jsoup.nodes.Element) r0)
            if (r1 == 0) goto L_0x000d
            goto L_0x0061
        L_0x000d:
            java.util.ArrayList<org.jsoup.nodes.Element> r1 = r7.formattingElements
            int r1 = r1.size()
            int r2 = r1 + -12
            r3 = 0
            if (r2 >= 0) goto L_0x0019
            r2 = r3
        L_0x0019:
            r4 = 1
            int r1 = r1 - r4
            r5 = r1
        L_0x001c:
            if (r5 != r2) goto L_0x001f
            goto L_0x0032
        L_0x001f:
            java.util.ArrayList<org.jsoup.nodes.Element> r0 = r7.formattingElements
            int r5 = r5 + -1
            java.lang.Object r0 = r0.get(r5)
            org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
            if (r0 == 0) goto L_0x0031
            boolean r6 = r7.onStack((org.jsoup.nodes.Element) r0)
            if (r6 == 0) goto L_0x001c
        L_0x0031:
            r4 = r3
        L_0x0032:
            if (r4 != 0) goto L_0x003e
            java.util.ArrayList<org.jsoup.nodes.Element> r0 = r7.formattingElements
            int r5 = r5 + 1
            java.lang.Object r0 = r0.get(r5)
            org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
        L_0x003e:
            org.jsoup.helper.Validate.notNull(r0)
            java.lang.String r2 = r0.normalName()
            org.jsoup.nodes.Element r2 = r7.insertStartTag(r2)
            int r4 = r0.attributesSize()
            if (r4 <= 0) goto L_0x005a
            org.jsoup.nodes.Attributes r4 = r2.attributes()
            org.jsoup.nodes.Attributes r6 = r0.attributes()
            r4.addAll(r6)
        L_0x005a:
            java.util.ArrayList<org.jsoup.nodes.Element> r4 = r7.formattingElements
            r4.set(r5, r2)
            if (r5 != r1) goto L_0x0031
        L_0x0061:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilder.reconstructFormattingElements():void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:3:0x000c, LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clearFormattingElementsToLastMarker() {
        /*
            r1 = this;
        L_0x0000:
            java.util.ArrayList<org.jsoup.nodes.Element> r0 = r1.formattingElements
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x000e
            org.jsoup.nodes.Element r0 = r1.removeLastFormattingElement()
            if (r0 != 0) goto L_0x0000
        L_0x000e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilder.clearFormattingElementsToLastMarker():void");
    }

    /* access modifiers changed from: package-private */
    public void removeFromActiveFormattingElements(Element element) {
        for (int size = this.formattingElements.size() - 1; size >= 0; size--) {
            if (this.formattingElements.get(size) == element) {
                this.formattingElements.remove(size);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isInActiveFormattingElements(Element element) {
        return onStack(this.formattingElements, element);
    }

    /* access modifiers changed from: package-private */
    public Element getActiveFormattingElement(String str) {
        for (int size = this.formattingElements.size() - 1; size >= 0; size--) {
            Element element = this.formattingElements.get(size);
            if (element == null) {
                return null;
            }
            if (element.normalName().equals(str)) {
                return element;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void replaceActiveFormattingElement(Element element, Element element2) {
        replaceInQueue(this.formattingElements, element, element2);
    }

    /* access modifiers changed from: package-private */
    public void insertMarkerToFormattingElements() {
        this.formattingElements.add((Object) null);
    }

    /* access modifiers changed from: package-private */
    public void insertInFosterParent(Node node) {
        Element element;
        Element fromStack = getFromStack("table");
        boolean z = false;
        if (fromStack == null) {
            element = (Element) this.stack.get(0);
        } else if (fromStack.parent() != null) {
            element = fromStack.parent();
            z = true;
        } else {
            element = aboveOnStack(fromStack);
        }
        if (z) {
            Validate.notNull(fromStack);
            fromStack.before(node);
            return;
        }
        element.appendChild(node);
    }

    /* access modifiers changed from: package-private */
    public void pushTemplateMode(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.tmplInsertMode.add(htmlTreeBuilderState);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public HtmlTreeBuilderState popTemplateMode() {
        if (this.tmplInsertMode.size() <= 0) {
            return null;
        }
        ArrayList<HtmlTreeBuilderState> arrayList = this.tmplInsertMode;
        return arrayList.remove(arrayList.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public int templateModeSize() {
        return this.tmplInsertMode.size();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public HtmlTreeBuilderState currentTemplateMode() {
        if (this.tmplInsertMode.size() <= 0) {
            return null;
        }
        ArrayList<HtmlTreeBuilderState> arrayList = this.tmplInsertMode;
        return arrayList.get(arrayList.size() - 1);
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.currentToken + ", state=" + this.state + ", currentElement=" + currentElement() + '}';
    }

    /* access modifiers changed from: protected */
    public boolean isContentForTagData(String str) {
        return str.equals("script") || str.equals("style");
    }
}
