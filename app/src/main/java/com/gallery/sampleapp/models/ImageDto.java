package com.gallery.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by juanjo on 27/05/14.
 */
public class ImageDto implements Parcelable, Serializable {

	@SerializedName("width")
	private int mWidth;
	@SerializedName("height")
	private int mHeight;
	@SerializedName("imageUrl")
	private String mUrl;

	public ImageDto() {

	}

	public ImageDto(Parcel in) {
		mWidth = in.readInt();
		mHeight = in.readInt();
		mUrl = in.readString();
	}

	public int getWidth() {
		return mWidth;
	}

	public void setWidth(int mWidth) {
		this.mWidth = mWidth;
	}

	public int getHeight() {
		return mHeight;
	}

	public void setHeight(int mHeight) {
		this.mHeight = mHeight;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	/**
	 * Describe the kinds of special objects contained in this Parcelable's
	 * marshalled representation.
	 * 
	 * @return a bitmask indicating the set of special object types marshalled
	 *         by the Parcelable.
	 */
	@Override
	public int describeContents() {
		return 0;
	}

	/**
	 * Flatten this object in to a Parcel.
	 * 
	 * @param dest
	 *            The Parcel in which the object should be written.
	 * @param flags
	 *            Additional flags about how the object should be written. May
	 *            be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mWidth);
		dest.writeInt(mHeight);
		dest.writeString(mUrl);
	}

	/**
	 * Parcelable creator
	 */
	public static final Parcelable.Creator<ImageDto> CREATOR = new Parcelable.Creator<ImageDto>() {
		public ImageDto createFromParcel(Parcel in) {
			return new ImageDto(in);
		}

		public ImageDto[] newArray(int size) {
			return new ImageDto[size];
		}
	};
}
