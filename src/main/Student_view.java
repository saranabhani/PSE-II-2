package main;

import java.util.List;

import controller.Course_Controller;
import controller.Student_Controller;
import controller.Teacher_Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Course;
import model.Student;

public class Student_view extends Application implements EventHandler<ActionEvent> {

	public Scene scene;
	public String name;
	Button logoutbtn,register,drop;
	ListView<String> current, comp,avail;
	ObservableList<String> cdata,codata,adata;
	
	
	public Student_view(String name) {
		this.name = name;
		prepare_scene();
	}

	private void prepare_scene() {
		// TODO Auto-generated method stub
		logoutbtn = new Button();
		logoutbtn.setText("LogOut");
		logoutbtn.setOnAction(this);
		GridPane grid =new GridPane();
		grid.setPadding(new Insets(10, 20, 10, 20));
		grid.setVgap(5); 
	    grid.setHgap(5); 
	    adata=FXCollections.observableArrayList();
	    Teacher_Controller tc = new Teacher_Controller();
	    Student_Controller sc = new Student_Controller();
	    Student s = tc.student_search(name);
	    List<Course> courses = sc.list_available_courses(s);
		for(int i=0;i<courses.size();i++)
		{
			adata.add(courses.get(i).getName());
		}
		avail=new ListView<>();
		avail.setItems(adata);
		boolean exist;
		cdata=FXCollections.observableArrayList();
		for (int i= 0 ; i < s.getCourses().size();i++)
		{
			exist= false;
			for (int j=0; j< s.getResults().size();j++)
			{
				if (s.getResults().get(j).getCourse().getName().equals(s.getCourses().get(i).getName()))
				{
					exist=true;
					break;
				}
				
				
			}
			if (!exist) cdata.add(s.getCourses().get(i).getName());
		}
		current=new ListView<>();
		current.setItems(cdata);
		
		codata=FXCollections.observableArrayList();
		List<Course> cocourses = sc.gaind_credits(tc.student_search(name));
		int res=0;
		for (int i=0 ;i <cocourses.size();i++)
		{	
			for (int j=0; j< s.getResults().size();j++)
			{
				if (s.getResults().get(j).getCourse().getName().equals(s.getCourses().get(i).getName()))
				{
					res=s.getResults().get(j).getGrade();
				}	
			}
		
			codata.add(cocourses.get(i).getName() + " Grade: " +res );
		}
		comp=new ListView<>();
		comp.setItems(codata);
		
		register=new Button(); register.setText("Register"); register.setOnAction(this);
		drop=new Button(); drop.setText("Drop"); drop.setOnAction(this);
		
		
		grid.add(new Label("Available Courses:"), 0, 0);
		grid.add(avail, 0, 1);
		grid.add(register, 0, 2);
		grid.add(new Label("Current Courses:"), 1, 0);
		grid.add(current, 1, 1);
		grid.add(drop, 1, 2);
		grid.add(new Label("Completed Courses:"), 2, 0);
		grid.add(comp, 2, 1);
		grid.add(logoutbtn, 2, 2);
		
		 scene = new Scene(grid,900,600);
		
		
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource()==logoutbtn)
		{
			
			Main.intia();
		}
		else if(event.getSource()== register)
		{
			Course_Controller cc= new Course_Controller(); 
			Teacher_Controller tc= new Teacher_Controller();
			Student_Controller sc= new Student_Controller(); 
			String cname= avail.getSelectionModel().getSelectedItem();
			Course c = cc.course_search(cname);
			Student s= tc.student_search(name);
			sc.take_course(s, c);
			adata.remove(cname);
			cdata.add(cname);
			current.setItems(cdata);
			avail.setItems(adata);
			
		}
		else if(event.getSource()==drop) 
		{
			Course_Controller cc= new Course_Controller(); 
			Teacher_Controller tc= new Teacher_Controller();
			Student_Controller sc= new Student_Controller(); 
			String cname= current.getSelectionModel().getSelectedItem();
			Course c = cc.course_search(cname);
			Student s= tc.student_search(name);
			sc.drop_course(s, c);
			
			adata.add(cname);
			cdata.remove(cname);
			current.setItems(cdata);
			avail.setItems(adata);
		}
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
