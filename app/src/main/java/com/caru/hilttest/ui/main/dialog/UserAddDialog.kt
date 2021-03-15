package com.caru.hilttest.ui.main.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.Window
import android.view.Window.FEATURE_NO_TITLE
import androidx.databinding.DataBindingUtil
import com.caru.hilttest.R
import com.caru.hilttest.databinding.DialogUserAddBinding
import com.caru.hilttest.model.User
import com.caru.hilttest.ui.main.viewmodel.BoardViewModel

class UserAddDialog(private val context: Context) {

    private lateinit var _binding : DialogUserAddBinding

    fun callFunction(display: Display?, viewModel : BoardViewModel) {
        val size = Point()
        display?.getSize(size)

        _binding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.dialog_user_add,
            null,
            false)

        val dlg = Dialog(context)
        dlg.requestWindowFeature(FEATURE_NO_TITLE)
        dlg.setContentView(_binding.root)

        val window: Window? = dlg.window
        window?.setLayout(MATCH_PARENT, WRAP_CONTENT)
        dlg.show()


        _binding.okBtn.setOnClickListener {
            Log.d("log", "callFunction: 응답하라" )
            viewModel.userInsert(
                    User(
                    0,
                    "${_binding.userName.text}",
                    "${_binding.userAge.text}".toInt()
            ))
            dlg.dismiss()
        }
    }
}