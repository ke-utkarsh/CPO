package ymsli.com.cpo.utils;

import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.inject.Singleton;
import ymsli.com.cpo.di.ApiModule;
import ymsli.com.cpo.ui.view.activity.AppraisalDetailActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.AppraisalHistoryActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.BookAppointmentActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.CustomerNftDetailActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.DealerNearActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.LoginActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.MintNftSuccessActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.ModifyItemsActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.OfferListActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.ProfileActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.QrScanActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.ServiceHistoryActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.ServiceHistoryDetailActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.ServiceItemsActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.ServiceSubmitActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.VehicleInformationCustomer_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.VehiclesInformationDealer_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.WarrantiesActivity_GeneratedInjector;
import ymsli.com.cpo.ui.view.activity.WebViewActivity_GeneratedInjector;
import ymsli.com.cpo.ui.viewModel.ActiveWarrantiesViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.AppraisalHistoryViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.BookAppointmentViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.DealerNearViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.GetNewUidViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.LoginViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.MintNftSuccessViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.ModifyItemsViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.NftDetailViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.OfferListViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.PostImageViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.ProfileViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.QrScanViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.ServiceHistoryViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.ServiceItemsViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.ServiceSubmitViewModel_HiltModules;
import ymsli.com.cpo.ui.viewModel.VehicleInformationViewModel_HiltModules;

public final class MyApplication_HiltComponents {
  private MyApplication_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
          ApiModule.class,
          ApplicationContextModule.class,
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class
      }
  )
  @Singleton
  public abstract static class SingletonC implements HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      GeneratedComponent,
      MyApplication_GeneratedInjector {
  }

  @Subcomponent
  @ServiceScoped
  public abstract static class ServiceC implements ServiceComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          ActiveWarrantiesViewModel_HiltModules.KeyModule.class,
          AppraisalHistoryViewModel_HiltModules.KeyModule.class,
          BookAppointmentViewModel_HiltModules.KeyModule.class,
          DealerNearViewModel_HiltModules.KeyModule.class,
          GetNewUidViewModel_HiltModules.KeyModule.class,
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          LoginViewModel_HiltModules.KeyModule.class,
          MintNftSuccessViewModel_HiltModules.KeyModule.class,
          ModifyItemsViewModel_HiltModules.KeyModule.class,
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class,
          NftDetailViewModel_HiltModules.KeyModule.class,
          OfferListViewModel_HiltModules.KeyModule.class,
          PostImageViewModel_HiltModules.KeyModule.class,
          ProfileViewModel_HiltModules.KeyModule.class,
          QrScanViewModel_HiltModules.KeyModule.class,
          ServiceHistoryViewModel_HiltModules.KeyModule.class,
          ServiceItemsViewModel_HiltModules.KeyModule.class,
          ServiceSubmitViewModel_HiltModules.KeyModule.class,
          VehicleInformationViewModel_HiltModules.KeyModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class,
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent,
      AppraisalDetailActivity_GeneratedInjector,
      AppraisalHistoryActivity_GeneratedInjector,
      BookAppointmentActivity_GeneratedInjector,
      CustomerNftDetailActivity_GeneratedInjector,
      DealerNearActivity_GeneratedInjector,
      LoginActivity_GeneratedInjector,
      MintNftSuccessActivity_GeneratedInjector,
      ModifyItemsActivity_GeneratedInjector,
      OfferListActivity_GeneratedInjector,
      ProfileActivity_GeneratedInjector,
      QrScanActivity_GeneratedInjector,
      ServiceHistoryActivity_GeneratedInjector,
      ServiceHistoryDetailActivity_GeneratedInjector,
      ServiceItemsActivity_GeneratedInjector,
      ServiceSubmitActivity_GeneratedInjector,
      VehicleInformationCustomer_GeneratedInjector,
      VehiclesInformationDealer_GeneratedInjector,
      WarrantiesActivity_GeneratedInjector,
      WebViewActivity_GeneratedInjector {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          ActiveWarrantiesViewModel_HiltModules.BindsModule.class,
          AppraisalHistoryViewModel_HiltModules.BindsModule.class,
          BookAppointmentViewModel_HiltModules.BindsModule.class,
          DealerNearViewModel_HiltModules.BindsModule.class,
          GetNewUidViewModel_HiltModules.BindsModule.class,
          HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
          LoginViewModel_HiltModules.BindsModule.class,
          MintNftSuccessViewModel_HiltModules.BindsModule.class,
          ModifyItemsViewModel_HiltModules.BindsModule.class,
          NftDetailViewModel_HiltModules.BindsModule.class,
          OfferListViewModel_HiltModules.BindsModule.class,
          PostImageViewModel_HiltModules.BindsModule.class,
          ProfileViewModel_HiltModules.BindsModule.class,
          QrScanViewModel_HiltModules.BindsModule.class,
          ServiceHistoryViewModel_HiltModules.BindsModule.class,
          ServiceItemsViewModel_HiltModules.BindsModule.class,
          ServiceSubmitViewModel_HiltModules.BindsModule.class,
          VehicleInformationViewModel_HiltModules.BindsModule.class
      }
  )
  @ViewModelScoped
  public abstract static class ViewModelC implements ViewModelComponent,
      HiltViewModelFactory.ViewModelFactoriesEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewModelComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = ViewWithFragmentCBuilderModule.class
  )
  @FragmentScoped
  public abstract static class FragmentC implements FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
