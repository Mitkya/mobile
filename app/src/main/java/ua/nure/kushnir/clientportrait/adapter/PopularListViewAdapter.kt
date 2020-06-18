package ua.nure.kushnir.clientportrait.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ua.nure.kushnir.clientportrait.R
import ua.nure.kushnir.clientportrait.model.entity.PopularProduct

class PopularListViewAdapter(context: Context, popularProducts: List<PopularProduct>) :
    BaseAdapter() {

    private val mContext: Context = context
    private val mPopularProducts: List<PopularProduct> = popularProducts

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowListView: View = layoutInflater.inflate(R.layout.row, parent, false)
        val titleInfoText: TextView = rowListView.findViewById(R.id.titleInfoText)
        val costInfoText: TextView = rowListView.findViewById(R.id.costInfoText)
        val wasBoughtText: TextView = rowListView.findViewById(R.id.wasBoughtInfoText)
        titleInfoText.text = mPopularProducts[position].title
        costInfoText.text = mPopularProducts[position].cost.toString()
        wasBoughtText.text = mPopularProducts[position].wasBought.toString()
        return rowListView
    }

    override fun getItem(position: Int): Any {
        return mPopularProducts[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mPopularProducts.size
    }

}