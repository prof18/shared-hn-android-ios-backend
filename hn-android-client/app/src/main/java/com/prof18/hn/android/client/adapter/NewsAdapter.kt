package com.prof18.hn.android.client.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.prof18.hn.android.client.R
import com.prof18.hn.android.client.data.model.News

class NewsAdapter(var items: List<News> = listOf()) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_news_card,
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.news_title)
        private val date = itemView.findViewById<TextView>(R.id.news_date)
        private val card = itemView.findViewById<MaterialCardView>(R.id.news_card)

        fun bind(item: News) {
            title.text = item.title
            date.text = item.formattedDate
            card.setOnClickListener {
                itemView.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(item.url)
                    )
                )
            }
        }
    }
}
