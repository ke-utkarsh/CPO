package ymsli.com.cpo.ui.view.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
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
import ymsli.com.cpo.data.model.*
import ymsli.com.cpo.databinding.ItemListServiceHistoryDetailBinding
import ymsli.com.cpo.databinding.ItemListServiceSubmitBinding
import ymsli.com.cpo.databinding.ListitemWarrantiesBinding
import ymsli.com.cpo.ui.view.activity.WebViewActivity
import ymsli.com.cpo.utils.Constants

class ServiceHistoryParentAdapter(
    private val context: Context,
    private val beforeImageList: List<MaintenanceConcerned>,
    private val afterImageList: List<MaintenanceConcerned>
) :
    RecyclerView.Adapter<ServiceHistoryParentAdapter.ViewHolder>() {

    lateinit var childBeforeItemAdapter: ServiceHistoryChildAdapter
    lateinit var childAfterItemAdapter: ServiceHistoryChildAdapter
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceHistoryParentAdapter.ViewHolder {
        val binding: ItemListServiceHistoryDetailBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_service_history_detail,
            parent,
            false
        )
        return ServiceHistoryParentAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = beforeImageList[position]
        holder.binding.tvIssueHead.text = item.LineType.Description
        holder.binding.tvIssueDescription.text=item.LineDesc


        childBeforeItemAdapter =
            ServiceHistoryChildAdapter(context, beforeImageList[position].LineImages, true) {
                showImageDialog(beforeImageList[position].LineDesc,beforeImageList[position].LineImages[it].OriginalImageUrl
                    ,beforeImageList[position].LineImages[it].IpfsUrl)
            }

        holder.binding.rvBeforeImages.adapter = childBeforeItemAdapter
        holder.binding.rvBeforeImages.setRecycledViewPool(viewPool)


        childAfterItemAdapter =
            ServiceHistoryChildAdapter(context, afterImageList[position].LineImages, false) {
                showImageDialog(afterImageList[position].LineDesc,afterImageList[position].LineImages[it].OriginalImageUrl
                ,afterImageList[position].LineImages[it].IpfsUrl)
            }
        holder.binding.rvAfterImages.adapter = childAfterItemAdapter
        holder.binding.rvAfterImages.setRecycledViewPool(viewPool)


    }

    override fun getItemCount(): Int {
        return beforeImageList.size
    }

    class ViewHolder(val mBinding: ItemListServiceHistoryDetailBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ItemListServiceHistoryDetailBinding = mBinding
    }

    private fun showImageDialog(s: String, originalImageUrl: String, ipfsurl: String) {
        var dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_images)
        Glide.with(context)
            .load(originalImageUrl)
            .apply(RequestOptions.centerInsideTransform())
            .placeholder(R.drawable.ic_image_black_24dp)
            .error(R.drawable.error_photo)
            .signature(
                ObjectKey(
                    originalImageUrl + System.currentTimeMillis().toString()
                )
            ).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    dialog.findViewById<ProgressBar>(R.id.pbProgress).visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    dialog.findViewById<ProgressBar>(R.id.pbProgress).visibility = View.GONE
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
                    dialog.findViewById<ImageView>(R.id.ivImage).setBackgroundDrawable(resource)

                }
            })
        dialog.findViewById<TextView?>(R.id.tvTitle).text = s
        val close: ImageView = dialog.findViewById(R.id.ivClose)
        close.setOnClickListener { dialog.dismiss() }
        var webView: TextView =dialog.findViewById(R.id.tvIpfs)
        webView.setOnClickListener {
            var intent= Intent(context.applicationContext, WebViewActivity::class.java)
            intent.putExtra(Constants.WEBVIEW_TITLE,s)
            intent.putExtra(Constants.URL,ipfsurl)
            context.startActivity(intent)
        }
        dialog.show()
    }

}