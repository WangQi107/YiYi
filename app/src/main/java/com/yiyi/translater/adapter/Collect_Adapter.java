package com.yiyi.translater.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yiyi.translater.R;
import com.yiyi.translater.model.Collect;
import com.yiyi.translater.views.XCRoundImageView;

import java.util.List;

public class Collect_Adapter extends BaseAdapter {
    private Context ctx;
    private List<Collect> collects;

    public Collect_Adapter(Context ctx, List<Collect> collects) {
        this.ctx = ctx;
        this.collects = collects;
    }

    @Override
    public int getCount() {
        return collects.size();
    }

    @Override
    public Object getItem(int position) {
        return collects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.item_words, null);
            holder = new ViewHolder();
            holder.wordheader = convertView.findViewById(R.id.word_header);
            holder.wordname = convertView.findViewById(R.id.word_name);
            holder.wordtime = convertView.findViewById(R.id.word_time);
            holder.wordfrom = convertView.findViewById(R.id.word_from);
            holder.wordto = convertView.findViewById(R.id.word_to);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Collect c = collects.get(position);
        holder.wordheader.setImageResource(R.mipmap.ic_header_boy);
        holder.wordname.setText(String.valueOf(c.getUserid()));
        holder.wordtime.setText(String.valueOf(c.getTime()));
        holder.wordfrom.setText("原文："+String.valueOf(c.getYuan()));
        holder.wordto.setText("译文："+String.valueOf(c.getCotent()));
        return convertView;
    }

    class ViewHolder {
        private XCRoundImageView wordheader;
        private TextView wordname;
        private TextView wordtime;
        private TextView wordfrom;
        private TextView wordto;
    }
}
