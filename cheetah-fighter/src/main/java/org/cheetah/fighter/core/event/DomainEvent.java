package org.cheetah.fighter.core.event;


/**
 * Created by Max on 2016/1/29.
 */
public abstract class DomainEvent<T> extends Event<T> {
    public DomainEvent(T source) {
        super(source);
    }
}
