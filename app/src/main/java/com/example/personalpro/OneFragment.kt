package com.example.personalpro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalpro.databinding.FragmentOneBinding
import com.example.personalpro.databinding.UserPlusBinding

class OneFragment : Fragment(),View.OnClickListener {
    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentOneBinding
    lateinit var dataList: MutableList<DataList>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater)
        binding.eftb.setOnClickListener(this)
        dataList = mutableListOf()
        for(i in 1..30){
            if(i % 2 == 0){
                dataList.add(DataList("서종혁${i}", "${20+i}세", "jd${i}@nate.com", R.drawable.man))
            }else{
                dataList.add(DataList("이정호${i}", "${20+i}세", "jd${i}@nate.com", R.drawable.man))
            }
        }

        val customRecycleAdapter = CustomRecycleAdapter(dataList)
        binding.recyclerView.adapter = customRecycleAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        return binding.root



    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.eftb -> {
                var userPlusBinding : UserPlusBinding
                val dialogBuilder = AlertDialog.Builder(requireContext())
                var userDialog: AlertDialog
                userPlusBinding = UserPlusBinding.inflate(layoutInflater)
                dialogBuilder.setTitle("사용자 추가 입력")
                dialogBuilder.setView(userPlusBinding.root)
                userDialog = dialogBuilder.create()
                userDialog.show()
                userPlusBinding.edtName.text.toString()
                userPlusBinding.edtAge.text.toString()
                userPlusBinding.edtEmail.text.toString()

                userPlusBinding.btnCancel.setOnClickListener {
                    userDialog.dismiss()
                }
                userPlusBinding.btnOk.setOnClickListener {
                    val newData = DataList(
                        userPlusBinding.edtName.text.toString(),
                        userPlusBinding.edtAge.text.toString(),
                        userPlusBinding.edtEmail.text.toString(),
                        R.drawable.man

                    )
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                    dataList.add(newData)
                    Toast.makeText(requireContext(), "사용자가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    userDialog.dismiss()
                }
            }
        }
    }

}