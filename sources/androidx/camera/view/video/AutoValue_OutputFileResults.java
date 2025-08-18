package androidx.camera.view.video;

import android.net.Uri;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_OutputFileResults extends OutputFileResults {
    private final Uri savedUri;

    AutoValue_OutputFileResults(Uri uri) {
        this.savedUri = uri;
    }

    public Uri getSavedUri() {
        return this.savedUri;
    }

    public String toString() {
        return "OutputFileResults{savedUri=" + this.savedUri + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OutputFileResults)) {
            return false;
        }
        OutputFileResults outputFileResults = (OutputFileResults) obj;
        Uri uri = this.savedUri;
        if (uri != null) {
            return uri.equals(outputFileResults.getSavedUri());
        }
        if (outputFileResults.getSavedUri() == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Uri uri = this.savedUri;
        return (uri == null ? 0 : uri.hashCode()) ^ 1000003;
    }
}
