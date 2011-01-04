package com.xtremelabs.robolectric.shadows;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.xtremelabs.robolectric.R;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.WithTestDefaultsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.test.MoreAsserts.assertNotEqual;
import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(WithTestDefaultsRunner.class)
public class BitmapDrawableTest {
    private Resources resources;

    @Before
    public void setUp() throws Exception {
        Robolectric.bindDefaultShadowClasses();

        Application application = new Application();
        resources = application.getResources();
    }

    @Test
    public void getBitmap_shouldReturnBitmapUsedToDraw() throws Exception {
        BitmapDrawable drawable = (BitmapDrawable) resources.getDrawable(R.drawable.an_image);
        assertEquals("Bitmap for resource: drawable/an_image", shadowOf(drawable.getBitmap()).getOrigin());
    }

    @Test
    public void draw_shouldCopyDescriptionToCanvas() throws Exception {
        BitmapDrawable drawable = (BitmapDrawable) resources.getDrawable(R.drawable.an_image);
        Canvas canvas = new Canvas();
        drawable.draw(canvas);

        assertThat(shadowOf(canvas).getDescription(), containsString("Bitmap for resource: drawable/an_image"));
    }
    
    @Test
    public void withColorFilterSet_draw_shouldCopyDescriptionToCanvas() throws Exception {
        BitmapDrawable drawable = (BitmapDrawable) resources.getDrawable(R.drawable.an_image);
        drawable.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix()));
        Canvas canvas = new Canvas();
        drawable.draw(canvas);

        String canvasDescription = shadowOf(canvas).getDescription();
        assertThat(canvasDescription, containsString("Bitmap for resource: drawable/an_image"));
        assertThat(canvasDescription, containsString("with color filter: ColorMatrixColorFilter<1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0>"));
    }

    @Test
    public void equals_shouldTestResourceId() throws Exception {
        Drawable drawable1a = resources.getDrawable(R.drawable.an_image);
        Drawable drawable1b = resources.getDrawable(R.drawable.an_image);
        Drawable drawable2 = resources.getDrawable(R.drawable.an_other_image);

        assertEquals(drawable1a, drawable1b);
        assertNotEqual(drawable1a, drawable2);
    }

    @Test
    public void equals_shouldTestBounds() throws Exception {
        Drawable drawable1a = resources.getDrawable(R.drawable.an_image);
        Drawable drawable1b = resources.getDrawable(R.drawable.an_image);

        drawable1a.setBounds(1, 2, 3, 4);
        drawable1b.setBounds(1, 2, 3, 4);

        assertEquals(drawable1a, drawable1b);

        drawable1b.setBounds(1, 2, 3, 5);
        assertNotEqual(drawable1a, drawable1b);
    }

    @Test
    public void shouldStillHaveShadow() throws Exception {
        Drawable drawable = resources.getDrawable(R.drawable.an_image);
        assertEquals(R.drawable.an_image, ((ShadowBitmapDrawable) Robolectric.shadowOf(drawable)).getLoadedFromResourceId());
    }
}
