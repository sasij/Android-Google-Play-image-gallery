package com.gallery.sampleapp.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.DisplayMetrics;

import com.gallery.sampleapp.R;
import com.gallery.sampleapp.fragments.GalleryFragment;
import com.gallery.sampleapp.fragments.ImageGalleryFragment;
import com.gallery.sampleapp.models.ImageDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanjo on 5/05/14.
 */
public class ImageGalleryPagerAdapter extends FragmentStatePagerAdapter {

	private final int mSize;

	private final Context mContext;
	private final List<ImageDto> mImageList;

	public ImageGalleryPagerAdapter(Context context, FragmentManager fm,
			ArrayList<ImageDto> list, int size) {
		super(fm);
		mSize = size;
		mContext = context;
		mImageList = list;
	}

	@Override
	public float getPageWidth(int position) {

		final int imageHeight = (int) convertPixelsToDp(mImageList
				.get(position).getHeight(), mContext);
		final int imageWidth = (int) convertPixelsToDp(mImageList.get(position)
				.getWidth(), mContext);
		final float newHeight = convertPixelsToDp(mContext.getResources()
				.getDimension(R.dimen.screenshots_height), mContext);
		final float newWidth = newHeight * imageWidth / imageHeight;

		return newWidth
				/ convertPixelsToDp(GalleryFragment.TOTAL_WIDTH, mContext);
	}

	@Override
	public int getCount() {
		return mSize;
	}

	@Override
	public Fragment getItem(final int position) {

		return ImageGalleryFragment.newInstance(position,
				mImageList.get(position).getUrl());
	}

	public float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}

}
