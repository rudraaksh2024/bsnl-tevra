package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

@Deprecated
public class Whitelist extends Safelist {
    public Whitelist() {
    }

    public Whitelist(Safelist safelist) {
        super(safelist);
    }

    public static Whitelist basic() {
        return new Whitelist(Safelist.basic());
    }

    public static Whitelist basicWithImages() {
        return new Whitelist(Safelist.basicWithImages());
    }

    public static Whitelist none() {
        return new Whitelist(Safelist.none());
    }

    public static Whitelist relaxed() {
        return new Whitelist(Safelist.relaxed());
    }

    public static Whitelist simpleText() {
        return new Whitelist(Safelist.simpleText());
    }

    public Whitelist addTags(String... strArr) {
        super.addTags(strArr);
        return this;
    }

    public Whitelist removeTags(String... strArr) {
        super.removeTags(strArr);
        return this;
    }

    public Whitelist addAttributes(String str, String... strArr) {
        super.addAttributes(str, strArr);
        return this;
    }

    public Whitelist removeAttributes(String str, String... strArr) {
        super.removeAttributes(str, strArr);
        return this;
    }

    public Whitelist addEnforcedAttribute(String str, String str2, String str3) {
        super.addEnforcedAttribute(str, str2, str3);
        return this;
    }

    public Whitelist removeEnforcedAttribute(String str, String str2) {
        super.removeEnforcedAttribute(str, str2);
        return this;
    }

    public Whitelist preserveRelativeLinks(boolean z) {
        super.preserveRelativeLinks(z);
        return this;
    }

    public Whitelist addProtocols(String str, String str2, String... strArr) {
        super.addProtocols(str, str2, strArr);
        return this;
    }

    public Whitelist removeProtocols(String str, String str2, String... strArr) {
        super.removeProtocols(str, str2, strArr);
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean isSafeTag(String str) {
        return super.isSafeTag(str);
    }

    /* access modifiers changed from: protected */
    public boolean isSafeAttribute(String str, Element element, Attribute attribute) {
        return super.isSafeAttribute(str, element, attribute);
    }

    /* access modifiers changed from: package-private */
    public Attributes getEnforcedAttributes(String str) {
        return super.getEnforcedAttributes(str);
    }
}
