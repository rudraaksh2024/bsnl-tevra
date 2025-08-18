package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import org.json.JSONException;
import org.json.JSONObject;

public class Fms_Fault_MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String Docket_No;
    private String Perf_Role;
    private String Phone_No;
    private String Pref_Fms_Role;
    private String Pref_Fms_TeamId;
    private String Pref_Fms_UserId;
    private String Pref_Fms_Username;
    private String Pref_Fms_Zone;
    private String Pref_Randkey;
    private String Pref_Username;
    private String Service_type;
    private String androidId;
    SharedPreferences.Editor editor;
    private AlertDialog error_dialog;
    private AlertDialog fault_asign_dialog;
    private AlertDialog fault_close_dialog;
    private LinearLayout lay1;
    private LinearLayout lay2;
    private LinearLayout lay_fault;
    private LinearLayout lay_fault_track;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    private AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;
    private Spannable span_pending;
    private Spannable span_track;
    private TextView txt_alert;
    private TextView txt_fault_pending;
    private TextView txt_fault_track;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_fault_mainactivity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay1);
        this.lay1 = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (((float) i) / 2.5f)));
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Fms_UserId = this.sharedPreferences.getString("fms_userid_key", (String) null);
        this.Pref_Fms_Role = this.sharedPreferences.getString("fms_role_key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Fms_Zone = this.sharedPreferences.getString("fms_user_zone_Key", (String) null);
        this.Pref_Fms_TeamId = this.sharedPreferences.getString("fms_teamid_key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
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
        this.txt_fault_pending = (TextView) findViewById(R.id.txt_fault_pending);
        this.txt_fault_track = (TextView) findViewById(R.id.txt_fault_track);
        try {
            JSONObject jSONObject = new JSONObject(getIntent().getStringExtra("RESPONSE"));
            JSONObject jSONObject2 = jSONObject.getJSONObject("PENDING_FAULT");
            JSONObject jSONObject3 = jSONObject.getJSONObject("TRACK_FAULT");
            String string = jSONObject2.getString("COUNT");
            String string2 = jSONObject3.getString("COUNT");
            this.txt_fault_pending.setText(string);
            this.txt_fault_track.setText(string2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SpannableString spannableString = new SpannableString(this.txt_fault_pending.getText());
        this.span_pending = spannableString;
        spannableString.setSpan(new RelativeSizeSpan(3.0f), 0, this.txt_fault_pending.length(), 33);
        this.txt_fault_pending.setText(this.span_pending);
        SpannableString spannableString2 = new SpannableString(this.txt_fault_track.getText());
        this.span_track = spannableString2;
        spannableString2.setSpan(new RelativeSizeSpan(3.0f), 0, this.txt_fault_track.length(), 33);
        this.txt_fault_track.setText(this.span_track);
        this.lay_fault = (LinearLayout) findViewById(R.id.lay_fault);
        this.lay_fault_track = (LinearLayout) findViewById(R.id.lay_fault_track);
        this.lay_fault.setOnClickListener(this);
        this.lay_fault_track.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fmsmenu, menu);
        menu.findItem(R.id.fmsuser).setTitle(this.Pref_Fms_Username);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.teevrahome) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (itemId == R.id.fmslogout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.logout_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Fms_Fault_MainActivity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Fms_Fault_MainActivity fms_Fault_MainActivity = Fms_Fault_MainActivity.this;
                    fms_Fault_MainActivity.editor = fms_Fault_MainActivity.sharedPreferences.edit();
                    Fms_Fault_MainActivity.this.editor.remove("isfmsloggedin_key");
                    Fms_Fault_MainActivity.this.editor.commit();
                    Intent intent = new Intent(Fms_Fault_MainActivity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    Fms_Fault_MainActivity.this.startActivity(intent);
                    Fms_Fault_MainActivity.this.finish();
                    Fms_Fault_MainActivity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.lay_fault) {
            startActivity(new Intent(this, FMS_Fault_Activity.class));
        } else if (view.getId() == R.id.lay_fault_track) {
            startActivity(new Intent(this, FMS_Track_Fault_Activity.class));
        }
    }
}
