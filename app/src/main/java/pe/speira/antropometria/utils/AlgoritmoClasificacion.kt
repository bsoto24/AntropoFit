package pe.speira.antropometria.utils

import pe.speira.antropometria.room.entities.ControlEntity
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.utils.ApplicationUtils.Companion.obtenerEdad
import kotlin.math.log10

class AlgoritmoClasificacion(pacienteEntity: PacienteEntity, controlEntity: ControlEntity) {

    var sumatoriaPliegues: Double = 0.0
    var porcentajeGrasaTotal: Double = 0.0
    var valoracionPorcentajeGrasa: Int = -1
    var grasaCorporal: Double = 0.0
    var masaMagra: Double = 0.0

    companion object {
        const val MASCULINO = 1
        const val NO_DEFINIDO = -1
        const val BAJO = 0
        const val ATLETA = 1
        const val ESTANDAR = 2
        const val ELEVADO = 3
    }

    init {
        sumatoriaPliegues = sumatoriaPliegues(
            controlEntity.pBicipital,
            controlEntity.pSubescapular,
            controlEntity.pSuprailiaco,
            controlEntity.pTricipital
        )

        porcentajeGrasaTotal =
            porcentajeGrasaTotal(
                pacienteEntity.sexo,
                obtenerEdad(pacienteEntity.fechaNacimiento),
                sumatoriaPliegues
            )

        valoracionPorcentajeGrasa = valoracionPorcentajeGrasa(pacienteEntity.sexo, porcentajeGrasaTotal)

        grasaCorporal = grasaCorporal(controlEntity.peso, porcentajeGrasaTotal)

        masaMagra = masaMagra(controlEntity.peso, grasaCorporal)

    }

    private fun sumatoriaPliegues(vararg pliegues: Double): Double {
        var sumatoria = 0.0
        pliegues.forEach { p -> sumatoria += p }
        return sumatoria
    }

    private fun porcentajeGrasaTotal(sexo: Int, edad: Int, sumatoriaPliegues: Double): Double {

        if (sexo == MASCULINO) {
            return when (edad) {
                in 17..19 -> ((4.95 / (1.162 - (0.063 * log10(sumatoriaPliegues)))) - 4.5) * 100
                in 20..29 -> ((4.95 / (1.1631 - (0.0632 * log10(sumatoriaPliegues)))) - 4.5) * 100
                in 30..39 -> ((4.95 / (1.1422 - (0.0544 * log10(sumatoriaPliegues)))) - 4.5) * 100
                in 40..49 -> ((4.95 / (1.162 - (0.07 * log10(sumatoriaPliegues)))) - 4.5) * 100
                in 50..100 -> ((4.95 / (1.1715 - (0.0779 * log10(sumatoriaPliegues)))) - 4.5) * 100
                else -> 0.0
            }
        } else {
            return when (edad) {
                in 17..19 -> ((4.95 / (1.1549 - (0.0678 * log10(sumatoriaPliegues)))) - 4.5) * 100
                in 20..29 -> ((4.95 / (1.1599 - (0.0717 * log10(sumatoriaPliegues)))) - 4.5) * 100
                in 30..39 -> ((4.95 / (1.1423 - (0.0632 * log10(sumatoriaPliegues)))) - 4.5) * 100
                in 40..49 -> ((4.95 / (1.1333 - (0.0612 * log10(sumatoriaPliegues)))) - 4.5) * 100
                in 50..100 -> ((4.95 / (1.1339 - (0.0645 * log10(sumatoriaPliegues)))) - 4.5) * 100
                else -> 0.0
            }
        }

    }

    private fun valoracionPorcentajeGrasa(sexo: Int, porcentajeGrasaTotal: Double): Int {

        if (sexo == MASCULINO) {
            return when {
                porcentajeGrasaTotal <= 0 -> NO_DEFINIDO
                porcentajeGrasaTotal < 6 -> BAJO
                porcentajeGrasaTotal < 12 -> ATLETA
                porcentajeGrasaTotal < 16 -> ESTANDAR
                porcentajeGrasaTotal >= 16 -> ELEVADO
                else -> NO_DEFINIDO
            }
        } else {
            return when {
                porcentajeGrasaTotal <= 0 -> NO_DEFINIDO
                porcentajeGrasaTotal < 12 -> BAJO
                porcentajeGrasaTotal < 18 -> ATLETA
                porcentajeGrasaTotal < 28 -> ESTANDAR
                porcentajeGrasaTotal >= 28 -> ELEVADO
                else -> NO_DEFINIDO
            }
        }

    }

    private fun grasaCorporal(peso: Double, porcentajeGrasaTotal: Double) = peso * (porcentajeGrasaTotal / 100)

    private fun masaMagra(peso: Double, grasaCorporal: Double) = peso - grasaCorporal


}