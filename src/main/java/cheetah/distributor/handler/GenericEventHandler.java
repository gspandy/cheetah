package cheetah.distributor.handler;

import cheetah.event.*;
import cheetah.exceptions.EventHandlerException;

import java.util.EventListener;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Created by Max on 2016/2/1.
 */
public class GenericEventHandler extends AbstractExpectabilityHandler {

    public GenericEventHandler(EventListener eventListener, ExecutorService executorService) {
        super(eventListener, executorService);
    }

    @Override
    public void handle(Event event) {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            if (this.getEventListener().getClass().isAssignableFrom(ApplicationListener.class)) {
                ApplicationListener applicationListener = (ApplicationListener) this.getEventListener();
                ApplicationEvent $event = (ApplicationEvent) event;
                applicationListener.onApplicationEvent($event);
                return Boolean.TRUE;
            } else if (this.getEventListener().getClass().isAssignableFrom(DomainEventListener.class)) {
                DomainEventListener domainEventListener = (DomainEventListener) this.getEventListener();
                DomainEvent $event = (DomainEvent) event;
                domainEventListener.onDomainEvent($event);
                return Boolean.TRUE;
            } else
                throw new EventHandlerException("[cheetah-distributor] : Generic event handler handle type error");
        }, getExecutorService());
        this.setFuture(future);
    }

}