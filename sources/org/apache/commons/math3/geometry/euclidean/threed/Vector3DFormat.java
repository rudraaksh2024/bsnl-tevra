package org.apache.commons.math3.geometry.euclidean.threed;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.commons.math3.util.CompositeFormat;

public class Vector3DFormat extends VectorFormat<Euclidean3D> {
    public Vector3DFormat() {
        super(VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, VectorFormat.DEFAULT_SEPARATOR, CompositeFormat.getDefaultNumberFormat());
    }

    public Vector3DFormat(NumberFormat numberFormat) {
        super(VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, VectorFormat.DEFAULT_SEPARATOR, numberFormat);
    }

    public Vector3DFormat(String str, String str2, String str3) {
        super(str, str2, str3, CompositeFormat.getDefaultNumberFormat());
    }

    public Vector3DFormat(String str, String str2, String str3, NumberFormat numberFormat) {
        super(str, str2, str3, numberFormat);
    }

    public static Vector3DFormat getInstance() {
        return getInstance(Locale.getDefault());
    }

    public static Vector3DFormat getInstance(Locale locale) {
        return new Vector3DFormat(CompositeFormat.getDefaultNumberFormat(locale));
    }

    public StringBuffer format(Vector<Euclidean3D> vector, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        Vector3D vector3D = (Vector3D) vector;
        return format(stringBuffer, fieldPosition, vector3D.getX(), vector3D.getY(), vector3D.getZ());
    }

    public Vector3D parse(String str) throws MathParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        Vector3D parse = parse(str, parsePosition);
        if (parsePosition.getIndex() != 0) {
            return parse;
        }
        throw new MathParseException(str, parsePosition.getErrorIndex(), Vector3D.class);
    }

    public Vector3D parse(String str, ParsePosition parsePosition) {
        double[] parseCoordinates = parseCoordinates(3, str, parsePosition);
        if (parseCoordinates == null) {
            return null;
        }
        return new Vector3D(parseCoordinates[0], parseCoordinates[1], parseCoordinates[2]);
    }
}
