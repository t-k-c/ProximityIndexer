package tk.tkctechnologies.pindexer.proximityindexer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import tk.tkctechnologies.pindexer.proximityindexer.RecyclerAdapters.DomainsAdapter;


public class SearchFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        drawerIcons = new int[]{R.drawable.ic_login_signup, R.drawable.ic_site, R.drawable.ic_account_blue, R.drawable.ic_location, R.drawable.ic_help,
                R.drawable.ic_settings, R.drawable.ic_about};
        Resources resources = getResources();
        Context context=getContext();
        drawerList = resources.getStringArray(R.array.domainMenu);
        drawerListDesc = resources.getStringArray(R.array.domainMenuDescription);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.domainLv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new DomainsAdapter(drawerList,drawerListDesc,drawerIcons,context));
        return view;
    }


    String[] drawerList, drawerListDesc;
    int[] drawerIcons;

    @Override
    public void onAttach(Context context) {
//        ViewPager view_pager = new ViewPager(getActivity());

        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    private class DomainArrayAdapter extends ArrayAdapter<String> {
        Context context;

         DomainArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull String[] objects) {
            super(context, resource, textViewResourceId, objects);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View vue = convertView;
            ViewHolder viewHolder;
            if (vue == null) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                vue = layoutInflater.inflate(R.layout.domains, parent, false);
                viewHolder = new ViewHolder(vue);
                vue.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) vue.getTag();
            }
            viewHolder.imageView.setImageResource(drawerIcons[position]);
            viewHolder.tv1.setText(drawerList[position]);
            viewHolder.tv2.setText(drawerListDesc[position]);
            return vue;
        }

        @Override
        public int getCount() {
            return drawerIcons.length;
        }
    }

    private class ViewHolder {
        ImageView imageView;
        TextView tv1, tv2;

        ViewHolder(View view) {
            imageView = view.findViewById(R.id.domainIcon);
            tv1 = view.findViewById(R.id.domainName);
            tv2 = view.findViewById(R.id.domainDesc);
        }
    }
}
