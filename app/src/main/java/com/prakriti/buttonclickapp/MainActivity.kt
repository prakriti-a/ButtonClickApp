package com.prakriti.buttonclickapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private const val TAG = "MainActivity"
private const val TEXT_CONTENTS_KEY = "TextContents"

class MainActivity : AppCompatActivity() {

//    private var editText: EditText? = null // init null before layout is inflated, declared as nullable by ?
//    private var button: Button? = null
    private var textView: TextView? = null

//    private var timesClicked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // this restores the instance state of the activity, incl contents of editable widgets

        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main) // setContentView() inflates the layout

        // to link with widgets in layout
        // declared locally, non-null val, not var. doesn't require safe call (?.) as its not initialized to null
        val editText: EditText = findViewById(R.id.editText) // resources are stored in R, ids are stored when created using @+id in XML file
        val button: Button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        textView?.text = "" // clearing the textview

        textView?.movementMethod = ScrollingMovementMethod() // make textview scrollable, after adding scrollbar in XML layout

        // event-driven programming
        button?.setOnClickListener(object : View.OnClickListener { // events & listeners
            override fun onClick(v: View?) {
                Log.d(TAG, "onClick")
                if(editText?.text.isNullOrBlank()) {
                    editText.error = "Empty field"
                }
                else {
                    textView?.append(editText?.text)
                    textView?.append("\n")
                    editText.text.clear() // = null // or use
                    //editText.setText("")
                }

//                timesClicked++
//                textView?.append("The button is tapped $timesClicked time") // append() to add text to existing text
//                if(timesClicked != 1) {
//                    textView?.append("s\n")
//                } else {
//                    textView?.append("\n")
//                }
            }
        })
    }

    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // only called if data is saved at all
        Log.d(TAG, "onRestoreInstanceState: ")
        super.onRestoreInstanceState(savedInstanceState)
        val savedString = savedInstanceState.getString(TEXT_CONTENTS_KEY, "")
        textView?.text = savedString
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart: ")
        super.onRestart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        super.onResume()
    }

    override fun onPause() { // save persistent / permanent data locally here
        Log.d(TAG, "onPause: ")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) { // save state, as opposed to data
        Log.d(TAG, "onSaveInstanceState: ")
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_CONTENTS_KEY, textView?.text.toString()) // textview is nullable
    }

    override fun onStop() { // data transfer / remote saving
        Log.d(TAG, "onStop: ")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
}