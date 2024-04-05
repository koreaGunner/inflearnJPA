package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("B") //DTYPE명을 엔티티명이 아닌 다른걸로 수정할수있음
public class Book extends Item{
    private String author;
    private String isbn;
}
