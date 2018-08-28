package tk.tkctechnologies.pindexer.proximityindexer;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends FragmentActivity{
    TextView tx;
    String[] drawerList;
    String[] drawerListDesc;
    int[] drawerIcons;
    DrawerLayout drawerLayout;
    NavigationView drawerView;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerView = (NavigationView) findViewById(R.id.drawerView);
        drawerIcons = new int[]{R.drawable.ic_login_signup,R.drawable.ic_site,R.drawable.ic_account_blue,R.drawable.ic_location,R.drawable.ic_help,
        R.drawable.ic_settings,R.drawable.ic_about};
        Resources resources =getResources();
        drawerList =  resources.getStringArray(R.array.drawerMenu);
        drawerListDesc =  resources.getStringArray(R.array.drawerMenuDescription);
        ImageButton drawertrigger = (ImageButton)findViewById(R.id.menu);
        drawertrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView,true);
            }
        });
        ListView  listView = (ListView) findViewById(R.id.drawerList);
        listView.setAdapter(new DrawerListAdapter(this,R.layout.custom_drawer_list,R.id.listDesc,drawerListDesc));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0 : startActivity(new Intent(MainActivity.this,LoginSignupActivity.class));break;
                    case 1 : startActivity(new Intent(MainActivity.this,Sites.class));break;
                    case 4 : startActivity(new Intent(MainActivity.this,HelpFeedBack.class));break;
                    default:break;
                }
            }
        });
        tx = (TextView)findViewById(R.id.pindexerText);
        tx.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/CabinSketch-Regular.ttf"));
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
         tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackgroundResource(R.drawable.icon_button);
        final FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fabColor)));

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

               switch(position){
                   case 0: return new HomeFragment();
                   case 1:return new SearchFragment();
                   case 2: return new NotificationsFragment();
                   case 3: return new BroadcastFragment();
                   default: return null;
               }

            }

            @Override
            public int getCount() {
                return 4;
            }

        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for(int i=0;i< tabLayout.getTabCount();i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);

            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab,null);
            ImageView imageView = view.findViewById(R.id.tabicon);
            imageView.setImageResource(getImageForTab(i));
            if(i<2){
                view.findViewById(R.id.dot).setVisibility(View.GONE);
            }
            assert tab != null;
            tab.setCustomView(view);
        }
         tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                 switch (tabLayout.getSelectedTabPosition()){
                     case  1 : tx.setText("Search");break;
                     case  2 : tx.setText("Notifications");break;
                     case  3 : tx.setText("Broadcasts");break;
                     default: tx.setText("Home");
                 }
             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }
         });
    }
    public void search(View v){
        if(tabLayout.getSelectedTabPosition()!=1){
            tabLayout.getTabAt(1).select();
        }
    }
    public int getImageForTab(int index){
        int result=R.drawable.ic_settings;
        switch (index){
            case 0 : result=R.drawable.ic_home_feed;break;
            case 1 : result=R.drawable.ic_search;break;
            case 2 : result=R.drawable.ic_notifications;break;
            case 3 : result=R.drawable.ic_broadcast;break;
            default : break;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(drawerView)){
            drawerLayout.closeDrawer(drawerView,true);
        }
        else if(tabLayout.getSelectedTabPosition()!=0){
           tabLayout.getTabAt(0).select();
        }
        else{
            super.onBackPressed();
        }

    }

    private class ViewHolder{
    ImageView imageView;
    TextView name;
    TextView description;
     ViewHolder(View view){
        imageView = (ImageView) view.findViewById(R.id.listIcon);
         name = (TextView) view.findViewById(R.id.listName);
         description = (TextView)  view.findViewById(R.id.listDesc);
//         Toast.makeText(MainActivity.this, "desc: "+description.getText(), Toast.LENGTH_SHORT).show();
    }
}
private class DrawerListAdapter extends ArrayAdapter<String>{


     DrawerListAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.custom_drawer_list,parent,false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) row.getTag();
        }
        viewHolder.imageView.setImageResource(drawerIcons[position]);
        viewHolder.name.setText(drawerList[position]);
        viewHolder.description.setText(drawerListDesc[position]);
        return row;
    }

    @Override
    public int getCount() {
        return drawerListDesc.length;
    }

}

}