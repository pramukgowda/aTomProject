#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
rm -rf out dist && mkdir -p out dist
find src/main/java -name '*.java' -print0 | xargs -0 javac --release 17 -encoding UTF-8 -Xlint:all -d out
jar --create --file dist/HotelBookingSystem.jar --main-class app.Main -C out .
echo "Created dist/HotelBookingSystem.jar"
