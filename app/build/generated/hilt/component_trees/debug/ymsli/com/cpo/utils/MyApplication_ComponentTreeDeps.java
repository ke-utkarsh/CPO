package ymsli.com.cpo.utils;

import dagger.hilt.internal.aggregatedroot.codegen._ymsli_com_cpo_utils_MyApplication;
import dagger.hilt.internal.componenttreedeps.ComponentTreeDeps;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_ActivityComponent;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_ActivityRetainedComponent;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_FragmentComponent;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_ServiceComponent;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_ViewComponent;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_ViewModelComponent;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_ViewWithFragmentComponent;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ActivityComponentBuilder;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ActivityRetainedComponentBuilder;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_FragmentComponentBuilder;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ServiceComponentBuilder;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ViewComponentBuilder;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ViewModelComponentBuilder;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ViewWithFragmentComponentBuilder;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_components_SingletonComponent;
import hilt_aggregated_deps._dagger_hilt_android_internal_lifecycle_DefaultViewModelFactories_ActivityEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_lifecycle_DefaultViewModelFactories_FragmentEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_lifecycle_HiltViewModelFactory_ViewModelFactoriesEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_lifecycle_HiltWrapper_DefaultViewModelFactories_ActivityModule;
import hilt_aggregated_deps._dagger_hilt_android_internal_lifecycle_HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_lifecycle_HiltWrapper_HiltViewModelFactory_ViewModelModule;
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_ActivityComponentManager_ActivityComponentBuilderEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_FragmentComponentManager_FragmentComponentBuilderEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_ServiceComponentManager_ServiceComponentBuilderEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_ViewComponentManager_ViewComponentBuilderEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_ViewComponentManager_ViewWithFragmentComponentBuilderEntryPoint;
import hilt_aggregated_deps._dagger_hilt_android_internal_modules_ApplicationContextModule;
import hilt_aggregated_deps._dagger_hilt_android_internal_modules_HiltWrapper_ActivityModule;
import hilt_aggregated_deps._ymsli_com_cpo_di_ApiModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ActiveWarrantiesViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ActiveWarrantiesViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_AppraisalHistoryViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_AppraisalHistoryViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_BookAppointmentViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_BookAppointmentViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_DealerNearViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_DealerNearViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_GetNewUidViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_GetNewUidViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_LoginViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_LoginViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_MintNftSuccessViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_MintNftSuccessViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ModifyItemsViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ModifyItemsViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_NftDetailViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_NftDetailViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_OfferListViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_OfferListViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_PostImageViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_PostImageViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ProfileViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ProfileViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_QrScanViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_QrScanViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ServiceHistoryViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ServiceHistoryViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ServiceItemsViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ServiceItemsViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ServiceSubmitViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_ServiceSubmitViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_VehicleInformationViewModel_HiltModules_BindsModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_viewModel_VehicleInformationViewModel_HiltModules_KeyModule;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_AppraisalDetailActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_AppraisalHistoryActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_BookAppointmentActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_CustomerNftDetailActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_DealerNearActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_LoginActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_MintNftSuccessActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_ModifyItemsActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_OfferListActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_ProfileActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_QrScanActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_ServiceHistoryActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_ServiceHistoryDetailActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_ServiceItemsActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_ServiceSubmitActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_VehicleInformationCustomer_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_VehiclesInformationDealer_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_WarrantiesActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_ui_view_activity_WebViewActivity_GeneratedInjector;
import hilt_aggregated_deps._ymsli_com_cpo_utils_MyApplication_GeneratedInjector;

