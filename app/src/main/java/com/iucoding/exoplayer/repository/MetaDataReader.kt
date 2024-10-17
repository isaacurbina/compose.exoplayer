package com.iucoding.exoplayer.repository

import android.net.Uri
import com.iucoding.exoplayer.model.MetaData

interface MetaDataReader {
    fun getMetaDataFromUri(contentUri: Uri): MetaData?
}
