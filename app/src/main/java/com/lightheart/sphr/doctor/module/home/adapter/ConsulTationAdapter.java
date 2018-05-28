package com.lightheart.sphr.doctor.module.home.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.blankj.utilcode.util.TimeUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.ConsultingListBean;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

public class ConsulTationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<ConsultingListBean> consulTationlist;
    private OnClicklistener onClicklistener;

    @Inject
    public ConsulTationAdapter(Context context, List<ConsultingListBean> consulTationlist) {
        this.context = context;
        this.consulTationlist = consulTationlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflater = LayoutInflater.from(context).inflate(R.layout.pending_item,parent,false);
        return new ConsulTation(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        ConsulTation  consulTation= (ConsulTation) holder;
        String time = TimeUtils.millis2String(consulTationlist.get(position).getConsultDate(), new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        consulTation.tvTime.setText(time);
        consulTation.tvName.setText(consulTationlist.get(position).getName());
        consulTation.tvCondition.setText(consulTationlist.get(position).getContent());
        consulTation.tvLookOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicklistener.Oclick(holder.itemView,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return consulTationlist!=null?consulTationlist.size():0;
    }
    public static class ConsulTation extends RecyclerView.ViewHolder{
        TextView tvTime;
        TextView tvName;
        TextView tvCondition;
        TextView tvLookOver;

        ConsulTation(View itemView) {

            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvName = itemView.findViewById(R.id.tvName);
            tvCondition = itemView.findViewById(R.id.tvCondition);
            tvLookOver = itemView.findViewById(R.id.tvLookOver);
        }
    }
    public void listener(OnClicklistener onClicklistener){
        this.onClicklistener=onClicklistener;

    }
    public interface OnClicklistener{
        void Oclick(View view,int position);
    }
}
