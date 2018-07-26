#!/usr/bin/env bash

# Delete old images
sudo docker rmi $(sudo docker images | grep "july" | awk '{print $3}')

# Package new image
mvn clean package

# Login
sudo docker login --username=develop@35482511 registry.cn-qingdao.aliyuncs.com

# Push image to the aliyun docker hub
sudo docker push registry.cn-qingdao.aliyuncs.com/syph/july:latest
