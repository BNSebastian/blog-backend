package freevoice.features.websocket.models;

public interface Subscriber<T> {
    /**
     * The first event that the Subscriber will receive is through a call to onSubscribe().
     * When the Publisher calls onSubscribe(), it passes a Subscription object
     * to the Subscriber. It’s through the Subscription that the Subscriber can manage
     * its subscription
     */
    void onSubscribe(Subscription sub);

    /**
     * Once the Subscriber has requested data, the data starts flowing through the
     * stream. For every item that’s published by the Publisher, the onNext() method will
     * be called to deliver the data to the Subscriber
     */
    void onNext(T item);


    void onError(Throwable ex);

    /**
     * If the Publisher has no more data to send and isn’t going to produce any
     * more data, it will call onComplete()
     */
    void onComplete();
}