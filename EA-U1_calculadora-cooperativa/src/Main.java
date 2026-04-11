
import java.util.Scanner;

public class Main {

    //Variables de clase para llevar el control del impuesto minimo y maximo
    static double impuestoMin = Double.MAX_VALUE;
    static double impuestoMax = Double.MIN_VALUE;
    static double presentacionMin, presentacionMax;

    // Constantes
    final static double CUOTA_LITRO = 2.7645;
    final static double[] PRESENTACIONES = {0.235, 0.400, 0.600, 0.900, 1.2, 1.8, 2.25, 3.25};

    //Colores para consola
    final static String AMARILLO = "\u001B[33m";
    final static String CYAN = "\u001B[36m";
    final static String RESET = "\u001B[0m";

    public static void main(String[] args) {
        //Simple cabecera para la visualizacion en consola
        System.out.println(CYAN + "CALCULADORA DE COSTOS : COOPERATIVA ESCOLAR");
        System.out.println(AMARILLO + "=".repeat(50) + RESET);
        System.out.println("Presentaciones disponibles:");
        System.out.println("235ml, 400ml, 600ml, 900ml, 1.2L, 1.8L, 2.25L y 3.25L\n");

        //Variables separadas para el precio de cada botella
        double botella235ml;
        double botella400ml;
        double botella600ml;
        double botella900ml;
        double botella1_2l;
        double botella1_8l;
        double botella2_25l;
        double botella3_25l;

        Scanner entradaPrecio = new Scanner(System.in);
        //Aqui solicito el precio de cada botella y llamo al metodo de calcular los datos para no repetir tanto código que ya de por sí es repetitivo
        //Lo normal seria utilizar una estructura de control repetitiva 
        System.out.print("Ingresa el precio base para " + formatearPresentacion(PRESENTACIONES[0]) + "  $");
        botella235ml = entradaPrecio.nextDouble();
        botella235ml = calcularDatos(PRESENTACIONES[0], botella235ml);

        System.out.print("Ingresa el precio base para " + formatearPresentacion(PRESENTACIONES[1]) + "  $");
        botella400ml = entradaPrecio.nextDouble();
        botella400ml = calcularDatos(PRESENTACIONES[1], botella400ml);

        System.out.print("Ingresa el precio base para " + formatearPresentacion(PRESENTACIONES[2]) + "  $");
        botella600ml = entradaPrecio.nextDouble();
        botella600ml = calcularDatos(PRESENTACIONES[2], botella600ml);

        System.out.print("Ingresa el precio base para " + formatearPresentacion(PRESENTACIONES[3]) + "  $");
        botella900ml = entradaPrecio.nextDouble();
        botella900ml = calcularDatos(PRESENTACIONES[3], botella900ml);

        System.out.print("Ingresa el precio base para " + formatearPresentacion(PRESENTACIONES[4]) + "  $");
        botella1_2l = entradaPrecio.nextDouble();
        botella1_2l = calcularDatos(PRESENTACIONES[4], botella1_2l);

        System.out.print("Ingresa el precio base para " + formatearPresentacion(PRESENTACIONES[5]) + "  $");
        botella1_8l = entradaPrecio.nextDouble();
        botella1_8l = calcularDatos(PRESENTACIONES[5], botella1_8l);

        System.out.print("Ingresa el precio base para " + formatearPresentacion(PRESENTACIONES[6]) + "  $");
        botella2_25l = entradaPrecio.nextDouble();
        botella2_25l = calcularDatos(PRESENTACIONES[6], botella2_25l);

        System.out.print("Ingresa el precio base para " + formatearPresentacion(PRESENTACIONES[7]) + "  $");
        botella3_25l = entradaPrecio.nextDouble();
        botella3_25l = calcularDatos(PRESENTACIONES[7], botella3_25l);

        System.out.println(AMARILLO + "=".repeat(50) + RESET);
        
        //Aqui muestro los resultados finales con el precio de cada botella
        System.out.println(CYAN + "Los precios calculados por botella son:" + RESET);
        System.out.println(formatearPresentacion(PRESENTACIONES[0]) + " $" + String.format("%.2f", botella235ml));
        System.out.println(formatearPresentacion(PRESENTACIONES[1]) + " $" + String.format("%.2f", botella400ml));
        System.out.println(formatearPresentacion(PRESENTACIONES[2]) + " $" + String.format("%.2f", botella600ml));
        System.out.println(formatearPresentacion(PRESENTACIONES[3]) + " $" + String.format("%.2f", botella900ml));
        System.out.println(formatearPresentacion(PRESENTACIONES[4]) + " $" + String.format("%.2f", botella1_2l));
        System.out.println(formatearPresentacion(PRESENTACIONES[5]) + " $" + String.format("%.2f", botella1_8l));
        System.out.println(formatearPresentacion(PRESENTACIONES[6]) + " $" + String.format("%.2f", botella2_25l));
        System.out.println(formatearPresentacion(PRESENTACIONES[7]) + " $" + String.format("%.2f", botella3_25l));

        //Finalmente muestro en cconsola los resultados finales con los productos con el mayor y menor impuesto
        System.out.println(AMARILLO + "=".repeat(50));
        System.out.print(CYAN + "Menor impuesto es de la botella de " + RESET + formatearPresentacion(presentacionMin));
        System.out.println(CYAN + "\t| Impuesto $" + RESET + String.format("%.2f", impuestoMin));
        System.out.println("=".repeat(50));
        System.out.print(CYAN + "Mayor impuesto es de la botella de " + RESET + formatearPresentacion(presentacionMax));
        System.out.println(CYAN + "\t| Impuesto $" + RESET + String.format("%.2f", impuestoMax));

    }

    //Con este metodo se realiza el calculo principal del impuesto y el precio de cada botella
    static double calcularDatos(double cantidad, double precio) {
        double impuesto = cantidad * CUOTA_LITRO;

        if (impuesto < impuestoMin) {
            impuestoMin = impuesto;
            presentacionMin = cantidad;
        }

        if (impuesto > impuestoMax) {
            impuestoMax = impuesto;
            presentacionMax = cantidad;
        }

        return precio + impuesto;
    }

        //Con este metodo me aseguro de convertir la presentacion al formato adecuado ej. 500ml o 2.5L
    static String formatearPresentacion(double cantidad) {
        String presentacion = (cantidad < 1.0) ? (int) (cantidad * 1000) + "ml" : cantidad + "L";

        return presentacion;
    }
}