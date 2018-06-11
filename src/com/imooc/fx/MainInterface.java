package com.imooc.fx;
import com.imooc.dao.*;
import com.imooc.bean.Message;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;;
public class MainInterface extends Application{
	public static String content="";
	public TextField textField=new TextField();
	public TextField [] text=new TextField[6];
	public Stage stage=new Stage();
	@Override
	public void start(Stage primaryStage){
		stage.setTitle("大众点评");
	    stage.setScene(firstScene());
		stage.show();
	}
    public static void main(String[] args){
    	Application.launch(args);
    }
    public Scene firstScene(){
    	HBox hBox=new HBox();
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		Label search=new Label("Search");
		Button go=new Button("Go");	
		Button concel=new Button("Concel");
		Button insert=new Button("Insert");
		/*go.setStyle("-fx-background-color:white");
		concel.setStyle("-fx-background-color:white");*/
		hBox.setStyle("-fx-background-iamge:url(Image/us.gif);");
		go.setOnAction(new StartSearch());
		concel.setOnAction(new Concel());
		insert.setOnAction(new InsertScene());
		hBox.getChildren().addAll(search,textField,go,concel,insert);
		Scene scene=new Scene(hBox,600,200);
		return scene;
    }
    public Scene nextScene(){
    	MessageDao messageDao = new MessageDao();
    	VBox pane=new VBox();
		Button back=new Button("Back");
		Button concel=new Button("Concel");
		//Label label=new Label(messageDao.search(content).getAddress());
		//Label label=new Label(content);
		//label.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,16));
		concel.setOnAction(new Concel());
		back.setOnAction(new lastScene());
		HBox hBox=new HBox();
		hBox.setSpacing(275);
		hBox.getChildren().addAll(back,concel);
		Image image=new Image(messageDao.search(content).getJpgURL());
        pane.getChildren().add(new ImageView(image));	
		pane.getChildren().addAll(new Label(messageDao.search(content).getName()),
				                  new Label(messageDao.search(content).getAddress()),
				                  new Label(messageDao.search(content).getAvgScore()),
				                  new Label(messageDao.search(content).getAllCommentNum()),
				                  new Label(messageDao.search(content).getAvgPrice()),
				                  hBox);
				               
		pane.setAlignment(Pos.CENTER);
		Scene scene=new Scene(pane,400,200);
		return scene;
    }
    public Scene insertScene(){
    	GridPane pane=new GridPane();
    	pane.setAlignment(Pos.CENTER);
    	pane.setHgap(5.5);
    	pane.setVgap(20);
    	pane.add(new Label("商家名"),0,0);
        text[0]=new TextField();
    	pane.add(text[0],1,0);
    	pane.add(new Label("地址"),0,1);
    	text[1]=new TextField();
    	pane.add(text[1],1,1);
    	pane.add(new Label("图片资源"),0,2);
    	text[2]=new TextField();
    	pane.add(text[2],1,2);
    	pane.add(new Label("评分"),0,3);
    	text[3]=new TextField();
    	pane.add(text[3],1,3);
    	pane.add(new Label("评论数"),0,4);
    	text[4]=new TextField();
    	pane.add(text[4],1,4);
    	pane.add(new Label("平均价格"),0,5);
    	text[5]=new TextField();
    	pane.add(text[5],1,5);
    	Button btOK=new Button("OK");
    	btOK.setOnAction(new InsertDB());
    	pane.add(btOK,6,6);
    	Scene scene=new Scene(pane,400,400);
    	return scene;
    }
    class StartSearch implements EventHandler<ActionEvent>{
    	public void handle(ActionEvent e){
    		content=textField.getText();
    		textField=new TextField();
    		stage.close();
    		stage.setTitle("商家信息");
    		stage.setScene(nextScene());
    		stage.show();
    	}
    }
    class Concel implements EventHandler<ActionEvent>{
    	public void handle(ActionEvent e){
    		stage.close();
    	}
    }
    class lastScene implements EventHandler<ActionEvent>{
    	public void handle(ActionEvent e){
    		stage.close();
    		stage.setTitle("大众点评");
    		stage.setScene(firstScene());
    		stage.show();
    	}
    }
    class InsertScene implements EventHandler<ActionEvent>{
    	public void handle(ActionEvent e){
    		stage.close();
    		stage.setTitle("插入商家信息");
    		stage.setScene(insertScene());
    		stage.show();
    	}
    }
    class InsertDB implements EventHandler<ActionEvent>{
    	public void handle(ActionEvent e){
    		Message a=new Message();
    		a.setName(text[0].getText());
    		a.setAddress(text[1].getText());
    		a.setJpgURL(text[2].getText());
    		a.setAvgScore(text[3].getText());
    		a.setAllCommentNum(text[4].getText());
    		a.setAvgPrice(text[5].getText());
    		MessageDao messageDao = new MessageDao();
    		messageDao.insert(a);
    		stage.setTitle("大众点评");
    		stage.setScene(firstScene());
    		stage.show();
    	}
    }
}
