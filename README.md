# mockk-poc

![mockk-logo](media/mockk-logo.png) ![kotlin-logo](media/kotlin-logo.png)

MockK is a very nice Mock Library for Kotlin. At first glance it is very reminiscent of Mockito. But more in-depth info on the available features, we realized that it is much more powerful.

This project is a POC about MockK. Some features have been tested, and the examples will be demonstrated below. Use the links to access sample code. Access the [official documentation](https://mockk.io/) to check all features in detail.

## Creating Mocks

Mocks can be created using a [method](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/chainedmock/PersonTest.kt#L12) or [anottation](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/injectmocks/autowired/NotificationSenderTest.kt#L26). When use method the property can be a `val` (constant). If you use annotation the property must be a `lateinit var` because the library will create the instance at some point after the property is declared.

By default, all methods of a mocked class must be mocked. If you want to change this behavior it is necessary to use the relaxed option. This way, methods that you don't mock will return default values (null for values and new mocks for complex objects). Relaxed option can be configured in the `mockk` method or via annotation `@RelaxedMockK`. To perform this configuration in the entire file use [the option](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/injectmocks/autowired/NotificationSenderTest.kt#L30) `relaxed = true` when initializing the mocks.

## Mocking Behavior and Chained Mocks

Mocks can have the behavior of their methods manipulated for testing purposes. Similar to Mockito MockK [allows this method manipulation](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/chainedmock/PersonTest.kt#L11), but a very interesting feature that it adds is the Mocks chaining easily. .

Each Mock of a complex type is already configured to return a Mock of the corresponding type, [allowing behavior change in chained methods](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/chainedmock/PersonTest.kt#L21).

## Verifying Calls

MockK offers an extensive API to verify method calls in tests. This is very useful in cases where the methods under test do not have an output to be verified. Or even when it is important to check if a method was actually invoked during execution.

Some examples are [simple verification](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/verify/VerifyTest.kt#L29), [using matches](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/verify/VerifyTest.kt#L38), [verify number of call on specific method](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/verify/VerifyTest.kt#L47) and [order that the methods are called](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/verify/VerifyTest.kt#L57). 

## Capturing Arguments

Sometimes it is necessary to check the arguments used when calling a method. For this, MockK provides the [capture method](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/capturing/CapturingArgumentsTest.kt#L34), which requires the prior creation of a [slot of the type of the captured argument](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/capturing/CapturingArgumentsTest.kt#L33).

This makes it possible to access the arguments captured from a [simple method call](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/capturing/CapturingArgumentsTest.kt#L38), or from [all calls made to the method](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/capturing/CapturingArgumentsTest.kt#L54).

## Mocking object instantiation

This is a very useful feature to be used when new instances are created in the test method, and you need to have some control over their behavior.

Using the [mockkConstructor](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/mocknewobject/CalculatorTest.kt#L12) method together with the [every](https://github.com/robsonbittencourt/mockk-poc/blob/main/src/test/kotlin/com/rbittencourt/mockkpoc/mocknewobject/CalculatorTest.kt#L13) MockK allows you to change the behavior of a class constructor so that it returns a Mock when it is instantiated instead of the actual instance of the class.