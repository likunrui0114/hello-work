package xyz.etesh.masterslave.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/21 9:07
 * @desc TODO
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "address")
    private String address;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
