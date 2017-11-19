package com.aurelius.module.bank.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.aurelius.module.user.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Bank")
@Getter @Setter
public class BankEntity implements Serializable{
	private static final long serialVersionUID = 7802290776606061243L;
	
	@Id
	@NotNull
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "bank_id")
	private String bank_id;
	private String bankName;
	private Date createdAt;
	private String createdBy;
	private Date modifiedAt;
	private String modifiedBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id", nullable = true)
	private UserEntity user;
}
