package org.acme.spring.data.jpa;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Embeddable
public class Box {

//    @Id
//    @GeneratedValue
//    private Long id;


    @ElementCollection
    private Map<String, String> metadata = new HashMap<>();

    public Box() {
    }

    public Box(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

}
