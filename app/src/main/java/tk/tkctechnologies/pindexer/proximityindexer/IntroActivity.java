package tk.tkctechnologies.pindexer.proximityindexer;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/CabinSketch-Regular.ttf");
        TextView textView = (TextView) findViewById(R.id.greetings);
        textView.setTypeface(typeface);
    }
}
