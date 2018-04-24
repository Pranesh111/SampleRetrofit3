package atrue.pranesh.retrofitprac.ui.business;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import atrue.pranesh.retrofitprac.R;
import atrue.pranesh.retrofitprac.model.Users;

/**
 * Created by Adminitrator on 4/24/2018.
 * Copyright IMDSTAR Technologies
 */

public class UsersHolder extends RecyclerView.ViewHolder {
    TextView textView;
    CardView cardView;
    View.OnClickListener listener;
    public UsersHolder(View itemView,View.OnClickListener listener) {
        super(itemView);
        textView=itemView.findViewById(R.id.text);
        cardView=itemView.findViewById(R.id.cardView);
        this.listener=listener;
    }

    public void onBind(Users users,int position) {
        textView.setText(users.name);
        cardView.setOnClickListener(listener);
        cardView.setTag(R.id.cardView,position);
        cardView.setTag(users);
    }
}
