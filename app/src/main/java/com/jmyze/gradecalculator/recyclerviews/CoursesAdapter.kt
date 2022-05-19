package com.jmyze.gradecalculator.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jmyze.gradecalculator.database.CourseObject
import com.jmyze.gradecalculator.ListColors
import com.jmyze.gradecalculator.R
import com.jmyze.gradecalculator.view.MainActivity

class CoursesAdapter(private var coursesList: ArrayList<CourseObject>) :
    RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder>() {

    inner class CoursesViewHolder(courseView: View, listener: OnItemClickListener) :
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
                    CourseObject(
                        0,
                        ListColors.BLACK.colorID,
                        leftHeader.text.toString(),
                        rightHeader.text.toString().toDouble(),
                        leftCaption.text.toString(),
                        rightCaption.text.toString()
                    )
                )
            }
        }
    }

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int, courseObject: CourseObject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val courseView =
            LayoutInflater.from(parent.context).inflate(R.layout.course_list, parent, false)
        return CoursesViewHolder(courseView, listener)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        val currentCourse = coursesList[position]
        val grade = currentCourse.gradePercentage.toString()

        if (currentCourse.instructor.isBlank())
            holder.leftCaption.visibility = View.GONE
        if (currentCourse.courseCode.isBlank())
            holder.rightCaption.visibility = View.GONE

        holder.itemContainer.setCardBackgroundColor(MainActivity.intToColor(currentCourse.colorID))
        holder.leftHeader.text = currentCourse.courseName
        holder.rightHeader.text = grade
        holder.leftCaption.text = currentCourse.instructor
        holder.rightCaption.text = currentCourse.courseCode
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }
}
