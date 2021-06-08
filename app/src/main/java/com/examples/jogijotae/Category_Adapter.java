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
//리사이클러뷰 사용하기위한 어댑터
public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewHolder> {
private static final String TAG="Category_Adapter"; //로그 찍기위한 태그
    private Activity activity;
    private List<Category_class> categoryclasses;


    public Category_Adapter(Activity activity, List<Category_class> categoryclasses) {
        this.activity = activity;

        this.categoryclasses = categoryclasses;
    }

    //data 갯수 반환
    @Override
    public int getItemCount() {
        return categoryclasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_user);     // 아이템을 보여주기 위해 연결
            number = (TextView) itemView.findViewById(R.id.number_user); // 사용하진 않지만 추후 더 많은 아이템을 보여줄 수 있으므로 연결

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {  //리스트에 아이템을 클릭시

                    Log.d(TAG,"태그는"+ categoryclasses.get(getBindingAdapterPosition()).getName());

                    /*  무슨아이템을 클릭했는지에 대한 정보를 인텐트로 보내면서 Category_GPS_detail로  화면전환*/
                    Intent intent01 = new Intent(view.getContext(), Category_GPS_detail.class);
                    intent01.putExtra("name", categoryclasses.get(getBindingAdapterPosition()).getName());
                    view.getContext().startActivity(intent01);

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(activity, "remove " +         // 구현중인 기능        사용자가 마음에 들지않으면 롱클릭(기능구현)   토스트 + 추후에 뜨지않음( 구현중 )
                            categoryclasses.get(getBindingAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                    removeItemView(getBindingAdapterPosition());                                //아이템삭제
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
        Category_class data = categoryclasses.get(position);

        // 데이터 결합
        holder.name.setText(data.getDirection()+"km\n"+data.getName()); // 거리 +km + 장소 이름 으로 데이터가 뜨게함


    }

    private void removeItemView(int position) {
        categoryclasses.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, categoryclasses.size()); // 지워진 만큼 다시 채워넣기.
    }




}
