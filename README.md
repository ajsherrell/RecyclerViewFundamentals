# RecyclerView Fundamentals - Uses Sleep Quality Tracker app.
- How to use RecyclerView with Sleep Tracker.
- Made with Google Labs!
- DiffUtil
- DataBinding
- Binding Adapters
- ListAdapter

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

### ViewHolder
- A ViewHolder describes an item view and metadata about its place within the RecyclerView. RecyclerView relies on this functionality to correctly position the view as the list scrolls, and to do interesting things like animate views when items are added or removed in the Adapter.
- If RecyclerView does need to access the views stored in the ViewHolder, it can do so using the view holder's itemView property. RecyclerView uses itemView when it's binding an item to display on the screen, when drawing decorations around a view like a border, and for implementing accessibility.

### DiffUtil
-  notifyDataSetChanged() tells RecyclerView that the entire list is potentially invalid. As a result, RecyclerView rebinds and redraws every item in the list, including items that are not visible on screen. This is a lot of unnecessary work. For large or complex lists, this process could take long enough that the display flickers or stutters as the user scrolls through the list.

To fix this problem, you can tell RecyclerView exactly what has changed. RecyclerView can then update only the views that changed on screen.

RecyclerView has a rich API for updating a single element. You could use notifyItemChanged() to tell RecyclerView that an item has changed, and you could use similar functions for items that are added, removed, or moved. You could do it all manually, but that task would be non-trivial and might involve quite a bit of code.

Fortunately, there's a better way.

- DiffUtil is efficient and does the hard work for you:
RecyclerView has a class called DiffUtil which is for calculating the differences between two lists. DiffUtil takes an old list and a new list and figures out what's different. It finds items that were added, removed, or changed. Then it uses an algorithm called a Eugene W. Myers's difference algorithm to figure out the minimum number of changes to make from the old list to produce the new list.

Once DiffUtil figures out what has changed, RecyclerView can use that information to update only the items that were changed, added, removed, or moved, which is much more efficient than redoing the entire list.

### Binding Adapters
- Binding adapters are adapters that take your data and adapt it into something that data binding can use to bind a view, like text or an image.
- To declare a binding adapter, you define a method that takes an item and a view, and annotate it with @BindingAdapter. In the body of the method, you implement the transformation. In Kotlin, you can write a binding adapter as an extension function on the view class that receives the data.

#### Summary
- Displaying a list or grid of data is one of the most common UI tasks in Android. RecyclerView is designed to be efficient even when displaying extremely large lists.
RecyclerView does only the work necessary to process or draw items that are currently visible on the screen.
When an item scrolls off the screen, its views are recycled. That means the item is filled with new content that scrolls onto the screen.
The adapter pattern in software engineering helps an object work together with another API. RecyclerView uses an adapter to transform app data into something it can display, without the need for changing how the app stores and processes data.

- To display your data in a RecyclerView, you need the following parts:

RecyclerView:
To create an instance of RecyclerView, define a <RecyclerView> element in the layout file.
LayoutManager:
A RecyclerView uses a LayoutManager to organize the layout of the items in the RecyclerView, such as laying them out in a grid or in a linear list.

In the <RecyclerView> in the layout file, set the app:layoutManager attribute to the layout manager (such as LinearLayoutManager or GridLayoutManager).

You can also set the LayoutManager for a RecyclerView programmatically. (This technique is covered in a later codelab.)
Layout for each item
Create a layout for one item of data in an XML layout file.
Adapter:
Create an adapter that prepares the data and how it will be displayed in a ViewHolder. Associate the adapter with the RecyclerView.

When RecyclerView runs, it will use the adapter to figure out how to display the data on the screen.

The adapter requires you to implement the following methods:
– getItemCount() to return the number of items.
– onCreateViewHolder() to return the ViewHolder for an item in the list.
– onBindViewHolder() to adapt the data to the views for an item in the list.

ViewHolder:
A ViewHolder contains the view information for displaying one item from the item's layout.
The onBindViewHolder() method in the adapter adapts the data to the views. You always override this method. Typically, onBindViewHolder() inflates the layout for an item, and puts the data in the views in the layout.
Because the RecyclerView knows nothing about the data, the Adapter needs to inform the RecyclerView when that data changes. Use notifyDataSetChanged()to notify the Adapter that the data has changed.

- DiffUtil:

RecyclerView has a class called DiffUtil which is for calculating the differences between two lists.
DiffUtil has a class called ItemCallBack that you extend in order to figure out the difference between two lists.
In the ItemCallback class, you must override the areItemsTheSame() and areContentsTheSame() methods.

- ListAdapter:

To get some list management for free, you can use the ListAdapter class instead of RecyclerView.Adapter. However, if you use ListAdapter you have to write your own adapter for other layouts, which is why this codelab shows you how to do it.
To open the intention menu in Android Studio, place the cursor on any item of code and press Alt+Enter (Option+Enter on a Mac). This menu is particularly helpful for refactoring code and creating stubs for implementing methods. The menu is context-sensitive, so you need to place cursor exactly to get the correct menu.