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
public final class BookAppointmentViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static BookAppointmentViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(BookAppointmentViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final BookAppointmentViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new BookAppointmentViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}