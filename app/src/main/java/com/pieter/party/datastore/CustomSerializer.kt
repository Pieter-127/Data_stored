package com.pieter.party.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object CustomSerializer : Serializer<EntryItem> {
    override val defaultValue: EntryItem = EntryItem.getDefaultInstance()

    override fun readFrom(input: InputStream): EntryItem {
        try {
            return EntryItem.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(t: EntryItem, output: OutputStream) = t.writeTo(output)
}

