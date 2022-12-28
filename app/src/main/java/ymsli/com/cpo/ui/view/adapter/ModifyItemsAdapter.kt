package ymsli.com.cpo.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.LineResult

import ymsli.com.cpo.databinding.ItemListModifyBinding

class ModifyItemsAdapter(private val context: Context, private val mList:List<LineResult>, private val isOtherItems:Boolean, private var issueDeleteListener:((pPosition : Int) -> Unit),
                         private var imageClickListener:((pPosition : Int) -> Unit),
                         private var childImageDeleteListener:((pPosition : ArrayList<Int>, parentPosition : Int) -> Unit))
: RecyclerView.Adapter<ModifyItemsAdapter.ViewHolder>(){
    lateinit var childItemAdapter: ModifyItemsImagesAdapter
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListModifyBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_modify,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem = mList[position]
        holder.binding.item = mListItem
        holder.binding.executePendingBindings()

        holder.binding.ivDeleteIssue.setOnClickListener {
            issueDeleteListener(holder.bindingAdapterPosition)
        }

        holder.binding.ivClickConcernedIssueImage.setOnClickListener {
            imageClickListener(holder.bindingAdapterPosition)
        }

        if(!isOtherItems)holder.binding.ivDeleteIssue.visibility= View.GONE
        else{
            if(mList.size>1)
            {
                holder.binding.ivDeleteIssue.visibility = View.VISIBLE
            }
            else
            {
                holder.binding.ivDeleteIssue.visibility = View.GONE
            }
        }

        val layoutManager = LinearLayoutManager(
            holder.binding.rvConcernedImages.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        if(mListItem.LineImages.isNullOrEmpty())
        {
            layoutManager.initialPrefetchItemCount =0
        }
        else
        {
            layoutManager.initialPrefetchItemCount = mListItem.LineImages.size
            childItemAdapter = ModifyItemsImagesAdapter(context,mListItem.LineImages,false){it->
                childImageDeleteListener(it, holder.bindingAdapterPosition)
            }
            holder.binding.rvConcernedImages.layoutManager = layoutManager
            holder.binding.rvConcernedImages.adapter = childItemAdapter
            holder.binding.rvConcernedImages.setRecycledViewPool(viewPool)
        }

        if (mListItem.LineImages.size == 0) {
            holder.binding.rvConcernedImages.visibility = View.GONE
        } else {
            holder.binding.rvConcernedImages.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(mBinding: ItemListModifyBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ItemListModifyBinding = mBinding
    }
}