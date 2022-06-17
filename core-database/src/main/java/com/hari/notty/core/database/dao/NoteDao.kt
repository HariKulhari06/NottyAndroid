package com.hari.notty.core.database.dao

import androidx.room.*
import com.hari.notty.core.database.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query(
        value = """
        SELECT * FROM note
        WHERE id = :noteId
    """
    )
    fun getNoteEntityStream(noteId: String): Flow<NoteEntity>

    @Query(value = "SELECT * FROM note")
    fun getNoteEntitiesStream(): Flow<List<NoteEntity>>

    /**
     * Inserts [noteEntities] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreNotes(noteEntities: List<NoteEntity>): List<Long>

    /**
     * Updates [entities] in the db that match the primary key, and no-ops if they don't
     */
    @Update
    suspend fun updateNotes(entities: List<NoteEntity>)

    /**
     * Inserts or updates [entities] in the db under the specified primary keys
     */
    @Transaction
    suspend fun upsertAuthors(entities: List<NoteEntity>) = upsert(
        items = entities,
        insertMany = ::insertOrIgnoreNotes,
        updateMany = ::updateNotes
    )

    /**
     * Deletes rows in the db matching the specified [ids]
     */
    @Query(
        value = """
            DELETE FROM note
            WHERE id in (:ids)
        """
    )
    suspend fun deleteAuthors(ids: List<String>)
}