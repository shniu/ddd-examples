
sudo docker build -t registry.cn-qingdao.aliyuncs.com/syph/july-ui:0.1 .

# Login
sudo docker login --username=develop@35482511 registry.cn-qingdao.aliyuncs.com

# Push image to the aliyun docker hub
sudo docker push registry.cn-qingdao.aliyuncs.com/syph/july-ui:0.1
