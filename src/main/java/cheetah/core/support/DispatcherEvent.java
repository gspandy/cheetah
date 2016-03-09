package cheetah.core.support;

import cheetah.core.AbstractDispatcher;
import cheetah.core.EventMessage;
import cheetah.core.EventResult;
import cheetah.core.NoMapperException;
import cheetah.governor.Governor;
import cheetah.handler.Feedback;
import cheetah.handler.Handler;

import java.util.EventListener;
import java.util.Map;

/**
 * Created by Max on 2016/1/29.
 */
public class DispatcherEvent extends AbstractDispatcher {

    @Override
    public EventResult dispatch() {
        EventMessage eventMessage = context().eventMessage();
        Map<Class<? extends EventListener>, Handler> handlerMap = context().handlers();
        if (!handlerMap.isEmpty()) {
            Governor governor = engine().assignGovernor();
            Feedback report = governor.initialize()
                    .registerEvent(eventMessage.event())
                    .registerHandlerSquad(handlerMap)
                    .setFisrtSucceed(eventMessage.fisrtWin())
                    .setNeedResult(eventMessage.needResult())
                    .command();
            return new EventResult(eventMessage.event().getSource(), report.isFail());
        }
        throw new NoMapperException();
    }

}