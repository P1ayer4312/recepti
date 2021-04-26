package com.proekt.recepti.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="recipe")
public class RecipeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ime;
    private String kategorija;
    private int vreme;
    private String linkSlika;

    @Column(length = 2000, nullable = false)
    private String sostojki;

    @Column(length = 30000, nullable = false)
    private String tekst;

    private String creator;


    public RecipeModel(String ime, String kategorija, int vreme, String linkSlika, String sostojki, String tekst, String creator) {
        this.ime = ime;
        this.kategorija = kategorija;
        this.vreme = vreme;
        this.linkSlika = linkSlika;
        this.sostojki = sostojki;
        this.tekst = tekst;
        this.creator = creator;
    }
}
