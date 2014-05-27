package com.gallery.sampleapp.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gallery.sampleapp.R;
import com.gallery.sampleapp.adapters.ImageGalleryPagerAdapter;
import com.gallery.sampleapp.models.ImageDto;

import java.util.ArrayList;

/**
 * Created by juanjo on 26/05/14.
 */
public class GalleryFragment extends Fragment {

	public static int TOTAL_WIDTH;
	public static int TOTAL_HEIGHT;

	private ViewPager mViewPager;
	private ImageGalleryPagerAdapter mImageGalleryPagerAdapter;

	public static GalleryFragment newInstance(ArrayList<ImageDto> urlList) {

		GalleryFragment galleryFragment = new GalleryFragment();

		Bundle bundle = new Bundle();
		bundle.putParcelableArrayList("images", urlList);

		galleryFragment.setArguments(bundle);

		return galleryFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_gallery, container,
				false);
		if (rootView == null) {
			throw new IllegalStateException(
					"Can't inflate the fragment gallery");
		}
		mViewPager = (ViewPager) rootView.findViewById(R.id.screenshotsPager);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setGallery();
	}

	private void setGallery() {
		Display display = getActivity().getWindowManager().getDefaultDisplay();

		if (android.os.Build.VERSION.SDK_INT >= 13) {
			Point size = new Point();
			display.getSize(size);
			TOTAL_WIDTH = size.x;
			TOTAL_HEIGHT = size.y;
		} else {
			TOTAL_WIDTH = display.getWidth();
			TOTAL_HEIGHT = display.getHeight();
		}

		mImageGalleryPagerAdapter = new ImageGalleryPagerAdapter(getActivity(),
				getChildFragmentManager(), getImages(), getImages().size());
		mViewPager.setAdapter(mImageGalleryPagerAdapter);
		// Necessary or the pager will only have one extra page to show
		// make this at least however many pages you can see
		mViewPager.setOffscreenPageLimit(mImageGalleryPagerAdapter.getCount());
		// A little space between pages
		mViewPager.setPageMargin(5);

	}

	private ArrayList<ImageDto> getImages() {
		return getArguments().getParcelableArrayList("images");
	}
}
