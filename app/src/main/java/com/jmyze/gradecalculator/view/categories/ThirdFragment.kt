package com.jmyze.gradecalculator.view.categories

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jmyze.gradecalculator.database.Grade
import com.jmyze.gradecalculator.R
import com.jmyze.gradecalculator.databinding.FragmentThirdBinding
import com.jmyze.gradecalculator.recyclerviews.GradeAdapter

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private lateinit var gradeRecyclerView: RecyclerView
    private var gradeArrayList: ArrayList<Grade> = ArrayList()
    private val adapter = GradeAdapter(gradeArrayList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        createGradeRecyclerView()

        binding.fab.setOnClickListener {
            openDialog()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openDialog() {
        val dialog = Dialog(this.requireContext())
        dialog.setContentView(R.layout.create_category_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val inputAssignment =
            dialog.findViewById<TextInputLayout>(R.id.dialog_input_category_name_layout)
        val inputGrade =
            dialog.findViewById<TextInputLayout>(R.id.dialog_input_category_weight_layout)
        val assignment = dialog.findViewById<TextInputEditText>(R.id.dialog_input_category_name)
        val grade = dialog.findViewById<TextInputEditText>(R.id.dialog_input_category_weight)

        inputAssignment.hint = "Assignment Name"
        inputGrade.hint = "Grade"

        dialog.findViewById<Button>(R.id.dialog_cancel_button).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.dialog_add_button).setOnClickListener {
            addGrade(Grade(assignment.text.toString(), grade.text.toString().toDouble()))
            dialog.dismiss()
        }

    }

    private fun createGradeRecyclerView() {
        gradeRecyclerView = binding.recyclerView
        gradeRecyclerView.layoutManager = LinearLayoutManager(context)
        gradeRecyclerView.setHasFixedSize(false)

        getGradeData(adapter)
    }

    private fun addGrade(grade: Grade) {
        gradeArrayList.add(grade)
        adapter.notifyItemInserted(gradeArrayList.size)
    }

    private fun getGradeData(adapter: GradeAdapter) {
        gradeRecyclerView.adapter = adapter
    }
}