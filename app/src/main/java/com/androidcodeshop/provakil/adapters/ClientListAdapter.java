package com.androidcodeshop.provakil.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.androidcodeshop.provakil.R;
import com.androidcodeshop.provakil.activities.ClientFormActivity;
import com.androidcodeshop.provakil.data.ClientDataList;
import com.androidcodeshop.provakil.datamodels.ClientDetailsModel;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.androidcodeshop.provakil.activities.ClientFormActivity.VIEW_MODE;


public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ClientDetailsModel> clientDetailsList;
    private Intent intent = null;

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

        holder.menuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, holder.menuTv);
                popup.inflate(R.menu.options_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_view:
                                Toast.makeText(mContext, "View Clicked", Toast.LENGTH_SHORT).show();
                                intent = new Intent(mContext, ClientFormActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra(VIEW_MODE, "true");
                                intent.putExtra("pos",position);
                                mContext.startActivity(intent);
                                return true;
                            case R.id.action_edit:
                                Toast.makeText(mContext, "Edit Clicked", Toast.LENGTH_SHORT).show();
                                intent = new Intent(mContext, ClientFormActivity.class);
                                intent.putExtra(VIEW_MODE, "false");
                                mContext.startActivity(intent);
                                return true;
                            case R.id.action_delete:
                                Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                ClientDataList.getStoredData().remove(position);
                                notifyDataSetChanged();
                                return true;
                            default:
                                intent = null;
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });
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
        @BindView(R.id.optionsMenuTv)
        TextView menuTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
