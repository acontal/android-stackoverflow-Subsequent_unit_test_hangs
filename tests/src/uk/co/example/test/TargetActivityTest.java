package uk.co.example.test;

import uk.co.example.activity.TargetActivity;
import android.app.*;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.*;

public class TargetActivityTest extends ActivityInstrumentationTestCase2<TargetActivity> {

	TargetActivity mActivity;
	ListView mResultList;
	TextView mTextField;
	ArrayAdapter<String> mAdapter;

	public TargetActivityTest() {
		super(TargetActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
		mResultList = (ListView) mActivity.findViewById(android.R.id.list);
		mTextField = (TextView) mActivity.findViewById(uk.co.example.R.id.txtResultViewHeader);
		String items[] = { "1", "2" };
		mAdapter = new ArrayAdapter<String>(mActivity, android.R.id.list, items);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testStartsIntent() {
		Instrumentation inst = getInstrumentation();
		Intent intent = new Intent(mActivity, uk.co.example.activity.TargetActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ActivityMonitor monitor = inst.addMonitor(uk.co.example.activity.TargetActivity.class.getName(), null, false);
		inst.startActivitySync(intent);
		monitor.waitForActivityWithTimeout(2000);
		assertEquals(1, monitor.getHits());
		Activity randomActivity = monitor.getLastActivity();
		inst.removeMonitor(monitor);
	}

	public final void testPreconditions() {
		assertNotNull(mActivity);
	}
}
