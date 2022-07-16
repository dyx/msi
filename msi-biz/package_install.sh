#!/bin/bash

workdir=$(cd $(dirname $0); pwd)

function backend(){
  mvn clean package -Dmaven.test.skip=true -Pprod -pl com.lhd:msi-biz -am
  cp ./target/*.jar msi_package
  mvn clean
  echo "**********后端打包完成**********"
}

function frontend(){
  cd $workdir
  if [ -d ../msi-web ] ;then
    cd ../msi-web
    npm install
    npm run build
    mv ./dist/ $workdir/msi_package/dist
    echo "**********前端打包完成**********"
    cd $workdir
  fi
}

rm -rf msi.zip msi_package
mkdir -p ./msi_package
cp ./deploy/base/* ./deploy/install/* msi_package

backend
frontend

zip -r -m msi.zip msi_package
