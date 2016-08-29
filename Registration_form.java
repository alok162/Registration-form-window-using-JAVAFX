

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Registration_form extends Application{
	String genderinput="";
	String dateoutput="";
	boolean flag1=false,flag2=false,flag3=false,flag4=false;
	public static void main(String args[])
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	    
		GridPane root=new GridPane();
		root.setHgap(10);
		root.setVgap(18);
		Text textscene=new Text("Welcome To Registration Form!");
		
		Label name=new Label("User_Name:");
		final TextField nameinput=new TextField();
		nameinput.setPromptText("Enter your full Name");
		nameinput.setFocusTraversable(false);
		root.add(name,0,1);
		root.add(nameinput,1,1);
		
		Label dob=new Label("DOB:");
		String pattern="yyyy-MM-dd";
		DatePicker date=new DatePicker();
		date.setPromptText(pattern.toLowerCase());

		date.setConverter(new StringConverter<LocalDate>() {
		     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 });
		root.add(dob,0,2);
		
		root.add(date,1,2);
		
		Label gender=new Label("Gender:");
		root.add(gender, 0, 3);
		ToggleGroup pickgender=new ToggleGroup();
		RadioButton pickmale=new RadioButton("Male");
	    pickmale.setToggleGroup(pickgender);
	    root.add(pickmale, 1, 3, 1, 1);
	    
	    RadioButton pickfemale=new RadioButton("Female");
	    pickfemale.setToggleGroup(pickgender);
	    root.add(pickfemale, 2, 3);
	    
	    
	   /*pickgender.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	        public void changed(ObservableValue<? extends Toggle> ov,
	            Toggle old_toggle, Toggle new_toggle) {
	          if (pickgender.getSelectedToggle() != null) {
	            //System.out.println(pickgender.getSelectedToggle().getUserData().toString());
	          }
	        }
	      });*/
	    Label interest=new Label("Interest:");
	    TextField interestinput=new TextField();
	    interestinput.setPromptText("Enter your interest");
	    interestinput.setFocusTraversable(false);
	    root.add(interest, 0, 4);
	    root.add(interestinput, 1, 4);
	    
	   root.add(textscene,0,0,3,1);
		Scene scene=new Scene(root,500,500);
		primaryStage.setTitle("Registration Form!");
		textscene.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
		root.setAlignment(Pos.CENTER);
		
		 Button save=new Button("Save");
		    root.add(save, 1, 5);
		    dateoutput=date.toString();
		  //dateoutput=date.getValue().toString();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date d = new Date();
		    //String alok=d.toString();
		    String alok=dateFormat.format(d).toString();
		    save.setOnAction(new EventHandler<ActionEvent>()
		    	{
                        @Override
						public void handle(ActionEvent event) {
                        	
                        	if(pickmale.isSelected())
                	    	{ 
                	    	genderinput="male"; 
                	    	flag3=true;
                	    	}
                	    if(pickfemale.isSelected())
                	    	{  
                	    	flag3=true;
                	    	genderinput="female"; 
                	    	}
                	    if(nameinput.getText().length()>=2 && nameinput.getText().matches("[A-Za-z ]+"))
                		{
                	    	flag1=true;
                		}
                	    if(interestinput.getText().length()>=2 && interestinput.getText().matches("A-Za-z ]+"))
                	    {
                	    	flag4=true;
                		}
                	    if(dateoutput.compareTo(alok)<0)
                	    {
                	    	flag2=true;
                	    }
                            System.out.println("Your Details:-");
                        	 
							System.out.println("Name: "+nameinput.getText());
							System.out.println("DOB: "+dateoutput);
							System.out.println("Gender: "+genderinput);
							System.out.println("Interest: "+interestinput.getText());
                	    Alert alert = new Alert(AlertType.WARNING);	
                	    if(flag1 && flag3 && flag4)
                	    {
							writenewuser();
                        }
                	    else if(!flag1)
                	    {
                	    	alert.setContentText("Please enter some valid name");
                	    	alert.showAndWait();
                	    }
                	    else if(!flag2)
                	    {
                	    
                	    	alert.setContentText("Pleese choose some valid date");
                	    	alert.showAndWait();
                	    }
                	    else if(!flag3)
                	    {
                	    	alert.setContentText("Please choose gender");
                	    	alert.showAndWait();
                	    }
                	    else if(!flag4)
                	    {
                	    	alert.setContentText("Please fill some valid interest");
                	    	alert.showAndWait();
                	    }
                	    else if(!flag1 && !flag3 || !flag1 && !flag4 || !flag3 && !flag4)
                	    {
                	    	alert.setContentText("Please fill appropriate in the field");
                	    	alert.showAndWait();
                	    }
                	   
                        }
                        public void writenewuser() {
                        	try
                 		    {
                 		    	FileWriter fstream=new FileWriter("registration_form.txt");
                 		    	BufferedWriter out=new BufferedWriter(fstream);
                 		    	out.write(nameinput.getText());
                 		    	out.newLine();
                 		    	out.write(dateoutput);
                 		    	out.newLine();
                 		    	out.write(genderinput);
                 		    	out.newLine();
                 		    	out.write(interestinput.getText());
                 		    	out.close();
                 		    }
                 		    catch(IOException e)
                 		    {
                 		    	e.printStackTrace();;
                 		    }
            				
            			}
						
		    		});
		    
		   
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
