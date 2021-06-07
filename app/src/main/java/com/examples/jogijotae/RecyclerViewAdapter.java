package com.examples.jogijotae;

import android.app.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
private static final String TAG="RecyclerViewAdapter";
    private Activity activity;
    private List<Person> person;
    private MainActivity ac;

    public RecyclerViewAdapter(Activity activity, List<Person> person) {
        this.activity = activity;
        //MainActivity의 recyclerViewAdapter = new RecyclerViewAdapter(this,person); person 연관
        this.person = person;
    }

    //data 갯수 반환
    @Override
    public int getItemCount() {
        return person.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_user);
            number = (TextView) itemView.findViewById(R.id.number_user);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "click " +
                            person.get(getBindingAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"태그는"+person.get(getBindingAdapterPosition()).getName());

                    Intent intent01 = new Intent(view.getContext(),Category_detail4.class);
                    intent01.putExtra("name",person.get(getBindingAdapterPosition()).getName());
                    view.getContext().startActivity(intent01);

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(activity, "remove " +
                            person.get(getBindingAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                    removeItemView(getBindingAdapterPosition());
                    return false;
                }
            });
        }
    }

    //뷰 홀더를 생성하고 뷰를 붙여주는 부분
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // 재활용 되는 View가 호출, Adapter가 해당 position에 해당하는 데이터를 결합
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Person data = person.get(position);

        // 데이터 결합
        holder.name.setText(data.getDirection()+"km\n"+data.getName());


    }

    private void removeItemView(int position) {
        person.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, person.size()); // 지워진 만큼 다시 채워넣기.
    }




}
