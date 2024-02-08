package com.codeinfinity.spoorstask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private Context mContext;
    private List<Employee> mEmployeeList;

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        mContext = context;
        mEmployeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = mEmployeeList.get(position);
        holder.bind(employee);
    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private TextView mAddressTextView;
        private TextView mDepartmentTextView;
        private TextView mGenderTextView;
        private TextView mFresherTextView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.tvName);
            mAddressTextView = itemView.findViewById(R.id.tvAddress);
            mDepartmentTextView = itemView.findViewById(R.id.tvDepartment);
            mGenderTextView = itemView.findViewById(R.id.tvGender);
            mFresherTextView = itemView.findViewById(R.id.tvFresher);
        }

        public void bind(Employee employee) {
            mNameTextView.setText(employee.getName());
            mAddressTextView.setText(employee.getAddress());
            mDepartmentTextView.setText(employee.getDepartment());
            mGenderTextView.setText(employee.getGender());
            mFresherTextView.setText(employee.isFresher() ? "Fresher" : "Experienced");
        }
    }
}

