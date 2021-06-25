package de.bnd.coding.sample.demo.data;

import javax.persistence.*;

@Entity
@Table( name = "challenges")
public class ChallengeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "challenge" )
    private String challenge;

    @Column( name = "category" )
    private String category;

    public ChallengeEntity() {
    }

    public ChallengeEntity(String challenge, String category) {
        this.challenge = challenge;
        this.category = category;
    }

    public String getChallenge() { return challenge; }

    public void setChallenge(String challenge) { this.challenge = challenge; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
