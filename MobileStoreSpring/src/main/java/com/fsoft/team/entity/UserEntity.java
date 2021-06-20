package com.fsoft.team.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblUsers")

public class UserEntity implements Serializable{

    @Id
    @Column(name = "userName", length = 50)
    private String userName;
    
    @Column(name = "fullName", length = 50)
    private String fullName;
    
    @Column(name = "password", length = 50)
    private String password;
    
    @Column(name = "role", length = 50)
    private String role;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderEntity> listOrder;

    public UserEntity(String userName, String fullName, String password, String role) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }
    
}
