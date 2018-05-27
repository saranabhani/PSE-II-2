package model;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-23T21:43:10.991+0200")
@StaticMetamodel(Course.class)
public class Course_ {
	public static volatile SingularAttribute<Course, Integer> C_Code;
	public static volatile SingularAttribute<Course, String> name;
	public static volatile SingularAttribute<Course, Integer> credits;
	public static volatile SingularAttribute<Course, Calendar> start_time;
	public static volatile SingularAttribute<Course, Teacher> teacher;
	public static volatile ListAttribute<Course, Student> students;
	public static volatile ListAttribute<Course, Result> results;
}
