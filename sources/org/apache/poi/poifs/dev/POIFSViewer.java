package org.apache.poi.poifs.dev;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public final class POIFSViewer {
    private POIFSViewer() {
    }

    public static void main(String[] strArr) {
        boolean z = true;
        if (strArr.length == 0) {
            System.err.println("Must specify at least one file to view");
            System.exit(1);
        }
        if (strArr.length <= 1) {
            z = false;
        }
        for (String viewFile : strArr) {
            viewFile(viewFile, z);
        }
    }

    private static void viewFile(String str, boolean z) {
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append(".");
            for (int i = 0; i < str.length(); i++) {
                sb.append(ProcessIdUtil.DEFAULT_PROCESSID);
            }
            sb.append(".");
            System.out.println(sb);
            System.out.println("|" + str + "|");
            System.out.println(sb);
        }
        try {
            POIFSFileSystem pOIFSFileSystem = new POIFSFileSystem(new File(str));
            for (String print : POIFSViewEngine.inspectViewable(pOIFSFileSystem, true, 0, "  ")) {
                System.out.print(print);
            }
            pOIFSFileSystem.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
