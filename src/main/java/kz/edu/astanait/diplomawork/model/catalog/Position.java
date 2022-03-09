package kz.edu.astanait.diplomawork.model.catalog;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "position")
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position_name")
    private String positionName;
}
