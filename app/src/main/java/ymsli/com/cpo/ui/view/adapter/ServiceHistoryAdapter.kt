package ymsli.com.cpo.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.AppointmentResult
import ymsli.com.cpo.data.model.MaintenanceList
import ymsli.com.cpo.databinding.ItemJobcardBinding
import ymsli.com.cpo.databinding.ListItemScheduledAppointmnetBinding

class ServiceHistoryAdapter (private val mList: List<MaintenanceList>,
private var warrantiesClickListener: ((pPosition: Int) -> Unit)
) : RecyclerView.Adapter<ServiceHistoryAdapter.ViewHolder>()  {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceHistoryAdapter.ViewHolder {
        val binding: ItemJobcardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_jobcard,
            parent,
            false
        )
        return ServiceHistoryAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceHistoryAdapter.ViewHolder, position: Int) {
        val mListItem = mList[position]
        var maintenanceData=""
        holder.binding.item = mListItem
        holder.binding.executePendingBindings()

        if(mListItem != null){
            holder.binding.tvJobCardId.text = mListItem.MaintenanceId.split("-")[4].toUpperCase()
            for(i in mListItem.Lines){
                if(maintenanceData!="") maintenanceData+= System.lineSeparator() + i.Serial.toString().substringBefore(".") + ". "+i.LineDesc
                else maintenanceData+= i.Serial.toString() + ". "+i.LineDesc
            }
            for(i in mListItem.Others){
                if(maintenanceData!="") maintenanceData+= System.lineSeparator() + i.Serial.toString() + ". "+i.LineDesc
                else maintenanceData+= i.Serial.toString() + ". "+i.LineDesc
            }


        }
        holder.binding.tvConcerned.text = maintenanceData
        holder.binding.parentLayout.setOnClickListener {
            warrantiesClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(mBinding: ItemJobcardBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ItemJobcardBinding = mBinding
    }
}