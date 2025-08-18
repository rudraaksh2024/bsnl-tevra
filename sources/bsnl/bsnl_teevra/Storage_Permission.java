package bsnl.bsnl_teevra;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Storage_Permission {
    private static final int STORAGE_PERMISSION_CODE = 100;
    private static Boolean permission = false;
    /* access modifiers changed from: private */
    public final Context context;

    public Storage_Permission(Context context2) {
        this.context = context2;
    }

    public Boolean CheckStoragePermission() {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            permission = false;
        } else {
            permission = true;
        }
        return permission;
    }

    public void RequestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) this.context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions((Activity) this.context, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 100);
        } else {
            showSettingsDialog();
        }
    }

    public Boolean handlePermissionResult(int i, int[] iArr) {
        if (i == 100) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                permission = false;
            } else {
                permission = true;
            }
        }
        return permission;
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.custom_info, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
        textView.setText("SETTINGS");
        builder.setCancelable(false);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.show();
        ((TextView) inflate.findViewById(R.id.txt_alert)).setText("STORAGE PERMISSION");
        ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("Storage Permission Is Needed To Save The File.<br>Please Enable It In The <b><i>APP SEETINGS.</i></b>", 0));
        ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                create.cancel();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + Storage_Permission.this.context.getPackageName()));
                intent.addFlags(268435456);
                intent.addFlags(BasicMeasure.EXACTLY);
                intent.addFlags(8388608);
                Storage_Permission.this.context.startActivity(intent);
                create.cancel();
            }
        });
    }
}
