package homework;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

class TxtReader{
	private Frame f;
	private MenuBar bar;
	private TextArea textArea;
	private Menu fileMenu;
	private MenuItem openItem, closeItem, exitItem;
	private FileDialog openFile;
	private File file;

	TxtReader(){
		f = new Frame("SimpleTXTReader");
		f.setSize(800,600); 
		bar = new MenuBar();
		textArea = new TextArea();
		fileMenu = new Menu("文件");
		openItem = new MenuItem("打开");
		closeItem = new MenuItem("关闭");
		exitItem = new MenuItem("退出");
		fileMenu.add(openItem);
		fileMenu.add(closeItem);
		fileMenu.addSeparator(); //添加分割线
		fileMenu.add(exitItem);
		bar.add(fileMenu);
		f.setMenuBar(bar);
		textArea.setEditable(false);//只可读
		closeItem.disable();  //关闭菜单不可选
		f.setVisible(true);
		openFile = new FileDialog(f, "打开", FileDialog.LOAD);
		f.add(textArea);
		Event();
	}

	private void Event(){
		openItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				openFile.setVisible(true); 
				//String filepath = System.getProperty("user.dir");
				String filepath = openFile.getDirectory();
				String fileName = openFile.getFile();
				if(filepath == null || fileName == null){
					return;
				}
				else{
					textArea.setText(null); 
				}
				file = new File(filepath, fileName); 
				file.setWritable(false);
				try{
					if(file.getName().endsWith(".txt") == true){
						closeItem.enable();
						BufferedReader buffer = new BufferedReader(new FileReader(file));
						String line = null; 
						while ((line = buffer.readLine()) != null){  //读文件
							textArea.append(line+"\n"); 
						}
						buffer.close();
					}
				}catch(FileNotFoundException e1){
					e1.printStackTrace();
				}catch(IOException e2){
					e2.printStackTrace();
				}
			}
		});

		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(""); //清空text
				closeItem.disable(); //关闭菜单不开心
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new TxtReader();
	}
}