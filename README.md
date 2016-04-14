[ ![Download](https://api.bintray.com/packages/cogswell-io/maven/cogs-java-tools-sdk/images/download.svg) ](https://bintray.com/cogswell-io/maven/cogs-java-tools-sdk/_latestVersion)
## Compile and install the source 

This project uses gradle wrapper, so you don't need to install gradle: you only need to run gradlew.

Linux:
```
./gradlew install
```

Windows:
```
gradlew.bat install
```

## [Code Samples](#code-samples)
You will see the name Gambit throughout our code samples. This was the code name used for Cogs prior to release.

### Preparation for using the Tools SDK

```java
import com.gambit.tools.sdk.GambitToolsService;

// Hex encoded access/secret pair from one of your api keys in the Web UI.
// These are intended for your internal use only, and should never be placed
// on client devices/apps.
String accessKey;
String secretKey;

// Create and setup the Cogs Tools service
GambitToolsService toolsService = GambitToolsService.getInstance();

// Shutdown the service when you are done using it (when your program closes).
// You can add this to a shutdown hook if you'd like.
toolsService.finish();
```

### POST /client_secret
This API route is used to generate new client salt/secret pairs for use in authenticating a client device or app when performing client API calls. Each salt/secret pair is permenantly tied to the api key which was used in order to authenticate this requeset. Disabling the associated api key disables all salt/secret pairs which were generated using that api key. It is the access component of that api key which is included in client requests as the access_key field, so all three (access_key, client_salt, and client_secret) should be saved together every time a new client salt/secret pair is generated.

```java
import java.util.concurrent.Future;
import com.gambit.tools.sdk.request.GambitRequestClientSecret;

GambitRequestClientSecret.Builder keyReqBuilder;

// Assemble the client key generation request, but do not build it.
keyReqBuilder = new GambitRequestClientSecret.Builder(accessKey, secretKey);

// Send the request, and receive a Future through which you can handle the outcome
// of the key generation, and from which you can acquire the new key.
Future<GambitResponse> future = toolsService.requestClientSecret(keyReqBuilder);

// In this example we are simply blocking until the operation completes, timing
// out after 15 seconds. If you do no wish to block the calling thread, you will
// need to either poll for completion (future.isDone()) or block for completion
// in another thread (Callable in executor service or explicitly managed thread).
try {
  GambitResponse response = future.get(15, TimeUnit.SECONDS);
} catch (CancelledException eCanceled) {
  // Handle cancellation
} catch (InterruptedException eInterrupted) {
  // Handle interruption
} catch (ExecutionException eExecution) {
  // Handle execution error
}
```

### POST /random_uuid
This API route is used in order to generate random (version 4) UUID from a solid entropy source. These can be used as identifiers in your application.

```java
import java.util.concurrent.Future;
import com.gambit.tools.sdk.request.GambitRequestRandomUUID;

GambitRequestRandomUUID.Builder uuidReqBuilder;

// Assemble the client key generation request, but do not build it.
uuidReqBuilder = new GambitRequestRandomUUID.Builder(accessKey, secretKey);

// Send the request, and receive a Future through which you can handle the outcome
// of the key generation, and from which you can acquire the new key.
Future<GambitResponse> future = toolsService.requestRandomUUID(uuidReqBuilder);

// In this example we are simply blocking until the operation completes, timing
// out after 15 seconds. If you do no wish to block the calling thread, you will
// need to either poll for completion (future.isDone()) or block for completion
// in another thread (Callable in executor service or explicitly managed thread).
try {
  GambitResponse response = future.get(15, TimeUnit.SECONDS);
} catch (CancelledException eCanceled) {
  // Handle cancellation
} catch (InterruptedException eInterrupted) {
  // Handle interruption
} catch (ExecutionException eExecution) {
  // Handle execution error
}
```

