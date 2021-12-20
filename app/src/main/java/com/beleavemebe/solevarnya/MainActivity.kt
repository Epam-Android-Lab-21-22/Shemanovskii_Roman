package com.beleavemebe.solevarnya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.core.view.forEach
import androidx.fragment.app.*
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.BaseFragment.*
import com.beleavemebe.solevarnya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(binding.mainToolbar)

        initBottomNav()
        if (savedInstanceState == null) {
            switchToFragment(::FirstFragment)
        }
    }

    private fun initBottomNav() {
        binding.bottomNavView.menu.forEach { item ->
            item.setOnMenuItemClickListener(::onBottomNavItemClicked)
        }
    }

    private fun onBottomNavItemClicked(item: MenuItem?): Boolean {
        item?.isChecked = true
        return when (item?.itemId) {
            R.id.menu_item_fragment_first -> switchToFragment(::FirstFragment)
            R.id.menu_item_fragment_second -> switchToFragment(::SecondFragment)
            R.id.menu_item_fragment_third -> switchToFragment(::ThirdFragment)
            else -> false
        }
    }

    private fun switchToFragment(createFragment: () -> BaseFragment): Boolean {
        val newFragment = createFragment()

        val currentStackTag = getCurrentTag()
        val targetStackTag = getTagFor(newFragment)

        supportFragmentManager.run {
            currentStackTag?.let {
                saveBackStack(it)
            }

            commit {
                setReorderingAllowed(true)
                replace(R.id.container, newFragment)
            }

            restoreBackStack(targetStackTag)
        }
        return true
    }

    fun onFragmentClicked(
        fragment: BaseFragment,
        stackPosition: Int
    ) = layNewFragment(fragment, stackPosition + 1)

    private fun layNewFragment(fragment: BaseFragment, layer: Int) {
        supportFragmentManager.commit {
            val currentStackTag = getCurrentTag()
            addToBackStack(currentStackTag)
            setReorderingAllowed(true)
            val args = bundleOf(BaseFragment.KEY_STACK_POSITION to layer)
            add(R.id.container, fragment::class.java, args, null)
        }
        incLabelOf(fragment)
    }

    override fun onBackPressed() {
        currentFragment?.let {
            decLabelOf(it)
        }
        super.onBackPressed()
    }

    private fun getCurrentTag(): String? =
        currentFragment?.let {
            getTagFor(it)
        }

    fun getTagFor(fragment: BaseFragment): String =
        when (fragment) {
            is FirstFragment -> getString(R.string.first_fragment)
            is SecondFragment -> getString(R.string.second_fragment)
            is ThirdFragment -> getString(R.string.third_fragment)
        }

    private fun incLabelOf(fragment: BaseFragment) = updateLabel(fragment, 1)
    private fun decLabelOf(fragment: BaseFragment) = updateLabel(fragment, -1)

    private fun updateLabel(fragment: BaseFragment, change: Int) {
        @IdRes val menuItemId =
            when (fragment) {
                is FirstFragment -> R.id.menu_item_fragment_first
                is SecondFragment -> R.id.menu_item_fragment_second
                is ThirdFragment -> R.id.menu_item_fragment_third
            }
        val badge = binding.bottomNavView.getOrCreateBadge(menuItemId)
        badge.number += change
        badge.isVisible = badge.number > 0
    }

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.run {
            val size = backStackEntryCount

            if (size == 0)
                null
            else
                fragments[size - 1] as BaseFragment
        }
}
