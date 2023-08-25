REPOSITORY=/home/ec2-user/action
PROJECT_NAME=birca-0.0.1-SNAPSHOT.jar

cd $REPOSITORY/$PROJECT_NAME/

echo "> Git Pull"

git pull

echo "> 프로젝트 Build 시작"

./gradlew clean build --exclude-task test

echo "> 서브모듈 설정파일 가져오기"

git submodule update

echo "> step1 디렉토리 이동"

cd $REPOSITORY

echo "> Build 파일 복사"

cp $REPOSITORY/$PROJECT_NAME.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f birca-0.0.1-SNAPSHOT.jar)

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
   echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ec2-user/action/deploy.log
else
   echo "> kill -15 $CURRENT_PID"
   kill -15 $CURRENT_PID
   sleep 5
fi

echo "> 새 애플리케이션 배포" >> /home/ec2-user/action/deploy.log

nohup java -jar $REPOSITORY/birca-0.0.1-SNAPSHOT.jar 2>&1 &