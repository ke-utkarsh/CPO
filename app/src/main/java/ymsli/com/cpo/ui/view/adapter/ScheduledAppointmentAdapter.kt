package ymsli.com.cpo.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.AppointmentResult
import ymsli.com.cpo.databinding.ListItemScheduledAppointmnetBinding

class ScheduledAppointmentAdapter(
    private val mList: List<AppointmentResult>,
    private var warrantiesClickListener: ((pPosition: Int) -> Unit)
) : RecyclerView.Adapter<ScheduledAppointmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemScheduledAppointmnetBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_scheduled_appointmnet,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem = mList[position]
        var concernedData = ""
        holder.binding.item = mListItem
        holder.binding.executePendingBindings()
        holder.binding.tvJobCardId.text = mListItem.ServiceRequestId.split("-")[4].toUpperCase()
        var j=1
        for(i in mListItem.Concerend){
            if(concernedData!= "")
            concernedData+= System.lineSeparator() + j +". "+i.LineDesc
            else concernedData= concernedData+ j + ". " + i.LineDesc
            j+=1
        }


        holder.binding.tvConcerned.text = concernedData
        holder.binding.parentLayout.setOnClickListener {
            warrantiesClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size;
    }

    class ViewHolder(mBinding: ListItemScheduledAppointmnetBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ListItemScheduledAppointmnetBinding = mBinding
    }

}