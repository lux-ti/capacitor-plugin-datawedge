package com.luxone.capacitor.plugin.datawedge

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import java.util.UUID

class DwApi(private val plugin: DataWedgePlugin) {
    private val receiver: BroadcastReceiver

    companion object {
        const val ACTION_DATAWEDGE = "com.symbol.datawedge.api.ACTION"

        const val EXTRA_COMMAND_IDENTIFIER = "COMMAND_IDENTIFIER"
        const val EXTRA_SEND_RESULT = "SEND_RESULT"
        const val EXTRA_CREATE_PROFILE = "com.symbol.datawedge.api.CREATE_PROFILE"
        const val EXTRA_RESULT = "RESULT"
        const val EXTRA_COMMAND = "COMMAND"
        const val EXTRA_RESULT_INFO = "RESULT_INFO"
        const val EXTRA_KEY_VALUE_NOTIFICATION_PROFILE_NAME = "PROFILE_NAME"

        const val SCAN_EVENT_INTENT_ACTION =".SCAN_EVENT"

        const val EVENT_NAME = "EVENT_NAME"
        const val SCAN_RESULT = "SCAN_RESULT"

        const val DATAWEDGE_SCAN_EXTRA_SOURCE = "com.symbol.datawedge.source"
        const val DATAWEDGE_SCAN_EXTRA_DATA_STRING = "com.symbol.datawedge.data_string"
        const val DATAWEDGE_SCAN_EXTRA_LABEL_TYPE = "com.symbol.datawedge.label_type"
        const val ACTION_RESULT = "com.symbol.datawedge.api.RESULT_ACTION"
        const val ACTION_RESULT_NOTIFICATION = "com.symbol.datawedge.api.NOTIFICATION_ACTION"
        const val ACTION_SET_CONFIG = "com.symbol.datawedge.api.SET_CONFIG"
    }

    init {
        this.receiver = JSEventsReceiver(this.plugin)
        val intentFilter = IntentFilter()
        intentFilter.addAction(this.plugin.context.packageName + SCAN_EVENT_INTENT_ACTION)
        intentFilter.addAction(ACTION_RESULT)
        intentFilter.addAction(ACTION_DATAWEDGE)
        intentFilter.addAction(ACTION_RESULT_NOTIFICATION)
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        this.plugin.context.registerReceiver(this.receiver, intentFilter)
    }

    fun destroy(){
        this.plugin.context.unregisterReceiver(receiver)
    }

    private fun exec(intent: Intent) {
        val id = UUID.randomUUID().toString()

        intent.action = ACTION_DATAWEDGE
        intent.putExtra(EXTRA_COMMAND_IDENTIFIER, id)
        intent.putExtra(EXTRA_SEND_RESULT, "true")

        this.plugin.context.sendBroadcast(intent)
    }

    fun createProfile(name: String) {
        val createProfileIntent = Intent()

        createProfileIntent.putExtra(EXTRA_CREATE_PROFILE, name)

        //https://techdocs.zebra.com/datawedge/latest/guide/api/createprofile/
        this.exec(createProfileIntent)

        val profileConfig = Bundle()
        profileConfig.putString(EXTRA_KEY_VALUE_NOTIFICATION_PROFILE_NAME, name)
        profileConfig.putString("PROFILE_ENABLED", "true") //  These are all strings
        profileConfig.putString("CONFIG_MODE", "UPDATE")

        val barcodeConfig = Bundle()
        barcodeConfig.putString("PLUGIN_NAME", "BARCODE")
        //  This is the default but never hurts to specify
        barcodeConfig.putString("RESET_CONFIG", "true")

        val barcodeProps = Bundle()
        barcodeConfig.putBundle("PARAM_LIST", barcodeProps)
        profileConfig.putBundle("PLUGIN_CONFIG", barcodeConfig)

        val appConfig = Bundle()
        //  Associate the profile with this app
        appConfig.putString("PACKAGE_NAME", this.plugin.context.packageName)
        appConfig.putStringArray("ACTIVITY_LIST", arrayOf("*"))
        profileConfig.putParcelableArray("APP_LIST", arrayOf(appConfig))

        val dwIntent = Intent()
        dwIntent.putExtra(ACTION_SET_CONFIG, profileConfig)

        this.exec(dwIntent)

        /// You can only configure one plugin at a time in some versions of DW, now do the intent output
        profileConfig.remove("PLUGIN_CONFIG")

        val intentConfig = Bundle()
        intentConfig.putString("PLUGIN_NAME", "INTENT")
        intentConfig.putString("RESET_CONFIG", "true")

        val intentProps = Bundle()
        intentProps.putString("intent_output_enabled", "true")
        intentProps.putString(
            "intent_action",
            this.plugin.context.packageName + SCAN_EVENT_INTENT_ACTION
        )
        intentProps.putString("intent_delivery", "2")  //  "2"
        intentConfig.putBundle("PARAM_LIST", intentProps)
        profileConfig.putBundle("PLUGIN_CONFIG", intentConfig)

        val profileIntent = Intent()

        profileIntent.putExtra(ACTION_SET_CONFIG, profileConfig)

        return this.exec(profileIntent)
    }
}