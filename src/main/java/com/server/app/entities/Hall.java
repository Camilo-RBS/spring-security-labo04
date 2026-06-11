package com.server.app.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "halls")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer capacity;
}
