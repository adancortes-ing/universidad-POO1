package calculadora;

import static calculadora.CMD.*;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Principal {

    final static double[] PRESENTACIONES = {0.235, 0.400, 0.600, 0.900, 1.2, 1.8, 2.25, 3.25};
    final static double CUOTA_LITRO = 2.7645;

    public static void main(String[] args) {

        Utilidades.dibujarPortada();
        limpiar();

        //Dibujar una pequeña interfaz con cabecera y datos principales
        dibujarRecuadro(1, COLS, 1, LINEAS, RESET);
        dibujarRecuadro(2, COLS - 1, 2, 5, AMARILLO);
        centrarTexto("COOPERATIVA ESCOLAR", 3);
        centrarTexto("CALCULADORA DE COSTOS", 4);

        imprimirTexto("Las presentaciones disponibles son: ", 4, 7);
        posicionXY(8, 8);
        for (double presentacion : PRESENTACIONES) {
            System.out.print(Utilidades.formatearPresentacion(presentacion) + ", ");
        }

        imprimirTexto("═".repeat(50), 4, 10);
        imprimirTexto("PRESENTACIÓN   | PRECIO     | IMPUESTO", 5, 11);

        //Solicita datos de los precios base de cada presentacion
        double impuestoMax = Double.MIN_VALUE, impuestoMin = Double.MAX_VALUE, precioBase;
        int lineaActual = 13, presentacionMin = 0, presentacionMax = 0;

        for (int i = 0; i < PRESENTACIONES.length; i++) {
            //Comprueba y formatea la cantidad de refresco
            String presentacion = Utilidades.formatearPresentacion(PRESENTACIONES[i]);

            //Solicita el precio base, calcula el impuesto y precio final
            precioBase = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el precio para " + presentacion));
            double impuesto = PRESENTACIONES[i] * CUOTA_LITRO;
            double precioFinal = precioBase + impuesto;

            //Determina cual es el mayor y menor impuesto
            if (impuesto > impuestoMax) {
                impuestoMax = impuesto;
                presentacionMax = i;
            }

            if (impuesto < impuestoMin) {
                impuestoMin = impuesto;
                presentacionMin = i;
            }

            //Formatea e imprime la linea con los datos de cada presentacion
            imprimirTexto(presentacion, 8, lineaActual);
            imprimirTexto("$" + String.format("%.1f", precioFinal), 22, lineaActual);
            imprimirTexto("+$" + String.format("%.2f", impuesto), 35, lineaActual);
            lineaActual++;
        }
        //Imprime cual es la presentacion que genera el menor y el mayor impuesto
        imprimirTexto("═".repeat(50), 4, lineaActual);
        String strMaximo = Utilidades.formatearPresentacion(PRESENTACIONES[presentacionMax]);
        String strMinimo = Utilidades.formatearPresentacion(PRESENTACIONES[presentacionMin]);

        imprimirTexto("La presentación de " + strMaximo + " genera el mayor impuesto" + VERDE + " $" + String.format("%.2f", impuestoMax) + RESET, 8, lineaActual + 2);
        imprimirTexto("La presentación de " + strMinimo + " genera el menor impuesto" + VERDE + " $" + String.format("%.2f", impuestoMin) + "\n\n" + RESET, 8, lineaActual + 3);
    }
}

class Utilidades {

    //Datos de la portada para cambiar en cada actividad
    private static final String UNIDAD = "1";
    private static final String NOMBRE_ACTIVIDAD = "Evidencia de aprendizaje";

    static String formatearPresentacion(double cantidad) {
        String presentacion = (cantidad < 1.0) ? (int) (cantidad * 1000) + "ml" : cantidad + "L";

        return presentacion;
    }

    static void dibujarPortada() {

        dibujarRecuadro(1, COLS, 1, LINEAS, CYAN);
        centrarTexto(AMARILLO + "UNIVERSIDAD ABIERTA Y A DISTANCIA DE MÉXICO" + RESET, 4, 2);
        centrarTexto("Programación Orientada a Objetos I", 6);
        centrarTexto("Unidad: " + UNIDAD + " ♦ Actividad: " + NOMBRE_ACTIVIDAD, 8);
        centrarTexto("♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦ ♦", 11);
        centrarTexto("Elaboración:", 14);
        centrarTexto(AMARILLO + "Estudiante: " + RESET + "Adán Cortés Rodríguez", 16, 2);
        centrarTexto(AMARILLO + "Matrícula: " + RESET + "ES251118724", 18, 2);
        centrarTexto(AMARILLO + "Grupo: " + RESET + "DS-DPO1-2601-B2-002", 20, 2);
        centrarTexto(AMARILLO + "Docente: " + RESET + "Margarita Marquez Tirso", 22, 2);

        imprimirTexto("Presiona Enter para continuar", 1, LINEAS + 1);
        Scanner entrada = new Scanner(System.in);
        entrada.nextLine();
    }
}

class CMD {

    static final int COLS = 90;
    static final int LINEAS = 25;

    // Colores para la consola
    static final String RESET = "\u001B[0m";
    static final String ROJO = "\u001B[31m";
    static final String VERDE = "\u001B[32m";
    static final String AMARILLO = "\u001B[33m";
    static final String CYAN = "\u001B[36m";

    static void posicionXY(int x, int y) {
        System.out.printf("\033[%d;%dH", y, x);
    }

    static void imprimirTexto(String texto, int x, int y) {
        posicionXY(x, y);
        System.out.print(texto);
    }

    static void centrarTexto(String texto, int posY) {
        int posX = ((COLS / 2) - (texto.length()) / 2);
        posicionXY(posX, posY);
        System.out.print(texto);
    }

    static void centrarTexto(String texto, int posY, int colores) {
        colores = colores * 5;
        int posX = ((COLS / 2) - (texto.length() - colores) / 2);
        posicionXY(posX, posY);
        System.out.print(texto);
    }

    static void dibujarRecuadro(int supIzq, int supDer, int infIzq, int infDer, String color) {
        for (int i = supIzq; i <= supDer; i++) {
            posicionXY(i, supIzq);
            System.out.print(color + "━");
            posicionXY(i, infDer);
            System.out.print("━");
        }

        for (int i = supIzq; i <= infDer; i++) {
            posicionXY(supIzq, i);
            System.out.print(color + "┃");
            posicionXY(supDer, i);
            System.out.print("┃");

            posicionXY(supIzq, supIzq);
            System.out.print("┏");
            posicionXY(supDer, supIzq);
            System.out.print("┓");
            posicionXY(infIzq, infDer);
            System.out.print("┗");
            posicionXY(supDer, infDer);
            System.out.print("┛" + RESET);
        }
    }

    static void limpiar() {
        try {
            // Limpiar la consola si el sistema es Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Limpiar la consola si el sistema es LINUX o mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            // Si algo falla, al menos imprimimos espacios
            posicionXY(1, 1);
            for (int i = 0; i <= LINEAS; i++) {
                System.out.println();
            }
        }
    }
}
