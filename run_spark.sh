#!/usr/bin/env bash

case "$#" in
    (0)
        FLAG=local
        ;;
    (1)
        FLAG=$1
        ;;
    (*)
        echo $0: "usage: ./run_spark.sh [local|bigdata]"
        exit 1
        ;;
esac


case ${FLAG} in
    (local)
        # Run application locally
        SUBMIT="/usr/local/spark-2.0.0/bin/spark-submit"
        MASTER='local[2]'
        PWORD_LENGTH=4
        NUM_CHARS=26
        ;;
    (bigdata)
        ml purge
        ml load Spark/2.2.0

        # Run application on YARN
        SUBMIT="spark-submit"
        MASTER='yarn'
        EXTRA='--executor-memory 10G --executor-cores 5'
        PWORD_LENGTH=5
        NUM_CHARS=26
        ;;
    (*)
        echo "Invalid FLAG option [$FLAG]"
        exit
        ;;
esac


${SUBMIT} \
  --class edu.vanderbilt.accre.passwords.PasswordCrackerApp \
  --master ${MASTER} \
  ${EXTRA} \
  target/scala-2.11/password-cracker_2.11-1.0.jar \
  ${PWORD_LENGTH} \
  ${NUM_CHARS}
