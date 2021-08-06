package com.example.warehouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {

    Context context;
    List<InventoryAppDto> list;

    public InventoryAdapter(Context context, List<InventoryAppDto> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(list != null && list.size() > 0) {
            InventoryAppDto dto = list.get(position);
            holder.txtNote.setText(dto.getNote());
            holder.txtDate.setText("Date : " + dto.getDate());
            holder.txtStock.setText("Stock : " + dto.getStock());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNote,txtDate,txtStock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNote = itemView.findViewById(R.id.txtNote);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtStock = itemView.findViewById(R.id.txtStock);
        }
    }
}
