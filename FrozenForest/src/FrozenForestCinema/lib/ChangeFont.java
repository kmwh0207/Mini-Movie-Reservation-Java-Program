package FrozenForestCinema.lib;

import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ChangeFont {
	//폰트 변경 메소드
		public static <T extends JPanel> void changefont_big(T class_){
			for( int i=0; i<class_.getComponentCount();i++) {
				class_.getComponent(i).setFont(new Font("Serif", Font.BOLD, 30));
			}
		}
		public static <T extends JPanel> void changefont_small(T class_){
			for( int i=0; i<class_.getComponentCount();i++) {
				class_.getComponent(i).setFont(new Font("Serif", Font.PLAIN, 20));
			}
		}
		public static <T extends JComponent> void changefont(T label,int size){
			label.setFont(new Font("Serif", Font.CENTER_BASELINE+Font.BOLD, size));
		}
}
