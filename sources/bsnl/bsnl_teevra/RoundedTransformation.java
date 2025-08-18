package bsnl.bsnl_teevra;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;

public class RoundedTransformation implements Transformation {
    private final int margin;
    private final int radius;

    public String key() {
        return "rounded";
    }

    public RoundedTransformation(int i, int i2) {
        this.radius = i;
        this.margin = i2;
    }

    public Bitmap transform(Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int i = this.margin;
        RectF rectF = new RectF((float) i, (float) i, (float) (bitmap.getWidth() - this.margin), (float) (bitmap.getHeight() - this.margin));
        int i2 = this.radius;
        canvas.drawRoundRect(rectF, (float) i2, (float) i2, paint);
        if (bitmap != createBitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }
}
