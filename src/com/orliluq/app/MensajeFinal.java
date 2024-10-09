package com.orliluq.app;

import java.util.Scanner;

public class MensajeFinal {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMensajeFinal() {
        while (true) {
            System.out.print("\n¿Deseas realizar otra conversión? (Sí/No): ");
            String opcion = scanner.nextLine().trim().toLowerCase();

            if (opcion.equals("sí") || opcion.equals("si")) {
                MenuPrincipal iniciarApp = new MenuPrincipal();
                iniciarApp.iniciar();
                break;
            } else if (opcion.equals("no")) {
                System.out.println("Programa Finalizado. ¡Muchas Gracias!");
                System.exit(0);
            } else {
                System.out.println("Opción inválida. Por favor, ingrese 'Sí' o 'No'.");
            }
        }
    }
}
