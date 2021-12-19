package com.beleavemebe.solevarnya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.core.view.forEach
import androidx.fragment.app.*
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.databinding.ActivityMainBinding
import java.lang.IllegalStateException
import java.lang.IndexOutOfBoundsException

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(binding.mainToolbar)

        initBottomNav()
        if (savedInstanceState == null) {
            replaceCurrentFragmentWith<FragmentA>()
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
            R.id.menu_item_fragment_a -> replaceCurrentFragmentWith<FragmentA>()
            R.id.menu_item_fragment_b -> replaceCurrentFragmentWith<FragmentB>()
            R.id.menu_item_fragment_c -> replaceCurrentFragmentWith<FragmentC>()
            else -> false
        }
    }

    private inline fun <reified F : BaseFragment> replaceCurrentFragmentWith(): Boolean {
        with(supportFragmentManager) {
            val currentBackStackTag = getCurrentBackStackTag()
            if (currentBackStackTag != null) {
                Log.d("MainActivity", "Saving $currentBackStackTag's backstack with $backStackEntryCount entries")
                saveBackStack(currentBackStackTag)
            }

            commit {
                setReorderingAllowed(true)
                replace<F>(R.id.container)
            }

            val targetBackStackTag = getBackStackTagFor<F>()
            restoreBackStack(targetBackStackTag)
            Log.d("MainActivity", "Restored $backStackEntryCount entries from backstack $targetBackStackTag")

            onBackPressedDispatcher.addCallback(this@MainActivity) {
                isEnabled = false
                super.onBackPressed()
                updateLabelOf<F>()
                isEnabled = true
            }
        }
        return true
    }

    inline fun <reified F : BaseFragment> deepenCurrentFragment(nextDepth: Int) {
        with(supportFragmentManager) {
            val currBackStackTag = getCurrentBackStackTag()
            val args = bundleOf(BaseFragment.KEY_STACK_POSITION to nextDepth)
            updateLabelOf<F>()
            commit {
                addToBackStack(currBackStackTag)
                setReorderingAllowed(true)
                add<F>(R.id.container, null, args)
            }
        }
    }

    inline fun <reified F : BaseFragment> updateLabelOf() =
        when (F::class) {
            FragmentA::class -> updateLabel(R.id.menu_item_fragment_a)
            FragmentB::class -> updateLabel(R.id.menu_item_fragment_b)
            FragmentC::class -> updateLabel(R.id.menu_item_fragment_c)
            else -> throw IllegalStateException(
                "Missing branch for ${F::class.java.simpleName}"
            )
        }

    fun updateLabel(@IdRes menuItemIdRes: Int) {
        val badge = binding.bottomNavView.getOrCreateBadge(menuItemIdRes)
        val count: Int = supportFragmentManager.backStackEntryCount + 1
        badge.isVisible = count > 0
        badge.number = count
    }

    fun FragmentManager.getCurrentBackStackTag(): String? =
        try {
            val fragment: Fragment = fragments[0]
            fragment::class.java.simpleName
        } catch (e: IndexOutOfBoundsException) {
            Log.e("MainActivity", "Empty back stack", e)
            null
        }

    private inline fun <reified F : Fragment> getBackStackTagFor(): String =
        F::class.java.simpleName
}