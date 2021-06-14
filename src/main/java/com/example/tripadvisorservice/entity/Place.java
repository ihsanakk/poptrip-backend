package com.example.tripadvisorservice.entity;


import com.example.tripadvisorservice.entity.enums.PlaceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private int id;

    @NonNull
    @Column(name = "place_type")
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @Column(name = "num_of_reviews")
    private Integer numberOfReviews;

    @Column(name = "likes")
    private Integer likes;

    @JsonIgnore
    @OneToMany(mappedBy = "place",fetch = FetchType.LAZY)
    private List<Review> reviews;

    @Column(name = "place_title")
    private String placeTitle;


    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "place_description")
    private String placeDescription;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedAt;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "image_url")
    private String imageUrl;



}
