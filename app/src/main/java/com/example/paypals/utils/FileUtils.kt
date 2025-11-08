package com.example.paypals.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

fun getFileNameFromUri(context: Context, uri: Uri): String? {
     val cursor = context.contentResolver.query(uri, null, null, null, null)
     return cursor?.use {
          val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
          if (it.moveToFirst() && nameIndex != -1) {
               it.getString(nameIndex)
          } else null
     }
}