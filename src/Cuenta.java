import java.util.*;

public class Cuenta {

    private double balance; 
    private Usuario userAccount; 
    private List<Gasto> expenses; 
    private List<Ingreso>income; 

    public Cuenta(Usuario usuarioCuenta) {
        this.userAccount=usuarioCuenta;
        this.balance=0;
        this.expenses=new ArrayList<Gasto>();
        this.income=new ArrayList<Ingreso>();
    }


    public void setUsuarioCuenta(Usuario usuarioCuenta) {
        this.userAccount = usuarioCuenta;
    }


    public void setSaldo(double saldo) {
        this.balance = saldo;
    }
  
    public double getSaldo() {
        return balance;
    }
  
    public Usuario getUsuarioCuenta() {
        return userAccount;
    }
    
  
    public List<Gasto> getGastos() {
        return expenses;
    }
    

    public List<Ingreso> getIngresos() {
        return income;
    }
    
    
    public double addIngresos(String description, double cantidad){
        
       
        Ingreso nuevoIngreso = new Ingreso(cantidad,description);       
        this.income.add(nuevoIngreso);      
        this.balance=this.balance+cantidad;     
        return balance;
    }
    
    
    public double addGastos(String description, double cantidad){
        
        try{ 
            this.balance = this.balance-cantidad;
            
            if(this.getSaldo()<0){ 
                throw new GastoException();
            }
            
        }catch(GastoException e){
            return -1;
        }
       
        Gasto nuevoGasto=new Gasto(cantidad,description);       
        expenses.add(nuevoGasto);     
        return balance;
    }
    
    @Override
    public String toString(){
        return "Bienvenido " + this.userAccount.getNombre() + ", el saldo de tu cuenta es " + this.balance;
    }
}
