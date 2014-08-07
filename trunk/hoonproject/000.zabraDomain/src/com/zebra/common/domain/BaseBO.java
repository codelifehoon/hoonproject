package com.zebra.common.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class BaseBO  implements Serializable  {


	private String retCode;
	private String retMsg;
	

	private Integer startSeq;
	private Integer	rowCnt;
	
	
	
}
