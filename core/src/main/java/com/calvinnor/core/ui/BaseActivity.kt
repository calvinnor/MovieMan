package com.calvinnor.core.ui

import android.os.Bundle
import android.view.Menu
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Base Activity to inherit from.
 * All common code and abstraction layer goes in here.
 */
abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()

        // Add the root fragment only on first launch
        if (savedInstanceState == null) {
            addRootFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (menuLayout != NO_LAYOUT) menuInflater.inflate(menuLayout, menu)
        return true
    }

    /**
     * Override this value to provide a Menu layout.
     *
     * @return The Menu resource ID.
     */
    @MenuRes
    protected open val menuLayout = NO_LAYOUT

    private fun setupToolbar() {
        title = toolbarTitle
    }

    /**
     * Override to add the root fragment during onCreate.
     * Only called when savedInstanceState is null.
     */
    protected fun addRootFragment() {
        // NO-OP
    }

    /**
     * Override this value to provide a Toolbar title.
     */
    protected open val toolbarTitle: String = ""

    companion object {
        private const val NO_LAYOUT = 0
    }
}
