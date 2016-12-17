package ru.wearemad.multilevellistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ExpandableListView elvList;
    private ParentLevelAdapter adapter;
    //groups count
    private static final int GROUPS_COUNT = 5;
    //subgroups count
    private static final int SUBGROUPS_COUNT = 3;
    //children count
    private static final int CHILDREN_COUNT = 5;
    //groups
    private List<ListItem> groups = new ArrayList<>();
    //subgroups
    private List<List<ListItem>> subgroups = new ArrayList<>();
    //last level items
    private List<List<List<ListItem>>> children = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elvList = (ExpandableListView) findViewById(R.id.elvList);
        //setup group data
        setupGroupData();
        //setup subgroups data
        setupSubgroupsData();
        //setup last level items
        setupLastLevelData();
        adapter = new ParentLevelAdapter(this, children, subgroups, groups);
        elvList.setAdapter(adapter);
    }

    private void setupGroupData () {
        for (int i = 0; i < GROUPS_COUNT; i++) {
            groups.add(new ListItem("group " + (i+1)));
        }
    }

    private void setupSubgroupsData () {
        for (int i = 0; i < groups.size(); i++) {
            List<ListItem> subgr = new ArrayList<>();
            for (int j = 0; j < SUBGROUPS_COUNT; j++) {
                subgr.add(new ListItem("group: " + (i+1) + " subgr: " + (j+1)));
            }
            subgroups.add(subgr);
        }
    }

    private void setupLastLevelData () {
        for (int i = 0; i < groups.size(); i++) {
            List<List<ListItem>> sublist = new ArrayList<>();
            for (int j = 0; j < subgroups.get(i).size(); j++) {
                List<ListItem> child = new ArrayList<>();
                for (int c = 0; c < CHILDREN_COUNT; c++) {
                    child.add(new ListItem("child " + (c+1)));
                }
                sublist.add(child);
            }
            children.add(sublist);
        }
    }

}
