package com.mokelock.graduation_project.services;

public interface MainServices {

    String getMsgFromDocker();

    String createContainers(String containerName, String imageName);

    String startContainer(String containerID);

    String stopContainer(String containerID);

    String removeContainer(String containerID);

}
