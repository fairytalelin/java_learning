package com.claylin.rpc.registry;

/**
 * 服务发现接口
 */
public interface ServiceDiscovery {

    /**
     * 根据服务名称查找服务地址
     *
     * @param serviceName 服务名称
     * @return
     */
    String discover(String serviceName);
}
