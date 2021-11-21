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
2. ProjectSDK はJDK17を指定
   ![overview image](image/01.png?raw=true)
   ![overview image](image/02.png?raw=true)
   [misc.xml#L7](../.idea/misc.xml#L7)
4. docker-composeよりMysql/Redisを起動
   `$ docker-compose -f ./docker/mac/docker-compose.yml up -d --build`
5. アプリケーションをbootRun
    * GradleタスクよりbootRun(`:micro-api -> Tasks -> application -> bootRun`)
    * DockerImageを作成して起動
        1. GradleタスクよりjibDockerBuild(`:micro-api -> Tasks -> jib -> jibDockerBuild`)
           `$ ./gradlew :micro-api:jibDockerBuild`
        1. Dockerより起動
           `$ docker run --name micro-api --network mac_app-net --rm -p 9001:9001 -it micro-api:d141aa2.dirty`

6. Gradleタスクよりstart(`:micro-site -> Tasks -> other -> startDevelopment`)

### Endpoints

[Springdoc(Api)][]
[Springdoc(Site)][]
[Springdoc(Admin)][]
[Site(Riot)][]
[Admin(Vue)][]
[phpMyAdmin][]
[Redisinsight][]
[Swagger-UI][]
[ReDoc][]

## 停止方法

1. アプリケーションの停止
    * bootRunの停止
    * Dockerより停止
      `$ docker stop micro-api`
1. docker-composeより停止
   `$ docker-compose -f ./docker/mac/docker-compose.yml stop`
1. docker-composeよりコンテナ破棄  
   `$ docker-compose -f ./docker/mac/docker-compose.yml down -v`

[Springdoc(Api)]: http://localhost:9001/CatCafeApi/swagger-ui.html            "Springdoc(Api)"

[Springdoc(Site)]: http://localhost:9011/CatCafeSite/swagger-ui.html          "Springdoc(Site)"

[Springdoc(Admin)]: http://localhost:9021/CatCafeAdmin/swagger-ui.html        "Springdoc(Admin)"

[Site(Riot)]: http://localhost:9011/CatCafeSite/                              "Site(Riot)"

[Admin(Vue)]: http://localhost:9021/CatCafeAdmin/                             "Admin(Vue)"

[phpMyAdmin]: http://localhost:8021/                                          "phpMyAdmin"

[Redisinsight]: http://localhost:8001/                                        "Redisinsight"

[Swagger-UI]: http://localhost:8002/                                          "Swagger-UI"

[ReDoc]: http://localhost:8081/                                               "ReDoc"