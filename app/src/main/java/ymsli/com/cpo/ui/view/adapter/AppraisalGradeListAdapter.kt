package ymsli.com.cpo.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.AppraisalGrade
import ymsli.com.cpo.data.model.AppraisalHistoryResult
import ymsli.com.cpo.databinding.ItemAppraisalHistoryBinding
import ymsli.com.cpo.databinding.ItemGradeListBinding

class AppraisalGradeListAdapter(private val context: Context, private val mList:List<AppraisalGrade>): RecyclerView.Adapter<AppraisalGradeListAdapter.ViewHolder>() {
    class ViewHolder(mBinding: ItemGradeListBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ItemGradeListBinding = mBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemGradeListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_grade_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem = mList[position]
        holder.binding.item = mListItem
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}