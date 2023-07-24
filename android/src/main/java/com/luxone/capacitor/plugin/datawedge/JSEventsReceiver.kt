package com.luxone.capacitor.plugin.datawedge

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.getcapacitor.Bridge
import com.getcapacitor.Plugin

class JSEventsReceiver(
    private val plugin: DataWedgePlugin
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action ?: "") {
            context.packageName + DwApi.SCAN_EVENT_INTENT_ACTION -> {
                val result = DwScanResult.of(intent)
                Log.d("DW", result.toJSON().toString())
                this.plugin.emit("scan_result", result.toJSON())
            }
            DwApi.ACTION_RESULT -> {
                val result = DwActionResult.of(intent)
                this.plugin.emit("action_result", result.toJSON())
            }
        }
    }
}