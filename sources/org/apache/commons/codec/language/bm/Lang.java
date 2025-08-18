package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages;

public class Lang {
    private static final String LANGUAGE_RULES_RN = "org/apache/commons/codec/language/bm/%s_lang.txt";
    private static final Map<NameType, Lang> Langs = new EnumMap(NameType.class);
    private final Languages languages;
    private final List<LangRule> rules;

    private static final class LangRule {
        /* access modifiers changed from: private */
        public final boolean acceptOnMatch;
        /* access modifiers changed from: private */
        public final Set<String> languages;
        private final Pattern pattern;

        private LangRule(Pattern pattern2, Set<String> set, boolean z) {
            this.pattern = pattern2;
            this.languages = set;
            this.acceptOnMatch = z;
        }

        public boolean matches(String str) {
            return this.pattern.matcher(str).find();
        }
    }

    static {
        for (NameType nameType : NameType.values()) {
            Langs.put(nameType, loadFromResource(String.format(LANGUAGE_RULES_RN, new Object[]{nameType.getName()}), Languages.getInstance(nameType)));
        }
    }

    public static Lang instance(NameType nameType) {
        return Langs.get(nameType);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b3, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b9, code lost:
        r9.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00bc, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.commons.codec.language.bm.Lang loadFromResource(java.lang.String r9, org.apache.commons.codec.language.bm.Languages r10) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Scanner r1 = new java.util.Scanner
            java.io.InputStream r2 = org.apache.commons.codec.Resources.getInputStream(r9)
            java.lang.String r3 = "UTF-8"
            r1.<init>(r2, r3)
            r2 = 0
        L_0x0011:
            r3 = r2
        L_0x0012:
            boolean r4 = r1.hasNextLine()     // Catch:{ all -> 0x00b1 }
            if (r4 == 0) goto L_0x00a8
            java.lang.String r4 = r1.nextLine()     // Catch:{ all -> 0x00b1 }
            if (r3 == 0) goto L_0x0027
            java.lang.String r5 = "*/"
            boolean r4 = r4.endsWith(r5)     // Catch:{ all -> 0x00b1 }
            if (r4 == 0) goto L_0x0012
            goto L_0x0011
        L_0x0027:
            java.lang.String r5 = "/*"
            boolean r5 = r4.startsWith(r5)     // Catch:{ all -> 0x00b1 }
            r6 = 1
            if (r5 == 0) goto L_0x0032
            r3 = r6
            goto L_0x0012
        L_0x0032:
            java.lang.String r5 = "//"
            int r5 = r4.indexOf(r5)     // Catch:{ all -> 0x00b1 }
            if (r5 < 0) goto L_0x003f
            java.lang.String r5 = r4.substring(r2, r5)     // Catch:{ all -> 0x00b1 }
            goto L_0x0040
        L_0x003f:
            r5 = r4
        L_0x0040:
            java.lang.String r5 = r5.trim()     // Catch:{ all -> 0x00b1 }
            int r7 = r5.length()     // Catch:{ all -> 0x00b1 }
            if (r7 != 0) goto L_0x004b
            goto L_0x0012
        L_0x004b:
            java.lang.String r7 = "\\s+"
            java.lang.String[] r5 = r5.split(r7)     // Catch:{ all -> 0x00b1 }
            int r7 = r5.length     // Catch:{ all -> 0x00b1 }
            r8 = 3
            if (r7 != r8) goto L_0x007f
            r4 = r5[r2]     // Catch:{ all -> 0x00b1 }
            java.util.regex.Pattern r4 = java.util.regex.Pattern.compile(r4)     // Catch:{ all -> 0x00b1 }
            r6 = r5[r6]     // Catch:{ all -> 0x00b1 }
            java.lang.String r7 = "\\+"
            java.lang.String[] r6 = r6.split(r7)     // Catch:{ all -> 0x00b1 }
            r7 = 2
            r5 = r5[r7]     // Catch:{ all -> 0x00b1 }
            java.lang.String r7 = "true"
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x00b1 }
            org.apache.commons.codec.language.bm.Lang$LangRule r7 = new org.apache.commons.codec.language.bm.Lang$LangRule     // Catch:{ all -> 0x00b1 }
            java.util.HashSet r8 = new java.util.HashSet     // Catch:{ all -> 0x00b1 }
            java.util.List r6 = java.util.Arrays.asList(r6)     // Catch:{ all -> 0x00b1 }
            r8.<init>(r6)     // Catch:{ all -> 0x00b1 }
            r6 = 0
            r7.<init>(r4, r8, r5)     // Catch:{ all -> 0x00b1 }
            r0.add(r7)     // Catch:{ all -> 0x00b1 }
            goto L_0x0012
        L_0x007f:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00b1 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b1 }
            r0.<init>()     // Catch:{ all -> 0x00b1 }
            java.lang.String r2 = "Malformed line '"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x00b1 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x00b1 }
            java.lang.String r2 = "' in language resource '"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x00b1 }
            java.lang.StringBuilder r9 = r0.append(r9)     // Catch:{ all -> 0x00b1 }
            java.lang.String r0 = "'"
            java.lang.StringBuilder r9 = r9.append(r0)     // Catch:{ all -> 0x00b1 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00b1 }
            r10.<init>(r9)     // Catch:{ all -> 0x00b1 }
            throw r10     // Catch:{ all -> 0x00b1 }
        L_0x00a8:
            r1.close()
            org.apache.commons.codec.language.bm.Lang r9 = new org.apache.commons.codec.language.bm.Lang
            r9.<init>(r0, r10)
            return r9
        L_0x00b1:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x00b3 }
        L_0x00b3:
            r10 = move-exception
            r1.close()     // Catch:{ all -> 0x00b8 }
            goto L_0x00bc
        L_0x00b8:
            r0 = move-exception
            r9.addSuppressed(r0)
        L_0x00bc:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.bm.Lang.loadFromResource(java.lang.String, org.apache.commons.codec.language.bm.Languages):org.apache.commons.codec.language.bm.Lang");
    }

    private Lang(List<LangRule> list, Languages languages2) {
        this.rules = Collections.unmodifiableList(list);
        this.languages = languages2;
    }

    public String guessLanguage(String str) {
        Languages.LanguageSet guessLanguages = guessLanguages(str);
        return guessLanguages.isSingleton() ? guessLanguages.getAny() : Languages.ANY;
    }

    public Languages.LanguageSet guessLanguages(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        HashSet hashSet = new HashSet(this.languages.getLanguages());
        for (LangRule next : this.rules) {
            if (next.matches(lowerCase)) {
                if (next.acceptOnMatch) {
                    hashSet.retainAll(next.languages);
                } else {
                    hashSet.removeAll(next.languages);
                }
            }
        }
        Languages.LanguageSet from = Languages.LanguageSet.from(hashSet);
        return from.equals(Languages.NO_LANGUAGES) ? Languages.ANY_LANGUAGE : from;
    }
}
