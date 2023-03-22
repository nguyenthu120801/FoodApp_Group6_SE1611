package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Entity.Category;
import com.example.foodapp.R;
import com.example.foodapp.onProductItemClick;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final Context context;
    List<Category> catList;
    private onProductItemClick onProductItemClick;

    public CategoryAdapter(Context context, List<Category> catList, onProductItemClick onProductItemClick) {
        this.context = context;
        this.catList = catList;
        this.onProductItemClick = onProductItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(catList.get(position).getName());
        Glide.with(holder.itemView.getContext()).load(catList.get(position).getImage()).into(holder.categoryimg);
        holder.tv_cate.setText(String.valueOf(catList.get(position).getID()));
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName, tv_cate;
        ImageView categoryimg;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryimg = itemView.findViewById(R.id.categoryImg);
            layout = itemView.findViewById(R.id.cat_layout);
            tv_cate = itemView.findViewById(R.id.tv_cate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProductItemClick.onCategoryItemClick(Integer.parseInt(tv_cate.getText().toString()));
                }
            });
        }
    }
}
