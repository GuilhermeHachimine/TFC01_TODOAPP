package com.example.todoapp.model

import android.os.Parcelable
import android.os.Parcel


class Task(
    val description: String,
    val isUrgent: Boolean,
    val isDone: Boolean = false
): Parcelable {


    // Implementação do Parcelable

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readByte() > 0,
        parcel.readByte() > 0
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeByte(if (isUrgent) 1 else 0)
        parcel.writeByte(if (isDone) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}