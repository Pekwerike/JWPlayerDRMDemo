package com.example.jwplayerdrmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jwplayerdrmdemo.drm.WidevineDRMCallback
import com.jwplayer.pub.api.configuration.PlayerConfig
import com.jwplayer.pub.api.configuration.UiConfig
import com.jwplayer.pub.api.configuration.ads.VastAdvertisingConfig
import com.jwplayer.pub.api.license.LicenseUtil
import com.jwplayer.pub.api.media.ads.AdBreak
import com.jwplayer.pub.api.media.playlists.PlaylistItem
import com.jwplayer.pub.view.JWPlayerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LicenseUtil().setLicenseKey(
            this,
            // TODO "YOUR_LICENSE_KEY"
        )
        val jwPlayerView = findViewById<JWPlayerView>(R.id.jwplayerview)


        val playlistItem: PlaylistItem = PlaylistItem.Builder()
            .file("https://bitmovin-a.akamaihd.net/content/art-of-motion_drm/mpds/11331.mpd")
            .mediaDrmCallback(
                WidevineDRMCallback()
            )
            .build()
        val adSchedule: MutableList<AdBreak> = mutableListOf()
        adSchedule.add(
            AdBreak.Builder()
                .tag("https://search.spotxchange.com/vast/2.00/85394?VPI=MP4&app[bundle]=roku.weatherapp&app[name]=myctvapp&app[cat]=IAB6-8&app[domain]=http%3A%2F%2Fpublishername.com/appname&app[privacypolicy]=1&app[storeurl]=http%3A%2F%2Fchannelstore.roku.com/details/11055/weatherapp&app[ver]=1.2.1&cb=7437276459847&device[devicetype]=7&device[ifa]=236A005B-700F-4889-B9CE-999EAB2B605D&device[make]=Roku&device[model]=Roku&device[dnt]=0&player_height=1080&player_width=1920&ip_addr=165.23.234.23&device[ua]=Roku%2FDVP-7.10%2520(047.10E04062A)]&schain=1.0,1!exchange1.com,1234,1,bid-request-1,publisher,publisher.com,ext_stuff!exchange2.com,abcd,1,bid-request2,intermediary,intermediary.com,other_ext_stuff")
                .offset("pre")
                .build()
        )
        adSchedule.add(
            AdBreak.Builder()
                .tag("https://search.spotxchange.com/vast/2.00/85394?VPI=MP4&app[bundle]=roku.weatherapp&app[name]=myctvapp&app[cat]=IAB6-8&app[domain]=http%3A%2F%2Fpublishername.com/appname&app[privacypolicy]=1&app[storeurl]=http%3A%2F%2Fchannelstore.roku.com/details/11055/weatherapp&app[ver]=1.2.1&cb=7437276459847&device[devicetype]=7&device[ifa]=236A005B-700F-4889-B9CE-999EAB2B605D&device[make]=Roku&device[model]=Roku&device[dnt]=0&player_height=1080&player_width=1920&ip_addr=165.23.234.23&device[ua]=Roku%2FDVP-7.10%2520(047.10E04062A)]&schain=1.0,1!exchange1.com,1234,1,bid-request-1,publisher,publisher.com,ext_stuff!exchange2.com,abcd,1,bid-request2,intermediary,intermediary.com,other_ext_stuff")
                .offset("00:02:00")
                .build()
        )

        val playerConfig = PlayerConfig.Builder()
            .playlist(listOf(playlistItem))
            .advertisingConfig(VastAdvertisingConfig.Builder().schedule(adSchedule).build())
            .autostart(true)
            .build()

        val jwPlayer = jwPlayerView.player

        jwPlayer.setup(playerConfig)
    }
}