package org.acme;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "document.path")
    String path;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        UUID uuid = UUID.randomUUID();
        String fileName = "EC_" + uuid.toString() + ".txt";
        String message = "";
        try {
            File file = new File(path + fileName);
            file.createNewFile();
            message = "Archivo creado: " + file.getName();
        } catch (IOException e) {
            message = "Ocurrio un error.";
            e.printStackTrace();
        }

        return message;
    }
}
