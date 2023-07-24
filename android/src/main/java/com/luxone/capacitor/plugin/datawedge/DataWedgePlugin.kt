package com.luxone.capacitor.plugin.datawedge

import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.SystemClock
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin


@CapacitorPlugin(name = "DataWedge")
class DataWedgePlugin : Plugin() {
    private lateinit var dw: DwApi

    override fun handleOnStart() {
        super.handleOnStart()
        dw = DwApi(this)

    }

    override fun handleOnDestroy() {
        super.handleOnDestroy()
        dw.destroy()
    }

    fun emit(event: String, data: JSObject?) {
        notifyListeners(event, data)
    }

    @PluginMethod()
    fun createProfile(call: PluginCall) {
        val name = call.getString("name")!!

        dw.createProfile(name)

    }
}