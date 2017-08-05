package com.aurelius.module.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.aurelius.module.bank.entity.BankEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "WebUser")
@Getter @Setter
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 826717052378585898L;
	
	@Id
	@NotNull
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;
	private String fullName;
	private String username;
	private String address;
	private Date createdAt;
	private String createdBy;
	private Date modifiedAt;
	private String modifiedBy;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<BankEntity> banks;
}
