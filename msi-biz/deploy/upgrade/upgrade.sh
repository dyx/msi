#!/bin/bash

if [ -f *.jar ]; then
echo "**********正在升级后端服务，请耐心等待**********"
docker-compose -p msi build backend
# 删除dangling image
docker rmi $(docker images -q -f dangling=true)
docker-compose -p msi up -d --no-deps backend
echo "**********后端服务升级完成**********"
fi

if [ -d ./dist ]; then
echo "**********正在升级前端服务，请耐心等待**********"
rm -rf ~/msi/nginx/html/*
cp -r ./dist/* ~/msi/nginx/html/
echo "**********前端服务升级完成，访问地址 http://localhost:8002 **********"
fi
