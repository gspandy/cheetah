package org.cheetah.fighter.core.eventbus;

import org.cheetah.fighter.core.FighterConfig;

/**
 * 引擎主管-负责管理构建一个完整的引擎
 * Created by Max on 2016/2/19.
 */
public interface EngineDirector {

    EventBus directEngine();

    void setFighterConfig(FighterConfig fighterConfig);

}
