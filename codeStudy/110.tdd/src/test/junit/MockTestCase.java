/**
 * @FileName  : MockTestCase.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 6. 9. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.junit;


import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.core.*;
import org.junit.Test;
import org.hamcrest.CoreMatchers;


import example.mockTestCase.MockTestClass;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

public class MockTestCase {
	
	@Test
	public void constructorCallTest() {
	
		//MockTestClass MockTestClass = new MockTestClass(null,null);
	}

	@Test
	public void matcherTest()
	{
		String s = "yes, we have no bananas today";
		Matcher<String> containsBananas = new StringContains("bananas");
		Matcher<String> containsMangoes = new StringContains("mangoes");

		
		assertTrue(containsBananas.matches(s));
		assertFalse(containsMangoes.matches(s));
		
		
		assertTrue(CoreMatchers.containsString("bananas").matches(s));
		assertFalse(CoreMatchers.containsString("mangoes").matches(s));
		
		
		assertThat(s,CoreMatchers.containsString("bananas"));
		//assertThat(s,CoreMatchers.containsString("mangoes"));
		
		
	}
	
	@Test
	public void mockitoTest1()
	{
		// mock 생성
		List mockedList = mock(List.class);

		// mock 객체 사용
		mockedList.add("one");
		mockedList.add("two");

		
		verify(mockedList).add("one");	// 검증 하기 (add 가 호출 되었는지 검증
		//verify(mockedList).clear();
	}
	
	@Test
	public void mockitoStubbingTest()
	{
		LinkedList mockedList = mock(LinkedList.class);
		
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenReturn(new RuntimeException());
		
		System.out.println("##### mockedList.get(0):" + mockedList.get(0));
		System.out.println("##### mockedList.get(999):" + mockedList.get(999));
		
		verify(mockedList).get(0);
				
	}
	
	@Test
	public void mockitoStubbing2Test()
	{
		LinkedList mockedList = mock(LinkedList.class);
		
		when(mockedList.get(anyInt())).thenReturn("element");

		System.out.println("##### mockedList.get(0):" + mockedList.get(0));
		System.out.println("##### mockedList.get(999):" + mockedList.get(999));
		
		verify(mockedList,atLeastOnce()).get(anyInt());	// argument matcher 를 사용할때에는 모든 parameter가 matcher 여야한다.
				
	}
	
	@Test
	public void mockitoCountTest()
	{
		LinkedList mockedList = mock(LinkedList.class);
		
		// mock 설정
		mockedList.add("once");
		 
		mockedList.add("twice");
		mockedList.add("twice");
		 
		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");
		 
		verify(mockedList).add("once");
		verify(mockedList,times(1)).add("once");
		
		verify(mockedList,times(2)).add("twice");
		verify(mockedList,times(3)).add("three times");
		
		verify(mockedList,never()).add("never happend");
		verify(mockedList,atLeastOnce()).add("twice"); 	// add()가 최소한 1번 이상 호출되었는지 검증
		verify(mockedList,atLeast(2)).add("three times");		// add()가 최소한 2번 이상 호출되었는지 검증
		verify(mockedList,atMost(5)).add("three times");		// add()가 최대한 5번 이하 호출되었는지 검증
		
		
		
		
				
	}
	
	
	
}
