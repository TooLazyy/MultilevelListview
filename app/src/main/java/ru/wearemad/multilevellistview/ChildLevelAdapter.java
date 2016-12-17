package ru.wearemad.multilevellistview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 17.12.2016.
 */

//simple expandable listview adapter for the last level
// here we just inflate simple layouts for both: children and group elements
public class ChildLevelAdapter extends BaseExpandableListAdapter {

    private List<ListItem> groupData = new ArrayList<>();
    private List<List<ListItem>> childData = new ArrayList<>();
    private LayoutInflater inflater;
    private int arrowColor;
    private Context context;

    public ChildLevelAdapter(Context c, List<List<ListItem>> child, List<ListItem> group) {
        this.childData = child;
        groupData = group;
        this.context = c;
        arrowColor = c.getResources().getColor(R.color.colorTextBlack);
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int i) {
        try {
            return childData.get(i).size();
        } catch (Exception ex) {
            return 0;
        }
    }

    //if you need onclicklisteners on children items,
    //override getGroup and getChild methods
    @Override
    public Object getGroup(int i) {
        return groupData.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childData.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean expanded, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.parent_item, null);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(Utils.dpToPixels(context, 36), 0, 0, 0);
        TextView text = (TextView) view.findViewById(R.id.tvGName);
        text.setText(groupData.get(i).getName());
        ImageView arrow = (ImageView) view.findViewById(R.id.ivArrow);
        if (expanded) {
            arrow.setImageResource(R.drawable.arrow_down);
        } else {
            arrow.setImageResource(R.drawable.arrowor_rigth);
        }
        arrow.setColorFilter(arrowColor, PorterDuff.Mode.SRC_ATOP);
        view.setLayoutParams(lp);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.child_item, null);
        }
        TextView text = (TextView) view.findViewById(R.id.tvGName);
        text.setText(childData.get(i).get(i1).getName());
        text.setPadding(25,0,0,0);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
