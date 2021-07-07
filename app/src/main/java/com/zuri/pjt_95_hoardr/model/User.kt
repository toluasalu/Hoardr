package com.zuri.pjt_95_hoardr.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_information")
data class User(@PrimaryKey(autoGenerate = true)
                var uid: Int,
                @ColumnInfo(name = "fullName") var fullName: String?,
                @ColumnInfo(name = "email") var email: String?,
                @ColumnInfo(name = "password")var password: String?
)
