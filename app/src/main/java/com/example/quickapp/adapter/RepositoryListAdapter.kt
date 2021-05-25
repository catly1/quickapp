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
import com.example.quickapp.ui.RepositoryList.RepositoryListFragmentDirections
import com.example.quickapp.ui.webview.WebViewFragment
import java.text.DateFormat

class RepositoryListAdapter(private val context: Context,  private val findNavController: NavController) : RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    var repoList: List<RepositoryListItem> = mutableListOf()

    fun update(fetchedRepoList: List<RepositoryListItem>){
        repoList = fetchedRepoList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameView : TextView = view.findViewById(R.id.name)
        val dateView : TextView = view.findViewById(R.id.updated_date)
        val descriptionView : TextView = view.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.table_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rowPos = holder.adapterPosition
        val item = repoList[rowPos]
        holder.itemView.setOnClickListener {
            val directions = RepositoryListFragmentDirections.actionRepositoryListFragmentToWebViewFragment(item.name, item.html_url)
            findNavController.navigate(directions)

//            --Custom tabs--
//            val builder = CustomTabsIntent.Builder()
//            AppCompatResources.getDrawable(context,R.drawable.baseline_arrow_back_white_24dp)?.let { it1 ->
//                builder.setCloseButtonIcon(
//                    it1.toBitmap())
//            }
//            val colorParams = CustomTabColorSchemeParams.Builder()
//                .setToolbarColor(context.resources.getColor(R.color.purple_500))
//                .build()
//            builder.setDefaultColorSchemeParams(colorParams)
//            builder.setShowTitle(true)
//            builder.setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
//            builder.setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right)
//            val intent = builder.build()
//            val headers = Bundle()
//            headers.putString("header1", "value1")
//            headers.putString("header2", "value2")
//            intent.intent.putExtra(Browser.EXTRA_HEADERS, headers)
//            intent.launchUrl(context, Uri.parse(item.html_url))
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