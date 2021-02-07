# 開発環境
====

## IDE

IntelliJ IDEAを使用します。

### Plugin

下記の最新版をインストールします。

1. google-java-format Settings
1. Lombok Plugin

## 起動方法

1. 当プロジェクトをIntelliJ IDEAにGradleProjectとしてclone
1. docker-composeよりMysqlを起動  
   `$ cd docker/mac`  
   `$ docker-compose -f docker-compose.yml build`  
   `$ docker-compose -f docker-compose.yml up -d`
1. GradleタスクよりbootRun(`:micro-api -> Tasks -> application -> bootRun`)
1. Gradleタスクよりstart(`:micro-site -> Tasks -> other -> start`)

### Endpoints

[OpenApi(Api)][]
[OpenApi(Site)][]
[OpenApi(Admin)][]
[Site(Riot)][]
[Admin(Vue)][]
[phpMyAdmin][]
[openapi-ui][]

## 停止方法

1. bootRunの停止
1. docker-composeより停止  
   `$ cd docker/mac`  
   `$ docker-compose -f docker-compose.yml stop`
1. docker-composeよりコンテナ破棄  
   `$ docker-compose -f docker-compose.yml down`

[OpenApi(Api)]: http://localhost:9001/CatCafeApi/swagger-ui.html            "OpenApi(Api)"

[OpenApi(Site)]: http://localhost:9011/CatCafeSite/swagger-ui.html          "OpenApi(Site)"

[OpenApi(Admin)]: http://localhost:9021/CatCafeAdmin/swagger-ui.html        "OpenApi(Admin)"

[Site(Riot)]: http://localhost:9011/CatCafeSite/                            "Site(Riot)"

[Admin(Vue)]: http://localhost:9021/CatCafeAdmin/                           "Admin(Vue)"

[phpMyAdmin]: http://localhost:8021/                                        "phpMyAdmin"

[OpenApi-UI]: http://127.0.0.1:8002/                                        "openapi-ui"