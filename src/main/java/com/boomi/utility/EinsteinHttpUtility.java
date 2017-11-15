package com.boomi.utility;

// standard imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// application imports
import com.boomi.helper.EinsteinHttpHelper;
import com.boomi.helper.EinsteinHttpHelper.Probability;

/**
 * Created by Chris_Timmerman on 9/22/2017.
 * <p>
 * Singleton utility class intended to send a post to sfdc einstein to post image url for facial recognition
 */
public class EinsteinHttpUtility {

    private static EinsteinHttpUtility instance;

    /**
     * Constructor
     */
    public EinsteinHttpUtility() {
    }


    /**
     * Singleton
     *
     * @return
     */
    public static synchronized EinsteinHttpUtility GetInstance() {
        if (instance == null) {
            instance = new EinsteinHttpUtility();
        }
        return instance;
    }

    /**
     * Sumbmit the post to einstein
     */
    public Probability getProbability(String url, String authKey, String modelId, String imageUrl, String uId) {
        Probability prob = null;
        EinsteinHttpHelper helper = new EinsteinHttpHelper(url, authKey, modelId, imageUrl, uId);
        prob = helper.Post();
        return prob;
    }
}
