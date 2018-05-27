package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-23T21:43:11.158+0200")
@StaticMetamodel(Teacher.class)
public class Teacher_ {
	public static volatile SingularAttribute<Teacher, Integer> code;
	public static volatile SingularAttribute<Teacher, String> name;
	public static volatile SingularAttribute<Teacher, String> password;
	public static volatile SingularAttribute<Teacher, String> email;
	public static volatile SingularAttribute<Teacher, String> role;
	public static volatile ListAttribute<Teacher, Course> courses;
}
