package cn.edu.hpu.yuan.yuannews.main.data;

import android.content.Context;

import cn.edu.hpu.yuan.yuannews.main.data.Local.NewsAPIShared;
import cn.edu.hpu.yuan.yuannews.main.data.remote.NewsAPIService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuan on 16-5-9.
 * 新闻API Module， 只能在Application中进行初始化操作
 * 1.数据访问类 Retrofit
 * 2.图片访问Glide
 */
@Module
public class NewsAPIModule {

    protected String baseUrl;

    protected  Retrofit retrofit;

    protected Context context;

    public NewsAPIModule(String baseUrl,Context context) {
        this.baseUrl = baseUrl;
        this.context=context;
    }


    @Provides
    Retrofit provideRetrofit(){
         retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    NewsAPIService proviceNewsAPIService(){
        provideRetrofit();
        return retrofit.create(NewsAPIService.class);
    }

    @Provides
    NewsAPIShared provideNewsAPIShared(){
        return new NewsAPIShared(context);
    }


}
