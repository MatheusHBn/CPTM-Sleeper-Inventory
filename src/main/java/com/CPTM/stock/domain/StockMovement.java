package com.CPTM.stock.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(nullable = false)
    private Sleeper sleeper;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private MovementType type;
    @ManyToOne
    private StockLocation origin;
    @ManyToOne
    private StockLocation destination;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(nullable = false)
    private User performedBy;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private String notes;
}
