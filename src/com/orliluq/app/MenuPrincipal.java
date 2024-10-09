package com.orliluq.app;

import com.orliluq.conversor.Divisas;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        while (true) {
            System.out.println("\n===== Menú Principal =====");
            System.out.println("1. Conversor de Divisas");
            System.out.println("2. Salir del Programa");
            System.out.print("Seleccione una opción (1-2): ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    Divisas conversorDivisas = new Divisas();
                    conversorDivisas.convertirDivisa();
                    break;
                case "2":
                    MensajeFinal mensajeFinal = new MensajeFinal();
                    mensajeFinal.mostrarMensajeFinal();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }
}
