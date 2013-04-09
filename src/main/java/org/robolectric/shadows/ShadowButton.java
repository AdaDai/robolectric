package org.robolectric.shadows;

import android.widget.Button;
import org.robolectric.internal.Implements;

@Implements(value = Button.class, inheritImplementationMethods = true)
public class ShadowButton extends ShadowTextView {

}
