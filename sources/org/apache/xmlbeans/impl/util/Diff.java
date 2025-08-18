package org.apache.xmlbeans.impl.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.List;

public class Diff {
    public static void readersAsText(Reader reader, String str, Reader reader2, String str2, List list) throws IOException {
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        LineNumberReader lineNumberReader2 = new LineNumberReader(reader2);
        String readLine = lineNumberReader.readLine();
        String readLine2 = lineNumberReader2.readLine();
        while (true) {
            if (readLine == null || readLine2 == null) {
                break;
            } else if (!readLine.equals(readLine2)) {
                list.add("File \"" + str + "\" and file \"" + str2 + "\" differ at line " + lineNumberReader.getLineNumber() + ":\n" + readLine + "\n========\n" + readLine2);
                break;
            } else {
                readLine = lineNumberReader.readLine();
                readLine2 = lineNumberReader2.readLine();
            }
        }
        if (readLine == null && readLine2 != null) {
            list.add("File \"" + str2 + "\" has extra lines at line " + lineNumberReader2.getLineNumber() + ":\n" + readLine2);
        }
        if (readLine != null && readLine2 == null) {
            list.add("File \"" + str + "\" has extra lines at line " + lineNumberReader.getLineNumber() + ":\n" + readLine);
        }
    }
}
