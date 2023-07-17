#! /bin/bash -e

for dir in */ ; do
	if [ "$dir" == "frontend-service/" ] ; then
		continue
	fi
	(cd $dir ; ./gradlew -b build.gradle $*)
done
