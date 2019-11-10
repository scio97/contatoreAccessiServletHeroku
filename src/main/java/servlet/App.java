package servlet;
import java.io.IOException;
import java.io.PrintWriter;
//import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "AppServlet",
        urlPatterns = {"/prova"}
	)
	
public class App extends HttpServlet{
    private static final long serialVersionUID = -3967314453512919811L;
    public static void main(String[] args) throws Exception {
        Dizionario d = new Dizionario();
        d.lenght();
    }
    boolean go=true;
    /*private String mymsg;
    public void init(ServletConfig config) throws ServletException 
    {     
       super.init(config);
       mymsg = config.getInitParameter("title");
       //mymsg = Constants.TITOLO;   
    }*/
    Dizionario d = new Dizionario();
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        if(go){
            d.add("abbondante","copioso, esuberante, nutrito","scarso, insufficiente, mancante");
            d.add("babbeo","babbione, babbuasso, baggiano","furbo, scaltro, astuto");
            d.add("cafone","copioso, esuberante, nutrito","signore, gentiluomo, gentleman");
            d.add("ubbidiente","obbediente","disubbidiente, indisciplinato, sfrenato");
            d.add("vacuo","povero, misero, scarso", "ricco, abbondante");
            go=false;
        }
        String mod = req.getParameter("mod");
        int vocaboli=d.lenght();
        res.setContentType("text/html");      
        PrintWriter out = res.getWriter(); 
        out.println("<h1>Dizionario dei sinonimi e dei contrari</h1>");
        if(mod.equals("cerca")){
            String termine = req.getParameter("termine").toLowerCase();
            String tipologia = req.getParameter("tipologia");
            String risultato = d.trova(termine, tipologia);
            out.println("<h3>Risultato ricerca:</h3>");     
            out.println("<p>Termine ricercato: " + termine +" Tipologia ricerca: " + tipologia + "</p>");
            out.println("<p>Risultato ricerca: "+ risultato + "</p>");
        }
        if(mod.equals("aggiungi")){
            String termine = req.getParameter("termine").toLowerCase();
            String sinonimi = req.getParameter("sinonimi").toLowerCase();
            String contrari = req.getParameter("contrari").toLowerCase();
            d.add(termine,sinonimi,contrari);
            vocaboli++;
            out.println("<h3>Termine inserito</h3>");

		}
		if(mod.equals("modifica")){
			String termine = req.getParameter("termine").toLowerCase();
            String sinonimi = req.getParameter("sinonimi").toLowerCase();
			String contrari = req.getParameter("contrari").toLowerCase();
			if(d.modifica(termine, sinonimi, contrari)){;
			out.println("<h3>modifica completata</h3>");
			}else{
				out.println("<h3>Termine non trovato</h3>");
			}
		}
		if(mod.equals("elimina")){
			String termine = req.getParameter("termine").toLowerCase();
			if(d.elimina(termine)){
				vocaboli--;
				out.println("<h3>Termine eliminato</h3>");
			}else{
				out.println("<h3>Termine non trovato</h3>");
			}
		}
        out.println("<p>Numero di vocaboli presenti nel dizionario: "+vocaboli + "</p>");
        out.println("<a href='http://localhost:8080'>link home</a>");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        if(go){
            d.add("abbondante","copioso, esuberante, nutrito","scarso, insufficiente, mancante");
            d.add("babbeo","babbione, babbuasso, baggiano","furbo, scaltro, astuto");
            d.add("cafone","copioso, esuberante, nutrito","signore, gentiluomo, gentleman");
            d.add("ubbidiente","obbediente","disubbidiente, indisciplinato, sfrenato");
            d.add("vacuo","povero, misero, scarso", "ricco, abbondante");
            go=false;
        }
        String mod = req.getParameter("mod");
        int vocaboli=d.lenght();
        res.setContentType("text/html");      
        PrintWriter out = res.getWriter(); 
        out.println("<h1>Dizionario dei sinonimi e dei contrari</h1>");
        if(mod.equals("cerca")){
            String termine = req.getParameter("termine").toLowerCase();
            String tipologia = req.getParameter("tipologia");
            String risultato = d.trova(termine, tipologia);
            out.println("<h3>Risultato ricerca:</h3>");     
            out.println("<p>Termine ricercato: " + termine +" Tipologia ricerca: " + tipologia + "</p>");
            out.println("<p>Risultato ricerca: "+ risultato + "</p>");
        }
        if(mod.equals("aggiungi")){
            String termine = req.getParameter("termine").toLowerCase();
            String sinonimi = req.getParameter("sinonimi").toLowerCase();
            String contrari = req.getParameter("contrari").toLowerCase();
            d.add(termine,sinonimi,contrari);
            vocaboli++;
            out.println("<h3>Termine inserito</h3>");

		}
		if(mod.equals("modifica")){
			String termine = req.getParameter("termine").toLowerCase();
            String sinonimi = req.getParameter("sinonimi").toLowerCase();
			String contrari = req.getParameter("contrari").toLowerCase();
			if(d.modifica(termine, sinonimi, contrari)){;
			out.println("<h3>modifica completata</h3>");
			}else{
				out.println("<h3>Termine non trovato</h3>");
			}
		}
		if(mod.equals("elimina")){
			String termine = req.getParameter("termine").toLowerCase();
			if(d.elimina(termine)){
				vocaboli--;
				out.println("<h3>Termine eliminato</h3>");
			}else{
				out.println("<h3>Termine non trovato</h3>");
			}
		}
        out.println("<p>Numero di vocaboli presenti nel dizionario: "+vocaboli + "</p>");
        out.println("<a href='http://localhost:8080'>link home</a>");
    }
}

