package xyz.gabrielrohez.apiecobici.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.gabrielrohez.apiecobici.utils.AppConstants;

public class RetrofitClient {

    private static RetrofitClient retrofitClient;
    public Retrofit retrofit_token;
    public Retrofit retrofit;

    private RetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
                .create();

        //loggin (just for testing)
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //http client
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor) //testing REMOVE/COMMENT BEFORE GENERATE APK
                .build();

        retrofit_token = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL_TOKEN)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

    }

    public static RetrofitClient getInstance() {
        if(retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }
}
