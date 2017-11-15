package com.boomi.helper;

// apache imports
import org.apache.http.client.*;
import org.apache.http.entity.mime.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.JSONArray;

/**
 * Created by Chris_Timmerman on 9/26/2017.
 */
public class EinsteinHttpHelper {
    private String url;
    private String authKey;
    private String modelId;
    private String imageUrl;
    private String userId;

    /**
     * Constructor
     */
    public EinsteinHttpHelper(String u, String aKey, String mId, String iUrl, String uId) {
        authKey = aKey;
        modelId = mId;
        url = u;
        imageUrl = iUrl;
        userId = uId;

    }

    /**
     * Returns the signature for version 4
     */
    public Probability Post() {
        Probability prob = new Probability();
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.addHeader("Authorization", authKey);

            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addTextBody("sampleLocation", imageUrl, ContentType.TEXT_PLAIN)
                    .addTextBody("modelId", modelId, ContentType.TEXT_PLAIN)
                    .build();
            post.setEntity(entity);
            HttpResponse response = httpclient.execute(post);
            HttpEntity respEntity = response.getEntity();

            if (respEntity != null) {
                String content =  EntityUtils.toString(respEntity);
                JSONObject obj = new JSONObject(content);

                JSONArray array = obj.getJSONArray("probabilities");
                for(int i = 0 ; i < array.length() ; i++){
                    String label = array.getJSONObject(i).getString("label");
                    if (label.equalsIgnoreCase(userId)){
                        prob.SetProbability(Float.parseFloat(array.getJSONObject(i).getString("probability")));
                        prob.SetLabel(userId);
                    }
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return prob;
    }

    /**
     *
     */
    public class Probability {
        public float probability = 0.0f;
        public String label = "None";

        // default constructor
        public Probability(){
        }

        // constructor
        public Probability(float p, String s){
            probability = p;
            label = s;
        }

        // Getter
        public float GetProbability(){return probability;}
        public String GetLable(){return label;}

        // Setter
        public void SetProbability(float p){probability = p;}
        public void SetLabel(String s){label = s;}
    }
}
