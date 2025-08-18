package bsnl.bsnl_teevra.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import bsnl.bsnl_teevra.R;
import com.google.android.gms.stats.CodePackage;
import java.util.Map;

public class PermissionManager {
    private final String PERMISSION_TYPE;
    private final int REQUEST_CODE;
    private final Activity activity;
    private final PermissionCallback callback;
    private String[] permissions;

    public interface PermissionCallback {
        void onPermissionDenied(int i);

        void onPermissionGranted(int i);

        void onPermissionPermanentlyDenied(int i);
    }

    public PermissionManager(Activity activity2, int i, String str, PermissionCallback permissionCallback) {
        this.activity = activity2;
        this.callback = permissionCallback;
        this.REQUEST_CODE = i;
        this.PERMISSION_TYPE = str;
    }

    public void setPermissions(String... strArr) {
        this.permissions = strArr;
    }

    public void requestPermissions() {
        String[] strArr = this.permissions;
        if (strArr != null && strArr.length != 0) {
            for (Map.Entry next : this.activity.getSharedPreferences(AppConstants.PERMISSION_MANAGER_PREFS, 0).getAll().entrySet()) {
                Log.d("PERMISSION-MANAGER", "Key: " + ((String) next.getKey()) + ", Value: " + next.getValue());
            }
            boolean z = false;
            boolean z2 = false;
            for (String str : this.permissions) {
                if (ContextCompat.checkSelfPermission(this.activity, str) == 0) {
                    PermissionCallback permissionCallback = this.callback;
                    if (permissionCallback != null) {
                        permissionCallback.onPermissionGranted(this.REQUEST_CODE);
                    }
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, str)) {
                    z = true;
                } else if (!isFirstRequest(this.REQUEST_CODE)) {
                    z2 = true;
                }
            }
            if (z) {
                ActivityCompat.requestPermissions(this.activity, this.permissions, this.REQUEST_CODE);
            } else if (!z2) {
                ActivityCompat.requestPermissions(this.activity, this.permissions, this.REQUEST_CODE);
            } else if (this.REQUEST_CODE == 201) {
                showSettingsDialog_Notification();
            } else {
                showSettingsDialog();
            }
        }
    }

    private boolean isFirstRequest(int i) {
        Boolean bool = true;
        SharedPreferences sharedPreferences = this.activity.getSharedPreferences(AppConstants.PERMISSION_MANAGER_PREFS, 0);
        if (i == 201) {
            bool = Boolean.valueOf(sharedPreferences.getBoolean(AppConstants.KEY_FIRST_REQUEST_POST_NOTIFICATIONS, true));
        } else if (i != 202) {
            switch (i) {
                case 101:
                    bool = Boolean.valueOf(sharedPreferences.getBoolean(AppConstants.KEY_FIRST_REQUEST_LOCATION, true));
                    break;
                case 102:
                    bool = Boolean.valueOf(sharedPreferences.getBoolean(AppConstants.KEY_FIRST_REQUEST_CODE_CAMERA, true));
                    break;
                case 103:
                    bool = Boolean.valueOf(sharedPreferences.getBoolean(AppConstants.KEY_FIRST_REQUEST_CODE_READ_EXTERNAL_STORAGE, true));
                    break;
            }
        } else {
            bool = Boolean.valueOf(sharedPreferences.getBoolean(AppConstants.KEY_FIRST_REQUEST_CODE_READ_MEDIA_IMAGE, true));
        }
        return bool.booleanValue();
    }

    public void markPermissionRequested(int i) {
        Log.d("PERMISSIONMANAGER", "markPermissionRequested" + this.REQUEST_CODE + "");
        SharedPreferences.Editor edit = this.activity.getSharedPreferences(AppConstants.PERMISSION_MANAGER_PREFS, 0).edit();
        if (i == 201) {
            edit.putBoolean(AppConstants.KEY_FIRST_REQUEST_POST_NOTIFICATIONS, false);
        } else if (i != 202) {
            switch (i) {
                case 101:
                    edit.putBoolean(AppConstants.KEY_FIRST_REQUEST_LOCATION, false);
                    break;
                case 102:
                    edit.putBoolean(AppConstants.KEY_FIRST_REQUEST_CODE_CAMERA, false);
                    break;
                case 103:
                    edit.putBoolean(AppConstants.KEY_FIRST_REQUEST_CODE_READ_EXTERNAL_STORAGE, false);
                    break;
            }
        } else {
            edit.putBoolean(AppConstants.KEY_FIRST_REQUEST_CODE_READ_MEDIA_IMAGE, false);
        }
        edit.apply();
    }

    public boolean arePermissionsGranted() {
        String[] strArr = this.permissions;
        if (!(strArr == null || strArr.length == 0)) {
            for (String checkSelfPermission : strArr) {
                if (ContextCompat.checkSelfPermission(this.activity, checkSelfPermission) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showSettingsDialog() {
        Dialog dialog = new Dialog(this.activity);
        dialog.setContentView(R.layout.custom_alert);
        dialog.getWindow().setLayout(-1, -2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCancelable(false);
        ((CheckBox) dialog.findViewById(R.id.chk_dnd)).setVisibility(8);
        ((TextView) dialog.findViewById(R.id.txt_info)).setText(Html.fromHtml(getPermissionMessage1(this.PERMISSION_TYPE), 0));
        ((TextView) dialog.findViewById(R.id.txt_cancel)).setOnClickListener(new PermissionManager$$ExternalSyntheticLambda2(this, dialog));
        ((TextView) dialog.findViewById(R.id.txt_settings)).setOnClickListener(new PermissionManager$$ExternalSyntheticLambda3(this, dialog));
        CustomAnimationUtils.fadeInDialog(this.activity, dialog);
        dialog.show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showSettingsDialog$0$bsnl-bsnl_teevra-utils-PermissionManager  reason: not valid java name */
    public /* synthetic */ void m254lambda$showSettingsDialog$0$bsnlbsnl_teevrautilsPermissionManager(Dialog dialog, View view) {
        CustomAnimationUtils.fadeOutDialog(this.activity, dialog);
        dialog.dismiss();
        if (this.REQUEST_CODE == 101) {
            this.activity.finish();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showSettingsDialog$1$bsnl-bsnl_teevra-utils-PermissionManager  reason: not valid java name */
    public /* synthetic */ void m255lambda$showSettingsDialog$1$bsnlbsnl_teevrautilsPermissionManager(Dialog dialog, View view) {
        CustomAnimationUtils.fadeOutDialog(this.activity, dialog);
        dialog.dismiss();
        openAppSettings();
    }

    private void showSettingsDialog_Notification() {
        SharedPreferences sharedPreferences = this.activity.getSharedPreferences(AppConstants.PERMISSION_MANAGER_PREFS, 0);
        if (!Boolean.valueOf(sharedPreferences.getBoolean(AppConstants.KEY_DND_POST_NOTIFICATION, false)).booleanValue()) {
            Dialog dialog = new Dialog(this.activity);
            dialog.setContentView(R.layout.custom_alert);
            dialog.getWindow().setLayout(-1, -2);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.setCancelable(false);
            CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.chk_dnd);
            checkBox.setVisibility(0);
            ((TextView) dialog.findViewById(R.id.txt_info)).setText(Html.fromHtml(getPermissionMessage1(this.PERMISSION_TYPE), 0));
            ((TextView) dialog.findViewById(R.id.txt_cancel)).setOnClickListener(new PermissionManager$$ExternalSyntheticLambda0(this, checkBox, sharedPreferences, dialog));
            ((TextView) dialog.findViewById(R.id.txt_settings)).setOnClickListener(new PermissionManager$$ExternalSyntheticLambda1(this, checkBox, sharedPreferences, dialog));
            CustomAnimationUtils.fadeInDialog(this.activity, dialog);
            dialog.show();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showSettingsDialog_Notification$2$bsnl-bsnl_teevra-utils-PermissionManager  reason: not valid java name */
    public /* synthetic */ void m256lambda$showSettingsDialog_Notification$2$bsnlbsnl_teevrautilsPermissionManager(CheckBox checkBox, SharedPreferences sharedPreferences, Dialog dialog, View view) {
        if (checkBox.isChecked()) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(AppConstants.KEY_DND_POST_NOTIFICATION, true);
            edit.apply();
        }
        CustomAnimationUtils.fadeOutDialog(this.activity, dialog);
        dialog.dismiss();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showSettingsDialog_Notification$3$bsnl-bsnl_teevra-utils-PermissionManager  reason: not valid java name */
    public /* synthetic */ void m257lambda$showSettingsDialog_Notification$3$bsnlbsnl_teevrautilsPermissionManager(CheckBox checkBox, SharedPreferences sharedPreferences, Dialog dialog, View view) {
        if (checkBox.isChecked()) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(AppConstants.KEY_DND_POST_NOTIFICATION, true);
            edit.apply();
        }
        CustomAnimationUtils.fadeOutDialog(this.activity, dialog);
        dialog.dismiss();
        openAppSettings();
    }

    private String getPermissionMessage1(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1611296843:
                if (str.equals(CodePackage.LOCATION)) {
                    c = 0;
                    break;
                }
                break;
            case -1166291365:
                if (str.equals("STORAGE")) {
                    c = 1;
                    break;
                }
                break;
            case -953256087:
                if (str.equals("POST_NOTIFICATIONS")) {
                    c = 2;
                    break;
                }
                break;
            case 1669509120:
                if (str.equals("CONTACT")) {
                    c = 3;
                    break;
                }
                break;
            case 1980544805:
                if (str.equals("CAMERA")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "<b>BSNL TEEVRA</b> Needs <b>Location</b> Permissions.<br>Please Allow The Permissions In <b><i>App Settings.</b></i>";
            case 1:
                return "<b>BSNL TEEVRA</b> Needs <b>Storage</b> Permissions.<br>Please Allow The Permissions In <b><i>App Settings.</b></i>";
            case 2:
                return "<b>BSNL TEEVRA</b> Needs <b>Notification</b> Access To Keep You informed about Network Outage and Other Important Updates.<br>Please Turn It On In <b><i>App Settings.</b></i>";
            case 3:
                return "<b>BSNL TEEVRA</b> needs <b>Contacts</b> access to sync and manage your contacts.<br>Please enable the permission in <b><i>App Settings.</b></i>";
            case 4:
                return "<b>BSNL TEEVRA</b> Needs <b>Camera</b> Access To Click & Save Images.<br>Please Allow The Permission In <b><i>App Settings.</b></i>";
            default:
                return "<b>BSNL TEEVRA</b> needs additional permissions to function properly.<br>Please enable the required permissions in <b><i>App Settings.</b></i>";
        }
    }

    private void openAppSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", this.activity.getPackageName(), (String) null));
        intent.addFlags(268435456);
        this.activity.startActivity(intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        markPermissionRequested(i);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (iArr[i2] == 0) {
                PermissionCallback permissionCallback = this.callback;
                if (permissionCallback != null) {
                    permissionCallback.onPermissionGranted(i);
                }
            } else if (this.callback != null) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, strArr[i2])) {
                    this.callback.onPermissionDenied(i);
                } else {
                    this.callback.onPermissionPermanentlyDenied(i);
                }
            }
        }
    }
}
