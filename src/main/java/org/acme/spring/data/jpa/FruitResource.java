package org.acme.spring.data.jpa;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Path("/fruits")
public class FruitResource {

    private final FruitRepository fruitRepository;

    public FruitResource(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @GET
    @Produces("application/json")
    public Iterable<Fruit> findAll() {
        return fruitRepository.findAll();
    }


    @DELETE
    @Path("{id}")
    public void delete(@PathParam long id) {
        fruitRepository.deleteById(id);
    }

    @DELETE
//    @Path("{id}")
    public void delete() {
        fruitRepository.deleteAll();
    }

    @POST
    @Path("/name/{name}/color/{color}")
    @Produces("application/json")
    public Fruit create(@PathParam String name, @PathParam String color) {
        return fruitRepository.save(new Fruit(name, color));
    }

    @PUT
    @Path("/id/{id}/box")
    @Produces("application/json")
    public Fruit addMetadata(@PathParam Long id) {
        Optional<Fruit> optional = fruitRepository.findById(id);
        if (optional.isPresent()) {
            Fruit fruit = optional.get();
            HashMap<String, String> metadata = new HashMap<>();
            metadata.put("clave1","valor1");
            metadata.put("clave2","valor2");
            Box box = new Box(metadata);
            fruit.setBox(box);
            Fruit save = fruitRepository.save(fruit);
//            Optional<Fruit> byId = fruitRepository.findById(1L);
            return save;
        }

        throw new IllegalArgumentException("No Fruit with id " + id + " exists");
    }

    @PUT
    @Path("/id/{id}/color/{color}")
    @Produces("application/json")
    public Fruit changeColor(@PathParam Long id, @PathParam String color) {
        Optional<Fruit> optional = fruitRepository.findById(id);
        if (optional.isPresent()) {
            Fruit fruit = optional.get();
            fruit.setColor(color);
            return fruitRepository.save(fruit);
        }

        throw new IllegalArgumentException("No Fruit with id " + id + " exists");
    }

    @GET
    @Path("/color/{color}")
    @Produces("application/json")
    public List<Fruit> findByColor(@PathParam String color) {
        return fruitRepository.findByColor(color);
    }
}