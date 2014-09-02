/**
 * @FileName  : DeliveryInfoBO.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 8. 29. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package example.business1.delivery.domain;

import lombok.Data;

@Data
public class DeliveryInfoBO {

	private String 	deliverySeq;
	private String 	deliveryType;
	private Integer deliveryCost;
	
	
}
