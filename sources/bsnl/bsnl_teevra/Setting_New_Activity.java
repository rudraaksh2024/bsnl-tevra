package bsnl.bsnl_teevra;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Setting_New_Activity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout card_bulk_onu;
    private LinearLayout card_bulk_tr069;
    private LinearLayout card_manage_inventory;
    private LinearLayout card_manage_user;
    private LinearLayout card_pms_config;
    private ImageView imageView;
    private LinearLayout lay1;
    private LinearLayout lay2;
    private LinearLayout lay3;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.setting_new_activity);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        this.lay1 = (LinearLayout) findViewById(R.id.lay1);
        this.lay2 = (LinearLayout) findViewById(R.id.lay2);
        this.lay3 = (LinearLayout) findViewById(R.id.lay3);
        int i2 = (int) (((double) i) / 2.5d);
        this.lay1.setLayoutParams(new LinearLayout.LayoutParams(-1, i2));
        this.lay2.setLayoutParams(new LinearLayout.LayoutParams(-1, i2));
        this.lay3.setLayoutParams(new LinearLayout.LayoutParams(-1, i2));
        this.card_manage_inventory = (LinearLayout) findViewById(R.id.card_manage_inventory);
        this.card_manage_user = (LinearLayout) findViewById(R.id.card_manage_user);
        this.card_pms_config = (LinearLayout) findViewById(R.id.card_pms_config);
        this.card_bulk_onu = (LinearLayout) findViewById(R.id.card_bulk_onu);
        this.card_bulk_tr069 = (LinearLayout) findViewById(R.id.card_bulk_tr069);
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.card_manage_inventory.setOnClickListener(this);
        this.card_manage_user.setOnClickListener(this);
        this.card_pms_config.setOnClickListener(this);
        this.card_bulk_onu.setOnClickListener(this);
        this.card_bulk_tr069.setOnClickListener(this);
        this.imageView.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.card_manage_inventory) {
            startActivity(new Intent(this, Manage_Inventory_Activity.class));
        } else if (view.getId() == R.id.card_manage_user) {
            startActivity(new Intent(this, Manage_User_Activity.class));
        } else if (view.getId() == R.id.card_pms_config) {
            startActivity(new Intent(this, Pms_Setting_New_Activity.class));
        } else if (view.getId() == R.id.card_bulk_onu) {
            startActivity(new Intent(this, Bulk_Onu_Activity.class));
        } else if (view.getId() == R.id.card_bulk_tr069) {
            startActivity(new Intent(this, Bulk_TR069_Activity.class));
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, DashBoard_New.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
    }
}
