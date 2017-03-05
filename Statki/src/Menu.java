import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by Dell on 05.03.2017.
 */
public class Menu extends JFrame implements ActionListener
{
    private JTextField log, haslo;
    private JLabel Llog, Lhaslo;
    private JButton zaloguj, wyjscie,rejestr;

    public Menu()
    {
        setSize(800,600);
        setTitle("STATKI Menu");
        setLayout(null);

        Llog=new JLabel("Login:");
        Llog.setBounds(50,50,100,20);
        add(Llog);

        Lhaslo=new JLabel("Hasło:");
        Lhaslo.setBounds(50,75,100,20);
        add(Lhaslo);

        log=new JTextField("");
        log.setBounds(150,50,200,20);
        add(log);

        //ustawić ukrywanie wpisywanego tekstu dla hasła
        haslo=new JTextField("");
        haslo.setBounds(150,75,200,20);
        add(haslo);

        zaloguj=new JButton("Zaloguj");
        zaloguj.setBounds(50,100,150,20);
        add(zaloguj);
        zaloguj.addActionListener(this);

        wyjscie=new JButton("Wyjście");
        wyjscie.setBounds(50,125,150,20);
        add(wyjscie);
        wyjscie.addActionListener(this);

        rejestr=new JButton("Rejestracja");
        rejestr.setBounds(50,150,150,20);
        add(rejestr);
        rejestr.addActionListener(this);

    }
    public static void main(String[] args)
    {
        Menu menu=new Menu();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        Object zrodlo=e.getSource();

        if(zrodlo==zaloguj)
        {
            //Listener dla buttona zaloguj

            //SPRAWDZENIE CZY LOGIN I HASŁO JEST POPRAWNE, UTWORZENIE BAZY DANYCH
            String login,hasło;
            login=log.getText();
            hasło=haslo.getText();


        }
        else if(zrodlo==wyjscie)
        {
            //Listener dla buttona wyjscie
            dispose();
        }
        else if(zrodlo==rejestr)
        {
            //Listener dla buttona rejestracji
            Rejestracja okno = new Rejestracja();
            okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            okno.setVisible(true);
        }
    }

}
