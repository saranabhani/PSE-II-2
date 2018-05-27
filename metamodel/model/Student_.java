package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-23T21:43:11.142+0200")
@StaticMetamodel(Student.class)
public class Student_ {
	public static volatile SingularAttribute<Student, Integer> S_Code;
	public static volatile SingularAttribute<Student, String> name;
	public static volatile SingularAttribute<Student, String> password;
	public static volatile SingularAttribute<Student, String> email;
	public static volatile ListAttribute<Student, Course> courses;
	public static volatile ListAttribute<Student, Result> results;
}
