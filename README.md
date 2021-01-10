# mockk-poc

> A POC to testing Kotling mock library MockK

MockK is a very nice Mock Library for Kotlin. At first glance it is very reminiscent of Mockito. But more in-depth info on the available features, we realized that it is much more powerful.

This project is a POC about MockK. Some features have been tested, and the examples will be demonstrated below. Use the links to access sample code. Access the [official documentation](https://mockk.io/) to check all features in detail.

## Creating Mocks

Mocks can be created using a [method](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/chainedmock/PersonTest.kt#L12) or [anottation](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/injectmocks/autowired/NotificationSenderTest.kt#L26). When use method the property can be a `val` (constant). If you use annotation the property must be a `lateinit var` because the library will create the instance at some point after the property is declared.

By default, all methods of a mocked class must be mocked. If you want to change this behavior it is necessary to use the relaxed option. This way, methods that you don't mock will return default values (null for values and new mocks for complex objects). Relaxed option can be configured in the `mockk` method or via annotation `@RelaxedMockK`. To perform this configuration in the entire file use [the option](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/injectmocks/autowired/NotificationSenderTest.kt#L30) `relaxed = true` when initializing the mocks.

## Mocking Behavior and Chained Mocks

Mocks can have the behavior of their methods manipulated for testing purposes. Similar to Mockito MockK [allows this method manipulation](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/chainedmock/PersonTest.kt#L11), but a very interesting feature that it adds is the Mocks chaining easily. .

Each Mock of a complex type is already configured to return a Mock of the corresponding type, [allowing behavior change in chained methods](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/chainedmock/PersonTest.kt#L21).