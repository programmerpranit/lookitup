package com.psp.lookitup.ui.adapters

import com.psp.lookitup.R
import com.psp.lookitup.data.Request
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RequestAdapter : ListAdapter<Request, RequestAdapter.RequestViewHolder>(DataComparator()) {

//    inner class QuestionViewHolder(
//        private val binding: ItemQuestionBinding
//        ) : RecyclerView.ViewHolder(binding.root)

    inner class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_request, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.title.text = currentItem.requestTitle
        //holder.name.text = currentItem.user.name
        // holder.time.text = currentItem.timestamp
    }

    class DataComparator : DiffUtil.ItemCallback<Request>() {
        override fun areItemsTheSame(oldItem: Request, newItem: Request): Boolean {
            return oldItem.requestTitle == newItem.requestTitle
        }

        override fun areContentsTheSame(oldItem: Request, newItem: Request): Boolean {
            return oldItem == newItem
        }

    }

}