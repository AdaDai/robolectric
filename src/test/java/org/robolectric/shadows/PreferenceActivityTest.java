package org.robolectric.shadows;

import android.preference.PreferenceActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.R;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricConfig;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestConfigs;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class) @RobolectricConfig(TestConfigs.WithDefaults.class)
public class PreferenceActivityTest {

    private TestPreferenceActivity activity;
    private ShadowPreferenceActivity shadow;

    @Before
    public void setUp() throws Exception {
        activity = new TestPreferenceActivity();
        shadow = Robolectric.shadowOf(activity);
    }

    @Test
    public void shouldInitializeListViewInOnCreate() {
        shadow.callOnCreate(null);
        assertThat(activity.getListView(), notNullValue());
    }

    @Test
    public void shouldInheritFromListActivity() {
        assertThat(shadow, instanceOf(ShadowListActivity.class));
    }

    @Test
    public void shouldNotInitializePreferenceScreen() {
        assertThat(activity.getPreferenceScreen(), nullValue());
    }

    @Test
    public void shouldRecordPreferencesResourceId() {
        assertThat(shadow.getPreferencesResId(), equalTo(-1));
        activity.addPreferencesFromResource(R.xml.preferences);
        assertThat(shadow.getPreferencesResId(), equalTo(R.xml.preferences));
    }

    @Test
    public void shouldLoadPreferenceScreen() {
        activity.addPreferencesFromResource(R.xml.preferences);
        assertThat(activity.getPreferenceScreen().getPreferenceCount(), equalTo(7));
    }

    @Test
    public void shouldFindPreferences() {
        activity.addPreferencesFromResource(R.xml.preferences);
        assertNotNull(activity.findPreference("category"));
        assertNotNull(activity.findPreference("inside_category"));
        assertNotNull(activity.findPreference("screen"));
        assertNotNull(activity.findPreference("inside_screen"));
        assertNotNull(activity.findPreference("checkbox"));
        assertNotNull(activity.findPreference("edit_text"));
        assertNotNull(activity.findPreference("list"));
        assertNotNull(activity.findPreference("preference"));
        assertNotNull(activity.findPreference("ringtone"));
    }

    private static class TestPreferenceActivity extends PreferenceActivity {
    }
}
