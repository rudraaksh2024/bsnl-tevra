package bsnl.bsnl_teevra;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.FileProvider;
import bsnl.bsnl_teevra.utils.PermissionManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.yalantis.ucrop.UCrop;
import java.io.File;

public class DashBoard_New extends AppCompatActivity implements View.OnClickListener, PermissionManager.PermissionCallback {
    public static final int CAMERA_PERMISSION_REQ = 100;
    public static final int CAMERA_REQUEST_CODE = 1000;
    private static final int GALLERY_REQUEST_CODE = 1001;
    private static int RESULT_LOAD_IMAGE = 1;
    public static final int STORAGE_PERMISSION_REQ = 101;
    public static final int STORAGE_REQUEST_CODE = 201;
    private static final int UCROP_REQUEST_CODE = 1002;
    private boolean Perf_Account_Status;
    /* access modifiers changed from: private */
    public String Pref_Access_level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Designation;
    private String Pref_Firm;
    /* access modifiers changed from: private */
    public String Pref_Mobile;
    /* access modifiers changed from: private */
    public String Pref_Name;
    private boolean Pref_PMS_key;
    private String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Unit;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private boolean Pref_detail_bb_Key;
    private boolean Pref_detail_ftth_Key;
    private boolean Pref_detail_tip_Key;
    private boolean Pref_fms_Key;
    private boolean Pref_nms_dslam_Key;
    private boolean Pref_nms_olt_Key;
    private boolean Pref_nms_tpartyolt_Key;
    private boolean Pref_nw_glance_Key;
    private boolean Pref_portverify_Key;
    private boolean Pref_report_Key;
    private boolean Pref_setting_Key;
    /* access modifiers changed from: private */
    public AlertDialog about_dialog;
    private String androidId;
    private LinearLayout card_view1;
    private LinearLayout card_view2;
    private LinearLayout card_view3;
    private LinearLayout card_view4;
    private LinearLayout card_view5;
    private LinearLayout card_view6;
    private LinearLayout card_view7;
    private LinearLayout card_view8;
    /* access modifiers changed from: private */
    public SharedPreferences.Editor editor;
    private AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public File file_profile_pic;
    private File file_temp;
    private ImageView img_dp;
    private ImageView img_menu;
    /* access modifiers changed from: private */
    public ImageView img_profile;
    private AlertDialog info_dialog;
    /* access modifiers changed from: private */
    public LinearLayout lay1;
    /* access modifiers changed from: private */
    public LinearLayout lay2;
    /* access modifiers changed from: private */
    public LinearLayout lay3;
    /* access modifiers changed from: private */
    public LinearLayout lay4;
    private LinearLayout lay_account_status;
    /* access modifiers changed from: private */
    public LinearLayout lay_dashboard;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    private Uri mUri;
    private PermissionManager permissionManager_PostNotifications;
    /* access modifiers changed from: private */
    public PermissionManager permissionManager_camera;
    private Uri photoURI;
    private AlertDialog profile_dialog;
    /* access modifiers changed from: private */
    public AlertDialog profile_pic_dialog;
    private AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;
    TextView txt_alert;
    TextView txt_header_hrms;
    TextView txt_header_name;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.dash_board_new_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Name = sharedPreferences2.getString("name_key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Mobile = this.sharedPreferences.getString("mobile_Key", (String) null);
        this.Pref_Designation = this.sharedPreferences.getString("designation_Key", (String) null);
        this.Pref_Unit = this.sharedPreferences.getString("unit_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Perf_Account_Status = this.sharedPreferences.getBoolean("Account_status_Key", false);
        this.Pref_Role = this.sharedPreferences.getString("role_Key", (String) null);
        this.Pref_Access_level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_PMS_key = this.sharedPreferences.getBoolean("pms_Key", false);
        this.Pref_portverify_Key = this.sharedPreferences.getBoolean("portverify_Key", false);
        this.Pref_detail_bb_Key = this.sharedPreferences.getBoolean("detail_bb_Key", false);
        this.Pref_detail_ftth_Key = this.sharedPreferences.getBoolean("detail_ftth_Key", false);
        this.Pref_detail_tip_Key = this.sharedPreferences.getBoolean("detail_tip_Key", false);
        final String string = this.sharedPreferences.getString("fms_username_Key", (String) null);
        this.Pref_fms_Key = this.sharedPreferences.getBoolean("fms_Key", false);
        this.Pref_Firm = this.sharedPreferences.getString("fms_firm_Key", (String) null).toUpperCase();
        this.Pref_nms_dslam_Key = this.sharedPreferences.getBoolean("nms_dslam_Key", false);
        this.Pref_nms_olt_Key = this.sharedPreferences.getBoolean("nms_olt_Key", false);
        this.Pref_nms_tpartyolt_Key = this.sharedPreferences.getBoolean("nms_tpartyolt_Key", false);
        this.Pref_nw_glance_Key = this.sharedPreferences.getBoolean("nw_glance_Key", false);
        this.Pref_report_Key = this.sharedPreferences.getBoolean("report_Key", false);
        this.Pref_setting_Key = this.sharedPreferences.getBoolean("setting_Key", false);
        this.file_temp = new File(getExternalCacheDir(), this.Pref_Username + "temp.jpg");
        this.file_profile_pic = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), this.Pref_Username + "_profile_pic.jpg");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        builder.setCancelable(false);
        builder.setView(inflate);
        this.progress_dialog = builder.create();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_error, (ViewGroup) null);
        this.txt_alert = (TextView) inflate2.findViewById(R.id.txt_error);
        builder2.setCancelable(false);
        builder2.setNegativeButton("Retry", (DialogInterface.OnClickListener) null);
        builder2.setView(inflate2);
        this.error_dialog = builder2.create();
        this.txt_header_name = (TextView) findViewById(R.id.txt_header_name);
        this.txt_header_hrms = (TextView) findViewById(R.id.txt_header_hrms);
        this.txt_header_name.setText("WELCOME, " + this.Pref_Name.toUpperCase());
        this.txt_header_hrms.setText(this.Pref_Username);
        PermissionManager permissionManager = new PermissionManager(this, 201, "POST_NOTIFICATIONS", this);
        this.permissionManager_PostNotifications = permissionManager;
        permissionManager.setPermissions("android.permission.POST_NOTIFICATIONS");
        PermissionManager permissionManager2 = new PermissionManager(this, 102, "CAMERA", this);
        this.permissionManager_camera = permissionManager2;
        permissionManager2.setPermissions("android.permission.CAMERA");
        if (Build.VERSION.SDK_INT >= 33 && !this.permissionManager_PostNotifications.arePermissionsGranted()) {
            this.permissionManager_PostNotifications.requestPermissions();
        }
        this.lay_dashboard = (LinearLayout) findViewById(R.id.lay_dashboard);
        this.lay1 = (LinearLayout) findViewById(R.id.lay1);
        this.lay2 = (LinearLayout) findViewById(R.id.lay2);
        this.lay3 = (LinearLayout) findViewById(R.id.lay3);
        this.lay4 = (LinearLayout) findViewById(R.id.lay4);
        this.card_view1 = (LinearLayout) findViewById(R.id.card_view1);
        this.card_view2 = (LinearLayout) findViewById(R.id.card_view2);
        this.card_view3 = (LinearLayout) findViewById(R.id.card_view3);
        this.card_view4 = (LinearLayout) findViewById(R.id.card_view4);
        this.card_view5 = (LinearLayout) findViewById(R.id.card_view5);
        this.card_view6 = (LinearLayout) findViewById(R.id.card_view6);
        this.card_view7 = (LinearLayout) findViewById(R.id.card_view7);
        this.card_view8 = (LinearLayout) findViewById(R.id.card_view8);
        this.img_menu = (ImageView) findViewById(R.id.img_menu);
        this.lay_dashboard.post(new Runnable() {
            public void run() {
                int measuredWidth = (int) (((double) DashBoard_New.this.lay_dashboard.getMeasuredWidth()) / 2.25d);
                DashBoard_New.this.lay1.setLayoutParams(new LinearLayout.LayoutParams(-1, measuredWidth));
                DashBoard_New.this.lay2.setLayoutParams(new LinearLayout.LayoutParams(-1, measuredWidth));
                DashBoard_New.this.lay3.setLayoutParams(new LinearLayout.LayoutParams(-1, measuredWidth));
                DashBoard_New.this.lay4.setLayoutParams(new LinearLayout.LayoutParams(-1, measuredWidth));
            }
        });
        if (this.Perf_Account_Status) {
            this.card_view1.setOnClickListener(this);
            this.card_view2.setOnClickListener(this);
            this.card_view3.setOnClickListener(this);
            this.card_view4.setOnClickListener(this);
            this.card_view5.setOnClickListener(this);
            this.card_view6.setOnClickListener(this);
            this.card_view7.setOnClickListener(this);
            this.card_view8.setOnClickListener(this);
        } else {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay_account_status);
            this.lay_account_status = linearLayout;
            linearLayout.setVisibility(0);
        }
        this.img_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(new ContextThemeWrapper((Context) DashBoard_New.this, (int) R.style.YOURSTYLE_PopupMenu), view);
                popupMenu.getMenuInflater().inflate(R.menu.dashboard_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.profile) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard_New.this);
                            View inflate = DashBoard_New.this.getLayoutInflater().inflate(R.layout.custom_profile, (ViewGroup) null);
                            ImageView unused = DashBoard_New.this.img_profile = (ImageView) inflate.findViewById(R.id.img_profile);
                            ImageView imageView = (ImageView) inflate.findViewById(R.id.img_edit_dp);
                            if (DashBoard_New.this.file_profile_pic.exists()) {
                                Picasso.get().load(FileProvider.getUriForFile(DashBoard_New.this, "bsnl.bsnl_teevra.provider", DashBoard_New.this.file_profile_pic)).transform((Transformation) new CircleTransform()).into(DashBoard_New.this.img_profile);
                            } else {
                                DashBoard_New.this.img_profile.setImageResource(R.drawable.ic_new_profile_purple);
                            }
                            ((TextView) inflate.findViewById(R.id.txt_name)).setText(DashBoard_New.this.Pref_Name);
                            ((TextView) inflate.findViewById(R.id.txt_username)).setText(DashBoard_New.this.Pref_Username);
                            ((TextView) inflate.findViewById(R.id.txt_fmsusername)).setText(string);
                            ((TextView) inflate.findViewById(R.id.txt_mobile)).setText(DashBoard_New.this.Pref_Mobile);
                            ((TextView) inflate.findViewById(R.id.txt_deg)).setText(DashBoard_New.this.Pref_Designation + " " + DashBoard_New.this.Pref_Unit);
                            ((TextView) inflate.findViewById(R.id.txt_circle)).setText(DashBoard_New.this.Pref_Circle);
                            ((TextView) inflate.findViewById(R.id.txt_ssa)).setText(DashBoard_New.this.Pref_SSA);
                            ((TextView) inflate.findViewById(R.id.txt_user)).setText(DashBoard_New.this.Pref_Role);
                            ((TextView) inflate.findViewById(R.id.txt_access)).setText(DashBoard_New.this.Pref_Access_level);
                            builder.setCancelable(true);
                            builder.setView(inflate);
                            AlertDialog unused2 = DashBoard_New.this.about_dialog = builder.create();
                            DashBoard_New.this.about_dialog.show();
                            imageView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard_New.this);
                                    View inflate = DashBoard_New.this.getLayoutInflater().inflate(R.layout.custom_profile_pic, (ViewGroup) null);
                                    builder.setView(inflate);
                                    AlertDialog unused = DashBoard_New.this.profile_pic_dialog = builder.create();
                                    DashBoard_New.this.profile_pic_dialog.requestWindowFeature(1);
                                    WindowManager.LayoutParams attributes = DashBoard_New.this.profile_pic_dialog.getWindow().getAttributes();
                                    attributes.gravity = 80;
                                    attributes.flags &= -3;
                                    DashBoard_New.this.profile_pic_dialog.getWindow().setAttributes(attributes);
                                    DashBoard_New.this.profile_pic_dialog.show();
                                    ((ImageView) inflate.findViewById(R.id.img_camera)).setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            DashBoard_New.this.profile_pic_dialog.cancel();
                                            if (!DashBoard_New.this.permissionManager_camera.arePermissionsGranted()) {
                                                DashBoard_New.this.permissionManager_camera.requestPermissions();
                                            } else {
                                                DashBoard_New.this.Open_Camera();
                                            }
                                        }
                                    });
                                    ((ImageView) inflate.findViewById(R.id.img_gallery)).setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            DashBoard_New.this.profile_pic_dialog.cancel();
                                            DashBoard_New.this.Open_Gallery();
                                        }
                                    });
                                }
                            });
                        } else if (itemId == R.id.about) {
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(DashBoard_New.this);
                            View inflate2 = DashBoard_New.this.getLayoutInflater().inflate(R.layout.custom_about, (ViewGroup) null);
                            ((TextView) inflate2.findViewById(R.id.txt_version)).setText("Version 2.7.1");
                            builder2.setCancelable(true);
                            builder2.setView(inflate2);
                            AlertDialog unused3 = DashBoard_New.this.about_dialog = builder2.create();
                            DashBoard_New.this.about_dialog.show();
                        } else if (itemId == R.id.logout) {
                            AlertDialog.Builder builder3 = new AlertDialog.Builder(DashBoard_New.this);
                            View inflate3 = DashBoard_New.this.getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
                            builder3.setCancelable(false);
                            builder3.setView(inflate3);
                            AlertDialog unused4 = DashBoard_New.this.logout_dialog = builder3.create();
                            DashBoard_New.this.logout_dialog.show();
                            ((TextView) inflate3.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    DashBoard_New.this.logout_dialog.cancel();
                                }
                            });
                            ((TextView) inflate3.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    SharedPreferences.Editor unused = DashBoard_New.this.editor = DashBoard_New.this.sharedPreferences.edit();
                                    DashBoard_New.this.editor.clear();
                                    DashBoard_New.this.editor.commit();
                                    DashBoard_New.this.finishAffinity();
                                    System.exit(0);
                                    DashBoard_New.this.logout_dialog.cancel();
                                }
                            });
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        this.img_dp = (ImageView) findViewById(R.id.img_dp);
        if (this.file_profile_pic.exists()) {
            Uri uriForFile = FileProvider.getUriForFile(this, "bsnl.bsnl_teevra.provider", this.file_profile_pic);
            Picasso.get().invalidate(uriForFile);
            Picasso.get().load(uriForFile).transform((Transformation) new CircleTransform()).into(this.img_dp);
            return;
        }
        this.img_dp.setImageResource(R.drawable.ic_new_profile_purple);
    }

    /* access modifiers changed from: private */
    public void Open_Camera() {
        this.file_temp = new File(getExternalCacheDir(), this.Pref_Username + "_temp.jpg");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Uri uriForFile = FileProvider.getUriForFile(this, "bsnl.bsnl_teevra.provider", this.file_temp);
        this.photoURI = uriForFile;
        intent.putExtra("output", uriForFile);
        startActivityForResult(intent, 1000);
    }

    /* access modifiers changed from: private */
    public void Open_Gallery() {
        startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1001);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri output;
        super.onActivityResult(i, i2, intent);
        if (i == 1000 && i2 == -1) {
            if (this.photoURI != null) {
                UCrop.of(this.photoURI, FileProvider.getUriForFile(this, "bsnl.bsnl_teevra.provider", this.file_profile_pic)).withAspectRatio(1.0f, 1.0f).withMaxResultSize(512, 512).start((Activity) this, 1002);
            }
        } else if (i == 1001 && i2 == -1 && intent != null) {
            Uri data = intent.getData();
            if (data != null) {
                UCrop.of(data, FileProvider.getUriForFile(this, "bsnl.bsnl_teevra.provider", this.file_profile_pic)).withAspectRatio(1.0f, 1.0f).withMaxResultSize(512, 512).start((Activity) this, 1002);
            }
        } else if (i == 1002 && i2 == -1 && intent != null && (output = UCrop.getOutput(intent)) != null) {
            Picasso.get().invalidate(output);
            Picasso.get().load(output).transform((Transformation) new CircleTransform()).error((int) R.drawable.ic_new_profile_purple).into(this.img_profile);
            Picasso.get().load(output).transform((Transformation) new CircleTransform()).error((int) R.drawable.ic_new_profile_purple).into(this.img_dp);
            File file = this.file_temp;
            if (file != null && file.exists()) {
                this.file_temp.delete();
            }
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 102) {
            this.permissionManager_camera.onRequestPermissionsResult(i, strArr, iArr);
        } else if (i == 201) {
            this.permissionManager_PostNotifications.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void onPermissionGranted(int i) {
        if (i == 102) {
            new Handler(Looper.getMainLooper()).postDelayed(new DashBoard_New$$ExternalSyntheticLambda0(this), 1000);
        } else if (i == 201) {
            Toast.makeText(this, "Post Notification Permission is Granted", 0).show();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPermissionGranted$0$bsnl-bsnl_teevra-DashBoard_New  reason: not valid java name */
    public /* synthetic */ void m234lambda$onPermissionGranted$0$bsnlbsnl_teevraDashBoard_New() {
        Open_Camera();
        Toast.makeText(this, "Camera Permission is Granted", 0).show();
    }

    public void onPermissionDenied(int i) {
        if (i == 102) {
            Toast.makeText(this, "Camera Permission Denied.", 0).show();
        } else if (i == 201) {
            Toast.makeText(this, "Post Notification Permission Denied.", 0).show();
        }
    }

    public void onPermissionPermanentlyDenied(int i) {
        if (i == 102) {
            Toast.makeText(this, "Camera Permission Denied.", 0).show();
        } else if (i == 201) {
            Toast.makeText(this, "Post Notification Permission Denied.", 0).show();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.card_view1) {
            if (this.Pref_detail_bb_Key || this.Pref_detail_ftth_Key || this.Pref_detail_tip_Key) {
                startActivity(new Intent(this, BBDetail_Activity.class));
                overridePendingTransition(0, 0);
                return;
            }
            Alert("BROADBAND-DETAILS");
        } else if (view.getId() == R.id.card_view2) {
            if (this.Pref_fms_Key) {
                startActivity(new Intent(this, FMS_Login_Activity.class));
            } else {
                Alert("FMS");
            }
        } else if (view.getId() == R.id.card_view3) {
            if (this.Pref_nms_dslam_Key || this.Pref_nms_olt_Key || this.Pref_nms_tpartyolt_Key) {
                startActivity(new Intent(this, NMS_Activity.class));
            } else {
                Alert("NMS");
            }
        } else if (view.getId() == R.id.card_view4) {
            startActivity(new Intent(this, Wizards_Activity.class));
        } else if (view.getId() == R.id.card_view5) {
            if (this.Pref_nw_glance_Key) {
                startActivity(new Intent(this, Network_Status_Activity.class));
            } else {
                Alert("NETWORK GLANCE");
            }
        } else if (view.getId() == R.id.card_view6) {
            if (this.Pref_PMS_key) {
                startActivity(new Intent(this, Pms_New_Activity.class));
            } else {
                Alert("PMS");
            }
        } else if (view.getId() == R.id.card_view7) {
            if (!this.Pref_Role.equals("USER") || this.Pref_report_Key) {
                startActivity(new Intent(this, Report_Activity.class));
            } else {
                Alert("REPORTs");
            }
        } else if (view.getId() != R.id.card_view8) {
        } else {
            if (!this.Pref_Role.equals("USER")) {
                startActivity(new Intent(this, Setting_New_Activity.class));
            } else {
                Alert("ADMIN");
            }
        }
    }

    private void Alert(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_submit);
        ((TextView) inflate.findViewById(R.id.txt_info)).setText("ACCESS RESTRICTED");
        ((TextView) inflate.findViewById(R.id.txt_error)).setText("You Are Not Authorized To Use " + str);
        ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
        textView.setBackgroundResource(0);
        textView.setGravity(5);
        textView.setText("OK");
        builder.setCancelable(false);
        builder.setView(inflate);
        AlertDialog create = builder.create();
        this.logout_dialog = create;
        create.show();
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DashBoard_New.this.logout_dialog.cancel();
            }
        });
    }
}
