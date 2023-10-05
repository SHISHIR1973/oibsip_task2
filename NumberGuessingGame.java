import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.border.*;

public class NumberGuessingGame extends JFrame {
    private JButton exit_btn;
    private JButton Next_attempt_btn;
    private JButton start_btn;
    private JButton new_game_btn;

    static JPanel panel1;
    static JPanel panel2;
    static JPanel panel3;
    static JPanel btnpanel;

    static JLabel header;

    static JLabel C_round;
    static JLabel C_round_box;
    static JLabel C_attempt;
    static JLabel C_attempt_box;
    static JLabel T_attempt;
    static JLabel T_attempt_box;

    static JLabel Generated_num;
    static JLabel Guessed_num;
    static JLabel Generated_num_display;
    static JLabel Compare_num_display;
    static JLabel Guessed_num_display;
    static JLabel Scorelabel;
    static JLabel Accuracylabel;
    static JLabel Scorelabel_box;
    static JLabel Accuracylabel_box;

    static JLabel T_round;
    static JComboBox<String> Total_round_box;
    static JLabel Dummy;
    static JLabel Total_attempts;
    static JComboBox<String> Total_attempt_box;

    static String T_Round = "";
    static String T_Attempt="";
    static double Gen_Num;
    static String Guessed_Num;
    static String round_set[] = {"Unlimited","1","2","3","5","6","12","15","20"};
    static String attempt_set[] = {"None","1","2","3","5","6","10"};

    static int success = 0;
    static float success_count =0;
    static float accurate = 0;
    static int Curr_rnd =1;
    static int Curr_atmt = 1;
    static int Tot_atmt= 0;

