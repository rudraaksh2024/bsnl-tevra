package bsnl.bsnl_teevra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Report_Activity extends AppCompatActivity implements View.OnClickListener {
    private String Perf_Role;
    private CardView card_tip_olt_report;
    private ImageView imageView;
    private LinearLayout lay1;
    private LinearLayout lay_miscellaneous;
    private LinearLayout lay_traffic;
    private SharedPreferences sharedPreferences;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.report_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Perf_Role = sharedPreferences2.getString("role_Key", (String) null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay1);
        this.lay1 = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (((float) i) / 2.5f)));
        this.lay_miscellaneous = (LinearLayout) findViewById(R.id.lay_miscellaneous);
        this.lay_traffic = (LinearLayout) findViewById(R.id.lay_traffic);
        ImageView imageView2 = (ImageView) findViewById(R.id.img_header);
        this.imageView = imageView2;
        imageView2.setOnClickListener(this);
        this.lay_miscellaneous.setOnClickListener(this);
        this.lay_traffic.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.lay_miscellaneous) {
            startActivity(new Intent(this, Report_Misc_Activity.class));
        } else if (view.getId() == R.id.lay_traffic) {
            startActivity(new Intent(this, Report_Traffic_Activity.class));
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, DashBoard_New.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
    }
}
