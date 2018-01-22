package sk.styk.martin.apkanalyzer.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_detail_list_item.view.*
import sk.styk.martin.apkanalyzer.R
import sk.styk.martin.apkanalyzer.activity.dialog.InfoDialog

/**
 * View used inside cardview - representing key value pair placed in cardview creating list of pairs
 *
 * @author Martin Styk
 * @version 06.07.2017.
 */
class DetailListItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs, R.attr.detailListItemViewStyle), View.OnClickListener {

    var titleText: String = ""
        set(value) {
            field = value;
            attribute_name.text = value
        }
    var valueText: String? = null
        set(value) {
            if (!value.isNullOrBlank()) {
                field = value
                attribute_value.text = value
            }
        }
    var descriptionText: String

    init {
        LayoutInflater.from(context).inflate(R.layout.view_detail_list_item, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DetailItemView, 0, 0)
        titleText = attributes.getString(R.styleable.DetailItemView_titleText)
        valueText = attributes.getString(R.styleable.DetailItemView_valueText)
        descriptionText = attributes.getString(R.styleable.DetailItemView_descriptionText)
        attributes.recycle()

        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        setOnClickListener(this)
    }

    override fun onClick(v: View) {
        InfoDialog.newInstance(titleText, valueText ?: "", descriptionText)
                .show((context as AppCompatActivity).supportFragmentManager, InfoDialog::class.java.simpleName)
    }

}