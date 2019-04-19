package com.calvinnor.core.ui

import android.os.Bundle
import android.view.Menu
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction

/**
 * Base Activity to inherit from.
 * All common code and abstraction layer goes in here.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupLayout()
        setupToolbar()

        // Add the root fragment only on first launch
        if (savedInstanceState == null) {
            addRootFragment()
        }

        this.fragment?.let {
            setupFragment(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (menuLayout != NO_LAYOUT) menuInflater.inflate(menuLayout, menu)
        return true
    }

    /**
     * Replace the fragment present in provided container.
     *
     * @param containerId The container to replace.
     * @param fragment    The fragment to place.
     */
    protected fun replaceFragment(@IdRes containerId: Int, fragment: BaseFragment, addToBackStack: Boolean) {
        supportFragmentManager.transaction {
            replace(containerId, fragment, fragment.fragmentTag)
            if (addToBackStack) addToBackStack(null)
        }
    }

    /**
     * Override this value to provide an activity layout.
     *
     * @return The layout resource ID.
     */
    @LayoutRes
    protected open val contentLayout = NO_LAYOUT

    /**
     * Override this value to provide a root fragment.
     *
     * @return The BaseFragment instance to inflate.
     */
    protected open var fragment: BaseFragment? = null

    /**
     * Override this value to provide the fragment container ID.
     *
     * @return An IdRes representing the container to place the root fragment.
     */
    @IdRes
    protected open val fragmentContainer = NO_LAYOUT

    /**
     * Override this value to provide a Menu layout.
     *
     * @return The Menu resource ID.
     */
    @MenuRes
    protected open val menuLayout = NO_LAYOUT

    private fun setupLayout() {
        if (contentLayout == NO_LAYOUT) return
        setContentView(contentLayout)
    }

    private fun setupToolbar() {
        title = toolbarTitle
    }

    private fun addRootFragment() {
        if (contentLayout == NO_LAYOUT) return
        val fragment = fragment ?: return

        replaceFragment(fragmentContainer, fragment, false)
    }

    /**
     * Override this value to provide a Toolbar title.
     */
    protected open val toolbarTitle: String = ""

    /**
     * Override this method to configure a Fragment after add.
     */
    protected open fun setupFragment(fragment: BaseFragment) {}

    companion object {
        private const val NO_LAYOUT = -1
    }
}
