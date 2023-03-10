// Generated by Dagger (https://dagger.dev).
package ymsli.com.cpo.di;

import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiModule_ProvideHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<SharedPreferences> sfProvider;

  public ApiModule_ProvideHttpClientFactory(Provider<SharedPreferences> sfProvider) {
    this.sfProvider = sfProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideHttpClient(sfProvider.get());
  }

  public static ApiModule_ProvideHttpClientFactory create(Provider<SharedPreferences> sfProvider) {
    return new ApiModule_ProvideHttpClientFactory(sfProvider);
  }

  public static OkHttpClient provideHttpClient(SharedPreferences sf) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideHttpClient(sf));
  }
}
