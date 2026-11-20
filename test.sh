#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
rm -rf out && mkdir -p out
find src/main/java src/test/java -name '*.java' -print0 | xargs -0 javac --release 17 -encoding UTF-8 -Xlint:all -d out
java -ea -cp out tests.AllTests
