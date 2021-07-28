package com.pogerapp.departments_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pogerapp.core.entity.user.Department
import com.pogerapp.departments_list.databinding.DepartmentItemViewBinding

class DepartmentsListAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val departments = ArrayList<Department>()

    fun setData(items: List<Department>) {
        departments.clear()
        departments.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DepartmentItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DepartmentViewHolder(binding).apply {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onClick(departments[adapterPosition].departmentId)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DepartmentViewHolder).bind(departments[position])
    }

    override fun getItemCount() = departments.size

    class DepartmentViewHolder(
        private val departmentViewBinding: DepartmentItemViewBinding
    ) : RecyclerView.ViewHolder(departmentViewBinding.root) {
        fun bind(department: Department) {
            departmentViewBinding.title.text = department.name
        }
    }
}