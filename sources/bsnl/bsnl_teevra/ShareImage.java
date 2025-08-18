package bsnl.bsnl_teevra;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileOutputStream;

public class ShareImage {
    public void share(Bitmap bitmap, File file, Activity activity) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            file.setReadable(true, false);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/*");
            intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, "bsnl.bsnl_teevra.provider", file));
            intent.setFlags(268435456);
            activity.startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
