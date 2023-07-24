package com.luxone.capacitor.plugin.datawedge

import android.content.Intent
import com.getcapacitor.JSObject
import org.json.JSONObject

class DwScanResult (
    val scanData: String,
    val labelType: String,
    val source: String
) {
    companion object {
        fun of(intent: Intent): DwScanResult {
            val source = intent.getStringExtra(DwApi.DATAWEDGE_SCAN_EXTRA_SOURCE) ?: ""
            val scanData =
                intent.getStringExtra(DwApi.DATAWEDGE_SCAN_EXTRA_DATA_STRING) ?: ""
            val labelType =
                intent.getStringExtra(DwApi.DATAWEDGE_SCAN_EXTRA_LABEL_TYPE) ?: ""

            val scanResult = JSONObject()

            scanResult.put(DwApi.EVENT_NAME, DwApi.SCAN_RESULT)
            scanResult.put("scanData", scanData)
            scanResult.put("labelType", labelType)
            scanResult.put("source", source)

            return DwScanResult(
                scanData = scanData,
                labelType = labelType,
                source = source
            )
        }
    }

    fun toJSON(): JSObject {
        val data = JSObject()

        data.put("scanData", scanData)
        data.put("labelType", labelType)
        data.put("source", source)

        return data
    }
}