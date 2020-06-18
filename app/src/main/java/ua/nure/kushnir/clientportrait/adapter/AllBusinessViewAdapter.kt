package ua.nure.kushnir.clientportrait.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ua.nure.kushnir.clientportrait.R
import ua.nure.kushnir.clientportrait.model.entity.Business

class AllBusinessViewAdapter(context: Context, businesses: List<Business>) : BaseAdapter() {

    private val mContext = context
    private val mBusinesses = businesses

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val allBusinessesListView: View =
            layoutInflater.inflate(R.layout.allbusinessrows, parent, false)
        val titleTextVIew: TextView = allBusinessesListView.findViewById(R.id.titleInfoText)
        titleTextVIew.text = mBusinesses[position].title
        return allBusinessesListView
    }

    override fun getItem(position: Int): Any {
        return mBusinesses[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mBusinesses.size
    }

}