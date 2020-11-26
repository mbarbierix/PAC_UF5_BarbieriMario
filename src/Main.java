import java.util.Scanner;

public class Main {
	
	//Variables
	
    private static final Scanner read = new Scanner(System.in);   
    private static String option="";  
    private static byte numOption=-1;
    private static Usuario newUser=new Usuario();  
    private static String nameUser="";   
    private static String ageUse="";    
    private static byte ageUser=-1;   
    private static String dniUser="";
    private static boolean userCreate=false; 
    private static Cuenta newAccount=null;  
    private static String amount="";  
    private static double totalAmount=0; 
    private static boolean negativeBalance;
    private static String description="";
    
    
    //Metodos
    
    private static void verMenu(){
        
        do{
        System.out.println("Realiza una nueva accion \n");	
        System.out.println("1. Introducir un nuevo gasto");
        System.out.println("2. Introducir un nuevo ingreso");
        System.out.println("3. Mostrar gastos");
        System.out.println("4. Mostrar ingresos");
        System.out.println("5. Mostrar saldo de la cuenta");
        System.out.println("6. Salir");
        
        try{
        
            
            System.out.print("Introduce una opcion del menu: ");
            option = read.nextLine();        
            numOption = Byte.parseByte(option);
        }
        catch(NumberFormatException e){
            System.out.println(e.getMessage());
            System.out.println("La opcion elegida debe ser un numero entre 1 y 6");
        }
        }while(numOption<0 && numOption>5);
    }
    
 
    private static void solicitarDatosUsuario(){
        userCreate=false;
      
        do{
            System.out.println("Introduce el nombre del usuario");
            nameUser=read.nextLine().toUpperCase();
        }while(nameUser.isEmpty());
        newUser.setNombre(nameUser);
        
        do{
            System.out.println("Introduce la edad del usuario");
            ageUse=read.nextLine();
      
            try{
                ageUser = Byte.parseByte(ageUse);
                newUser.setEdad(ageUser);
            }catch(NumberFormatException e){
                System.out.println("La edad debe ser numerica, mayor que cero y menor de 128");
            }
        }while(ageUse.isEmpty() || ageUser<=0);
        
        
     
        do{
            System.out.println("Introduce el DNI del usuario");
            dniUser=read.nextLine().toUpperCase();
            
           
            if(newUser.setDNI(dniUser)){
                newUser.setDNI(dniUser);
            }
            else{
                System.out.println("El DNI introducido no es correcto. Vuelva a introducir el DNI");
            }
        }while(dniUser.isEmpty() || newUser.setDNI(dniUser)==false);
        
        userCreate=true;
    }
    
    
    private static void introducirIngresos(){
        
        amount="";
        description="";
        totalAmount=0;
        
        do{
            System.out.print("Introduce una descripcion: ");
            description = read.nextLine();
            
        }while(description.isEmpty());
        
        do{
            System.out.print("Introduce el importe del ingreso: ");
            amount = read.nextLine();
            
            try{ 
                totalAmount=Double.parseDouble(amount);
            }catch(NumberFormatException e){
                System.out.println("El importe del ingreso debe ser numerico " + e.getMessage());
            }
        }while(amount.isEmpty());
        
     
        newAccount.addIngresos(description, totalAmount);
        System.out.println("Ingreso registrado correctamente");
    }
   
   
    private static void introducirGastos(){
            
        amount="";
        description="";
        totalAmount=0;
        
        do{
            System.out.print("Introduce una descripcion: ");
            description = read.nextLine();
            
        }while(description.isEmpty());
        
      
        do{
            System.out.print("Introduce el importe del gasto: ");
            amount = read.nextLine();
            
            try{ 
                totalAmount=Double.parseDouble(amount);
            }catch(Exception e){
                System.out.println("El importe del gasto debe ser numerico  " + e.getMessage());
            }         
            
        }while(amount.isEmpty());
            
	        if(newAccount.getSaldo()<totalAmount || newAccount.getSaldo()==0){
	            
	            System.out.println("Es necesario realizar primero un ingreso para registrar un gasto");                
	        }
       
        else{      
            newAccount.addGastos(description, totalAmount);
            System.out.println("Gasto correctamente registrado.");  
        }
    }
    
   
    private static void mostrarGastos(){
        
     
       if(!newAccount.getGastos().isEmpty()){
           
           for(int x=0;x<newAccount.getGastos().size();x++){
               System.out.println(newAccount.getGastos().get(x).toString());
           }
       }
       else{ 
           System.out.println("No existen gastos en la cuenta.");
       }
    }
    
    private static void mostrarIngresos(){
        
   
       if(!newAccount.getIngresos().isEmpty()){
         
           for(int x=0;x<newAccount.getIngresos().size();x++){
               System.out.println(newAccount.getIngresos().get(x).toString());
           }
       }
       else{
           System.out.println("No existen ingresos en la cuenta.");
       }
    }
    
    //Main
 
    public static void main(String[] args) {
        
    
        do{
           solicitarDatosUsuario();
       }while(userCreate=false);
        
       
        System.out.println("Datos del nuevo usuario:" + "\n");
        System.out.println(newUser.toString());
        
    
        newAccount=new Cuenta(newUser);
        
     
        do{ 
            
            verMenu();

       
            switch(numOption){

                case 1: 
                    introducirGastos();
                    break;

                case 2: 
                    introducirIngresos();
                    break;

                case 3: 
                    mostrarGastos();
                    break;

                case 4: 
                        mostrarIngresos();
                    break;

                case 5: 
                    System.out.println(newAccount.toString());
                    break;
                    
                case 6: 
                    System.out.println("Programa finalizado." + "\n"
                            + "Gracias por utilizar la aplicacionn.");
                    break;

                default: 

                    System.out.println("Introduzca un valor correcto");
                    break;
            }
            
        }while(numOption !=6);
        read.close();
    }
    
}

