package ymsli.com.cpo.ui.view.adapter

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.ActiveWarrantiesData
import ymsli.com.cpo.databinding.ListitemWarrantiesBinding
class WarrantiesAdapter(private val activity: Activity,private val mList: List<ActiveWarrantiesData>,
                        private var warrantiesClickListener: (( pPosition : Int) -> Unit)) : RecyclerView.Adapter<WarrantiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListitemWarrantiesBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.listitem_warranties,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem = mList[position]
        holder.binding.item = mListItem
        holder.binding.executePendingBindings()
        holder.binding.btnCreateNft.setOnClickListener {
           warrantiesClickListener(position)
        }
        displayImage(mListItem.DisplayImage, holder.binding.pbProgress,holder.binding.ivVehiclePhoto)
    }

    private fun displayImage(
        displayImage: String,
        pbProgress: ProgressBar,
        ivVehiclePhoto: ImageView, ) {
        Glide.with(activity)
            .load(displayImage)
            .apply(RequestOptions.centerInsideTransform())
            .placeholder(R.drawable.ic_image_black_24dp)
            .error(R.drawable.error_photo)
            .signature(
                ObjectKey(
                    displayImage + System.currentTimeMillis().toString()
                )
            ).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    pbProgress.visibility = View.GONE
                    return false;
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    pbProgress.visibility = View.GONE
                    return false;
                }
            })
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    @Nullable transition: Transition<in Drawable?>?
                ) {
                    ivVehiclePhoto.setBackgroundDrawable(resource)
                }
            })
    }

    override fun getItemCount(): Int {
        return mList.size;
    }

    class ViewHolder(mBinding: ListitemWarrantiesBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ListitemWarrantiesBinding = mBinding
    }

}