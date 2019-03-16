package com.example.demoworkmanager

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.AutoCompleteTextView

class DialogOpen(val mContext: Context) : Dialog(mContext) {

    var themeResID: Int? = null

    constructor(mContext: Context, themeResID: Int) : this(mContext) {
        this.themeResID = themeResID
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_new)
        if (themeResID != null) {

        }

        val actv_data = findViewById<AutoCompleteTextView>(R.id.actv_data)

        val adpter = CustomAdapter(mContext, prepareList())

        actv_data.setAdapter(adpter)
        actv_data?.threshold=1
        actv_data?.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adpter.filter.filter(s.toString())
            }
        })
        actv_data?.setOnTouchListener(object :View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                actv_data?.showDropDown()
                return false
            }
        })

    }

    private fun prepareList(): ArrayList<DataItem> {
        val list = ArrayList<DataItem>()
        for (i in 65..90)
            list.add(DataItem(i.toChar() + "Name " + i, i))
        return list
    }
}
