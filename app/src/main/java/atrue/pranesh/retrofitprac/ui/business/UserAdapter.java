package atrue.pranesh.retrofitprac.ui.business;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import atrue.pranesh.retrofitprac.R;
import atrue.pranesh.retrofitprac.model.Users;


public class UserAdapter extends RecyclerView.Adapter<UsersHolder> {
    UsersHolder usersHolder;
    List<Users> users;
    View.OnClickListener listener;

    public UserAdapter(List<Users> users,View.OnClickListener listener){
        this.users=users;
        this.listener=listener;
    }
    @Override
    public UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adap, parent, false);
        usersHolder = new UsersHolder(view,listener);
        return usersHolder;
    }

    @Override
    public void onBindViewHolder(UsersHolder holder, int position) {
        usersHolder.onBind(users.get(position),position);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
