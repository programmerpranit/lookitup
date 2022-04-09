package com.psp.lookitup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.psp.lookitup.R
import com.psp.lookitup.data.Request


class RequestAdapter(private val listner:IRequestClicked) : ListAdapter<Request, RequestAdapter.RequestViewHolder>(DataComparator()) {

    inner class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val location: TextView = itemView.findViewById(R.id.tvLocation)
        val name: TextView = itemView.findViewById(R.id.tvName)
        val occupation: TextView = itemView.findViewById(R.id.tvOccupation)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_request, parent, false)
        val viewHolder = RequestViewHolder(view)
        view.setOnClickListener{
            listner.onItemClicked(getItem(viewHolder.adapterPosition))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.title.text = currentItem.requestTitle
        holder.location.text = currentItem.roomLocation
        holder.name.text = currentItem.name
        holder.occupation.text = currentItem.occupation
    }
    class DataComparator : DiffUtil.ItemCallback<Request>() {
        override fun areItemsTheSame(oldItem: Request, newItem: Request): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Request, newItem: Request): Boolean {
            return oldItem == newItem
        }
    }

    interface IRequestClicked{
        fun onItemClicked(item: Request)
    }
}