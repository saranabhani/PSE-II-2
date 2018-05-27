package main;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import controller.Course_Controller;
import controller.Student_Controller;
import controller.Teacher_Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Course;
import model.Student;
import model.Teacher;

public class Teacher_view extends Application implements EventHandler<ActionEvent>  {

	public Scene scene;
	public String name;
	Button logoutbtn;
	Button out;
	Button removebtn,editbtn,addbtn,sout,gbtn,gout;
	@FXML
	ListView<String> clist;
	ListView<String> slist;
	ListView<String> colist,ctlist;
	ListView<String> stlist,sttlist;
	ObservableList<String> cdata,ctdata;
	ObservableList<String> stdata,sttdata;
	ObservableList<String> codata;
	ChoiceBox gards;
	public Teacher_view(String name) {
		this.name = name;
		prepare_scene();
	}

	private void prepare_scene() {
		// TODO Auto-generated method stub
		
		TabPane tp = new TabPane();
		Tab courses_tab = new Tab();
		courses_tab.setText("Courses");
		intialize_courses(courses_tab);
		Tab students_tab = new Tab();
		students_tab.setText("Students");
		intialize_students(students_tab);
		Tab grades_tab = new Tab();
		grades_tab.setText("Grades");
		intialize_grades(grades_tab);
		
		tp.getTabs().add(courses_tab);
		tp.getTabs().add(students_tab);
		tp.getTabs().add(grades_tab);
	    logoutbtn = new Button();
		logoutbtn.setText("LogOut");
		logoutbtn.setOnAction(this);
		//grid.add(logoutbtn, 1, 1);
	    
	   
	
	  scene = new Scene(tp,900,600);
		// Group root = (Group) scene.getRoot();
		 
	}

