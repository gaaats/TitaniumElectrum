package com.game.space.sho.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "devil_table")
data class DevilEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    @SerializedName("id")
    @Expose
    val id: Int,
    @ColumnInfo("deeplink")
    @SerializedName("deeplink")
    @Expose
    val deeplink: String,
    @ColumnInfo("naming")
    @SerializedName("naming")
    @Expose
    val naming: String,
    @ColumnInfo("advertid")
    @SerializedName("advertid")
    @Expose
    val advertid: String,
    @ColumnInfo("link")
    @SerializedName("link")
    @Expose
    val link: String,

) {
}