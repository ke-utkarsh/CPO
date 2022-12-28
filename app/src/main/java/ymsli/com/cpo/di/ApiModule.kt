package ymsli.com.cpo.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ymsli.com.cpo.data.api.API
import ymsli.com.cpo.data.sharedpref.SharedPref
import ymsli.com.cpo.utils.SharedPreferenceConstant
import ymsli.com.cpo.utils.SharedPreferenceConstant.TOKEN

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Provides
    fun provideLaunchListApi(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://ec2-3-6-137-143.ap-south-1.compute.amazonaws.com:8081/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHttpClient(sf: SharedPreferences): OkHttpClient {

        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val request: Request = original.newBuilder()
                    .header("Authorization", "Bearer "+SharedPref.getString(SharedPreferenceConstant.TOKEN, "").toString())
                    .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideSharedPreference(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences(
            SharedPreferenceConstant.PREFERENCE_NAME,
            MODE_PRIVATE
        )
    }
}