package com.test.mitrais.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.test.mitrais.deserializer.SimpleDateDeserializer;
import com.test.mitrais.deserializer.ToUpperCharDeserializer;
import com.test.mitrais.deserializer.TrimStringDeserializer;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JsonDeserialize(using = TrimStringDeserializer.class)
    @Column(name = "mobile_number", length = 15, nullable = false, unique = true)
    private String mobileNumber;

    @JsonDeserialize(using = TrimStringDeserializer.class)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @JsonDeserialize(using = TrimStringDeserializer.class)
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @JsonDeserialize(using = SimpleDateDeserializer.class)
    @Column(name = "birth_date")
    private Date birthDate;

    @JsonDeserialize(using = ToUpperCharDeserializer.class)
    @Column(name = "gender")
    private char gender;

    @JsonDeserialize(using = TrimStringDeserializer.class)
    @Column(name = "email_address", length = 100, nullable = false, unique = true)
    private String emailAddress;

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        if (this.mobileNumber != null) {
            if (this.mobileNumber.startsWith("0")) {
                this.mobileNumber = this.mobileNumber.replaceFirst("0", "+62");
            }
        }
    }
}


