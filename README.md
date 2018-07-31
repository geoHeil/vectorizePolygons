# vectorizaton of images in spark

```
make run-java
IllegalArgumentException: ImageRead: No OperationDescriptor is registered in the current operation registry under this name


make run-spark
Caused by: java.lang.IllegalArgumentException: ImageRead: No OperationDescriptor is registered in the current operation registry under this name.
```



when looking at the jai registration files in [registryFilesGenerated](registryFilesGenerated) none of them sadly contains the `vectors` registration.