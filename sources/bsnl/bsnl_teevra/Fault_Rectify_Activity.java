package bsnl.bsnl_teevra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class Fault_Rectify_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private TabLayout tab_fault_rectify;
    /* access modifiers changed from: private */
    public TextView txt_header;
    private ViewPager viewpager_fault_rectify;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fault_rectify_activity);
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.tab_fault_rectify = (TabLayout) findViewById(R.id.tab_fault_rectify);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_fault_rectify);
        this.viewpager_fault_rectify = viewPager;
        setupViewPager(viewPager);
        this.tab_fault_rectify.setupWithViewPager(this.viewpager_fault_rectify);
        this.txt_header.setText("(PORT-RESET)");
        this.imageView.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragments(new Fault_Rectify_Port_Reset_Fragment(), "PORT-RESET");
        sectionPageAdapter.addFragments(new Fault_Rectify_Profile_ChangeFragment(), "PROFILE-CHANGE");
        viewPager.setAdapter(sectionPageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (i == 0) {
                    Fault_Rectify_Activity.this.txt_header.setText("(PORT-RESET)");
                } else if (i == 1) {
                    Fault_Rectify_Activity.this.txt_header.setText("(PROFILE-CHANGE)");
                }
            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        }
    }
}
