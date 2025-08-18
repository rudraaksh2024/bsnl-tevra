package com.google.firebase.heartbeatinfo;

import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_HeartBeatResult extends HeartBeatResult {
    private final List<String> usedDates;
    private final String userAgent;

    AutoValue_HeartBeatResult(String str, List<String> list) {
        if (str != null) {
            this.userAgent = str;
            if (list != null) {
                this.usedDates = list;
                return;
            }
            throw new NullPointerException("Null usedDates");
        }
        throw new NullPointerException("Null userAgent");
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public List<String> getUsedDates() {
        return this.usedDates;
    }

    public String toString() {
        return "HeartBeatResult{userAgent=" + this.userAgent + ", usedDates=" + this.usedDates + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeartBeatResult)) {
            return false;
        }
        HeartBeatResult heartBeatResult = (HeartBeatResult) obj;
        if (!this.userAgent.equals(heartBeatResult.getUserAgent()) || !this.usedDates.equals(heartBeatResult.getUsedDates())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.usedDates.hashCode() ^ ((this.userAgent.hashCode() ^ 1000003) * 1000003);
    }
}
