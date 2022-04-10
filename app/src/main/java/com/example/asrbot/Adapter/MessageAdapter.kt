package com.example.asrbot.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.asrbot.R
import com.example.asrbot.activity.MainActivity
import com.example.asrbot.data.Message
import com.example.asrbot.utils.Constants.RECEIVE_ID
import com.example.asrbot.utils.Constants.SEND_ID
//
//public class MessageAdapter extends ArrayAdapter<Message> {
//
////    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>()
//
//    var messageList= mutableListOf<Message>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
//       return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
//    }
//    inner class MessageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
//        init {
//            itemView.setOnClickListener{
//                messageList.removeAt(adapterPosition)
//                notifyItemRemoved(adapterPosition)
//            }
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//
//    override fun onBindViewHolder(holder:MessageAdapter.MessageViewHolder, position: Int) {
//        val currentMessage=messageList[position]
//        when(currentMessage.id){
//            SEND_ID->{
//
//                holder.itemView.findViewById<TextView>(R.id.tv_message).apply {
//                    text=currentMessage.message
//                    visibility =View.VISIBLE
//                }
//                holder.itemView.findViewById<TextView>(R.id.tv_bot_message).visibility=View.GONE
//            }
//            RECEIVE_ID->{
//                holder.itemView.findViewById<TextView>(R.id.tv_bot_message).apply {
//                    text=currentMessage.message
//                    visibility=View.VISIBLE
//                }
//                holder.itemView.findViewById<TextView>(R.id.tv_message).visibility=View.GONE
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return messageList.size
//    }
//
//    fun insertMessage(message: Message){
//        this.messageList.add(message)
//        notifyItemInserted(messageList.size)
//    }
//
//}


class MessageAdapter(private val context: MainActivity, var messageList: MutableList<Message>): ArrayAdapter<Message>(context, R.layout.item) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


       val inflater:LayoutInflater= LayoutInflater.from(context)
        val view : View=inflater.inflate(R.layout.item,parent,false)


        val botMessage:TextView=view.findViewById(R.id.tv_bot_message)
        val message:TextView=view.findViewById(R.id.tv_message)

        val currentMessage=messageList[position]
        when(currentMessage.id){
            SEND_ID->{
                message.apply {
                    text=currentMessage.message
                    visibility=View.VISIBLE
                }
                botMessage.visibility=View.GONE
            }
            RECEIVE_ID->{
                                botMessage.apply {
                    text=currentMessage.message
                    visibility =View.VISIBLE
                }
                message.visibility=View.GONE
            }
        }
        return view
    }

//    override fun getItem(position: Int): Message? {
//        return messageList[position]
//    }

    override fun getCount(): Int {
        return messageList.size
    }

    fun insertMessage(message: Message){
        this.messageList.add(message)
        notifyDataSetChanged()
    }


}