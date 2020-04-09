package com.mokelock.graduation_project.model;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

public class SingletonDockerClient {

    private static SingletonDockerClient singletonDockerClient;
    private static DockerClient dockerClient;

    private SingletonDockerClient() {
    }

    public static SingletonDockerClient getInstance() {
        try {
            if (null == singletonDockerClient) {
                synchronized (SingletonDockerClient.class) {
                    if (null == singletonDockerClient) {
                        singletonDockerClient = new SingletonDockerClient();
                        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                                .withDockerHost("tcp://localhost:2376")
                                .withDockerTlsVerify(true)
                                .withDockerCertPath("/home/user/.docker/certs")
                                .withDockerConfig("/home/user/.docker")
                                .withApiVersion("1.30") // optional
                                .withRegistryUrl("https://index.docker.io/v1/")
                                .withRegistryUsername("mlock")
                                .withRegistryPassword("sQEZcSLMqVMS4PE")
                                .withRegistryEmail("541257940@qq.com")
                                .build();
                        dockerClient = DockerClientBuilder.getInstance(config).build();
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (null == dockerClient) {
            System.out.println("null");
        }
        return singletonDockerClient;
    }

    public DockerClient getDockerClient() {
        return dockerClient;
    }

}
