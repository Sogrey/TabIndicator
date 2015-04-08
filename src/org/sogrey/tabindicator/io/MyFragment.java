package org.sogrey.tabindicator.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sogrey.tabindicator.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

@SuppressLint("ValidFragment")
public class MyFragment extends Fragment implements OnClickListener {

	private Activity mActivity;
	private View mView;
	private int mIndex;
	private Resources resources;
	private SimpleAdapter list;
	private ListView mListView;

	/* mList是用来存放要显示的数据 */
	private List<HashMap<String, Object>> mList = new ArrayList<HashMap<String, Object>>();

	public MyFragment(Activity activity, int index) {
		mActivity = activity;
		mIndex = index;
		resources = mActivity.getResources();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		mView = inflater.inflate(R.layout.lay1, null);
		initList();

		/* 给各个页卡不同的背景以示区别 */
		if (mIndex == 0) {
			mView.setBackgroundColor(0xffFCDAD5);
		} else if (mIndex == 1) {
			mView.setBackgroundColor(0xffFFFAB3);
		} else {
			mView.setBackgroundColor(0xffC8E2B1);
		}
		return mView;
	}

	/* 然后， 我们来给mList添加一些要显示的数据 */
	private void initList() {
		mList = getData();
		mListView = (ListView) mView.findViewById(R.id.mylistview);
		list = new SimpleAdapter(mActivity, mList, R.layout.listview,
				new String[] { "title", "logo" }, new int[] { R.id.title,
						R.id.logo });
		mListView.setAdapter(list);
	}

	private List<HashMap<String, Object>> getData() {
		HashMap<String, Object> hashMap;
		for (int i = 0; i < 12; i++) {
			hashMap = new HashMap<String, Object>();
			hashMap.put("logo", R.drawable.ic_launcher);
			hashMap.put("title", resources.getString(R.string.app_name));
			mList.add(hashMap);
		}
		return mList;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		default:
			break;
		}
	}
}
