package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    //create a list of SleepNight variable to hold the data
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged() //When notifyDataSetChanged() is called, the RecyclerView redraws the whole list, not just the changed items.
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position] //creates a variable for one item at a given position in the data

        //get the data into the viewHolder and onto the screen
        holder.textView.text = item.sleepQuality.toString()

        //set items with low sleep quality to red
        if (item.sleepQuality <= 1) {
            holder.textView.setTextColor(Color.RED)
        } else {
            //reset the color
            holder.textView.setTextColor(Color.BLACK)
        }
    }

    //This function takes two parameters and returns a ViewHolder. The parent parameter, which is the view group that holds the view holder, is always the RecyclerView. The viewType parameter is used when there are multiple views in the same RecyclerView. For example, if you put a list of text views, an image, and a video all in the same RecyclerView, the onCreateViewHolder() function would need to know what type of view to use.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        //The layout inflater knows how to create views from XML layouts. The context contains information on how to correctly inflate the view. In an adapter for a recycler view, you always pass in the context of the parent view group, which is the RecyclerView.
        val layoutInflater = LayoutInflater.from(parent.context)

        //  The third, boolean, argument is attachToRoot. This argument needs to be false, because RecyclerView adds this item to the view hierarchy for you when it's time.
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }

}