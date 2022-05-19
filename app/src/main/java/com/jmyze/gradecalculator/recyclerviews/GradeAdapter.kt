package com.jmyze.gradecalculator.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jmyze.gradecalculator.database.Grade
import com.jmyze.gradecalculator.ListColors
import com.jmyze.gradecalculator.R
import com.jmyze.gradecalculator.view.MainActivity

class GradeAdapter(private var gradeList: ArrayList<Grade>) :
    RecyclerView.Adapter<GradeAdapter.GradeViewHolder>() {

    inner class GradeViewHolder(gradeView: View) :
        RecyclerView.ViewHolder(gradeView) {
        val itemContainer: CardView = gradeView.findViewById(R.id.item_container)
        val leftHeader: TextView = gradeView.findViewById(R.id.left_header)
        val rightHeader: TextView = gradeView.findViewById(R.id.right_header)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val courseView =
            LayoutInflater.from(parent.context).inflate(R.layout.grade_list, parent, false)
        return GradeViewHolder(courseView)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val currentGrade = gradeList[position]

        holder.itemContainer.setCardBackgroundColor(MainActivity.intToColor(ListColors.ORANGE.colorID))
        holder.leftHeader.text = currentGrade.assignment
        val grade = currentGrade.grade.toString() + "%"
        holder.rightHeader.text = grade
    }

    override fun getItemCount(): Int {
        return gradeList.size
    }
}
