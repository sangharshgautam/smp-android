package uk.co.sangharsh.smp.subwise.ui.active_subscription;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.sangharsh.smp.subwise.R;
import uk.co.sangharsh.smp.subwise.ui.active_subscription.placeholder.SubscriptionContent;

/**
 * A fragment representing a list of Items.
 */
public class ActiveSubscriptionFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final int REQUEST_INTERNET_PERMISSION = 100;
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActiveSubscriptionFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ActiveSubscriptionFragment newInstance(int columnCount) {
        ActiveSubscriptionFragment fragment = new ActiveSubscriptionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_subscription_list, container, false);




        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();


            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            checkInternetPermission(recyclerView);

        }
        return view;
    }
    private void checkInternetPermission(RecyclerView recyclerView) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
            new String[]{Manifest.permission.INTERNET},
                    REQUEST_INTERNET_PERMISSION);
        } else {
            // Permission already granted, proceed with network operations
            fetchSubscriptions(getActivity().getApplicationContext(), recyclerView);
        }
    }
    private void fetchSubscriptions(Context context, RecyclerView recyclerView) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://smp2.p.rapidapi.com/subscriptions";

        // Request a string response from the provided URL.
        JsonArrayRequest activeSubscriptionsRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(context, "Result received "+response.length(), Toast.LENGTH_SHORT).show();
                List<SubscriptionContent.Platform> subscriptions = new ArrayList<>();

                for(int i=0; i< response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        JSONObject platform = object.getJSONObject("platform");
                        subscriptions.add(new SubscriptionContent.Platform(String.valueOf(i), platform.getString("provider"), platform.getString("name"), platform.getString("iconUrl")));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                recyclerView.setAdapter(new ActiveSubscriptionViewAdapter(subscriptions));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("x-rapidapi-host", "smp2.p.rapidapi.com");
                headers.put("x-rapidapi-key", "YWeHyZMK5emshQOz2nrv1LO5gSuVp1FIRLwjsnzknHkdcx35JB");
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(activeSubscriptionsRequest);
    }
}