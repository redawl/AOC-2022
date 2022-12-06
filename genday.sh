#!/usr/bin/env bash

DAY=$1

create_package(){
  cp -r "src/main/java/aocdayTEMPLATE" "src/main/java/aocday${DAY}"
  mv src/main/java/aocday"$DAY"/AOCDay{TEMPLATE,"$DAY"}.java

  cp -r "src/test/java/aocdayTEMPLATE" "src/test/java/aocday${DAY}"
  mv src/test/java/aocday"$DAY"/AOCDay{TEMPLATE,"$DAY"}Tests.java
}

create_main(){
  sed -i "s/TEMPLATE/$DAY/" src/main/java/aocday"${DAY}"/AOCDay"${DAY}".java
  sed -i "s/TEMPLATE/$DAY/" src/test/java/aocday"${DAY}"/AOCDay"${DAY}"Tests.java
}

create_package
create_main
