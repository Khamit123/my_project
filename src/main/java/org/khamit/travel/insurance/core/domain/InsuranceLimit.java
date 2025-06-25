package org.khamit.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "insurance_limit")
public class InsuranceLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "limit_min", nullable = false)
    private Double limitMin;

    @Column(name = "limit_max", nullable = false)
    private Double limitMax;

    @Column(name = "coef", nullable = false)
    private Double coef;

}