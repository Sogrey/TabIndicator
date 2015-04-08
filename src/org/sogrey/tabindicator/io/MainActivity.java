/**
 * @author Sogrey
 * 2015年4月8日
 */
package org.sogrey.tabindicator.io;

import java.util.ArrayList;
import java.util.List;

import org.sogrey.tabindicator.R;
import org.sogrey.tabindicator.view.PageIndicator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

/**
 * @author Sogrey
 * 2015年4月8日
 */
public class MainActivity extends FragmentActivity{
	/**
	 * @author Sogrey
	 *
	 * 2015年4月8日
	 */
	ViewPager mViewPager;
	TabPageAdapter mTabsAdapter;
	PageIndicator mIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initControl();
		initViewPager();
	}

	private void initControl() {
		mViewPager = (ViewPager) findViewById(R.id.pvr_user_pager);
		mViewPager.setOffscreenPageLimit(2);/* 预加载页面 */
		mIndicator = (PageIndicator) findViewById(R.id.pvr_user_indicator);
		mIndicator
				.setOnPageChangeListener(new IndicatorOnPageChangedListener());
	}

	/* 初始化ViewPager */
	private void initViewPager() {
		mTabsAdapter = new TabPageAdapter(this);
		mViewPager.setAdapter(mTabsAdapter);
		mIndicator.setViewPager(mViewPager);
		new ContentAsyncTask().execute();
	}

	/* 页卡框架 */
	public class TabPageAdapter extends FragmentPagerAdapter {

		private ArrayList<Fragment> mFragments;
		public List<String> tabs = new ArrayList<String>();

		public TabPageAdapter(MainActivity activity) {
			super(activity.getSupportFragmentManager());
			mFragments = new ArrayList<Fragment>();
		}

		public void addTab(String title, Fragment fragment) {
			mFragments.add(fragment);
			tabs.add(title);
			notifyDataSetChanged();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return tabs.get(position);
		}

		@Override
		public Fragment getItem(int arg0) {
			return mFragments.get(arg0);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}
	}

	public class ContentAsyncTask extends AsyncTask<Integer, Integer, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			mTabsAdapter.addTab(getString(R.string.pvrCompleted),
					new MyFragment(MainActivity.this, 0));
			mTabsAdapter.addTab(getString(R.string.pvrUnfinished),
					new MyFragment(MainActivity.this, 1));
			mTabsAdapter.addTab(getString(R.string.pvrFailure), new MyFragment(
					MainActivity.this, 2));
			mTabsAdapter.notifyDataSetChanged();
			mViewPager.setCurrentItem(1);
		}

		@Override
		protected String doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			return null;
		}
	}



	/* 指示器切换监听 */
	private class IndicatorOnPageChangedListener implements
			OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
		}
	}

	/* 页卡切换监听 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
}
