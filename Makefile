run-spark:
	./gradlew shadowJar && \
	spark-submit --verbose \
	--class com.github.geoheil.sparkvectorization.SparkVectorization \
	--master 'local[*]' \
	--driver-memory 8G \
	build/libs/spark-asciigrid-all.jar

run-java:
	./gradlew shadowJar && \
	java -jar build/libs/spark-asciigrid-all.jar

replSparkShell:
	./gradlew shadowJar && \
	spark-shell --master 'local[2]' \
	--jars build/libs/spark-asciigrid-all.jar