package com.example.kadai_002.entity;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    private String name;
        
    @Column(name = "furigana")
    private String furigana;    
    

    @Column(name = "date_of_birth")
    private Date dateOfBirth;    
    
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "email")
    private String email;
        
    @Column(name = "password")
    private String password;    
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;   
    
    @Column(name = "enabled")
    private Boolean enabled;
    
    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;
    
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt; 
    
    @Column(name = "is_paid_member")
    private Boolean isPaidMember;
   
    @Column(name = "paid_member_expire_date")
    private Date paidMemberExpireDate;

    // ゲッター・セッター
    public boolean isPaidMember() {
        return isPaidMember;
    }

    public void setPaidMember(boolean isPaidMember) {
        this.isPaidMember = isPaidMember;
    }

    public Date getPaidMemberExpireDate() {
        return paidMemberExpireDate;
    }

    public void setPaidMemberExpireDate(Date paidMemberExpireDate) {
        this.paidMemberExpireDate = paidMemberExpireDate;
    }
}
