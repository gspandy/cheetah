package cheetah.distributor;

/**
 * Created by Max on 2016/2/7.
 */
public interface Worker {
    EventResult allot(EventMessage eventMessage);
}
