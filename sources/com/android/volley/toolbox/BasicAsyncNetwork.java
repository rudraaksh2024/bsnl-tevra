package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.AsyncNetwork;
import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestTask;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.AsyncHttpStack;
import com.android.volley.toolbox.NetworkUtility;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class BasicAsyncNetwork extends AsyncNetwork {
    private final AsyncHttpStack mAsyncStack;
    /* access modifiers changed from: private */
    public final ByteArrayPool mPool;

    private BasicAsyncNetwork(AsyncHttpStack asyncHttpStack, ByteArrayPool byteArrayPool) {
        this.mAsyncStack = asyncHttpStack;
        this.mPool = byteArrayPool;
    }

    public void setBlockingExecutor(ExecutorService executorService) {
        super.setBlockingExecutor(executorService);
        this.mAsyncStack.setBlockingExecutor(executorService);
    }

    public void setNonBlockingExecutor(ExecutorService executorService) {
        super.setNonBlockingExecutor(executorService);
        this.mAsyncStack.setNonBlockingExecutor(executorService);
    }

    /* access modifiers changed from: private */
    public void onRequestSucceeded(Request<?> request, long j, HttpResponse httpResponse, AsyncNetwork.OnRequestComplete onRequestComplete) {
        int statusCode = httpResponse.getStatusCode();
        List<Header> headers = httpResponse.getHeaders();
        if (statusCode == 304) {
            Request<?> request2 = request;
            onRequestComplete.onSuccess(NetworkUtility.getNotModifiedNetworkResponse(request, SystemClock.elapsedRealtime() - j, headers));
            return;
        }
        Request<?> request3 = request;
        AsyncNetwork.OnRequestComplete onRequestComplete2 = onRequestComplete;
        byte[] contentBytes = httpResponse.getContentBytes();
        if (contentBytes == null && httpResponse.getContent() == null) {
            contentBytes = new byte[0];
        }
        byte[] bArr = contentBytes;
        if (bArr != null) {
            onResponseRead(j, statusCode, httpResponse, request, onRequestComplete, headers, bArr);
            return;
        }
        getBlockingExecutor().execute(new ResponseParsingTask(httpResponse.getContent(), httpResponse, request, onRequestComplete, j, headers, statusCode));
    }

    /* access modifiers changed from: private */
    public void onRequestFailed(Request<?> request, AsyncNetwork.OnRequestComplete onRequestComplete, IOException iOException, long j, HttpResponse httpResponse, byte[] bArr) {
        try {
            getBlockingExecutor().execute(new InvokeRetryPolicyTask(request, NetworkUtility.shouldRetryException(request, iOException, j, httpResponse, bArr), onRequestComplete));
        } catch (VolleyError e) {
            onRequestComplete.onError(e);
        }
    }

    private class InvokeRetryPolicyTask<T> extends RequestTask<T> {
        final AsyncNetwork.OnRequestComplete callback;
        final Request<T> request;
        final NetworkUtility.RetryInfo retryInfo;

        InvokeRetryPolicyTask(Request<T> request2, NetworkUtility.RetryInfo retryInfo2, AsyncNetwork.OnRequestComplete onRequestComplete) {
            super(request2);
            this.request = request2;
            this.retryInfo = retryInfo2;
            this.callback = onRequestComplete;
        }

        public void run() {
            try {
                NetworkUtility.attemptRetryOnException(this.request, this.retryInfo);
                BasicAsyncNetwork.this.performRequest(this.request, this.callback);
            } catch (VolleyError e) {
                this.callback.onError(e);
            }
        }
    }

    public void performRequest(Request<?> request, AsyncNetwork.OnRequestComplete onRequestComplete) {
        if (getBlockingExecutor() != null) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final Request<?> request2 = request;
            final AsyncNetwork.OnRequestComplete onRequestComplete2 = onRequestComplete;
            this.mAsyncStack.executeRequest(request, HttpHeaderParser.getCacheHeaders(request.getCacheEntry()), new AsyncHttpStack.OnRequestComplete() {
                public void onSuccess(HttpResponse httpResponse) {
                    BasicAsyncNetwork.this.onRequestSucceeded(request2, elapsedRealtime, httpResponse, onRequestComplete2);
                }

                public void onAuthError(AuthFailureError authFailureError) {
                    onRequestComplete2.onError(authFailureError);
                }

                public void onError(IOException iOException) {
                    BasicAsyncNetwork.this.onRequestFailed(request2, onRequestComplete2, iOException, elapsedRealtime, (HttpResponse) null, (byte[]) null);
                }
            });
            return;
        }
        throw new IllegalStateException("mBlockingExecuter must be set before making a request");
    }

    /* access modifiers changed from: private */
    public void onResponseRead(long j, int i, HttpResponse httpResponse, Request<?> request, AsyncNetwork.OnRequestComplete onRequestComplete, List<Header> list, byte[] bArr) {
        int i2 = i;
        Request<?> request2 = request;
        NetworkUtility.logSlowRequests(SystemClock.elapsedRealtime() - j, request, bArr, i);
        if (i2 < 200 || i2 > 299) {
            AsyncNetwork.OnRequestComplete onRequestComplete2 = onRequestComplete;
            onRequestFailed(request, onRequestComplete, new IOException(), j, httpResponse, bArr);
            return;
        }
        NetworkResponse networkResponse = new NetworkResponse(i, bArr, false, SystemClock.elapsedRealtime() - j, list);
        AsyncNetwork.OnRequestComplete onRequestComplete3 = onRequestComplete;
        onRequestComplete.onSuccess(networkResponse);
    }

    private class ResponseParsingTask<T> extends RequestTask<T> {
        AsyncNetwork.OnRequestComplete callback;
        HttpResponse httpResponse;
        InputStream inputStream;
        Request<T> request;
        long requestStartMs;
        List<Header> responseHeaders;
        int statusCode;

        ResponseParsingTask(InputStream inputStream2, HttpResponse httpResponse2, Request<T> request2, AsyncNetwork.OnRequestComplete onRequestComplete, long j, List<Header> list, int i) {
            super(request2);
            this.inputStream = inputStream2;
            this.httpResponse = httpResponse2;
            this.request = request2;
            this.callback = onRequestComplete;
            this.requestStartMs = j;
            this.responseHeaders = list;
            this.statusCode = i;
        }

        public void run() {
            try {
                BasicAsyncNetwork.this.onResponseRead(this.requestStartMs, this.statusCode, this.httpResponse, this.request, this.callback, this.responseHeaders, NetworkUtility.inputStreamToBytes(this.inputStream, this.httpResponse.getContentLength(), BasicAsyncNetwork.this.mPool));
            } catch (IOException e) {
                BasicAsyncNetwork.this.onRequestFailed(this.request, this.callback, e, this.requestStartMs, this.httpResponse, (byte[]) null);
            }
        }
    }

    public static class Builder {
        private static final int DEFAULT_POOL_SIZE = 4096;
        private AsyncHttpStack mAsyncStack;
        private ByteArrayPool mPool = null;

        public Builder(AsyncHttpStack asyncHttpStack) {
            this.mAsyncStack = asyncHttpStack;
        }

        public Builder setPool(ByteArrayPool byteArrayPool) {
            this.mPool = byteArrayPool;
            return this;
        }

        public BasicAsyncNetwork build() {
            if (this.mPool == null) {
                this.mPool = new ByteArrayPool(4096);
            }
            return new BasicAsyncNetwork(this.mAsyncStack, this.mPool);
        }
    }
}
