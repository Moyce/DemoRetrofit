package com.feicuiedu.demoretrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/7/5 0005.
 *
 // https://api.github.com/repos/square/okhttp/contributors
 // https://api.github.com/repos/square/retrofit/contributors
 // OKHTTP网络连接-----------------------------------------------
 // 构建好 请求
 //String owner = "square"; // 公司
 //        String repo = "retrofit";// 产品
 //        Request request = new Request.Builder()
 //        .url("https://api.github.com/repos/" + owner + "/" + repo + "/contributors")
 //        .build();
 */
public interface GithubApi {
    // https://api.github.com/

    @GET("repos/square/retrofit/contributors")
    Call<ResponseBody> getRetrofitContributors();

    @GET("repos/square/okhttp/contributors")
    Call<ResponseBody> getOkHttpContributors();

    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> getContributors(@Path("owner") String owner,@Path("repo") String repo);











}
