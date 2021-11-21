# 打包
docker build -t jenkinxu/mongodb-demo:1.0.0-SNAPSHOT ../.. --build-arg JAR_FILE=mongodb-demo-1.0.0-SNAPSHOT.jar -f Dockerfile
