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
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import bsnl.bsnl_teevra.fms.FMS_Bulk_TR069_Detail_Activity;

public class Fms_Admin_Activity extends AppCompatActivity implements View.OnClickListener {
    private String Pref_Fms_Username;
    private String Pref_Fms_role;
    private LinearLayout card_fms_bulk_onu;
    private LinearLayout card_fms_bulk_tr069;
    private LinearLayout card_fms_inventory;
    SharedPreferences.Editor editor;
    private AlertDialog error_dialog;
    private AlertDialog info_dialog;
    private LinearLayout lay1;
    private LinearLayout lay2;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    private AlertDialog progress_dialog;
    SharedPreferences sharedPreferences;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_admin_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Fms_role = this.sharedPreferences.getString("fms_role_key", (String) null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        this.lay1 = (LinearLayout) findViewById(R.id.lay1);
        this.lay2 = (LinearLayout) findViewById(R.id.lay2);
        int i2 = (int) (((double) i) / 2.5d);
        this.lay1.setLayoutParams(new LinearLayout.LayoutParams(-1, i2));
        this.lay2.setLayoutParams(new LinearLayout.LayoutParams(-1, i2));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.card_fms_inventory);
        this.card_fms_inventory = linearLayout;
        linearLayout.setOnClickListener(this);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.card_fms_bulk_onu);
        this.card_fms_bulk_onu = linearLayout2;
        linearLayout2.setOnClickListener(this);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.card_fms_bulk_tr069);
        this.card_fms_bulk_tr069 = linearLayout3;
        linearLayout3.setOnClickListener(this);
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
                    Fms_Admin_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Fms_Admin_Activity fms_Admin_Activity = Fms_Admin_Activity.this;
                    fms_Admin_Activity.editor = fms_Admin_Activity.sharedPreferences.edit();
                    Fms_Admin_Activity.this.editor.remove("isfmsloggedin_key");
                    Fms_Admin_Activity.this.editor.commit();
                    Intent intent = new Intent(Fms_Admin_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    Fms_Admin_Activity.this.startActivity(intent);
                    Fms_Admin_Activity.this.finish();
                    Fms_Admin_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.card_fms_inventory) {
            startActivity(new Intent(this, Fms_Inventory_NewActivity.class));
        } else if (view.getId() == R.id.card_fms_bulk_tr069) {
            startActivity(new Intent(this, FMS_Bulk_TR069_Detail_Activity.class));
        } else if (view.getId() == R.id.card_fms_bulk_onu) {
            startActivity(new Intent(this, FMS_Bulk_Onu_Detail_Activity.class));
        }
    }
}
