package com.example.quickapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quickapp.R
import com.example.quickapp.data.model.RepositoryListItem
import java.text.DateFormat
import java.text.SimpleDateFormat

class RepositoryListAdapter(private val context: Context,  private val findNavController: NavController) : RecyclerView.Adapter<RepositoryListAdapter.RowViewHolder>() {

    var repoList: List<RepositoryListItem> = mutableListOf()

    fun update(fetchedRepoList: List<RepositoryListItem>){
        repoList = fetchedRepoList
        notifyDataSetChanged()
    }

    inner class RowViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameView : TextView = view.findViewById(R.id.name)
        val dateView : TextView = view.findViewById(R.id.updated_date)
        val descriptionView : TextView = view.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.table_list_item, parent, false)
        return RowViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val rowPos = holder.adapterPosition
        holder.itemView.apply {

//            if (rowPos == 0) {
//                setHeaderBg(holder.nameView)
//                setHeaderBg(holder.dateView)
//                setHeaderBg(holder.descriptionView)
//                holder.nameView.text = resources.getString(R.string.name)
//                holder.dateView.text = resources.getString(R.string.updated_date)
//                holder.descriptionView.text = resources.getString(R.string.description)
//
//            } else {
                val item = repoList[rowPos]

//                setContentBg(holder.nameView)
//                setContentBg(holder.dateView)
//                setContentBg(holder.descriptionView)

                holder.nameView.text = item.name
                holder.dateView.text = DateFormat.getDateInstance().format(item.updated_at)
                holder.descriptionView.text = item.description

//        }
        }
    }

    private fun setHeaderBg(view: View) {
        view.setBackgroundResource(R.drawable.table_header_cell_bg)
    }

    private fun setContentBg(view: View) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }
}