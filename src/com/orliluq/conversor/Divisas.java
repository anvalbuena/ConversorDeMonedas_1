package com.orliluq.conversor;

import com.orliluq.app.MensajeFinal;
import java.util.Scanner;

public class Divisas {
    private Scanner scanner = new Scanner(System.in);
    private DivisasApi api = new DivisasApi();

    public void convertirDivisa() {
        while (true) {
            System.out.println("\n===== Conversión de Divisas =====");
            System.out.println("Seleccione una opción de conversión:");
            System.out.println("1. USD a ARS");
            System.out.println("2. ARS a USD");
            System.out.println("3. USD a BRL");
            System.out.println("4. BRL a USD");
            System.out.println("5. USD a COP");
            System.out.println("6. COP a USD");
            System.out.println("7. USD a VES");
            System.out.println("8. VES a USD");
            System.out.println("9. Volver al Menú Principal");
            System.out.print("Ingrese el número de la opción deseada (1-9): ");

            String opcion = scanner.nextLine().trim();
            String opcionDivisaElegida = "";

            switch (opcion) {
                case "1":
                    opcionDivisaElegida = "USD a ARS";
                    break;
                case "2":
                    opcionDivisaElegida = "ARS a USD";
                    break;
                case "3":
                    opcionDivisaElegida = "USD a BRL";
                    break;
                case "4":
                    opcionDivisaElegida = "BRL a USD";
                    break;
                case "5":
                    opcionDivisaElegida = "USD a COP";
                    break;
                case "6":
                    opcionDivisaElegida = "COP a USD";
                    break;
                case "7":
                    opcionDivisaElegida = "USD a VES";
                    break;
                case "8":
                    opcionDivisaElegida = "VES a USD";
                    break;
                case "9":
                    return; // Volver al menú principal
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    continue;
            }

            // Solicitar la cantidad a convertir
            System.out.print("Ingrese la cantidad que desea convertir de " + opcionDivisaElegida + ": ");
            String cantidadInput = scanner.nextLine().trim();

            if (cantidadInput.isEmpty()) {
                System.out.println("La cantidad no puede estar vacía.");
                continue;
            }

            try {
                double cantidadAConvertir = Double.parseDouble(cantidadInput.replace(",", "."));
                if (cantidadAConvertir < 0) {
                    System.out.println("La cantidad debe ser un número positivo.");
                    continue;
                }

                // Realizar la operación de conversión
                ConversionResponse resultado = api.obtenerConversion(
                        obtenerCodigoDivisa(origen(opcionDivisaElegida)),
                        obtenerCodigoDivisa(destino(opcionDivisaElegida)),
                        cantidadAConvertir
                );

                if (resultado.getConversionResult() == 0) {
                    System.out.println("No se pudo realizar la conversión. Por favor, inténtelo de nuevo más tarde.");
                    continue;
                }

                // Mostrar el resultado de la conversión
                System.out.println("\n===== Resultado de la Conversión =====");
                System.out.printf("%.2f %s equivale a %.2f %s%n",
                        cantidadAConvertir,
                        origen(opcionDivisaElegida),
                        resultado.getConversionResult(),
                        destino(opcionDivisaElegida)
                );
                System.out.println("Tasa de Conversión: " + resultado.getConversionRate());
                System.out.println("Fecha de Actualización: " + resultado.getLastUpdate());

            } catch (NumberFormatException exception) {
                // Manejar errores de formato de número
                System.out.println("Error: Ingrese un número válido.");
                continue;
            }

            // Preguntar si el usuario quiere realizar otra conversión
            MensajeFinal mensajeFinal = new MensajeFinal();
            mensajeFinal.mostrarMensajeFinal();
            break; // Después de decidir, salir del bucle
        }
    }

    private String origen(String opcion) {
        return opcion.split(" a ")[0];
    }

    private String destino(String opcion) {
        return opcion.split(" a ")[1];
    }

    private String obtenerCodigoDivisa(String nombreDivisa) {
        switch (nombreDivisa) {
            case "USD":
                return "USD";
            case "ARS":
                return "ARS";
            case "BRL":
                return "BRL";
            case "COP":
                return "COP";
            case "VES":
                return "VES";
            default:
                return "";
        }
    }
}
