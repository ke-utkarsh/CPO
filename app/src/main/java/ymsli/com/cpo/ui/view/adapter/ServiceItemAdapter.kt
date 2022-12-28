package ymsli.com.cpo.ui.view.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.ServiceDataResult

class ServiceItemAdapter (private val mList:ArrayList<ServiceDataResult>, private var clickListener: ((pposition : Int) -> Unit)): RecyclerView.Adapter<ServiceItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_service_item, parent, false)
        return ServiceItemAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=mList[position]
        holder.check.text=item.Description
        holder.check.buttonTintList= ColorStateList(arrayOf(intArrayOf(-android.R.attr.checked),intArrayOf(-android.R.attr.checked)),
        intArrayOf(Color.GRAY,Color.BLACK))
        holder.cardView.setBackgroundResource(R.drawable.line_white_background)
        if(holder.check.text=="Other items"){
            holder.check.isChecked=true
            holder.cardView.setBackgroundResource(R.drawable.black_line_white_background)
            holder.check.buttonTintList=ColorStateList(arrayOf(intArrayOf(-android.R.attr.checked)),intArrayOf(Color.BLACK))
        }
        holder.check.setOnClickListener {
            clickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) :RecyclerView.ViewHolder(ItemView) {
        val check:CheckBox=itemView.findViewById(R.id.cbCheck)
        val cardView:CardView=itemView.findViewById(R.id.cvJobCard)
    }
}