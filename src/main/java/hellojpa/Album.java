package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A") //DTYPE명을 엔티티명이 아닌 다른걸로 수정할수있음
public class Album extends Item{
    private String artist;
}
