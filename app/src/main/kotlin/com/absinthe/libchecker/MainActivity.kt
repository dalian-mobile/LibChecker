package com.absinthe.libchecker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.absinthe.libchecker.databinding.ActivityMainBinding
import com.absinthe.libchecker.ui.applist.AppListFragment
import com.absinthe.libchecker.ui.classify.ClassifyFragment
import com.absinthe.libchecker.ui.settings.SettingsFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> AppListFragment()
                    1 -> ClassifyFragment()
                    2 -> SettingsFragment()
                    else -> AppListFragment()
                }
            }
        }

        // 当ViewPager切换页面时，改变底部导航栏的状态
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.navView.menu.getItem(position).isChecked = true
            }
        })

        // 当ViewPager切换页面时，改变ViewPager的显示
        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_app_list -> binding.viewpager.setCurrentItem(0, true)
                R.id.navigation_classify -> binding.viewpager.setCurrentItem(1, true)
                R.id.navigation_settings -> binding.viewpager.setCurrentItem(2, true)
            }
            true
        }
    }
}
