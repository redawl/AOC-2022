#!/usr/bin/env bash

DAY=$1

create_package(){
  cp -r "src/main/java/aocdayTEMPLATE" "src/main/java/aocday${DAY}"
  mv src/main/java/aocday"$DAY"/AOCDay{TEMPLATE,"$DAY"}.java
}

create_main(){
  sed -i "s/TEMPLATE/$DAY/" src/main/java/aocday"${DAY}"/AOCDay"${DAY}".java
}

create_package
create_main
