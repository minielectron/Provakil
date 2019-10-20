package com.androidcodeshop.provakil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidcodeshop.provakil.R;
import com.androidcodeshop.provakil.datamodels.ClientDetailsModel;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ClientDetailsModel> clientDetailsList;

    public ClientListAdapter(Context mContext, ArrayList<ClientDetailsModel> clientDetailsList) {
        this.mContext = mContext;
        this.clientDetailsList = clientDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.client_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTv.setText(clientDetailsList.get(position).getName());
        holder.clientCodeTv.setText(String.format(Locale.ENGLISH, "Client Code : %s", clientDetailsList.get(position).getmClientCode()));
        holder.phoneNumberTv.setText(String.format(Locale.ENGLISH, "Mobile No : %s", clientDetailsList.get(position).getmContactNumber()));
    }

    @Override
    public int getItemCount() {
        return clientDetailsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.client_code_tv)
        TextView clientCodeTv;
        @BindView(R.id.phone_number_tv)
        TextView phoneNumberTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