	private void intialize_grades(Tab grades_tab) {
		// TODO Auto-generated method stub
		GridPane grid =new GridPane();
		grid.setPadding(new Insets(10, 20, 10, 20));
		grid.setVgap(5); 
	    grid.setHgap(5); 
		Teacher_Controller cc = new Teacher_Controller();
		Teacher t = cc.teacher_search(name);
		List<Course> courses = t.getCourses();
		 ctdata = FXCollections.observableArrayList();
		 sttdata = FXCollections.observableArrayList();
		ctlist = new ListView<>();
		sttlist= new ListView<>();
		for (int i=0; i<courses.size();i++)
		{
			ctdata.add(courses.get(i).getName());
		}
		
	
		ctlist.setItems(ctdata);
		ctlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				System.out.println("fat");
				Course_Controller coc = new Course_Controller();
				Course c = coc.course_search(newValue);
				sttdata.clear();
				
				List<Student> students=c.getStudent();
				for(int i=0; i< students.size();i++)
				{
					sttdata.add(students.get(i).getName());
				}
			}
			
		});
		
		sttlist.setItems(sttdata);
		
		gbtn = new Button();
		gbtn.setText("Give grade");
		gbtn.setOnAction(this);
		 gards = new ChoiceBox(FXCollections.observableArrayList(
				1 , 2, 3, 4 , 5)
			);
		gout = new Button();
		gout.setText("Logout");
		gout.setOnAction(this);
		grid.add(new Label("Courses"), 0, 0);
		grid.add(ctlist, 0, 1);
		grid.add(new Label("Students"), 1, 0);
		grid.add(sttlist, 1, 1);
		grid.add(new Label("grade"), 2, 1);
		grid.add(gards, 3, 1);
		grid.add(gbtn, 2, 2);
		grid.add(gout, 3, 2);
		
		grades_tab.setContent(grid);
		
		
	}

	private void intialize_students(Tab students_tab) {
		// TODO Auto-generated method stub
		GridPane grid =new GridPane();
		grid.setPadding(new Insets(10, 20, 10, 20));
		grid.setVgap(5); 
	    grid.setHgap(5); 
		Teacher_Controller cc = new Teacher_Controller();
		List<Student> students = cc.list_all_students();
		 stdata = FXCollections.observableArrayList();
		 for (int i=0; i<students.size();i++)
			{
				stdata.add(students.get(i).getName());
			}
	 codata = FXCollections.observableArrayList();
		stlist = new ListView<>();
		stlist.setItems(stdata);
		stlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				System.out.println("fat");
				Student s = cc.student_search(newValue);
				codata.clear();
				
				List<Course> courses=s.getCourses();
				for(int i=0; i< courses.size();i++)
				{
					codata.add(courses.get(i).getName());
				}
			}
			
		});
		
		
		
		colist= new ListView<>();
		
		colist.setItems(codata);
		
		sout = new Button();
		sout.setText("Logout");
		sout.setOnAction(this);
		grid.add(new Label("Students"), 0, 0);
		grid.add(stlist, 0, 1);
		grid.add(new Label("Courses"), 1, 0);
		grid.add(colist, 1, 1);
		grid.add(sout, 3, 2);
		
		students_tab.setContent(grid);
		
		
	}

	private void intialize_courses(Tab courses_tab) {
		// TODO Auto-generated method stub
		
		//BorderPane borderPane = new BorderPane();
		GridPane grid =new GridPane();
		grid.setPadding(new Insets(10, 20, 10, 20));
		grid.setVgap(5); 
	    grid.setHgap(5); 
		Course_Controller cc = new Course_Controller();
		List<Course> courses = cc.list_all_courses();
		 cdata = FXCollections.observableArrayList();
		ObservableList<String> sdata = FXCollections.observableArrayList();
		clist = new ListView<>();
		slist= new ListView<>();
		for (int i=0; i<courses.size();i++)
		{
			cdata.add(courses.get(i).getName());
		}
		Label coursedescl = new Label ();
		coursedescl.setText("Course Details:");
		Label coursedet = new Label ();
		clist.setItems(cdata);
		clist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				System.out.println("fat");
				
				
				Course c = cc.course_search(newValue);
				sdata.clear();
				if(c!= null)
				{
				coursedet.setText("Course Code: "+c.getCode()+" Course Name: " + c.getName() + " \n Credits: " + c.getCredits() +" \n Teacher: "+ c.getTeacher().getName()+" \n Time: "+c.getsStart_time());
				List<Student> students=c.getStudent();
				for(int i=0; i< students.size();i++)
				{
					sdata.add(students.get(i).getName());
				}
			
				}
			}
			
		});
		
		slist.setItems(sdata);
		
		 editbtn = new Button();
		 removebtn = new Button();
		 addbtn = new Button();
		editbtn.setText("Edit Course");
		removebtn.setText("Remove Course");
		addbtn.setText("Add Course");
		out = new Button();
		out.setText("Logout");
		out.setOnAction(this);
		removebtn.setOnAction(this);
		addbtn.setOnAction(this);
		editbtn.setOnAction(this);
		grid.add(new Label("Courses"), 0, 0);
		grid.add(clist, 0, 1);
		grid.add(coursedescl, 0, 2);
		grid.add(coursedet, 0, 3);
		grid.add(new Label("Students"), 1, 0);
		grid.add(slist, 1, 1);
		grid.add(addbtn, 3, 2);
		grid.add(editbtn, 4, 2);
		grid.add(removebtn, 5, 2);
		//grid.add(logoutbtn, 1, 2);
		grid.add(out, 5, 3);
		//grid.add(new Label("why"), 5, 3);
		//	borderPane.setLeft(clist);
		//borderPane.set(coursedescl);
		
		courses_tab.setContent(grid);
		
		
		
		
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if((event.getSource()==logoutbtn)||(event.getSource()==out)||(event.getSource()==sout)||(event.getSource()==gout))
		{
			
			Main.intia();
		}
		else if (event.getSource()==removebtn)
		{
			Teacher_Controller tc = new Teacher_Controller();
			String cname=clist.getSelectionModel().getSelectedItem();
			tc.delete_course(cname);
			System.out.println(cname);
			cdata.remove(cname);
			if (cdata.size()==0)
			cdata=FXCollections.observableArrayList();
			clist.setItems(cdata);
			
		}else if (event.getSource()==editbtn)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Edit Course");
			alert.setHeaderText("Course details");
			
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));
			TextField coursen = new TextField();
			coursen.setPromptText("Course Name: ");
			
			ChoiceBox credits = new ChoiceBox(FXCollections.observableArrayList(
					1 , 2, 3, 4 , 5)
				);
			
			Teacher_Controller tc = new Teacher_Controller();
			ObservableList<String> tdata = FXCollections.observableArrayList();
			List<Teacher> teachers = tc.list_all_teachers();
			for (int i=0;i<teachers.size();i++)
			{
				tdata.add(teachers.get(i).getName());
			}
			ChoiceBox teachersb = new ChoiceBox(tdata);
			DatePicker datePicker = new DatePicker();

			grid.add(new Label("Course Name: "), 0, 0);
			grid.add(coursen, 1, 0);
			grid.add(new Label("Credits:"), 0, 1);
			grid.add(credits, 1, 1);
			grid.add(new Label("Teacher:"), 0, 2);
			grid.add(teachersb, 1, 2);
			grid.add(new Label("Start Date"), 0, 3);
			grid.add(datePicker, 1, 3);
			alert.getDialogPane().setExpandableContent(grid);

			Optional<ButtonType> result = alert.showAndWait();
			int cr = (int) credits.getSelectionModel().getSelectedItem();
			String c2 = (String) teachersb.getSelectionModel().getSelectedItem();
			Teacher c1= tc.teacher_search(c2);
			LocalDate localDate = datePicker.getValue();
			Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Calendar cal = Calendar.getInstance();
			 cal.setTime(date);
			//Calendar cc
			 Teacher_Controller cc = new Teacher_Controller();
			if (result.get() == ButtonType.OK){
			    Course c = new Course(coursen.getText(),cr,cal,c1);
			   String course_name = clist.getSelectionModel().getSelectedItem();
			    cc.modify_course(course_name, c);
			    cdata.remove(course_name);
			    cdata.add(c.getName());
				clist.setItems(cdata);
			} else {
			    alert.close();
			}
			
			
			
			
			
			
		}else if(event.getSource()==addbtn)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Add Course");
			alert.setHeaderText("Course details");
			
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));
			TextField coursen = new TextField();
			coursen.setPromptText("Course Name: ");
			
			ChoiceBox credits = new ChoiceBox(FXCollections.observableArrayList(
					1 , 2, 3, 4 , 5)
				);
			
			Teacher_Controller tc = new Teacher_Controller();
			ObservableList<String> tdata = FXCollections.observableArrayList();
			List<Teacher> teachers = tc.list_all_teachers();
			for (int i=0;i<teachers.size();i++)
			{
				tdata.add(teachers.get(i).getName());
			}
			ChoiceBox teachersb = new ChoiceBox(tdata);
			DatePicker datePicker = new DatePicker();

			grid.add(new Label("Course Name: "), 0, 0);
			grid.add(coursen, 1, 0);
			grid.add(new Label("Credits:"), 0, 1);
			grid.add(credits, 1, 1);
			grid.add(new Label("Teacher:"), 0, 2);
			grid.add(teachersb, 1, 2);
			grid.add(new Label("Start Date"), 0, 3);
			grid.add(datePicker, 1, 3);
			alert.getDialogPane().setExpandableContent(grid);

			Optional<ButtonType> result = alert.showAndWait();
			int cr = (int) credits.getSelectionModel().getSelectedItem();
			String c2 = (String) teachersb.getSelectionModel().getSelectedItem();
			Teacher c1= tc.teacher_search(c2);
			LocalDate localDate = datePicker.getValue();
			Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Calendar cal = Calendar.getInstance();
			 cal.setTime(date);
			//Calendar cc
			 Course_Controller cc = new Course_Controller();
			if (result.get() == ButtonType.OK){
			    Course c = new Course(coursen.getText(),cr,cal,c1);
			    cc.add_course(c);
			    cdata.add(c.getName());
				clist.setItems(cdata);
			} else {
			    alert.close();
			}
			
		}else if(event.getSource()==gbtn)
		{
			int g= (int) gards.getSelectionModel().getSelectedItem();
			Course_Controller sc = new Course_Controller();
			Teacher_Controller tc = new Teacher_Controller();
			String sname= sttlist.getSelectionModel().getSelectedItem();
			String cname = ctlist.getSelectionModel().getSelectedItem();
			Student s= tc.student_search(sname);
			Course c= sc.course_search(cname);
			tc.give_grade(c, s, g);
			
		}
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
