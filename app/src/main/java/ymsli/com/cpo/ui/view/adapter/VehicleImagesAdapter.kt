package ymsli.com.cpo.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.VehiclePhoto
import ymsli.com.cpo.databinding.ItemImageDesignBinding
import ymsli.com.cpo.utils.Utils

class VehicleImagesAdapter(private val context: Context, private val mList: List<VehiclePhoto>,private val nft:Boolean,
                           private var deleteImageListener: (( pPosition : ArrayList<Int>) -> Unit)) : RecyclerView.Adapter<VehicleImagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemImageDesignBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_image_design,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem = mList[position]
        Utils.setImage(context,mListItem.ThumbnailUrl,holder.binding.ivVehicleImage)

        if(nft){
            holder.binding.deleteImage.visibility=GONE
        }else{
            holder.binding.deleteImage.visibility=VISIBLE
        }
        holder.binding.deleteImage.setOnClickListener {
            deleteImageListener(arrayListOf<Int>(position,0))
        }
        holder.binding.ivVehicleImage.setOnClickListener {
            deleteImageListener(arrayListOf<Int>(position,1))
        }
    }

    override fun getItemCount(): Int {
        return mList.size;
    }

    class ViewHolder(mBinding: ItemImageDesignBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ItemImageDesignBinding = mBinding
    }

}