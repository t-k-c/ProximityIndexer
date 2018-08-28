package tk.tkctechnologies.pindexer.proximityindexer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button= (Button) findViewById(R.id.toaster);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv1 = (TextView)findViewById(R.id.resp);
                EditText editText = (EditText) findViewById(R.id.et1);
                EditText editText2 = (EditText) findViewById(R.id.et2);
                if(editText.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()){
                    Toast.makeText(main.this, "Empty field(s) ", Toast.LENGTH_SHORT).show();
                    return;
                }
                int response = Integer.parseInt(editText.getText().toString())+Integer.parseInt(editText2.getText().toString());
                tv1.setText(response+"");
                Toast.makeText(main.this, "Toast Boy "+response, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
