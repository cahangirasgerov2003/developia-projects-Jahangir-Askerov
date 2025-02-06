package az.developia.spring_java20_jahangir_askerov.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String author;
    private double price;
    private String color;
    private Integer pageCount;
}
 