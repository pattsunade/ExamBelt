package com.patriciadelgado.consumidores.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subss")
public class Subs extends BaseModels {
    @NonNull
    @NotEmpty
    private String name;
    @Min(1)
    private double price;
    
    private boolean estado;
	
	@OneToMany(mappedBy="subss", fetch = FetchType.LAZY)
    private List<User> users;
    
        @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private User admin;

}
