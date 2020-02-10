package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {

    //create a list of SleepNight variable to hold the data
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged() //When notifyDataSetChanged() is called, the RecyclerView redraws the whole list, not just the changed items.
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position] //creates a variable for one item at a given position in the data

        holder.bind(item)
    }

    //This function takes two parameters and returns a ViewHolder. The parent parameter, which is the view group that holds the view holder, is always the RecyclerView. The viewType parameter is used when there are multiple views in the same RecyclerView. For example, if you put a list of text views, an image, and a video all in the same RecyclerView, the onCreateViewHolder() function would need to know what type of view to use.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    //create the ViewHolder. Change the signature of the ViewHolder class so that the constructor is private. Because from() is now a method that returns a new ViewHolder instance, there's no reason for anyone to call the constructor of ViewHolder anymore.
    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        //Put the cursor on the word holder of the holder parameter of bind(). Press Alt+Enter (Option+Enter on a Mac) to open the intention menu. Select Convert parameter to receiver to convert this to an extension function
        fun bind(item: SleepNight) {
            //reference to the resources for this view
            val res = itemView.context.resources

            //set the text of the sleepLength text view to the duration.
            sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)

            //set the quality
            quality.text = convertNumericQualityToString(item.sleepQuality, res)

            //set the quality image
            qualityImage.setImageResource(when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })
        }
        companion object { //The from() function needs to be in a companion object so it can be called on the ViewHolder class, not called on a ViewHolder instance.
            fun from(parent: ViewGroup): ViewHolder {
                //The layout inflater knows how to create views from XML layouts. The context contains information on how to correctly inflate the view. In an adapter for a recycler view, you always pass in the context of the parent view group, which is the RecyclerView.
                val layoutInflater = LayoutInflater.from(parent.context)

                //  The third, boolean, argument is attachToRoot. This argument needs to be false, because RecyclerView adds this item to the view hierarchy for you when it's time.
                val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
                return ViewHolder(view)
            }
        }
    }

}