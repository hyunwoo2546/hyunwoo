package com.hyunwoo;

import com.hyunwoo.examlib.entity.Exam;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Exam exam = new Exam(10,20,30);
        System.out.println(exam.total());
        System.out.println(exam.avg());
    }
}
