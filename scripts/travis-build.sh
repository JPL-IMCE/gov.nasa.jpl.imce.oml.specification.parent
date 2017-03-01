#!/bin/bash

set -ev

# Build if TRAVIS_TAG is unset or empty.
[ -n "${TRAVIS_TAG}" ] && exit 0;

# Get the tag for this commit
t=$(git name-rev --tags --name-only $(git rev-parse HEAD))

# Bypass the build if the tag is anything but 'undefined'.
[ "undefined" != "$t" ] && exit 0;

./gradlew --version

# Download dependencies and create a mavenized target platform repository for dependency resolution
./gradlew :installTargetPlatform

# Delete all Eclipse project-related metadata
./gradlew cleanEclipse

# build the OML metamodel
./gradlew :jpl.imce.oml.specification.ecore:build

# generate Eclipse metadata
./gradlew eclipse

# generate & build the Xtext grammar
./gradlew :jpl.imce.oml.specification:build -S

# generate Eclipse metadata
./gradlew eclipse

# execute the OML specification unit tests
./gradlew :jpl.imce.oml.specification.tests:test

# generate the Scala libraries
./gradlew :gov.nasa.jpl.imce.oml.specification.scala.generators:build

# generate Eclipse metadata
./gradlew eclipse

# build the SBT projects
(cd gov.nasa.jpl.oml.specification.resolver; sbt compile)

# Use the mavenized target platform repository to build the actual eclipse projects
./gradlew build 

