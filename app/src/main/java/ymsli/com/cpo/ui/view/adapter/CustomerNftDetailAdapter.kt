package ymsli.com.cpo.ui.view.adapter

import android.app.Activity
import android.graphics.drawable.Drawable
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatButton
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
import ymsli.com.cpo.data.model.NftDetail

class CustomerNftDetailAdapter(private val activity: Activity, private val mList:List<NftDetail>, private var viewDetailsClickListener: ((pposition : Int) -> Unit), private var metaDataClickListener: ((pposition : Int) -> Unit))
    : RecyclerView.Adapter<CustomerNftDetailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nft, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=mList[position]
        holder.BikeName.text = item.Model
        holder.Id.text = item.NFTId
        holder.Date.text = item.NftMintDate

        if(item.NFTStatus.Id==2){
            holder.Metadata.visibility=GONE
        }else{
            holder.Metadata.visibility= VISIBLE
        }
        holder.ViewDetails.setOnClickListener{
            viewDetailsClickListener(position)
        }
        holder.Metadata.setOnClickListener {
            metaDataClickListener(position)
        }
        displayImage(item.DisplayImage, holder.Progressbar1,holder.VehiclePhoto)
    }

    override fun getItemCount(): Int {
        return mList.size
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

    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val ViewDetails:AppCompatButton=itemView.findViewById(R.id.btnViewDetails)
        val BikeName: TextView =itemView.findViewById(R.id.tvBikeName)
        val Id: TextView =itemView.findViewById(R.id.tvNft)
        val Date: TextView =itemView.findViewById(R.id.tvDate)
        val Metadata: TextView =itemView.findViewById(R.id.tvMetadata)
        val VehiclePhoto: ImageView = itemView.findViewById(R.id.ivVehiclePhoto)
        val Progressbar1: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
}