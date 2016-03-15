package cheetah.fighter.governor;

import cheetah.commons.utils.Assert;
import cheetah.commons.utils.IDGenerator;
import cheetah.fighter.event.Event;
import cheetah.fighter.handler.Feedback;
import cheetah.fighter.handler.Handler;

import java.util.EventListener;
import java.util.Map;

/**
 * Created by Max on 2016/3/2.
 */
public abstract class AbstractGovernor implements Governor {
    private String id;
    private boolean fisrtSucceed;
    private boolean needResult;
    private Event event;
    private Map<Class<? extends EventListener>, Handler> handlerMap;

    @Override
    public Governor reset() {
        this.handlerMap = null;
        this.fisrtSucceed = false;
        this.needResult = false;
        this.event = null;
        return this;
    }

    @Override
    public Governor initialize() {
        this.id = IDGenerator.generateId();
        this.fisrtSucceed = false;
        this.needResult = false;
        return this;
    }

    @Override
    public Feedback command() {
        return notifyAllWorker();
    }

    protected abstract Feedback notifyAllWorker();

    @Override
    public Governor registerEvent(Event $event) {
        this.event = $event;
        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Governor setFisrtSucceed(boolean $fisrtSucceed) {
        this.fisrtSucceed = $fisrtSucceed;
        return this;
    }

    @Override
    public Governor registerHandlerSquad(Map<Class<? extends EventListener>, Handler> handlerMap) {
        this.handlerMap = handlerMap;
        return this;
    }

    @Override
    public Governor setNeedResult(boolean $needResult) {
        this.needResult = $needResult;
        return this;
    }

    @Override
    public void expelHandler(Handler handler) {
        Assert.notNull(handler, "handler must not be null");
        handlerMap.remove(handler.getEventListener().getClass());
    }

    @Override
    public Governor kagebunsin() throws CloneNotSupportedException {
        return (Governor) super.clone();
    }

    public String id() {
        return id;
    }

    public Boolean fisrtSucceed() {
        return fisrtSucceed;
    }

    public Boolean needResult() {
        return needResult;
    }

    protected Map<Class<? extends EventListener>, Handler> handlerMap() {
        return handlerMap;
    }

    protected Event event() {
        return event;
    }
}
