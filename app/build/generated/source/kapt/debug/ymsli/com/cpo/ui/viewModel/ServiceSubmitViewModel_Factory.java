// Generated by Dagger (https://dagger.dev).
package ymsli.com.cpo.ui.viewModel;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;
import ymsli.com.cpo.data.repository.AppRepository;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceSubmitViewModel_Factory implements Factory<ServiceSubmitViewModel> {
  private final Provider<Application> appProvider;

  private final Provider<AppRepository> appRepositoryProvider;

  public ServiceSubmitViewModel_Factory(Provider<Application> appProvider,
      Provider<AppRepository> appRepositoryProvider) {
    this.appProvider = appProvider;
    this.appRepositoryProvider = appRepositoryProvider;
  }

  @Override
  public ServiceSubmitViewModel get() {
    return newInstance(appProvider.get(), appRepositoryProvider.get());
  }

  public static ServiceSubmitViewModel_Factory create(Provider<Application> appProvider,
      Provider<AppRepository> appRepositoryProvider) {
    return new ServiceSubmitViewModel_Factory(appProvider, appRepositoryProvider);
  }

  public static ServiceSubmitViewModel newInstance(Application app, AppRepository appRepository) {
    return new ServiceSubmitViewModel(app, appRepository);
  }
}
