package com.appmk.book.AOVDDFHFPJTMQIDS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BEK on 19.02.2017.
 */

public class SonsListViewAdapter extends BaseAdapter {

    private List<Sons> sons;
    private Context context;
    private LayoutInflater inflater;

    public SonsListViewAdapter(Context context, List<Sons> sons) {
        this.context = context;
        this.sons = sons;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return sons.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.row_sons_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.sonTitle.setText(sons.get(position).getTitle());
        viewHolder.sonDefinition.setText(sons.get(position).getDefinition());

        return convertView;
    }

    private class ViewHolder {
        TextView sonDefinition;
        TextView sonTitle;

        public ViewHolder(View convertView){
            sonDefinition  = (TextView) convertView.findViewById(R.id.sonDefinition);
            sonTitle = (TextView) convertView.findViewById(R.id.sonTitle);

        }
    }
}
