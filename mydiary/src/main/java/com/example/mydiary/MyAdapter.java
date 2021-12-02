package com.example.mydiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class MyAdapter extends BaseAdapter {

    //어떤 데이터랑 맵핑 할것인지 정해준다.
    ArrayList<DiaryVO> data;    // map에 arrayList를 담아서 사용
    public MyAdapter(){}
    public MyAdapter(ArrayList<DiaryVO> data){this.data = data;}


    public void setData(ArrayList<DiaryVO> data) {
        this.data = data;
    }

    public int getCount() {
        return data.size();
    }
    public Object getItem(int i) {
        return data.get(i);
    }
    public long getItemId(int i) {
        return i;
    }
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.listview_item, viewGroup, false);//list_item 엑티비티 보여주기
        //view가 뭘까 ???
        TextView txtTime = view.findViewById(R.id.txtTime);
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtContent = view.findViewById(R.id.txtContent);
        txtTime.setText(data.get(i).getTime());
        txtTitle.setText(data.get(i).getId());
        txtContent.setText(data.get(i).getContent());

        return view;
    }
}
