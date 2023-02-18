package com.example.finalsubmission.main;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.fragment.app.Fragment;

import com.example.finalsubmission.R;
import com.example.finalsubmission.data.InventoryDatabaseManager;


public class ListFragment extends Fragment {

    public interface OnInventorySelectedListener {
        void onInventorySelected(int inventoryItemId);
    }

    private OnInventorySelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInventorySelectedListener) {
            mListener = (OnInventorySelectedListener) context;
        }
        else {
            throw new RuntimeException(context.toString() +
                    " must implement OnInventorySelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflator,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflator.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.inventory_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //
        InventoryAdapter adapter = new InventoryAdapter(InventoryDatabaseManager.getInstance(getContext()).getItems());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private class InventoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private InventoryItem mInventoryItem;
        
        private TextView mNameTextView;

        public InventoryHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_inventory, parent, false));
            itemView.setOnClickListener(this);
            mNameTextView = itemView.findViewById(R.id.inventoryItemDescription);
        }

        public void bind(InventoryItem inventoryItem) {
            mInventoryItem = inventoryItem;
            mNameTextView.setText(mInventoryItem.getDescription());
        }

        @Override
        public void onClick(View view) {
            mListener.onInventorySelected(mInventoryItem.getId());
        }
    }
    
    private class InventoryAdapter extends RecyclerView.Adapter<InventoryHolder> {
        
        private List<InventoryItem> mInventoryItems;
        
        public InventoryAdapter(List<InventoryItem> inventoryItems) {
            mInventoryItems = inventoryItems;
        }
        
        @Override
        public InventoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new InventoryHolder(layoutInflater, parent);
        }
        
        @Override
        public void onBindViewHolder(InventoryHolder holder, int position) {
            InventoryItem inventoryItem = mInventoryItems.get(position);
            holder.bind(inventoryItem);
        }
        
        @Override
        public int getItemCount() {
            return mInventoryItems.size();
        }
    }
}


