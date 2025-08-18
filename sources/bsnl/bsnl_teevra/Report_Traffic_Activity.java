package bsnl.bsnl_teevra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Report_Traffic_Activity extends AppCompatActivity implements View.OnClickListener {
    private String Perf_Role;
    private ImageView imageView;
    private ImageView img_tip_olt;
    private LinearLayout lay_olt_reboot;
    private LinearLayout lay_peak_traffic;
    private LinearLayout lay_peak_traffic_daywise;
    private SharedPreferences sharedPreferences;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.report_traffic_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Perf_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.lay_peak_traffic_daywise = (LinearLayout) findViewById(R.id.lay_peak_traffic_daywise);
        this.lay_peak_traffic = (LinearLayout) findViewById(R.id.lay_peak_traffic);
        this.lay_olt_reboot = (LinearLayout) findViewById(R.id.lay_olt_reboot);
        this.imageView.setOnClickListener(this);
        this.lay_peak_traffic_daywise.setOnClickListener(this);
        this.lay_peak_traffic.setOnClickListener(this);
        this.lay_olt_reboot.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.lay_peak_traffic_daywise) {
            startActivity(new Intent(this, Report_Traffic_Tip_Activity.class));
        } else if (view.getId() == R.id.lay_peak_traffic) {
            startActivity(new Intent(this, Report_Traffic_Tip_Cum_Activity.class));
        } else if (view.getId() == R.id.lay_olt_reboot) {
            startActivity(new Intent(this, Report_Olt_Reboot_Activity.class));
        }
    }
}
