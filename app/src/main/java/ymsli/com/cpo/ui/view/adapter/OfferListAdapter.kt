package ymsli.com.cpo.ui.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.OfferList
import ymsli.com.cpo.databinding.ListItemOfferBinding
import ymsli.com.cpo.databinding.ListitemWarrantiesBinding

class OfferListAdapter(private val activity: Activity,private val mList:List<OfferList>,
private val rejectClickListener: (( pPosition : Int) -> Unit),private val acceptClickListener: (( pPosition : Int) -> Unit)) : RecyclerView.Adapter<OfferListAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemOfferBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_offer,
            parent,
            false
        )
        return OfferListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem = mList[position]
        holder.binding.item = mListItem
        holder.binding.executePendingBindings()
        holder.binding.tvUserShortName.text= mListItem.Name.split(' ').mapNotNull { it.firstOrNull()?.toString() }.reduce { acc, s -> acc + s }
        holder.binding.tvReject.setOnClickListener {
            rejectClickListener(position)
        }
        holder.binding.tvAccept.setOnClickListener {
            acceptClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(mBinding: ListItemOfferBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ListItemOfferBinding = mBinding
    }

}