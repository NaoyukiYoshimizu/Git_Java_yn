package com.example.demo.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * ユーザー情報 Entity
 */
@Entity
@Data
@Table(name = "addressbook")
public class User {
	 /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 名前
     */
    @Column(name = "name")
    private String name;
    /**
     * 住所
     */
    @Column(name = "age")
    private int age;
    /**
     * 住所
     */
    @Column(name = "address")
    private String address;
}
