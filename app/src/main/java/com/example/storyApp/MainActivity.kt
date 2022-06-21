package com.example.storyApp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
     private var storyTitles = arrayOf<String>()
     private var storyContents = arrayOf<String>()
     private var storyImages = arrayOf<String>()
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

         storyTitles = resources.getStringArray(R.array.storyTitles)
         storyContents = resources.getStringArray(R.array.storyContents)
        storyImages = resources.getStringArray(R.array.storyImages)

        val adapter = ItemAdapter(storyTitles,storyContents,storyImages)
      //  storyList.layoutManager = LinearLayoutManager(this)
        storyList.adapter = adapter
        chooseLayout()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        if(item.itemId == R.id.random){
            val randPosition = Random.nextInt(0,storyTitles.size)
            val intent = Intent(applicationContext,Details::class.java)
            intent.putExtra("storyTitle",storyTitles[randPosition])
            intent.putExtra("storyContent",storyContents[randPosition])
            intent.putExtra("storyImage",storyImages[randPosition])
            startActivity(intent)

        }
        if (item.itemId == R.id.about){
            val intent = Intent(applicationContext, About::class.java)
            startActivity(intent)

        }
        if (item.itemId == R.id.contact){
            val intent = Intent(applicationContext, Contact::class.java)
            startActivity(intent)

        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu?.findItem(R.id.action_switch)
        setIcon(layoutButton)
        return true

    }
    private fun chooseLayout(){
    if (isLinearLayoutManager){
        storyList.layoutManager = LinearLayoutManager(this)

    }else{
        val layoutManager = GridLayoutManager(this,4)
        layoutManager.orientation = GridLayoutManager.HORIZONTAL
        storyList.layoutManager = layoutManager
    }
}
    private fun setIcon(menuItem: MenuItem?){
        if(menuItem == null) return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_view_list_24)
        else ContextCompat.getDrawable(this, R.drawable.ic_baseline_grid_on_24)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_switch -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
