package com.example.zhangshuai.junit;

import com.example.zhangshuai.java.Test01;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class Test01Test {

    @Test
    public void stringLengthIsOver15() {
        assertThat(new Test01().stringLengthIsOver15("1111khjlkjlkjlkjljljljlkkjhkhk"),is(true));
        assertThat(new Test01().stringLengthIsOver15("萨芬撒开发的撒开饭啦啥都发送沙发上发顺丰"),is(true));
        assertThat(new Test01().stringLengthIsOver15("dafdafasfdas大飒飒的大师两款发动机撒凉快点附近萨拉快递费结束啦"),is(true));
        assertThat(new Test01().stringLengthIsOver15("```1111khjlkjlkjlkjljljljlkkjhkhk```"),is(true));
        assertThat(new Test01().stringLengthIsOver15("dsafsff"),is(true));
        assertThat(new Test01().stringLengthIsOver15("s"),is(true));
        assertThat(new Test01().stringLengthIsOver15("sss"),is(true));
        assertThat(new Test01().stringLengthIsOver15("fsfafsafasfasfsafsafs"),is(true));
        assertThat(new Test01().stringLengthIsOver15("fuck you"),is(true));
    }
}