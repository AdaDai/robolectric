package org.robolectric.shadows;

import android.widget.ImageButton;
import org.robolectric.internal.Implements;

@Implements(value = ImageButton.class, inheritImplementationMethods = true)
public class ShadowImageButton extends ShadowImageView {
}
