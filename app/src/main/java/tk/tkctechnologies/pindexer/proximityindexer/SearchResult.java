package tk.tkctechnologies.pindexer.proximityindexer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SearchResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
    }
    public void backButtonClicked(View v){
        super.onBackPressed();
    }
    public void openSite(View v){
        startActivity(new Intent(this,SitePresentation.class));
    }

}
