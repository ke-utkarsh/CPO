package ymsli.com.cpo.ui.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.*
import ymsli.com.cpo.databinding.ItemListServiceSubmitBinding
import ymsli.com.cpo.databinding.ListitemWarrantiesBinding

class ServiceSubmitParentAdapter(
    private val context: Context,
    private val beforeImageList: List<Concerned>,
    private val afterImageList: List<Concerned>,
    private var cameraClickListener: ((position: Int) -> Unit),
    private var childImageDeleteAndFocusListener: ((pPosition: ArrayList<Int>, parentPosition: Int) -> Unit)
) :
    RecyclerView.Adapter<ServiceSubmitParentAdapter.ViewHolder>() {

    lateinit var childBeforeItemAdapter: ServiceSubmitChildAdapter
    lateinit var childAfterItemAdapter: ServiceSubmitChildAdapter
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceSubmitParentAdapter.ViewHolder {
        val binding: ItemListServiceSubmitBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_service_submit,
            parent,
            false
        )
        return ServiceSubmitParentAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = beforeImageList[position]
        holder.binding.tvIssueHead.text = item.LineDesc


        holder.binding.ivClickBeforeImage.setOnClickListener {
            cameraClickListener(holder.absoluteAdapterPosition)
        }

        var t = beforeImageList[position].LineImages
        childBeforeItemAdapter =
            ServiceSubmitChildAdapter(context, beforeImageList[position].LineImages, false) {
                childImageDeleteAndFocusListener(it, holder.bindingAdapterPosition)
            }

        holder.binding.rvBeforeImages.adapter = childBeforeItemAdapter
        holder.binding.rvBeforeImages.setRecycledViewPool(viewPool)


        childAfterItemAdapter =
            ServiceSubmitChildAdapter(context, afterImageList[position].LineImages, true) {
                childImageDeleteAndFocusListener(it, holder.bindingAdapterPosition)
            }
        holder.binding.rvAfterImages.adapter = childAfterItemAdapter
        holder.binding.rvAfterImages.setRecycledViewPool(viewPool)



    if (afterImageList.isEmpty())
    {
        holder.binding.rvAfterImages.visibility = View.GONE
    } else
    {
        holder.binding.rvAfterImages.visibility = View.VISIBLE
    }

}

override fun getItemCount(): Int {
    return beforeImageList.size
}

class ViewHolder(val mBinding: ItemListServiceSubmitBinding) :
    RecyclerView.ViewHolder(mBinding.root) {
    var binding: ItemListServiceSubmitBinding = mBinding
}

}