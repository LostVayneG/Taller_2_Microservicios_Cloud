# Taller 2 Microservicios Cloud
### Requisitos
*Versión de java usada: Java 16*
*Tener instalado maven ultima versión*

### URLS para probar los microservicios

#### Sumador:
* http://localhost:8888/calculadora/suma?a=3&b=4&user=Daniel
* http://localhost:8888/calculadora/history?operation=sumador

#### Restador:
* http://localhost:8888/calculadora/resta?a=3&b=4&user=Pedro
* http://localhost:8888/calculadora/history?operation=restador

#### Multiplicador:
* http://localhost:8888/calculadora/multiplicacion?a=3&b=4&user=Andres
* http://localhost:8888/calculadora/history?operation=multiplicador

#### Divisor:
* http://localhost:8888/calculadora/division?a=12&b=4&user=Pedro
* http://localhost:8888/calculadora/history?operation=divisor

## Parte 1
### Compilación y Ejecución
Ubicarse dentro de la carpeta "Taller_2_Parte_1"
#### Pasos:
1. Ubicarse dentro de la carpeta "jerseyServer" 
2. Abrir una consola o terminal de comandos en esa carpeta
3. Escribir el comando "mvn clean install" para compilar
4. Despues escribir el comando "mvn exec:java" para ejecutar
5. Volver a la carpeta "Taller_2_Parte_1"
6. Ubicarse dentro de la carpeta "RestClient"
7. Abrir una consola o terminal de comandos en esa carpeta
8. Escribir el comando "mvn clean install" para compilar
9. Despues escribir el comando "mvn exec:java" para ejecutar
10. El cliente mostrará un menu para interactuar

## Parte 2
### Compilación y Ejecución
Ubicarse dentro de la carpeta "Taller_2_Parte_2"
#### Pasos:
1. Ubicarse dentro de la carpeta "eurekaserver" 
2. Abrir una consola o terminal de comandos en esa carpeta
3. Escribir el comando "mvn clean install" para compilar
4. Despues escribir el comando "mvn spring-boot:run" para ejecutar
5. Volver a la carpeta "Taller_2_Parte_2"
6. Ubicarse dentro de la carpeta "calculadora"
7. Abrir una consola o terminal de comandos en esa carpeta
8. Escribir el comando "mvn clean install" para compilar
9. Despues escribir el comando "mvn spring-boot:run" para ejecutar
10. Volver a la carpeta "Taller_2_Parte_2"

Los pasos a continuación se pueden realizar dentro de las carpetas "restador", "sumador","multiplicador" y "divisor" dependiendo de cuales operaciones quiere agregar a la calculadora

11. Ubicarse dentro cualquiera de las carpetas mencionadas
12. Abrir una consola o terminal de comandos en esa carpeta
13. Escribir el comando "mvn clean install" para compilar
14. Despues escribir el comando "mvn spring-boot:run" para ejecutar
15. Abrir un navegador y usar las urls del principio para probar los microservicios

! IMPORTANTE ¡

Si desea ejecutar varias instancias en WINDOWS de un mismo microservicio pero en puertos diferentes antes del paso 14 use el comando "SET SERVER_PORT=1234" donde 1234 es el puerto donde quiere ejecutar la otra instancia. 
