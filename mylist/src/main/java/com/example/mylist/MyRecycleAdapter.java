package com.example.mylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;


public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyHolder>  {
    ArrayList<MemberVO> data;
    public MyRecycleAdapter(){}
    public MyRecycleAdapter(ArrayList<MemberVO> data){
        this.data = data;
    }
    //public void  setData(ArrayList<MemberVO>)

    //view에 클릭 이벤트를 건다.
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        //밑에 생성자 로부터 txtName, txtAddr 가져온다.
        MyHolder vh = new MyHolder(v);
        return vh;
    }

    //바인딩 하는 부분
    //매칭할 때 홀더로부터 꺼내쓰면 된다. myHolder
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.txtName.setText(data.get(position).getTitle());
        holder.txtAddr.setText(data.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //view홀더 상속 받는 것 부터 하기
    //초기화 시킨다.
    class MyHolder  extends RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtAddr;

        //생성자
        public MyHolder(@NonNull View itemView){
        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtAddr = itemView.findViewById(R.id.txtAddr);
        }
    }
}
