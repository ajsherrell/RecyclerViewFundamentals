package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightAdapter : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

    //create a list of SleepNight variable to hold the data -- not needed anymore because ListAdapter keeps track of the list for you.
//    var data = listOf<SleepNight>()
//        set(value) {
//            field = value
//            notifyDataSetChanged() //When notifyDataSetChanged() is called, the RecyclerView redraws the whole list, not just the changed items.
//        }

    //override fun getItemCount() = data.size -- Delete the override of getItemCount(), because the ListAdapter implements this method for you.

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // val item = data[position] //creates a variable for one item at a given position in the data -- change to:
        val item = getItem(position)

        holder.bind(item)
    }

    //This function takes two parameters and returns a ViewHolder. The parent parameter, which is the view group that holds the view holder, is always the RecyclerView. The viewType parameter is used when there are multiple views in the same RecyclerView. For example, if you put a list of text views, an image, and a video all in the same RecyclerView, the onCreateViewHolder() function would need to know what type of view to use.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    //create the ViewHolder. Change the signature of the ViewHolder class so that the constructor is private. Because from() is now a method that returns a new ViewHolder instance, there's no reason for anyone to call the constructor of ViewHolder anymore.
    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {

        //Put the cursor on the word holder of the holder parameter of bind(). Press Alt+Enter (Option+Enter on a Mac) to open the intention menu. Select Convert parameter to receiver to convert this to an extension function
        fun bind(item: SleepNight) {
            binding.sleep = item
            binding.executePendingBindings() //This call is an optimization that asks data binding to execute any pending bindings right away.
            //It's always a good idea to call executePendingBindings() when you use binding adapters in a RecyclerView, because it can slightly speed up sizing the views.
        }
        companion object { //The from() function needs to be in a companion object so it can be called on the ViewHolder class, not called on a ViewHolder instance.
            fun from(parent: ViewGroup): ViewHolder {
                //The layout inflater knows how to create views from XML layouts. The context contains information on how to correctly inflate the view. In an adapter for a recycler view, you always pass in the context of the parent view group, which is the RecyclerView.
                val layoutInflater = LayoutInflater.from(parent.context)

                //  The third, boolean, argument is attachToRoot. This argument needs to be false, because RecyclerView adds this item to the view hierarchy for you when it's time.
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight> () {
        override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            //code that tests whether the two passed-in SleepNight items, oldItem and newItem, are the same. If the items have the same nightId, they are the same item, so return true. Otherwise, return false. DiffUtil uses this test to help discover if an item was added, removed, or moved.
            return oldItem.nightId == newItem.nightId
        }

        override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            //check whether oldItem and newItem contain the same data; that is, whether they are equal. This equality check will check all the fields, because SleepNight is a data class. Data classes automatically define equals and a few other methods for you. If there are differences between oldItem and newItem, this code tells DiffUtil that the item has been updated.
            return oldItem == newItem
        }

    }

}