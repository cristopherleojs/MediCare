package com.example.medicare.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(
    tableName = "programacion_horarios",
    foreignKeys = [
        ForeignKey(
            entity = Medicamento::class,
            parentColumns = ["idMedicamento"],
            childColumns = ["idMedicamentoFk"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Programacion(
    @PrimaryKey(autoGenerate = true)
    val idProgramacion: Int = 0,

    @ColumnInfo(name = "id_medicamento_fk")
    val idMedicamentoFk: Int,

    @ColumnInfo(name = "hora_primera_toma")
    val horaPrimeraToma: String,

    @ColumnInfo(name = "frecuencia_horas")
    val frecuenciaHoras: Int,

    @ColumnInfo(name = "dias_semana")
    val diasSemana: String
)