class Nodo{
	String valore;
	String sinonimi;
	String contrari;
	Nodo next;

	public Nodo(String valore, String sinonimi, String contrari){
		this.valore=valore;
		this.sinonimi=sinonimi;
		this.contrari=contrari;
		this.next=null;
	}
}

class Dizionario{
	Nodo head;

	public Dizionario(){
		head=null;
	}

	public String trova(String termine,String tipologia){
		Nodo tmp = head;
		if(tipologia.equals("s")||tipologia.equals("c")){
			for (int i=0;i<lenght();i++){
				if(termine.equals(tmp.valore)){
					if(tipologia.equals("s")){
						return tmp.sinonimi;
					}
					if(tipologia.equals("c")){
						return tmp.contrari;
					}
				}
			tmp=tmp.next;
			}
		}else{
			return "Tipologia di ricerca non supportata";
		}
		return "Termine non trovato";
	}

	public boolean modifica(String termine, String sinonimi, String contrari){
		Nodo tmp = head;
		for(int i=0;i<lenght();i++){
			if(termine.equals(tmp.valore)){
				tmp.sinonimi=tmp.sinonimi+", "+sinonimi;
				tmp.contrari=tmp.contrari+", "+contrari;
				return true;
			}
			tmp=tmp.next;
		}
		return false;
	}

	public boolean elimina(String termine){
		Nodo tmp = head;
		if(tmp.valore.equals(termine)){
			head=tmp.next;
			return true;
		}
		while(tmp.next!=null){
			if(termine.equals(tmp.next.valore)){
				tmp.next=tmp.next.next;
				return true;
			}
			tmp=tmp.next;
		}return false;
	}

	public void add(String valoreNodo, String sinonimiNodo, String contrariNodo){
		Nodo n = new Nodo(valoreNodo,sinonimiNodo,contrariNodo);
		Nodo tmp=head;

		if (head==null){
			head=n;
		}else{
			while(tmp.next!=null){
				tmp=tmp.next;
			}
			tmp.next=n;
		}
	}

	public int lenght(){
		Nodo tmp=head;
		int lenght=0;
		if(tmp==null){
			return 0;
		}else{
			while(tmp.next!=null){
				tmp=tmp.next;
				lenght++;
			}
			lenght++;
			return lenght;
		}
	}
}