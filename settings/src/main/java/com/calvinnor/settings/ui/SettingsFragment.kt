package com.calvinnor.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.calvinnor.settings.R
import com.calvinnor.settings.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val viewBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        _binding = fragmentSettingsBinding
        return fragmentSettingsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addPreferencesFragment()
        setupBackPressListener()
    }

    private fun addPreferencesFragment() = childFragmentManager.commit {
        replace(R.id.fcvPreferences, SettingsPreferencesFragment())
    }

    private fun setupBackPressListener() {
        viewBinding.mtToolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
