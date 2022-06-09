package com.example.giffy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giffy.R;
import com.example.giffy.model.CategoryModel;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {

    private List<CategoryModel> categoryModelList;
    private CategoryListClickListener clickListener;

    public CategoryListAdapter(List<CategoryModel> categoryModelList, CategoryListClickListener clickListener) {
        this.categoryModelList = categoryModelList;
        this.clickListener = clickListener;
    }

    public void updateData(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.MyViewHolder holder, int position) {
        holder.categoryName.setText(categoryModelList.get(position).getName());
        holder.categoryAddress.setText("Address: "+categoryModelList.get(position).getAddress());
        holder.categoryHours.setText("Working hours: " + categoryModelList.get(position).getHours().getTodaysHours());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(categoryModelList.get(position));
            }
        });
        Glide.with(holder.thumbImage)
                .load(categoryModelList.get(position).getImage())
                .into(holder.thumbImage);

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        TextView  categoryAddress;
        TextView  categoryHours;
        ImageView thumbImage;

        public MyViewHolder( View itemView) {
            super(itemView);
            categoryName= itemView.findViewById(R.id.categoryName);
            categoryAddress= itemView.findViewById(R.id.categoryAddress);
            categoryHours= itemView.findViewById(R.id.categoryHours);
            thumbImage= itemView.findViewById(R.id.thumbImage);
        }
    }

    public interface CategoryListClickListener {
        public void onItemClick(CategoryModel categoryModelList);
    }


}
