package com.project.springboot.fileprac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type_working")
    private String typeWorking;

    @Column(name = "city")
    private String city;

}
