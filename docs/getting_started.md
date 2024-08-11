
# Getting Started

## Installation

To use Kanos in your project, add the following to your `build.gradle.kts` file:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.kvrae.kanos:kanos:0.1.0-DEV")
}
```

## Usage

To get the list of available commands, run the following command in your terminal:

```shell
./gradlew kanos
```

Run the following command in your terminal to delete half of your project's files:

```shell
./gradlew kanos --snap-fingers
```

Run the following command in your terminal to delete all of your project's files:

```shell
./gradlew kanos --snap-hands
```

For more information on how to contribute, see the [Contributing](contributing.md) page.