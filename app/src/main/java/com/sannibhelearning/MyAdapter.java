package com.sannibhelearning;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.net.URL;
import java.util.ArrayList;

public class MyAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    ImageView image;


    // ArrayList<String> mGroups;

    private String[] mGroups ;/*= {
            "Course 1",
            "Course 2",
            "Course 3",
            "Course 4",
            "Course 5",
            "Course 6",
            "Course 7",
            "Course 8",
            "Course 9",
    };*/

    private String[] mGroupDrawables;

    private final String[][] mChilds = {
            {"Modules","Modules2","Modules3"},
            {"Modules",},
            {"Modules",},
            {"Modules",},
            {"Modules",},
            {"Modules",},
            {"Modules",},
            {"Modules",},
            {"Modules",},

    };

    public MyAdapter(Context context, ArrayList<String> courses,ArrayList<String> images)  {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //  mGroups=courses;
        Log.d("sqllak","courses:"+courses.size());
        mGroups=new String[courses.size()];
        for(int i=0;i<courses.size();i++){
            mGroups[i]=courses.get(i);
        }
        //mGroups=(String[]) courses.toArray();
        Log.d("sqllak","array:"+mGroups[0]);

        mGroupDrawables=new String[images.size()];
        for(int i=0;i<images.size();i++){
            mGroupDrawables[i]=images.get(i);
        }
    }

    @Override
    public int getGroupCount() {
        return mGroups.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.sample_activity_list_group_item, parent, false);
        }

        image = (ImageView) convertView.findViewById(R.id.sample_activity_list_group_item_image);
        //image.setImageResource(mGroupDrawables[groupPosition]);

        Picasso.get().load("http://192.168.0.4:80/sannibh/admin/"+mGroupDrawables[groupPosition]).error(R.drawable.ic_profile_icon).into(image,new Callback.EmptyCallback() {
            @Override public void onSuccess() {
                // Index 0 is the image view.
                Log.d("sqllak","imageloaded");
                //animator.setDisplayedChild(0);
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                Log.d("sqllak", "" + e.getMessage());
            }
        });;
        //image.setImageURI(Uri.parse("http://192.168.0.4:80/sannibh/admin/courses/Python/image/Python.jpg"));




        final TextView text = (TextView) convertView.findViewById(R.id.sample_activity_list_group_item_text);
        text.setText(mGroups[groupPosition]);

        final ImageView expandedImage = (ImageView) convertView.findViewById(R.id.sample_activity_list_group_expanded_image);
        final int resId = isExpanded ? R.drawable.ic_down_arrow : R.drawable.ic_down_arrow;
        expandedImage.setImageResource(resId);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChilds[groupPosition].length;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChilds[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.sample_activity_list_child_item, parent, false);
        }

        final TextView text = (TextView) convertView.findViewById(R.id.sample_activity_list_child_item_text);
        text.setText(mChilds[groupPosition][childPosition]);
        final String temp=mChilds[groupPosition][childPosition];
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sqllak",""+temp);


                //Module clicks handle here
            }
        });

        return convertView;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
        //Course click handle here

        Log.d("sqllak",""+mGroups[groupPosition]);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


}