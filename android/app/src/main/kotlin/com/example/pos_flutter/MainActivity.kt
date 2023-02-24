package com.example.pos_flutter

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import com.example.pos_flutter.enums.ReceiptType
import com.example.pos_flutter.models.PaymentData
import com.google.gson.Gson
import com.kishcore.sdk.hybrid.api.SDKManager
import com.kishcore.sdk.hybrid.api.WaterMarkLanguage
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel


class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.example.pos_flutter/pos"
//    val methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SDKManager.init(MainActivity@this)

    }

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
                call, result ->
            if (call.method == "printBitmap") {
                val bitmapByteArray = call.argument<ByteArray>("bitmapByteArray")
                bitmapByteArray?.let {
                    val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
                    val printStatus = print(MainActivity@this, bmp)
                    result.success(printStatus)
                } ?: run {
                    result.notImplemented()
                }
            } else if (call.method == "payment") {
                val amount = call.argument<String>("amount")
                amount?.let {
                    payment(amount)
                    result.success(true)
                } ?: run {
                    result.notImplemented()
                }
            } else {
                result.notImplemented()
            }
        }
    }

    private fun print(context: Context?, bmp: Bitmap?) {
        if (SDKManager.getPrinterStatus() == SDKManager.STATUS_OK) {
            SDKManager.printBitmap(
                context,
                bmp,
                true,
                70,
                WaterMarkLanguage.ENGLISH,
                true
            ) { data ->
                showToast(
                    "پایان پرینت"
                )
            }
        } else {
            showToast("پرینتر با مشکل مواجه شده است.")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun payment(amount: String) {
        try{
            val packageName: String = Const.NOVIN_POS_PACKAGE_NAME
//        if (!isPackageInstalled(packageName)) {
//            showToast("اپلیکیشن نوین پوز نصب نمی باشد.")
//            return
//        }
            val intent = Intent(Const.PAYMENT_INTENT)
            intent.setPackage(packageName)
            val data = PaymentData()
            data.androidPosMessageHeader = "@@PNA@@" // fix
            data.ecrType = "1" // fix
            data.amount = amount
            data.transactionType = "00" // fix for payment
            // optional
            data.billNo = "123456"
            data.additionalData = "123456"
            data.originalAmount = amount
            data.swipeCardTimeout = "30000"
            data.receiptType = ReceiptType.CustomerAndMerchant
            val bundle = Bundle()
            bundle.putString("Data", Gson().toJson(data))
            intent.putExtras(bundle)

            startActivityForResult(intent,Const.REQUEST_CODE_FOR_PAYMENT)
            Log.d("xxxxxxxxxxxxxxxx","Finished");
        }
        catch(ex:Exception){
            Log.d("xxxxxxxxxxxxxxxx",ex.toString());
        }
    }

    private fun isPackageInstalled(packageName: String): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        SDKManager.closePrinter()
    }
}
