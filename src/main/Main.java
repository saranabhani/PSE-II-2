package main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.*;

public class Main extends Application implements EventHandler<ActionEvent> {


	
	Button sb,tb;
	static TextField uname;
	static PasswordField pass;
	Teacher_Controller tc;
	Student_Controller sc;
	public static  Stage window;
	public static Scene scene;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	
	}



		public void  new_Teacher() {
			Teacher_Controller tc=new Teacher_Controller();
			Teacher t= new Teacher ("tama","bjg","@hoa","tutor");
			tc.add_teacher(t);		
	}
		
		public Course new_course(EntityManager entityManager)
		{

			Teacher t = entityManager.find(Teacher.class, 1);
			Calendar calendar = new GregorianCalendar(2013,0,31);
			Course c=new Course ("xxx",5,calendar,t);
			Course_Controller cc=new Course_Controller();
			cc.add_course(c);
			return c;
		}
		
		public void new_student()
		{
			 Student_Controller sc=new Student_Controller();
			 Student s=new Student("nnn","bo","@blakdf");
			 sc.add_student(s);
		}



		@Override
		public void start(Stage primarystage) throws Exception {
			// TODO Auto-generated method stub
			window = primarystage;
			tc=new Teacher_Controller();
			window.setTitle("Learning System");
			 sb= new Button();
			 tb = new Button();
			 uname= new TextField();
			 pass = new PasswordField();
			 
			sb.setText("Student Login");
			tb.setText("Teacher Login");
			sb.setOnAction(this);
			tb.setOnAction(this);
			
			GridPane grid = new GridPane();
			grid.setVgap(4);
		    grid.setHgap(10);
		    grid.setPadding(new Insets(5, 5, 5, 5));
		    grid.add(new Label("User Name: "), 0, 0);
		    grid.add(uname, 1, 0);
		    grid.add(new Label("Password: "), 0, 1);
		    grid.add(pass, 1, 1);
		    
		   
		
			 scene = new Scene(grid,300,300);
			    grid.add(sb, 1, 3);
			    grid.add(tb, 1, 5);
			
		
			    window.setScene(scene);
			    window.show();
			
			
			
		}






		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource()==sb)
			{
				sc=new Student_Controller();
				if(sc.student_login(uname.getText(), pass.getText()))
					{
						
						Student_view sv = new Student_view(uname.getText());
						window.setScene(sv.scene);
					
					}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					 
					alert.setTitle("Error logging in");
					alert.setHeaderText("Failed logging in");
					alert.setContentText("username or password is incorrect");
					 
					alert.showAndWait();
				}

			}
			else if(event.getSource()==tb)
			{
				if(tc.teacher_login(uname.getText(), pass.getText()))
				{
					Teacher_view tv = new Teacher_view(uname.getText());
					window.setScene(tv.scene);
				}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				 
				alert.setTitle("Error logging in");
				alert.setHeaderText("Failed logging in");
				alert.setContentText("username or password is incorrect");
				 
				alert.showAndWait();
			}
			}
		}
		

		public static void intia()
		{
			uname.setText("");
			pass.setText("");
			window.setScene(scene);
		}
		
}
