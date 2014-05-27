package com.gallery.sampleapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gallery.sampleapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by juanjo on 5/05/14.
 */
public class ImageGalleryFragment extends Fragment {

	private ImageView mImageView;

	public static ImageGalleryFragment newInstance(int position, String urlImage) {
		final ImageGalleryFragment fragment = new ImageGalleryFragment();

		final Bundle args = new Bundle();
		args.putInt("position", position);
		args.putString("image", urlImage);
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View viewRoot = inflater.inflate(R.layout.fragment_gallery_image,
				container, false);

		if (viewRoot == null) {
			throw new IllegalStateException("Can't create view");
		}

		mImageView = (ImageView) viewRoot.findViewById(R.id.imageView);
		mImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),
						"Click on image " + getPosition(), Toast.LENGTH_SHORT)
						.show();
			}
		});
		Picasso.with(getActivity()).load(getImage()).into(mImageView);

		return viewRoot;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private int getPosition() {
		return getArguments() != null ? getArguments().getInt("position") : -1;
	}

	private String getImage() {
		return getArguments().getString("image");
	}
}
