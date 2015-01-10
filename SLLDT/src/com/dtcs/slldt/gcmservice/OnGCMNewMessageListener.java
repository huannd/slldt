package com.dtcs.slldt.gcmservice;

/**
 * Delegate notify when received new message from google cloud message.<br/>
 * Listener from {@link GCMManagerMessage #onMessageRegular(android.content.Intent)}
 * 
 * @author TND
 * 
 */
public interface OnGCMNewMessageListener {
    /**
     * call back method called when received new message from google cloud message for objects register
     *  listener {@link OnGCMNewMessageListener}.{@link #onNewMessage(String)} via {@linkplain GCMManagerMessage}.{@code addDelegateListener(OnGCMNewMessageListener pGcmNewMessageListener)}}
     * 
     * @param msg
     */
    void onNewMessage(String msg);
}
