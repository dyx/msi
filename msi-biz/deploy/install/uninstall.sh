#!/bin/bash
# $1 卸载时是否删除数据，yes为删除 其他为不删除

projectName="msi"

docker-compose -p $projectName down
docker rmi $projectName-biz $projectName-jdk

if [ "$1" = "yes" ]; then
rm -rf ~/$projectName
fi
