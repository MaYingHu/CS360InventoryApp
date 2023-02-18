package com.example.finalsubmission.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.finalsubmission.R;
import com.example.finalsubmission.data.InventoryDatabaseManager;

public class DetailsFragment extends Fragment {

    private InventoryItem mInventoryItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInventoryItem = InventoryDatabaseManager.getInstance(getContext()).getInventoryItem(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView nameTextView = (TextView) view.findViewById(R.id.inventoryItemDescription);
        nameTextView.setText(mInventoryItem.getDescription());

        TextView descriptionTextView = (TextView) view.findViewById(R.id.inventoryItemQuantity);
        descriptionTextView.setText(mInventoryItem.getQuantity());

        return view;
    }
}
