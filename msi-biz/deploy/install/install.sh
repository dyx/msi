#!/bin/bash

# 构建基础镜像
docker build -f jdk.base.Dockerfile -t msi-jdk .

mkdir -p ~/msi/mysql/init && cp ./*.sql ~/msi/mysql/init
mkdir -p ~/msi/mysql/conf && cp ./*.cnf ~/msi/mysql/conf
mkdir -p ~/msi/nginx/conf.d && cp ./*.conf ~/msi/nginx/conf.d

docker-compose -p msi up -d

docker stop msi-biz
docker stop msi-web

echo "**********正在启动后端服务，请耐心等待**********"
docker start msi-biz
sleep 5
echo "**********后端服务启动成功**********"

if [ -d ./dist ]; then
echo "**********正在启动前端服务，请耐心等待**********"
rm -rf ~/msi/nginx/html/*
cp -r ./dist/* ~/msi/nginx/html/
docker start msi-web
  echo "**********前端服务启动成功，访问地址 http://localhost:8002 **********"
fi

echo "**********安装完成**********"
