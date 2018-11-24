package com.example.wampSync;

import org.json.JSONException;

public interface AsyncResponse {
    void processFinish(String output) throws JSONException;
}
