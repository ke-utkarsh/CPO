package ymsli.com.cpo.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.ConcernedImagesModel
import ymsli.com.cpo.data.model.PdiImageData
import ymsli.com.cpo.databinding.ItemImageDesignBinding
import ymsli.com.cpo.utils.Utils

class ModifyItemsImagesAdapter(private val context: Context, private val mList: ArrayList<PdiImageData>, private val nft:Boolean,
                               private var deleteImageListener: (( pPosition : ArrayList<Int>) -> Unit)) : RecyclerView.Adapter<ModifyItemsImagesAdapter.ViewHolder>()  {
    private var nftStatus  = nft

    fun changeNftValue(nft:Boolean)
    {
        nftStatus = nft
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModifyItemsImagesAdapter.ViewHolder {
        val binding: ItemImageDesignBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_image_design,
            parent,
            false
        )
        return ModifyItemsImagesAdapter.ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ModifyItemsImagesAdapter.ViewHolder, position: Int) {
        val mListItem = mList[position]
        Utils.setImage(context,mListItem.ThumbnailUrl,holder.binding.ivVehicleImage)
        if(nftStatus){
            holder.binding.deleteImage.visibility= View.GONE
        }else{
            holder.binding.deleteImage.visibility= View.VISIBLE
        }

        holder.binding.deleteImage.setOnClickListener {
            deleteImageListener(arrayListOf(position,0) )
        }
        holder.binding.ivVehicleImage.setOnClickListener {
            deleteImageListener(arrayListOf(position,1) )
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(mBinding: ItemImageDesignBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ItemImageDesignBinding = mBinding
    }
}