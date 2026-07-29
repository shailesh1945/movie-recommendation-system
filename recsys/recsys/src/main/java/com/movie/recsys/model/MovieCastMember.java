package com.movie.recsys.model;

public class MovieCastMember {

    private Long actorId;
    private String actorName;
    private String characterName;
    private Integer castOrder;

    public MovieCastMember() {
    }

    public Long getActorId() { return actorId; }
    public void setActorId(Long actorId) { this.actorId = actorId; }

    public String getActorName() { return actorName; }
    public void setActorName(String actorName) { this.actorName = actorName; }

    public String getCharacterName() { return characterName; }
    public void setCharacterName(String characterName) { this.characterName = characterName; }

    public Integer getCastOrder() { return castOrder; }
    public void setCastOrder(Integer castOrder) { this.castOrder = castOrder; }
}