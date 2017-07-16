package br.ufop.performance.gui;

import java.io.File;

import br.ufop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class AdvancedSettingsTestController {
	private Main main;
	
	@FXML
	private Button back;
	
	@FXML
	private Button myActions;
	
	@FXML
	private Button typing;
	
	@FXML
	private Button checkingBoxes; 
	
	@FXML
	private Button selectingOption; //the action and the view is the same as checkingBoxes
	
	@FXML
	private Button clicking;
	
	@FXML
	private Button submitting; //the action and the view is the same as clicking
	
	@FXML
	private Button checkingAlert;
	
	@FXML
	private Button contextClicking;
	
	/**This function sets a variable with the main controller of the application*/
	/**Recebe o principal controlador (Main) da aplicação*/
	public void setMain(Main main) {
        this.main = main;
	}
	
	/**Returns to main test screen*/
	/**Retorna à tela principal de testes*/
	@FXML
	private void backButtonAction() {
		main.showTestCreationView();
	}

	/**Shows chosen actions (e.g: clicking, typing etc)*/
	/**Exibe as ações escolhidas (ex: clicking, typing)*/
	@FXML
	private void myActionsButtonAction() {
		main.showMyActionsView();
	}
	
	/**Opens Typing view so the user can set details about the action of typing*/
	/**Abre a tela da ação Typing(digitar) para o usuário configurar detalhes da ação de digitar*/
	@FXML
	private void typingButton() {
		main.showTypingView();
	}
	
	/**Opens Checking Boxes view so the user can set details about this action*/
	/**Abre a tela da ação Checking Boxes (selecionar uma ou várias opções) para o usuário configurar detalhes desta ação*/
	@FXML
	private void checkingBoxesButton() {
		main.showCheckingBoxesView();
	}
	
	/**Opens Selecting Option view so the user can set details about this action*/
	/**Abre a tela da ação Selecting Option (selecionar uma opção) para o usuário configurar detalhes desta ação*/
	@FXML
	private void selectingOptionButton() {
		//it has the same action and view as checkingBox
		main.showSelectingOptionView();
	}
	
	/**Opens Clicking view so the user can set details about this action*/
	/**Abre a tela da ação Clicking(clicar) para o usuário configurar detalhes desta ação*/
	@FXML
	private void clickingButton() {
		main.showClickingView();
	}
	
	/**Opens Submitting view so the user can set details about this action*/
	/**Abre a tela da ação Submitting(submeter formulário) para o usuário configurar detalhes desta ação*/
	@FXML
	private void submittingButton() {
		main.showSubmittingView();
	}
	
	/**Opens Checking Alert view so the user can set details about this action*/
	/**Abre a tela da ação Checking Alert(confirmar/recusar mensagem de alerta) para o usuário configurar detalhes desta ação*/
	@FXML
	private void checkingAlertButton() {
		main.showCheckingAlertView();
	}
	
	/**Opens Context Clicking view so the user can set details about this action*/
	/**Abre a tela da ação Context Clicking para o usuário configurar detalhes desta ação*/
	@FXML
	private void contextClickingButton() {
		main.showContextClickingView();
	}
}
