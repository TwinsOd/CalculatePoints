package od.twins.clabr.data.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView


fun onCreateListDialog(
    context: Context,
    listId: Int,
    listener: DialogInterface.OnClickListener
): Dialog? {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("Выберите")
    builder.setItems(listId, listener)

    return builder.create()
}

fun onCreateProgressDialog(context: Context, message: String): AlertDialog {
    val llPadding = 30
    val ll = LinearLayout(context)
    ll.orientation = LinearLayout.HORIZONTAL
    ll.setPadding(llPadding, llPadding, llPadding, llPadding)
    ll.gravity = Gravity.CENTER
    var llParam = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    llParam.gravity = Gravity.CENTER
    ll.layoutParams = llParam

    val progressBar = ProgressBar(context)
    progressBar.isIndeterminate = true
    progressBar.setPadding(0, 0, llPadding, 0)
    progressBar.layoutParams = llParam

    llParam = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    llParam.gravity = Gravity.CENTER
    val tvText = TextView(context)
    tvText.text = message
    tvText.setTextColor(Color.parseColor("#000000"))
    tvText.textSize = 20.toFloat()
    tvText.layoutParams = llParam

    ll.addView(progressBar)
    ll.addView(tvText)

    val builder = AlertDialog.Builder(context)
    builder.setCancelable(true)
    builder.setView(ll)

    val dialog = builder.create()
    dialog.setCancelable(false)
    val window = dialog.window
    if (window != null) {
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = layoutParams
    }
    return dialog
}