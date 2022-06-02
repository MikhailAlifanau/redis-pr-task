package com.malifanau.redisprtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Location represents a point on a 2 dimensional Graph with 2 coordinates.
 */
@Data
@Entity
@Table(name = "LOCATION")
@NoArgsConstructor
@AllArgsConstructor
@Cache(region = "location",usage = CacheConcurrencyStrategy.READ_WRITE)
public class Location {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ABSCISSA", nullable = false)
    private Double abscissa;

    @Column(name = "ORDINATE", nullable = false)
    private Double ordinate;
}
