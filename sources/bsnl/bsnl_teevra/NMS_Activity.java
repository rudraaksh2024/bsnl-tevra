package bsnl.bsnl_teevra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class NMS_Activity extends AppCompatActivity implements View.OnClickListener {
    private Boolean Perf_nms_dslam;
    private Boolean Perf_nms_olt;
    private Boolean Perf_nms_tip;
    private ImageView imageView;
    private SharedPreferences sharedPreferences;
    private TabLayout tab_nms;
    private ViewPager viewpager_nms;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.nms_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Perf_nms_dslam = Boolean.valueOf(sharedPreferences2.getBoolean("nms_dslam_Key", false));
        this.Perf_nms_olt = Boolean.valueOf(this.sharedPreferences.getBoolean("nms_olt_Key", false));
        this.Perf_nms_tip = Boolean.valueOf(this.sharedPreferences.getBoolean("nms_tpartyolt_Key", false));
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.tab_nms = (TabLayout) findViewById(R.id.tab_nms);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_nms);
        this.viewpager_nms = viewPager;
        viewPager.setOffscreenPageLimit(2);
        setupViewPager(this.viewpager_nms);
        this.tab_nms.setupWithViewPager(this.viewpager_nms);
        this.imageView.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        if (this.Perf_nms_dslam.booleanValue()) {
            if (this.Perf_nms_olt.booleanValue()) {
                if (this.Perf_nms_tip.booleanValue()) {
                    sectionPageAdapter.addFragments(new NMS_DSLAM_Fragment(), "DSLAM NMS");
                    sectionPageAdapter.addFragments(new NMS_OLT_Fragment(), "OLT NMS");
                    sectionPageAdapter.addFragments(new NMS_TIP_Fragment(), "TIPS OLT NMS");
                } else {
                    sectionPageAdapter.addFragments(new NMS_DSLAM_Fragment(), "DSLAM NMS");
                    sectionPageAdapter.addFragments(new NMS_OLT_Fragment(), "OLT NMS");
                }
            } else if (this.Perf_nms_tip.booleanValue()) {
                sectionPageAdapter.addFragments(new NMS_DSLAM_Fragment(), "DSLAM NMS");
                sectionPageAdapter.addFragments(new NMS_TIP_Fragment(), "TIPS OLT NMS");
            } else {
                sectionPageAdapter.addFragments(new NMS_DSLAM_Fragment(), "DSLAM NMS");
            }
        } else if (this.Perf_nms_olt.booleanValue()) {
            if (this.Perf_nms_tip.booleanValue()) {
                sectionPageAdapter.addFragments(new NMS_OLT_Fragment(), "OLT NMS");
                sectionPageAdapter.addFragments(new NMS_TIP_Fragment(), "TIPS OLT NMS");
            } else {
                sectionPageAdapter.addFragments(new NMS_OLT_Fragment(), "OLT NMS");
            }
        } else if (this.Perf_nms_tip.booleanValue()) {
            sectionPageAdapter.addFragments(new NMS_TIP_Fragment(), "TIPS OLT NMS");
        }
        viewPager.setAdapter(sectionPageAdapter);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        }
    }
}
