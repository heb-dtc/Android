package com.flo.upnp.simplecontrolpoint;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by florent.noel on 5/17/13.
 */
public class BrowseLocalFragment extends ListFragment {
    private ArrayList<String> mSdCardFiles = null;
    private ListView mList = null;

    public BrowseLocalFragment() {
        mSdCardFiles = getFiles();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.page_browse_local_fragment, container, false);

        //get the list
        mList = (ListView)rootView;
        //populate
        mList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mSdCardFiles));

        String page = "Browse Local Files";
        getActivity().setTitle(page);

        return rootView;
    }

    public ArrayList<String> getFiles() {
        File storage = Environment.getExternalStorageDirectory();
        ArrayList<String> fileList = new ArrayList<String>();

        File[] files = storage.listFiles();
        if (files.length == 0)
            return null;
        else {
            for (int i=0; i<files.length; i++)
                fileList.add(files[i].getName());
        }

        return fileList;
    }
}
