package bsnl.bsnl_teevra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Report_Misc_Activity extends AppCompatActivity implements View.OnClickListener {
    private String Perf_Role;
    private ImageView imageView;
    private ImageView img_dai;
    private ImageView img_olt_pms;
    private ImageView img_olt_pwd;
    private LinearLayout lay_dai;
    private LinearLayout lay_olt_pms;
    private LinearLayout lay_olt_pwd;
    private SharedPreferences sharedPreferences;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.report_misc_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Perf_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.lay_dai = (LinearLayout) findViewById(R.id.lay_dai);
        this.lay_olt_pwd = (LinearLayout) findViewById(R.id.lay_olt_pwd);
        this.lay_olt_pms = (LinearLayout) findViewById(R.id.lay_olt_pms);
        ImageView imageView2 = (ImageView) findViewById(R.id.img_header);
        this.imageView = imageView2;
        imageView2.setOnClickListener(this);
        this.lay_dai.setOnClickListener(this);
        this.lay_olt_pwd.setOnClickListener(this);
        this.lay_olt_pms.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.lay_dai) {
            startActivity(new Intent(this, Report_Misc_Dai_Activity.class));
        } else if (view.getId() == R.id.lay_olt_pwd) {
            startActivity(new Intent(this, Report_Misc_Oltlogin_Activity.class));
        } else if (view.getId() == R.id.lay_olt_pms) {
            startActivity(new Intent(this, Report_Misc_Pms_Activity.class));
        }
    }
}
