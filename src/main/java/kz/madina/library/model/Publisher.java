package kz.madina.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "publisher",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Book> books = new ArrayList<>();


    public static class Builder {
        private String name;
        private String description;

        public Builder() {
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Publisher build() {
            Publisher publisher = new Publisher();
            publisher.setName(this.name);
            publisher.setDescription(this.description);
            publisher.setBooks(new ArrayList<>());
            return publisher;
        }
    }
}
