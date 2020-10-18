package com.calvinnor.settings.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.calvinnor.settings.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackPressListener()
    }

    private fun setupBackPressListener() {
        mtToolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }
}
