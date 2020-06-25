package ir.papiloo.familyfund;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class APIGettingPosts {
    private Context context;

    public APIGettingPosts(Context context){
        this.context= context;
    }

    public void getPost(final OnPostsReceived onPostsReceived){
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://Papiloo.ir/Papiloo/CashDesk/getposts.php",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        onPostsReceived.onReceived(ParsingPostJSON(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Get_Error: ", error.toString());
                onPostsReceived.onReceived(null);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(8000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    private Posts ParsingPostJSON(JSONArray response){
        try {
            JSONArray postArray= response.getJSONArray(0);
            Posts posts= new Posts();
            posts.setTitle(postArray.getString(0));
            posts.setFullPost(postArray.getString(1));
            return posts;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface OnPostsReceived{
        void onReceived(Posts posts);
    }
}
