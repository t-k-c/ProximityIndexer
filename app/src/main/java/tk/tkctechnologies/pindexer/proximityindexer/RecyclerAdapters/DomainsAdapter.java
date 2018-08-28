package tk.tkctechnologies.pindexer.proximityindexer.RecyclerAdapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import tk.tkctechnologies.pindexer.proximityindexer.R;
import tk.tkctechnologies.pindexer.proximityindexer.SearchResult;

/**
 * Created by codename-tkc on 20/01/2018.
 */

public class DomainsAdapter extends RecyclerView.Adapter<DomainsAdapter.ViewHolder> {
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Random random  = new Random();
        int colors[] = {Color.rgb(33, 150, 243), Color.rgb(0, 150, 136), Color.rgb( 244, 67, 54),Color.rgb(233, 30, 99),Color.rgb(156, 39, 176), Color.rgb(103, 58, 183), Color.rgb(63, 81, 181)};
        holder.name.setText(this.name[position]);
        holder.description.setText(description[position]);
        holder.icon.setImageResource(R.drawable.ic_domain);
        final int color =  colors[Math.abs(random.nextInt()%colors.length)];
        holder.icon.setColorFilter(color);
       View v =  holder.itemView;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context!=null){
                   Intent i = new Intent(context, SearchResult.class);
                    i.putExtra("color",color);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.domains, parent, false);
        return new ViewHolder(v);
    }
    String[] description,name;
    int[] icons;
    Context context;
    public DomainsAdapter(String[] name,String[] desc,int[] icons){
        this.name = name;
        this.description = desc;
        this.icons = icons;
    }
    public DomainsAdapter(String[] name,String[] desc,int[] icons,Context context){
        this.name = name;
        this.description = desc;
        this.icons = icons;
        this.context =context;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.domainName);
            description = itemView.findViewById(R.id.domainDesc);
            icon = itemView.findViewById(R.id.domainIcon);
        }
    }
}
