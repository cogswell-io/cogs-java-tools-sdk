package com.gambit.tools.sdk.response;

import com.gambit.tools.sdk.GambitResponse;

public class GambitResponseRandomUUID extends GambitResponse {

    /**
     * Gambit API Random UUID
     */
    protected String mUUID;

    /**
     * Construct the response object using the raw response body and response code
     * @param response The raw HTTP response body as text
     * @param code The raw HTTP response code as an integer
     */
    public GambitResponseRandomUUID(String response, int code) {
        super(response, code);
        
        if (isSuccess()) {
            if (mJson.has("uuid")) {
                mUUID = mJson.getString("uuid");
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
     * Get Gambit API Random UUID
     * @return A Random UUID
     */
    public String getUUID() {
        return mUUID;
    }

}
