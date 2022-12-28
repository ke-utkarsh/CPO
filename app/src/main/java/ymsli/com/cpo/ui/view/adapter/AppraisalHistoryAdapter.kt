package ymsli.com.cpo.ui.view.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.AppraisalHistoryResult
import ymsli.com.cpo.databinding.ItemAppraisalHistoryBinding
import ymsli.com.cpo.databinding.ItemListModifyBinding

class AppraisalHistoryAdapter(private val context: Context, private val mList:List<AppraisalHistoryResult>, private var nextClickListener:((pPosition : Int) -> Unit)): RecyclerView.Adapter<AppraisalHistoryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppraisalHistoryAdapter.ViewHolder {
        val binding: ItemAppraisalHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_appraisal_history,
            parent,
            false
        )
        return AppraisalHistoryAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem = mList[position]
        holder.binding.item = mListItem
        holder.binding.executePendingBindings()

        holder.binding.ivNext.setOnClickListener {
            nextClickListener(position)
        }

        if(mListItem.Grade == "B"){
            holder.binding.ivIcon.backgroundTintList= ColorStateList(arrayOf(intArrayOf(-android.R.attr.checked),intArrayOf(-android.R.attr.checked)),
                intArrayOf(Color.parseColor("#FFC000"),Color.parseColor("#FFC000"))
            )
        }
        if(mListItem.Grade == "C"){
            holder.binding.ivIcon.backgroundTintList= ColorStateList(arrayOf(intArrayOf(-android.R.attr.checked),intArrayOf(-android.R.attr.checked)),
                intArrayOf(Color.parseColor("#FE0000"),Color.parseColor("#FE0000"))
            )
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(mBinding: ItemAppraisalHistoryBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ItemAppraisalHistoryBinding = mBinding
    }

}