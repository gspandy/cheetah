package cheetah.event;

/**
 * Created by Max on 2016/1/29.
 */
public interface SmartDomainEventListener<E extends DomainEvent> extends DomainEventListener {
    boolean supportsEventType(Class<? extends DomainEvent> eventType);
}
