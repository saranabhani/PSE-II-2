package controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.*;

public class Teacher_Controller {
	
	EntityManagerFactory emfactory;
	CriteriaBuilder cb ;
	EntityManager entityManager;
	
	
	
	
	
	public Teacher_Controller() {
		emfactory=Persistence.createEntityManagerFactory("learning");
		 entityManager = emfactory.createEntityManager();
		 cb= entityManager.getCriteriaBuilder();
		
	}
	
	

	public boolean teacher_login(String uname,String pass)
	{
	
		CriteriaQuery<Teacher> cq= cb.createQuery(Teacher.class);
		Root<Teacher> teachers= cq.from(Teacher.class);
		cq.select(teachers);
		List<Teacher> rteachers = entityManager.createQuery(cq).getResultList();
		for(Teacher tt : rteachers)
		{
			if (tt.getName().equalsIgnoreCase(uname)&& tt.getPassword().equals(pass))
			{
				
				return true;
			}
		}
		
		return false;
	}
	
	
	public void add_teacher(Teacher t)
	{
		

		entityManager.getTransaction().begin();
		if(check_existance(t))
		{
			entityManager.persist(t);
    		entityManager.getTransaction().commit();
		}
		else
		{
			System.out.println("Teacher already Exists ");
		}
			
		entityManager.close();
		emfactory.close();
	}

	
	private boolean check_existance(Teacher t)
	{
	
		entityManager.getTransaction().begin();
	
	    CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);
	    Root<Teacher> teacher = cq.from(Teacher.class);
	        cq.where(teacher.get(Teacher_.name).in(t.getName()),teacher.get(Teacher_.email).in(t.getEmail()));
	        cq.select(teacher);
	        List<Teacher> resultList = entityManager.createQuery(cq).getResultList();
	        if (resultList.isEmpty()) return true;
			else return false;
	}
	
	
	
	
	public void modify_course(String course_name,Course c)
	{
		
		entityManager.getTransaction().begin();	
		Course course_to_modify=new Course_Controller().course_search(course_name);
		if (course_to_modify == null)
		{
			System.out.println("No Such course");
		}
		else
		{
		course_to_modify.setCredits(c.getCredits());
		course_to_modify.setName(c.getName());
		course_to_modify.setStart_time(c.getStart_time());
		entityManager.merge(course_to_modify);  
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		emfactory.close();		
	}
	
	
	public Student student_search(String name)
	{
		
		Student s=null;
		CriteriaQuery<Student> cq= cb.createQuery(Student.class);
		Root<Student> students= cq.from(Student.class);
		cq.select(students);
		List<Student> rstudents = entityManager.createQuery(cq).getResultList();
		for(Student ss : rstudents)
		{
			if (ss.getName().equalsIgnoreCase(name))
			{
				s=ss;
				break;
			}
		}
		return s;
	}
	
	public Teacher teacher_search(String name)
	{
		
		Teacher s=null;
		CriteriaQuery<Teacher> cq= cb.createQuery(Teacher.class);
		Root<Teacher> Teachers= cq.from(Teacher.class);
		cq.select(Teachers);
		List<Teacher> rTeacher = entityManager.createQuery(cq).getResultList();
		for(Teacher ss : rTeacher)
		{
			if (ss.getName().equalsIgnoreCase(name))
			{
				s=ss;
				break;
			}
		}
		return s;
	}
	
	
	public void delete_course(String name)
	{
		
		entityManager.getTransaction().begin();
	
		Course course_to_remove=new Course_Controller().course_search(name);
		Course c=entityManager.find(Course.class, course_to_remove.getCode());
		entityManager.remove(c);
		entityManager.flush();  
		entityManager.getTransaction().commit();
		entityManager.close();
		emfactory.close();		
	}
	
	
	
	public void course_details(String name)
	{
		Course c=new Course_Controller().course_search(name);
		if (c !=null) {
		System.out.println(c.getName()+":"+c.getCredits());
		System.out.println("Registered students:");
		for(int i=0 ; i<c.getStudent().size(); i++)
		{
			System.out.println(c.getStudent().get(i).getCode() + " - " + c.getStudent().get(i).getName());
		}
		}else System.out.println("No such course");
		
	}
	
	
	
	
	public void give_grade(Course c, Student s,int grade)
	{
		
		entityManager.getTransaction().begin();
		Result r = new Result (grade,s,c);
		entityManager.merge(r);
		entityManager.flush();  
		entityManager.getTransaction().commit();
		entityManager.close();
		emfactory.close();		
	}

	
	public List<Student> list_all_students()
	{
	
		CriteriaQuery<Student> cq= cb.createQuery(Student.class);
		Root<Student> students= cq.from(Student.class);
		cq.select(students);
		List<Student> rstudents=entityManager.createQuery(cq).getResultList();
		if(rstudents.isEmpty() || rstudents==null) {
			return null;
		}
		return rstudents;

	}
	
	public List<Teacher> list_all_teachers()
	{
	
		CriteriaQuery<Teacher> cq= cb.createQuery(Teacher.class);
		Root<Teacher> Teachers= cq.from(Teacher.class);
		cq.select(Teachers);
		List<Teacher> rTeachers =entityManager.createQuery(cq).getResultList();
		if(rTeachers.isEmpty() || rTeachers==null) {
			return null;
		}
		return rTeachers;

	}
	
}
