package freevoice.features.websocket.models;

public interface Subscription {
    /**
     * When calling request(), the Subscriber passes in a long value to indicate
     * how many data items it’s willing to accept.
     */
    void request(long n);


    /**
     * indicate that it’s no longer interested in receiving data and is canceling the subscription
     */
    void cancel();
}