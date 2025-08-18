package org.apache.poi.sl.draw.geom;

import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public interface ArcToCommandIf extends PathCommand {
    String getHR();

    String getStAng();

    String getSwAng();

    String getWR();

    void setHR(String str);

    void setStAng(String str);

    void setSwAng(String str);

    void setWR(String str);

    void execute(Path2D.Double doubleR, Context context) {
        Context context2 = context;
        double value = context2.getValue(getWR());
        double value2 = context2.getValue(getHR());
        double value3 = context2.getValue(getStAng()) / 60000.0d;
        double d = value;
        double d2 = value2;
        double convertOoxml2AwtAngle = ArcToCommand.convertOoxml2AwtAngle(value3, d, d2);
        double convertOoxml2AwtAngle2 = ArcToCommand.convertOoxml2AwtAngle(value3 + (context2.getValue(getSwAng()) / 60000.0d), d, d2) - convertOoxml2AwtAngle;
        double radians = Math.toRadians(value3);
        double atan2 = Math.atan2(Math.sin(radians) * value, Math.cos(radians) * value2);
        Point2D currentPoint = doubleR.getCurrentPoint();
        doubleR.append(new Arc2D.Double((currentPoint.getX() - (Math.cos(atan2) * value)) - value, (currentPoint.getY() - (Math.sin(atan2) * value2)) - value2, value * 2.0d, value2 * 2.0d, convertOoxml2AwtAngle, convertOoxml2AwtAngle2, 0), true);
    }
}
