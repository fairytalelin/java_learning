package com.claylin.rpc.registry.zookeeper;

import com.claylin.rpc.registry.ServiceDiscovery;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 基于 ZooKeeper 的服务发现接口实现
 */
public class ZooKeeperServiceDiscovery implements ServiceDiscovery {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperServiceDiscovery.class);

    private String zkAddress;


    public ZooKeeperServiceDiscovery(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    @Override
    public String discover(String serviceName) {
        // 创建 ZooKeeper客户端
        ZkClient zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
        LOGGER.debug("connect zookeeper");

        try {
            String servicePath = Constant.ZK_REGISTRY_PATH + "/" + serviceName;
            if (!zkClient.exists(servicePath)) {
                throw new RuntimeException(String.format("can not find any service node on path %s", servicePath));
            }

            List<String> adderssList = zkClient.getChildren(servicePath);
            if (adderssList == null || adderssList.isEmpty()) {
                throw new RuntimeException(String.format("can not find any address node non path: %s", servicePath));
            }

            String address;
            int size = adderssList.size();
            if (size == 1) {
                address = adderssList.get(0);
                LOGGER.debug("get only address node: {}", address);
            } else {
                // 若存在多个地址, 则随机获取一个地址
                address = adderssList.get(ThreadLocalRandom.current().nextInt(size));
                LOGGER.debug("get random address node: {}", address);
            }
            String addressPath = servicePath + "/" + address;
            return zkClient.readData(addressPath);
        } finally {
            zkClient.close();
        }

    }
}
