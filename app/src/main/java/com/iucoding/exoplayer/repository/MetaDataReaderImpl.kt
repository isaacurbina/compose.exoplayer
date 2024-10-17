package com.iucoding.exoplayer.repository

import android.app.Application
import android.net.Uri
import android.provider.MediaStore
import com.iucoding.exoplayer.model.MetaData

class MetaDataReaderImpl(
    private val app: Application
) : MetaDataReader {

    override fun getMetaDataFromUri(contentUri: Uri): MetaData? {
        if (contentUri.scheme != CONTENT_SCHEME) return null
        val fileName = app.contentResolver
            .query(
                contentUri,
                arrayOf(MediaStore.Video.VideoColumns.DISPLAY_NAME),
                null,
                null,
                null
            )
            ?.use { cursor ->
                val index = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                cursor.getString(index)
            }
        return fileName?.let { path ->
            MetaData(
                fileName = Uri.parse(path).lastPathSegment ?: return null
            )
        }
    }

    companion object {
        private const val CONTENT_SCHEME = "content"
    }
}
