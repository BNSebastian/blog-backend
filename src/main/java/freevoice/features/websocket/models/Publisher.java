package freevoice.features.websocket.models;

/**
 * The Publisher interface declares a
 * single method, subscribe(), through which a Subscriber can subscribe to the Publisher
 */
public interface Publisher<T> {

    /**
     * Once a Subscriber has subscribed, it can receive events from the Publisher
     */
    void subscribe(Subscriber<? super T> subscriber);
}