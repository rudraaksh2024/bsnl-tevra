package bsnl.bsnl_teevra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Whats_New_Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String Demo_videoID = "DemoVideoID_key";
    private static final String Is_Demo_viewed_preference = "isDemoViewed_key";
    private static final String mypreference = "myloginpreference";
    private CheckBox chk_marked_as_view;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private Toolbar toolbar;
    private TextView txt_skip;
    private TextView txt_tv1;
    private TextView txt_tv2;
    private TextView txt_tv2_2;
    private TextView txt_tv3;
    private TextView txt_tv3_3;
    private YouTubePlayerView youTubePlayerView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.whats_new_activity);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.txt_tv1 = (TextView) findViewById(R.id.txt_tv1);
        this.txt_tv2 = (TextView) findViewById(R.id.txt_tv2);
        this.txt_tv3 = (TextView) findViewById(R.id.txt_tv3);
        this.txt_tv2_2 = (TextView) findViewById(R.id.txt_tv2_2);
        this.txt_tv3_3 = (TextView) findViewById(R.id.txt_tv3_3);
        this.txt_tv2_2.setText(Html.fromHtml("Some features of the <b><i>BSNL Teevra</i></b> app, including <b><i>Camera</i></b> functionality and <b><i>File operations</i></b>, were reportedly not working on devices running the latest Android versions.This update ensures compliance with <b><i>latest Android</i></b> permissions and improves <b><i>stability</i></b> and <b><i>privacy.</i></b>", 63));
        this.txt_tv3_3.setText(Html.fromHtml("The <b><i>Olt Site Inspection</i></b> feature is integrated with <b><i>CDR3</i></b> system.", 63));
        this.txt_skip = (TextView) findViewById(R.id.txt_skip);
        this.chk_marked_as_view = (CheckBox) findViewById(R.id.chk_marked_as_view);
        this.txt_skip.setOnClickListener(this);
        this.txt_tv1.setPaintFlags(8);
        this.txt_tv2.setPaintFlags(8);
        this.txt_tv3.setPaintFlags(8);
        this.txt_skip.setPaintFlags(8);
        this.youTubePlayerView = (YouTubePlayerView) findViewById(R.id.video_play);
        getLifecycle().addObserver(this.youTubePlayerView);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.youTubePlayerView.release();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.txt_skip) {
            String string = getString(R.string.Demo_VideoID);
            SharedPreferences sharedPreferences2 = getSharedPreferences(mypreference, 0);
            this.sharedPreferences = sharedPreferences2;
            this.editor = sharedPreferences2.edit();
            if (this.chk_marked_as_view.isChecked()) {
                this.editor.putBoolean(Is_Demo_viewed_preference, true);
                this.editor.putString(Demo_videoID, string);
                this.editor.commit();
            }
            this.youTubePlayerView.release();
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        }
    }
}
