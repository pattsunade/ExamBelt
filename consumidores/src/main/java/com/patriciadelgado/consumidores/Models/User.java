package com.patriciadelgado.consumidores.Models;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseModels {
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
     @NotEmpty
    private String lastName;
    
    private int userRol;
    
    private Date date;
    // uniqueConstraints = @UniqueConstraint(columnNames = "email")
     @NotEmpty
    private String email;
    @Size(min=8, message="Password must be greater than 8 characters")
    private String password;
    @Transient
    private String passwordConfirmation;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subs_id")
    private Subs subss;

     @OneToMany(mappedBy = "admin", fetch =  FetchType.LAZY)
    private List<Subs> crearSuscripciones;


}
