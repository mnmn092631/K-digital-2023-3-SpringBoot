package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	private Long cnt = 0L;
}
