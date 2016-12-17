package ru.wearemad.multilevellistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by Artem on 17.12.2016.
 */

//custom expandable listview which will has height based on expanded/collapsed children
public class FullHeightExpandableList extends ExpandableListView {

    //base constructor
    public FullHeightExpandableList(Context c) {
        super(c);
    }

    //we need that one too
    public FullHeightExpandableList (Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //we heed that to make list to be expanded max
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
