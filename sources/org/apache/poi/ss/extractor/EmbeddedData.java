package org.apache.poi.ss.extractor;

import org.apache.poi.ss.usermodel.Shape;

public class EmbeddedData {
    private String contentType = "binary/octet-stream";
    private byte[] embeddedData;
    private String filename;
    private Shape shape;

    public EmbeddedData(String str, byte[] bArr, String str2) {
        setFilename(str);
        setEmbeddedData(bArr);
        setContentType(str2);
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String str) {
        if (str == null) {
            this.filename = "unknown.bin";
        } else {
            this.filename = str.replaceAll("[^/\\\\]*[/\\\\]", "").trim();
        }
    }

    public byte[] getEmbeddedData() {
        return this.embeddedData;
    }

    public void setEmbeddedData(byte[] bArr) {
        this.embeddedData = bArr == null ? null : (byte[]) bArr.clone();
    }

    public Shape getShape() {
        return this.shape;
    }

    public void setShape(Shape shape2) {
        this.shape = shape2;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }
}
