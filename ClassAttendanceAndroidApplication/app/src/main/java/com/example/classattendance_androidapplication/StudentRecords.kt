package com.example.classattendance_androidapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

// Here we define an entity called StudentRecords to represent student records in the database.
// We specify the foreign key relationships with other entities.
@Entity(foreignKeys = [ForeignKey(entity = Student::class, parentColumns = ["id"], childColumns = ["studentId"]),
ForeignKey(entity = Module::class, parentColumns = ["id"], childColumns = ["moduleID"])])
data class StudentRecords(
    // We define some variables of the student associated with thier record.
    @ColumnInfo(name = "studentId") var studentId: String = "",
    var dateCreated: LocalDate,
    @ColumnInfo(name = "moduleID") var moduleID: Int = 0

){
    // We define a variable called id as the primary key for this entity.
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}