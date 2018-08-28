package tk.tkctechnologies.pindexer.proximityindexer;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emailText;
    EditText passwordText;
    boolean firstTime = true; //first time of button click
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.signup);
        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup:
//            NB: DIDNT USE CALL BACK BECAUSE OF SUPPORTIVITY FOR API 15
                if (firstTime) {
                    //verify the email before the animation
                    emailText.animate().alpha(0f).translationY(-100f).setDuration(500).start();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        passwordText.setVisibility(View.VISIBLE);
                                        passwordText.animate().alpha(1f).translationY(0f).setDuration(500).start();
                                        button.setText(getResources().getString(R.string.submit));
                                        emailText.setEnabled(false);
                                    }
                                });
                                firstTime = false;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();

                } else {
                    // submit the password here;
                }
                break;
            default:
                break;
        }
    }
}
