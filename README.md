# Management System Initializer
基于SpringBoot2+Vue3前后端分离的管理系统初始化器

## 功能列表
- [x] 层级项目结构，利于项目扩展 msi/pom.xml
- [x] 基础CRUD SysUserController
- [x] 数据库字段自动填充 CustomMetaObjectHandler
- [x] 自定义拦截器 CustomInterceptor
- [x] 自定义过滤器 CustomFilter
- [x] 自动生成接口文档 Knife4jConfig
- [x] 统一接口返回值 R
- [x] 统一接口时间格式 JacksonConfig
- [x] 统一接口日志 HttpLogAop
- [ ] 统一操作日志
- [x] 统一全局异常 GlobalExceptionHandler
- [x] 多环境配置文件 application.yml>spring.profiles.active=@profileActive@
- [ ] 代码生成器
- [x] 一键脚本打包部署 package_install.sh package_upgrade.sh
  - 基于Docker和Shell脚本实现
  - 安装脚本，安装数据库、初始化数据库、前端服务、后端服务
  - 升级脚本，可通过参数配置升级前端或后端

## 主要依赖
> 后端
- spring-boot 2.7
- mybatis-plus 3.5
- mapstruct 1.5
- lombok 1.18
- hutool 5.8
- knife4j 3.0

> 前端
- Vue 3.2
- element-plus 2.2
- axios 0.27
- vue-router 4.1
- Vite 3.0

## 运行
### 环境
- Node.js 14.18+/16+ [Vite从v2迁移](https://cn.vitejs.dev/guide/migration.html)
- JDK 1.8+
- Maven 3.6+
- MySQL 8+

### 前端
```bash
cd msi-web
npm install
npm run dev
```

### 后端
- 新建msi数据库，执行数据库脚本`./msi-biz/install/1_init_ddl.sql` `./msi-biz/install/2_init_data.sql`
- 修改数据库信息为自己的配置`./msi-biz/src/main/resources/application-dev.yml``
- 运行 MsiApplication

### 访问
- 后端接口文档 `http://localhost:8001/doc.html`
- 前端 `http://localhost:8002`

## 部署
### 环境
- Docker 20+
- DockerCompose 1.29+

> 初始安装
```bash
# 1.执行打包脚本（本地）
bash package_install.sh

# 2.上传包，将msi.zip上传到要部署的服务器（本地->服务器）

# 3.执行安装脚本（服务器）
unzip msi.zip && cd msi_package && bash install.sh

# 如果安装过程中出错，可以先卸载，再重新安装
bash uninstall.sh yes && bash install.sh
```

> 升级
```bash
# 1.执行打包脚本（本地）
bash package_upgrade.sh

# 2.上传包，将msi.zip拷贝到要部署的服务器（本地->服务器）

# 3.执行升级脚本（服务器）
unzip msi.zip && cd msi_package && bash upgrade.sh
# 前后端同时升级 bash upgrade.sh  
# 后端升级 bash upgrade.sh 1
# 前端升级 bash upgrade.sh 2
```

## 待办
- Windows脚本
  - 临时方案，安装Git软件，使用`Git Bash`执行Shell脚本实现
- 参考`docker multi-stage builds`优化包体积及构建速度
