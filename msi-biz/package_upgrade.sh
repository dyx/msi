#!/bin/bash
# $1 模式，1后端 2前端 其他值为全部

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
cp ./deploy/base/* ./deploy/upgrade/* msi_package

if [ $1 ] ;then
  if [ $1 = 1 ] ;then
    backend
  elif [ $1 = 2 ] ;then
   frontend
  else
    backend
    frontend
  fi
else
    backend
    frontend
fi

zip -r -m msi.zip msi_package
