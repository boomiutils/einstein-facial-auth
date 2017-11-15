package com.boomi;

// junit imports
import com.boomi.helper.EinsteinHttpHelper;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

// java imports
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// application imports
import com.boomi.utility.EinsteinHttpUtility;
import com.boomi.helper.EinsteinHttpHelper.Probability;

/**
 * Unit test for Einstein facial recoginition test.
 */
public class EinsteinTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EinsteinTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(EinsteinTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        System.out.println("Starting Tests");
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Action", "ListUsers");
        params.put("Version", "2010-05-08");

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        headers.put("X-Amz-Date", "20150830T123600Z");
        headers.put("Host", "iam.amazonaws.com");

        // create the signing utility class
        EinsteinHttpUtility util = EinsteinHttpUtility.GetInstance();
        Probability prob = util.getProbability("https://api.einstein.ai/v2/vision/predict",
                                                "AUTHTOKEN",
                                                "MODELID FROM EINSTEIN",
                                                "IMAGE URL",
                                                "LABEL OF DATASET PROBABILITY");

        System.out.println("\n<TEST RESULT> Returned Label :" + prob.label + " Returned Probability :"+ Float.toString(prob.probability));

        assertTrue(true);
    }
}