    public static void Calculate(){
        T_attempt_box.setText(String.valueOf(Tot_atmt));
        Guessed_Num = JOptionPane.showInputDialog(null,"Enter your Guessed Number:");
        while (Guessed_Num.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please enter a Number.","ERROR",JOptionPane.ERROR_MESSAGE);
            Guessed_Num = JOptionPane.showInputDialog("Enter your Guessed NUMBER:");
        }
        Generated_num_display.setText("X");
        Guessed_num_display.setText(Guessed_Num);
        if ((int)Gen_Num > Integer.parseInt(Guessed_Num)){
            Compare_num_display.setText(">");
        }
        else if ((int)Gen_Num < Integer.parseInt(Guessed_Num)){
            Compare_num_display.setText("<");
        }
        else{
            Generated_num_display.setText(String.valueOf((int)Gen_Num));
            Compare_num_display.setText("=");
            success+=10;
            success_count+=1;
            accurate = 100*(success_count/Tot_atmt);
            Scorelabel_box.setText(String.valueOf(success));
            Accuracylabel_box.setText(new DecimalFormat("##.##").format(accurate)+"%");
            
            JOptionPane.showMessageDialog(null,"You Guessed the Number!! \n Check Your Score.","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
            panel2.removeAll();

        }
        Curr_atmt+=1;

    }
    
    public static void StartGame(){
        if (T_Round != "" && T_Attempt != ""){
            int No_of_rnd = Integer.parseInt(T_Round);
            int No_of_atmt = Integer.parseInt(T_Attempt);
            if (Curr_rnd<=No_of_rnd){
                C_round_box.setText(String.valueOf(Curr_rnd));
                if (Curr_atmt<=No_of_atmt){
                    C_attempt_box.setText(String.valueOf(Curr_atmt));
                    Tot_atmt+=1;
                    Calculate();
                }
                else{
                    JOptionPane.showMessageDialog(null,("Failed to Guess the Number !! \nThe number is "+String.valueOf((int)Gen_Num)+"\nGo to next round with new Generated number..."),"MESSAGE",JOptionPane.INFORMATION_MESSAGE);
                    Gen_Num=Math.random() * 100 + 1;
                    Curr_atmt=1;
                    Curr_rnd+=1;
                    StartGame();
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Game Over!!","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
                panel2.removeAll();
            }
        }
        else{
            Tot_atmt+=1;
            Calculate();
        }    

    }

    public NumberGuessingGame(){
        ImageIcon obj = new ImageIcon("background1.jpg");
        Image img=obj.getImage();
        Image temp=img.getScaledInstance(1600,900,Image.SCALE_SMOOTH);
        obj = new ImageIcon(temp);
        JLabel bg=new JLabel("",obj,JLabel.CENTER);
        bg.setBounds(0,0,1600,900);
        add(bg);

        bg.setLayout(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();
        c.anchor= GridBagConstraints.WEST;
        c.fill=GridBagConstraints.HORIZONTAL;

        // window top label
        header=new JLabel("NUMBER GUESSING GAME",JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 60));
        header.setForeground(Color.WHITE);

        // game stage
        panel1 = new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon obj1 = new ImageIcon("panel1.jpg");
                Image img1=obj1.getImage();
                Image temp1=img1.getScaledInstance(1600,900,Image.SCALE_SMOOTH);
                obj1 = new ImageIcon(temp1);
                g.drawImage(obj1.getImage(),0,0,this.getWidth(),this.getHeight(),null);
            }
        };
        panel1.setPreferredSize(new Dimension(1500,100));
        // panel1.setBackground(Color.GRAY);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.WHITE,Color.BLACK),"Game Stage"));
        ((javax.swing.border.TitledBorder)panel1.getBorder()).setTitleFont(new Font("Arial", Font.BOLD, 20));
        ((javax.swing.border.TitledBorder)panel1.getBorder()).setTitleColor(Color.WHITE);
        GridBagConstraints constraints1= new GridBagConstraints();
        constraints1.anchor= GridBagConstraints.WEST;
        constraints1.insets= new Insets(10,10,10,10);
        
        Border black = BorderFactory.createLineBorder(Color.BLACK);

        // game stage components
        C_round=new JLabel("CURRENT ROUND:",JLabel.CENTER);
        constraints1.gridx=0;
        constraints1.gridy=0;
        C_round.setFont(new Font("Arial", Font.BOLD, 20));
        C_round.setForeground(Color.WHITE);
        panel1.add(C_round,constraints1);

        C_round_box=new JLabel(" ");
        C_round_box.setBorder(black);
        C_round_box.setFont(new Font("Arial", Font.BOLD, 20));
        C_round_box.setOpaque(true);
        C_round_box.setBackground(Color.WHITE);
        C_round_box.setForeground(Color.BLACK);
        C_round_box.setPreferredSize(new Dimension(200,25));
        constraints1.gridx=1;
        constraints1.gridy=0;
        panel1.add(C_round_box,constraints1);

        C_attempt=new JLabel("ROUND ATTEMPT:",JLabel.CENTER);
        constraints1.gridx=2;
        constraints1.gridy=0;
        C_attempt.setFont(new Font("Arial", Font.BOLD, 20));
        C_attempt.setForeground(Color.WHITE);
        panel1.add(C_attempt,constraints1);

        C_attempt_box=new JLabel(" ");
        C_attempt_box.setBorder(black);
        C_attempt_box.setFont(new Font("Arial", Font.BOLD, 20));
        C_attempt_box.setOpaque(true);
        C_attempt_box.setBackground(Color.WHITE);
        C_attempt_box.setForeground(Color.BLACK);
        C_attempt_box.setPreferredSize(new Dimension(200,25));
        constraints1.gridx=3;
        constraints1.gridy=0;
        panel1.add(C_attempt_box,constraints1);

        T_attempt=new JLabel("TOTAL ATTEMPT:",JLabel.CENTER);
        constraints1.gridx=4;
        constraints1.gridy=0;
        T_attempt.setFont(new Font("Arial", Font.BOLD, 20));
        T_attempt.setForeground(Color.WHITE);
        panel1.add(T_attempt,constraints1);

        T_attempt_box=new JLabel(" ");
        T_attempt_box.setFont(new Font("Arial", Font.BOLD, 20));
        T_attempt_box.setBorder(black);
        T_attempt_box.setOpaque(true);
        T_attempt_box.setBackground(Color.WHITE);
        T_attempt_box.setForeground(Color.BLACK);
        T_attempt_box.setPreferredSize(new Dimension(200,25));
        constraints1.gridx=5;
        constraints1.gridy=0;
        panel1.add(T_attempt_box,constraints1);

        // game display
        panel2= new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon obj2 = new ImageIcon("panel2.jpg");
                Image img2=obj2.getImage();
                Image temp2=img2.getScaledInstance(1600,900,Image.SCALE_SMOOTH);
                obj2 = new ImageIcon(temp2);
                g.drawImage(obj2.getImage(),0,0,this.getWidth(),this.getHeight(),null);
            }
        };
        panel2.setPreferredSize(new Dimension(1500,450));
        // panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.WHITE,Color.BLACK),"Display"));
        ((javax.swing.border.TitledBorder)panel2.getBorder()).setTitleFont(new Font("Arial", Font.BOLD, 20));
        ((javax.swing.border.TitledBorder)panel2.getBorder()).setTitleColor(Color.WHITE);
        GridBagConstraints constraints2= new GridBagConstraints();
        constraints2.anchor= GridBagConstraints.WEST;
        constraints2.insets= new Insets(10,10,10,10);

        // game dispaly components
        Generated_num=new JLabel("GENERATED NUMBER",JLabel.CENTER);
        Generated_num.setFont(new Font("Arial", Font.BOLD, 30));
        Generated_num.setOpaque(true);
        Generated_num.setBackground(new Color(255,255,255,80));
        Generated_num.setForeground(new Color(93, 28, 71));
        constraints2.gridx=0;
        constraints2.gridy=0;
        panel2.add(Generated_num,constraints2);

        Guessed_num=new JLabel("GUESSED NUMBER",JLabel.CENTER);
        Guessed_num.setFont(new Font("Arial", Font.BOLD, 30));
        Guessed_num.setOpaque(true);
        Guessed_num.setBackground(new Color(255,255,255,80));
        Guessed_num.setForeground(new Color(51, 24, 86));
        constraints2.gridx=2;
        constraints2.gridy=0;
        panel2.add(Guessed_num,constraints2);
        
        Generated_num_display=new JLabel(" ",JLabel.CENTER);
        Generated_num_display.setFont(new Font("Arial", Font.BOLD, 80));
        Generated_num_display.setForeground(Color.CYAN);
        // Generated_num_display.setBackground(Color.GRAY);
        Generated_num_display.setPreferredSize(new Dimension(300,100));
        constraints2.gridx=0;
        constraints2.gridy=1;
        panel2.add(Generated_num_display,constraints2);

        Compare_num_display=new JLabel(" ",JLabel.CENTER);
        Compare_num_display.setFont(new Font("Arial", Font.BOLD, 80));
        Compare_num_display.setForeground(Color.YELLOW);
        // Compare_num_display.setBackground(Color.GRAY);
        Compare_num_display.setPreferredSize(new Dimension(150,100));
        constraints2.gridx=1;
        constraints2.gridy=1;
        panel2.add(Compare_num_display,constraints2);

        Guessed_num_display=new JLabel(" ",JLabel.CENTER);
        Guessed_num_display.setFont(new Font("Arial", Font.BOLD, 80));
        Guessed_num_display.setForeground(Color.CYAN);
        // Guessed_num_display.setBackground(Color.GRAY);
        Guessed_num_display.setPreferredSize(new Dimension(250,100));
        constraints2.gridx=2;
        constraints2.gridy=1;
        panel2.add(Guessed_num_display,constraints2);

        Scorelabel=new JLabel("SCORE",JLabel.CENTER);
        Scorelabel.setFont(new Font("Arial", Font.BOLD, 25));
        Scorelabel.setForeground(Color.WHITE);
        Scorelabel.setPreferredSize(new Dimension(300,70));
        Scorelabel.setVerticalAlignment(JLabel.BOTTOM);
        constraints2.gridx=0;
        constraints2.gridy=2;
        panel2.add(Scorelabel,constraints2);

        Accuracylabel=new JLabel("ACCURACY",JLabel.CENTER);
        Accuracylabel.setFont(new Font("Arial", Font.BOLD, 25));
        Accuracylabel.setForeground(Color.WHITE);
        Accuracylabel.setPreferredSize(new Dimension(300,70));
        Accuracylabel.setVerticalAlignment(JLabel.BOTTOM);
        constraints2.gridx=2;
        constraints2.gridy=2;
        panel2.add(Accuracylabel,constraints2);

        Scorelabel_box=new JLabel(" 0",JLabel.CENTER);
        Scorelabel_box.setFont(new Font("Arial", Font.BOLD, 30));
        Scorelabel_box.setForeground(Color.GREEN);
        Scorelabel_box.setPreferredSize(new Dimension(280,35));
        constraints2.gridx=0;
        constraints2.gridy=3;
        panel2.add(Scorelabel_box,constraints2);

        Accuracylabel_box=new JLabel(" 0%",JLabel.CENTER);
        Accuracylabel_box.setFont(new Font("Arial", Font.BOLD, 30));
        Accuracylabel_box.setForeground(Color.GREEN);
        Accuracylabel_box.setPreferredSize(new Dimension(290,35));
        constraints2.gridx=2;
        constraints2.gridy=3;
        panel2.add(Accuracylabel_box,constraints2);

        Next_attempt_btn=new JButton("NEXT ATTEMPT");
        Next_attempt_btn.setBackground(Color.BLUE);
        Next_attempt_btn.setForeground(Color.WHITE);
        Next_attempt_btn.setFont(new Font("Arial", Font.BOLD, 20));
        Next_attempt_btn.addActionListener(e -> {StartGame();});
        constraints2.gridx=1;
        constraints2.gridy=4;
        panel2.add(Next_attempt_btn,constraints2);

        // game controls
        panel3= new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon obj3 = new ImageIcon("panel3.jpg");
                Image img3=obj3.getImage();
                Image temp3=img3.getScaledInstance(1600,900,Image.SCALE_SMOOTH);
                obj3 = new ImageIcon(temp3);
                g.drawImage(obj3.getImage(),0,0,this.getWidth(),this.getHeight(),null);
            }
        };
        panel3.setPreferredSize(new Dimension(1500,250));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.WHITE,Color.BLACK),"Game Type"));
        ((javax.swing.border.TitledBorder)panel3.getBorder()).setTitleFont(new Font("Arial", Font.BOLD, 20));
        ((javax.swing.border.TitledBorder)panel3.getBorder()).setTitleColor(Color.WHITE);
        GridBagConstraints constraints3= new GridBagConstraints();
        constraints3.anchor= GridBagConstraints.WEST;
        constraints3.insets= new Insets(10,10,10,10);

        // game controls components
        T_round=new JLabel("NO. OF ROUNDS:",JLabel.CENTER);
        constraints3.gridx=0;
        constraints3.gridy=0;
        T_round.setFont(new Font("Arial", Font.BOLD, 25));
        T_round.setForeground(Color.WHITE);
        panel3.add(T_round,constraints3);

        Total_round_box = new JComboBox<String>(round_set);
        Total_round_box.setFont(new Font("Arial", Font.BOLD, 25));
        Total_round_box.setPreferredSize(new Dimension(200,26));
        Total_round_box.setForeground(Color.BLACK);
        Total_round_box.setBackground(Color.WHITE);       
        constraints3.gridx=1;
        constraints3.gridy=0;
        panel3.add(Total_round_box,constraints3);

        Total_round_box.addItemListener(e -> {if(T_Round == ""){T_Round = (String) Total_round_box.getSelectedItem();}});

        Dummy = new JLabel();
        Dummy.setPreferredSize(new Dimension(200,26));
        constraints3.gridx=2;
        constraints3.gridy=0;
        panel3.add(Dummy,constraints3);

        Total_attempts=new JLabel("NO. OF ATTEMPTS:",JLabel.CENTER);
        constraints3.gridx=3;
        constraints3.gridy=0;
        Total_attempts.setFont(new Font("Arial", Font.BOLD, 25));
        Total_attempts.setForeground(Color.WHITE);
        panel3.add(Total_attempts,constraints3);
        
        Total_attempt_box = new JComboBox<String>(attempt_set);
        Total_attempt_box.setFont(new Font("Arial", Font.BOLD, 25));
        Total_attempt_box.setPreferredSize(new Dimension(200,26));
        Total_attempt_box.setForeground(Color.BLACK);
        Total_attempt_box.setBackground(Color.WHITE);
        constraints3.gridx=4;
        constraints3.gridy=0;
        panel3.add(Total_attempt_box,constraints3);

        Total_attempt_box.addItemListener(e -> {if (T_Attempt == ""){T_Attempt = (String) Total_attempt_box.getSelectedItem();}});

        btnpanel = new JPanel(new GridLayout(1,5,80,0));
        btnpanel.setOpaque(true);
        btnpanel.setBackground(new Color(0,0,0,0));

        JLabel hiddenlbl = new JLabel();
        hiddenlbl.setPreferredSize(new Dimension(100,20));
        hiddenlbl.setOpaque(true);
        hiddenlbl.setBackground(new Color(0,0,0,0));
        btnpanel.add(hiddenlbl);
        
        new_game_btn=new JButton("NEW GAME");
        new_game_btn.setBackground(Color.ORANGE);
        new_game_btn.setForeground(Color.BLACK);
        new_game_btn.setFont(new Font("Arial", Font.BOLD, 20));
        new_game_btn.addActionListener(e -> {
            this.dispose();
            success = 0;
            success_count =0;
            accurate = 0;
            Curr_rnd =1;
            Curr_atmt = 1;
            Tot_atmt= 0;
            new NumberGuessingGame();});
        btnpanel.add(new_game_btn);

        start_btn=new JButton("START GAME");
        start_btn.setBackground(Color.GREEN);
        start_btn.setForeground(Color.BLACK);
        start_btn.setFont(new Font("Arial", Font.BOLD, 20));
        start_btn.addActionListener(e -> {Gen_Num=Math.random() * 100 + 1; StartGame();});
        btnpanel.add(start_btn);

        exit_btn=new JButton("EXIT");
        exit_btn.setBackground(Color.RED);
        exit_btn.setForeground(Color.BLACK);
        exit_btn.setFont(new Font("Arial", Font.BOLD, 20));
        exit_btn.addActionListener(e -> System.exit(0));
        btnpanel.add(exit_btn);
        
        // adding btnpanel to bottom panel
        constraints3.gridx=0;
        constraints3.gridy=1;
        constraints3.gridwidth=5;
        panel3.add(btnpanel,constraints3);

        // adding all panels to jframe
        c.gridx=0;
        c.gridy=0;
        bg.add(header,c);
        c.gridx=0;
        c.gridy=1;
        bg.add(panel1,c);
        c.gridx=0;
        c.gridy=2;
        bg.add(panel2,c);
        c.gridx=0;
        c.gridy=3;
        bg.add(panel3,c);

        // configuration for jframe
        setSize(1600,900);
        setTitle("Number Guessing Game");
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    } 
    public static void main(String []args) throws Exception{
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
    
}
