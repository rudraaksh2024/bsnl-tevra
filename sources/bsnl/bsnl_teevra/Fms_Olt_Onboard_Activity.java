package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Fms_Olt_Onboard_Activity extends AppCompatActivity implements View.OnClickListener {
    private String Pref_Fms_Username;
    private String Pref_Fms_role;
    private AlertDialog custom_olt_onboard;
    SharedPreferences.Editor editor;
    private AlertDialog error_dialog;
    private LinearLayout lay_new_olt;
    private RelativeLayout lay_site_main;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    private AlertDialog progress_dialog;
    SharedPreferences sharedPreferences;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_olt_onboard_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Fms_role = this.sharedPreferences.getString("fms_role_key", (String) null);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay_new_olt);
        this.lay_new_olt = linearLayout;
        linearLayout.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.lay_new_olt) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_olt_onboard, (ViewGroup) null);
            this.lay_site_main = (RelativeLayout) inflate.findViewById(R.id.lay_site_main);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.lay_site_main.setLayoutParams(new LinearLayout.LayoutParams(-1, (displayMetrics.widthPixels * 10) / 16));
            builder.setCancelable(true);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.custom_olt_onboard = create;
            create.show();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(this.custom_olt_onboard.getWindow().getAttributes());
            layoutParams.width = -1;
            layoutParams.height = -2;
            this.custom_olt_onboard.getWindow().setAttributes(layoutParams);
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
                    Fms_Olt_Onboard_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Fms_Olt_Onboard_Activity fms_Olt_Onboard_Activity = Fms_Olt_Onboard_Activity.this;
                    fms_Olt_Onboard_Activity.editor = fms_Olt_Onboard_Activity.sharedPreferences.edit();
                    Fms_Olt_Onboard_Activity.this.editor.remove("isfmsloggedin_key");
                    Fms_Olt_Onboard_Activity.this.editor.commit();
                    Intent intent = new Intent(Fms_Olt_Onboard_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    Fms_Olt_Onboard_Activity.this.startActivity(intent);
                    Fms_Olt_Onboard_Activity.this.finish();
                    Fms_Olt_Onboard_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
