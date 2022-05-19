package com.jmyze.gradecalculator.view.grades

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jmyze.gradecalculator.database.CourseObject
import com.jmyze.gradecalculator.recyclerviews.CategoriesAdapter
import com.jmyze.gradecalculator.CoursesViewModel
import com.jmyze.gradecalculator.R
import com.jmyze.gradecalculator.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryRecyclerView: RecyclerView

    private val coursesViewModel: CoursesViewModel by activityViewModels()
    private lateinit var categoryArrayList: ArrayList<String>
    private lateinit var weightArrayList: ArrayList<Double>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

//        coursesViewModel.course.observe(viewLifecycleOwner) { course ->
//            binding.courseNameTextview.text = course.courseName
//            binding.infoviewInstructorName.text = course.instructor
//            binding.infoviewCourseCode.text = course.courseCode
//            val grade = course.getClassAverage().toString() + "%"
//            binding.infoviewGradePercentage.text = grade
//
//            categoryArrayList = course.gradeCategories
//            weightArrayList = course.categoryWeight
//            val adapter = CategoriesAdapter(categoryArrayList, weightArrayList)
//
//            for (i in 0 until categoryArrayList.size) {
//                addCategory(adapter, categoryArrayList[i], weightArrayList[i])
//            }
//            createCategoryRecyclerView(adapter)
//
//            binding.fab.setOnClickListener {
//                openDialog(adapter, course)
//            }
//        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openDialog(adapter: CategoriesAdapter, course: CourseObject) {
        val dialog = Dialog(this.requireContext())
        dialog.setContentView(R.layout.create_category_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val categoryName = dialog.findViewById<TextView>(R.id.dialog_input_category_name)
        val categoryWeight = dialog.findViewById<TextView>(R.id.dialog_input_category_weight)

        dialog.findViewById<Button>(R.id.dialog_cancel_button).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.dialog_add_button).setOnClickListener {
            addCategory(
                adapter,
                categoryName.text.toString(),
                categoryWeight.text.toString().toDouble()
            )
            dialog.dismiss()
        }
    }

    private fun createCategoryRecyclerView(adapter: CategoriesAdapter) {
        categoryRecyclerView = binding.recyclerview
        categoryRecyclerView.layoutManager = LinearLayoutManager(context)
        categoryRecyclerView.setHasFixedSize(false)

        getCourseData(adapter)
    }

    private fun addCategory(adapter: CategoriesAdapter, category: String, weight: Double) {
        categoryArrayList.add(category)
        weightArrayList.add(weight)
        adapter.notifyItemInserted(categoryArrayList.size)
    }

    private fun getCourseData(adapter: CategoriesAdapter) {
        categoryRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : CategoriesAdapter.OnItemClickListener {
            override fun onItemClick(
                position: Int,
                gradeCategories: ArrayList<String>,
                categoryWeight: ArrayList<Double>
            ) {
                findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment)
            }
        })
    }
}