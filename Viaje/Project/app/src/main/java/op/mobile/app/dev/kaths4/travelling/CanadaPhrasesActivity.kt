package op.mobile.app.dev.kaths4.travelling

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import op.mobile.app.dev.kaths4.travelling.model.PharsesData

class CanadaPhrasesActivity : AppCompatActivity() {
    private lateinit var listview: ListView
    var arrayList: ArrayList<PharsesData> = ArrayList()
    var adapter: CanadaPhrasesActivity.MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canada_phrases)

        listview = findViewById(R.id.list_items)
        arrayList.add(
            PharsesData(
                getString(R.string.CanadaPhraseOne),
                getString(R.string.CanadaExplainOne)
            )
        )
        arrayList.add(
            PharsesData(
                getString(R.string.CanadaPhraseTwo),
                getString(R.string.CanadaExplainTwo)
            )
        )
        arrayList.add(
            PharsesData(
                getString(R.string.CanadaPhraseThree),
                getString(R.string.CanadaExplainThree)
            )
        )
        arrayList.add(
            PharsesData(

                getString(R.string.CanadaPhraseFour),
                getString(R.string.CanadaExplainFour)
            )
        )
        arrayList.add(
            PharsesData(

                getString(R.string.CanadaPhraseFive),
                getString(R.string.CanadaExplainFive)
            )
        )
        arrayList.add(
            PharsesData(

                getString(R.string.CanadaPhraseSix),
                getString(R.string.CanadaExplainSix)
            )
        )
        adapter = MyAdapter(this, arrayList)
        listview.adapter = adapter
    }

    class MyAdapter(
        private val context: Context,
        private val arrayList: java.util.ArrayList<PharsesData>
    ) : BaseAdapter() {
        private lateinit var phrasesone: TextView
        private lateinit var explanation: TextView

        override fun getCount(): Int {
            return arrayList.size
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        @SuppressLint("SetTextI18n", "ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            var convertView = convertView
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.phrases_list_view_items, parent, false)
            phrasesone = convertView.findViewById(R.id.txt_phrases1)
            explanation = convertView.findViewById(R.id.txt_explanation)
            phrasesone.text = "" + arrayList[position].pharses
            explanation.text = "" + arrayList[position].explanation
            return convertView
        }
    }
}