package atrue.pranesh.retrofitprac.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import atrue.pranesh.retrofitprac.R;
import atrue.pranesh.retrofitprac.model.Users;
import atrue.pranesh.retrofitprac.model.Weather;
import atrue.pranesh.retrofitprac.network.ApiClient;
import atrue.pranesh.retrofitprac.network.ApiStories;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherFragment extends Fragment {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.weather_frag,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=view.findViewById(R.id.textWe);

        Bundle bundle=getArguments();
        Users users= (Users) bundle.getSerializable("user");
        if(users!=null){
            fetchData();
        }
    }

    private void fetchData() {
        ApiClient.BASE_URL="https://api.openweathermap.org/data/";
        ApiStories apiStories= ApiClient.getClient().create(ApiStories.class);
        Call<ResponseBody> responseBodyCall=apiStories.getWeather(2.5,"forecast","b6f56c8a16812c7302236c6c7d5ae84b");
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                StringBuffer stringBuffer=new StringBuffer();
                if(response.body()!=null){
                    InputStream inputStream=response.body().byteStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    String str;
                    try {
                        while((str=bufferedReader.readLine())!=null){
                            stringBuffer.append(str);
                            stringBuffer.append("\r\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String jsonResponse;
                    try {
                        JSONObject jsonObject=new JSONObject(stringBuffer.toString());
                        if(jsonObject.has("cod")){
                            if(jsonObject.get("cod").equals("200")){
                                if(jsonObject.has("list")){
                                    jsonResponse=jsonObject.getJSONArray("list").toString();
                                    Gson gson=new Gson();
                                    List<Weather.List>lists= Arrays.asList(gson.fromJson(jsonResponse, Weather.List[].class));

                                    List<Weather> stacks=gson.fromJson(jsonResponse,new TypeToken<List<Weather>>(){}.getType());

                                    showText(lists);
                                    showToast(stacks.size());
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private void showToast(int size) {
        Toast.makeText(getActivity(),size +"",Toast.LENGTH_SHORT).show();
    }

    private void showText(List<Weather.List> lists) {
        if(textView!=null){
            textView.setText(lists.size()+"");
        }
    }
}
