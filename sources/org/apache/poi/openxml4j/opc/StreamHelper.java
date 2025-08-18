package org.apache.poi.openxml4j.opc;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.XMLHelper;
import org.w3c.dom.Document;

public final class StreamHelper {
    private StreamHelper() {
    }

    public static boolean saveXmlInStream(Document document, OutputStream outputStream) {
        try {
            Transformer newTransformer = XMLHelper.newTransformer();
            DOMSource dOMSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new FilterOutputStream(outputStream) {
                public void write(byte[] bArr, int i, int i2) throws IOException {
                    this.out.write(bArr, i, i2);
                }

                public void close() throws IOException {
                    this.out.flush();
                }
            });
            newTransformer.setOutputProperty("encoding", "UTF-8");
            newTransformer.setOutputProperty("indent", "no");
            newTransformer.setOutputProperty("standalone", "yes");
            newTransformer.transform(dOMSource, streamResult);
            return true;
        } catch (TransformerException unused) {
            return false;
        }
    }

    public static boolean copyStream(InputStream inputStream, OutputStream outputStream) {
        try {
            IOUtils.copy(inputStream, outputStream);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
