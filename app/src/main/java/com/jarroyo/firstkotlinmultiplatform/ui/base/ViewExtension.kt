package com.jarroyo.firstkotlinmultiplatform.ui.base

import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun EditText.text(): String = this.text.toString()

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun AppCompatActivity.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, message, duration).show()
}

fun TextView.setHtmlText(text: String?) {
    if (text != null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY))
        } else {
            this.setText(Html.fromHtml(text))
        }
        this.movementMethod = LinkMovementMethod.getInstance()
    }
}
/*fun ImageView.loadUrl(url: String) {
    Picasso.get().load(url).into(this)
}

fun ImageView.loadRoundedUrl(url: String) {
    val radius = 5
    val transformation = RoundedCornersTransformation(radius, 0)

    Picasso.get().load(url).transform(transformation).into(this)
}*/

/*fun AppCompatActivity.snackBar(message: String, onClickListener: View.OnClickListener? = null) {
    //val snack = Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
    val snack = com.google.android.material.snackbar.Snackbar.make(findViewById(android.R.id.content), Html.fromHtml(message), com.google.android.material.snackbar.Snackbar.LENGTH_LONG)

    // Custom Snackbar
    val layout = snack.getView() as Snackbar.SnackbarLayout

    // Background
    layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))

    // TextColor
    val textView = layout.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
    textView.setTextColor(ContextCompat.getColor(this, R.color.white))

    // Action
    //snack.setAction(getString(R.string.undo), onClickListener)
    //snack.setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
    snack.show()
}*/

interface SnackbarTopListener {
    fun onSnackbarAnimationFinish()
}