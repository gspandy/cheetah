package cheetah.mapper.support;

import cheetah.handler.Handler;
import cheetah.mapper.Mapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 机器映射器
 * 在调度中心接受到一个事件后，调度会将其封装为一个Handler， 将其存到本映射器中。
 * 调度中每次都会检查映射器中有没有对应的工作机器，没有则创建，有则直接获取，不会每次都会创建
 * Created by Max on 2016/2/23.
 */
public class HandlerMapper implements Mapper {
    private volatile Map<HandlerMapperKey, Map<Class<? extends EventListener>, Handler>> handlerMapper = new ConcurrentHashMap<>();
    private static final Mapper genericMapper = new HandlerMapper();

    public static Mapper getGenericMapper() {
        return genericMapper;
    }

    @Override
    public Map<Class<? extends EventListener>, Handler> getMachine(HandlerMapperKey mapperKey) {
        return isExists(mapperKey) ? new HashMap<>(handlerMapper.get(mapperKey)) : Collections.EMPTY_MAP;
    }

    @Override
    public void put(HandlerMapperKey mapperKey, Map<Class<? extends EventListener>, Handler> machines) {
        if (machines.isEmpty()) {
            return;
        }
        handlerMapper.put(mapperKey, machines);
    }

    @Override
    public Set<HandlerMapperKey> mapperKeys() {
        return handlerMapper.keySet();
    }

    @Override
    public boolean isExists(HandlerMapperKey mapperKey) {
        return handlerMapper.containsKey(mapperKey);
    }
}
