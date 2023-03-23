package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.Product;
import com.example.foodapp.R;
import com.example.foodapp.onProductItemClick;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {
    private final Context context;
    List<Product> productList;
    List<Product> productListOld;
    private final onProductItemClick onProductItemClick;
    public SearchAdapter(Context context, List<Product> productList, com.example.foodapp.onProductItemClick onProductItemClick) {
        this.context = context;
        this.productList = productList;
        this.productListOld = new ArrayList<>(productList);
        this.onProductItemClick = onProductItemClick;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_search, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.tv_id.setText(String.valueOf(productList.get(position).getProductID()));
        holder.itemsearch.setText(productList.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search = charSequence.toString().trim();
                List<Product> filteredList = new ArrayList<>();
                if (search.isEmpty()) {
                    filteredList.addAll(productListOld);
                } else {
                    for (Product product : productListOld) {
                        if (product.getProductName().toLowerCase().contains(search.toLowerCase())) {
                            filteredList.add(product);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                productList.clear();
                productList.addAll((List<Product>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemsearch;
        TextView tv_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemsearch = itemView.findViewById(R.id.itemsearch);
            tv_id = itemView.findViewById(R.id.tv_id_search);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProductItemClick.onProductClick(Integer.parseInt(tv_id.getText().toString()), "FoodDetail");
                }
            });
        }
    }
}
