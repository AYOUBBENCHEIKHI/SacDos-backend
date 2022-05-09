package ben.dev.appSacDos.algorithme;

import java.util.ArrayList;
import java.util.List;

import ben.dev.appSacDos.dao.ProduitRepo;
import ben.dev.appSacDos.model.Produit;
import ben.dev.appSacDos.web.ProduitService;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

public class SacDos {
	double [] p;
	double [] w;
	double capacite;
	int n;
	IloCplex modele;
	IloNumVar [] x;
	
	public SacDos(double[] p, double[] w, double capacite) throws IloException {
		super();
		this.p = p;
		this.w = w;
		this.capacite = capacite;
		modele =new IloCplex();
		n=p.length;
		CreationModele();
		System.out.println(modele.toString());
	}

	private void CreationModele() {
		// TODO Auto-generated method stub
		CreationVariable();
		creationContraints();
		CreationObj();
	}

	private void CreationObj() {
		// TODO Auto-generated method stub
		try {
			IloLinearNumExpr f=modele.linearNumExpr();
			for (int i = 0; i < n; i++) {
				f.addTerm(p[i],x[i]);
			}
			modele.addMaximize(f);
		} catch (IloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void CreationVariable() {
		// TODO Auto-generated method stub
		try {
			x=modele.boolVarArray(n);
		} catch (IloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void creationContraints() {
		// TODO Auto-generated method stub
		try {
			IloLinearNumExpr f=modele.linearNumExpr();
			for (int i = 0; i < n; i++) {
				f.addTerm(w[i],x[i]);
			}
			modele.addLe(f,capacite);
		} catch (IloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean solve () {
		boolean hasSolved= false ;
		try {
		hasSolved= modele.solve ();
		} catch ( IloException e){
		System . err. println (" Concert exception caught : " + e );}
		return hasSolved;
		
	}
	public double [] getSolution() {
		double [] s=new double [n];
		try {
			s = modele.getValues(x);
		} catch ( IloException ex ){
			System . err. println (" Concert exception caught : " + ex );
		}
		return s ;
		}
	public List<Integer> getsolutionSac() {
		List<Integer> sol = new ArrayList<Integer>();
		if ( solve ()){
			double [] d= getSolution();
			for ( int i =0; i<d. length ;i ++){
				if (d[ i]==1)
					sol.add( i);
				}
			}
			return sol;
		}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		double [] b ={12 ,2 ,1 ,4 ,1};
		double [] c ={4 ,2 ,1 ,10 ,2};
		SacDos ex1;
		try {
			ex1 = new SacDos (c, b, 15);
			List<Integer> sol = ex1. getsolutionSac();
			System.out.println(sol.get(1));
			;
			for(int i=1;i<=p.size();i++) {
				System.out.println(p.get(i));
			}
		} catch (IloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}*/

}
