package main.Manager;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFileChooser;

import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImgChooser{

	JFileChooser fileChooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "png");

	public void printFilePath(JTextField txtImg) {


		fileChooser.setFileFilter(filter); // 파일 필터 추가
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		// 창 열기 정상 수행시 0 반환, 취소시 1 반환

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			txtImg.setText(fileChooser.getSelectedFile().getPath());
			// 레이블에 파일 경로 넣기


		}

	}
}
