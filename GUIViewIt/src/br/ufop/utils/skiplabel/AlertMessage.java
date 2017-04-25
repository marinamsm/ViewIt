package br.ufop.utils.skiplabel;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertMessage {
	
	public static void showConfirmationAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		showAlert(title, header, content, alert);
	}
	
	public static void showErrorAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
	}
	
	public static void showWarningAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
	}
	
	public static void showInfoAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
	}
	
	private static void showAlert(String title, String header, String content, Alert alert) {
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
