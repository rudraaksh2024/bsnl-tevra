package org.apache.poi.sl.draw.geom;

import java.util.Objects;
import org.apache.poi.util.Internal;

public class ArcToCommand implements ArcToCommandIf {
    private String hr;
    private String stAng;
    private String swAng;
    private String wr;

    public void setHR(String str) {
        this.hr = str;
    }

    public String getHR() {
        return this.hr;
    }

    public String getStAng() {
        return this.stAng;
    }

    public String getWR() {
        return this.wr;
    }

    public void setWR(String str) {
        this.wr = str;
    }

    public void setStAng(String str) {
        this.stAng = str;
    }

    public String getSwAng() {
        return this.swAng;
    }

    public void setSwAng(String str) {
        this.swAng = str;
    }

    @Internal
    public static double convertOoxml2AwtAngle(double d, double d2, double d3) {
        double d4 = d3 / d2;
        double d5 = -d;
        double d6 = d5 % 360.0d;
        double d7 = d5 - d6;
        int i = (int) (d6 / 90.0d);
        if (i == -3) {
            d7 -= 360.0d;
            d6 += 360.0d;
        } else if (i == -2 || i == -1) {
            d7 -= 180.0d;
            d6 += 180.0d;
        } else if (i == 1 || i == 2) {
            d7 += 180.0d;
            d6 -= 180.0d;
        } else if (i == 3) {
            d7 += 360.0d;
            d6 -= 360.0d;
        }
        return Math.toDegrees(Math.atan2(Math.tan(Math.toRadians(d6)), d4)) + d7;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArcToCommand)) {
            return false;
        }
        ArcToCommand arcToCommand = (ArcToCommand) obj;
        if (!Objects.equals(this.wr, arcToCommand.wr) || !Objects.equals(this.hr, arcToCommand.hr) || !Objects.equals(this.stAng, arcToCommand.stAng) || !Objects.equals(this.swAng, arcToCommand.swAng)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.wr, this.hr, this.stAng, this.swAng});
    }
}
