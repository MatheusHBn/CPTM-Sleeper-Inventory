package com.CPTM.stock.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Sleeper {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String identifier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wood_type_id", nullable = false)
    private WoodType woodType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_location_id", nullable = false)
    private StockLocation stockLocation;
    @Column(precision = 10, scale = 3)
    @Positive
    private BigDecimal lengthMeters;
    @Column(precision = 10, scale = 3)
    @Positive
    private BigDecimal widthMeters;
    @Column(precision = 10, scale = 3)
    @Positive
    private BigDecimal heightMeters;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private SleeperStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
