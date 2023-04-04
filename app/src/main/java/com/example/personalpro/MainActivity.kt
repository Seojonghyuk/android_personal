package com.example.personalpro

import ThreeFragment
import TwoFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.personalpro.databinding.ActivityMainBinding
import com.example.personalpro.databinding.UserPlusBinding
import com.example.personalpro.databinding.UsertabButtonBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var customViewPagerAdapter: CustomViewPagerAdapter
    lateinit var tabTitleList: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCallActivity2.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("name", "홍길동")
            intent.putExtra("age", 26)
            startActivity(intent)
        }







        customViewPagerAdapter = CustomViewPagerAdapter(this)
        customViewPagerAdapter.addListFragment(OneFragment())
        customViewPagerAdapter.addListFragment(TwoFragment())
        customViewPagerAdapter.addListFragment(ThreeFragment())
        tabTitleList = mutableListOf("", "", "")
        binding.viewpager2.adapter = customViewPagerAdapter


        TabLayoutMediator(binding.tablayout, binding.viewpager2) { tab, position ->
            tab.text = tabTitleList.get(position)
            tab.setCustomView(tabCustomView(position))
        }.attach()



    }


    fun tabCustomView(position: Int): View {
        val binding = UsertabButtonBinding.inflate(layoutInflater)
        when (position) {
            0 -> binding.ivIcon.setImageResource(R.drawable.camera_24)
            1 -> binding.ivIcon.setImageResource(R.drawable.nu)
            2 -> binding.ivIcon.setImageResource(R.drawable.loop)
        }
        binding.tvTabName.text = tabTitleList.get(position)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_menu, menu)
        val searchMenuItem = menu?.findItem(R.id.menu_search)
        val searchView = searchMenuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{


            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, "${query}", Toast.LENGTH_SHORT).show()
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
//                Log.e("MainActivity", "${newText}")
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu1 -> Toast.makeText(applicationContext, "${item.title}클릭", Toast.LENGTH_SHORT).show()
            R.id.menu2 -> Toast.makeText(applicationContext, "${item.title}클릭", Toast.LENGTH_SHORT).show()
            R.id.menu3 -> Toast.makeText(applicationContext, "${item.title}클릭", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(applicationContext, "잘못클릭함", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}