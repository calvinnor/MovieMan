package com.calvinnor.core.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding

/**
 * Base Fragment to inherit from.
 * All common code and abstraction goes here.
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val viewBinding get() = _binding!!

    companion object {
        private const val NO_LAYOUT = 0
    }

    protected val contextNonNull: Context
        get() = requireContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) loadDependencies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val inflatedBinding = inflateView(inflater, container)
        _binding = inflatedBinding
        return inflatedBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(menu != NO_LAYOUT)
        if (savedInstanceState != null) onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (this.menu == NO_LAYOUT) return
        inflater.inflate(this.menu, menu)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    /**
     * Override to inflate with a View Binding.
     */
    abstract fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ): VB

    /**
     * Override this value to provide a Menu.
     */
    @MenuRes protected open val menu = NO_LAYOUT

    /**
     * Override this value to provide a Refresh Indicator
     */
    protected open val refreshIndicator: SwipeRefreshLayout? = null

    /**
     * Override this value to provide a fragment tag.
     * This will be used in Fragment Transactions.
     */
    abstract val fragmentTag: String

    /**
     * Override this method to get Saved State after onViewCreated()
     */
    open fun onRestoreInstanceState(savedInstanceState: Bundle) {}

    /**
     * Override this method to load Dependency Modules.
     *
     * Only called once in the Persistent Lifecycle.
     */
    open fun loadDependencies() {}

}
