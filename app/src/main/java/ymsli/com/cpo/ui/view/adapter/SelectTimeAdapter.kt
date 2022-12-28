package ymsli.com.cpo.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.databinding.ListItemSelectTimeBinding




class SelectTimeAdapter(private val context : Context, private val mList: List<String>, private var timeClickListener: ((pPosition : Int) -> Unit)) : RecyclerView.Adapter<SelectTimeAdapter.ViewHolder>() {


    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemSelectTimeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_select_time,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem  = mList[position]
        holder.binding.tvTime.text = mListItem

        if(position == selectedPosition )
        {
            holder.binding.parentLayout.background = ContextCompat.getDrawable(context, R.drawable.black_cut_background)
            holder.binding.tvTime.setTextColor(context.resources.getColor(R.color.white))
        }
        else
        {
            holder.binding.parentLayout.background = ContextCompat.getDrawable(context, R.drawable.line_white_background)
            holder.binding.tvTime.setTextColor(context.resources.getColor(R.color.black))
        }

        holder.binding.parentLayout.setOnClickListener {
            selectedPosition = holder.absoluteAdapterPosition
            timeClickListener(holder.absoluteAdapterPosition)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return mList.size;
    }

    class ViewHolder(mBinding: ListItemSelectTimeBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ListItemSelectTimeBinding = mBinding
    }

}