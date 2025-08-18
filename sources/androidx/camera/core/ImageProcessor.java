package androidx.camera.core;

import java.util.List;

public interface ImageProcessor {

    public interface Request {
        List<ImageProxy> getInputImages();

        int getOutputFormat();
    }

    public interface Response {
        ImageProxy getOutputImage();
    }

    Response process(Request request);
}
