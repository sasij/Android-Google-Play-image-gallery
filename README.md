# Image Gallery like Google Play for Android

Simple photo gallery using viewPager. The effect is similar to the Google Play gallery. I overwrite the getPageWidth method of the FragmentStatePagerAdapter. The gallery accommodates both vertical and horizontal images automatically. To achieve this, you must include the width and height of the images in the json object

You can wath a sample video [here](http://youtu.be/KqUdL4FJ5YQ)

##Usage

If you download your images from URL, your json should be something as:

```json
[
	{
      width: 400,
      height: 200,
      imageUrl: "http://lorempixel.com/400/200/sports/0"
    },
	{
      width: 400,
      height: 200,
      imageUrl: "http://lorempixel.com/400/200/sports/1"
	},
	{
      width: 400,
      height: 200,
      imageUrl: "http://lorempixel.com/400/200/sports/2"
	},
	{
      width: 200,
      height: 400,
      imageUrl: "http://lorempixel.com/200/400/sports/3"
	},
	{
      width: 400,
      height: 200,
      imageUrl: "http://lorempixel.com/400/200/sports/4"
	}
]
```
You can use my API fake in order to test your code.
http://fakapi.eu01.aws.af.cm/v2/portals/personal_juegos_android/sample_images/

In order to adapt the images, you need to indicate to the gallery, the height and width of images. It is for this reason that we have to add the height and width.

You only need copy in your project the fragment, adapter and model folder and set in your dimens.xml a value for the gallery height. Something like this

```xml
<dimen name="screenshots_height">160dp</dimen>
```
After that, include in your layout the viewPager
```xml
    <FrameLayout
        android:id="@+id/galleryFrame"
        android:layout_width="match_parent"
        android:layout_height="@dimen/screenshots_height" />

```
and finally replace the fragment in your activity or fragment

```java
Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment_gallery");

		if (fragment == null && imageList != null) {

			fragment = GalleryFragment.newInstance(imageList);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.galleryFrame, fragment, "fragment_gallery")
					.commit();
		}
```

Feel free to contact me if you have any problem.
