package com.gambit.tools.sdk.response;

import com.gambit.tools.sdk.GambitResponse;

public class GambitResponseClientSecret extends GambitResponse {

    /**
     * Gambit API Client Salt
     */
    protected String mClientSalt;

    /**
     * Gambit API Client Secret
     */
    protected String mClientSecret;

    /**
     * Construct the response object using the raw response body and response code
     * @param response The raw HTTP response body as text
     * @param code The raw HTTP response code as an integer
     */
    public GambitResponseClientSecret(String response, int code) {
        super(response, code);
        
        if (isSuccess()) {
            if (mJson.has("client_salt") && mJson.has("client_secret")) {
                mClientSalt = mJson.getString("client_salt");
                mClientSecret = mJson.getString("client_secret");
            }
            else {
                //not good at all
                
                mIsSuccess = false;
                mErrorCode = "UNKNOWN";
                mErrorDetails = "Unknown response: "+response;
            }
        }
    }
    
    /**
     * Get Gambit API Client Salt
     * @return Client Salt Hash used to make requests from the GambitSDK library
     */
    public String getClientSalt() {
        return mClientSalt;
    }

    /**
     * Get Gambit API Client Secret
     * @return Client Secret Hash used to make requests from the GambitSDK library
     */
    public String getClientSecret() {
        return mClientSecret;
    }

}
