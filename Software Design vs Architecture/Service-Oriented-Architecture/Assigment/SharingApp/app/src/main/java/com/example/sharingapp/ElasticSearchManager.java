package com.example.sharingapp;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class ElasticSearchManager {
    private static final String SERVER = "http://34.202.206.222:8080";
    private static final String INDEX = "20103826";
    private static final String ITEM_TYPE = "items";
    private static final String USER_TYPE = "users";
    private static final String BID_TYPE = "bids";
    private static JestDroidClient client;

    public static class AddBidTask extends AsyncTask<Bid, Void, Void> {
        @Override
        protected Void doInBackground(Bid... bids) {
            verifySettings();
            for (Bid bid : bids) {
                Index index = new Index.Builder(bid).index(INDEX).type(BID_TYPE).build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        bid.setBidId(result.getId());
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the bids");
                }
            }
            return null;
        }
    }

    public static class GetBidListTask extends AsyncTask<String, Void, ArrayList<Bid>> {
        @Override
        protected ArrayList<Bid> doInBackground(String... search_parameters) {
            verifySettings();
            ArrayList<Bid> bids = new ArrayList<Bid>();
            String query = "{\"query\":{\"match_all\":{}}}";
            Search search = new Search.Builder(query).addIndex(INDEX).addType(BID_TYPE).build();
            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Bid> foundBids = result.getSourceAsObjectList(Bid.class);
                    bids.addAll(foundBids);
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the server");
            }
            return bids;
        }
    }

    public static class RemoveBidTask extends AsyncTask<Bid, Void, Void> {
        @Override
        protected Void doInBackground(Bid... bids) {
            verifySettings();
            for (Bid bid : bids) {
                try {
                    client.execute(new Delete.Builder(bid.getBidId()).index(INDEX).type(BID_TYPE).build());
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the bids");
                }
            }
            return null;
        }
    }

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder(SERVER);
            DroidClientConfig config = builder.build();
            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}