package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-23T21:43:11.127+0200")
@StaticMetamodel(Result.class)
public class Result_ {
	public static volatile SingularAttribute<Result, Integer> grade;
	public static volatile SingularAttribute<Result, Student> student;
	public static volatile SingularAttribute<Result, Course> course;
}
