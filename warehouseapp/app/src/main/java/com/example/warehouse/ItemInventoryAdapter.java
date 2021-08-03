package com.example.warehouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemInventoryAdapter extends RecyclerView.Adapter<ItemInventoryAdapter.ViewHolder> {

    Context context;
    List<ItemInventoryAppDto> list;

    public ItemInventoryAdapter (Context context,List<ItemInventoryAppDto> list) {
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
            ItemInventoryAppDto dto = list.get(position);
            holder.txtNote.setText(dto.getNote());
            holder.txtIn.setText(dto.getInQty());
            holder.txtOut.setText(dto.getOutQty());
            holder.txtStock.setText(dto.getStock());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNote,txtIn,txtOut,txtStock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNote = itemView.findViewById(R.id.txtNote);
            txtIn = itemView.findViewById(R.id.txtIn);
            txtOut = itemView.findViewById(R.id.txtOut);
            txtStock = itemView.findViewById(R.id.txtStock);
        }
    }
}
