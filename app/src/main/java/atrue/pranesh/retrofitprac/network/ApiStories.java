package atrue.pranesh.retrofitprac.network;

import java.util.List;

import atrue.pranesh.retrofitprac.model.Users;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Adminitrator on 4/24/2018.
 * Copyright IMDSTAR Technologies
 */

public interface ApiStories {
    @GET("users")
    Call<List<Users>> getUser();
    //https://api.openweathermap.org/data/2.5/forecast?&lang=en&mode=json&q=Mumbai&apiKey=b6f56c8a16812c7302236c6c7d5ae84b
    @GET("{version}/{forecast}?lang=en&mode=json&q=Mumbai")
    Call<ResponseBody> getWeather(@Path("version") double version,@Path("forecast") String forecast,@Query("apiKey") String apiKey);

}
