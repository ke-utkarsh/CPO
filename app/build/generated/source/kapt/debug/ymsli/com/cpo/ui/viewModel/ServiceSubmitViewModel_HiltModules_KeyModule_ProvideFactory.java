// Generated by Dagger (https://dagger.dev).
package ymsli.com.cpo.ui.viewModel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceSubmitViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static ServiceSubmitViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(ServiceSubmitViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final ServiceSubmitViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new ServiceSubmitViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}
