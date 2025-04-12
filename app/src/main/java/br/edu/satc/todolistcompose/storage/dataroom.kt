package br.edu.satc.todolistcompose.storage

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "task_title") val taskTitle: String = "",
    @ColumnInfo(name = "task_description") val taskDescription: String = "",
    @ColumnInfo(name = "task_complete") val taskComplete: Boolean = false
)

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Update
    fun update(vararg task: Task)

    @Insert
    fun insert(vararg task: Task)
}

@Database(entities = [Task::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}