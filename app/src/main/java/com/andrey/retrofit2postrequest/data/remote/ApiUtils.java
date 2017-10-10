package com.andrey.retrofit2postrequest.data.remote;

/**
 * Created by sts on 10.10.17.
 */

public class ApiUtils {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com";
    //private static final String BASE_URL = "http://httpbin.org/";

    public ApiUtils(){}

    public static ApiService getApiService()
    {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
