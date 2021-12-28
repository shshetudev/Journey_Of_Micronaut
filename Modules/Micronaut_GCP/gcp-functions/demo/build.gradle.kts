plugins {
    id("groovy") 
    id("io.micronaut.library") version "2.0.6"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

version = "0.1"
group = "com.example"

repositories {
    mavenCentral()
}

micronaut {
    runtime("lambda")
    testRuntime("spock2")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
}


dependencies {
    implementation("io.micronaut:micronaut-runtime")
    implementation("javax.annotation:javax.annotation-api")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")

    implementation("io.micronaut.aws:micronaut-function-aws")
    implementation("io.micronaut.gcp:micronaut-gcp-common:4.0.0")
    implementation("io.micronaut.gcp:micronaut-gcp-tracing:4.0.0")
    implementation("io.micronaut.gcp:micronaut-gcp-function")
//    implementation("io.micronaut.gcp:micronaut-gcp-http-client")

    testImplementation("io.micronaut:micronaut-function")
    testImplementation("io.micronaut:micronaut-http-client")

    compileOnly("com.google.cloud.functions:functions-framework-api:1.0.4")
}


java {
    sourceCompatibility = JavaVersion.toVersion("11")
    targetCompatibility = JavaVersion.toVersion("11")
}



tasks.named("assemble") {
    dependsOn(":shadowJar")
}