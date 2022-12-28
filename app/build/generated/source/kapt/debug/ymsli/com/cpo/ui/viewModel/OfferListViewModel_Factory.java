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
public final class OfferListViewModel_Factory implements Factory<OfferListViewModel> {
  private final Provider<Application> appProvider;

  private final Provider<AppRepository> appRepositoryProvider;

  public OfferListViewModel_Factory(Provider<Application> appProvider,
      Provider<AppRepository> appRepositoryProvider) {
    this.appProvider = appProvider;
    this.appRepositoryProvider = appRepositoryProvider;
  }

  @Override
  public OfferListViewModel get() {
    return newInstance(appProvider.get(), appRepositoryProvider.get());
  }

  public static OfferListViewModel_Factory create(Provider<Application> appProvider,
      Provider<AppRepository> appRepositoryProvider) {
    return new OfferListViewModel_Factory(appProvider, appRepositoryProvider);
  }

  public static OfferListViewModel newInstance(Application app, AppRepository appRepository) {
    return new OfferListViewModel(app, appRepository);
  }
}
