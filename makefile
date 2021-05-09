PROJECT_DIR=$(shell pwd)

ARTIFACT_REVISION=1.0
ARTIFACT_NAME=twain
ARTIFACT=$(PROJECT_DIR)/build/$(ARTIFACT_NAME)-$(ARTIFACT_REVISION).jar

$(ARTIFACT) :
	./gradlew clean bootJar

buildApp: $(ARTIFACT)/

build: buildApp
	docker build -t kheir/calc:$(ARTIFACT_REVISION) .

run:
	docker run -p 8080:8080 kheir/calc:1.0
