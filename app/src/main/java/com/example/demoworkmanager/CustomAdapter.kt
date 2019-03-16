package com.example.demoworkmanager

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import android.view.LayoutInflater


class CustomAdapter(val context: Context, val list: ArrayList<DataItem>) : BaseAdapter(), Filterable {
    var mStringFilterList=ArrayList<DataItem>()

    init {
        mStringFilterList.addAll(list)
    }
    override fun getFilter(): Filter {
        return object : Filter() {

            override fun convertResultToString(resultValue: Any?): CharSequence {
                if (resultValue is DataItem){
                return resultValue.name
                }
                return ""
            }
            override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {

                var results = Filter.FilterResults()
                if (constraint != null && constraint.isNotEmpty()) {
                    val newfilterList =  ArrayList<DataItem>()
                    val conSer=constraint.toString().toLowerCase()
                        for (data in mStringFilterList){
                            if (data.name.toLowerCase().contains(conSer)){
                                newfilterList.add(data)
                            }

                        }
                    results.count = newfilterList.size
                    results.values = newfilterList
                }else{
                    results.count = mStringFilterList.size
                    results.values = mStringFilterList
                }

                return results
            }

            override fun publishResults(
                constraint: CharSequence?,
                results: Filter.FilterResults
            ) {
                if (results.count > 0) {
                    list.clear()
                    list.addAll(results.values as ArrayList<DataItem>)
                    notifyDataSetChanged()
                }
                else
                    notifyDataSetInvalidated()
            }
        }

    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflter = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var convertView = convertView
        convertView = inflter.inflate(R.layout.row_item, null)//set layout for displaying items
        val text = convertView.findViewById(R.id.text_name) as TextView//get id for image view
        text.setText(list.get(position).name)
        return convertView
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return list.get(position).id.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}