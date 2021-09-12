package ClientRest;

import Model.Paseo;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClientRest {
    public static final String SERVER_URL = "http://localhost:8080/myapp/myresource";
    private static Client client;
    private static WebTarget webTarget;
    private static WebTarget serviceTarget;
    private static Invocation.Builder invocationBuilder;
    private static Response response;

    public static void main(String[] args) throws Exception {
        client = ClientBuilder.newClient();
        webTarget = client.target(SERVER_URL);
        int opcion = 1;

        while(opcion != 0){
            System.out.println("========== Opciones ==========");
            System.out.println("1. Mostrar pasesos");
            System.out.println("2. Eliminar paseo");
            System.out.println("3. Actualizar paseo");
            System.out.println("4. Agregar paseo");
            System.out.println("0. Salir");
            System.out.println("==============================");
            System.out.print("Ingrese una opcion: ");
            Scanner userInput = new Scanner(System.in);
            opcion = userInput.nextInt();
            if(opcion == 1){
                ShowPaseos();
            }
            else if(opcion == 2){
                DeletePaseo();
            }
            else if(opcion == 3){
                UpdatePaseo();
            }
            else if(opcion == 4){
                CreatePaseo();
            }
        }
    }

    private static void ShowPaseos(){
        serviceTarget = webTarget.path("paseos");
        invocationBuilder= serviceTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();
        System.out.println("RESPONSE FROM SERVER code: "+response.getStatus());
        List<Paseo> paseos= response.readEntity(new GenericType<List<Paseo>>() {});
        for (Paseo p:paseos) {
            System.out.println(p.toString());
        }
    }

    private static void DeletePaseo(){
        System.out.println("Id del paseo: ");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        serviceTarget = webTarget.path("paseos/"+id);
        invocationBuilder= serviceTarget.request(MediaType.TEXT_PLAIN);
        response = invocationBuilder.delete();
        System.out.println("RESPONSE FROM SERVER code: "+response.getStatus());
        String respuestaTexto= response.readEntity(String.class);
        System.out.println("Content: "+respuestaTexto);
    }

    private static void UpdatePaseo() throws IOException {
        System.out.println("Id del paseo: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(input.readLine());
        System.out.println("Nuevo origen: ");
        String origen = input.readLine();
        System.out.println("Nuevo destino: ");
        String destino = input.readLine();
        Paseo paseoToUpdate = new Paseo();
        paseoToUpdate.setId(id);
        paseoToUpdate.setLugarOrigen(origen);
        paseoToUpdate.setLugarDestino(destino);
        serviceTarget = webTarget.path("paseos");
        invocationBuilder= serviceTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.put(Entity.json(paseoToUpdate));
        System.out.println("RESPONSE FROM SERVER code: "+response.getStatus());
        String respuestaTexto= response.readEntity(String.class);
        System.out.println("Content: "+respuestaTexto);
    }

    private static void CreatePaseo() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nuevo origen: ");
        String origen = input.readLine();
        System.out.println("Nuevo destino: ");
        String destino = input.readLine();
        System.out.println("Nombre paseo: ");
        String nombre = input.readLine();
        Paseo paseoToCreate = new Paseo();
        paseoToCreate.setId(0);
        paseoToCreate.setLugarOrigen(origen);
        paseoToCreate.setLugarDestino(destino);
        paseoToCreate.setNombre(nombre);
        paseoToCreate.setFecha(new Date());
        serviceTarget = webTarget.path("paseos");
        invocationBuilder= serviceTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.post(Entity.json(paseoToCreate));
        System.out.println("RESPONSE FROM SERVER code: "+response.getStatus());
        String respuestaTexto= response.readEntity(String.class);
        System.out.println("Content: "+respuestaTexto);
    }

}
