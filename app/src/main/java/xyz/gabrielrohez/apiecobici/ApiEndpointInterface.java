package xyz.gabrielrohez.apiecobici;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpointInterface {

    @GET("/token")
    AccessTokenResponse obtainAccessToken(@Query("client_id") String client_id, @Query("client_secret") String client_secret, @Query("grant_type") String grant_type);

    @GET("/token")
    AccessTokenResponse updateAccessToken(@Query("client_id") String client_id, @Query("client_secret") String client_secret, @Query("grant_type") String grant_type, @Query("refresh_token") String refresh_token);

    @GET("/stations.json")
    InfoStationResponse getInfoStation(@Query("access_token") String access_token);

    @GET("/stations/status.json")
    AvailabilityStationsResponse getAvailabilityStations(@Query("access_token") String access_token);
}
