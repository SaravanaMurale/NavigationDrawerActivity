package com.pojo.navigationdrawer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pojo.navigationdrawer.R;
import com.pojo.navigationdrawer.model.CalledStatusResponse;
import com.pojo.navigationdrawer.utils.AppConstant;
import com.pojo.navigationdrawer.utils.SessionHandler;
import com.pojo.navigationdrawer.utils.SqliteManager;

import java.util.List;


public class ViewAllNumberAdapter extends RecyclerView.Adapter<ViewAllNumberAdapter.ViewAllViewHolder> {

    Context context;
    OnNumberClickable onNumberClickable;
    List<CalledStatusResponse> calledStatusResponseList;

    public ViewAllNumberAdapter(Context context, List<CalledStatusResponse> calledStatusResponseList, OnNumberClickable onNumberClickable) {

        this.context=context;
        this.onNumberClickable=onNumberClickable;
        this.calledStatusResponseList=calledStatusResponseList;

    }


    public interface OnNumberClickable{
        public void onNumberClick(CalledStatusResponse calledStatusResponse);
    }


    @NonNull
    @Override
    public ViewAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_viewall_adapter, parent, false);
        return new ViewAllViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllViewHolder holder, int position) {


        if(calledStatusResponseList.get(holder.getAdapterPosition()).isCalledStatus().equalsIgnoreCase("false")){
            holder.callStatusIcon.setVisibility(View.VISIBLE);
        }else {

            holder.callStatusIcon.setVisibility(View.GONE);

        }


        holder.name.setText(calledStatusResponseList.get(holder.getAdapterPosition()).getName());
        holder.number.setText(calledStatusResponseList.get(holder.getAdapterPosition()).getMobileNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClickable.onNumberClick(calledStatusResponseList.get(holder.getAdapterPosition()));
            }
        });

        holder.whatsAppDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String text = "Dear Customer, Do you want to promote your business through Digital Marketing or Website or Mobile. Please call us. www.pojotechnology.com";

                    String toNumber = calledStatusResponseList.get(holder.getAdapterPosition()).getMobileNumber();
                    if (!toNumber.equals("") && toNumber != null) {

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                        context.startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        holder.callIconDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SessionHandler.setValueSInt(context, AppConstant.REFRESH_STATUS,1);
                new SqliteManager(context).updateCalledStatus(calledStatusResponseList.get(holder.getAdapterPosition()).getId());

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + calledStatusResponseList.get(holder.getAdapterPosition()).getMobileNumber()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return calledStatusResponseList.size();
    }

    public class ViewAllViewHolder extends RecyclerView.ViewHolder{

        TextView name,number;
        LinearLayout headerBlock;
        ImageView callStatusIcon,whatsAppDetails,callIconDetail;

        public ViewAllViewHolder(@NonNull View itemView) {
            super(itemView);

            name=(TextView) itemView.findViewById(R.id.userNameDetail);
            number=(TextView) itemView.findViewById(R.id.customerMobileNumberDetails);

            headerBlock=(LinearLayout) itemView.findViewById(R.id.headerBlock);

            callStatusIcon=(ImageView) itemView.findViewById(R.id.callStatusIcon);
            whatsAppDetails=(ImageView) itemView.findViewById(R.id.whatsAppDetails);
            callIconDetail=(ImageView) itemView.findViewById(R.id.callIconDetail);

        }
    }

}
