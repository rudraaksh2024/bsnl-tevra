package bsnl.bsnl_teevra.utils;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PermissionManager$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ PermissionManager f$0;
    public final /* synthetic */ CheckBox f$1;
    public final /* synthetic */ SharedPreferences f$2;
    public final /* synthetic */ Dialog f$3;

    public /* synthetic */ PermissionManager$$ExternalSyntheticLambda1(PermissionManager permissionManager, CheckBox checkBox, SharedPreferences sharedPreferences, Dialog dialog) {
        this.f$0 = permissionManager;
        this.f$1 = checkBox;
        this.f$2 = sharedPreferences;
        this.f$3 = dialog;
    }

    public final void onClick(View view) {
        this.f$0.m257lambda$showSettingsDialog_Notification$3$bsnlbsnl_teevrautilsPermissionManager(this.f$1, this.f$2, this.f$3, view);
    }
}
