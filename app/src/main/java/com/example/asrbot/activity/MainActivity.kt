package com.example.asrbot.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.asrbot.Adapter.MessageAdapter
import com.example.asrbot.R
import com.example.asrbot.data.Message
import com.example.asrbot.databinding.ActivityMainBinding
import com.example.asrbot.fragment.VoiceFragment
import com.example.asrbot.utils.BotResponse
import com.example.asrbot.utils.Constants.OPEN_GOOGLE
import com.example.asrbot.utils.Constants.OPEN_SEARCH
import com.example.asrbot.utils.Constants.PLAY_MY
import com.example.asrbot.utils.Constants.PLAY_SONG
import com.example.asrbot.utils.Constants.PLAY_YOUTUBE
import com.example.asrbot.utils.Constants.RECEIVE_ID
import com.example.asrbot.utils.Constants.SEND_ID
import com.example.asrbot.utils.Time
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*



class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    //You can ignore this messageList if you're coming from the tutorial,
    // it was used only for my personal debugging
    var messagesList = mutableListOf<Message>()
    private lateinit var adapter: MessageAdapter


    private val botList = listOf("Aditya", "ASR_BOT", "Adi")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         drawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            it.isChecked=true

            when(it.itemId){

                R.id.nav_voice ->replaceFragment(VoiceFragment(),it.title.toString())
            }

            true


        }


        listView()
        clickEvents()

        val random = (0..3).random()
        customBotMessage("Hello! Today you're speaking with ${botList[random]}, how may I help?")
    }

    private fun listView() {
        adapter= MessageAdapter(this,messagesList)
        binding.rvMessages.adapter=adapter
        binding.rvMessages.layoutMode
        }


    private fun clickEvents() {

        //Send a message
        binding.btnSend.setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
        binding.etMessage.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    binding.rvMessages.smoothScrollToPosition(messagesList.size -1)
                }
            }
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                binding.rvMessages.smoothScrollToPosition(messagesList.size -1)
            }
        }
    }

    private fun sendMessage() {
        val message = binding.etMessage.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            messagesList.add(Message(message, SEND_ID, timeStamp))
            binding.etMessage.setText("")


           adapter.insertMessage(Message(message, SEND_ID, timeStamp))

           //Scrolls us to the position of the latest message
            binding.rvMessages.smoothScrollToPosition(messagesList.size -1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            //Fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {
                //Gets the response
                val response = BotResponse.basicResponses(message)

                //Adds it to our local list
                messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                //Inserts our message into the adapter
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                //Scrolls us to the position of the latest message
                binding.rvMessages.smoothScrollToPosition(messagesList.size -1)

                //Starts Google
                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                    PLAY_SONG->{
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String?= message.substringAfterLast("play")
                        site.data=Uri.parse("https://search.brave.com/search?q=$searchTerm")
                        startActivity(site)
                    }
                    PLAY_MY->{
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data=Uri.parse("https://www.youtube.com/watch?v=Il-an3K9pjg")
                        startActivity(site)
                    }
                    PLAY_YOUTUBE->{
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("solve")
                        site.data=Uri.parse("https://www.youtube.com/results?search_query=$searchTerm")
                        startActivity(site)
                    }

                }
            }
        }
    }

    private fun customBotMessage(message: String) {


        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()

                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID,timeStamp))

                binding.rvMessages.smoothScrollToPosition(messagesList.size -1)
            }
        }
    }


//    private fun replaceFragment(fragment: Fragment, title:String){
//        val fragmentManager=supportFragmentManager
//        val fragmentTransaction=fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.framelayout,fragment)
//        fragmentTransaction.commit()
//        drawerLayout.closeDrawers()
//        setTitle(title)
//    }

    private fun replaceFragment(fragment: Fragment,title:String){
                val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}

