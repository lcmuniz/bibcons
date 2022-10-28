package com.eficaztech.bibcons;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Edicao")
public class Edicao {
    @Id
    private Integer edicaoId;
}
