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

class ColombiaPhrasesActivity : AppCompatActivity() {
    private lateinit var listview: ListView
    var arrayList: ArrayList<PharsesData> = ArrayList()
    var adapter: ColombiaPhrasesActivity.MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colombia_phrases)

        listview = findViewById(R.id.list_items)
        arrayList.add(
            PharsesData(
                getString(R.string.ColombiaPhraseOne),
                getString(R.string.ColombiaExplainOne)
            )
        )
        arrayList.add(
            PharsesData(
                getString(R.string.ColombiaPhraseTwo),
                getString(R.string.ColombiaExplainTwo)
            )
        )
        arrayList.add(
            PharsesData(
                getString(R.string.ColombiaPhraseThree),
                getString(R.string.ColombiaExplainThree)
            )
        )
        arrayList.add(
            PharsesData(

                getString(R.string.ColombiaPhraseFour),
                getString(R.string.ColombiaExplainFour)
            )
        )
        arrayList.add(
            PharsesData(

                getString(R.string.ColombiaPhraseFive),
                getString(R.string.ColombiaExplainFive)
            )
        )
        arrayList.add(
            PharsesData(

                getString(R.string.ColombiaPhraseSix),
                getString(R.string.ColombiaExplainSix)
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