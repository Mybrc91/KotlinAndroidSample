package win.gdzrch.kotlinsample

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.list_item.*

/**
 * Created by w on 2017/5/24.
 */
class ListAdapter constructor(val ctx:Context ,val resid :Int,val list : List<String>) : BaseAdapter() {

    init {

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var valueView = convertView
        var viewHolder:ViewHolder

        if (valueView==null){
            valueView = View.inflate(ctx,resid,null)
            viewHolder = ViewHolder(valueView)
            valueView.setTag(viewHolder)
        }else{
            viewHolder = valueView.getTag() as ViewHolder
        }

        viewHolder.textview.text =  getItem(position)

        return valueView!!;
    }

    override fun getItem(position: Int) = list.get(position)

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = list.size

    class ViewHolder(view: View){
        init {
            ButterKnife.bind(this,view)
        }
        @BindView(R.id.text)
        lateinit var textview :TextView

    }
}