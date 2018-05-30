package com.wishihab.widefend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;



public class AppAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<AppList> listStorage;

    public AppAdapter(Context context, List<AppList> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.content_listapp, parent, false);

            listViewHolder.textInListView = (TextView)convertView.findViewById(R.id.list_app_name);
            listViewHolder.imageInListView = (ImageView)convertView.findViewById(R.id.app_icon);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.textInListView.setText(listStorage.get(position).getName());
        listViewHolder.imageInListView.setImageDrawable(listStorage.get(position).getIcon());

        return convertView;

    }

    static class ViewHolder{

        TextView textInListView;
        ImageView imageInListView;
    }


}




