package com.softsquared.template.kotlin.src.main.home.bottom_sheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.login.LoginActivity
import com.softsquared.template.kotlin.src.register.RegisterActivity

class BottomSheet() : BottomSheetDialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.findViewById<TextView>(R.id.register_btn)?.setOnClickListener {
            dismiss()
            var intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
        }

        view?.findViewById<Button>(R.id.login1)?.setOnClickListener {
            dismiss()
            var intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        view?.findViewById<Button>(R.id.login2)?.setOnClickListener {
            dismiss()
            var intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}