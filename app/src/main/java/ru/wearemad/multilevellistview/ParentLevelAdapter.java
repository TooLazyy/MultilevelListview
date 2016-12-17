package ru.wearemad.multilevellistview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 17.12.2016.
 */

public class ParentLevelAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;
    //last level items list
    private List<List<List<ListItem>>> levelThree = new ArrayList<>();
    //second level items list (sub groups)
    private List<List<ListItem>> levelTwo = new ArrayList<>();
    //first level (groups)
    private List<ListItem> groupHeaders = new ArrayList<>();
    private Context context;
    private int arrowColor;

    public ParentLevelAdapter (Context c, List<List<List<ListItem>>> levelThree,
                               List<List<ListItem>> levelTwo,
                               List<ListItem> groupHeaders) {

        this.context = c;
        arrowColor = c.getResources().getColor(R.color.colorTextBlack);
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.levelThree = levelThree;
        this.levelTwo = levelTwo;
        this.groupHeaders = groupHeaders;
    }

    @Override
    public int getGroupCount() {
        return groupHeaders.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    //nothing special
    //we just inflate parent view - simple layout with icons and text view (s)
    @Override
    public View getGroupView(int i, boolean expanded, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.parent_item, null);
        }
        TextView text = (TextView) view.findViewById(R.id.tvGName);
        text.setText(groupHeaders.get(i).getName());
        ImageView arrow = (ImageView) view.findViewById(R.id.ivArrow);
        if (expanded) {
            arrow.setImageResource(R.drawable.arrow_down);
        } else {
            arrow.setImageResource(R.drawable.arrowor_rigth);
        }
        arrow.setColorFilter(arrowColor, PorterDuff.Mode.SRC_ATOP);
        return view;
    }

    //the magic goes here
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {

        final FullHeightExpandableList list = new FullHeightExpandableList(this.context);
        ChildLevelAdapter adapter = new ChildLevelAdapter(this.context,
                levelThree.get(groupPosition),
                levelTwo.get(groupPosition));
        list.setGroupIndicator(null);
        list.setAdapter(adapter);
        //if you wanna to set onclick listeners on your child elements
        list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                //get list adapter first
                ChildLevelAdapter listAdapter = (ChildLevelAdapter) expandableListView.getExpandableListAdapter();
                //get child by its position
                ListItem item = (ListItem) listAdapter.getChild(i, i1);
                return false;
            }
        });
        // another option to inflate subgroup view is
        /*inflate your layout with FullHeightExpandableList
        * view  = inflater.inflate(R.layout.layout_id, null);
        * list = (FullHeightExpandableList) v.findViewById (listid);
        * then setup your listview
        * return view;
        * */
        return list;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
