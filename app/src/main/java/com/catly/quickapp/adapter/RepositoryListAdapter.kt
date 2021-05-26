package com.catly.quickapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quickapp.R
import com.catly.quickapp.data.model.RepositoryListItem
import com.catly.quickapp.ui.repository_list.RepositoryListFragmentDirections
import java.text.DateFormat

class RepositoryListAdapter(
    private val findNavController: NavController
) : RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    var repoList: List<RepositoryListItem> = mutableListOf()

    fun update(fetchedRepoList: List<RepositoryListItem>) {
        repoList = fetchedRepoList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val dateView: TextView = view.findViewById(R.id.updated_date)
        val descriptionView: TextView = view.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rowPos = holder.adapterPosition
        val item = repoList[rowPos]
        holder.itemView.setOnClickListener {
            val directions =
                RepositoryListFragmentDirections.actionRepositoryListFragmentToWebViewFragment(
                    item.name,
                    item.html_url
                )
            findNavController.navigate(directions)

        }
        holder.itemView.apply {
            holder.nameView.text = item.name
            holder.dateView.text = DateFormat.getDateInstance().format(item.updated_at)
            holder.descriptionView.text = item.description

//        }
        }
    }

    override fun getItemCount(): Int {
        return repoList.size
    }
}