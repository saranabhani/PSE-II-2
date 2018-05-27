package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.*;

public class Student_Controller {
	
	
	EntityManagerFactory emfactory;
	CriteriaBuilder cb ;
	EntityManager entityManager;
	
	public  Student_Controller() {
		emfactory=Persistence.createEntityManagerFactory("learning");
		 entityManager = emfactory.createEntityManager();
		 cb= entityManager.getCriteriaBuilder();
		
	}
	
	public boolean student_login(String uname,String pass)
	{
		
		System.out.println("faat");
		CriteriaQuery<Student> cq= cb.createQuery(Student.class);
		Root<Student> students= cq.from(Student.class);
		cq.select(students);
		List<Student> rstudents = entityManager.createQuery(cq).getResultList();
		for(Student ss : rstudents)
		{
			if (ss.getName().equalsIgnoreCase(uname)&& ss.getPassword().equals(pass))
			{
				System.out.println("faaaat");
				return true;
			}
		}
		
		return false;
	}
	
	
	public void add_student(Student s)
	{
		
		
		entityManager.getTransaction().begin();
		if(check_existance(s))
		{
			entityManager.persist(s);
    		entityManager.getTransaction().commit();
		}
		else
		{
			System.out.println("Student already Exists ");
		}
			

		entityManager.close();
		emfactory.close();
	}

	
	private boolean check_existance(Student s)
	{
		
		entityManager.getTransaction().begin();
		cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Student> cq = cb.createQuery(Student.class);
	    Root<Student> student = cq.from(Student.class);
	    cq.where(student.get(Student_.name).in(s.getName()),student.get(Student_.email).in(s.getEmail()));
	    cq.select(student);
	    List<Student> resultList = entityManager.createQuery(cq).getResultList();
	    if (resultList.isEmpty()) return true;
	    else return false;
	}

	

	
	
	public void take_course(Student s,Course c)
	{
		
		entityManager.getTransaction().begin();
		cb = entityManager.getCriteriaBuilder();
		c.getStudent().add(s);
		entityManager.merge(c);
		entityManager.flush();  
		entityManager.getTransaction().commit();
		entityManager.close();
		emfactory.close();		
	}
	
	
	public void drop_course(Student s,Course c)
	{
	
		entityManager.getTransaction().begin();
		cb = entityManager.getCriteriaBuilder();
		c.getStudent().remove(s);
		entityManager.merge(c);
		entityManager.flush();  
		entityManager.getTransaction().commit();
		entityManager.close();
		emfactory.close();		
		
	}
	
	
	public List <Course> gaind_credits(Student s)
	{
		//int total=0;
		List<Course> co = new ArrayList<Course>();
		for (int i=0; i<s.getResults().size();i++)
		{
			co.add(s.getResults().get(i).getCourse());
		}
		return co;
		
		//System.out.println("Total credits:" + total);
	}
	
	
	
	
	public void list_taken_courses(Student s)
	{
		int total=0;
		for (int i = 0; i < s.getCourses().size(); i++) {
			System.out.println(s.getCourses().get(i).getName() + ": " + s.getCourses().get(i).getCredits()+" credits");	
			total+=s.getCourses().get(i).getCredits();
		}
		System.out.println(total);
	}
	
	public List<Course> list_available_courses(Student s)
	{
		Course_Controller cc =new Course_Controller();
		List<Course> union = new ArrayList<Course>(s.getCourses());
		union.addAll(cc.list_all_courses());
		List<Course> intersection = new ArrayList<Course>(s.getCourses());
		intersection.retainAll(cc.list_all_courses());
		union.removeAll(intersection);
		return union;
	}
	
	public void check_grades(Student s)
	{
		for (int i = 0; i < s.getResults().size(); i++) {
			System.out.println("Course Name:" +s.getResults().get(i).getCourse().getName() + " , Grade: " + s.getResults().get(i).getGrade());			
		}
	}

	
	
	
	
	
}
