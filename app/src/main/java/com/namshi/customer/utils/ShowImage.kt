package com.namshi.customer.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.namshi.customer.R


/**
 * Created by @mohamedebrahim96
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
object ShowImage {

    fun showImage(context: Context, url: String) {
        val alertadd: AlertDialog.Builder = AlertDialog.Builder(context)
        val factory = LayoutInflater.from(context)
        val view: View = factory.inflate(R.layout.show_image_dialog, null)
        Glide.with(context)
            .load(url).into(view.findViewById(R.id.dialogImageview))
        alertadd.setView(view)
        alertadd.show()
    }

    fun setupImageBitmap(mContext: Context, bitmap: Bitmap) {
        val settingsDialog = Dialog(mContext)
        val li =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        settingsDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        settingsDialog.setContentView(
            li.inflate(R.layout.show_image_dialog, null)
        )
        settingsDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val profileImage = settingsDialog.findViewById<ImageView>(R.id.dialogImageview)
        profileImage.setImageBitmap(bitmap)
        settingsDialog.show()
        profileImage.setOnClickListener {
            settingsDialog.dismiss()
        }
    }
}