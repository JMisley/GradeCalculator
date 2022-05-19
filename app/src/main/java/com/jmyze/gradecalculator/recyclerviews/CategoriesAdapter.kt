package com.jmyze.gradecalculator.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jmyze.gradecalculator.ListColors
import com.jmyze.gradecalculator.R
import com.jmyze.gradecalculator.view.MainActivity

class CategoriesAdapter(
    private var categoryList: ArrayList<String>,
    private var weightList: ArrayList<Double>
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    inner class CategoriesViewHolder(courseView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(courseView) {
        val itemContainer: CardView = courseView.findViewById(R.id.item_container)
        val leftHeader: TextView = courseView.findViewById(R.id.left_header)
        val rightHeader: TextView = courseView.findViewById(R.id.right_header)
        val leftCaption: TextView = courseView.findViewById(R.id.left_caption)
        val rightCaption: TextView = courseView.findViewById(R.id.right_caption)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(
                    adapterPosition,
                    categoryList,
                    weightList
                )
            }
        }
    }

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(
            position: Int,
            gradeCategories: ArrayList<String>,
            categoryWeight: ArrayList<Double>
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val courseView =
            LayoutInflater.from(parent.context).inflate(R.layout.course_list, parent, false)
        return CategoriesViewHolder(courseView, listener)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.leftCaption.visibility = View.GONE
        holder.rightCaption.visibility = View.GONE

        holder.itemContainer.setCardBackgroundColor(MainActivity.intToColor(ListColors.ORANGE.colorID))
        holder.leftHeader.text = categoryList[position]
        holder.rightHeader.text = weightList[position].toString()
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}
