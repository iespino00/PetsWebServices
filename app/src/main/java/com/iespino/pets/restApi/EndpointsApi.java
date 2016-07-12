package com.iespino.pets.restApi;


import com.iespino.pets.restApi.model.PetsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface EndpointsApi
{
    @GET(ConstantesRestApi.URL_GET_FOLOWS_MEDIA_RECENT)
    Call<PetsResponse> getRecentMedia(@Path("user-id") String id);

    @GET(ConstantesRestApi.URL_GET_PROFILE_USER_WITH_ID)
    Call<PetsResponse> getProfile(@Path("user-id") String id);

    @GET(ConstantesRestApi.URL_GET_FOLLOWS)
    Call<PetsResponse> getFollows();

   @GET(ConstantesRestApi.URL_GET_FOLOWS_MEDIA_RECENT)
    Call<PetsResponse> getFollowsMediaRecent(@Path("user-id") String id);

  /*  @GET(ConstantesRestApi.URL_GET_FOLOWS_MEDIA_RECENT)
    Call<PetsResponse> getFollowsMediaRecent();*/
}
