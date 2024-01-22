import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.cursolinkedin.R

class MyListAdapter(private val dataList: MutableList<String>) :
    RecyclerView.Adapter<MyListAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = dataList[position]
    }

    override fun getItemCount() = dataList.size

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        val prev = dataList.removeAt(fromPosition)
        dataList.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, prev)
        notifyItemMoved(fromPosition, toPosition)
    }
}
