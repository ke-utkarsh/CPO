package ymsli.com.cpo.ui.view.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.ConcernedIssueModel
import ymsli.com.cpo.databinding.ListItemParentConcernedBinding


class ConcernedIssueAdapter(private val context: Context, private val mList: List<ConcernedIssueModel>,
                            private var issueDeleteListener:((pPosition : Int) -> Unit),
                            private var imageClickListener:((pPosition : Int) -> Unit),
                            private var childImageDeleteListener:((pPosition : ArrayList<Int>, parentPosition : Int) -> Unit)

) : RecyclerView.Adapter<ConcernedIssueAdapter.ViewHolder>(){

    private var nftStatus  = false
    lateinit var childItemAdapter: ConcernedImagesAdapter
    private val viewPool = RecyclerView.RecycledViewPool()

    fun changeNftValue(nft:Boolean)
    {
        nftStatus = nft
        childItemAdapter.changeNftValue(nft)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemParentConcernedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_parent_concerned,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItem = mList[position]
        holder.binding.item = mListItem
        holder.binding.executePendingBindings()




        //hide all the editable view if nftStatus is true
        if(nftStatus)
        {
            holder.binding.ivDeleteIssue.visibility =GONE
            holder.binding.cardLickIssueImages.visibility =GONE
            holder.binding.etIssue.isEnabled = false
            if(position == mList.size-1)
            {
                holder.binding.divider.visibility =GONE
            }
            else{
                holder.binding.divider.visibility = VISIBLE
            }
        }
        else
        {
            holder.binding.ivDeleteIssue.visibility = VISIBLE
            holder.binding.cardLickIssueImages.visibility = VISIBLE
            holder.binding.etIssue.isEnabled = true
        }

        //If there is only one item in concerned issue then hide the cross icon
        if(mList.size>1)
        {
            holder.binding.ivDeleteIssue.visibility = VISIBLE
        }
        else
        {
            holder.binding.ivDeleteIssue.visibility = GONE
        }

        holder.binding.ivDeleteIssue.setOnClickListener {
            issueDeleteListener(holder.bindingAdapterPosition)
        }

        holder.binding.ivClickConcernedIssueImage.setOnClickListener {
            imageClickListener(holder.bindingAdapterPosition)
        }

        val layoutManager = LinearLayoutManager(
            holder.binding.rvConcernedImages.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        if(mListItem.issueImages.isNullOrEmpty())
        {
            layoutManager.initialPrefetchItemCount =0
        }
        else
        {
            layoutManager.initialPrefetchItemCount = mListItem.issueImages.size
            childItemAdapter = ConcernedImagesAdapter(context,mListItem.issueImages,nftStatus){
                childImageDeleteListener(it, holder.bindingAdapterPosition)
            }
            holder.binding.rvConcernedImages.layoutManager = layoutManager
            holder.binding.rvConcernedImages.adapter = childItemAdapter
            holder.binding.rvConcernedImages.setRecycledViewPool(viewPool)
        }

        if (mListItem.issueImages.size == 0) {
            holder.binding.rvConcernedImages.visibility = GONE
        } else {
            holder.binding.rvConcernedImages.visibility = VISIBLE
        }
    }


    override fun getItemCount(): Int {
        return mList.size;
    }


    class ViewHolder(mBinding: ListItemParentConcernedBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var binding: ListItemParentConcernedBinding = mBinding


    }

}