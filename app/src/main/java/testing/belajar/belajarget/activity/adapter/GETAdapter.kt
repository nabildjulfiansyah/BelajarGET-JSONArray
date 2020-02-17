package testing.belajar.belajarget.activity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import testing.belajar.belajarget.R
import testing.belajar.belajarget.activity.received.GETReceived
import kotlin.collections.ArrayList

class GETAdapter(
    var context: Context,
    private var idArray: ArrayList<String>,
    private var userIdArray: ArrayList<String>,
    private var titleArray: ArrayList<String>,
    private var bodyArray: ArrayList<String>) :
    RecyclerView.Adapter<GETAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // infalte the item Layout
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recylerview_get, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) { // set the data in items
        holder.id.text = idArray[position]
        holder.userId.text = userIdArray[position]
        holder.title.text = titleArray[position]
        holder.body.text = bodyArray[position]
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener {
            // display a toast with person name on item click

        }
    }

    override fun getItemCount(): Int {
        return idArray.size
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var id: TextView = itemView.findViewById<View>(R.id.tvId) as TextView
        var userId: TextView = itemView.findViewById<View>(R.id.tvUserId) as TextView
        var title : TextView = itemView.findViewById<View>(R.id.tvTitle) as TextView
        var body : TextView =  itemView.findViewById<View>(R.id.tvBody) as TextView

    }

}

