package com.example.jwplayerdrmdemo.drm

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import com.google.android.exoplayer2.drm.ExoMediaDrm
import com.jwplayer.pub.api.media.drm.MediaDrmCallback
import com.example.jwplayerdrmdemo.drm.DRMUtils.executePost
import java.io.IOException
import java.util.*

class WidevineDRMCallback : MediaDrmCallback {


    @Throws(IOException::class)
    override fun executeProvisionRequest(
        uuid: UUID,
        request: ExoMediaDrm.ProvisionRequest
    ): ByteArray {
        val url = request.defaultUrl + "&signedRequest=" + String(request.data)
        return executePost(url, null, null)
    }

    @Throws(IOException::class)
    override fun executeKeyRequest(uuid: UUID, request: ExoMediaDrm.KeyRequest): ByteArray {
        return executePost(
            "https://proxy.uat.widevine.com/proxy?provider=widevine_test",
            request.data,
            null
        )
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {

    }

    companion object CREATOR : Parcelable.Creator<WidevineDRMCallback> {
        override fun createFromParcel(parcel: Parcel): WidevineDRMCallback {
            return WidevineDRMCallback()
        }

        override fun newArray(size: Int): Array<WidevineDRMCallback?> {
            return arrayOfNulls(size)
        }
    }
}