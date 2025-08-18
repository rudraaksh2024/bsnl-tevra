package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.utils.SourceRoot;
import java.io.File;
import java.io.IOException;
import java.util.function.Predicate;
import org.apache.commons.io.IOUtils;

class Parser {
    final File[] classpath;
    final File[] javaFiles;

    public Parser(File[] fileArr, File[] fileArr2) {
        this.javaFiles = fileArr != null ? (File[]) fileArr.clone() : new File[0];
        this.classpath = fileArr2 != null ? (File[]) fileArr2.clone() : new File[0];
    }

    public ClassOrInterfaceDeclaration loadSource(String str) {
        String str2 = str.replace('.', '/') + ".java";
        File[] fileArr = this.javaFiles;
        int length = fileArr.length;
        int i = 0;
        while (i < length) {
            String path = fileArr[i].getPath();
            if (path.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/').endsWith(str2)) {
                String substring = path.substring(0, path.length() - str2.length());
                String substring2 = str.indexOf(46) == -1 ? "" : str.substring(0, str.lastIndexOf(46));
                try {
                    return (ClassOrInterfaceDeclaration) new SourceRoot(new File(substring).toPath()).tryToParse(substring2, (substring2.isEmpty() ? str : str.substring(substring2.length() + 1)) + ".java").getResult().flatMap(new Parser$$ExternalSyntheticLambda3(str)).orElse((Object) null);
                } catch (IOException unused) {
                    return null;
                }
            } else {
                i++;
            }
        }
        return null;
    }

    static /* synthetic */ ClassOrInterfaceDeclaration lambda$null$0(TypeDeclaration typeDeclaration) {
        return (ClassOrInterfaceDeclaration) typeDeclaration;
    }

    static /* synthetic */ boolean lambda$matchType$3(String str, TypeDeclaration typeDeclaration) {
        if (!(typeDeclaration instanceof ClassOrInterfaceDeclaration) || !((Boolean) typeDeclaration.getFullyQualifiedName().map(new Parser$$ExternalSyntheticLambda2(str)).orElse(false)).booleanValue()) {
            return false;
        }
        return true;
    }

    private static Predicate<TypeDeclaration<?>> matchType(String str) {
        return new Parser$$ExternalSyntheticLambda1(str);
    }
}
