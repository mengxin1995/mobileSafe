package com.my.mobilesafe;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.my.mobilesafe.db.dao.BlackNumberDao;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test() throws Exception {
        final int expected = 5;
        final int reality = 5;
        assertEquals(expected, reality);
    }
    public void test_insert(){
        BlackNumberDao dao = BlackNumberDao.getInstance(getContext());
        dao.insert("1860000000", "1");
    }
}