package atrue.pranesh.retrofitprac.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import atrue.pranesh.retrofitprac.R;
import atrue.pranesh.retrofitprac.model.Users;
import atrue.pranesh.retrofitprac.network.ApiClient;
import atrue.pranesh.retrofitprac.network.ApiStories;
import atrue.pranesh.retrofitprac.ui.business.UserAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeUserFragment extends Fragment implements View.OnClickListener{
RecyclerView recyclerView;
List<Users> usersLst=new ArrayList<>();
    UserAdapter userAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_frgament,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.userRecy);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        if(usersLst.size()>0){
            setAdapter(usersLst);
        }
        else{
            fetchUsersList();
        }

    }

    private void fetchUsersList() {
        ApiClient.BASE_URL="https://jsonplaceholder.typicode.com/";
        ApiStories apiStories= ApiClient.getClient().create(ApiStories.class);
        Call<List<Users>> listCall=apiStories.getUser();
        listCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(getActivity(),"Failde",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(List<Users> body) {
        userAdapter=new UserAdapter(body,this);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.cardView){
         int pos= (int) view.getTag(R.id.cardView);
         Users users= (Users) view.getTag();

         callWeatherFragment(users);
        }
    }

    private void callWeatherFragment(Users users) {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        WeatherFragment weatherFragment=new WeatherFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("user",users);
        weatherFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.base_container,weatherFragment,"Wea").addToBackStack("Wea").commit();
    }
}
