package bsnl.bsnl_teevra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class BBDetail_Activity extends AppCompatActivity implements View.OnClickListener {
    private boolean Perf_Detail_BB;
    private boolean Perf_Detail_FTTH;
    private boolean Perf_Detail_TIP;
    private ImageView imageView;
    private SharedPreferences sharedPreferences;
    private TabLayout tabLayout_bbdetail;
    private ViewPager viewPager_bbdetail;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.bbdetail_activity);
        ImageView imageView2 = (ImageView) findViewById(R.id.img_header);
        this.imageView = imageView2;
        imageView2.setOnClickListener(this);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Perf_Detail_BB = sharedPreferences2.getBoolean("detail_bb_Key", false);
        this.Perf_Detail_FTTH = this.sharedPreferences.getBoolean("detail_ftth_Key", false);
        this.Perf_Detail_TIP = this.sharedPreferences.getBoolean("detail_tip_Key", false);
        this.tabLayout_bbdetail = (TabLayout) findViewById(R.id.tab_detail_bb);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_detail_bb);
        this.viewPager_bbdetail = viewPager;
        viewPager.setOffscreenPageLimit(2);
        setupViewPager(this.viewPager_bbdetail);
        this.tabLayout_bbdetail.setupWithViewPager(this.viewPager_bbdetail);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        if (this.Perf_Detail_BB) {
            if (this.Perf_Detail_FTTH) {
                if (this.Perf_Detail_TIP) {
                    sectionPageAdapter.addFragments(new Detail_BB_Fragment(), "BROADBAND");
                    sectionPageAdapter.addFragments(new Detail_FTTH_Fragment(), "FTTH");
                    sectionPageAdapter.addFragments(new Detail_TIP_Fragment(), "TIP");
                } else {
                    sectionPageAdapter.addFragments(new Detail_BB_Fragment(), "BROADBAND");
                    sectionPageAdapter.addFragments(new Detail_FTTH_Fragment(), "FTTH");
                }
            } else if (this.Perf_Detail_TIP) {
                sectionPageAdapter.addFragments(new Detail_BB_Fragment(), "BROADBAND");
                sectionPageAdapter.addFragments(new Detail_TIP_Fragment(), "TIP");
            } else {
                sectionPageAdapter.addFragments(new Detail_BB_Fragment(), "BROADBAND");
            }
        } else if (this.Perf_Detail_FTTH) {
            if (this.Perf_Detail_TIP) {
                sectionPageAdapter.addFragments(new Detail_FTTH_Fragment(), "FTTH");
                sectionPageAdapter.addFragments(new Detail_TIP_Fragment(), "TIP");
            } else {
                sectionPageAdapter.addFragments(new Detail_FTTH_Fragment(), "FTTH");
            }
        } else if (this.Perf_Detail_TIP) {
            sectionPageAdapter.addFragments(new Detail_TIP_Fragment(), "TIP");
        }
        viewPager.setAdapter(sectionPageAdapter);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        }
    }
}
