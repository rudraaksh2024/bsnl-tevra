package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FMS_BBC_Dashboard_Activity extends AppCompatActivity implements View.OnClickListener {
    private String Pref_Access_Level;
    private String Pref_Circle;
    private String Pref_Fms_Username;
    private String Pref_Nw_Glance;
    private String Pref_Randkey;
    private String Pref_Role;
    private String Pref_SSA;
    private String Pref_Username;
    private String androidId;
    private LinearLayout card_bbc_geoloc;
    private LinearLayout card_fms_site;
    private LinearLayout card_svr;
    SharedPreferences.Editor editor;
    private AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private Intent intent;
    private LinearLayout lay1;
    private LinearLayout lay2;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    private AlertDialog progress_dialog;
    SharedPreferences sharedPreferences;
    private TextView txt_alert;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_bbc_dashboard_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Fms_Username = this.sharedPreferences.getString("fms_username_Key", (String) null);
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
        this.lay1 = (LinearLayout) findViewById(R.id.lay1);
        this.lay2 = (LinearLayout) findViewById(R.id.lay2);
        int i2 = (int) (((double) i) / 2.5d);
        this.lay1.setLayoutParams(new LinearLayout.LayoutParams(-1, i2));
        this.lay2.setLayoutParams(new LinearLayout.LayoutParams(-1, i2));
        this.card_bbc_geoloc = (LinearLayout) findViewById(R.id.card_bbc_geoloc);
        this.card_fms_site = (LinearLayout) findViewById(R.id.card_fms_site);
        this.card_svr = (LinearLayout) findViewById(R.id.card_svr);
        this.card_bbc_geoloc.setOnClickListener(this);
        this.card_fms_site.setOnClickListener(this);
        this.card_svr.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.card_bbc_geoloc) {
            startActivity(new Intent(this, FMS_BBC_Activity.class));
        } else if (view.getId() == R.id.card_fms_site) {
            startActivity(new Intent(this, FMS_BBC_Site_Insp_Activity.class));
        } else if (view.getId() == R.id.card_svr) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
            textView.setBackgroundResource(0);
            textView.setText("OK");
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.info_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("Feature Is Under Development"));
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Dashboard_Activity.this.info_dialog.cancel();
                }
            });
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fmsmenu, menu);
        menu.findItem(R.id.fmsuser).setTitle(this.Pref_Fms_Username);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.teevrahome) {
            Intent intent2 = new Intent(this, DashBoard_New.class);
            intent2.setFlags(67108864);
            startActivity(intent2);
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
                    FMS_BBC_Dashboard_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Dashboard_Activity fMS_BBC_Dashboard_Activity = FMS_BBC_Dashboard_Activity.this;
                    fMS_BBC_Dashboard_Activity.editor = fMS_BBC_Dashboard_Activity.sharedPreferences.edit();
                    FMS_BBC_Dashboard_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_BBC_Dashboard_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_BBC_Dashboard_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_BBC_Dashboard_Activity.this.startActivity(intent);
                    FMS_BBC_Dashboard_Activity.this.finish();
                    FMS_BBC_Dashboard_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
