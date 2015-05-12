package course.labs.todomanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

        final Viewholder holder;
		// TODO - Get the current ToDoItem
		final ToDoItem toDoItem = (ToDoItem) getItem(position);

        if (convertView == null) {
            // TODO - Inflate the View for this ToDoItem
            // from todo_item.xml
            LayoutInflater mInflater;
            mInflater = LayoutInflater.from(mContext);
            // RelativeLayout itemLayout = (RelativeLayout) mInflater.inflate(R.layout.todo_item, null);
            convertView = mInflater.inflate(R.layout.todo_item, null);
            holder = new Viewholder();
            holder.mTitleView = (TextView)convertView.findViewById(R.id.titleView);
            holder.mStatusView = (CheckBox)convertView.findViewById(R.id.statusCheckBox);
            holder.mPriorityView = (TextView)convertView.findViewById(R.id.priorityView);
            holder.mDateView = (TextView)convertView.findViewById(R.id.dateView);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }

		// Fill in specific ToDoItem data
		// Remember that the data that goes in this View
		// corresponds to the user interface elements defined
		// in the layout file

		// TODO - Display Title in TextView
		// final TextView titleView = (TextView)itemLayout.findViewById(R.id.titleView);
        // titleView.setText(toDoItem.getTitle());
        holder.mTitleView.setText(toDoItem.getTitle());

		// TODO - Set up Status CheckBox
		// final CheckBox statusView = (CheckBox)itemLayout.findViewById(R.id.statusCheckBox);
        boolean statusBool;
        if (toDoItem.getStatus().toString() == "DONE") {
            statusBool = true;
        } else {
            statusBool = false;
            // Change background to light red
            convertView.setBackgroundColor(Color.parseColor("#FFFFEFCD"));
        }

        // statusView.setChecked(statusBool);
        holder.mStatusView.setChecked(statusBool);

		// TODO - Must also set up an OnCheckedChangeListener,
		// which is called when the user toggles the status checkbox

		//statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        holder.mStatusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    // statusView.setChecked(isChecked);
                    holder.mStatusView.setChecked(isChecked);

                    // set color attribute
                    String bgColor;
                    // if checkbox just got checked
                    if (isChecked) {
                        bgColor = "#ffe9ffe4";
                    } else {
                        bgColor = "#FFFFEFCD";
                    }
                    // set bg color

                    //convertView.setBackgroundColor(Color.parseColor(bgColor));
                    }
				});

		// TODO - Display Priority in a TextView
		// final TextView priorityView = (TextView)itemLayout.findViewById(R.id.priorityView);
        holder.mPriorityView.setText(toDoItem.getPriority().toString());


		// TODO - Display Time and Date.
		// Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and time String

		// final TextView dateView = (TextView)itemLayout.findViewById(R.id.dateView);
        holder.mDateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));

		// Return the View you just created
		// return itemLayout;
        return convertView;

	}

    static class Viewholder {
        TextView mTitleView;
        CheckBox mStatusView;
        TextView mPriorityView;
        TextView mDateView;
    }
}
