package tk.tkctechnologies.pindexer.proximityindexer;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpFeedBack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_feed_back);
        TextView title = (TextView) findViewById(R.id.titleTv);
        title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/CabinSketch-Regular.ttf"));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fabColor)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Send FeedBack", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                HelpFeedBack.super.onBackPressed();
                            }
                        }).show();
            }
        });

    }
    public void backButtonClicked(View v){
        super.onBackPressed();
    }
}
