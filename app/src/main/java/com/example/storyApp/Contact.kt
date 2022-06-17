package com.example.storyApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView

class Contact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_contact)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val textView: TextView = findViewById(R.id.facebook)
        val mail: TextView = findViewById(R.id.mail)
        val contact: TextView = findViewById(R.id.contact_us)
        val copyright: TextView = findViewById(R.id.copyright)
        textView.text = getText(R.string.facebook)
        mail.text = getText(R.string.our_mail)
        contact.text = getText(R.string.our_contact)
        copyright.text = getText(R.string.our_copy)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
