package ymsli.com.cpo.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.ActiveWarrantiesData
import ymsli.com.cpo.data.model.DealersResult
import ymsli.com.cpo.databinding.ItemDealerNearMeBinding
import ymsli.com.cpo.databinding.ListitemWarrantiesBinding

class DealerNearAdapter(private val mList: List<DealersResult>, private var dealerClickListener: (( pPosition : Int) -> Unit)) : RecyclerView.Adapter<DealerNearAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDealerNearMeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_dealer_near_me,
            parent,
            false
        )
        return DealerNearAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem = mList[position]
        holder.binding.item = mListItem
        holder.binding.executePendingBindings()
        holder.binding.btnBook.setOnClickListener {
            dealerClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size;
    }
    class ViewHolder(mBinding: ItemDealerNearMeBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ItemDealerNearMeBinding = mBinding
    }

}