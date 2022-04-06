package com.example.asrbot.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.asrbot.R
import com.example.asrbot.data.Message
import com.example.asrbot.utils.Constants.RECEIVE_ID
import com.example.asrbot.utils.Constants.SEND_ID

class MessageAdapter: RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {



    var messageList= mutableListOf<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
       return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
    }
    inner class MessageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener{
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    @SuppressLint("SetTextI18n")

    override fun onBindViewHolder(holder:MessageAdapter.MessageViewHolder, position: Int) {
        val currentMessage=messageList[position]
        when(currentMessage.id){
            SEND_ID->{

                holder.itemView.findViewById<TextView>(R.id.tv_message).apply {
                    text=currentMessage.message
                    visibility =View.VISIBLE
                }
                holder.itemView.findViewById<TextView>(R.id.tv_bot_message).visibility=View.GONE
            }
            RECEIVE_ID->{
                holder.itemView.findViewById<TextView>(R.id.tv_bot_message).apply {
                    text=currentMessage.message
                    visibility=View.VISIBLE
                }
                holder.itemView.findViewById<TextView>(R.id.tv_message).visibility=View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun insertMessage(message: Message){
        this.messageList.add(message)
        notifyItemInserted(messageList.size)
    }

}