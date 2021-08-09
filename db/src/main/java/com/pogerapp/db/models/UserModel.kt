package com.pogerapp.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pogerapp.core.entity.user.User
import java.util.*

@Entity
class UserModel (
    @PrimaryKey
    val uid: Int = 0,

    @ColumnInfo(name = "f_name")
    val firstName: String?,

    @ColumnInfo(name = "l_name")
    val lastName: String?,

    @ColumnInfo(name = "birthday")
    val birthday: String?,

    @ColumnInfo(name = "avatr_url")
    val avatar: String?
) {
    companion object{
        fun fromEntity(user: User) = UserModel(
            uid = user.uid,
            firstName = user.firstName,
            lastName = user.lastName,
            birthday = user.birthday,
            avatar = user.avatar
        )
    }
}