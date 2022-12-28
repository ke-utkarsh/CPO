package ymsli.com.cpo.ui.view.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.*
import ymsli.com.cpo.databinding.ItemImageDesignBinding
import ymsli.com.cpo.databinding.ItemListServiceSubmitBinding
import ymsli.com.cpo.utils.Utils

class ServiceHistoryChildAdapter (private val context: Context, private val mList:List<LineImage>,private val isClose : Boolean,
                                 private var focuImageClickListener: ((pposition : Int) -> Unit)):
    RecyclerView.Adapter<ServiceHistoryChildAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHistoryChildAdapter.ViewHolder {
        val binding: ItemImageDesignBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_image_design,
            parent,
            false
        )
        return ServiceHistoryChildAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=mList[position]
        if(!isClose){
            holder.binding.deleteImage.visibility= View.GONE
        }

        Utils.setImage(context,item.ThumbnailUrl,holder.binding.ivVehicleImage)


        holder.binding.ivVehicleImage.setOnClickListener {
            focuImageClickListener(position)
        }

       holder.binding.deleteImage.visibility= View.GONE

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder( val mBinding: ItemImageDesignBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ItemImageDesignBinding = mBinding
    }
}