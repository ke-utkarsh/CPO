package ymsli.com.cpo.ui.view.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.DateResult
import ymsli.com.cpo.data.sharedpref.SharedPref
import ymsli.com.cpo.databinding.ListItemSelectDateBinding
import ymsli.com.cpo.utils.Constants


class SelectDateAdapter(private val context : Context, private val mList: List<DateResult>,
                        private var dateClickListener: ((pPosition : Int) -> Unit)) :
    RecyclerView.Adapter<SelectDateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemSelectDateBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_select_date,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem = mList[position]
        holder.binding.item = mListItem
        holder.binding.executePendingBindings()

        if(mListItem.status == "1" )
        {
            holder.binding.parentLayout.background = ContextCompat.getDrawable(context, R.drawable.black_cut_background)
            holder.binding.tvDate.setTextColor(context.resources.getColor(R.color.white))
            holder.binding.tvMonth.setTextColor(context.resources.getColor(R.color.white))
        }
        else
        {
            holder.binding.parentLayout.background = ContextCompat.getDrawable(context, R.drawable.line_white_background)
            holder.binding.tvDate.setTextColor(context.resources.getColor(R.color.black))
            holder.binding.tvMonth.setTextColor(context.resources.getColor(R.color.black))
        }

        holder.binding.parentLayout.setOnClickListener {
            dateClickListener(holder.absoluteAdapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return mList.size;
    }

    class ViewHolder(mBinding: ListItemSelectDateBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ListItemSelectDateBinding = mBinding
    }

}