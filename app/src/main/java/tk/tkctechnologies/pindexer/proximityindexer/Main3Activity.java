package tk.tkctechnologies.pindexer.proximityindexer;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class Main3Activity extends AppCompatActivity {
TextView tv1,tv2;
    String prevR="0";
    String val;
    String lastVal="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
         tv1 = (TextView) findViewById(R.id.textView);
         tv2 = (TextView) findViewById(R.id.textView2);
    }
    public void btnCl(View v){
        Button button = (Button) v;
        tv1.append(button.getText());
        String text = button.getText().toString();
        if(text.equals("*") || text.equals("+") || text.equals("/") || text.equals("-")){
            val = prevR+""+button.getText();
        }
        else{
            lastVal = text;
                calc(v);
        }
    }
    public void btnClear(View v){
        tv1.setText("");
        tv2.setText("");

    }
    public void calc(View v) {

        String text = val+lastVal;
        Toast.makeText(this, ""+text, Toast.LENGTH_SHORT).show();
        Double one, two;
        if (text.contains("*")) {
            String[] strings = text.split("[*]");
            try {
                one = Double.parseDouble(strings[0]);
                two = Double.parseDouble(strings[1]);
                tv2.setText("" + (one * two));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Snackbar.make(v, "Please input correctly", Snackbar.LENGTH_SHORT).show();
                return;
            }
        }
        if (text.contains("+")) {
            String[] strings = text.split("[+]");
            try {
                one = Double.parseDouble(strings[0]);
                two = Double.parseDouble(strings[1]);
                tv2.setText("" + (one + two));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Snackbar.make(v, "Please input correctly", Snackbar.LENGTH_SHORT).show();
                return;
            }
        }
        if (text.contains("-")) {
            String[] strings = text.split("-");
            try {
                one = Double.parseDouble(strings[0]);
                two = Double.parseDouble(strings[1]);
                tv2.setText("" + (one - two));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Snackbar.make(v, "Please input correctly", Snackbar.LENGTH_SHORT).show();
                return;
            }
        }
        if (text.contains("/")) {
            String[] strings = text.split("/");
            try {
                one = Double.parseDouble(strings[0]);
                two = Double.parseDouble(strings[1]);
                if (two != 0)
                    tv2.setText("" + (one / two));
                else
                    throw new Exception("NumberFormation");
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.make(v, "Please input correctly", Snackbar.LENGTH_SHORT).show();
                return;
            }
        }
        if(!tv2.getText().toString().isEmpty()){
            prevR =tv2.getText().toString();
        }

        if(v.getId() == R.id.button22){
            tv1.setText("");
    }
    }
}
