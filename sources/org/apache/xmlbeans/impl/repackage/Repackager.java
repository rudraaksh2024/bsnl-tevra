package org.apache.xmlbeans.impl.repackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Repackager {
    private Matcher[] _fromMatchers;
    private final List<List<String>> _fromPackages = new ArrayList();
    private String[] _toPackageNames;
    private final List<List<String>> _toPackages = new ArrayList();

    public Repackager(String str) {
        boolean z;
        List<String> splitPath = splitPath(str, ';');
        do {
            z = false;
            for (int i = 1; i < splitPath.size(); i++) {
                int i2 = i - 1;
                String str2 = splitPath.get(i2);
                String str3 = splitPath.get(i);
                if (str2.indexOf(58) < str3.indexOf(58)) {
                    splitPath.set(i2, str3);
                    splitPath.set(i, str2);
                    z = true;
                }
            }
        } while (z);
        for (String next : splitPath) {
            int indexOf = next.indexOf(58);
            if (indexOf >= 0) {
                int i3 = indexOf + 1;
                if (next.indexOf(58, i3) < 0) {
                    String substring = next.substring(0, indexOf);
                    String substring2 = next.substring(i3);
                    this._fromPackages.add(splitPath(substring, '.'));
                    this._toPackages.add(splitPath(substring2, '.'));
                }
            }
            throw new RuntimeException("Illegal repackage specification: " + next);
        }
        this._fromMatchers = new Matcher[(this._fromPackages.size() * 2)];
        this._toPackageNames = new String[(this._fromPackages.size() * 2)];
        addPatterns('.', 0);
        addPatterns('/', this._fromPackages.size());
    }

    /* access modifiers changed from: package-private */
    public void addPatterns(char c, int i) {
        for (int i2 = 0; i2 < this._fromPackages.size(); i2++) {
            List list = this._fromPackages.get(i2);
            List list2 = this._toPackages.get(i2);
            String str = "";
            for (int i3 = 0; i3 < list.size(); i3++) {
                if (i3 > 0) {
                    str = str + "\\" + c;
                }
                str = str + ((String) list.get(i3));
            }
            String str2 = "";
            for (int i4 = 0; i4 < list2.size(); i4++) {
                if (i4 > 0) {
                    str2 = str2 + c;
                }
                str2 = str2 + ((String) list2.get(i4));
            }
            int i5 = i + i2;
            this._fromMatchers[i5] = Pattern.compile(str).matcher("");
            this._toPackageNames[i5] = str2;
        }
    }

    public StringBuffer repackage(StringBuffer stringBuffer) {
        int i = 0;
        StringBuffer stringBuffer2 = null;
        while (true) {
            Matcher[] matcherArr = this._fromMatchers;
            if (i >= matcherArr.length) {
                return stringBuffer;
            }
            Matcher matcher = matcherArr[i];
            matcher.reset(stringBuffer);
            while (matcher.find()) {
                if (stringBuffer2 == null) {
                    stringBuffer2 = new StringBuffer();
                }
                matcher.appendReplacement(stringBuffer2, this._toPackageNames[i]);
            }
            if (stringBuffer2 != null) {
                matcher.appendTail(stringBuffer2);
                stringBuffer = stringBuffer2;
                stringBuffer2 = null;
            }
            i++;
        }
    }

    public List<List<String>> getFromPackages() {
        return this._fromPackages;
    }

    public List<List<String>> getToPackages() {
        return this._toPackages;
    }

    public static List<String> splitPath(String str, char c) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            int indexOf = str.indexOf(c);
            if (indexOf < 0) {
                break;
            }
            arrayList.add(str.substring(0, indexOf));
            str = str.substring(indexOf + 1);
        }
        if (str.length() > 0) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public static String dirForPath(String str) {
        return new File(str).getParent();
    }
}
