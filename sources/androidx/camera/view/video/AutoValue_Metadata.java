package androidx.camera.view.video;

import android.location.Location;
import androidx.camera.view.video.Metadata;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_Metadata extends Metadata {
    private final Location location;

    private AutoValue_Metadata(Location location2) {
        this.location = location2;
    }

    public Location getLocation() {
        return this.location;
    }

    public String toString() {
        return "Metadata{location=" + this.location + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Metadata)) {
            return false;
        }
        Metadata metadata = (Metadata) obj;
        Location location2 = this.location;
        if (location2 != null) {
            return location2.equals(metadata.getLocation());
        }
        if (metadata.getLocation() == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Location location2 = this.location;
        return (location2 == null ? 0 : location2.hashCode()) ^ 1000003;
    }

    static final class Builder extends Metadata.Builder {
        private Location location;

        Builder() {
        }

        public Metadata.Builder setLocation(Location location2) {
            this.location = location2;
            return this;
        }

        public Metadata build() {
            return new AutoValue_Metadata(this.location);
        }
    }
}
