package ymsli.com.cpo.utils

import android.app.Application
import android.content.Context
import android.text.TextUtils
import androidx.multidex.MultiDexApplication
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.squareup.moshi.Moshi
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import org.komputing.khex.extensions.toNoPrefixHexString
import org.walletconnect.Session
import org.walletconnect.impls.*
import org.walletconnect.nullOnThrow
import ymsli.com.cpo.data.sharedpref.SharedPref
import ymsli.com.cpo.server.BridgeServer
import java.io.File
import java.net.URLEncoder
import java.util.*

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        private var context: Context? = null
        lateinit var instance: MyApplication
        private var mRequestQueue: RequestQueue? = null
        lateinit var toWCUri: String
        lateinit var config: Session.Config
        private lateinit var client: OkHttpClient
        private lateinit var moshi: Moshi
        private lateinit var bridge: BridgeServer
        private lateinit var storage: WCSessionStore
        lateinit var session: Session

        fun resetSession() {
            nullOnThrow { session }?.clearCallbacks()
            val key = ByteArray(32).also { Random().nextBytes(it) }.toNoPrefixHexString()
            config = Session.Config(
                UUID.randomUUID().toString(),
                "wss://bridge.walletconnect.org",
                //"https://bridge.walletconnect.org",
                key)

            toWCUri = "wc:${config.handshakeTopic}@2?${config.bridge}=${
                URLEncoder.encode(
                    config.bridge, "UTF-8")}&key=$key"

            session = WCSession(
                config,
                MoshiPayloadAdapter(moshi),
                storage,
                OkHttpTransport.Builder(client, moshi),
                Session.PeerMeta("https://ymsl.in/",name = "Example DApp")
            )
                session.offer()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
        SharedPref.init(applicationContext)
        initMoshi()
        initClient()
        initBridge()
        initSessionStorage()
    }

    private fun initClient() {
        client = OkHttpClient.Builder().build()
    }

    private fun initMoshi() {
        moshi = Moshi.Builder().build()
    }

    private fun initBridge() {
        bridge = BridgeServer(moshi)
        bridge.start()
    }

    private fun initSessionStorage() {
        storage = FileWCSessionStore(File(cacheDir, "session_store.json").apply { createNewFile() }, moshi)
    }


    private fun getRequestQueue(): RequestQueue {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(applicationContext)
        }
        return mRequestQueue!!
    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String?) {
        req.tag =
            if (TextUtils.isEmpty(tag)) "TAG" else tag
        getRequestQueue().add(req)
    }
}