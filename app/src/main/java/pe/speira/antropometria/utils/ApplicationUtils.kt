package pe.speira.antropometria.utils

import java.util.*

class ApplicationUtils {

    companion object {

        fun obtenerEdad(fechaNacimiento: Date?): Int {
            var age = 0
            val nacimiento = Calendar.getInstance()
            val actualidad = Calendar.getInstance()
            if (fechaNacimiento != null) {
                actualidad.time = Date()
                nacimiento.time = fechaNacimiento
                age = actualidad.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR)
                if (actualidad.get(Calendar.DAY_OF_YEAR) < nacimiento.get(Calendar.DAY_OF_YEAR)) {
                    age -= 1
                }
            }
            return age
        }

    }
}