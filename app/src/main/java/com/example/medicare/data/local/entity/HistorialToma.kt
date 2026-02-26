package com.example.medicare.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(
    tableName = "historial_tomas",
    foreignKeys = [
        ForeignKey(
            entity = Programacion::class,
            parentColumns = ["idProgramacion"],
            childColumns = ["idProgramacionFk"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HistorialToma(
    @PrimaryKey(autoGenerate = true)
    val idToma: Int = 0,

    @ColumnInfo(name = "id_programacion_fk")
    val idProgramacionFk: Int,

    @ColumnInfo(name = "fecha_hora_programada")
    val fechaHoraProgramada: String,

    @ColumnInfo(name = "fecha_hora_real")
    val fechaHoraReal: String?,

    @ColumnInfo(name = "estado")
    val estado: String
)