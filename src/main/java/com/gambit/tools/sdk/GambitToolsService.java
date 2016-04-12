package com.gambit.tools.sdk;

import com.gambit.tools.sdk.request.GambitRequestClientSecret;
import com.gambit.tools.sdk.request.GambitRequestRandomUUID;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The main class that all SDK users will use to work with the Gambit Tools SDK.
 */
public class GambitToolsService {

    /**
     * Singleton instance
     */
    protected static GambitToolsService mInstance;
    
    /**
     * Thread loop
     */
    protected final ExecutorService mExecutor;

    /**
     * Gambit API Endpoint Hostname
     */
    protected String mEndpointHostname;

    /**
     * Singleton constructor
     */
    protected GambitToolsService() {
        mExecutor = Executors.newCachedThreadPool();
    }

    /**
     * Creates a {@link GambitToolsService} if none previously existed in the VM,
     * otherwise returns the existing {@link GambitToolsService} instance.
     * @return GambitToolsService
     */
    public static GambitToolsService getInstance() {
        if (mInstance == null) {
            mInstance = new GambitToolsService();
        }

        return mInstance;
    }

    /**
     * Finalize {@link GambitToolsService}  by shutting down all threads.
     */
    public void finish() {
        getExecutorService().shutdown();
    }

    /**
     * Get the main thread pool
     * @return executor service
     */
    protected ExecutorService getExecutorService() {
        return mExecutor;
    }

    /**
     * Set Gambit API Endpoint Hostname
     * @param hostname The endpoint hostname. Protocol is always secure.
     */
    public void setEndpointUrl(String hostname) {
        mEndpointHostname = hostname;
    }

    /**
     * Get Gambit API Endpoint Hostname
     * @return Gambit API Endpoint Hostname
     */
    public String getEndpointHostname() {
        return mEndpointHostname;
    }

    /**
     * Request client salt and client secret based on API public and private keys
     * @param builder Builder that configures the {@link GambitRequest} inheriting object
     * @return Promised object that inherits {@link GambitResponse}
     */
    public Future<GambitResponse> requestClientSecret(GambitRequestClientSecret.Builder builder) {
        return mExecutor.submit(builder.build());
    }
    
    /**
     * Request random UUID based on API public and private keys
     * @param builder Builder that configures the {@link GambitRequest} inheriting object
     * @return Promised object that inherits {@link GambitResponse}
     */
    public Future<GambitResponse> requestRandomUUID(GambitRequestRandomUUID.Builder builder) {
        return mExecutor.submit(builder.build());
    }

}
