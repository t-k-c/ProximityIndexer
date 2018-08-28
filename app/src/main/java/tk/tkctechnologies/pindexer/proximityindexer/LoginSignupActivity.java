package tk.tkctechnologies.pindexer.proximityindexer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class LoginSignupActivity extends AppCompatActivity {

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        /*TextView textView = (TextView) findViewById(R.id.logo);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/BerkshireSwash-Regular.ttf"));*/
        Button[] buttons = new Button[]{(Button) findViewById(R.id.email),(Button) findViewById(R.id.signup)};
        for(final Button button : buttons){
            button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        button.setTextColor(getResources().getColor(R.color.loginGradientEnd));
                    }
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                        button.setTextColor(getResources().getColor(R.color.white));
                    }
                    return false;
                }
            });
        }
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSignupActivity.this,LoginActivity.class));
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.main_holder);
        relativeLayout.animate().alpha(1f).setStartDelay(500).setDuration(1650).start();
    }
}
