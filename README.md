# vectorizaton of images in spark

```
make run-java
IllegalArgumentException: ImageRead: No OperationDescriptor is registered in the current operation registry under this name


make run-spark
Caused by: java.lang.IllegalArgumentException: ImageRead: No OperationDescriptor is registered in the current operation registry under this name.
```



when looking at the jai registration files in [registryFilesGenerated](registryFilesGenerated) none of them sadly contains the `vectors` registration.

When executing using IntelliJ

it works just fine
````
POLYGON ((0 0, 50 0, 50 50, 150 50, 150 150, 200 150, 200 250, 50 250, 50 200, 100 200, 100 150, 0 150, 0 0))

````