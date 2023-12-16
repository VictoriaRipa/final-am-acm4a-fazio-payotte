// FirebaseAuthenticationTest.groovy

import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.tasks.OnCompleteListener

task pruebaAutenticacion() {
    doLast {
        def mAuth = FirebaseAuth.getInstance()

        // Ejemplo: Registrarse con correo y contraseña
        registerUser(mAuth, "ejemplo@example.com", "contraseña123")

        // Ejemplo: Iniciar sesión con correo y contraseña
        signInUser(mAuth, "ejemplo@example.com", "contraseña123")
    }
}

// Método para registrar un usuario con correo y contraseña
def registerUser(FirebaseAuth mAuth, String email, String password) {
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener({ task ->
                if (task.isSuccessful()) {
                    // Registro exitoso
                    println("Usuario registrado correctamente")
                } else {
                    // Falló el registro
                    println("Error al registrar usuario: ${task.getException()}")
                }
            } as OnCompleteListener)
}

// Método para iniciar sesión con correo y contraseña
def signInUser(FirebaseAuth mAuth, String email, String password) {
    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener({ task ->
                if (task.isSuccessful()) {
                    // Inicio de sesión exitoso
                    println("Inicio de sesión exitoso")
                    // Puedes redirigir a otra actividad aquí
                } else {
                    // Falló el inicio de sesión
                    println("Error al iniciar sesión: ${task.getException()}")
                }
            } as OnCompleteListener)
}
