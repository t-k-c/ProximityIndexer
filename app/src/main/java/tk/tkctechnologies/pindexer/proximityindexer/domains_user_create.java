package tk.tkctechnologies.pindexer.proximityindexer;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tk.tkctechnologies.pindexer.proximityindexer.RecyclerAdapters.DomainsAdapter;

public class domains_user_create extends AppCompatActivity {
 String[] drawerList,drawerListDesc;
    int[] drawerIcons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domains_user_create);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fabColor)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawerIcons = new int[]{R.drawable.ic_login_signup, R.drawable.ic_site, R.drawable.ic_account_blue, R.drawable.ic_location, R.drawable.ic_help,
                R.drawable.ic_settings, R.drawable.ic_about};
        Resources resources = getResources();
        drawerList = resources.getStringArray(R.array.drawerMenu);
        drawerListDesc = resources.getStringArray(R.array.drawerMenuDescription);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.domainLv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DomainsAdapter(drawerList,drawerListDesc,drawerIcons));
    }

}
