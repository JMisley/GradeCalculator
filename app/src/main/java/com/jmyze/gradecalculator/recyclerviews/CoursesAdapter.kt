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

class CoursesAdapter(var coursesList: List<CourseObject>) :
    RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder>() {

    inner class CoursesViewHolder(courseView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(courseView) {
        val itemContainer: CardView = courseView.findViewById(R.id.item_container)
        val courseName: TextView = courseView.findViewById(R.id.left_header)
        val grade: TextView = courseView.findViewById(R.id.right_header)
        val instructor: TextView = courseView.findViewById(R.id.left_caption)
        val courseCode: TextView = courseView.findViewById(R.id.right_caption)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(
                    adapterPosition,
                    CourseObject(
                        courseName.text.toString(),
                        ListColors.BLACK.colorID,
                        grade.text.toString().toDouble(),
                        instructor.text.toString(),
                        courseCode.text.toString()
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
            holder.instructor.visibility = View.GONE
        if (currentCourse.courseCode.isBlank())
            holder.courseCode.visibility = View.GONE

        holder.itemContainer.setCardBackgroundColor(MainActivity.intToColor(currentCourse.colorID))
        holder.courseName.text = currentCourse.courseName
        holder.grade.text = grade
        holder.instructor.text = currentCourse.instructor
        holder.courseCode.text = currentCourse.courseCode
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }
}
