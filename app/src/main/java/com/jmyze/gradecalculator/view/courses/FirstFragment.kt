package com.jmyze.gradecalculator.view.courses

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jmyze.gradecalculator.*
import com.jmyze.gradecalculator.database.*
import com.jmyze.gradecalculator.databinding.FragmentFirstBinding
import com.jmyze.gradecalculator.recyclerviews.CoursesAdapter

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var courseRecyclerView: RecyclerView
    private var courseArrayList: ArrayList<CourseObject> = arrayListOf()

    private lateinit var coursesViewModel: CoursesViewModel
    private lateinit var courseAdapter: CoursesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        courseAdapter = CoursesAdapter(courseArrayList)
        createCourseRecyclerView()

        val courseRepository = CourseRepository(CourseDatabase(this.requireContext()))
        val viewModelFactory = CoursesViewModelFactory(courseRepository)
        coursesViewModel =
            ViewModelProvider(this, viewModelFactory)[CoursesViewModel::class.java]

        coursesViewModel.getAllCourses().observe(viewLifecycleOwner) {
            courseAdapter.coursesList = it
            courseAdapter.notifyItemRangeInserted(0, it.size)
        }

        binding.fab.setOnClickListener {
            openDialog()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createCourseRecyclerView() {
        courseRecyclerView = binding.recyclerView
        courseRecyclerView.layoutManager = LinearLayoutManager(context)
        courseRecyclerView.setHasFixedSize(true)

        getCourseData()
    }

    private fun openDialog() {
        val dialog = Dialog(this.requireContext())
        dialog.setContentView(R.layout.create_course_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val courseName = dialog.findViewById<TextView>(R.id.dialog_input_course)
        val courseCode = dialog.findViewById<TextView>(R.id.dialog_input_course_code)
        val courseInstructor = dialog.findViewById<TextView>(R.id.dialog_input_instructor)
        val colorButton = dialog.findViewById<Button>(R.id.dialog_choose_color_button)
        var selectedColorID = ListColors.BLACK.colorID

        dialog.findViewById<Button>(R.id.dialog_cancel_button).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.dialog_add_button).setOnClickListener {
            addCourse(
                CourseObject(
                    courseName.text.toString(),
                    selectedColorID,
                    0.0,
                    courseInstructor.text.toString(),
                    courseCode.text.toString()
                )
            )
            dialog.dismiss()
        }

        var clickCount = 0
        colorButton.setOnClickListener {
            clickCount = (clickCount + 1) % ListColors.values().size
            colorButton.setBackgroundColor(
                ContextCompat.getColor(
                    this.requireContext(), ListColors.values()[clickCount].colorID
                )
            )
            selectedColorID = ListColors.values()[clickCount].colorID
        }
    }

    private fun addCourse(course: CourseObject) {
        coursesViewModel.insert(course)
        courseAdapter.notifyItemInserted(courseArrayList.size)
    }

    private fun getCourseData() {
        courseRecyclerView.adapter = courseAdapter
        courseAdapter.setOnItemClickListener(object : CoursesAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, courseObject: CourseObject) {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        })
    }
}