@ComponentTreeDeps(
    rootDeps = _ymsli_com_cpo_utils_MyApplication.class,
    defineComponentDeps = {
        _dagger_hilt_android_components_ActivityComponent.class,
        _dagger_hilt_android_components_ActivityRetainedComponent.class,
        _dagger_hilt_android_components_FragmentComponent.class,
        _dagger_hilt_android_components_ServiceComponent.class,
        _dagger_hilt_android_components_ViewComponent.class,
        _dagger_hilt_android_components_ViewModelComponent.class,
        _dagger_hilt_android_components_ViewWithFragmentComponent.class,
        _dagger_hilt_android_internal_builders_ActivityComponentBuilder.class,
        _dagger_hilt_android_internal_builders_ActivityRetainedComponentBuilder.class,
        _dagger_hilt_android_internal_builders_FragmentComponentBuilder.class,
        _dagger_hilt_android_internal_builders_ServiceComponentBuilder.class,
        _dagger_hilt_android_internal_builders_ViewComponentBuilder.class,
        _dagger_hilt_android_internal_builders_ViewModelComponentBuilder.class,
        _dagger_hilt_android_internal_builders_ViewWithFragmentComponentBuilder.class,
        _dagger_hilt_components_SingletonComponent.class
    },
    aggregatedDeps = {
        _dagger_hilt_android_internal_lifecycle_DefaultViewModelFactories_ActivityEntryPoint.class,
        _dagger_hilt_android_internal_lifecycle_DefaultViewModelFactories_FragmentEntryPoint.class,
        _dagger_hilt_android_internal_lifecycle_HiltViewModelFactory_ViewModelFactoriesEntryPoint.class,
        _dagger_hilt_android_internal_lifecycle_HiltWrapper_DefaultViewModelFactories_ActivityModule.class,
        _dagger_hilt_android_internal_lifecycle_HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint.class,
        _dagger_hilt_android_internal_lifecycle_HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
        _dagger_hilt_android_internal_managers_ActivityComponentManager_ActivityComponentBuilderEntryPoint.class,
        _dagger_hilt_android_internal_managers_FragmentComponentManager_FragmentComponentBuilderEntryPoint.class,
        _dagger_hilt_android_internal_managers_HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint.class,
        _dagger_hilt_android_internal_managers_HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint.class,
        _dagger_hilt_android_internal_managers_HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
        _dagger_hilt_android_internal_managers_ServiceComponentManager_ServiceComponentBuilderEntryPoint.class,
        _dagger_hilt_android_internal_managers_ViewComponentManager_ViewComponentBuilderEntryPoint.class,
        _dagger_hilt_android_internal_managers_ViewComponentManager_ViewWithFragmentComponentBuilderEntryPoint.class,
        _dagger_hilt_android_internal_modules_ApplicationContextModule.class,
        _dagger_hilt_android_internal_modules_HiltWrapper_ActivityModule.class,
        _ymsli_com_cpo_di_ApiModule.class,
        _ymsli_com_cpo_ui_viewModel_ActiveWarrantiesViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_ActiveWarrantiesViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_AppraisalHistoryViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_AppraisalHistoryViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_BookAppointmentViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_BookAppointmentViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_DealerNearViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_DealerNearViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_GetNewUidViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_GetNewUidViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_LoginViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_LoginViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_MintNftSuccessViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_MintNftSuccessViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_ModifyItemsViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_ModifyItemsViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_NftDetailViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_NftDetailViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_OfferListViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_OfferListViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_PostImageViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_PostImageViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_ProfileViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_ProfileViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_QrScanViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_QrScanViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_ServiceHistoryViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_ServiceHistoryViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_ServiceItemsViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_ServiceItemsViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_ServiceSubmitViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_ServiceSubmitViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_viewModel_VehicleInformationViewModel_HiltModules_BindsModule.class,
        _ymsli_com_cpo_ui_viewModel_VehicleInformationViewModel_HiltModules_KeyModule.class,
        _ymsli_com_cpo_ui_view_activity_AppraisalDetailActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_AppraisalHistoryActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_BookAppointmentActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_CustomerNftDetailActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_DealerNearActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_LoginActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_MintNftSuccessActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_ModifyItemsActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_OfferListActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_ProfileActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_QrScanActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_ServiceHistoryActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_ServiceHistoryDetailActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_ServiceItemsActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_ServiceSubmitActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_VehicleInformationCustomer_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_VehiclesInformationDealer_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_WarrantiesActivity_GeneratedInjector.class,
        _ymsli_com_cpo_ui_view_activity_WebViewActivity_GeneratedInjector.class,
        _ymsli_com_cpo_utils_MyApplication_GeneratedInjector.class
    }
)
public final class MyApplication_ComponentTreeDeps {
}
