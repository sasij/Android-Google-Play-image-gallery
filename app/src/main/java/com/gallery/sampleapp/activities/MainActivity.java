package com.gallery.sampleapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gallery.sampleapp.R;
import com.gallery.sampleapp.fragments.GalleryFragment;
import com.gallery.sampleapp.models.ImageDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

	private ArrayList<ImageDto> mImageList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getImages();
	}

	private void getImages() {

		RequestQueue queue = Volley.newRequestQueue(this);
		String url = "http://fakapi.eu01.aws.af.cm/v2/portals/personal_juegos_android/sample_images/";

		queue.add(new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray jsonArray) {
				Type type = new TypeToken<ArrayList<ImageDto>>() {
				}.getType();
				mImageList = new Gson().fromJson(jsonArray.toString(), type);
				setFragmentGallery(mImageList);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Log.e("Gallery", volleyError.getMessage());
			}
		}));
	}

	private void setFragmentGallery(ArrayList<ImageDto> imageList) {
		Fragment fragment = getSupportFragmentManager().findFragmentByTag(
				"fragment_gallery");

		if (fragment == null && imageList != null) {

			fragment = GalleryFragment.newInstance(imageList);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.galleryFrame, fragment, "fragment_gallery")
					.commit();
		}
	}
}
