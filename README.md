# RecyclerView Fundamentals - Uses Sleep Quality Tracker app.
- How to use RecyclerView with Sleep Tracker.
- Made with Google Labs!


### RecyclerView
- By default, RecyclerView only does work to process or draw items that are currently visible on the screen. For example, if your list has a thousand elements but only 10 elements are visible, RecyclerView does only enough work to draw 10 items on the screen. When the user scrolls, RecyclerView figures out what new items should be on the screen and does just enough work to display those items.

- When an item scrolls off the screen, the item's views are recycled. That means the item is filled with new content that scrolls onto the screen. This RecyclerView behavior saves a lot of processing time and helps lists scroll fluidly.

- When an item changes, instead of redrawing the entire list, RecyclerView can update that one item. This is a huge efficiency gain when displaying lists of complex items!

### Adapter
- The adapter pattern in software engineering helps an object to work with another API. RecyclerView uses an adapter to transform app data into something the RecyclerView can display, without changing how the app stores and processes the data. For the sleep-tracker app, you build an adapter that adapts data from the Room database into something that RecyclerView knows how to display, without changing the ViewModel.

- To display your data in a RecyclerView, you need the following parts:

Data to display.
A RecyclerView instance defined in your layout file, to act as the container for the views.
A layout for one item of data.
If all the list items look the same, you can use the same layout for all of them, but that is not mandatory. The item layout has to be created separately from the fragment's layout, so that one item view at a time can be created and filled with data.
A layout manager.
The layout manager handles the organization (the layout) of UI components in a view.
A view holder.
The view holder extends the ViewHolder class. It contains the view information for displaying one item from the item's layout. View holders also add information that RecyclerView uses to efficiently move views around the screen.
An adapter.
The adapter connects your data to the RecyclerView. It adapts the data so that it can be displayed in a ViewHolder. A RecyclerView uses the adapter to figure out how to display the data on the screen.