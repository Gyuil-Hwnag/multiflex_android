package com.softsquared.template.kotlin.src.qrcode

import android.os.Bundle
import com.softsquared.template.kotlin.config.BaseActivity
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.google.zxing.BarcodeFormat

import com.google.zxing.MultiFormatWriter

import android.widget.ImageView
import com.softsquared.template.kotlin.databinding.*
import java.lang.Exception


class CreateQrActivity : BaseActivity<ActivityCreateqrBinding>(ActivityCreateqrBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var text2 = getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)
        var login_status_jwt = text2.getString("X-ACCESS-TOKEN", null)
        var userIdx = text2.getInt("userIdx", -1)

        var iv: ImageView = binding.qrcode
        var text = userIdx.toString()

        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            iv.setImageBitmap(bitmap)
        } catch (e: Exception) {
        }

        binding.closeBtn.setOnClickListener {
            finish()
        }

    }
}