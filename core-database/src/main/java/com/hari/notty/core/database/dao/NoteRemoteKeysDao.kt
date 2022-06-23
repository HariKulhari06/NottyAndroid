package com.hari.notty.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hari.notty.core.database.model.NoteRemoteKeysEntity

@Dao
interface NoteRemoteKeysDao {

    @Query("SELECT * FROM note_remote_keys WHERE id =:id")
    suspend fun getRemoteKeys(id: String): NoteRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<NoteRemoteKeysEntity>)

    @Query("DELETE FROM note_remote_keys")
    suspend fun deleteAllRemoteKeys()

}