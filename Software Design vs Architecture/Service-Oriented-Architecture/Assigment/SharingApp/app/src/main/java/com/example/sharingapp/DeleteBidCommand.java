package com.example.sharingapp;

import android.content.Context;

/**
 * Command to delete a bid
 */
public class DeleteBidCommand extends Command {

    private Bid bid;

    public DeleteBidCommand(Bid bid) {
        this.bid = bid;
    }

    // Delete bid from remote server
    public void execute(){
        ElasticSearchManager.RemoveBidTask remove_bid_task = new ElasticSearchManager.RemoveBidTask();
        remove_bid_task.execute(bid);

        try {
            remove_bid_task.get();
            super.setIsExecuted(true);
        } catch (Exception e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}