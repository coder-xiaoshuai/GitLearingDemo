package com.example.zhangshuai.test;

import com.example.zhangshuai.test.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonUtilsTest2 {
    @Mock
    Person p1;
    @Mock
    Person p2;

    //和RunWith选择其中一种方式即可
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void isSameAge() {
        when(p1.getAge()).thenReturn(25);
        when(p2.getAge()).thenReturn(25);
        assertTrue(PersonUtils.isSameAge(p1, p2));
    }

    @Test
    public void isSameName() {
        when(p1.getName()).thenReturn("jack");
        when(p2.getName()).thenReturn("jack");
        assertTrue(PersonUtils.isSameAge(p1, p2));
    }
}