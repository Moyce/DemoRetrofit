package com.feicuiedu.demoretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// 1. 我们要使用Retrofit
// 依赖
// 2. 我们想访问一个web接口(RESTE)
// 根据文档,创建一个interface
// 3. 创建出Retrofit
// 4. create我们的Interface


public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private GithubApi githubApi;
    private Call<List<Contributor>> call;
    private ArrayAdapter<Contributor> adapter;

    @Bind(R.id.listView) ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //
        adapter = new ArrayAdapter<Contributor>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        //
        initRetrofit();
        // 创建API接口
        githubApi = retrofit.create(GithubApi.class);
        // 调用接口(构建请求得到Call)
        call = githubApi.getContributors("square", "retrofit");
        // 异步请求队列
        call.enqueue(callBack);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                // 转换器(GSON) 依赖包
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private Callback<List<Contributor>> callBack = new Callback<List<Contributor>>() {

        @Override public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
            List<Contributor> contributorList = response.body();
            adapter.addAll(contributorList);
            adapter.notifyDataSetChanged();
        }
        @Override public void onFailure(Call<List<Contributor>> call, Throwable t) {
            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

}
