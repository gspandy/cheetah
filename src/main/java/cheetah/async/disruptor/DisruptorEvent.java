package cheetah.async.disruptor;

import cheetah.worker.Command;

/**
 * Created by Max on 2016/2/29.
 */
public class DisruptorEvent {
    private Command command;

    public void set(Command command) {
        this.command = command;
    }

    public Command get() {
        return this.command;
    }
}
