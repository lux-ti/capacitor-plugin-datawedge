package com.luxone.capacitor.plugin.datawedge

import android.content.Intent
import com.getcapacitor.JSObject
import org.json.JSONObject

class DwActionResult (
    val command: String,
    val result: String,
    val info: JSObject?,
    val commandId: String
) {
    companion object {
        fun of(intent: Intent): DwActionResult {
            val result = intent.getStringExtra(DwApi.EXTRA_RESULT) ?: ""
            val command = intent.getStringExtra(DwApi.EXTRA_COMMAND) ?: ""
            val commandIdentifier= intent.getStringExtra(DwApi.EXTRA_COMMAND_IDENTIFIER) ?: ""

            var info: JSObject? = null;

            if (intent.hasExtra(DwApi.EXTRA_RESULT_INFO)) {
                info = JSObject();

                val bundle = intent.getBundleExtra(DwApi.EXTRA_RESULT_INFO)
                val keys: Set<String> = bundle!!.keySet()

                for (key in keys) {
                    info.put(key, bundle.getString(key))
                }
            }

            return DwActionResult(
                result = result,
                command = command,
                info = info,
                commandId = commandIdentifier
            )
        }
    }

    fun toJSON(): JSObject {
        val data = JSObject()

        data.put("result", result)
        data.put("command", command)
        data.put("info", info)
        data.put("command_id", commandId)

        return data
    }
}