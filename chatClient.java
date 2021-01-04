package homework;

import java.io.*;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class chatClient extends JFrame {
	private static final long serialVersionUID = 1L;
	Socket client = null;
	Container container = this.getContentPane();
	private JTextField inputBox = new JTextField(30), newName = new JTextField(15);
	private JButton sendBtn;
	private MenuItem changeNameBtn, exitBtn;
	private JTextArea chatContentBox = new JTextArea();
	private JPanel north = new JPanel(new BorderLayout());
	private JPanel south = new JPanel();
	private BufferedReader in;
	public String name = "";

	public chatClient() {
		// 整体部分
		setTitle("聊天室");
		setSize(800, 960);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// 禁用窗口右上角的关闭键

		// 工具栏
		MenuBar menubar = new MenuBar();
		setMenuBar(menubar);
		Menu file = new Menu("操作");
		file.setFont(new Font("幼圆", Font.PLAIN, 25));
		changeNameBtn = new MenuItem("改名");
		exitBtn = new MenuItem("退出");
		file.add(changeNameBtn);
		file.addSeparator();
		file.add(exitBtn);
		menubar.add(file);

		//
		newName.setFont(new Font("幼圆", Font.PLAIN, 25));

		// north——聊天内容框chatContentBox
		north.add(newName);
		add(north, BorderLayout.NORTH);
		chatContentBox.setEditable(false);
		chatContentBox.setLineWrap(true);
		chatContentBox.setWrapStyleWord(true);
		chatContentBox.setFont(new Font("幼圆", Font.PLAIN, 25));

		// 按钮 退出exitBtn 发送sendBtn
		sendBtn = new JButton("发送");
		sendBtn.setFont(new Font("幼圆", Font.PLAIN, 23));

		// south——输入信息框inputBox
		inputBox = new JTextField(30);
		inputBox.setFont(new Font("幼圆", Font.PLAIN, 25));
		south.add(new JLabel());
		south.add(inputBox);
		south.add(sendBtn);
		add(new JScrollPane(chatContentBox), BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		setVisible(true);
		
		// 交互的核心
		try {
			client = new Socket("127.0.0.1", 9999);
			chatContentBox.append("你(【" + client.getLocalPort() + "】)正在进入聊天室...\n");
			new ClientThread().start();
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String msg;
			while ((msg = in.readLine()) != null) {
				chatContentBox.append(msg + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (client != null)
					client.close();
				if (in != null)
					in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	
	class ClientThread extends Thread {
		private PrintWriter out;

		public void run() {
            try {
				FileWriter fileWritter = new FileWriter("D:\\vscode workspace\\java\\Chatrecord.txt",true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWritter); 
            	out = new PrintWriter(client.getOutputStream(), true);
        		if(name == ""){
        			out.println(client.getLocalPort()+" : "+"我加入了聊天室\n");
        		}
        		else{
        			out.println(name+" : "+"我加入了聊天室\n");
        		}
                changeNameBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a){
                    	if(newName.getText().length()!=0){
                    		if(name == ""){
                    			out.println(client.getLocalPort()+" : "+"我改名为"+'"'+newName.getText()+'"'+"\n");
                    		}
                    		else{
                    			out.println(name+" : "+"我改名为"+'"'+newName.getText()+'"'+"\n");
                    		}
                    		name = newName.getText();
                    	}
                    }
                });            	
            	
                exitBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a){
						out.println("已退出聊天室\n");
						try{
							bufferedWriter.close();
						}catch (IOException e) {
							e.printStackTrace();
						}
                    	System.exit(0);
                    }
                });
            	
        		sendBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a){
                    	String sendMsg = inputBox.getText();
                    	if(sendMsg.length()!=0) {
                    		if(name == ""){
								out.println(client.getLocalPort()+" : "+sendMsg+"\n");
								try{
									bufferedWriter.write(client.getLocalPort()+" : "+sendMsg);
									bufferedWriter.newLine();
									bufferedWriter.flush();
								}catch (IOException e) {
									e.printStackTrace();
								}
                    		}
                    		else{
								out.println(name+" : "+sendMsg+"\n");
								try{
									bufferedWriter.write(name+" : "+sendMsg);
									bufferedWriter.newLine();
									bufferedWriter.flush();
								}catch (IOException e) {
									e.printStackTrace();
								}
                    		}
                    		sendMsg = "";
                    		inputBox.setText("");
						}
                    }
				});
		
				inputBox.addKeyListener(new KeyAdapter(){
					public void keyPressed(KeyEvent a) {
						if(a.getKeyCode() == KeyEvent.VK_ENTER){
							String sendMsg = inputBox.getText();
							if(sendMsg.length()!=0) {
								if(name == ""){
									out.println(client.getLocalPort()+" : "+sendMsg+"\n");
									try{
										bufferedWriter.write(client.getLocalPort()+" : "+sendMsg);
										bufferedWriter.newLine();
										bufferedWriter.flush();
									}catch (IOException e) {
										e.printStackTrace();
									}
								}
								else{
									out.println(name+" : "+sendMsg+"\n");
									try{
										bufferedWriter.write(name+" : "+sendMsg);
										bufferedWriter.newLine();
										bufferedWriter.flush();
									}catch (IOException e) {
										e.printStackTrace();
									}
								}
								sendMsg = "";
								inputBox.setText("");
							}
						}
					}
				});
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
    public static void main(String[] args) { new chatClient(); }
}