package ymsli.com.cpo;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ymsli.com.cpo.databinding.ActivityAppraisalDetailBindingImpl;
import ymsli.com.cpo.databinding.ActivityAppraisalHistoryBindingImpl;
import ymsli.com.cpo.databinding.ActivityBookAppointmentBindingImpl;
import ymsli.com.cpo.databinding.ActivityCameraClickBindingImpl;
import ymsli.com.cpo.databinding.ActivityCustomerTitleBindingImpl;
import ymsli.com.cpo.databinding.ActivityLoginBindingImpl;
import ymsli.com.cpo.databinding.ActivityNftBindingImpl;
import ymsli.com.cpo.databinding.ActivityOfferListBindingImpl;
import ymsli.com.cpo.databinding.ActivityProfileBindingImpl;
import ymsli.com.cpo.databinding.ActivityServiceHistoryBindingImpl;
import ymsli.com.cpo.databinding.ActivityServiceHistoryDetailBindingImpl;
import ymsli.com.cpo.databinding.ActivityServiceSubmitBindingImpl;
import ymsli.com.cpo.databinding.ActivityVehicleInformationCustomerBindingImpl;
import ymsli.com.cpo.databinding.ActivityWarrantiesBindingImpl;
import ymsli.com.cpo.databinding.DialogLinkCryptoBindingImpl;
import ymsli.com.cpo.databinding.DialogRejectBindingImpl;
import ymsli.com.cpo.databinding.FragmentVehicleInformationCustomerBindingImpl;
import ymsli.com.cpo.databinding.ItemAppraisalHistoryBindingImpl;
import ymsli.com.cpo.databinding.ItemDealerNearMeBindingImpl;
import ymsli.com.cpo.databinding.ItemGradeListBindingImpl;
import ymsli.com.cpo.databinding.ItemImageDesignBindingImpl;
import ymsli.com.cpo.databinding.ItemJobcardBindingImpl;
import ymsli.com.cpo.databinding.ItemListModifyBindingImpl;
import ymsli.com.cpo.databinding.ItemListServiceHistoryDetailBindingImpl;
import ymsli.com.cpo.databinding.ItemListServiceSubmitBindingImpl;
import ymsli.com.cpo.databinding.ListItemOfferBindingImpl;
import ymsli.com.cpo.databinding.ListItemParentConcernedBindingImpl;
import ymsli.com.cpo.databinding.ListItemScheduledAppointmnetBindingImpl;
import ymsli.com.cpo.databinding.ListItemSelectDateBindingImpl;
import ymsli.com.cpo.databinding.ListItemSelectTimeBindingImpl;
import ymsli.com.cpo.databinding.ListitemWarrantiesBindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYAPPRAISALDETAIL = 1;

  private static final int LAYOUT_ACTIVITYAPPRAISALHISTORY = 2;

  private static final int LAYOUT_ACTIVITYBOOKAPPOINTMENT = 3;

  private static final int LAYOUT_ACTIVITYCAMERACLICK = 4;

  private static final int LAYOUT_ACTIVITYCUSTOMERTITLE = 5;

  private static final int LAYOUT_ACTIVITYLOGIN = 6;

  private static final int LAYOUT_ACTIVITYNFT = 7;

  private static final int LAYOUT_ACTIVITYOFFERLIST = 8;

  private static final int LAYOUT_ACTIVITYPROFILE = 9;

  private static final int LAYOUT_ACTIVITYSERVICEHISTORY = 10;

  private static final int LAYOUT_ACTIVITYSERVICEHISTORYDETAIL = 11;

  private static final int LAYOUT_ACTIVITYSERVICESUBMIT = 12;

  private static final int LAYOUT_ACTIVITYVEHICLEINFORMATIONCUSTOMER = 13;

  private static final int LAYOUT_ACTIVITYWARRANTIES = 14;

  private static final int LAYOUT_DIALOGLINKCRYPTO = 15;

  private static final int LAYOUT_DIALOGREJECT = 16;

  private static final int LAYOUT_FRAGMENTVEHICLEINFORMATIONCUSTOMER = 17;

  private static final int LAYOUT_ITEMAPPRAISALHISTORY = 18;

  private static final int LAYOUT_ITEMDEALERNEARME = 19;

  private static final int LAYOUT_ITEMGRADELIST = 20;

  private static final int LAYOUT_ITEMIMAGEDESIGN = 21;

  private static final int LAYOUT_ITEMJOBCARD = 22;

  private static final int LAYOUT_ITEMLISTMODIFY = 23;

  private static final int LAYOUT_ITEMLISTSERVICEHISTORYDETAIL = 24;

  private static final int LAYOUT_ITEMLISTSERVICESUBMIT = 25;

  private static final int LAYOUT_LISTITEMOFFER = 26;

  private static final int LAYOUT_LISTITEMPARENTCONCERNED = 27;

  private static final int LAYOUT_LISTITEMSCHEDULEDAPPOINTMNET = 28;

  private static final int LAYOUT_LISTITEMSELECTDATE = 29;

  private static final int LAYOUT_LISTITEMSELECTTIME = 30;

  private static final int LAYOUT_LISTITEMWARRANTIES = 31;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(31);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_appraisal_detail, LAYOUT_ACTIVITYAPPRAISALDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_appraisal_history, LAYOUT_ACTIVITYAPPRAISALHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_book_appointment, LAYOUT_ACTIVITYBOOKAPPOINTMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_camera_click, LAYOUT_ACTIVITYCAMERACLICK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_customer_title, LAYOUT_ACTIVITYCUSTOMERTITLE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_nft, LAYOUT_ACTIVITYNFT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_offer_list, LAYOUT_ACTIVITYOFFERLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_profile, LAYOUT_ACTIVITYPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_service_history, LAYOUT_ACTIVITYSERVICEHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_service_history_detail, LAYOUT_ACTIVITYSERVICEHISTORYDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_service_submit, LAYOUT_ACTIVITYSERVICESUBMIT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_vehicle_information_customer, LAYOUT_ACTIVITYVEHICLEINFORMATIONCUSTOMER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.activity_warranties, LAYOUT_ACTIVITYWARRANTIES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.dialog_link_crypto, LAYOUT_DIALOGLINKCRYPTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.dialog_reject, LAYOUT_DIALOGREJECT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.fragment_vehicle_information_customer, LAYOUT_FRAGMENTVEHICLEINFORMATIONCUSTOMER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.item_appraisal_history, LAYOUT_ITEMAPPRAISALHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.item_dealer_near_me, LAYOUT_ITEMDEALERNEARME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.item_grade_list, LAYOUT_ITEMGRADELIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.item_image_design, LAYOUT_ITEMIMAGEDESIGN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.item_jobcard, LAYOUT_ITEMJOBCARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.item_list_modify, LAYOUT_ITEMLISTMODIFY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.item_list_service_history_detail, LAYOUT_ITEMLISTSERVICEHISTORYDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.item_list_service_submit, LAYOUT_ITEMLISTSERVICESUBMIT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.list_item_offer, LAYOUT_LISTITEMOFFER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.list_item_parent_concerned, LAYOUT_LISTITEMPARENTCONCERNED);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.list_item_scheduled_appointmnet, LAYOUT_LISTITEMSCHEDULEDAPPOINTMNET);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.list_item_select_date, LAYOUT_LISTITEMSELECTDATE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.list_item_select_time, LAYOUT_LISTITEMSELECTTIME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ymsli.com.cpo.R.layout.listitem_warranties, LAYOUT_LISTITEMWARRANTIES);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYAPPRAISALDETAIL: {
          if ("layout/activity_appraisal_detail_0".equals(tag)) {
            return new ActivityAppraisalDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_appraisal_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYAPPRAISALHISTORY: {
          if ("layout/activity_appraisal_history_0".equals(tag)) {
            return new ActivityAppraisalHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_appraisal_history is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYBOOKAPPOINTMENT: {
          if ("layout/activity_book_appointment_0".equals(tag)) {
            return new ActivityBookAppointmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_book_appointment is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCAMERACLICK: {
          if ("layout/activity_camera_click_0".equals(tag)) {
            return new ActivityCameraClickBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_camera_click is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCUSTOMERTITLE: {
          if ("layout/activity_customer_title_0".equals(tag)) {
            return new ActivityCustomerTitleBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_customer_title is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new ActivityLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYNFT: {
          if ("layout/activity_nft_0".equals(tag)) {
            return new ActivityNftBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_nft is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYOFFERLIST: {
          if ("layout/activity_offer_list_0".equals(tag)) {
            return new ActivityOfferListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_offer_list is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPROFILE: {
          if ("layout/activity_profile_0".equals(tag)) {
            return new ActivityProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSERVICEHISTORY: {
          if ("layout/activity_service_history_0".equals(tag)) {
            return new ActivityServiceHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_service_history is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSERVICEHISTORYDETAIL: {
          if ("layout/activity_service_history_detail_0".equals(tag)) {
            return new ActivityServiceHistoryDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_service_history_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSERVICESUBMIT: {
          if ("layout/activity_service_submit_0".equals(tag)) {
            return new ActivityServiceSubmitBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_service_submit is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYVEHICLEINFORMATIONCUSTOMER: {
          if ("layout/activity_vehicle_information_customer_0".equals(tag)) {
            return new ActivityVehicleInformationCustomerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_vehicle_information_customer is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYWARRANTIES: {
          if ("layout/activity_warranties_0".equals(tag)) {
            return new ActivityWarrantiesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_warranties is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGLINKCRYPTO: {
          if ("layout/dialog_link_crypto_0".equals(tag)) {
            return new DialogLinkCryptoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_link_crypto is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGREJECT: {
          if ("layout/dialog_reject_0".equals(tag)) {
            return new DialogRejectBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_reject is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTVEHICLEINFORMATIONCUSTOMER: {
          if ("layout/fragment_vehicle_information_customer_0".equals(tag)) {
            return new FragmentVehicleInformationCustomerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_vehicle_information_customer is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMAPPRAISALHISTORY: {
          if ("layout/item_appraisal_history_0".equals(tag)) {
            return new ItemAppraisalHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_appraisal_history is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMDEALERNEARME: {
          if ("layout/item_dealer_near_me_0".equals(tag)) {
            return new ItemDealerNearMeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_dealer_near_me is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMGRADELIST: {
          if ("layout/item_grade_list_0".equals(tag)) {
            return new ItemGradeListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_grade_list is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMIMAGEDESIGN: {
          if ("layout/item_image_design_0".equals(tag)) {
            return new ItemImageDesignBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_image_design is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMJOBCARD: {
          if ("layout/item_jobcard_0".equals(tag)) {
            return new ItemJobcardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_jobcard is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMLISTMODIFY: {
          if ("layout/item_list_modify_0".equals(tag)) {
            return new ItemListModifyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_list_modify is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMLISTSERVICEHISTORYDETAIL: {
          if ("layout/item_list_service_history_detail_0".equals(tag)) {
            return new ItemListServiceHistoryDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_list_service_history_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMLISTSERVICESUBMIT: {
          if ("layout/item_list_service_submit_0".equals(tag)) {
            return new ItemListServiceSubmitBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_list_service_submit is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTITEMOFFER: {
          if ("layout/list_item_offer_0".equals(tag)) {
            return new ListItemOfferBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_item_offer is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTITEMPARENTCONCERNED: {
          if ("layout/list_item_parent_concerned_0".equals(tag)) {
            return new ListItemParentConcernedBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_item_parent_concerned is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTITEMSCHEDULEDAPPOINTMNET: {
          if ("layout/list_item_scheduled_appointmnet_0".equals(tag)) {
            return new ListItemScheduledAppointmnetBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_item_scheduled_appointmnet is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTITEMSELECTDATE: {
          if ("layout/list_item_select_date_0".equals(tag)) {
            return new ListItemSelectDateBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_item_select_date is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTITEMSELECTTIME: {
          if ("layout/list_item_select_time_0".equals(tag)) {
            return new ListItemSelectTimeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_item_select_time is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTITEMWARRANTIES: {
          if ("layout/listitem_warranties_0".equals(tag)) {
            return new ListitemWarrantiesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for listitem_warranties is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "item");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(31);

    static {
      sKeys.put("layout/activity_appraisal_detail_0", ymsli.com.cpo.R.layout.activity_appraisal_detail);
      sKeys.put("layout/activity_appraisal_history_0", ymsli.com.cpo.R.layout.activity_appraisal_history);
      sKeys.put("layout/activity_book_appointment_0", ymsli.com.cpo.R.layout.activity_book_appointment);
      sKeys.put("layout/activity_camera_click_0", ymsli.com.cpo.R.layout.activity_camera_click);
      sKeys.put("layout/activity_customer_title_0", ymsli.com.cpo.R.layout.activity_customer_title);
      sKeys.put("layout/activity_login_0", ymsli.com.cpo.R.layout.activity_login);
      sKeys.put("layout/activity_nft_0", ymsli.com.cpo.R.layout.activity_nft);
      sKeys.put("layout/activity_offer_list_0", ymsli.com.cpo.R.layout.activity_offer_list);
      sKeys.put("layout/activity_profile_0", ymsli.com.cpo.R.layout.activity_profile);
      sKeys.put("layout/activity_service_history_0", ymsli.com.cpo.R.layout.activity_service_history);
      sKeys.put("layout/activity_service_history_detail_0", ymsli.com.cpo.R.layout.activity_service_history_detail);
      sKeys.put("layout/activity_service_submit_0", ymsli.com.cpo.R.layout.activity_service_submit);
      sKeys.put("layout/activity_vehicle_information_customer_0", ymsli.com.cpo.R.layout.activity_vehicle_information_customer);
      sKeys.put("layout/activity_warranties_0", ymsli.com.cpo.R.layout.activity_warranties);
      sKeys.put("layout/dialog_link_crypto_0", ymsli.com.cpo.R.layout.dialog_link_crypto);
      sKeys.put("layout/dialog_reject_0", ymsli.com.cpo.R.layout.dialog_reject);
      sKeys.put("layout/fragment_vehicle_information_customer_0", ymsli.com.cpo.R.layout.fragment_vehicle_information_customer);
      sKeys.put("layout/item_appraisal_history_0", ymsli.com.cpo.R.layout.item_appraisal_history);
      sKeys.put("layout/item_dealer_near_me_0", ymsli.com.cpo.R.layout.item_dealer_near_me);
      sKeys.put("layout/item_grade_list_0", ymsli.com.cpo.R.layout.item_grade_list);
      sKeys.put("layout/item_image_design_0", ymsli.com.cpo.R.layout.item_image_design);
      sKeys.put("layout/item_jobcard_0", ymsli.com.cpo.R.layout.item_jobcard);
      sKeys.put("layout/item_list_modify_0", ymsli.com.cpo.R.layout.item_list_modify);
      sKeys.put("layout/item_list_service_history_detail_0", ymsli.com.cpo.R.layout.item_list_service_history_detail);
      sKeys.put("layout/item_list_service_submit_0", ymsli.com.cpo.R.layout.item_list_service_submit);
      sKeys.put("layout/list_item_offer_0", ymsli.com.cpo.R.layout.list_item_offer);
      sKeys.put("layout/list_item_parent_concerned_0", ymsli.com.cpo.R.layout.list_item_parent_concerned);
      sKeys.put("layout/list_item_scheduled_appointmnet_0", ymsli.com.cpo.R.layout.list_item_scheduled_appointmnet);
      sKeys.put("layout/list_item_select_date_0", ymsli.com.cpo.R.layout.list_item_select_date);
      sKeys.put("layout/list_item_select_time_0", ymsli.com.cpo.R.layout.list_item_select_time);
      sKeys.put("layout/listitem_warranties_0", ymsli.com.cpo.R.layout.listitem_warranties);
    }
  }
}
