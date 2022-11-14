package com.example.jwplayerdrmdemo.drm

import com.google.android.exoplayer2.util.Util.toByteArray
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object DRMUtils {
    @JvmStatic
    @Throws(IOException::class)
    fun executePost(
        url: String?,
        data: ByteArray?,
        requestProperties: Map<String?, String?>?
    ): ByteArray {

        var urlConnection: HttpURLConnection? = null
        return try {
            urlConnection = URL(url).openConnection() as HttpURLConnection
            urlConnection.requestMethod = "POST"
            urlConnection.doOutput = data != null
            urlConnection.doInput = true
            if (requestProperties != null) {
                for ((key, value) in requestProperties) {
                    urlConnection.setRequestProperty(key, value)
                }
            }
            // Write the request body, if there is one.
            if (data != null) {
                urlConnection.outputStream.use { out ->
                    out.write(data)
                }
            }
            // Read and return the response body.
            val inputStream: InputStream = urlConnection.getInputStream()
            try {
                toByteArray(inputStream)
            } finally {
                inputStream.close()
            }
        } finally {
            urlConnection?.disconnect()
        }
    }
}